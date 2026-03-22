package com.library.labFinal;

/**
 * STRATEGY PATTERN - PremiumMembershipStrategy
 *
 * VIP membership with maximum benefits.
 *
 * Benefits:
 * - Borrow Limit: UNLIMITED
 * - Loan Period: 30 days
 * - Late Fee Discount: 100% (FREE - no late fees!)
 * - Cost: 299 Baht/month
 * - Priority holds and reservations
 *
 * Best value for avid readers!
 */
public class PremiumMembershipStrategy implements MembershipStrategy {
    private static final int BORROW_LIMIT = Integer.MAX_VALUE; // Unlimited
    private static final int LOAN_PERIOD = 30;
    private static final double LATE_FEE_DISCOUNT = 1.0; // 100% discount (FREE)
    private static final double MEMBERSHIP_COST = 299.0;

    @Override
    public int getBorrowLimit() {
        return BORROW_LIMIT;
    }

    @Override
    public int getLoanPeriodDays() {
        return LOAN_PERIOD;
    }

    @Override
    public double applyLateFeeDiscount(double baseFee) {
        double discount = baseFee * LATE_FEE_DISCOUNT;
        double finalFee = baseFee - discount;
        System.out.println(" 👑 [Premium Member Privilege]");
        System.out.println(" Original Fee: " + baseFee + " Baht");
        System.out.println(" Premium Discount (100%): -" + baseFee + " Baht");
        System.out.println(" Final Fee: " + finalFee + " Baht (FREE!)");
        return 0.0; // No late fees for premium members!
    }

    @Override
    public String getMembershipType() {
        return "Premium Member";
    }

    @Override
    public double getMembershipCost() {
        return MEMBERSHIP_COST;
    }

    @Override
    public boolean hasUnlimitedBorrowing() {
        return true;
    }
}

