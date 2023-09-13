package com.mckcieply.shoppingcart.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Setter
@Getter
@ToString

public class Item {
    private String name;
    private double price;
    private int quantity;


}
