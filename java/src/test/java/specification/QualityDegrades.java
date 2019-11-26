package specification;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import se.crisp.edu.refactor.gildedrose.Item;

import static org.junit.Assert.assertEquals;

public class QualityDegrades {

    private World world;

    public QualityDegrades(World world) {
        this.world = world;
    }

    @Given("there is an item {string} with quality {string} and due sell in {string}")
    public void thereIsAnItemWithQualityAndDueSellIn(String itemName, String itemQuality, String itemSellIn) {
        world.setItem(new Item(itemName, Integer.parseInt(itemSellIn), Integer.parseInt(itemQuality)));
        world.getGildedRose().addItem(world.getItem());
    }

    @And("{string} days has passed")
    public void daysHasPassed(String days) {
        for (int n = 0; n < Integer.parseInt(days); n++) {
            world.getGildedRose().updateQuality();
        }
    }

    @Then("the quality will be {string}")
    public void theQualityWillBe(String itemQuality) {
        assertEquals(Integer.parseInt(itemQuality), world.getItem().quality);
    }
}
