package com.library.labFinal;

public class EBook extends LibraryItem
        implements DigitalContent, Taxable {

    private String downloadUrl;
    private double fileSize;
    private final double price = 99.0; // default price

    public EBook(String title, String author, String isbn,
                 String url, double size) {
        super(title, author, isbn);
        this.downloadUrl = url;
        this.fileSize = size;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public double getFileSize() {
        return fileSize;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double calculateTax() {
        return price * 0.05; // 5% digital tax
    }

    @Override
    public void streamOnline() {
        System.out.println("Streaming '" + title +
                "' from URL: " + downloadUrl);
        System.out.println("Starting online stream... connected!");
        System.out.println("You can now read the book online without downloading.");
    }

    @Override
    public void download() {
        System.out.println("Downloading '" + title +
                "' from URL: " + downloadUrl);
        System.out.printf("Downloading file... (%.2f MB)%n", fileSize);
        System.out.println("Download complete! File saved to your device.");
        System.out.println("You can now read the book offline.");
    }


    @Override
    public void printSummary() {
        System.out.println("- Title: " + getTitle());
        System.out.println("- ISBN: " + getIsbn());
        System.out.println("- Status: " +
                (isAvailable() ? "Available" : "Borrowed"));
        printDueDate();
    }

    @Override
    public void displayDetails() {
        System.out.println("E-BOOK");
        System.out.println("- Title: " + title);
        System.out.println("- Author: " + author);
        System.out.println("- ISBN: " + isbn);
        System.out.printf("- Price: %.2f Baht%n", price);
        System.out.println("- Download URL: " + downloadUrl);
        System.out.printf("- File Size: %.2f MB%n", fileSize);
        System.out.println("- Status: " +
                (isAvailable ? "Available" : "Borrowed"));
        printDueDate();
        System.out.println();
    }

    @Override
    public double calculateLateFee(int days) {
        return 0.0;
    }

    @Override
    public String toString() {
        return "EBook[Title='" + getTitle() +
                "', Size='" + String.format("%.2f", fileSize) + " MB'" +
                ", Status='" + (isAvailable() ? "Available" : "Borrowed") +
                "']";

    }
}