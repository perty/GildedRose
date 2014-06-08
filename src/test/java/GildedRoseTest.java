import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GildedRoseTest {

    static final String SOME_NAME = "test item";
    static final int LONG_TIME_LEFT = 20;

    @Test
    public void has_inventory_with_items()  {
        GildedRose gildedRose = new GildedRose();

        assertTrue(gildedRose.getItems().size() > 0);
    }

    @Test
    public void update_quality_other_item_then_decrease_quality_by_1()  {
        GildedRose gildedRose = new GildedRose();
        Item otherItem = getItemThatIsOfTypeOther(gildedRose.getItems());
        int initialQuality = otherItem.getQuality();

        gildedRose.updateQuality();

        assertThat(otherItem.getQuality(), CoreMatchers.equalTo(initialQuality - 1));
    }

    @Test
    public void update_quality_then_decrease_date_by_1()  {
        GildedRose gildedRose = new GildedRose();
        Item someItem = getItemWithDaysLeft(gildedRose);
        int initialSellIn = someItem.getSellIn();

        gildedRose.updateQuality();

        assertThat(someItem.getSellIn(), CoreMatchers.equalTo(initialSellIn - 1));
    }

    @Test
    public void when_sell_date_passed_decrease_quality_by_2()  {
        GildedRose gildedRose = new GildedRose();
        int initialQuality = 10;
        Item item = new Item(SOME_NAME, 0, initialQuality);
        gildedRose.addItem(item);

        gildedRose.updateQuality();

        assertThat(item.getQuality(), CoreMatchers.equalTo(initialQuality - 2));
    }

    @Test
    public void never_decrease_quality_below_zero()  {
        GildedRose gildedRose = new GildedRose();
        Item item = new Item(SOME_NAME, 0, 0);
        gildedRose.addItem(item);

        gildedRose.updateQuality();

        assertThat(item.getQuality(), CoreMatchers.equalTo(0));
    }

    @Test
    public void update_quality_of_Aged_Brie_item_then_increase_by_1()  {
        GildedRose gildedRose = new GildedRose();
        Item otherItem = getItemThatIsOfType(SpecialItems.AGED_BRIE, gildedRose.getItems());
        int initialQuality = otherItem.getQuality();

        gildedRose.updateQuality();

        assertThat(otherItem.getQuality(), CoreMatchers.equalTo(initialQuality + 1));
    }

    @Test
    public void quality_is_never_above_50() throws Exception {
        GildedRose gildedRose = new GildedRose();
        Item item = new Item(SpecialItems.AGED_BRIE, 10, 49);
        gildedRose.addItem(item);

        gildedRose.updateQuality();
        gildedRose.updateQuality();

        assertThat(item.getQuality(), CoreMatchers.equalTo(50));
    }

    @Test
    public void Sulfuras_never_decrease_in_quality() throws Exception {
        GildedRose gildedRose = new GildedRose();
        Item sulfurasItem = getItemThatIsOfType(SpecialItems.SULFURAS, gildedRose.getItems());
        int initialQuality = sulfurasItem.getQuality();

        gildedRose.updateQuality();

        assertThat(sulfurasItem.getQuality(), CoreMatchers.equalTo(initialQuality));
    }

    @Test
    public void Back_stage_pass_increase_in_quality_when_long_time_left() throws Exception {
        GildedRose gildedRose = new GildedRose();
        int initialQuality = 10;
        Item item = getFixedBackStagePass(gildedRose, initialQuality, LONG_TIME_LEFT);

        gildedRose.updateQuality();

        assertThat(item.getQuality(), CoreMatchers.equalTo(initialQuality + 1));
    }


    @Test
    public void Back_stage_pass_increase_by_2_in_quality_when_ten_days_left() throws Exception {
        GildedRose gildedRose = new GildedRose();
        int initialQuality = 12;
        Item item = getFixedBackStagePass(gildedRose, initialQuality, 10);


        gildedRose.updateQuality();

        assertThat(item.getQuality(), CoreMatchers.equalTo(initialQuality + 2));

    }

    @Test
    public void Back_stage_pass_increase_by_3_in_quality_when_five_days_left() throws Exception {
        GildedRose gildedRose = new GildedRose();
        int initialQuality = 12;
        Item item = getFixedBackStagePass(gildedRose, initialQuality, 5);

        gildedRose.updateQuality();

        assertThat(item.getQuality(), CoreMatchers.equalTo(initialQuality + 3));
    }

    @Test
    public void Back_stage_pass_drops_to_zero_in_quality_when_no_days_left() throws Exception {
        GildedRose gildedRose = new GildedRose();
        int initialQuality = 12;
        Item item = getFixedBackStagePass(gildedRose, initialQuality, 0);

        gildedRose.updateQuality();

        assertThat(item.getQuality(), CoreMatchers.equalTo(0));
    }

    private Item getFixedBackStagePass(GildedRose gildedRose, int initialQuality, int sellIn) {
        Item item = getItemThatIsOfType(SpecialItems.BACKSTAGE_PASSES, gildedRose.getItems());
        item.setQuality(initialQuality);
        item.setSellIn(sellIn);
        return item;
    }

    private Item getItemWithDaysLeft(GildedRose gildedRose) {
        return gildedRose.getItems().get(0);
    }

    private Item getItemThatIsOfTypeOther(List<Item> items) {

        for (Item result : items) {
            boolean failed = false;
            for (String prefix : SpecialItems.SPECIALS) {
                if (result.getName().startsWith(prefix)) {
                    failed = true;
                }
            }
            if(! failed) {
                return result;
            }
        }
        return null;
    }

    private Item getItemThatIsOfType(String typePrefix, List<Item> items) {
        for (Item result : items) {
            if (result.getName().startsWith(typePrefix)) {
                return result;
            }
        }
        return null;
    }
}