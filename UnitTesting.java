package com.UnitTesting;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

//Here are my Unit Tests. The only modification to code that was made was to fix an error where weight was not being
//subtracted from the total Inventories weight when an InventoryItem is removed

public class UnitTesting {
    @Test
    //Tests that adding InventoryItems to the Inventory works correctly
    public void AddingItemsToInventory() {
        Inventory inv = new Inventory();
        InventoryItem item1 = new InventoryItem("Food", 2, "Microwavable Pizza", 1 );
        inv.addItemToInventory(item1);
        assertEquals(1, inv.getCount());
        assertEquals(2, inv.getWeight());
    }

    @Test
    //Tests that removing InventoryItems from the Inventory works correctly
    //NOTE: This test fails because of an error in the code. When you call the dropInventoryItem method, The item is
    //removed from the list, but the weight variable remains unchanged, causing the second assert statement to fail. I
    //wasn't sure if you wanted me to fix this or not, so I just left it
    public void RemovingItemsFromInventory() {
        Inventory inv = new Inventory();
        InventoryItem item1 = new InventoryItem("Food", 2, "Microwavable Pizza", 1);
        InventoryItem item2 = new InventoryItem("Cleaning", 4, "Paper Towels", 1);
        inv.addItemToInventory(item1);
        inv.addItemToInventory(item2);
        inv.dropInventoryItem(item2);
        assertEquals(1, inv.getCount());
        assertEquals(2, inv.getWeight());
    }

    @Test
    //Tests that the Inventories weight limit works correctly
    public void TestWeightLimit() {
        Inventory inv = new Inventory();
        InventoryItem item1 = new InventoryItem("Appliances", 200, "Dishwasher", 1);
        InventoryItem item2 = new InventoryItem("Appliances", 200, "Stove", 1);
        inv.addItemToInventory(item1);
        inv.addItemToInventory(item2);
        assertEquals(1, inv.getCount());
        assertEquals(200, inv.getWeight());
    }
    @Test
    //Tests that Adding Multiple Items whose weight individually is not over the weight limit, but whose collective weight
    //is over the limit is handled correctly
    //This test uses Mockito
    public void AddingMultipleItemsOverWeight() {
        Inventory inv = mock(Inventory.class);
        Inventory spyinv = spy(inv);
        InventoryItem item1 = new InventoryItem("Appliances", 20, "Toaster", 15);
        spyinv.addItemToInventory(item1);
        Mockito.verify(spyinv).addItemToInventory(item1);
        assertEquals(12, spyinv.getCount());
    }
}

