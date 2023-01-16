CREATE TABLE tb_products
(
    id            VARCHAR(36)     NOT NULL,
    name          VARCHAR(100)    NOT NULL,
    description   VARCHAR(255),
    price         DECIMAL(10, 2)  NOT NULL,
    active        BOOLEAN         NOT NULL DEFAULT TRUE,
    restaurant_id BIGINT UNSIGNED NOT NULL,
    UNIQUE (id),
    PRIMARY KEY (id),
    FOREIGN KEY (restaurant_id) REFERENCES tb_restaurants (id)
) ENGINE = InnoDB;

INSERT INTO tb_products(id, name, description, price, active, restaurant_id)
VALUES (UUID(), 'Pizza', 'Pizza de calabresa', 25.00, TRUE, 1);
INSERT INTO tb_products(id, name, description, price, active, restaurant_id)
VALUES (UUID(), 'Pizza', 'Pizza de frango', 25.00, TRUE, 2);
INSERT INTO tb_products(id, name, description, price, active, restaurant_id)
VALUES (UUID(), 'Pizza', 'Pizza de marguerita', 25.00, TRUE, 3);
INSERT INTO tb_products(id, name, description, price, active, restaurant_id)
VALUES (UUID(), 'Pizza', 'Pizza de portuguesa', 25.00, TRUE, 4);