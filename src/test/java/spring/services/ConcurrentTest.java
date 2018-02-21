package spring.services;

import fr.esiea.Item;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import persistence.items.NotEnoughElementsException;

import java.util.List;

public class ConcurrentTest {

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

    //TODO
    @Test
    public void concurrent_buy_of_same_items_not_enough_stock(){
        SpringWebApp swa = new SpringWebApp();
        swa.createObject(ItemType.AGED_BRIE,"Aged Brie", 10,10,10);

        Thread c1 = new Thread(() -> {
            try {
                swa.buyItem(ItemType.AGED_BRIE,"Aged Brie",7);
            } catch (NotEnoughElementsException e) {
                e.printStackTrace();
            }
        });

        Thread c2 = new Thread(() -> {
            try {
                swa.buyItem(ItemType.AGED_BRIE,"Aged Brie",7);
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

    }
}
