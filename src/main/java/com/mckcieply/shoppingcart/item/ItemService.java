package com.mckcieply.shoppingcart.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void changeQuantity(List<Item> items, List<String> differences) {

        for(Item item : items) {
            // Converting char to int avoiding ASCII table values
            char difference_char = differences.get(items.indexOf(item)).charAt(9);
            int difference = Character.getNumericValue(difference_char);

            item.setQuantity(item.getQuantity() - difference);
            itemRepository.save(item);
        }
    }
}
