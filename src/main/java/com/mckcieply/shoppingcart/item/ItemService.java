package com.mckcieply.shoppingcart.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int getLength() {
        return itemRepository.findAll().size();
    }

    // Creating hashmap to bind item id with quantity bought
    public HashMap<Long, Integer> boughtHashMap(List<Item> itemsInStock, List<String> itemsBought) {
        HashMap<Long, Integer> boughtHashMap = new HashMap<>();
        for(Item item : itemsInStock) {
            int index = itemsInStock.indexOf(item);

            long itemId = item.getId();

            char quantityChar = itemsBought.get(index).charAt(9);
            int quantity = Character.getNumericValue(quantityChar);

            boughtHashMap.put(itemId, quantity);
        }
        return boughtHashMap;
    }
    public void changeQuantity(List<Item> items, List<String> differences) {

        for(Item item : items) {
            // Converting char to int avoiding ASCII table values
            char difference_char = differences.get(items.indexOf(item)).charAt(9);
            int difference = Character.getNumericValue(difference_char);

            char item_id_char = differences.get(items.indexOf(item)).charAt(4);
            int item_id = Character.getNumericValue(item_id_char);


            item.setQuantity(item.getQuantity() - difference);
            itemRepository.save(item);

        }
    }

    public void calculatePrice(){

    }
}
