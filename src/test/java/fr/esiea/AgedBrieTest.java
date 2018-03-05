package fr.esiea;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import persistence.items.HashMapPersistence;
import persistence.items.ItemPersistence;

public class AgedBrieTest {

    @Test
    public void UpdateQualityNegativeSellInLessthan50QualityAgedBrie(){
        Item agedBrie = new AgedBrie("Aged Brie", -1, 40);
        ItemPersistence items = new HashMapPersistence();
        items.saveItem(agedBrie);
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(agedBrie.quality).as("qualité de Aged Brie ").isEqualTo(42);
    }

    @Test
    public void UpdateQualityNegativeSellInMoreorEqualTo50QualityAgedBrie(){
        Item agedBrie = new AgedBrie("Aged Brie", -1, 50);
        ItemPersistence items = new HashMapPersistence();
        items.saveItem(agedBrie);
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(agedBrie.quality).as("qualité de Aged Brie ").isEqualTo(50);
    }

    @Test
    public void UpdateQualityAgedBrieQuality(){
        Item agedBrie = new AgedBrie("Aged Brie", 5, 10);
        ItemPersistence items = new HashMapPersistence();
        items.saveItem(agedBrie);
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(agedBrie.quality).as("qualité de Aged Brie").isEqualTo(11);
    }

}