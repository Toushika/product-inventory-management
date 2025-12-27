# Product & Inventory Management Microservices

This project contains **two Spring Boot microservices**:

1. **Product Management Service** – manages product information.
   **Base URL:** `/product`

2. **Inventory Management Service** – manages inventory stock and checks product availability via **WebClient**.
   **Base URL:** `/inventory`

Both services use **PostgreSQL** for persistence and follow **REST API architecture**.

---

## Table of Contents

* [Technologies](#technologies)
* [APIs](#apis)
* [Product Availability Check](#product-availability-check)
* [Getting Started](#getting-started)
* [Build & Run](#build--run)
* [Future Improvements](#future-improvements)

---

## Technologies

* Java 21
* Spring Boot 3
* Spring Data JPA
* Spring Web / WebFlux (`WebClient`)
* PostgreSQL
* Gradle
* Lombok
* Actuator

---

## APIs

### Product Management Service

**Base URL:** `/product`

Example endpoints:

* `/save` – Create product
* `/get/all` – Get all products
* `/id?productId={id}` – Get product by ID
* `/category?productCategory={category}&page={page}&size={size}` – Get products by category with pagination
* `/update/{productId}` – Update a product
* `/delete/{productId}` – Delete a product
* `/availability/{productId}` – Check if product exists

### Inventory Management Service

**Base URL:** `/inventory`

Example endpoints:

* `/save` – Create inventory record
* `/reserve` – Reserve quantity of a product

---

## Product Availability Check

The Inventory service uses a **WebClient** to call the Product service `/product/availability/{productId}` before creating or reserving inventory.
This ensures that inventory operations are only performed for valid products.

---

## Getting Started

### Prerequisites

* Java 21
* Gradle
* PostgreSQL running on `localhost:5432`
* Two separate databases:

    * `product_management_db`
    * `inventory_management_db`

### Environment Variables / `application.yaml`

Each service should be configured with its respective database connection and credentials.

---

## Build & Run

1. Navigate to the service folder:

* Product service:

    * `cd product-management`
    * `./gradlew clean build`
    * `./gradlew bootRun`

* Inventory service:

    * `cd inventory-management`
    * `./gradlew clean build`
    * `./gradlew bootRun`

2. Default service URLs:

    * Product service: `http://localhost:8095/product`
    * Inventory service: `http://localhost:8096/inventory`
