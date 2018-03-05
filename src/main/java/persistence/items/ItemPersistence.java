package persistence.items;

import fr.esiea.Item;

import java.util.Iterator;
import java.util.List;

public interface ItemPersistence extends Iterable<Item>{

    int saveItem(Item item);
    Item getItemByName(Item item) throws NoSuchElementException;
    List<Item> getAllItems();
    int hasItem(Item item);

}
