package specification;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class AgedBrie extends TestUtility {

    public boolean isHigher(String itemName, String itemQuality) {
        return getItem(itemName).getQuality() > Integer.parseInt(itemQuality);
    }
}
