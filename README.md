# 🗂️ Personal Study Resource Manager (PSRM)

A **personal practice project** designed to integrate multiple programming skills into a functional app for smarter **study resource management**.

---

## 📘 Overview

The **Personal Study Resource Manager (PSRM)** helps users **organize, tag, and recall** their study materials across their devices.

Instead of moving files around, users can:
- Add **custom tags** to group files.
- Write **notes or descriptions** to remember key details.
- Upload a **snapshot image** to visually recall the resource.
- **Open files directly** from their original locations using stored directory links.

This creates a flexible and searchable way to manage study files without changing their system structure.

---

## 🌟 Key Features

- 🏷️ **Tag-based organization** for quick filtering and grouping  
- 📝 **Custom notes/descriptions** to add learning context  
- 🖼️ **Optional image snapshots** to visually identify resources  
- 📂 **Direct file access** from stored local paths  

> 💡 *Future plans include file uploads, web sync, and multi-device access.*

---

## 🧠 Future Directions

- 🌐 **Web version** — allows login and syncing with the desktop app for multi-device access  
- 🧾 **Editable metadata online** — update tags or notes from the web without storing real files  
- 🛡️ **File storage** — potential support for secure cloud uploads (not included in current version)

---

## 🛠️ Tech Stack

| Layer | Technologies |
|-------|---------------|
| **Frontend** | JavaFX (Desktop), React (Web) |
| **Backend** | Java Spring Boot, Node.js |
| **Database** | H2 (Local), PostgreSQL (Production) |
| **Data Format** | REST API, JSON |

---

## 🚀 How to Run Locally

Enter following command in terminal from the project folder
   ```bash
   ./mvnw clean install -pl backend spring-boot:run
