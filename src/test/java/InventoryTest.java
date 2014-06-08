import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;


public class InventoryTest {

    @Test
    public void inventory_starts_with_aged_brie() throws Exception {
        Inventory inventory = new Inventory();

        assertThat(inventory.getItems(), hasItem(itemNameMatcher(SpecialItems.AGED_BRIE)));
    }

    @Test
    public void inventory_starts_with_Sulfuras() throws Exception {
        Inventory inventory = new Inventory();

        assertThat(inventory.getItems(), hasItem(itemNameMatcher(SpecialItems.SULFURAS)));
    }

    @Test
    public void inventory_starts_with_Backstage_pass() throws Exception {
        Inventory inventory = new Inventory();

        assertThat(inventory.getItems(), hasItem(itemNameMatcher(SpecialItems.BACKSTAGE_PASSES)));
    }

    @Test
    public void inventory_starts_with_Conjured() throws Exception {
        Inventory inventory = new Inventory();

        assertThat(inventory.getItems(), hasItem(itemNameMatcher(SpecialItems.CONJURED)));
    }

    @Test
    public void inventory_starts_with_other_items() throws Exception {
        Inventory inventory = new Inventory();

        assertThat(inventory.getItems(), hasItem(itemOtherMatcher()));
    }


    private BaseMatcher<Item> itemNameMatcher(final String prefix) {
        return new BaseMatcher<Item>() {
            @Override
            public boolean matches(Object item) {
                if (item instanceof Item) {
                    Item i = (Item) item;
                    return i.getName().startsWith(prefix);
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(String.format("item with name starting with '%s'.", prefix));
            }
        };
    }

    private Matcher<Item> itemOtherMatcher() {
        return new BaseMatcher<Item>() {
            @Override
            public boolean matches(Object item) {
                if (item instanceof Item) {
                    Item i = (Item) item;
                    for(String prefix : SpecialItems.SPECIALS) {
                        if(!i.getName().startsWith(prefix)) {
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(String.format("item which not starts with any of '%s'.", SpecialItems.SPECIALS.toString()));
            }
        };

    }
}
