package com.ifood.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Double total;

    @Column(name = "shipping_fee")
    private Double shippingFee;

    private OrderStatus orderStatus;

    private PaymentMethods paymentMethods;

    private Integer quantity;

    private String observation;

    private Date createdAt;
    private Date confirmedAt;

    private Date deliveredAt;

    private Date canceledAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
