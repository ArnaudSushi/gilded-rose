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
    public Iterator<Item> iterator() {
        return database.values().iterator();
    }
}
