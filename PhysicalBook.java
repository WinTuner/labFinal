package com.library.labFinal;

public class PhysicalBook extends LibraryItem implements Taxable {

    private double price;
    private String shelfLocation;

    public PhysicalBook(String title, String author,
                        String isbn, double price,
                        String shelfLocation) {
        super(title, author, isbn);
        this.price = price;
        this.shelfLocation = shelfLocation;
    }

    public String getShelfLocation() {
        return shelfLocation;
    }

    @Override
    public double getPrice() {
        return price;
    }

    // ✅ Required by Taxable
    @Override
    public double calculateTax() {
        return price * 0.07;   // 7% tax
    }

    @Override
    public void displayDetails() {
        System.out.println("PHYSICAL BOOK");
        System.out.println("- Title: " + title);
        System.out.println("- Author: " + author);
        System.out.println("- ISBN: " + isbn);
        System.out.println("- Price: " + price + " Baht");
        System.out.println("- Shelf Location: " + shelfLocation);
        System.out.println("- Status: " +
                (isAvailable ? "Available" : "Borrowed"));
        printDueDate();
        System.out.println();
    }

    @Override
    public void printSummary() {
        System.out.println("PHYSICAL BOOK");
        System.out.println("- Title: " + getTitle());
        System.out.println("- Author: " + getAuthor());
        System.out.println("- ISBN: " + getIsbn());
        System.out.println("- Price: " + getPrice() + " Baht");
        System.out.println("- Shelf Location: " + shelfLocation);
        System.out.println("- Status: " + (isAvailable() ? "Available" : "Borrowed"));
        if (isAvailable()) {
            System.out.println("- Return Due Date: N/A (Book is available)");
        } else {
            System.out.println("- Return Due Date: " + getDueDate());
        }
        System.out.println();
    }

    @Override
    public double calculateLateFee(int days) {
        return days * 5.0;
    }

    @Override
    public String toString() {
        return "PhysicalBook[Title='" + getTitle() +
                "', Location='" + shelfLocation +
                "', Status='" + (isAvailable() ? "Available" : "Borrowed") +
                "']";
    }
}