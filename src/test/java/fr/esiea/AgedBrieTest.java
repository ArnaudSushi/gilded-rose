package fr.esiea;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class AgedBrieTest {

    @Test
    public void UpdateQualityNegativeSellInLessthan50QualityAgedBrie(){
        Item agedBrie = new AgedBrie("Aged Brie", -1, 40);
        Item[] items = new Item[]{agedBrie};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(agedBrie.quality).as("qualité de Aged Brie ").isEqualTo(42);
    }

    @Test
    public void UpdateQualityNegativeSellInMoreorEqualTo50QualityAgedBrie(){
        Item agedBrie = new AgedBrie("Aged Brie", -1, 50);
        Item[] items = new Item[]{agedBrie};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(agedBrie.quality).as("qualité de Aged Brie ").isEqualTo(50);
    }

    @Test
    public void UpdateQualityAgedBrieQuality(){
        Item agedBrie = new AgedBrie("Aged Brie", 5, 10);
        Item[] items = new Item[]{agedBrie};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(agedBrie.quality).as("qualité de Aged Brie").isEqualTo(11);
    }

}