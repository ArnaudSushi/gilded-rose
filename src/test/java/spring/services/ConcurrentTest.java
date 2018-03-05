package spring.services;

import fr.esiea.Item;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import persistence.items.NotEnoughElementsException;

import java.util.List;


public class ConcurrentTest {

    @Before
    public void clearDB(){
        SpringWebApp.database.clear();
    }

    @Test
    public void concurrent_buy_of_same_items_enough_stock(){
        SpringWebApp swa = new SpringWebApp();
        swa.createObject(ItemType.AGED_BRIE,"Aged Brie", 10,10,10);

        Thread c1 = new Thread(() -> {
            try {
                swa.buyItem(ItemType.AGED_BRIE,"Aged Brie",5);
            } catch (NotEnoughElementsException e) {
                e.printStackTrace();
            }
        });

        Thread c2 = new Thread(() -> {
            try {
                swa.buyItem(ItemType.AGED_BRIE,"Aged Brie",5);
            } catch (NotEnoughElementsException e) {
                e.printStackTrace();
            }
        });
        c1.start();
        c2.start();

        try {
            c1.join();
            c2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertThat(swa.listItems()).as("Elements").hasSize(0);
    }

    //When two clients start extracting items at the same time but there are not enough for both of them
    //they should put them back in
    @Test
    public void concurrent_buy_of_same_items_not_enough_stock(){
        SpringWebApp swa = new SpringWebApp();
        swa.createObject(ItemType.AGED_BRIE,"Aged Brie", 10,10,20);

        Thread c1 = new Thread(() -> {
            try {
                swa.buyItem(ItemType.AGED_BRIE,"Aged Brie",17);
            } catch (NotEnoughElementsException e) {
                e.printStackTrace();
            }
        });

        Thread c2 = new Thread(() -> {
            try {
                swa.buyItem(ItemType.AGED_BRIE,"Aged Brie",17);
            } catch (NotEnoughElementsException e) {
                e.printStackTrace();
            }
        });
        c1.start();
        c2.start();

        try {
            c1.join();
            c2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Item> items = swa.listItems();
        System.out.println(items.size());
        boolean isGoodSize = items.size() == 3 || items.size() == 20;
        Assertions.assertThat(isGoodSize).as("La longueur des items est correct").isTrue();

    }

}
