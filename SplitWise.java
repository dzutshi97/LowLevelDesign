//https://github.com/PiyushRajmsit/SplitWise/blob/main/src/com/splitwise/expense/Utils.java

// OR refer entity design of Split & Expense from above link & below service class code. See Option 2 solution which is more easier

// Option 1: Use a Map to Pass User Shares
// ExpenseService.java
public void addEqualExpense(String expenseName, Double totalAmount, Long paidByUserId, 
                            Long createdByUserId, List<Long> userIds) {
    User paidBy = bookKeeper.getUser(paidByUserId);
    User createdBy = bookKeeper.getUser(createdByUserId);
    List<Split> splitList = new ArrayList<>();
    
    Double eachShare = totalAmount / userIds.size();
    for (Long userId : userIds) {
        User user = bookKeeper.getUser(userId);
        splitList.add(new EqualSplit(user, eachShare));
    }
    
    Expense expense = new EqualExpense(totalAmount, paidBy, createdBy, splitList, expenseName);
    updateBalances(expense);
}

public void addExactExpense(String expenseName, Double totalAmount, Long paidByUserId,
                            Long createdByUserId, Map<Long, Double> userShares) {
    User paidBy = bookKeeper.getUser(paidByUserId);
    User createdBy = bookKeeper.getUser(createdByUserId);
    List<Split> splitList = new ArrayList<>();
    
    for (Map.Entry<Long, Double> entry : userShares.entrySet()) {
        User user = bookKeeper.getUser(entry.getKey());
        Double share = entry.getValue();
        splitList.add(new ExactSplit(user, share));
    }
    
    Expense expense = new ExactExpense(totalAmount, paidBy, createdBy, splitList, expenseName);
    updateBalances(expense);
}

public void addPercentExpense(String expenseName, Double totalAmount, Long paidByUserId,
                              Long createdByUserId, Map<Long, Double> userPercentages) {
    User paidBy = bookKeeper.getUser(paidByUserId);
    User createdBy = bookKeeper.getUser(createdByUserId);
    List<Split> splitList = new ArrayList<>();
    
    for (Map.Entry<Long, Double> entry : userPercentages.entrySet()) {
        User user = bookKeeper.getUser(entry.getKey());
        Double percent = entry.getValue();
        Double userShare = Utils.getAmountFromPercent(totalAmount, percent);
        splitList.add(new PercentSplit(user, userShare, percent));
    }
    
    Expense expense = new PercentExpense(totalAmount, paidBy, createdBy, splitList, expenseName);
    updateBalances(expense);
}

private void updateBalances(Expense expense) {
    for (Split s : expense.getSplitList()) {
        s.getUser().addBalance(s.getAmount());
        s.getUser().addExpense(expense);
    }
    System.out.println("Expense Successfully Added");
}

// Main.java -
public class Main {
    public static void main(String[] args) {
        ExpenseService expenseService = new ExpenseService();
        
        // Equal Split - just pass user IDs
        expenseService.addEqualExpense(
            "Dinner",
            300.0,
            1L,  // paidBy
            1L,  // createdBy
            Arrays.asList(1L, 2L, 3L)  // users to split among
        );
        
        // Exact Split - pass userId -> exactAmount map
        Map<Long, Double> exactShares = new LinkedHashMap<>();
        exactShares.put(1L, 100.0);  // User 1 owes 100
        exactShares.put(2L, 150.0);  // User 2 owes 150
        exactShares.put(3L, 50.0);   // User 3 owes 50
        
        expenseService.addExactExpense(
            "Movie Tickets",
            300.0,
            1L,
            1L,
            exactShares
        );
        
        // Percent Split - pass userId -> percentage map
        Map<Long, Double> percentShares = new LinkedHashMap<>();
        percentShares.put(1L, 50.0);  // User 1 pays 50%
        percentShares.put(2L, 30.0);  // User 2 pays 30%
        percentShares.put(3L, 20.0);  // User 3 pays 20%
        
        expenseService.addPercentExpense(
            "Groceries",
            500.0,
            2L,
            2L,
            percentShares
        );
    }
}

// Option 2: Use a Request DTO (More Structured)
// ExpenseRequest.java
public class ExpenseRequest {
    private String expenseName;
    private Double totalAmount;
    private Long paidByUserId;
    private Long createdByUserId;
    private ExpenseType expenseType;
    private List<Long> userIds;                    // For EQUAL
    private Map<Long, Double> userShares;          // For EXACT (userId -> amount) or PERCENT (userId -> percentage)
    
    // Getters, setters, and builder pattern
}

//Then your service becomes:
public void addExpense(ExpenseRequest request) {
    switch (request.getExpenseType()) {
        case EQUAL:
            addEqualExpense(request);
            break;
        case EXACT:
            addExactExpense(request);
            break;
        case PERCENT:
            addPercentExpense(request);
            break;
    }
}
