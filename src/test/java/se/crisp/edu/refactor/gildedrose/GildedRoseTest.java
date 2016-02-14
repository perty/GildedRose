package se.crisp.edu.refactor.gildedrose;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GildedRoseTest {

    public static final String SOME_ITEM = "some old item";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";

    @Test
    public void quality_degrades() {
        GildedRose gildedRose = new GildedRose();
        ArrayList<Item> items = gildedRose.getInventory().getItems();
        int initialQuality = items.get(0).getQuality();

        gildedRose.updateQuality();

        assertTrue(items.get(0).getQuality() < initialQuality);
    }

    @Test
    public void when_sell_by_date_has_passed_quality_degrades_twice_as_fast() throws Exception {
        int quality = 10;
        GildedRose gildedRose = addItemWithQuality(quality);

        gildedRose.updateQuality();

        Item item = findItemByName(gildedRose, SOME_ITEM);
        assertThat(item.getQuality(), is(quality - 2));
    }

    @Test
    public void the_quality_is_never_negative() throws Exception {
        int quality = 0;
        GildedRose gildedRose = addItemWithQuality(quality);

        gildedRose.updateQuality();

        Item item = findItemByName(gildedRose, SOME_ITEM);
        assertThat(item.getQuality(), is(0));

    }

    @Test
    public void aged_bries_increases_in_quality_as_it_gets_older() throws Exception {
        GildedRose gildedRose = new GildedRose();
        int agedBrie1 = findItemByName(gildedRose, AGED_BRIE).getQuality();

        gildedRose.updateQuality();

        Item agedBrie2 = findItemByName(gildedRose, AGED_BRIE);

        assertTrue(agedBrie1 < agedBrie2.getQuality());
    }

    @Test
    public void quality_never_reaches_more_than_50() throws Exception {
        GildedRose gildedRose = new GildedRose();
        Item agedBrie = findItemByName(gildedRose, AGED_BRIE);

        while(agedBrie.getQuality() < 50){
            gildedRose.updateQuality();
        }
        gildedRose.updateQuality();

        assertTrue(findItemByName(gildedRose, AGED_BRIE).getQuality() == 50);
    }

    @Test
    public void sulfuras_legendary_item_never_has_to_be_sold() throws Exception {
        GildedRose gildedRose = new GildedRose();
        int sulfuras = findItemByName(gildedRose, SULFURAS_HAND_OF_RAGNAROS).getSellIn();

        gildedRose.updateQuality();

        assertEquals(sulfuras, findItemByName(gildedRose, SULFURAS_HAND_OF_RAGNAROS).getSellIn());
    }

    @Test
    public void backstage_passes_increases_in_quality() throws Exception {
        GildedRose gildedRose = new GildedRose();
        int quality = findItemByName(gildedRose, BACKSTAGE_PASS).getQuality();

        gildedRose.updateQuality();

        assertTrue(quality < findItemByName(gildedRose, BACKSTAGE_PASS).getQuality());
    }

    @Test
    public void backstage_passes_increases_by_2_in_quality_when_10_days_or_less() throws Exception {
        GildedRose gildedRose = new GildedRose();
        Item backstage = findItemByName(gildedRose, BACKSTAGE_PASS);

        updateUntilDaysRemaining(gildedRose, backstage, 10);

        int quality = backstage.getQuality();
        gildedRose.updateQuality();

        assertEquals(quality + 2, backstage.getQuality());
    }

    @Test
    public void backstage_passes_increases_by_3_in_quality_when_5_days_or_less() throws Exception {
        GildedRose gildedRose = new GildedRose();
        Item backstage = findItemByName(gildedRose, BACKSTAGE_PASS);

        updateUntilDaysRemaining(gildedRose, backstage, 5);

        int quality = backstage.getQuality();
        gildedRose.updateQuality();

        assertEquals(quality + 3, backstage.getQuality());
    }

    @Test
    public void backstage_passes_quality_drops_to_0_after_the_concert() throws Exception {
        GildedRose gildedRose = new GildedRose();
        Item backstage = findItemByName(gildedRose, BACKSTAGE_PASS);

        updateUntilDaysRemaining(gildedRose, backstage, -1);

        assertEquals(0, backstage.getQuality());
    }

    // New requirement

    @Test
    public void conjured_degrades_twice_as_fast_as_normal_items() throws Exception {


    }

    private GildedRose addItemWithQuality(int quality) {
        Item someOldItem = new Item(SOME_ITEM, 0, quality);
        GildedRose gildedRose = new GildedRose();
        gildedRose.getInventory().addItem(someOldItem);
        return gildedRose;
    }

    private Item findItemByName(GildedRose gildedRose, String name) {
        return gildedRose.getInventory().getItems()
                .stream()
                .filter(t -> t.getName().equals(name))
                .findFirst()
                .get();
    }

    private void updateUntilDaysRemaining(GildedRose gildedRose, Item item, int days) {
        for(int n = item.getSellIn(); n > days; n = item.getSellIn()){
            gildedRose.updateQuality();
        }
    }

}