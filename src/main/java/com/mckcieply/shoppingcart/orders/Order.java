package com.mckcieply.shoppingcart.orders;

import jakarta.persistence.*;

import lombok.*;

import java.util.Date;

@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Double total_price;

    @NonNull
    private Date date_created;


}
