package Src;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private static Inventory inventory = new Inventory();
    private Map<String, Tool> toolInventory;

    public Inventory() {
        toolInventory = new HashMap<>();

        // Creating Tool types
        ToolType LADDER = new ToolType("Ladder", 1.99, true, true, false);
        ToolType CHAINSAW = new ToolType("Chainsaw", 1.49, true, false, true);
        ToolType JACKHAMMER = new ToolType("Jackhammer", 2.99, true, false, false);

        // Adding current tools to inventory
        toolInventory.put("CHNS", new Tool("CHNS", CHAINSAW, "Stihl"));
        toolInventory.put("LADW", new Tool("LADW", LADDER, "Werner"));
        toolInventory.put("JAKD", new Tool("JAKD", JACKHAMMER, "DeWalt"));
        toolInventory.put("JAKR", new Tool("JAKR", JACKHAMMER, "Ridgid"));
    }

    public Tool getByToolCode(String toolCode) {
        return toolInventory.get(toolCode);
    }

    public static Inventory getInventory() {
        return inventory;
    }
}
