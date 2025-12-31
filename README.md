
# 📚 Books Management API

A **backend REST API** built with **Java Spring Boot** to manage books and stationery items.
This project allows users to **add, view, update, delete, search, and filter items**, demonstrating clean API design and database handling.

---

## 📝 Features

* **CRUD Operations:** Create, Read, Update, Delete stationery items
* **Search & Filtering:** Search items by name or category and filter results easily
* **Pagination:** View large lists efficiently
* **DTO Pattern:** Separate API data from database models for clean architecture
* **MyBatis Integration:** Handles database operations smoothly
* **Layered Architecture:** Controller → Service → Mapper → DTO → Model

---

## 💻 Tech Stack

* **Language:** Java
* **Framework:** Spring Boot
* **Database:** MySQL / PostgreSQL
* **ORM:** MyBatis
* **Tools:** Postman (for API testing), Maven

---

## 🚀 API Endpoints

| Method | Endpoint                          | Description                            |
| ------ | --------------------------------- | -------------------------------------- |
| GET    | `/api/items`                      | Get all items (paginated)              |
| GET    | `/api/items/{id}`                 | Get item by ID                         |
| POST   | `/api/items`                      | Add a new item                         |
| PUT    | `/api/items/{id}`                 | Update item details                    |
| DELETE | `/api/items/{id}`                 | Delete an item                         |
| GET    | `/api/items/search?query=keyword` | Search items by name or category       |


---

## 📂 Project Structure

```
controller  → Handles API requests
service     → Contains business logic
mapper      → MyBatis SQL queries
dto         → Data Transfer Objects for requests/responses
model       → Database entities
```

---

## 🎯 Purpose

This project demonstrates:

* Real-world API design skills
* Database handling with MyBatis
* Clean architecture using DTOs and layered design
* Efficient pagination
* Searching and filtering functionality

---

## 🔮 Future Improvements

* Add authentication and authorization using Spring Security
* Advanced search and multi-attribute filtering
* Swagger/OpenAPI documentation
* Enhanced validation and centralized exception handling

---

## 👩‍💻 Author

**Jyoti Dangal**
BE Computer Engineering
