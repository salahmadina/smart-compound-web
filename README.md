# 🏢 Smart Compound Management System (Web Application)

![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-MVC-brightgreen?style=for-the-badge&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-Database-orange?style=for-the-badge&logo=mysql)
![HTML](https://img.shields.io/badge/HTML5-Frontend-red?style=for-the-badge&logo=html5)
![CSS](https://img.shields.io/badge/CSS3-Style-blue?style=for-the-badge&logo=css3)
![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)

---

## 📖 Short Description

A full-stack **Spring Boot MVC web application** that manages residential compound operations including resident services, QR-based entry/exit tracking, secure authentication, and transportation booking.

---

## 📌 Overview

The Smart Compound Management System is a web-based application built using **Java Spring Boot and MVC architecture**. It is designed to digitalize and automate the management of residential compounds.

The system provides a platform for residents to interact with compound services while allowing staff to efficiently manage operations in real time.

---

## 🚀 Features

### 👨‍👩‍👧 Residents
- Submit service & maintenance requests  
- Report issues inside the compound  
- QR-based entry and exit system  
- View personal activity history  
- Book compound bus transportation  

---

### 🧑‍💼 Staff / Admin
- Manage resident accounts  
- Handle service requests and complaints  
- Monitor QR entry/exit logs  
- Track resident movement inside compound  
- Manage bus bookings and schedules  

---

## 🚌 Transportation System

Residents can book seats in compound buses directly through the system, improving daily transportation organization and scheduling.

---

## 🔐 Security System

- QR code-based authentication for entry and exit  
- Role-based access control (Resident / Staff / Admin)  
- Activity logging and tracking system  
- 🔑 **Secure password storage using hashing (e.g., BCrypt)**  
  - Passwords are never stored in plain text  
  - All passwords are hashed before saving to the database  
  - Ensures user credentials are protected even if the database is compromised  

---

## 🛠️ Tech Stack

- ☕ Java 17  
- 🌱 Spring Boot  
- 🧠 Spring MVC Architecture  
- 🗄️ MySQL Database  
- 🌐 HTML5 / CSS3  
- 🔧 Maven  

---

## 🧱 Architecture

- **Model** → Entity classes (Database models)  
- **View** → HTML + Thymeleaf pages  
- **Controller** → Handles HTTP requests  
- **Service** → Business logic layer  
- **Repository** → Database layer (Spring Data JPA)  

---

## 🎯 Project Goal

To build a **real-world smart system** that improves residential compound management by automating services, enhancing security, and improving communication between residents and staff.

---

## ▶️ How to Run

```bash
# Clone project
git clone https://github.com/your-username/your-repo.git

# Open in IDE (IntelliJ / Eclipse)

# Configure MySQL database in application.properties

# Run Spring Boot application
