
# 🚀 JSON Dataset API Documentation

This document provides a detailed overview of the available API endpoints, including request and response examples.

---

## 🌐 **Base URL**
```
http://localhost:8080
```

---

## 🏆 **Problem Statement**
You are required to develop a Spring Boot application to handle JSON dataset records. The application should expose two APIs:

1. **Insert Record API:** To insert a JSON record into a specific dataset and persist it in the database.  
2. **Query API:** To query a specific dataset and perform group-by and sort-by operations on the JSON records.  swa

---

### ✅ **Requirements**
✔️ Use a relational database to store JSON dataset records  
✔️ Provide clear request and response structures  
✔️ Support dynamic group-by and sort-by operations based on the provided parameters  

---

## 🚀 **Setup & Run Instructions**
### **1. Clone the Repository**
```bash
git clone https://github.com/your-username/json-dataset-api.git
```

### **2. Navigate to the Project Directory**
```bash
cd json-dataset-api
```

### **3. Configure `application.properties`**  
Set up your database and application properties:

`src/main/resources/application.properties`
```properties
# Spring Boot Configurations
server.port=8080
spring.application.name=JSONDatasetAPI

# Database Configurations
spring.datasource.url=jdbc:mysql://localhost:3306/json_dataset
spring.datasource.username=root
spring.datasource.password=password

# Hibernate Configurations
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update


```

### **4. Build the Project**
```bash
./mvnw clean install
```

### **5. Run the Application**
```bash
./mvnw spring-boot:run
```

---

## 1️⃣ **Insert Record API**
### ➡️ **POST** `/api/dataset/{datasetName}/record`
**Description:**  
Insert a JSON record into a specific dataset and persist it in the database.

---

### 📥 **Request Parameters**
| Parameter     | Type   | Required | Description                |
|--------------|--------|----------|----------------------------|
| `datasetName` | String | ✅        | Name of the dataset         |

---

### 📤 **Request Body**
| Field         | Type   | Description                   |
|---------------|--------|-------------------------------|
| `id`          | Integer | ID of the record              |
| `name`        | String  | Name of the record            |
| `age`         | Integer | Age of the person             |
| `department`  | String  | Department                    |

---

### 📥 **Example Request**
```bash
POST /api/dataset/employee_dataset/record
```
```json
{
  "id": 1,
  "name": "John Doe",
  "age": 30,
  "department": "Engineering"
}
```

---

### ✅ **Success Response**
```json
{
  "message": "Record added successfully",
  "dataset": "employee_dataset",
  "recordId": 1
}
```

---

### ❌ **Error Responses**
| Status Code | Error               | Description                              |
|-------------|---------------------|------------------------------------------|
| 400         | Bad Request          | Invalid input parameters                |
| 500         | Internal Server Error | Internal processing error               |

---

## 2️⃣ **Query API with Group-By**
### ➡️ **GET** `/api/dataset/{datasetName}/query`
**Description:**  
Query dataset and perform grouping based on specified field.

---

### 📥 **Request Parameters**
| Parameter     | Type   | Required | Description                      |
|--------------|--------|----------|----------------------------------|
| `datasetName` | String | ✅        | Name of the dataset               |
| `groupBy`     | String | ❌        | Field to group by (e.g., `department`) |

---

### 📥 **Example Request**
```bash
GET /api/dataset/employee_dataset/query?groupBy=department
```

---

### ✅ **Success Response**
```json
{
  "groupedRecords": {
    "Engineering": [
      { "id": 1, "name": "John Doe", "age": 30, "department": "Engineering" },
      { "id": 2, "name": "Jane Smith", "age": 25, "department": "Engineering" }
    ],
    "Marketing": [
      { "id": 3, "name": "Alice Brown", "age": 28, "department": "Marketing" }
    ]
  }
}
```

---

### ❌ **Error Responses**
| Status Code | Error               | Description                              |
|-------------|---------------------|------------------------------------------|
| 400         | Bad Request          | Invalid input parameters                |
| 404         | Not Found            | Dataset or record not found             |
| 500         | Internal Server Error | Internal processing error               |

---

## 3️⃣ **Query API with Sort-By**
### ➡️ **GET** `/api/dataset/{datasetName}/query`
**Description:**  
Query dataset and perform sorting based on specified field.

---

### 📥 **Request Parameters**
| Parameter     | Type   | Required | Description                        |
|--------------|--------|----------|------------------------------------|
| `datasetName` | String | ✅        | Name of the dataset                 |
| `sortBy`       | String | ❌        | Field to sort by (e.g., `age`)       |
| `order`         | String | ❌        | Sorting order (`asc` or `desc`)      |

---

### 📥 **Example Request**
```bash
GET /api/dataset/employee_dataset/query?sortBy=age&order=asc
```

---

### ✅ **Success Response**
```json
{
  "sortedRecords": [
    { "id": 2, "name": "Jane Smith", "age": 25, "department": "Engineering" },
    { "id": 3, "name": "Alice Brown", "age": 28, "department": "Marketing" },
    { "id": 1, "name": "John Doe", "age": 30, "department": "Engineering" }
  ]
}
```

---

### ❌ **Error Responses**
| Status Code | Error               | Description                              |
|-------------|---------------------|------------------------------------------|
| 400         | Bad Request          | Invalid input parameters                |
| 404         | Not Found            | Dataset or record not found             |
| 500         | Internal Server Error | Internal processing error               |

---
