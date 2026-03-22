# 🏛️ Library Management System - Design Document

## Project Overview
A comprehensive Java-based Library Management System demonstrating OOP principles and design patterns for educational purposes.

**Project Name**: City Central Library Management System
**Version**: 1.0.0
**Date**: March 2026
**Author**: OOP Lab Final Project

---

## 1. System Architecture

### 1.1 Class Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                     LibrarySystem (Singleton)                   │
│  - instance: LibrarySystem                                      │
│  - allItems: List<LibraryItem>                                 │
│  - allMembers: List<Member>                                    │
│  + getInstance(): LibrarySystem                                │
│  + addItem(item): void                                         │
│  + addMember(member): void                                     │
│  + findItemByTitle(title): LibraryItem                         │
│  + findMemberById(id): Member                                  │
│  + reportOverdueItems(): void                                  │
│  + reportMostBorrowedItems(): void                             │
│  + saveToCSV(filename): void                                   │
│  + loadFromCSV(filename): void                                 │
└─────────────────────────────────────────────────────────────────┘
                              △
                              │ manages
                    ┌─────────┴──────────┐
                    │                    │
         ┌──────────▼────────────┐   ┌──▼─────────────────┐
         │  LibraryItem (ABC)    │   │  Member            │
         │ ─────────────────     │   │ ──────────────     │
         │ - title               │   │ - memberId         │
         │ - author              │   │ - name             │
         │ - isbn                │   │ - borrowedItems    │
         │ - isAvailable         │   │ - strategy         │
         │ - borrower            │   │ + borrowItem()     │
         │ - dueDate             │   │ + returnItem()     │
         │ + checkOut(member)    │   │ + canBorrow()      │
         │ + returnItem()        │   │ + setStrategy()    │
         │ + displayDetails()    │   └────────────────────┘
         │ + calculateLateFee()  │            △
         └──────────┬────────────┘            │
                    │ extends               │
        ┌───────────┴───────────┐           │ uses
        │                       │           │
┌─────▼──────────────┐  ┌──────▼────────────────────┐
│  PhysicalBook      │  │  <<interface>>           │
│ ───────────────    │  │  MembershipStrategy      │
│ - price            │  │ ──────────────────       │
│ - shelfLocation    │  │ + getBorrowLimit()       │
│ implements: Taxable│  │ + getLoanPeriodDays()    │
└────────────────────┘  │ + applyLateFeeDiscount() │
                        │ + getMembershipType()    │
┌─────▼──────────────┐  └──────┬───────────────────┘
│  EBook             │         │
│ ───────────────    │   ┌─────┴────────────────────────┐
│ - downloadUrl      │   │                              │
│ - fileSize         │   ├──────────────────────────────┤
│ implements:        │   │ Concrete Strategies:         │
│   Taxable          │   ├──────────────────────────────┤
│   DigitalContent   │   │ • BasicMembershipStrategy    │
└────────────────────┘   │ • StudentMembershipStrategy  │
                         │ • PremiumMembershipStrategy  │
         ┌───────────────┤ • FamilyMembershipStrategy   │
         │               │                              │
    ┌────▼─────────┐     └──────────────────────────────┘
    │ <<interface>>│
    │   Taxable    │     ┌──────────────────────┐
    │ ─────────    │     │ <<interface>>        │
    │+ calculateTax()     │ DigitalContent      │
    └──────────────┘     │ ──────────────────  │
                         │ + streamOnline()    │
                         │ + download()        │
                         └──────────────────────┘
```

---

## 2. Design Patterns

### 2.1 Singleton Pattern

**Purpose**: Ensure only one LibrarySystem instance exists

**Implementation**: 
```java
public class LibrarySystem {
    private static LibrarySystem instance;
    
    private LibrarySystem() { } // Private constructor
    
    public static synchronized LibrarySystem getInstance() {
        if (instance == null) {
            instance = new LibrarySystem();
        }
        return instance;
    }
}
```

**Benefits**:
- Centralized management of library data
- Thread-safe access
- Global point of access

---

### 2.2 Strategy Pattern

**Purpose**: Encapsulate different membership behaviors

**Implementation**:
```java
public interface MembershipStrategy {
    int getBorrowLimit();
    int getLoanPeriodDays();
    double applyLateFeeDiscount(double baseFee);
    String getMembershipType();
}

// Concrete Strategies
public class BasicMembershipStrategy implements MembershipStrategy { ... }
public class StudentMembershipStrategy implements MembershipStrategy { ... }
public class PremiumMembershipStrategy implements MembershipStrategy { ... }
public class FamilyMembershipStrategy implements MembershipStrategy { ... }
```

**Benefits**:
- Easy to add new membership types
- Flexible runtime strategy changes
- Each membership encapsulates its own logic
- No need for if-else statements

**Example Usage**:
```java
Member member = new Member("M001", "John", new BasicMembershipStrategy());
// Later: upgrade to Premium
member.setMembershipStrategy(new PremiumMembershipStrategy());
```

---

## 3. OOP Principles Implementation

### 3.1 Inheritance

**Abstract Base Class**: `LibraryItem`
```java
public abstract class LibraryItem {
    protected String title;
    protected String author;
    protected String isbn;
    
    public abstract double getPrice();
    public abstract void displayDetails();
}
```

**Concrete Classes**:
- `PhysicalBook extends LibraryItem`
- `EBook extends LibraryItem`

---

### 3.2 Polymorphism

**Method Overriding**:
```java
// Parent method called with different implementations
LibraryItem[] items = {new PhysicalBook(...), new EBook(...)};
for (LibraryItem item : items) {
    item.displayDetails(); // Calls appropriate implementation
}
```

---

### 3.3 Encapsulation

```java
public class Member {
    private String memberId;      // Private field
    private List<LibraryItem> borrowedItems;
    
    // Public getter
    public String getMemberId() { return memberId; }
    
    // Public method to manage borrowed items
    public void borrowItem(LibraryItem item) { ... }
}
```

---

### 3.4 Interfaces

**MembershipStrategy Interface**:
- Defines contract for membership behavior
- Implemented by 4 concrete strategy classes

**Taxable Interface**:
- Calculates tax on items
- Implemented by PhysicalBook (7%) and EBook (5%)

**DigitalContent Interface**:
- Manages digital operations
- Implemented by EBook (streaming/downloading)

---

## 4. Data Flow

### 4.1 Borrowing Process

```
User Input (Member ID, Item Title)
        ↓
LibraryManagementApp.handleBorrowing()
        ↓
Find Member → Find Item
        ↓
Validation Checks:
  • Is item available?
  • Can member borrow?
  • Not exceeding limit?
        ↓
YES ─→ LibraryItem.checkOut(member)
         • Mark as unavailable
         • Set borrower
         • Calculate due date
         • Add to member's list
         
NO  ─→ Display error
```

### 4.2 Report Generation

```
User selects Report Type
        ↓
LibraryManagementApp.viewReports()
        ↓
Based on selection:
  ├─→ reportOverdueItems()
  │    • Filter items with dueDate < today
  │    • Calculate days overdue
  │    • Display list
  │
  ├─→ reportMostBorrowedItems()
  │    • Count borrows per item
  │    • Sort by count
  │    • Display ranking
  │
  ├─→ reportMemberMostBorrowings()
  │    • Find member with max borrowed count
  │    • Display member details
  │
  └─→ reportAvailableVsBorrowed()
       • Count available items
       • Count borrowed items
       • Display percentages
```

---

## 5. Key Classes & Responsibilities

| Class | Responsibility |
|-------|-----------------|
| **LibraryManagementApp** | Main entry point, menu system, user interaction |
| **LibrarySystem** | Core system management, data storage, singleton instance |
| **Member** | Member data, borrowed items, membership strategy |
| **LibraryItem** | Base class for items, checkout/return logic |
| **PhysicalBook** | Physical book properties, tax calculation |
| **EBook** | Digital content properties, streaming/download |
| **MembershipStrategy** | Interface for membership behavior |
| ***MembershipStrategy** | Concrete implementations for each membership type |

---

## 6. Use Cases & Flows

### Use Case 1: Register New Member
```
Actor: Librarian
Steps:
1. Open system
2. Select "Manage Members" → "Register New Member"
3. Enter Member ID, Name, Select Membership Type
4. System validates (ID uniqueness, name non-empty)
5. Member added to system
6. Display confirmation with member details
```

### Use Case 2: Borrow Item
```
Actor: Member
Preconditions:
  - Member exists in system
  - Item exists and is available
  - Member has not reached borrow limit
Steps:
1. Select "Borrow Items"
2. Enter Member ID and Item Title
3. System validates all conditions
4. Item marked as borrowed
5. Due date calculated (based on membership)
6. Item added to member's list
7. Confirmation displayed
```

### Use Case 3: Return Item
```
Actor: Member
Preconditions:
  - Member has borrowed items
Steps:
1. Select "Return Items"
2. Enter Member ID
3. View list of borrowed items
4. Select item to return
5. Item marked as available
6. Late fees calculated if applicable
7. Confirmation with fee details
```

### Use Case 4: View Reports
```
Actor: Librarian
Options:
1. Overdue Items
2. Most Borrowed Items
3. Member with Most Borrowings
4. Available vs Borrowed Items

System retrieves data and formats for display
```

### Use Case 5: View Statistics
```
Actor: Any User
Display:
- Total items in library
- Available items count
- Borrowed items count
- Total members count
- Percentages
```

---

## 7. Input Validation

| Input | Validation |
|-------|-----------|
| Member ID | Must be unique, non-empty |
| Member Name | Must be non-empty |
| Item Title | Must exist in system |
| Price | Must be positive number |
| File Size | Must be positive number |
| Membership Type | Must be 1-4 (valid option) |

---

## 8. File Storage Format (CSV)

```
=== LIBRARY DATA BACKUP ===
Type,Data

=== MEMBERS ===
MEMBER,M001,Somsak,Basic Member
MEMBER,M002,Suda,Student Member

=== ITEMS ===
BOOK,Java Programming,John Smith,978-0134685991,450.00,A1-04
EBOOK,Effective Java,Joshua Bloch,978-0134685991,url,5.20
```

---

## 9. Error Handling Strategy

| Scenario | Handling |
|----------|----------|
| Member ID already exists | Display error, prompt new ID |
| Item not available | Display "Item already borrowed" |
| Borrow limit exceeded | Display current count vs limit |
| Invalid input format | Catch NumberFormatException |
| File not found (CSV) | Gracefully skip loading |
| File write error | Display warning message |

---

## 10. Testing Checklist

- ✅ Singleton returns same instance
- ✅ Members can register with all 4 types
- ✅ Items can be borrowed and returned
- ✅ Borrow limits enforced
- ✅ Late fees calculated correctly
- ✅ Strategy changes work at runtime
- ✅ Reports generate without errors
- ✅ File I/O operations function
- ✅ Input validation catches errors
- ✅ All menus responsive to user input

---

## 11. Strengths of Design

1. **Flexibility**: New membership types can be added without modifying existing code
2. **Maintainability**: Clear separation of concerns
3. **Scalability**: Easy to extend with new features
4. **Robustness**: Comprehensive error handling
5. **Reusability**: Strategy pattern allows runtime behavior changes
6. **Testability**: Each component has clear responsibilities

---

## 12. Future Enhancements

1. **Database Integration**: Replace CSV with SQLite/MySQL
2. **GUI Interface**: JavaFX or Swing for better UX
3. **Reservation System**: Allow members to reserve items
4. **Email Notifications**: Overdue reminders
5. **Payment System**: Online fine payment
6. **Reviews & Ratings**: Member feedback system
7. **Mobile App**: Android/iOS companion app
8. **Analytics Dashboard**: Charts and graphs

---

**Document Version**: 1.0
**Last Updated**: March 2026

