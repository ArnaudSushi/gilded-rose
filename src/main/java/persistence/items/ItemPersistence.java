package persistence.items;

import fr.esiea.Item;

import java.util.List;

public interface ItemPersistence {

    int saveItem(Item item);
    Item getItemByName(Item item) throws NoSuchElementException;
    List<Item> getAllItems();
    boolean hasItem(Item item);

}
