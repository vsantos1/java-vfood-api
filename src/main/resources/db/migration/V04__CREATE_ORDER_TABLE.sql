CREATE TABLE tb_orders
(
    id             SERIAL PRIMARY KEY,
    quantity       INT             NOT NULL,
    order_status   VARCHAR(20)     NOT NULL,
    payment_method VARCHAR(20)     NOT NULL,
    shipping_fee   DECIMAL(10, 2)  NOT NULL,
    observation    VARCHAR(255),
    total          DECIMAL(10, 2)  NOT NULL,
    created_at     TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    confirmed_at   TIMESTAMP,
    delivered_at   TIMESTAMP,
    canceled_at    TIMESTAMP,
    restaurant_id  BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES tb_restaurants (id)

) ENGINE = InnoDB;

INSERT INTO tb_orders (quantity, order_status, payment_method, observation, total, created_at, confirmed_at,
                       delivered_at, canceled_at, shipping_fee, restaurant_id)
VALUES (1, 'PENDING', 'CREDIT_CARD', 'Teste', 100.00, '2020-10-10 10:10:10', '2020-10-10 10:10:10',
        '2020-10-10 10:10:10', '2020-10-10 10:10:10', 7, 1);
INSERT INTO tb_orders (quantity, order_status, payment_method, observation, total, created_at, confirmed_at,
                       delivered_at, canceled_at, shipping_fee, restaurant_id)
VALUES (2, 'DELIVERED', 'PIX', 'Teste2', 150.00, '2020-10-10 10:10:10', '2020-10-10 10:10:10', '2020-10-10 10:10:10',
        '2020-10-10 10:10:10', 15, 3);
INSERT INTO tb_orders (quantity, order_status, payment_method, observation, total, created_at, confirmed_at,
                       delivered_at, canceled_at, shipping_fee, restaurant_id)
VALUES (3, 'PENDING', 'DEBIT_CARD', 'Teste2', 50.00, '2020-10-10 10:10:10', '2020-10-10 10:10:10',
        '2020-10-10 10:10:10', '2020-10-10 10:10:10', 10, 2);
