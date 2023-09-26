package com.mckcieply.shoppingcart.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    // Save item to database
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    // Get all items from database
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    // Get length of list of items
    public int getLength() {
        return itemRepository.findAll().size();
    }

    // Get list of quantities bought from application/html type form
    public List <Integer> itemsQuantityBought(String fromForm){
        String[] separatedItems = fromForm.split("&");
        List<Integer> quantities = new ArrayList<>();
        for (String item : separatedItems) {
            String[] quantity = item.split("=");
            quantities.add(Integer.parseInt(quantity[1]));

        }
        return quantities;
    }
    // Creating hashmap to bind item id with quantity bought
    public HashMap<Long, Integer> boughtHashMap(List<Item> itemsInStock, List<Integer> itemsBought) {
        HashMap<Long, Integer> boughtHashMap = new HashMap<>();
        for(Item item : itemsInStock) {
            int index = itemsInStock.indexOf(item);

            boughtHashMap.put(item.getId(), itemsBought.get(index));
        }
        return boughtHashMap;
    }

    // Changing quantity of items in stock based on ammount ordered
    public void changeQuantity(HashMap<Long, Integer> boughtItems, List<Item> itemsInStock) {

        for(Item item : itemsInStock) {
            long itemId = item.getId();
            item.setQuantity(item.getQuantity() - boughtItems.get(itemId));

            itemRepository.save(item);

        }
    }

    // Calculating total price of items bought
    public Double calculatePrice(HashMap<Long, Integer> boughtItems){
        double totalPrice = 0;
        for (long key : boughtItems.keySet()) {
            Item item = itemRepository.findById(key).get();
            totalPrice += item.getPrice() * boughtItems.get(key);
        }
        return totalPrice;
    }


}
