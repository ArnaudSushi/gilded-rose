package fr.esiea;

public class AgedBrie extends Item {
    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    /*
     * Method to update AgedBrie quality. 
     * The quality is less than or equal to 50
     */
    @Override
    public void updateQuality() {
    	if (quality < 50) {
            quality++;
        }

    	sellIn--;

        if(sellIn<0 && quality < 50) {
            quality++;
        }
    }
}

