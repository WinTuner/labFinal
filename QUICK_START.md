# 🚀 Quick Start Guide - Library Management System

## Installation & Setup

### Prerequisites
- Java 8 or higher installed
- Terminal/Command Prompt access

### Step 1: Navigate to Project Directory
```bash
cd /home/natto/Documents/OOP-Lab-2026
```

### Step 2: Compile the Project
```bash
javac -d com src/com/library/labFinal/*.java
```

Expected output: No errors, compiles silently

### Step 3: Run the Application
```bash
java -cp com com.library.labFinal.LibraryManagementApp
```

---

## First Run - What You'll See

```
======================================================================
  🏛️  CITY CENTRAL LIBRARY - MANAGEMENT SYSTEM
======================================================================
  Welcome to the Library Management System
  Location: 123 Main Street, Downtown
======================================================================

----------------------------------------------------------------------
MAIN MENU:
----------------------------------------------------------------------
1. Manage Members (Register, View, Search)
2. Manage Library Items (Add, View, Search)
3. Borrow Items
4. Return Items
5. View Reports (Overdue, Most Borrowed)
6. View Statistics
7. View Demo Mode (Design Patterns)
8. Exit & Save
----------------------------------------------------------------------
```

---

## 5-Minute Demo Walkthrough

### ✅ Demo Scenario: Complete Workflow

#### **Step 1: View Demo Mode (2 minutes)**
```
Input: 7
→ Shows all OOP concepts in action
→ Demonstrates inheritance, polymorphism, interfaces, strategy pattern
→ Shows borrowing with different membership levels
```

#### **Step 2: View Statistics (1 minute)**
```
Input: 6
→ Shows library has 4 members and 5 items
→ 3 items available, 2 borrowed
```

#### **Step 3: View All Members (30 seconds)**
```
Input: 1 → 2
→ Shows 4 pre-loaded members
  - Somsak (Basic)
  - Suda (Student)
  - Somchai (Premium)
  - Niran (Family)
```

#### **Step 4: View All Items (30 seconds)**
```
Input: 2 → 3
→ Shows 5 pre-loaded items
  - 3 Physical Books
  - 2 E-Books
```

#### **Step 5: Exit (15 seconds)**
```
Input: 8
→ Saves data to library_data.csv
→ Displays goodbye message
```

**Total Time: ~5 minutes** ⏱️

---

## Key Menu Options Explained

### 1️⃣ **Manage Members**
- **Register New Member**: Create new member with membership type
- **View All Members**: List all registered members
- **Search Member by ID**: Find specific member and view details
- **View Borrow History**: See what member has borrowed
- **Upgrade/Change Membership**: Change membership type

### 2️⃣ **Manage Library Items**
- **Add Physical Book**: Enter book details with shelf location
- **Add E-Book**: Enter e-book details with download URL
- **View All Items**: List all library items
- **Search by Title**: Find item and view details
- **Delete Item**: Remove available items from system

### 3️⃣ **Borrow Items**
- Enter member ID
- Enter item title
- System validates and allows borrowing if:
  - Item is available
  - Member hasn't reached limit
  - Member is registered

### 4️⃣ **Return Items**
- Enter member ID
- Select item from their list
- System marks as available
- Calculates late fees if overdue

### 5️⃣ **View Reports**
1. **Overdue Items**: Items past due date
2. **Most Borrowed Items**: Popular items
3. **Member Activity**: Who borrowed most
4. **Inventory Status**: Available vs Borrowed

### 6️⃣ **View Statistics**
- Total items and members
- Available/borrowed counts
- System singleton confirmation

### 7️⃣ **Demo Mode**
- Shows 7 design pattern demonstrations
- Interactive OOP explanation
- Perfect for understanding code structure

---

## Sample Inputs for Testing

### Test Case 1: Register New Member
```
Menu: 1 → 1
Member ID: M005
Name: Sarah
Membership: 2 (Student)
✓ Member registered
```

### Test Case 2: View Member Details
```
Menu: 1 → 3
Member ID: M001
✓ Shows Somsak's details
```

### Test Case 3: Search for Book
```
Menu: 2 → 4
Title: Java Programming
✓ Shows book details and shelf location
```

### Test Case 4: Generate Report
```
Menu: 5 → 1
✓ Shows overdue items (if any)
```

---

## File System

After running the application:

```
OOP-Lab-2026/
├── src/                           (Source code)
│   └── com/library/labFinal/*.java
├── com/                           (Compiled classes)
│   └── com/library/labFinal/*.class
├── library_data.csv              (Data backup - created on exit)
├── README.md                     (Full documentation)
├── DESIGN_DOCUMENT.md           (Architecture & design)
└── QUICK_START.md               (This file)
```

---

## Troubleshooting

### Problem: "Java not found"
**Solution**: Ensure Java is installed and in PATH
```bash
java -version    # Check if installed
```

### Problem: "Compilation failed"
**Solution**: Make sure you're in the correct directory
```bash
pwd    # Verify current directory
ls src/com/library/labFinal/    # Check files exist
```

### Problem: "Class not found"
**Solution**: Ensure compilation created classes
```bash
ls com/com/library/labFinal/*.class    # Check .class files
```

---

## Data Persistence

- **First Run**: Creates sample data automatically
- **Data Saved**: On menu option 8 (Exit & Save)
- **Data Loaded**: On next startup from library_data.csv
- **Format**: CSV for easy viewing/editing

---

## Sample Data Included

**Members** (Pre-loaded):
```
ID: M001, Name: Somsak, Type: Basic (1 item, 14 days, 0% discount)
ID: M002, Name: Suda, Type: Student (5 items, 21 days, 20% discount)
ID: M003, Name: Somchai, Type: Premium (∞ items, 30 days, 100% discount)
ID: M004, Name: Niran, Type: Family (6 items, 21 days, 10% discount)
```

**Items** (Pre-loaded):
```
Books:
- Java Programming (John Smith)
- Clean Code (Robert Martin)
- Design Patterns (Gang of Four)

E-Books:
- Effective Java (Joshua Bloch)
- Python Crash Course (Eric Matthes)
```

---

## System Requirements

| Component | Requirement |
|-----------|-------------|
| Java Version | 8+ |
| Memory | 100 MB minimum |
| Disk Space | 50 MB |
| OS | Windows, Mac, Linux |

---

## Tips for First Use

1. **Start with Demo Mode** (Option 7) to understand the system
2. **View Statistics** (Option 6) to see data overview
3. **View Members & Items** before borrowing
4. **Try registering a new member** to understand workflow
5. **Use Search features** to find specific items/members

---

## Common Workflows

### Workflow 1: One-Time Demo
```
1. Run application
2. Select option 7 (Demo Mode)
3. Read through all 7 design patterns
4. Select option 8 (Exit & Save)
```

### Workflow 2: System Administration
```
1. View Statistics (6)
2. View Reports (5)
3. Manage Members (1)
4. Manage Items (2)
5. Exit & Save (8)
```

### Workflow 3: Member Operations
```
1. Borrow Items (3)
2. Return Items (4)
3. View Borrow History (1 → 4)
4. Exit & Save (8)
```

---

## Next Steps

- **Read README.md** for full features documentation
- **Read DESIGN_DOCUMENT.md** for architecture details
- **Explore the source code** in src/com/library/labFinal/
- **Modify and extend** the system with new features

---

**Created**: March 2026
**Version**: 1.0.0
**Status**: Ready to Use ✅

