package com.gildedrose;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    private static final int SULFURAS_QUALITY = 80;

    @Test
    public void every_items_name_do_not_change_on_update() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    public void sulfuras_item_quality_does_not_change_on_update() {
        Item sulfuras = new Item(GildedRose.SULFURAS, 2, SULFURAS_QUALITY);
        getGildedRose().updateOneItemQuality(sulfuras);
        assertEquals(SULFURAS_QUALITY, sulfuras.quality);
    }

    @Test
    public void sulfuras_item_sell_in_does_not_change_on_update() {
        int initialSellIn = 2;
        Item sulfuras = new Item(GildedRose.SULFURAS, initialSellIn, SULFURAS_QUALITY);
        getGildedRose().updateOneItemQuality(sulfuras);
        assertEquals(initialSellIn, sulfuras.sellIn);
    }

    @Test
    public void sell_in_decrease_by_one_at_each_update_for_not_sulfuras_item() {
        int initialSellIn = 10;
        Item notSulfurasItem = new Item("not-sulfuras item", initialSellIn, 0);
        getGildedRose().updateOneItemQuality(notSulfurasItem);
        assertEquals(initialSellIn - 1, notSulfurasItem.sellIn);
    }

    @Test
    public void quality_decrease_by_one_at_each_update_until_sell_in_reaches_zero() {
        int initialQuality = 5;
        Item basicItem = new Item("basic item", 1, initialQuality);
        getGildedRose().updateOneItemQuality(basicItem);
        assertEquals(initialQuality - 1, basicItem.quality);
    }

    @Test
    public void quality_decrease_by_two_at_each_update_when_sell_in_has_reached_zero() {
        int initialQuality = 5;
        Item basicItem = new Item("basic item", -1, initialQuality);
        getGildedRose().updateOneItemQuality(basicItem);
        assertEquals(initialQuality - 2, basicItem.quality);
    }

    @Test
    public void quality_does_not_decrease_below_zero() {
        Item anyItem = new Item("any item", -1, GildedRose.MINIMUM_QUALITY);
        getGildedRose().updateOneItemQuality(anyItem);
        assertEquals(GildedRose.MINIMUM_QUALITY, anyItem.quality);
    }

    @Test
    public void aged_brie_quality_increase_by_one_on_update_before_sell_by_date() {
        int initialQuality = 5;
        Item agedBrie = new Item(GildedRose.AGED_BRIE, 5, initialQuality);
        getGildedRose().updateOneItemQuality(agedBrie);
        assertEquals(initialQuality + 1, agedBrie.quality);
    }

    private GildedRose getGildedRose() {
        return new GildedRose(null);
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

}
