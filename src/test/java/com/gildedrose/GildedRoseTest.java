package com.gildedrose;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    @Test
    public void item_name_does_not_change_on_update() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    public void sell_in_decrease_by_one_at_each_update_for_one_item() {
        int initialSellIn = 10;
        Item basicItem = new Item("basic item", initialSellIn, 0);
        FakeGildedRose gildedRose = new FakeGildedRose(null);
        gildedRose.updateOneItemQuality(basicItem);
        assertEquals(initialSellIn - 1, basicItem.sellIn);
    }


    @Test
    @Ignore
    public void quality_of_conjured_items_decrease_by_two_each_day() {
        int initialQuality = 6;
        Item conjuredManaCake = new Item("Conjured Mana Cake",3 ,initialQuality);
        GildedRose gildedRose = new GildedRose(new Item[]{conjuredManaCake});
        gildedRose.updateQuality();

        assertEquals(initialQuality - 2, conjuredManaCake.quality);
    }

    private class FakeGildedRose extends GildedRose {

        FakeGildedRose(Item[] items) {
            super(items);
        }
    }
}
