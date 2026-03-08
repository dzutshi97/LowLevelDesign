/**
Concurrent Web Scraper: Build a web scraper that concurrently fetches and processes data 
from multiple websites. Utilize Java's ExecutorService and Callable to perform parallel tasks efficiently

1. ExecutorService
Thread pool management - reuses threads instead of creating new ones
newFixedThreadPool(n) creates a pool with exactly n threads
Other options: newCachedThreadPool(), newSingleThreadExecutor(), newScheduledThreadPool()
2. Callable vs Runnable
Callable	Runnable
Returns a result (V call())	Returns nothing (void run())
Can throw checked exceptions	Cannot throw checked exceptions
Returns Future<V> when submitted	Returns Future<?>
3. Future API
get() - blocks until result is available
get(timeout, unit) - blocks with timeout
isDone() - check if task completed
cancel(mayInterruptIfRunning) - attempt to cancel
**/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConcurrentWebScraper {

    private final ExecutorService executorService;
    private final int timeoutSeconds;

    public ConcurrentWebScraper(int threadPoolSize, int timeoutSeconds) {
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
        this.timeoutSeconds = timeoutSeconds;
    }

    /**
     * Callable task that fetches and processes a single URL
     */
    static class ScraperTask implements Callable<ScrapedResult> {
        private final String url;

        public ScraperTask(String url) {
            this.url = url;
        }

        @Override
        public ScrapedResult call() throws Exception {
            long startTime = System.currentTimeMillis();
            
            HttpURLConnection connection = null;
            try {
                URL urlObj = new URL(url);
                connection = (HttpURLConnection) urlObj.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setRequestProperty("User-Agent", "Mozilla/5.0");

                int responseCode = connection.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    return new ScrapedResult(url, false, "HTTP Error: " + responseCode, 
                            null, System.currentTimeMillis() - startTime);
                }

                // Read the response
                StringBuilder content = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                }

                // Process the content - extract title and links
                String html = content.toString();
                String title = extractTitle(html);
                List<String> links = extractLinks(html);

                long duration = System.currentTimeMillis() - startTime;
                return new ScrapedResult(url, true, title, links, duration);

            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }

        private String extractTitle(String html) {
            Pattern pattern = Pattern.compile("<title>(.*?)</title>", 
                    Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher matcher = pattern.matcher(html);
            return matcher.find() ? matcher.group(1).trim() : "No title found";
        }

        private List<String> extractLinks(String html) {
            List<String> links = new ArrayList<>();
            Pattern pattern = Pattern.compile("href=[\"'](https?://[^\"']+)[\"']", 
                    Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(html);
            while (matcher.find() && links.size() < 10) { // Limit to 10 links
                links.add(matcher.group(1));
            }
            return links;
        }
    }

    /**
     * Result object containing scraped data
     */
    static class ScrapedResult {
        private final String url;
        private final boolean success;
        private final String title;
        private final List<String> links;
        private final long durationMs;

        public ScrapedResult(String url, boolean success, String title, 
                           List<String> links, long durationMs) {
            this.url = url;
            this.success = success;
            this.title = title;
            this.links = links;
            this.durationMs = durationMs;
        }

        @Override
        public String toString() {
            return String.format(
                "URL: %s\nSuccess: %s\nTitle: %s\nLinks Found: %d\nDuration: %dms\n",
                url, success, title, links != null ? links.size() : 0, durationMs
            );
        }

        // Getters
        public String getUrl() { return url; }
        public boolean isSuccess() { return success; }
        public String getTitle() { return title; }
        public List<String> getLinks() { return links; }
        public long getDurationMs() { return durationMs; }
    }

    /**
     * Scrape multiple URLs concurrently
     */
    public List<ScrapedResult> scrapeUrls(List<String> urls) {
        List<ScrapedResult> results = new ArrayList<>();
        List<Future<ScrapedResult>> futures = new ArrayList<>();

        // Submit all tasks
        for (String url : urls) {
            Callable<ScrapedResult> task = new ScraperTask(url);
            Future<ScrapedResult> future = executorService.submit(task);
            futures.add(future);
        }

        // Collect results
        for (int i = 0; i < futures.size(); i++) {
            Future<ScrapedResult> future = futures.get(i);
            String url = urls.get(i);
            try {
                ScrapedResult result = future.get(timeoutSeconds, TimeUnit.SECONDS);
                results.add(result);
            } catch (TimeoutException e) {
                future.cancel(true);
                results.add(new ScrapedResult(url, false, "Timeout", null, -1));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                results.add(new ScrapedResult(url, false, "Interrupted", null, -1));
            } catch (ExecutionException e) {
                results.add(new ScrapedResult(url, false, 
                        "Error: " + e.getCause().getMessage(), null, -1));
            }
        }

        return results;
    }

    /**
     * Alternative: Use invokeAll for batch submission
     */
    public List<ScrapedResult> scrapeUrlsWithInvokeAll(List<String> urls) 
            throws InterruptedException {
        
        List<Callable<ScrapedResult>> tasks = new ArrayList<>();
        for (String url : urls) {
            tasks.add(new ScraperTask(url));
        }

        // invokeAll blocks until all tasks complete or timeout
        List<Future<ScrapedResult>> futures = executorService.invokeAll(
                tasks, timeoutSeconds, TimeUnit.SECONDS);

        List<ScrapedResult> results = new ArrayList<>();
        for (int i = 0; i < futures.size(); i++) {
            Future<ScrapedResult> future = futures.get(i);
            String url = urls.get(i);
            try {
                if (future.isCancelled()) {
                    results.add(new ScrapedResult(url, false, "Cancelled/Timeout", null, -1));
                } else {
                    results.add(future.get());
                }
            } catch (ExecutionException e) {
                results.add(new ScrapedResult(url, false, 
                        "Error: " + e.getCause().getMessage(), null, -1));
            }
        }

        return results;
    }

    /**
     * Graceful shutdown
     */
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    // ==================== MAIN ====================
    public static void main(String[] args) {
        List<String> urls = List.of(
            "https://www.google.com",
            "https://www.github.com",
            "https://www.stackoverflow.com",
            "https://www.wikipedia.org",
            "https://www.reddit.com"
        );

        // Create scraper with 3 threads and 10 second timeout
        ConcurrentWebScraper scraper = new ConcurrentWebScraper(3, 10);

        try {
            System.out.println("Starting concurrent web scraping...\n");
            long startTime = System.currentTimeMillis();

            List<ScrapedResult> results = scraper.scrapeUrls(urls);

            long totalTime = System.currentTimeMillis() - startTime;

            // Print results
            System.out.println("========== RESULTS ==========\n");
            for (ScrapedResult result : results) {
                System.out.println(result);
                System.out.println("-".repeat(40));
            }

            // Summary statistics
            long successCount = results.stream().filter(ScrapedResult::isSuccess).count();
            System.out.printf("\nTotal URLs: %d, Successful: %d, Failed: %d\n", 
                    urls.size(), successCount, urls.size() - successCount);
            System.out.printf("Total execution time: %dms\n", totalTime);

        } finally {
            scraper.shutdown();
        }
    }
}
