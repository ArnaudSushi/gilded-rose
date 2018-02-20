package persistence.items;


import fr.esiea.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
        LOGGER.info(item + " created with ID: " + id);
        return id;
    }

    @Override
    public Item getItemByName(Item item) throws NoSuchElementException {
        if(!database.containsValue(item)){
            throw new NoSuchElementException("Element not found.");
        }

        Item tempItem;
        for(int i : database.keySet()){
            tempItem = database.get(i);
            if(tempItem.equals(item)){
                return database.remove(i);
            }
        }
        return null;
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(database.values());
    }

    public boolean hasItem(Item item){
       return database.containsValue(item);
    }
}
