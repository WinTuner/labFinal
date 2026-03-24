City Central Library Management System
Overview

โปรเจคนี้เป็นระบบจัดการห้องสมุดที่พัฒนาด้วยภาษา Java โดยใช้หลัก OOP ในการออกแบบโครงสร้างโปรแกรมให้มีความเป็นระบบ เข้าใจง่าย และสามารถต่อยอดได้ในอนาคต รองรับการจัดการสมาชิก รายการหนังสือ และระบบยืม-คืน ผ่านเมนูแบบ command-line

---

## Features หลัก

##  Member Management

* สมัครสมาชิก
* ดูข้อมูล / ค้นหาสมาชิก
* เปลี่ยนประเภทสมาชิก

---

## Library Items

* เพิ่มหนังสือ (Physical / E-Book)
* แสดง / ค้นหา / ลบรายการ
* แสดงสถานะว่าง/ถูกยืม

---

## Borrowing System

* ยืม-คืนหนังสือ
* ตรวจสอบ limit ของสมาชิก
* คำนวณ due date อัตโนมัติ
* คิดค่าปรับเมื่อคืนช้า

---

## Reports

* รายการค้างส่ง (Overdue)
* หนังสือที่ถูกยืมบ่อย
* สมาชิกที่ยืมมากสุด
* สถานะ inventory

---

## Data Persistence

* บันทึกข้อมูลเป็น CSV
* โหลดข้อมูลอัตโนมัติ
* เซฟตอนออกจากโปรแกรม

---

##  OOP Concepts

* Inheritance → `PhysicalBook`, `EBook`
* Polymorphism → `LibraryItem`
* Interface → `MembershipStrategy`, `Taxable`
* Overriding / Overloading
* Encapsulation

**Design Patterns:**

* Singleton → `LibrarySystem`
* Strategy → Membership

---

##  Class Structure

```
LibraryItem (abstract)
 ├── PhysicalBook
 └── EBook

Member
 └── uses MembershipStrategy

MembershipStrategy
 ├── Basic
 ├── Student
 ├── Premium
 └── Family

LibrarySystem (Singleton)
```

---

#  Quick Start Guide

## Installation

### Requirements

* Java 8+
* Terminal / Command Prompt

---

##  Run Project

```bash
cd /home/natto/Documents/OOP-Lab-2026
javac -d com src/com/library/labFinal/*.java
java -cp com com.library.labFinal.LibraryManagementApp
```

---

##  Main Menu

```
1. Manage Members
2. Manage Library Items
3. Borrow Items
4. Return Items
5. View Reports
6. View Statistics
7. Demo Mode
8. Exit & Save
```

---

# 🎮 Demo (5 นาที)

### Step 1: Demo Mode

```
7
```

→ แสดง OOP + design patterns

### Step 2: Statistics

```
6
```

### Step 3: Members

```
1 → 2
```

### Step 4: Items

```
2 → 3
```

### Step 5: Exit

```
8
```

---

# Use Cases

* สมัครสมาชิก
* เพิ่มหนังสือ
* ยืมหนังสือ
* คืนหนังสือ
* ดูรายงาน

---

# Sample Data

### Members

* Somsak (Basic)
* Suda (Student)
* Somchai (Premium)
* Niran (Family)

### Items

* Java Programming
* Clean Code
* Design Patterns
* Effective Java
* Python Crash Course

---

# Validation

* ป้องกัน ID ซ้ำ
* ตรวจ input ว่าง
* เช็ค limit การยืม
* เช็คสถานะหนังสือ

---

# Data

* Save: `library_data.csv`
* Load: ตอนเปิดโปรแกรม

---

# File Structure

```
src/
com/
library_data.csv
README.md
```

---

# Troubleshooting

**Java not found**

```bash
java -version
```

**Compile ไม่ผ่าน**

```bash
pwd
ls src/com/library/labFinal/
```

**Run ไม่ได้**

```bash
ls com/com/library/labFinal/*.class
```

---

# Tips

* เริ่มจาก Demo Mode
* ดู Statistics ก่อน
* ลองสมัคร member ใหม่
* ใช้ search ช่วย

---

# สรุป

โปรเจคนี้สามารถทำงานได้จริง มีการใช้ OOP และมีโครงสร้างที่ชัดเจน เหมาะสำหรับการนำเสนอและต่อยอดในอนาคต เช่น GUI หรือระบบจองหนังสือ
