# Simple Spring API

A simple **Spring Boot REST API** for managing tasks, demonstrating:

* RESTful API design
* DTO pattern
* Service layer abstraction
* Exception handling
* Validation with `jakarta.validation`

## 📌 Features

* Create a new task
* Update an existing task
* Retrieve a task by ID
* Retrieve all tasks
* JSON-based API responses with status codes and messages

---

## 🛠 Tech Stack

* **Java 17+**
* **Spring Boot 3+**
* **Spring Web**
* **Spring Validation**
* **Lombok**
* **Maven**

---

## 📂 Project Structure

```
src/
 ├─ main/java/com/example/simple_spring_api/
 │   ├─ controller/        # REST Controllers
 │   ├─ dto/               # Data Transfer Objects
 │   ├─ exceptions/        # Custom Exceptions
 │   ├─ model/              # Entity Classes
 │   ├─ service/           # Service Layer
 │   └─ SimpleSpringApiApplication.java
 └─ resources/
     └─ application.properties
```

---

## ▶️ Running the Application

### **1. Clone the repository**

```bash
git clone https://github.com/ashish-panicker/simple-spring-api.git
cd simple-spring-api
```

### **2. Build the project**

```bash
mvn clean install
```

### **3. Run the application**

```bash
mvn spring-boot:run
```

The application will start at:

```
http://localhost:8080
```

---

## 📡 API Endpoints

### 1. **Create a Task**

```
POST /api/v1/tasks
Content-Type: application/json
```

**Request Body:**

```json
{
  "title": "Learn Spring Boot",
  "description": "Study REST API development"
}
```

**Response:**

```json
{
  "status": "CREATED",
  "message": "Task created successfully",
  "data": {
    "id": 1,
    "name": "Learn Spring Boot",
    "description": "Study REST API development",
    "completed": false
  }
}
```

---

### 2. **Update a Task**

```
PUT /api/v1/tasks
Content-Type: application/json
```

**Request Body:**

```json
{
  "id": 1,
  "title": "Learn Spring Boot - Updated",
  "description": "Study REST API development in depth"
}
```

**Response:**

```json
{
  "status": "OK",
  "message": "Task updated successfully",
  "data": {
    "id": 1,
    "name": "Learn Spring Boot - Updated",
    "description": "Study REST API development in depth",
    "completed": false
  }
}
```

---

### 3. **Get Task by ID**

```
GET /api/v1/tasks/{taskId}
```

**Response:**

```json
{
  "status": "OK",
  "message": "Task fetched successfully",
  "data": {
    "id": 1,
    "name": "Learn Spring Boot",
    "description": "Study REST API development",
    "completed": false
  }
}
```

---

### 4. **Get All Tasks**

```
GET /api/v1/tasks
```

**Response (when tasks exist):**

```json
{
  "status": "OK",
  "message": "Task fetched successfully",
  "data": [
    {
      "id": 1,
      "name": "Learn Spring Boot",
      "description": "Study REST API development",
      "completed": false
    }
  ]
}
```

**Response (when no tasks exist):**

```json
{
  "status": "NO_CONTENT",
  "message": "No tasks found",
  "data": []
}
```

---

## ⚠️ Notes

* Request DTOs are validated with `@Valid` — invalid data will return `400 Bad Request`.
* Custom `TaskNotFoundException` is thrown if a task is not found.
* Uses `ResponseDto` for consistent API responses.

---

## 📜 License

This project is licensed under the MIT License.
