/**

Design a service for managing coupons and scratch cards, focusing on user interaction and backend processes.
User Registration: Users can create accounts to manage their coupons and scratch cards.
Coupon Creation: Admins can create coupons with attributes like discount percentage, expiration date, and usage limits.
Scratch Card Generation: Users can receive scratch cards that reveal discounts or prizes when scratched.
Redemption Process: Users can redeem coupons or scratch card prizes through a simple interface.
┌─────────────────────────────────────────────────────────────────┐
│                        STRATEGY PATTERN                          │
│  ┌──────────────────┐                                           │
│  │DiscountStrategy  │ <── Interface                             │
│  └────────┬─────────┘                                           │
│           │                                                      │
│     ┌─────┴─────┬─────────────┐                                 │
│     ▼           ▼             ▼                                 │
│ Percentage   Flat        BuyOneGetOne                           │
│ Strategy    Strategy      Strategy                              │
└─────────────────────────────────────────────────────────────────┘

┌──────────┐     ┌──────────┐     ┌─────────────┐
│   User   │────>│  Coupon  │     │ ScratchCard │
└──────────┘     └──────────┘     └─────────────┘
      │               │                  │
      └───────────────┴──────────────────┘
                      │
              ┌───────▼───────┐
              │RedemptionService│
              └───────────────┘

**/

// DiscountType.java
public enum DiscountType {
    PERCENTAGE,
    FLAT,
    BUY_ONE_GET_ONE
}

// CouponStatus.java
public enum CouponStatus {
    ACTIVE,
    EXPIRED,
    EXHAUSTED
}

// ScratchCardStatus.java
public enum ScratchCardStatus {
    UNSCRATCHED,
    SCRATCHED,
    REDEEMED
}

// 2. Entities
// User.java
public class User {
    private Long id;
    private String name;
    private String email;
    private List<Coupon> coupons;
    private List<ScratchCard> scratchCards;

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.coupons = new ArrayList<>();
        this.scratchCards = new ArrayList<>();
    }

    public void addCoupon(Coupon coupon) {
        this.coupons.add(coupon);
    }

    public void addScratchCard(ScratchCard card) {
        this.scratchCards.add(card);
    }

    // Getters and setters
}

// Coupon.java
public class Coupon {
    private Long id;
    private String code;
    private DiscountType discountType;
    private Double discountValue;        // percentage or flat amount
    private LocalDateTime expiryDate;
    private Integer maxUsageLimit;
    private Integer currentUsageCount;
    private CouponStatus status;

    public Coupon(Long id, String code, DiscountType discountType, 
                  Double discountValue, LocalDateTime expiryDate, Integer maxUsageLimit) {
        this.id = id;
        this.code = code;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.expiryDate = expiryDate;
        this.maxUsageLimit = maxUsageLimit;
        this.currentUsageCount = 0;
        this.status = CouponStatus.ACTIVE;
    }

    public boolean isValid() {
        return status == CouponStatus.ACTIVE 
            && LocalDateTime.now().isBefore(expiryDate)
            && currentUsageCount < maxUsageLimit;
    }

    public void incrementUsage() {
        this.currentUsageCount++;
        if (currentUsageCount >= maxUsageLimit) {
            this.status = CouponStatus.EXHAUSTED;
        }
    }

    // Getters and setters
}

// ScratchCard.java
public class ScratchCard {
    private Long id;
    private User owner;
    private Double prizeAmount;          // Hidden until scratched
    private ScratchCardStatus status;
    private LocalDateTime expiryDate;

    public ScratchCard(Long id, User owner, Double prizeAmount, LocalDateTime expiryDate) {
        this.id = id;
        this.owner = owner;
        this.prizeAmount = prizeAmount;
        this.expiryDate = expiryDate;
        this.status = ScratchCardStatus.UNSCRATCHED;
    }

    public Double scratch() {
        if (status != ScratchCardStatus.UNSCRATCHED) {
            throw new IllegalStateException("Card already scratched!");
        }
        this.status = ScratchCardStatus.SCRATCHED;
        return prizeAmount;
    }

    public boolean isRedeemable() {
        return status == ScratchCardStatus.SCRATCHED 
            && LocalDateTime.now().isBefore(expiryDate);
    }

    public void markRedeemed() {
        this.status = ScratchCardStatus.REDEEMED;
    }

    // Getters and setters
}

//3. Strategy Pattern - Discount Calculation
// DiscountStrategy.java (Strategy Interface)
public interface DiscountStrategy {
    Double calculateDiscount(Double originalAmount, Double discountValue);
}
// PercentageDiscountStrategy.java
public class PercentageDiscountStrategy implements DiscountStrategy {
    @Override
    public Double calculateDiscount(Double originalAmount, Double discountValue) {
        return originalAmount * (discountValue / 100);
    }
}
// FlatDiscountStrategy.java
public class FlatDiscountStrategy implements DiscountStrategy {
    @Override
    public Double calculateDiscount(Double originalAmount, Double discountValue) {
        return Math.min(discountValue, originalAmount); // Can't discount more than original
    }
}
// BuyOneGetOneStrategy.java
public class BuyOneGetOneStrategy implements DiscountStrategy {
    @Override
    public Double calculateDiscount(Double originalAmount, Double discountValue) {
        // Assumes discountValue is item price, gives one free
        return discountValue;
    }
}
// DiscountStrategyFactory.java
public class DiscountStrategyFactory {
    
    private static final Map<DiscountType, DiscountStrategy> strategies = new HashMap<>();
    
    static {
        strategies.put(DiscountType.PERCENTAGE, new PercentageDiscountStrategy());
        strategies.put(DiscountType.FLAT, new FlatDiscountStrategy());
        strategies.put(DiscountType.BUY_ONE_GET_ONE, new BuyOneGetOneStrategy());
    }
    
    public static DiscountStrategy getStrategy(DiscountType type) {
        return strategies.get(type);
    }
}
//4. Repositories (In-Memory)

// UserRepository.java
public class UserRepository {
    private Map<Long, User> users = new HashMap<>();
    private Long idCounter = 1L;

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idCounter++);
        }
        users.put(user.getId(), user);
        return user;
    }

    public User findById(Long id) {
        return users.get(id);
    }

    public User findByEmail(String email) {
        return users.values().stream()
            .filter(u -> u.getEmail().equals(email))
            .findFirst()
            .orElse(null);
    }
}
// CouponRepository.java
public class CouponRepository {
    private Map<Long, Coupon> coupons = new HashMap<>();
    private Map<String, Coupon> couponsByCode = new HashMap<>();
    private Long idCounter = 1L;

    public Coupon save(Coupon coupon) {
        if (coupon.getId() == null) {
            coupon.setId(idCounter++);
        }
        coupons.put(coupon.getId(), coupon);
        couponsByCode.put(coupon.getCode(), coupon);
        return coupon;
    }

    public Coupon findByCode(String code) {
        return couponsByCode.get(code);
    }
}
// ScratchCardRepository.java
public class ScratchCardRepository {
    private Map<Long, ScratchCard> scratchCards = new HashMap<>();
    private Long idCounter = 1L;

    public ScratchCard save(ScratchCard card) {
        if (card.getId() == null) {
            card.setId(idCounter++);
        }
        scratchCards.put(card.getId(), card);
        return card;
    }

    public ScratchCard findById(Long id) {
        return scratchCards.get(id);
    }
}
//5. Services
// UserService.java
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String name, String email) {
        if (userRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email already registered!");
        }
        User user = new User(null, name, email);
        return userRepository.save(user);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId);
    }
}
// CouponService.java
public class CouponService {
    private CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    // Admin creates coupon
    public Coupon createCoupon(String code, DiscountType discountType, 
                               Double discountValue, LocalDateTime expiryDate, 
                               Integer maxUsageLimit) {
        Coupon coupon = new Coupon(null, code, discountType, discountValue, 
                                   expiryDate, maxUsageLimit);
        return couponRepository.save(coupon);
    }

    public Coupon getCouponByCode(String code) {
        return couponRepository.findByCode(code);
    }

    public void assignCouponToUser(String couponCode, User user) {
        Coupon coupon = couponRepository.findByCode(couponCode);
        if (coupon != null && coupon.isValid()) {
            user.addCoupon(coupon);
        }
    }
}

// ScratchCardService.java
public class ScratchCardService {
    private ScratchCardRepository scratchCardRepository;
    private Random random = new Random();

    public ScratchCardService(ScratchCardRepository scratchCardRepository) {
        this.scratchCardRepository = scratchCardRepository;
    }

    public ScratchCard generateScratchCard(User user) {
        // Random prize between 10 and 500
        Double prize = 10 + (random.nextDouble() * 490);
        prize = Math.round(prize * 100.0) / 100.0;
        
        LocalDateTime expiry = LocalDateTime.now().plusDays(30);
        ScratchCard card = new ScratchCard(null, user, prize, expiry);
        scratchCardRepository.save(card);
        user.addScratchCard(card);
        return card;
    }

    public Double scratchCard(Long cardId, User user) {
        ScratchCard card = scratchCardRepository.findById(cardId);
        if (card == null || !card.getOwner().equals(user)) {
            throw new IllegalArgumentException("Invalid scratch card!");
        }
        return card.scratch();
    }

    public ScratchCard getCard(Long cardId) {
        return scratchCardRepository.findById(cardId);
    }
}

// RedemptionService.java - USES STRATEGY PATTERN
public class RedemptionService {
    private CouponService couponService;
    private ScratchCardService scratchCardService;

    public RedemptionService(CouponService couponService, ScratchCardService scratchCardService) {
        this.couponService = couponService;
        this.scratchCardService = scratchCardService;
    }

    // Redeem coupon - STRATEGY PATTERN USED HERE
    public Double redeemCoupon(String couponCode, Double orderAmount) {
        Coupon coupon = couponService.getCouponByCode(couponCode);
        
        if (coupon == null || !coupon.isValid()) {
            throw new IllegalArgumentException("Invalid or expired coupon!");
        }

        // Get appropriate strategy based on coupon type
        DiscountStrategy strategy = DiscountStrategyFactory.getStrategy(coupon.getDiscountType());
        
        Double discount = strategy.calculateDiscount(orderAmount, coupon.getDiscountValue());
        Double finalAmount = orderAmount - discount;
        
        coupon.incrementUsage();
        
        System.out.println("Coupon Applied! Discount: " + discount);
        return finalAmount;
    }

    // Redeem scratch card
    public Double redeemScratchCard(Long cardId, User user) {
        ScratchCard card = scratchCardService.getCard(cardId);
        
        if (card == null || !card.getOwner().equals(user)) {
            throw new IllegalArgumentException("Invalid scratch card!");
        }
        
        if (!card.isRedeemable()) {
            throw new IllegalStateException("Card not scratched or already redeemed!");
        }
        
        Double prize = card.getPrizeAmount();
        card.markRedeemed();
        
        System.out.println("Scratch card redeemed! Prize: " + prize);
        return prize;
    }
}

//6. Main.java - Demo
public class Main {
    public static void main(String[] args) {
        // Initialize repositories
        UserRepository userRepo = new UserRepository();
        CouponRepository couponRepo = new CouponRepository();
        ScratchCardRepository scratchCardRepo = new ScratchCardRepository();

        // Initialize services
        UserService userService = new UserService(userRepo);
        CouponService couponService = new CouponService(couponRepo);
        ScratchCardService scratchCardService = new ScratchCardService(scratchCardRepo);
        RedemptionService redemptionService = new RedemptionService(couponService, scratchCardService);

        // 1. User Registration
        User user1 = userService.registerUser("John Doe", "john@email.com");
        User user2 = userService.registerUser("Jane Smith", "jane@email.com");
        System.out.println("Users registered: " + user1.getName() + ", " + user2.getName());

        // 2. Admin creates coupons (different discount types)
        Coupon percentCoupon = couponService.createCoupon(
            "SAVE20", 
            DiscountType.PERCENTAGE, 
            20.0,                                    // 20% off
            LocalDateTime.now().plusDays(30), 
            100
        );

        Coupon flatCoupon = couponService.createCoupon(
            "FLAT50", 
            DiscountType.FLAT, 
            50.0,                                    // Flat ₹50 off
            LocalDateTime.now().plusDays(15), 
            50
        );

        System.out.println("Coupons created: " + percentCoupon.getCode() + ", " + flatCoupon.getCode());

        // 3. Generate scratch cards for users
        ScratchCard card1 = scratchCardService.generateScratchCard(user1);
        ScratchCard card2 = scratchCardService.generateScratchCard(user1);
        System.out.println("Scratch cards generated for " + user1.getName());

        // 4. User scratches a card
        Double revealedPrize = scratchCardService.scratchCard(card1.getId(), user1);
        System.out.println("Card scratched! Prize revealed: ₹" + revealedPrize);

        // 5. Redemption - Coupon (STRATEGY PATTERN IN ACTION)
        System.out.println("\n--- COUPON REDEMPTION (Strategy Pattern) ---");
        Double orderAmount = 500.0;
        
        // Using PERCENTAGE strategy
        Double finalAmount1 = redemptionService.redeemCoupon("SAVE20", orderAmount);
        System.out.println("Order: ₹" + orderAmount + " → After 20% discount: ₹" + finalAmount1);

        // Using FLAT strategy
        Double finalAmount2 = redemptionService.redeemCoupon("FLAT50", orderAmount);
        System.out.println("Order: ₹" + orderAmount + " → After flat ₹50 discount: ₹" + finalAmount2);

        // 6. Redemption - Scratch Card
        System.out.println("\n--- SCRATCH CARD REDEMPTION ---");
        Double prize = redemptionService.redeemScratchCard(card1.getId(), user1);
        System.out.println("User " + user1.getName() + " redeemed ₹" + prize);
    }
}

/**
Output

Users registered: John Doe, Jane Smith
Coupons created: SAVE20, FLAT50
Scratch cards generated for John Doe
Card scratched! Prize revealed: ₹247.35

--- COUPON REDEMPTION (Strategy Pattern) ---
Coupon Applied! Discount: 100.0
Order: ₹500.0 → After 20% discount: ₹400.0
Coupon Applied! Discount: 50.0
Order: ₹500.0 → After flat ₹50 discount: ₹450.0

--- SCRATCH CARD REDEMPTION ---
Scratch card redeemed! Prize: 247.35
User John Doe redeemed ₹247.35

 Key Interview Points
Strategy Pattern eliminates if-else chains for discount calculation
Single Responsibility - Each service handles one domain
Open/Closed - Add new discount types without modifying existing code
Easy to extend - Add MinPurchaseDiscountStrategy, TimeLimitedStrategy, etc.
/**
