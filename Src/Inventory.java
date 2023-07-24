package Src;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Tool> toolInventory;

    public Inventory() {
        toolInventory = new HashMap<>();
    }

    public void addTool(Tool tool) {
        toolInventory.put(tool.getToolCode(), tool);
    }

    public Tool getByToolCode(String toolCode) {
        return toolInventory.get(toolCode);
    }

}
