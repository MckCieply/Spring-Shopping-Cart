package com.mckcieply.shoppingcart.orderItems;

import com.mckcieply.shoppingcart.item.Item;
import com.mckcieply.shoppingcart.orders.Order;
import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "orderItems")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @NonNull
    private Integer quantity;

}
