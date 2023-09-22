package com.mckcieply.shoppingcart.orders;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@RequiredArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product_id;

    private int quantity;

    private Date date_created;
}
