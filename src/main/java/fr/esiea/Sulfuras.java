package fr.esiea;

public class Sulfuras extends Item{
    public Sulfuras(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    /*
     * Method to update Sulfuras quality. 
     * A Sulfuras item keeps the same quality over time.
     */
    @Override
    public void updateQuality() {
    	System.out.println("Quality updated");
    }
}
