package com.library.labFinal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Member class that uses Strategy Pattern for membership types.
 * Each member has a MembershipStrategy that determines their borrowing capabilities.
 */
public class Member {

    private String memberId;
    private String name;
    private List<LibraryItem> borrowedItems;
    private MembershipStrategy membershipStrategy;

    public Member(String id, String name, MembershipStrategy strategy) {
        this.memberId = id;
        this.name = name;
        this.borrowedItems = new ArrayList<>();
        this.membershipStrategy = strategy;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public MembershipStrategy getMembershipStrategy() {
        return membershipStrategy;
    }

    public void setMembershipStrategy(MembershipStrategy strategy) {
        this.membershipStrategy = strategy;
    }

    public boolean canBorrow() {
        int limit = membershipStrategy.getBorrowLimit();
        return borrowedItems.size() < limit;
    }

    public int getBorrowedCount() {
        return borrowedItems.size();
    }

    public List<LibraryItem> getBorrowedItems() {
        return Collections.unmodifiableList(borrowedItems);
    }

    public void borrowItem(LibraryItem item) {
        borrowedItems.add(item);
    }

    public void returnItem(LibraryItem item) {
        borrowedItems.remove(item);
    }

    public double calculateLateFee(double baseFee) {
        return membershipStrategy.applyLateFeeDiscount(baseFee);
    }

    public void displayMemberInfo() {
        System.out.println("--- MEMBER INFORMATION ---");
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Membership Type: " + membershipStrategy.getMembershipType());
        System.out.println("Borrow Limit: " + membershipStrategy.getBorrowLimit() + " items");
        System.out.println("Loan Period: " + membershipStrategy.getLoanPeriodDays() + " days");
        System.out.println("Current Borrowed: " + borrowedItems.size() + " items");
        System.out.println();
    }
}