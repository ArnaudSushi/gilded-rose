package spring.services;

import java.util.List;

import fr.esiea.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import persistence.items.HashMapPersistence;
import persistence.items.NoSuchElementException;


@SpringBootApplication
@RestController
public class SpringWebApp {

    HashMapPersistence database = new HashMapPersistence();


    public static void main(String[] args) {
        SpringApplication.run(SpringWebApp.class);
    }

    @RequestMapping("/create_object")
    int[] createObject(@RequestParam("type") ItemType type, @RequestParam("name") String name,
                       @RequestParam("price") int price, @RequestParam("quality") int quality,
                       @RequestParam("quantity") int quantity) {

        Item item = type.build(name, price, quality);
        int id[] = new int[quantity];
        for (int i = 0; i < quantity; i++) {
            id[i] = database.saveItem(item);
        }
        return id;
    }

    @RequestMapping("/list_items")
    List<Item> listItems() {
        return database.getAllItems();
    }

    @RequestMapping("/buy_object")
    Item[] buyItem(@RequestParam("type") ItemType type, @RequestParam("name") String name,
                   @RequestParam("quantity") int quantity, @RequestParam("quality") int quality){

        Item item = type.build(name, 0, quality);
        Item items[] = new Item[quantity];

        for (int i = 0; i < quantity; i++) {
            try {
                items[i] = database.getItemByName(item);
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                return items;
            }
        }
        return items;
    }

    @RequestMapping("/get_types")
    List<String> listTypes() {
        return ItemType.listTypes();
    }
}
