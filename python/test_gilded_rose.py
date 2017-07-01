# -*- coding: utf-8 -*-
import unittest

from gilded_rose import Item, GildedRose

SOME_ITEM_NAME = "foo"


class GildedRoseTest(unittest.TestCase):
    def test_when_sell_by_date_has_passed_quality_degrades_twice_as_fast(self):
        self.assertEquals(True, False)

    def test_the_quality_is_never_negative(self):
        self.assertEquals(True, False)

    def test_aged_bries_increases_in_quality_as_it_gets_older(self):
        self.assertEquals(True, False)

    def test_quality_never_reaches_more_than_50(self):
        self.assertEquals(True, False)

    def test_sulfuras_legendary_item_never_has_to_be_sold(self):
        self.assertEquals(True, False)

    def test_backstage_passes_increases_in_quality(self):
        self.assertEquals(True, False)

    def test_backstage_passes_increases_by_2_in_quality_when_10_days_or_less(self):
        self.assertEquals(True, False)

    def test_backstage_passes_increases_by_3_in_quality_when_5_days_or_less(self):
        self.assertEquals(True, False)

    def test_backstage_passes_quality_drops_to_0_after_the_concert(self):
        self.assertEquals(True, False)

    # New requirement

    def test_conjured_degrades_twice_as_fast_as_normal_items(self):
        self.assertEquals(True, False)

    def test_foo(self):
        items = [Item(SOME_ITEM_NAME, 0, 0)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEquals(SOME_ITEM_NAME, items[0].name)


if __name__ == '__main__':
    unittest.main()
