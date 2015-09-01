package se.crisp.edu.refactor.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GildedRoseTest {

    @Test
    public void test() {
        GildedRose gildedRose = new GildedRose();
        gildedRose.updateQuality();
        assertTrue(true);
    }

}