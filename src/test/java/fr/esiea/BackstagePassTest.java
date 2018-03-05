package fr.esiea;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import persistence.items.HashMapPersistence;
import persistence.items.ItemPersistence;

public class BackstagePassTest {

    @Test
    public void updateQualityNegativeSellInBackstage(){
        Item backstagePass = new BackstagePass("Backstage passes to a TAFKAL80ETC concert", -1, 10);
        ItemPersistence items = new HashMapPersistence();
        items.saveItem(backstagePass);
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(backstagePass.quality).as("qualité de Backstage ").isEqualTo(0);
    }

    @Test
    public void updateQualityBackstageQuality(){
        Item backstagePass = new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 5, 10);
        ItemPersistence items = new HashMapPersistence();
        items.saveItem(backstagePass);
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(backstagePass.quality).as("qualité de Backstage ").isEqualTo(13);
    }

}