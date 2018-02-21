package persistence.items;

import fr.esiea.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import spring.services.ItemType;

import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.Assert.*;

public class HashMapPersistenceTest {

    @Test
    public void save_sulfuras() {
        HashMapPersistence db = new HashMapPersistence();
        Item item = new Sulfuras("Sulfuras, Hand of Ragnaros", 5, 9);
        db.saveItem(item);
        Assertions.assertThat(db.hasItem(item)).as("Nombre de Sulfuras, Hand of Ragnaros dans la DB").isEqualTo(1);
    }

    @Test
    public void save_brie() {
        HashMapPersistence db = new HashMapPersistence();
        Item item = new AgedBrie("Aged Brie", 5, 9);
        db.saveItem(item);
        Assertions.assertThat(db.hasItem(item)).as("Nombre de Aged Brie dans la DB").isEqualTo(1);
    }
    @Test
    public void save_backstage() {
        HashMapPersistence db = new HashMapPersistence();
        Item item = new BackstagePass("Backstage pass", 5, 9);
        db.saveItem(item);
        Assertions.assertThat(db.hasItem(item)).as("Nombre de Backstage pass dans la DB").isEqualTo(1);
    }

    @Test
    public void save_item() {
        HashMapPersistence db = new HashMapPersistence();
        Item item = new Item("Quelque chose", 5, 9);
        db.saveItem(item);
        Assertions.assertThat(db.hasItem(item)).as("Nombre de Quelque chose dans la DB").isEqualTo(1);
    }


    @Test
    public void getItemByName() throws NoSuchElementException{
        HashMapPersistence db = new HashMapPersistence();
        Item item = new Sulfuras("Sulfuras, Hand of Ragnaros", 5, 9);
        db.saveItem(item);
        db.getItemByName(item);
        Assertions.assertThat(db.hasItem(item)).as("Nombre de Sulfuras, Hand of Ragnaros dans la DB").isEqualTo(0);
    }

    @Test
    public void getItemWrongNameSameType() {
        HashMapPersistence db = new HashMapPersistence();
        Item item = new Sulfuras("Sulfuras, Hand of Ragnaros", 5, 9);
        Item wrongItem = new Sulfuras("Eye of Sulfuras", 5 , 9);
        db.saveItem(item);

        Throwable thrown = catchThrowable(() -> {
            db.getItemByName(wrongItem);
        });

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(thrown).hasMessageContaining("Element not found.");
        softly.assertThat(db.hasItem(item)).as("Nombre de Sulfuras, Hand of Ragnaros dans la DB").isEqualTo(1);
        softly.assertAll();
    }

}