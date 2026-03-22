package com.library.labFinal;

/**
 * STRATEGY PATTERN - MembershipStrategy Interface
 *
 * Defines the contract for different membership types.
 * Each membership type has different behaviors:
 * - Borrow limits
 * - Loan periods
 * - Late fee discounts
 *
 * Benefits of Strategy Pattern:
 * - Easy to add new membership types without modifying existing code
 * - Member can change strategy at runtime (upgrade membership)
 * - Eliminates if-else or switch statements for member types
 * - Each strategy is a separate class (Single Responsibility Principle)
 *
 * Similar to SimUDuck's FlyBehavior and QuackBehavior!
 */
public interface MembershipStrategy {
    int getBorrowLimit();
    int getLoanPeriodDays();
    double applyLateFeeDiscount(double baseFee);
    String getMembershipType();
    double getMembershipCost();
    boolean hasUnlimitedBorrowing();
}

