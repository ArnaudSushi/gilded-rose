package fr.esiea;

public class BackstagePass extends Item {

    public BackstagePass(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    /*
     * Method to update BackstagePass quality. 
     * The higher the sellIn is, the slower the quality increases
     */
    @Override
    public void updateQuality() {
        int qualityEvolution = ((10/sellIn) + 1);
        quality += qualityEvolution;
        
        if (sellIn < 0) {
            quality = 0;
        }
    }
}
