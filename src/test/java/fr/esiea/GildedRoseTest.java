package fr.esiea;

import org.assertj.core.api.Assertions;
import org.junit.Test;


public class GildedRoseTest {

    @Test
    public void decayOfObjectQualityByOne() {
        Item item = new Item("testObject", 5, 10);
        Item[] items = new Item[]{item};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(item.quality).as("qualité du produit testé").isEqualTo(9);
    }
}