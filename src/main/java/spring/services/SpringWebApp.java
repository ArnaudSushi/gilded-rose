package spring.services;

import java.util.ArrayList;
import java.util.List;

import fr.esiea.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import persistence.items.HashMapPersistence;
import persistence.items.ItemPersistence;
import persistence.items.NoSuchElementException;
import persistence.items.NotEnoughElementsException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
@RestController
public class SpringWebApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringWebApp.class);

    static ItemPersistence database = new HashMapPersistence();


    public static void main(String[] args) {
        SpringApplication.run(SpringWebApp.class);
        Runnable updateQualityTask = new GildedRose(database);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(updateQualityTask, 0L, 15L, TimeUnit.MINUTES);
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

    /*
     *  Get all the items in the DB
     */
    @RequestMapping("/list_items")
    List<Item> listItems() {
        return database.getAllItems();
    }

    /*
     * Extracts "quantity" objects with matching type "type" and name "name". Given the DB implementation used, we cannot
     * get a batch of items, instead we have to verify there are enough and then extract them one by one.
     * Maybe not the best implementation, but another one nonetheless.
     */
    @RequestMapping("/buy_object")
    List<Item> buyItem(@RequestParam("type") ItemType type, @RequestParam("name") String name,
                   @RequestParam("quantity") int quantity) throws NotEnoughElementsException {

        Item item = type.build(name, 0, 0);
        if(database.hasItem(item) < quantity){
            throw new NotEnoughElementsException("Pas assez d'articles.");
        }

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            try {
                items.add(database.getItemByName(item));
            } catch (NoSuchElementException e) {
                // Race case: two clients start extracting elements at the same time because the initial check passed,
                // but there are not enough items for both. Clients must then put back in those items and retry.
                LOGGER.warn("Warning: someone already took "+item.name+" backing up operation.");
                for(Item item1 : items) database.saveItem(item1);
                throw new NotEnoughElementsException("Quelqu'un a déjà pris des items! Veuillez réessayer.");
            }
        }
        return items;
    }

    //Get all the possible types of items the database supports
    @RequestMapping("/get_types")
    List<String> listTypes() {
        return ItemType.listTypes();
    }
}
