package fr.esiea;

public class BackstagePass extends Item {

    public BackstagePass(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        /*
        quality++;
        if (sellIn <= 10) {
            quality++;
        }
        if (sellIn <= 5) {
            quality++;
        }
        if (sellIn < 0) {
            quality = 0;
        }*/
        int qualityEvolution = ((10/sellIn) + 1);
        quality += qualityEvolution;
    }
}
