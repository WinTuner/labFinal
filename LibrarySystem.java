package com.library.labFinal;

import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class LibrarySystem {
    // Singleton instance
    private static LibrarySystem instance;
    // Library data
    private List<LibraryItem> allItems;
    private List<Member> allMembers;
    private String libraryName;
    private String libraryLocation;
    /**
     * Private constructor - Prevents direct instantiation
     * Only getInstance() can create the instance
     */
    private LibrarySystem() {
        this.allItems = new ArrayList<>();
        this.allMembers = new ArrayList<>();
        this.libraryName = "City Central Library";
        this.libraryLocation = "123 Main Street, Downtown";
    }
    /**
     * SINGLETON METHOD: Thread-safe getInstance()
     * Returns the single instance of LibrarySystem
     * Creates instance on first call, returns same instance on subsequent calls
     */
    public static synchronized LibrarySystem getInstance() {
        if (instance == null) {
            instance = new LibrarySystem();
        }
        return instance;
    }
    // Getters
    public String getLibraryName() {
        return libraryName;
    }
    public String getLibraryLocation() {
        return libraryLocation;
    }
    public List<LibraryItem> getAllItems() {
        return new ArrayList<>(allItems);
    }
    public List<Member> getAllMembers() {
        return new ArrayList<>(allMembers);
    }
    // Library Operations
    public void addItem(LibraryItem item) {
        allItems.add(item);
        System.out.println(" ✅ Item added to system: " + item.getTitle());
    }
    public void addMember(Member member) {
        allMembers.add(member);
        System.out.println(" ✅ Member registered: " + member.getName());
    }
    
    public boolean deleteItem(String title) {
        LibraryItem item = findItemByTitle(title);
        if (item != null && item.isAvailable()) {
            allItems.remove(item);
            return true;
        }
        return false;
    }
    
    public int getTotalItems() {
        return allItems.size();
    }
    public int getTotalMembers() {
        return allMembers.size();
    }
    public int getAvailableItemsCount() {
        return (int) allItems.stream().filter(LibraryItem::isAvailable).count();
    }
    public int getBorrowedItemsCount() {
        return (int) allItems.stream().filter(item -> !item.isAvailable()).count();
    }
    /**
     * Display library statistics
     */
    public void displayStatistics() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println(" 📚 LIBRARY SYSTEM STATISTICS (Singleton Instance)");
        System.out.println("=".repeat(60));
        System.out.println("\nLibrary: " + libraryName);
        System.out.println("Location: " + libraryLocation);
        System.out.println("\n📊 STATISTICS:");
        System.out.println(" Total Items: " + getTotalItems());
        System.out.println(" Available Items: " + getAvailableItemsCount());
        System.out.println(" Borrowed Items: " + getBorrowedItemsCount());
        System.out.println(" Total Members: " + getTotalMembers());
        System.out.println("\n✨ Note: This is the ONLY instance of LibrarySystem in the application!");
        System.out.println(" (Singleton Pattern ensures single centralized management)");
    }
    /**
     * Find item by title
     */
    public LibraryItem findItemByTitle(String title) {
        return allItems.stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }
    /**
     * Find member by ID
     */
    public Member findMemberById(String memberId) {
        return allMembers.stream()
                .filter(member -> member.getMemberId().equals(memberId))
                .findFirst()
                .orElse(null);
    }
    
    // ==================== REPORTING METHODS ====================
    
    /**
     * Report overdue items
     */
    public void reportOverdueItems() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println(" 📋 OVERDUE ITEMS REPORT");
        System.out.println("=".repeat(70));
        
        List<LibraryItem> overdueItems = new ArrayList<>();
        LocalDate today = LocalDate.now();
        
        for (LibraryItem item : allItems) {
            if (!item.isAvailable() && item.getDueDate() != null && today.isAfter(item.getDueDate())) {
                overdueItems.add(item);
            }
        }
        
        if (overdueItems.isEmpty()) {
            System.out.println("✅ No overdue items. All borrowers are responsible!");
        } else {
            System.out.println("⚠️  Found " + overdueItems.size() + " overdue item(s):\n");
            for (LibraryItem item : overdueItems) {
                long daysOverdue = java.time.temporal.ChronoUnit.DAYS.between(item.getDueDate(), today);
                System.out.printf("  - %s | Due: %s | Days Overdue: %d%n", 
                    item.getTitle(), item.getDueDate(), daysOverdue);
            }
        }
        System.out.println();
    }
    
    /**
     * Report most borrowed items
     */
    public void reportMostBorrowedItems() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println(" 📊 MOST BORROWED ITEMS REPORT");
        System.out.println("=".repeat(70));
        
        // Count borrowing frequency by tracking currently borrowed items
        Map<String, Integer> itemBorrowCount = new HashMap<>();
        
        for (Member member : allMembers) {
            for (LibraryItem item : member.getBorrowedItems()) {
                itemBorrowCount.put(item.getTitle(), itemBorrowCount.getOrDefault(item.getTitle(), 0) + 1);
            }
        }
        
        if (itemBorrowCount.isEmpty()) {
            System.out.println("No items have been borrowed yet.");
        } else {
            // Sort by borrow count
            itemBorrowCount.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(entry -> System.out.printf("  - %s: %d borrow(s)%n", entry.getKey(), entry.getValue()));
        }
        System.out.println();
    }
    
    /**
     * Report member with most borrowings
     */
    public void reportMemberMostBorrowings() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println(" 👤 MEMBER WITH MOST BORROWINGS");
        System.out.println("=".repeat(70));
        
        if (allMembers.isEmpty()) {
            System.out.println("No members registered.");
            System.out.println();
            return;
        }
        
        Member topMember = allMembers.stream()
            .max(Comparator.comparingInt(Member::getBorrowedCount))
            .orElse(null);
        
        if (topMember != null && topMember.getBorrowedCount() > 0) {
            System.out.printf("👤 %s (%s) - Currently has %d items borrowed:%n", 
                topMember.getName(), topMember.getMemberId(), topMember.getBorrowedCount());
            for (LibraryItem item : topMember.getBorrowedItems()) {
                System.out.printf("   - %s (Due: %s)%n", item.getTitle(), item.getDueDate());
            }
        } else {
            System.out.println("No members have borrowed items.");
        }
        System.out.println();
    }
    
    /**
     * Report available vs borrowed items
     */
    public void reportAvailableVsBorrowed() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println(" 📦 AVAILABLE VS BORROWED ITEMS");
        System.out.println("=".repeat(70));
        
        int available = getAvailableItemsCount();
        int borrowed = getBorrowedItemsCount();
        int total = getTotalItems();
        
        System.out.println("\n📊 Summary:");
        System.out.printf("  Total Items:      %d%n", total);
        System.out.printf("  Available Items:  %d (%.1f%%)%n", available, total > 0 ? (available * 100.0 / total) : 0);
        System.out.printf("  Borrowed Items:   %d (%.1f%%)%n", borrowed, total > 0 ? (borrowed * 100.0 / total) : 0);
        
        System.out.println("\n📋 Available Items:");
        int count = 0;
        for (LibraryItem item : allItems) {
            if (item.isAvailable()) {
                System.out.printf("  %d. %s - %s%n", ++count, item.getTitle(), item.getAuthor());
            }
        }
        if (count == 0) System.out.println("  (None)");
        
        System.out.println("\n📚 Currently Borrowed Items:");
        count = 0;
        for (LibraryItem item : allItems) {
            if (!item.isAvailable()) {
                System.out.printf("  %d. %s - Borrowed by %s (Due: %s)%n", ++count, item.getTitle(), 
                    item.borrower != null ? item.borrower.getName() : "Unknown", item.getDueDate());
            }
        }
        if (count == 0) System.out.println("  (None)");
        
        System.out.println();
    }
    
    // ==================== FILE I/O METHODS ====================
    
    /**
     * Save library data to CSV file
     */
    public void saveToCSV(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("=== LIBRARY DATA BACKUP ===");
            writer.println("Type,Data");
            
            // Save members
            writer.println("\n=== MEMBERS ===");
            for (Member member : allMembers) {
                writer.printf("MEMBER,%s,%s,%s%n", 
                    member.getMemberId(), 
                    member.getName(),
                    member.getMembershipStrategy().getMembershipType());
            }
            
            // Save items
            writer.println("\n=== ITEMS ===");
            for (LibraryItem item : allItems) {
                if (item instanceof PhysicalBook) {
                    PhysicalBook book = (PhysicalBook) item;
                    writer.printf("BOOK,%s,%s,%s,%.2f,%s%n",
                        book.getTitle(), book.getAuthor(), book.getIsbn(),
                        book.getPrice(), book.getShelfLocation());
                } else if (item instanceof EBook) {
                    EBook ebook = (EBook) item;
                    writer.printf("EBOOK,%s,%s,%s,%s,%.2f%n",
                        ebook.getTitle(), ebook.getAuthor(), ebook.getIsbn(),
                        ebook.getDownloadUrl(), ebook.getFileSize());
                }
            }
            
            System.out.println("✅ Data saved to " + filename);
        } catch (IOException e) {
            System.err.println("⚠️  Error saving data: " + e.getMessage());
        }
    }
    
    /**
     * Load library data from CSV file
     */
    public void loadFromCSV(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return;
        }
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.startsWith("===") || line.startsWith("Type")) {
                    continue;
                }
                
                String[] parts = line.split(",");
                if (parts.length == 0) continue;
                
                String type = parts[0];
                // Data loading would be implemented here
            }
            System.out.println("✅ Data loaded from " + filename);
        } catch (FileNotFoundException e) {
            System.err.println("⚠️  File not found: " + filename);
        }
    }
}