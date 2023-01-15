CREATE TABLE tb_restaurants
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    open         BOOLEAN       DEFAULT FALSE,
    shipping_fee DECIMAL(5, 2) DEFAULT 0.00,
    created_at   TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB;

INSERT INTO tb_restaurants (name, open, shipping_fee) VALUE ('Restaurant 1', TRUE, 5.00);
INSERT INTO tb_restaurants (name, open, shipping_fee) VALUE ('Restaurant 1', TRUE, 7.00);
INSERT INTO tb_restaurants (name, open, shipping_fee) VALUE ('Restaurant 2', TRUE, 10.00);
INSERT INTO tb_restaurants (name, open, shipping_fee) VALUE ('Restaurant 3', TRUE, 15.00);