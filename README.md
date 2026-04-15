# 🏢 Smart Compound Management System (Web Application)

![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-MVC-brightgreen?style=for-the-badge&logo=springboot)
![Apache Derby](https://img.shields.io/badge/Database-Embedded%20Derby-yellow?style=for-the-badge)
![HTML](https://img.shields.io/badge/HTML5-Frontend-red?style=for-the-badge&logo=html5)
![CSS](https://img.shields.io/badge/CSS3-Style-blue?style=for-the-badge&logo=css3)
![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)

---

## 📖 Short Description

A full-stack **Spring Boot MVC web application** that manages residential compound operations including resident services, QR-based entry/exit tracking, secure authentication, and transportation booking.

---

## 📌 Overview

The Smart Compound Management System is a web-based application built using **Java Spring Boot and MVC architecture**. It is designed to digitalize and automate the management of residential compounds.

The system allows residents to interact with services while enabling staff to efficiently manage operations, monitor activity, and control access in real time.

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
- 🔑 Secure password storage using hashing (e.g., BCrypt)  
  - Passwords are never stored in plain text  
  - All passwords are hashed before saving to the database  
  - Ensures user credentials are protected even if the database is compromised  

---

## 🗄️ Database

- Embedded **Apache Derby Database**
- Lightweight file-based database (no external server required)
- Automatically integrates with Spring Boot application
- Suitable for academic and small-scale systems

---

## 🛠️ Tech Stack

- ☕ Java 17  
- 🌱 Spring Boot  
- 🧠 Spring MVC Architecture  
- 🗄️ Embedded Apache Derby  
- 🌐 HTML5 / CSS3  
- 🔧 Maven  

---

## 🧱 Architecture

- **Model** → Entity classes (Database models)  
- **View** → HTML + Thymeleaf pages  
- **Controller** → Handles HTTP requests  
- **Service** → Business logic layer  
- **Repository** → Data access layer (Spring Data JPA)  

---

## 🎯 Project Goal

To build a **real-world smart system** that improves residential compound management by automating services, enhancing security, and improving communication between residents and staff.

---

## ▶️ How to Run

```bash
# Clone project
git clone https://github.com/your-username/your-repo.git

# Open project in IntelliJ / Eclipse

# Run Spring Boot application

# Access system in browser
http://localhost:8080
