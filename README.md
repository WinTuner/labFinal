# 📚 City Central Library Management System

## 🎯 Overview
A comprehensive Java-based Library Management System that demonstrates professional OOP design patterns and real-world application functionality. The system manages library members, books, and borrowing operations with multiple membership strategies.

**Status**: ✅ Fully functional and tested
**Java Version**: Java 8+
**Build System**: Command-line compilation

---

## ✨ Key Features

### 1. **Member Management**
- Register new members with personalized membership types
- View all members and their borrowing history
- Search members by ID
- Upgrade/change membership plans dynamically
- Support for 4 membership tiers: Basic, Student, Premium, Family

### 2. **Library Inventory Management**
- Add physical books with shelf locations
- Add e-books with download URLs
- View all items with availability status
- Search items by title
- Delete items from inventory

### 3. **Borrowing & Returning System**
- Borrow items with membership-specific limits
- Automatic due date calculation based on membership type
- Return items with late fee calculation
- Member-specific borrow limits (1-6 items or unlimited)
- Variable loan periods (14-30 days)

### 4. **Reports & Analytics**
- **Overdue Items Report**: Track items past their due date
- **Most Borrowed Items**: Identify popular items
- **Member Activity**: See who borrowed the most
- **Inventory Status**: Available vs. Borrowed items comparison

### 5. **Data Persistence**
- Save library data to CSV format
- Load previously saved data on startup
- Automatic data saving on exit

---

## 🏗️ Architecture & OOP Concepts

### **Design Patterns**
1. **Singleton Pattern**: `LibrarySystem` - Ensures single centralized library instance
2. **Strategy Pattern**: `MembershipStrategy` - Flexible membership type system
3. **Polymorphism**: `LibraryItem` abstract class with `PhysicalBook` and `EBook` subclasses

### **OOP Implementation**
- **Inheritance**: `PhysicalBook` and `EBook` extend `LibraryItem`
- **Interfaces**: `MembershipStrategy`, `Taxable`, `DigitalContent`
- **Overriding**: Methods like `checkOut()`, `displayDetails()`, `calculateTax()`
- **Overloading**: `calculateLateFee()` with different parameter types
- **Encapsulation**: Private fields with public getters/setters

### **Class Hierarchy**
```
LibraryItem (abstract)
├── PhysicalBook (implements Taxable)
└── EBook (implements DigitalContent, Taxable)

Member
└── uses MembershipStrategy (interface)

MembershipStrategy (interface)
├── BasicMembershipStrategy
├── StudentMembershipStrategy
├── PremiumMembershipStrategy
└── FamilyMembershipStrategy

LibrarySystem (Singleton)
```

---

## 📋 Membership Types

| Type | Borrow Limit | Loan Period | Late Fee Discount | Cost |
|------|--------------|-------------|-------------------|------|
| **Basic** | 1 item | 14 days | None (0%) | FREE |
| **Student** | 5 items | 21 days | 20% | FREE |
| **Premium** | Unlimited | 30 days | 100% (FREE!) | 100 Baht/month |
| **Family** | 6 items | 21 days | 10% | 150 Baht/month |

---

## 🚀 How to Run

### **1. Compile the Project**
```bash
cd /home/natto/Documents/OOP-Lab-2026
javac -d com src/com/library/labFinal/*.java
```

### **2. Run the Application**
```bash
java -cp com com.library.labFinal.LibraryManagementApp
```

### **3. Interactive Menu**
The application starts with a main menu offering:
```
1. Manage Members (Register, View, Search)
2. Manage Library Items (Add, View, Search)
3. Borrow Items
4. Return Items
5. View Reports (Overdue, Most Borrowed)
6. View Statistics
7. View Demo Mode (Design Patterns)
8. Exit & Save
```

---

## 📖 Use Cases

### **Use Case 1: Register a New Member**
1. Select "1. Manage Members" from main menu
2. Choose "1. Register New Member"
3. Enter Member ID, Name, and select membership type
4. System confirms registration with member details

### **Use Case 2: Add Items to Library**
1. Select "2. Manage Library Items"
2. Choose to add Physical Book or E-Book
3. Enter details (title, author, ISBN, price/URL, etc.)
4. Item is added to system

### **Use Case 3: Borrow an Item**
1. Select "3. Borrow Items"
2. Enter Member ID and Item Title
3. System checks:
   - Item availability
   - Member's borrow limit
4. If valid, item is borrowed with calculated due date

### **Use Case 4: Return an Item**
1. Select "4. Return Items"
2. Enter Member ID
3. Select item from their borrowed list
4. System calculates late fees if applicable

### **Use Case 5: View Reports**
1. Select "5. View Reports"
2. Choose report type:
   - Overdue Items
   - Most Borrowed Items
   - Member with Most Borrowings
   - Available vs. Borrowed Items

---

## 🎨 Sample Data

The system initializes with sample data:

**Members:**
- Somsak (M001) - Basic Member
- Suda (M002) - Student Member
- Somchai (M003) - Premium Member
- Niran (M004) - Family Member

**Items:**
- Java Programming by John Smith (Physical Book)
- Clean Code by Robert Martin (Physical Book)
- Design Patterns by Gang of Four (Physical Book)
- Effective Java by Joshua Bloch (E-Book)
- Python Crash Course by Eric Matthes (E-Book)

---

## 📊 Input Validation & Error Handling

✅ **Member ID Uniqueness**: Prevents duplicate member IDs
✅ **Numeric Validation**: Checks price and file size inputs
✅ **Availability Checks**: Prevents borrowing unavailable items
✅ **Limit Enforcement**: Respects membership borrow limits
✅ **Name Validation**: Ensures non-empty member names
✅ **File I/O Error Handling**: Graceful handling of CSV operations

---

## 💾 Data Persistence

The system automatically:
- **Saves** data to `library_data.csv` when exiting
- **Loads** data from `library_data.csv` on startup
- **Displays** status messages for file operations

---

## 🎓 Design Patterns Demonstration

### **Demo Mode (Menu Option 7)**
The application includes a comprehensive demo showing:
1. **Singleton Pattern**: Same instance returned on multiple calls
2. **Polymorphism**: Different item types treated as LibraryItem
3. **Strategy Pattern**: Different membership strategies in action
4. **Interface Implementation**: Taxable interface on items
5. **Borrowing Demo**: Different limits based on membership
6. **Late Fee Calculation**: Strategy-based fee discounts
7. **Runtime Strategy Change**: Members upgrading memberships

---

## 📝 Technical Details

### **File Structure**
```
src/com/library/labFinal/
├── LibraryManagementApp.java      (Main entry point with menu system)
├── LibrarySystem.java              (Singleton system core)
├── Member.java                     (Member class)
├── LibraryItem.java               (Abstract base for items)
├── PhysicalBook.java              (Physical book implementation)
├── EBook.java                     (Digital book implementation)
├── MembershipStrategy.java        (Strategy interface)
├── BasicMembershipStrategy.java   (Basic membership)
├── StudentMembershipStrategy.java (Student membership)
├── PremiumMembershipStrategy.java (Premium membership)
├── FamilyMembershipStrategy.java  (Family membership)
├── Taxable.java                   (Tax calculation interface)
├── DigitalContent.java            (Digital content interface)
└── README.md                      (This file)
```

### **Key Methods**

**LibrarySystem (Singleton)**
- `getInstance()`: Get single library instance
- `addMember()`, `addItem()`: Add to system
- `findMemberById()`, `findItemByTitle()`: Search operations
- `reportOverdueItems()`: Generate overdue report
- `reportMostBorrowedItems()`: Borrowing statistics
- `saveToCSV()`, `loadFromCSV()`: Data persistence

**LibraryItem (Abstract)**
- `checkOut()`: Borrow item
- `returnItem()`: Return item
- `calculateLateFee()`: Late fee calculation
- `displayDetails()`: Display item information

**Member**
- `borrowItem()`, `returnItem()`: Manage borrowed items
- `canBorrow()`: Check if can borrow more
- `calculateLateFee()`: Get fee with membership discount
- `setMembershipStrategy()`: Change membership type

---

## ✔️ Testing Results

✅ All classes compile without errors
✅ Singleton pattern works correctly
✅ Strategy pattern demonstrates all membership types
✅ Borrowing respects membership limits
✅ Late fee discounts apply correctly
✅ File I/O operations function properly
✅ Interactive menu system is responsive
✅ All 5+ use cases work as intended

---

## 🎯 Success Criteria Met

| Requirement | Status |
|------------|--------|
| Runs without errors | ✅ |
| OOP: Class, inheritance, interface, override, overload | ✅ |
| In-memory + file storage (CSV) | ✅ |
| Input validation & error handling | ✅ |
| 5+ use cases | ✅ (8 use cases) |
| 2+ reports | ✅ (4 reports) |
| Clear documentation | ✅ |
| Design patterns demonstrated | ✅ |
| Demo mode | ✅ |

---

## 🔧 Future Enhancements

- Add GUI interface with JavaFX or Swing
- Implement reservation system
- Add email notifications for overdue items
- Extended report generation (PDF export)
- Member rating/review system
- Fine payment tracking
- Book wishlist feature

---

## 📞 Support & Contact

For questions or issues with the Library Management System, please refer to:
1. The demo mode (Menu Option 7) for design pattern explanations
2. Individual class documentation in source files
3. The comprehensive architecture diagram above

---

**Developed**: March 2026
**Class**: OOP Lab Final Project
**Version**: 1.0.0

