package se.crisp.edu.refactor.gildedrose;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GildedRoseTest {

    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    @Test
    public void test() {
        GildedRose gildedRose = new GildedRose();
        gildedRose.updateQuality();
        assertTrue(true);
    }

    @Test
    public void when_sell_by_date_has_passed_quality_degrades_twice_as_fast() throws Exception {
        Inventory inventory = new Inventory();
        Item testItem = new Item("testItem", 1, 10);
        inventory.addItem(testItem);

        GildedRose gildedRose = new GildedRose(inventory);

        gildedRose.updateQuality();

        assertThat(testItem.getQuality(), is(7));
    }

    @Test
    public void the_quality_is_never_negative() throws Exception {
        Inventory inventory = new Inventory();
        Item testItem = new Item("testItem", 1, 10);
        inventory.addItem(testItem);

        GildedRose gildedRose = new GildedRose(inventory);

        for (int n = 0; n < 10; n++) {
            gildedRose.updateQuality();
        }

        assertThat(testItem.getQuality(), is(0));
    }

    @Test
    public void aged_bries_increases_in_quality_as_it_gets_older() throws Exception {
        Inventory inventory = new Inventory();
        Item testItem = new Item(AGED_BRIE, 1, 10);
        inventory.addItem(testItem);

        GildedRose gildedRose = new GildedRose(inventory);

        gildedRose.updateQuality();

        assertThat(testItem.getQuality(), is(13));
    }

    @Test
    public void quality_never_reaches_more_than_50() throws Exception {
        Inventory inventory = new Inventory();
        Item testItem = new Item(AGED_BRIE, 1, 50);
        inventory.addItem(testItem);

        GildedRose gildedRose = new GildedRose(inventory);

        gildedRose.updateQuality();

        assertThat(testItem.getQuality(), is(50));
    }

    @Test
    public void sulfuras_legendary_item_never_has_to_be_sold() throws Exception {
        Inventory inventory = new Inventory();
        Item testItem = new Item(SULFURAS, 1, 10);
        inventory.addItem(testItem);

        GildedRose gildedRose = new GildedRose(inventory);

        gildedRose.updateQuality();

        assertThat(testItem.getQuality(), is(10));
    }

    @Test
    public void backstage_passes_increases_in_quality() throws Exception {
        Inventory inventory = new Inventory();
        Item testItem = new Item(BACKSTAGE_PASSES, 20, 10);
        inventory.addItem(testItem);

        GildedRose gildedRose = new GildedRose(inventory);

        gildedRose.updateQuality();

        assertThat(testItem.getQuality(), is(12));
    }

    @Test
    public void backstage_passes_increases_by_2_in_quality_when_10_days_or_less() throws Exception {
        Inventory inventory = new Inventory();
        Item testItem = new Item(BACKSTAGE_PASSES, 9, 10);
        inventory.addItem(testItem);

        GildedRose gildedRose = new GildedRose(inventory);

        gildedRose.updateQuality();

        assertThat(testItem.getQuality(), is(14));
    }

    @Test
    public void backstage_passes_increases_by_3_in_quality_when_5_days_or_less() throws Exception {
        Inventory inventory = new Inventory();
        Item testItem = new Item(BACKSTAGE_PASSES, 5, 10);
        inventory.addItem(testItem);

        GildedRose gildedRose = new GildedRose(inventory);

        gildedRose.updateQuality();

        assertThat(testItem.getQuality(), is(16));

    }

    @Test
    public void backstage_passes_quality_drops_to_0_after_the_concert() throws Exception {
        Inventory inventory = new Inventory();
        Item testItem = new Item(BACKSTAGE_PASSES, 1, 10);
        inventory.addItem(testItem);

        GildedRose gildedRose = new GildedRose(inventory);

        gildedRose.updateQuality();

        assertThat(testItem.getQuality(), is(0));

    }

    // New requirement

    @Test
    public void conjured_degrades_twice_as_fast_as_normal_items() throws Exception {


    }
}