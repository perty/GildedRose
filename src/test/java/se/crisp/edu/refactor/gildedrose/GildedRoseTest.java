package se.crisp.edu.refactor.gildedrose;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GildedRoseTest {

    public static final String SOME_ITEM = "some old item";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    public static final int SELL_IN = 20;
    public static final int QUALITY = 10;

    @Test
    public void quality_degrades() {
        int initialQuality = 1;
        GildedRose gildedRose = startWithItem(SOME_ITEM, SELL_IN, initialQuality);

        gildedRose.updateQuality();

        assertTrue(findItemByName(gildedRose, SOME_ITEM).getQuality() < initialQuality);
    }

    @Test
    public void when_sell_by_date_has_passed_quality_degrades_twice_as_fast() throws Exception {
        int quality = 10;
        GildedRose gildedRose = startWithItem(SOME_ITEM, 0, quality);

        gildedRose.updateQuality();

        Item item = findItemByName(gildedRose, SOME_ITEM);
        assertThat(item.getQuality(), is(quality - 2));
    }

    @Test
    public void the_quality_is_never_negative() throws Exception {
        int quality = 0;
        GildedRose gildedRose = startWithItem(SOME_ITEM, SELL_IN, quality);

        gildedRose.updateQuality();

        Item item = findItemByName(gildedRose, SOME_ITEM);
        assertThat(item.getQuality(), is(0));

    }

    @Test
    public void aged_bries_increases_in_quality_as_it_gets_older() throws Exception {
        int initialQuality = 5;
        GildedRose gildedRose = startWithItem(AGED_BRIE, SELL_IN, initialQuality);

        gildedRose.updateQuality();

        int updatedQuality = findItemByName(gildedRose, AGED_BRIE).getQuality();

        assertTrue(initialQuality < updatedQuality);
    }

    @Test
    public void quality_never_reaches_more_than_50() throws Exception {
        GildedRose gildedRose = startWithItem(AGED_BRIE, SELL_IN, 50);

        gildedRose.updateQuality();

        assertTrue(findItemByName(gildedRose, AGED_BRIE).getQuality() == 50);
    }

    @Test
    public void sulfuras_legendary_item_never_has_to_be_sold() throws Exception {
        GildedRose gildedRose = startWithItem(SULFURAS_HAND_OF_RAGNAROS, 0, QUALITY);

        gildedRose.updateQuality();

        Item sulfura = findItemByName(gildedRose, SULFURAS_HAND_OF_RAGNAROS);
        assertEquals(0, sulfura.getSellIn());
    }

    @Test
    public void backstage_passes_increases_in_quality() throws Exception {
        int initialQuality = 10;
        GildedRose gildedRose = startWithItem(BACKSTAGE_PASS, SELL_IN, initialQuality);

        gildedRose.updateQuality();

        assertEquals(initialQuality + 1, findItemByName(gildedRose, BACKSTAGE_PASS).getQuality());
    }

    @Test
    public void backstage_passes_increases_by_2_in_quality_when_10_days_or_less() throws Exception {
        GildedRose gildedRose = startWithItem(BACKSTAGE_PASS, 10, QUALITY);

        gildedRose.updateQuality();

        Item backstage = findItemByName(gildedRose, BACKSTAGE_PASS);
        assertEquals(QUALITY + 2, backstage.getQuality());
    }

    @Test
    public void backstage_passes_increases_by_3_in_quality_when_5_days_or_less() throws Exception {
        GildedRose gildedRose = startWithItem(BACKSTAGE_PASS, 5, QUALITY);

        gildedRose.updateQuality();

        Item backstage = findItemByName(gildedRose, BACKSTAGE_PASS);
        assertEquals(QUALITY + 3, backstage.getQuality());
    }

    @Test
    public void backstage_passes_quality_drops_to_0_after_the_concert() throws Exception {
        GildedRose gildedRose = startWithItem(BACKSTAGE_PASS, -1, QUALITY);

        gildedRose.updateQuality();

        Item backstage = findItemByName(gildedRose, BACKSTAGE_PASS);
        assertEquals(0, backstage.getQuality());
    }

    // New requirement

    @Test
    public void conjured_degrades_twice_as_fast_as_normal_items() throws Exception {


    }

    private GildedRose setUpGildedRose() {
        return new GildedRose(new TestInventory());
    }

    private GildedRose startWithItem(String name, int sellIn, int quality) {
        return startWithItem(new Item(name, sellIn, quality));
    }

    private GildedRose startWithItem(Item item) {
        GildedRose gildedRose = setUpGildedRose();
        gildedRose.getInventory().addItem(item);
        return gildedRose;
    }

    private Item findItemByName(GildedRose gildedRose, String name) {
        return gildedRose.getInventory().getItems()
                .stream()
                .filter(t -> t.getName().equals(name))
                .findFirst()
                .get();
    }

}