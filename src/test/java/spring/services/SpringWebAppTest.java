package spring.services;

import fr.esiea.AgedBrie;
import fr.esiea.BackstagePass;
import fr.esiea.Item;
import fr.esiea.Sulfuras;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import persistence.items.NotEnoughElementsException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class SpringWebAppTest {

    @Before
    public void clearDB(){
        SpringWebApp.database.clear();
    }

    @Test
    public void createSulfuras() {
        SpringWebApp swa = new SpringWebApp();
        swa.createObject(ItemType.SULFURAS,"Sulfuras",2,5,1);
        Assertions.assertThat(SpringWebApp.database.hasItem(new Sulfuras("Sulfuras",0,5)))
                .as("Nombre de Sulfuras en DB").isEqualTo(1);
    }

    @Test
    public void createBackstage() {
        SpringWebApp swa = new SpringWebApp();
        swa.createObject(ItemType.BACKSTAGE,"Backstage pass ACDC",2,5,1);
        Assertions.assertThat(SpringWebApp.database.hasItem(new BackstagePass("Backstage pass ACDC",0,5)))
                .as("Nombre de Backstage pass en DB").isEqualTo(1);
    }

    @Test
    public void createBrie() {
        SpringWebApp swa = new SpringWebApp();
        swa.createObject(ItemType.AGED_BRIE,"AgedBrie President",2,5,1);
        Assertions.assertThat(SpringWebApp.database.hasItem(new AgedBrie("AgedBrie President",0,5)))
                .as("Nombre de AgedBrie President en DB").isEqualTo(1);
    }

    @Test
    public void create_generic_item() {
        SpringWebApp swa = new SpringWebApp();
        swa.createObject(ItemType.ITEM,"Quelque chose",2,5,1);
        Assertions.assertThat(SpringWebApp.database.hasItem(new Item("Quelque chose",0,5)))
                .as("Nombre de en DB").isEqualTo(1);
    }

    @Test
    public void listItems() {
        SpringWebApp swa = new SpringWebApp();
        swa.createObject(ItemType.SULFURAS,"Sulfuras",2,5,5);
        List<Item> itemList = swa.listItems();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(itemList).as("Liste d'éléments").hasOnlyElementsOfType(Sulfuras.class);
        softly.assertThat(itemList).as("Nombre d'éléments").hasSize(5);
        softly.assertThat(SpringWebApp.database.hasItem(new Sulfuras("Sulfuras",5,5)))
                .as("Nombre de Sulfuras en DB").isEqualTo(5);
        softly.assertAll();


    }

    @Test
    public void buy_item_with_enough_stock() throws NotEnoughElementsException{
        SpringWebApp swa = new SpringWebApp();
        List<Item> items;
        swa.createObject(ItemType.AGED_BRIE,"AgedBrie",2,5,5);
        items = swa.buyItem(ItemType.AGED_BRIE, "AgedBrie", 5);


        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(items).as("Eléments achetés").hasOnlyElementsOfType(AgedBrie.class);
        softly.assertThat(items).as("Nombre d'éléments").hasSize(5);
        softly.assertThat(SpringWebApp.database.hasItem(new Item("AgedBrie",5,5)))
                .as("Nombre de AgedBries").isEqualTo(0);
        softly.assertAll();
    }

    @Test
    public void buy_item_without_enough_stock(){
        SpringWebApp swa = new SpringWebApp();
        List<Item> items = new ArrayList<>();
        swa.createObject(ItemType.AGED_BRIE,"AgedBrie",2,5,5);

        SoftAssertions softly = new SoftAssertions();
        Throwable thrown = catchThrowable(() -> swa.buyItem(ItemType.AGED_BRIE, "AgedBrie", 6));

        softly.assertThat(thrown).hasMessageContaining("Pas assez d'articles.");
        softly.assertThat(items).as("Nombre d'éléments").hasSize(0);
        softly.assertThat(SpringWebApp.database.hasItem(new AgedBrie("AgedBrie",5,5)))
                .as("Nombre de AgedBries").isEqualTo(5);
        softly.assertAll();
    }

    @Test
    public void getlistOfAllTypes() {
        SpringWebApp swa = new SpringWebApp();
        List<String> types = swa.listTypes();
        Assertions.assertThat(types).as("Liste des types disponibles").
                contains("AGED_BRIE","BACKSTAGE","SULFURAS");
    }

    @Test
    public void testMain() {
        SpringWebApp.main(null);
    }


}