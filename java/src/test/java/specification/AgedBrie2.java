package specification;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class AgedBrie2 extends TestUtility {

    public boolean isHigher(String itemName, String itemQuality) {
        return getItem(itemName).getQuality() > Integer.parseInt(itemQuality);
    }

    public int add(String x, String y) {
        return Integer.parseInt(x) + Integer.parseInt(y);
    }

    public String getQuality(String itemName) {
        return getItem(itemName).getQuality() + "";
    }
}
