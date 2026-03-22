📚 City Central Library Management System
🎯 Overview

โปรเจคนี้เป็นระบบห้องสมุดที่เขียนด้วย Java โดยใช้หลัก OOP เพื่อจัดการสมาชิก หนังสือ และระบบยืม-คืน สามารถใช้งานผ่านเมนูในโปรแกรมได้จริง

✨ Features หลัก
👤 Member
สมัครสมาชิก เลือกประเภทสมาชิกได้ (Basic, Student, Premium, Family)
ดูข้อมูลสมาชิก
ค้นหาสมาชิก
เปลี่ยนประเภทสมาชิกได้

📖 Library Items
เพิ่มหนังสือ (Physical) และ E-Book
ดูรายการทั้งหมด
ค้นหาหนังสือ
ลบรายการได้

🔁 Borrow / Return
ยืมหนังสือ (มี limit ตามประเภทสมาชิก)
คืนหนังสือ
คำนวณวันครบกำหนดอัตโนมัติ
มีค่าปรับกรณีคืนช้า

📊 Reports
รายการหนังสือค้างคืน
หนังสือที่ถูกยืมบ่อย
สมาชิกที่ยืมเยอะ
สถานะหนังสือ (ว่าง/ไม่ว่าง)

💾 Data
บันทึกข้อมูลเป็นไฟล์ CSV
โหลดข้อมูลเดิมตอนเปิดโปรแกรม
เซฟอัตโนมัติตอนออก

🧠 OOP ที่ใช้
Inheritance → Book, EBook สืบทอดจาก LibraryItem
Interface → MembershipStrategy, Taxable, DigitalContent
Polymorphism → ใช้ LibraryItem แทนหลายประเภท
Overriding → เช่น displayDetails()
Overloading → เช่น calculateLateFee()
Encapsulation → ใช้ private + getter/setter

🏗️ โครงสร้างหลัก
LibraryItem (abstract)
 ├── PhysicalBook
 └── EBook

Member
MembershipStrategy (interface)
 ├── Basic
 ├── Student
 ├── Premium
 └── Family

LibrarySystem (Singleton)
📋 Membership (สรุปสั้น)
Basic → ยืมได้ 1 เล่ม
Student → 5 เล่ม
Premium → ไม่จำกัด
Family → 6 เล่ม

(แต่ละแบบมีระยะเวลาและส่วนลดค่าปรับต่างกัน)

🚀 วิธีรัน
javac -d com src/com/library/labFinal/*.java
java -cp com com.library.labFinal.LibraryManagementApp

📖 Use Case ตัวอย่าง
สมัครสมาชิก
เพิ่มหนังสือ
ยืมหนังสือ
คืนหนังสือ
ดูรายงาน

⚠️ Validation
เช็ค ID ซ้ำ
เช็ค input ตัวเลข
เช็คว่ายืมเกิน limit ไหม
เช็คหนังสือว่างหรือไม่

🎯 สรุป
โปรแกรมรันได้จริง
มีเมนูใช้งาน
ใช้ OOP ครบ (class, interface, inheritance, etc.)
มี file save/load
มี use case ครบตามที่กำหนด
