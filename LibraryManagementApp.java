package com.library.labFinal;

import java.util.Scanner;
import java.util.List;

public class LibraryManagementApp {
    private static LibrarySystem librarySystem;
    private static Scanner scanner;

    public static void main(String[] args) {
        // Initialize the application
        librarySystem = LibrarySystem.getInstance();
        scanner = new Scanner(System.in);

        // Load sample data
        initializeSampleData();

        // Load data from file if exists
        librarySystem.loadFromCSV("library_data.csv");

        // Show welcome message and start menu loop
        showWelcome();
        runMainMenu();

        // Save data before exit
        librarySystem.saveToCSV("library_data.csv");
        scanner.close();
        System.out.println("\n✅ Thank you for using City Central Library! Goodbye!\n");
    }

    private static void showWelcome() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("  🏛️  CITY CENTRAL LIBRARY - MANAGEMENT SYSTEM");
        System.out.println("=".repeat(70));
        System.out.println("  Welcome to the Library Management System");
        System.out.println("  Location: 123 Main Street, Downtown");
        System.out.println("=".repeat(70));
    }

    private static void runMainMenu() {
        boolean running = true;
        while (running) {
            showMainMenu();
            String choice = getInput("Select an option: ");

            switch (choice.trim().toUpperCase()) {
                case "1":
                    manageMembers();
                    break;
                case "2":
                    manageItems();
                    break;
                case "3":
                    handleBorrowing();
                    break;
                case "4":
                    handleReturns();
                    break;
                case "5":
                    viewReports();
                    break;
                case "6":
                    viewStatistics();
                    break;
                case "7":
                    viewDemoMode();
                    break;
                case "8":
                    running = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("MAIN MENU:");
        System.out.println("-".repeat(70));
        System.out.println("1. Manage Members (Register, View, Search)");
        System.out.println("2. Manage Library Items (Add, View, Search)");
        System.out.println("3. Borrow Items");
        System.out.println("4. Return Items");
        System.out.println("5. View Reports (Overdue, Most Borrowed)");
        System.out.println("6. View Statistics");
        System.out.println("7. View Demo Mode (Design Patterns)");
        System.out.println("8. Exit & Save");
        System.out.println("-".repeat(70));
    }

    private static void manageMembers() {
        boolean managing = true;
        while (managing) {
            System.out.println("\n" + "-".repeat(50));
            System.out.println("MEMBER MANAGEMENT:");
            System.out.println("-".repeat(50));
            System.out.println("1. Register New Member");
            System.out.println("2. View All Members");
            System.out.println("3. Search Member by ID");
            System.out.println("4. View Member Borrow History");
            System.out.println("5. Upgrade/Change Membership");
            System.out.println("6. Back to Main Menu");
            System.out.println("-".repeat(50));

            String choice = getInput("Select option: ");

            switch (choice.trim().toUpperCase()) {
                case "1":
                    registerNewMember();
                    break;
                case "2":
                    viewAllMembers();
                    break;
                case "3":
                    searchMemberById();
                    break;
                case "4":
                    viewMemberBorrowHistory();
                    break;
                case "5":
                    upgradeMembership();
                    break;
                case "6":
                    managing = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void registerNewMember() {
        System.out.println("\n📝 REGISTER NEW MEMBER");
        System.out.println("-".repeat(50));

        String memberId = getInput("Enter Member ID: ");
        if (librarySystem.findMemberById(memberId) != null) {
            System.out.println("❌ Member ID already exists!");
            return;
        }

        String name = getInput("Enter Member Name: ");
        if (name.isEmpty()) {
            System.out.println("❌ Name cannot be empty!");
            return;
        }

        showMembershipOptions();
        String membershipChoice = getInput("Select membership type (1-4): ");

        MembershipStrategy strategy = parseMembershipStrategy(membershipChoice);
        if (strategy == null) {
            System.out.println("❌ Invalid membership selection.");
            return;
        }

        Member newMember = new Member(memberId, name, strategy);
        librarySystem.addMember(newMember);
        System.out.println("✅ Member registered successfully!");
        newMember.displayMemberInfo();
    }

    private static void showMembershipOptions() {
        System.out.println("\nAvailable Membership Types:");
        System.out.println("1. Basic (1 item, 14 days, No discount) - FREE");
        System.out.println("2. Student (5 items, 21 days, 20% discount) - FREE");
        System.out.println("3. Premium (Unlimited, 30 days, 100% discount) - 100 Baht/month");
        System.out.println("4. Family (6 items, 21 days, 10% discount) - 150 Baht/month");
    }

    private static MembershipStrategy parseMembershipStrategy(String choice) {
        switch (choice.trim()) {
            case "1":
                return new BasicMembershipStrategy();
            case "2":
                return new StudentMembershipStrategy();
            case "3":
                return new PremiumMembershipStrategy();
            case "4":
                return new FamilyMembershipStrategy();
            default:
                return null;
        }
    }

    private static void viewAllMembers() {
        List<Member> members = librarySystem.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("\n❌ No members registered yet.");
            return;
        }

        System.out.println("\n📋 ALL MEMBERS (" + members.size() + " total)");
        System.out.println("-".repeat(70));
        for (Member member : members) {
            System.out.printf("ID: %-10s | Name: %-20s | Type: %-15s | Borrowed: %d%n",
                    member.getMemberId(), member.getName(),
                    member.getMembershipStrategy().getMembershipType(),
                    member.getBorrowedCount());
        }
        System.out.println("-".repeat(70));
    }

    private static void searchMemberById() {
        String memberId = getInput("\nEnter Member ID to search: ");
        Member member = librarySystem.findMemberById(memberId);

        if (member == null) {
            System.out.println("❌ Member not found!");
            return;
        }

        System.out.println("\n✅ Member Found:");
        member.displayMemberInfo();

        System.out.println("Currently Borrowed Items:");
        List<LibraryItem> borrowed = member.getBorrowedItems();
        if (borrowed.isEmpty()) {
            System.out.println("  (None)");
        } else {
            for (LibraryItem item : borrowed) {
                System.out.printf("  - %s (Due: %s)%n", item.getTitle(), item.getDueDate());
            }
        }
    }

    private static void viewMemberBorrowHistory() {
        String memberId = getInput("\nEnter Member ID: ");
        Member member = librarySystem.findMemberById(memberId);

        if (member == null) {
            System.out.println("❌ Member not found!");
            return;
        }

        System.out.println("\n📚 Borrow History for " + member.getName());
        System.out.println("-".repeat(50));

        List<LibraryItem> borrowed = member.getBorrowedItems();
        if (borrowed.isEmpty()) {
            System.out.println("No items borrowed.");
        } else {
            System.out.println("Currently Borrowed:");
            for (int i = 0; i < borrowed.size(); i++) {
                LibraryItem item = borrowed.get(i);
                System.out.printf("%d. %s (Due: %s)%n", i + 1, item.getTitle(), item.getDueDate());
            }
        }
    }

    private static void upgradeMembership() {
        String memberId = getInput("\nEnter Member ID to upgrade: ");
        Member member = librarySystem.findMemberById(memberId);

        if (member == null) {
            System.out.println("❌ Member not found!");
            return;
        }

        System.out.println("\nCurrent Membership: " + member.getMembershipStrategy().getMembershipType());
        showMembershipOptions();
        String choice = getInput("Select new membership type (1-4): ");

        MembershipStrategy newStrategy = parseMembershipStrategy(choice);
        if (newStrategy == null) {
            System.out.println("❌ Invalid selection.");
            return;
        }

        member.setMembershipStrategy(newStrategy);
        System.out.println("✅ Membership upgraded successfully!");
        System.out.println("New Membership: " + newStrategy.getMembershipType());
    }

    private static void manageItems() {
        boolean managing = true;
        while (managing) {
            System.out.println("\n" + "-".repeat(50));
            System.out.println("ITEM MANAGEMENT:");
            System.out.println("-".repeat(50));
            System.out.println("1. Add New Physical Book");
            System.out.println("2. Add New E-Book");
            System.out.println("3. View All Items");
            System.out.println("4. Search Item by Title");
            System.out.println("5. Delete Item");
            System.out.println("6. Back to Main Menu");
            System.out.println("-".repeat(50));

            String choice = getInput("Select option: ");

            switch (choice.trim().toUpperCase()) {
                case "1":
                    addPhysicalBook();
                    break;
                case "2":
                    addEBook();
                    break;
                case "3":
                    viewAllItems();
                    break;
                case "4":
                    searchItemByTitle();
                    break;
                case "5":
                    deleteItem();
                    break;
                case "6":
                    managing = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void addPhysicalBook() {
        System.out.println("\n📖 ADD NEW PHYSICAL BOOK");
        System.out.println("-".repeat(50));

        String title = getInput("Enter Book Title: ");
        String author = getInput("Enter Author: ");
        String isbn = getInput("Enter ISBN: ");

        String priceStr = getInput("Enter Price (Baht): ");
        double price;
        try {
            price = Double.parseDouble(priceStr);
            if (price < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid price format!");
            return;
        }

        String shelfLocation = getInput("Enter Shelf Location: ");

        PhysicalBook book = new PhysicalBook(title, author, isbn, price, shelfLocation);
        librarySystem.addItem(book);
        System.out.println("✅ Book added successfully!");
    }

    private static void addEBook() {
        System.out.println("\n📱 ADD NEW E-BOOK");
        System.out.println("-".repeat(50));

        String title = getInput("Enter Book Title: ");
        String author = getInput("Enter Author: ");
        String isbn = getInput("Enter ISBN: ");
        String downloadUrl = getInput("Enter Download URL: ");

        String sizeStr = getInput("Enter File Size (MB): ");
        double fileSize;
        try {
            fileSize = Double.parseDouble(sizeStr);
            if (fileSize < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid file size format!");
            return;
        }

        EBook ebook = new EBook(title, author, isbn, downloadUrl, fileSize);
        librarySystem.addItem(ebook);
        System.out.println("✅ E-Book added successfully!");
    }

    private static void viewAllItems() {
        List<LibraryItem> items = librarySystem.getAllItems();
        if (items.isEmpty()) {
            System.out.println("\n❌ No items in the library.");
            return;
        }

        System.out.println("\n📚 ALL LIBRARY ITEMS (" + items.size() + " total)");
        System.out.println("-".repeat(70));
        for (int i = 0; i < items.size(); i++) {
            LibraryItem item = items.get(i);
            String type = item instanceof EBook ? "E-Book" : "Physical Book";
            String status = item.isAvailable() ? "Available" : "Borrowed";
            System.out.printf("%d. [%s] %s - %s | Status: %s%n",
                    i + 1, type, item.getTitle(), item.getAuthor(), status);
        }
        System.out.println("-".repeat(70));
    }

    private static void searchItemByTitle() {
        String title = getInput("\nEnter Item Title to search: ");
        LibraryItem item = librarySystem.findItemByTitle(title);

        if (item == null) {
            System.out.println("❌ Item not found!");
            return;
        }

        System.out.println("\n✅ Item Found:");
        item.displayDetails();
    }

    private static void deleteItem() {
        String title = getInput("\nEnter Item Title to delete: ");
        if (librarySystem.deleteItem(title)) {
            System.out.println("✅ Item deleted successfully!");
        } else {
            System.out.println("❌ Item not found or cannot be deleted!");
        }
    }

    private static void handleBorrowing() {
        System.out.println("\n🏷️  BORROW ITEM");
        System.out.println("-".repeat(50));

        String memberId = getInput("Enter Member ID: ");
        Member member = librarySystem.findMemberById(memberId);
        if (member == null) {
            System.out.println("❌ Member not found!");
            return;
        }

        System.out.println("Member: " + member.getName() + " (" + member.getMembershipStrategy().getMembershipType() + ")");
        System.out.println("Current Borrowed: " + member.getBorrowedCount() + "/" + member.getMembershipStrategy().getBorrowLimit());

        String itemTitle = getInput("Enter Item Title to borrow: ");
        LibraryItem item = librarySystem.findItemByTitle(itemTitle);

        if (item == null) {
            System.out.println("❌ Item not found!");
            return;
        }

        if (item.checkOut(member)) {
            System.out.println("✅ Borrow successful!");
        } else {
            System.out.println("❌ Borrow failed!");
        }
    }

    private static void handleReturns() {
        System.out.println("\n🔄 RETURN ITEM");
        System.out.println("-".repeat(50));

        String memberId = getInput("Enter Member ID: ");
        Member member = librarySystem.findMemberById(memberId);
        if (member == null) {
            System.out.println("❌ Member not found!");
            return;
        }

        List<LibraryItem> borrowed = member.getBorrowedItems();
        if (borrowed.isEmpty()) {
            System.out.println("No items borrowed by this member.");
            return;
        }

        System.out.println("Items borrowed by " + member.getName() + ":");
        for (int i = 0; i < borrowed.size(); i++) {
            System.out.printf("%d. %s (Due: %s)%n", i + 1, borrowed.get(i).getTitle(), borrowed.get(i).getDueDate());
        }

        String indexStr = getInput("Select item number to return: ");
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (index < 0 || index >= borrowed.size()) {
                System.out.println("❌ Invalid selection!");
                return;
            }

            LibraryItem item = borrowed.get(index);
            item.returnItem();
            System.out.println("✅ Item returned successfully!");

            // Calculate late fee if any
            if (item.getDueDate() != null && java.time.LocalDate.now().isAfter(item.getDueDate())) {
                long daysLate = java.time.temporal.ChronoUnit.DAYS.between(item.getDueDate(), java.time.LocalDate.now());
                double lateFee = item.calculateLateFee((int) daysLate);
                double discountedFee = member.calculateLateFee(lateFee);
                System.out.println("⚠️  Late Fee: " + lateFee + " Baht → " + discountedFee + " Baht (after discount)");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input!");
        }
    }

    private static void viewReports() {
        boolean viewing = true;
        while (viewing) {
            System.out.println("\n" + "-".repeat(50));
            System.out.println("REPORTS:");
            System.out.println("-".repeat(50));
            System.out.println("1. Overdue Items Report");
            System.out.println("2. Most Borrowed Items Report");
            System.out.println("3. Member with Most Borrowings");
            System.out.println("4. Available vs Borrowed Items");
            System.out.println("5. Back to Main Menu");
            System.out.println("-".repeat(50));

            String choice = getInput("Select report: ");

            switch (choice.trim().toUpperCase()) {
                case "1":
                    librarySystem.reportOverdueItems();
                    break;
                case "2":
                    librarySystem.reportMostBorrowedItems();
                    break;
                case "3":
                    librarySystem.reportMemberMostBorrowings();
                    break;
                case "4":
                    librarySystem.reportAvailableVsBorrowed();
                    break;
                case "5":
                    viewing = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void viewStatistics() {
        librarySystem.displayStatistics();
    }

    private static void viewDemoMode() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println(" DESIGN PATTERNS DEMO (OOP CONCEPTS)");
        System.out.println("=".repeat(70));
        runDesignPatternDemo();
    }

    private static void runDesignPatternDemo() {
        // Clear existing data
        LibrarySystem demoSystem = LibrarySystem.getInstance();

        // ==================== SINGLETON PATTERN DEMO ====================
        System.out.println("\n[STEP 1] SINGLETON PATTERN:");
        System.out.println("Creating LibrarySystem instances...");
        LibrarySystem instance1 = LibrarySystem.getInstance();
        LibrarySystem instance2 = LibrarySystem.getInstance();
        System.out.println(" ✅ Same instance? " + (instance1 == instance2));
        System.out.println(" Proof: All instances manage the SAME library data!");

        // ==================== CREATE LIBRARY ITEMS ====================
        System.out.println("\n[STEP 2] POLYMORPHISM - Library Items:");
        System.out.println("Creating different types of items (inheritance):");

        // Add Physical Books
        PhysicalBook book1 = new PhysicalBook("Java Programming", "John Smith", "978-0134685991", 450.0, "A1-04");
        PhysicalBook book2 = new PhysicalBook("Clean Code", "Robert Martin", "978-0132350884", 520.0, "B2-15");
        PhysicalBook book3 = new PhysicalBook("Design Patterns", "Gang of Four", "978-0201633612", 680.0, "A3-22");

        EBook ebook1 = new EBook("Effective Java", "Joshua Bloch", "978-0134685991",
                "https://library.ebooks.com/effective-java.pdf", 5.2);
        EBook ebook2 = new EBook("Python Crash Course", "Eric Matthes", "978-1593279288",
                "https://library.ebooks.com/python-crash.pdf", 8.7);

        // Polymorphism: treating both PhysicalBook and EBook as LibraryItem
        LibraryItem[] items = {book1, book2, book3, ebook1, ebook2};
        for (LibraryItem item : items) {
            System.out.println(" ✅ Added: " + item.getTitle() + " [" + item.getClass().getSimpleName() + "]");
        }

        // ==================== STRATEGY PATTERN DEMO ====================
        System.out.println("\n[STEP 3] STRATEGY PATTERN - Membership Types:");
        System.out.println("Different membership strategies with different behaviors:");

        Member basicMember = new Member("M101", "Alice", new BasicMembershipStrategy());
        Member studentMember = new Member("M102", "Bob", new StudentMembershipStrategy());
        Member premiumMember = new Member("M103", "Charlie", new PremiumMembershipStrategy());
        Member familyMember = new Member("M104", "Diana", new FamilyMembershipStrategy());

        System.out.println("\n✅ Member Strategies Comparison:");
        System.out.printf("%-15s | Limit | Period | Fee Discount | Cost%n", "Membership Type");
        System.out.println("-".repeat(60));
        System.out.printf("%-15s | %-5d | %-6d | %-12s | %s%n",
                basicMember.getMembershipStrategy().getMembershipType(),
                basicMember.getMembershipStrategy().getBorrowLimit(),
                basicMember.getMembershipStrategy().getLoanPeriodDays(),
                "None",
                "Free");
        System.out.printf("%-15s | %-5d | %-6d | %-12s | %s%n",
                studentMember.getMembershipStrategy().getMembershipType(),
                studentMember.getMembershipStrategy().getBorrowLimit(),
                studentMember.getMembershipStrategy().getLoanPeriodDays(),
                "20%",
                "Free");
        System.out.printf("%-15s | %-5s | %-6d | %-12s | %s%n",
                premiumMember.getMembershipStrategy().getMembershipType(),
                "∞",
                premiumMember.getMembershipStrategy().getLoanPeriodDays(),
                "100%",
                "100 Baht/mo");
        System.out.printf("%-15s | %-5d | %-6d | %-12s | %s%n",
                familyMember.getMembershipStrategy().getMembershipType(),
                familyMember.getMembershipStrategy().getBorrowLimit(),
                familyMember.getMembershipStrategy().getLoanPeriodDays(),
                "10%",
                "150 Baht/mo");

        // ==================== INTERFACE IMPLEMENTATION ====================
        System.out.println("\n[STEP 4] INTERFACE IMPLEMENTATION:");
        System.out.println("Demonstrating Taxable interface:");
        System.out.printf("%-30s | Price | Tax Rate | Tax Amount%n", "Item");
        System.out.println("-".repeat(60));

        Taxable taxableBook = book1;
        System.out.printf("%-30s | %.2f | %s | %.2f%n",
                book1.getTitle(),
                book1.getPrice(),
                "7%",
                taxableBook.calculateTax());

        Taxable taxableEBook = ebook1;
        System.out.printf("%-30s | %.2f | %s | %.2f%n",
                ebook1.getTitle(),
                ebook1.getPrice(),
                "5%",
                taxableEBook.calculateTax());

        // ==================== BORROWING DEMO WITH POLYMORPHISM ====================
        System.out.println("\n[STEP 5] BORROWING DEMONSTRATION:");
        System.out.println("Different member types borrow items...\n");

        System.out.println("Basic Member (Limit: 1):");
        book1.checkOut(basicMember);

        System.out.println("\nStudent Member (Limit: 5):");
        book2.checkOut(studentMember);
        book3.checkOut(studentMember);

        System.out.println("\nPremium Member (Limit: Unlimited):");
        ebook1.checkOut(premiumMember);
        ebook2.checkOut(premiumMember);

        System.out.println("\nFamily Member (Limit: 6):");
        book1.returnItem();
        book1.checkOut(familyMember);

        // ==================== LATE FEE CALCULATION (Overloading) ====================
        System.out.println("\n[STEP 6] LATE FEE CALCULATION (Overriding):");
        System.out.println("Different member types get different fee discounts:\n");

        double baseFee = 100.0;
        System.out.printf("Base Late Fee: %.2f Baht%n%n", baseFee);

        System.out.println("1. Basic Member (0% discount):");
        System.out.printf("   Final Fee: %.2f Baht%n%n", basicMember.calculateLateFee(baseFee));

        System.out.println("2. Student Member (20% discount):");
        System.out.printf("   Final Fee: %.2f Baht%n%n", studentMember.calculateLateFee(baseFee));

        System.out.println("3. Premium Member (100% FREE):");
        System.out.printf("   Final Fee: %.2f Baht%n%n", premiumMember.calculateLateFee(baseFee));

        System.out.println("4. Family Member (10% discount):");
        System.out.printf("   Final Fee: %.2f Baht%n\n", familyMember.calculateLateFee(baseFee));

        // ==================== RUNTIME STRATEGY CHANGE ====================
        System.out.println("[STEP 7] RUNTIME STRATEGY CHANGE:");
        System.out.println("Alice upgrades from Basic to Premium membership...\n");

        System.out.println("Before Upgrade:");
        System.out.println(" Membership: " + basicMember.getMembershipStrategy().getMembershipType());
        System.out.println(" Borrow Limit: " + basicMember.getMembershipStrategy().getBorrowLimit());

        basicMember.setMembershipStrategy(new PremiumMembershipStrategy());

        System.out.println("\nAfter Upgrade:");
        System.out.println(" Membership: " + basicMember.getMembershipStrategy().getMembershipType());
        System.out.println(" Borrow Limit: " + (basicMember.getMembershipStrategy().hasUnlimitedBorrowing() ? "Unlimited ✨" : basicMember.getMembershipStrategy().getBorrowLimit()));

        System.out.println("\n✅ KEY OOP CONCEPTS DEMONSTRATED:");
        System.out.println("  ✓ INHERITANCE: LibraryItem → PhysicalBook, EBook");
        System.out.println("  ✓ POLYMORPHISM: Items treated as LibraryItem type");
        System.out.println("  ✓ INTERFACE: Taxable, DigitalContent, MembershipStrategy");
        System.out.println("  ✓ OVERRIDING: checkOut(), displayDetails(), calculateTax()");
        System.out.println("  ✓ OVERLOADING: calculateLateFee() with different parameters");
        System.out.println("  ✓ DESIGN PATTERN: Singleton, Strategy patterns");
    }

    private static void initializeSampleData() {
        // Initialize with sample data if library is empty
        if (librarySystem.getTotalMembers() == 0) {
            // Add sample members
            librarySystem.addMember(new Member("M001", "Somsak", new BasicMembershipStrategy()));
            librarySystem.addMember(new Member("M002", "Suda", new StudentMembershipStrategy()));
            librarySystem.addMember(new Member("M003", "Somchai", new PremiumMembershipStrategy()));
            librarySystem.addMember(new Member("M004", "Niran", new FamilyMembershipStrategy()));

            // Add sample items
            librarySystem.addItem(new PhysicalBook("Java Programming", "John Smith", "978-0134685991", 450.0, "A1-04"));
            librarySystem.addItem(new PhysicalBook("Clean Code", "Robert Martin", "978-0132350884", 520.0, "B2-15"));
            librarySystem.addItem(new PhysicalBook("Design Patterns", "Gang of Four", "978-0201633612", 680.0, "A3-22"));
            librarySystem.addItem(new EBook("Effective Java", "Joshua Bloch", "978-0134685991",
                    "https://library.ebooks.com/effective-java.pdf", 5.2));
            librarySystem.addItem(new EBook("Python Crash Course", "Eric Matthes", "978-1593279288",
                    "https://library.ebooks.com/python-crash.pdf", 8.7));
        }
    }

    private static String getInput(String prompt) {
        System.out.print(prompt);
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            // Handle EOF gracefully (for non-interactive input)
            System.out.println();
            return "8"; // Exit command
        }
    }
}
