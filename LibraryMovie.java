package com.library.labFinal;

/**
 * LibraryMovie
 * Challenge Class
 * Does NOT extend LibraryItem
 * Implements DigitalContent and Taxable
 */
public class LibraryMovie implements DigitalContent, Taxable {

    private String title;
    private String director;
    private String streamingUrl;
    private int durationMinutes;
    private int releaseYear;
    private String genre;
    private double price;

    public LibraryMovie(String title, String director,
                        String streamingUrl,
                        int durationMinutes,
                        int releaseYear,
                        String genre,
                        double price) {

        this.title = title;
        this.director = director;
        this.streamingUrl = streamingUrl;
        this.durationMinutes = durationMinutes;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    // ================= DigitalContent =================

    @Override
    public void streamOnline() {
        System.out.println("Streaming movie: " + title);
        System.out.println("Director: " + director);
        System.out.println("Genre: " + genre);
        System.out.println("Connecting to: " + streamingUrl);
        System.out.println("Playing movie... (" + durationMinutes + " minutes)");
    }

    @Override
    public void download() {
        System.out.println("Downloading movie: " + title);
        System.out.println("Download complete. Ready for offline viewing.");
    }

    // ================= Taxable =================

    @Override
    public double calculateTax() {
        return price * 0.05; // 5% digital tax
    }

    // Optional display method (not required but useful)
    public void displayDetails() {
        System.out.println("MOVIE");
        System.out.println("- Title: " + title);
        System.out.println("- Director: " + director);
        System.out.println("- Year: " + releaseYear);
        System.out.println("- Genre: " + genre);
        System.out.println("- Duration: " + durationMinutes + " minutes");
        System.out.printf("- Price: %.2f Baht%n", price);
        System.out.println();
    }
}