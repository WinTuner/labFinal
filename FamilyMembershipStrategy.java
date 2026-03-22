package com.library.labFinal;

/**
 * STRATEGY PATTERN - FamilyMembershipStrategy
 *
 * Special membership for families with shared borrowing benefits.
 *
 * Benefits:
 * - Borrow Limit: 6 items
 * - Loan Period: 21 days (3 weeks for family convenience)
 * - Late Fee Discount: 10% (family consideration)
 * - Cost: 99 Baht/month
 *
 * Perfect for families who want to borrow together!
 */
public class FamilyMembershipStrategy implements MembershipStrategy {
    private static final int BORROW_LIMIT = 6;
    private static final int LOAN_PERIOD = 21;
    private static final double LATE_FEE_DISCOUNT = 0.10; // 10% discount
    private static final double MEMBERSHIP_COST = 99.0;

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
        System.out.println(" 👨‍👩‍👧‍👦 [Family Member Discount Applied]");
        System.out.println(" Original Fee: " + baseFee + " Baht");
        System.out.println(" Family Discount (10%): -" + discount + " Baht");
        System.out.println(" Final Fee: " + finalFee + " Baht");
        return finalFee;
    }

    @Override
    public String getMembershipType() {
        return "Family Member";
    }

    @Override
    public double getMembershipCost() {
        return MEMBERSHIP_COST;
    }

    @Override
    public boolean hasUnlimitedBorrowing() {
        return false;
    }
}

