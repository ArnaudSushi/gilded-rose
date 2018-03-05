package fr.esiea;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import persistence.items.HashMapPersistence;
import persistence.items.ItemPersistence;

public class GildedRoseTest {

    @Test
    public void notEmptyCreationTest() {
        Item pawntem = new Item("basic", 10, 10);

        ItemPersistence items = new HashMapPersistence();
        items.saveItem(pawntem);

        GildedRose gildedRose = new GildedRose(items);
        Assertions.assertThat(gildedRose.getItems()).as("Les objets vandables").isEqualTo(items.getAllItems());
    }

    @Test
    public void emptyCreationTest() {
        ItemPersistence items = new HashMapPersistence();

        GildedRose gildedRose = new GildedRose();
        Assertions.assertThat(gildedRose.getItems()).as("La taverne").isEqualTo(items.getAllItems());
    }

    @Test
    public void addItemTest() {
        Item pawnItem = new Item("basicItem", 10, 10);

        ItemPersistence items = new HashMapPersistence();
        items.saveItem(pawnItem);

        GildedRose gildedRose = new GildedRose();
        gildedRose.addAnItem(pawnItem);
        Assertions.assertThat(gildedRose.getItems()).as("Les objets vendables").isEqualTo(items.getAllItems());
    }
}