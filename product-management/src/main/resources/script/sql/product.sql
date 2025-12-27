DROP TABLE products;

CREATE TABLE products (
                          product_id      VARCHAR(255) PRIMARY KEY,
                          name            VARCHAR(255),
                          description     TEXT,
                          sku             VARCHAR(255) NOT NULL UNIQUE,
                          product_category VARCHAR(50),
                          price           DOUBLE PRECISION,
                          created_at      VARCHAR(50),
                          updated_at      VARCHAR(50)
);