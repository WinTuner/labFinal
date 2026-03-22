package com.library.labFinal;

import java.time.LocalDate;

public abstract class LibraryItem {

    protected String title;
    protected String author;
    protected String isbn;
    protected boolean isAvailable;
    protected Member borrower;
    protected LocalDate dueDate;

    public LibraryItem(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
        this.borrower = null;
        this.dueDate = null;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return isAvailable; }
    public LocalDate getDueDate() { return dueDate; }
    public Member getBorrower() { return borrower; }

    public abstract double getPrice();
    public abstract void displayDetails();
    public abstract double calculateLateFee(int days);

    public boolean checkOut(Member member) {

        if (!isAvailable) {
            System.out.println("Error: Item '" + title +
                    "' is already borrowed and cannot be checked out again.");
            return false;
        }

        if (!member.canBorrow()) {
            System.out.println("Member " + member.getName() +
                    " has reached the borrow limit (" + member.getMembershipStrategy().getBorrowLimit() + ").");
            System.out.printf("Borrow request denied for member %s.%n",
                    member.getName());
            return false;
        }

        this.isAvailable = false;
        this.borrower = member;
        // Use membership strategy's loan period
        int loanPeriodDays = member.getMembershipStrategy().getLoanPeriodDays();
        this.dueDate = LocalDate.now().plusDays(loanPeriodDays);

        // ✅ Important
        member.borrowItem(this);

        System.out.println("Item '" + title +
                "' has been checked out successfully.");
        System.out.println("Item '" + title +
                "' has been borrowed by " + member.getName() + ".");
        System.out.println("Loan Period: " + loanPeriodDays + " days");
        System.out.println("Return Due Date: " + dueDate);

        return true;
    }

    public void returnItem() {

        if (isAvailable) {
            System.out.println("Item is already available.");
            return;
        }

        if (borrower != null) {
            borrower.returnItem(this);
        }

        isAvailable = true;
        borrower = null;
        dueDate = null;

        System.out.println("Item '" + title +
                "' has been returned successfully.");
    }

    // 🔹 helper สำหรับ printSummary
    protected void printDueDate() {
        if (isAvailable) {
            System.out.println("- Return Due Date: N/A (Item is available)");
        } else {
            System.out.println("- Return Due Date: " + dueDate);
        }
    }

    public abstract void printSummary();

    @Override
    public abstract String toString();
}