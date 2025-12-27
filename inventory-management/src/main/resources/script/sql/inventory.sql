DROP TABLE inventories;

CREATE TABLE inventories (
                             inventory_id        VARCHAR(255) PRIMARY KEY,
                             product_id          VARCHAR(255),
                             available_quantity  INTEGER,
                             reserved_quantity   INTEGER,
                             in_stock            BOOLEAN,
                             created_at          VARCHAR(50),
                             update_at           VARCHAR(50)
);
