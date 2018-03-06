package persistence.items;

import fr.esiea.Item;

import java.util.List;

//Database implementations to store Items must implement this interface and must be iterable
public interface ItemPersistence extends Iterable<Item>{

    int saveItem(Item item);
    Item getItemByName(Item item) throws NoSuchElementException;
    List<Item> getAllItems();
    int hasItem(Item item);
    void clear();

}
