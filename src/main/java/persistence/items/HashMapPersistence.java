package persistence.items;


import fr.esiea.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * Database implementation based on a ConcurrentHashMap and an AtomicInteger to allow multiple clients
 * to insert and retrieve items at the same time. Each element stored represents an item, identified by a sequentially
 * generated integer. Multiple identical items are stored as individual entries in the DB, this means, inserting 3
 * "Item 1" items results in 3 entries created in the DB, no "quantity" value is stored in either the DB or the item
 * itself.
 */
public class HashMapPersistence implements ItemPersistence {

    private static final Logger LOGGER = LoggerFactory.getLogger(HashMapPersistence.class);

    private final ConcurrentMap<Integer, Item> database = new ConcurrentHashMap<>();
    private final AtomicInteger sequenceGenerator = new AtomicInteger();

    @Override
    public int saveItem(Item item) {
        int id = sequenceGenerator.incrementAndGet();
        database.put(id, item);
        LOGGER.info(item.name + " created with ID: " + id);
        return id;
    }

    /*
     * Gets the item passed as parameter from the DB. Throws an exception if the element does not exist in the database.
     * We have to iterate over the keySet instead of the values directly to obtain the actual item in the DB,
     * otherwise, we wouldn't have the exact attributes of the item in the DB but the random ones used for the search.
     */
    @Override
    public Item getItemByName(Item item) throws NoSuchElementException {
        if(!database.containsValue(item)){
            throw new NoSuchElementException("Element not found.");
        }

        Item tempItem = null;
        for(int i : database.keySet()){
            tempItem = database.get(i);
            if(tempItem.equals(item)){
                tempItem =  database.remove(i);
                LOGGER.info(item.name + " found and removed with id: " + i);
                break;
            }
        }
        return tempItem;
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(database.values());
    }

    public int hasItem(Item item){
        int count = 0;
        for(int i : database.keySet()){
            if(database.get(i).equals(item)){
                count++;
            }
        }
        LOGGER.info("Found " + count + " " + item.name);
        return count;
    }

    @Override
    public void clear() {
        database.clear();
    }

    //Iterates over the values of the DB only, no keys involved
    @Override
    public Iterator<Item> iterator() {
        return database.values().iterator();
    }
}
