package fr.esiea;

import org.assertj.core.api.Assertions;
import org.junit.Test;


public class GildedRoseTest {

    @Test
    public void creationTest() {
        Item pawnItem = new Item("basicItem", 10, 10);
        Item[] items = new Item[]{pawnItem};
        GildedRose gildedRose = new GildedRose(items);
        Assertions.assertThat(gildedRose.items).as("Les objets vandable").isEqualTo(items);
    }

    @Test
    public void decayOfDefaultObjectQualityByOne() {
        Item defaultItem = new Item("testObject", 5, 10);
        Item[] items = new Item[]{defaultItem};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(defaultItem.quality).as("qualité du produit testé").isEqualTo(9);
    }
}