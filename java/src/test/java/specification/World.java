package specification;

import se.crisp.edu.refactor.gildedrose.GildedRose;
import se.crisp.edu.refactor.gildedrose.Item;

@SuppressWarnings("WeakerAccess")
public class World {
    private final GildedRose gildedRose = new GildedRose();
    private Item item;

    public GildedRose getGildedRose() {
        return gildedRose;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
