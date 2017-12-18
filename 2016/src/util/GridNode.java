package util;


public class GridNode {
    private String name, size, usage;
    private int used, available;

    public GridNode(String name, String size, int used, int available, String usage) {
        this.name = name;
        this.size = size;
        this.used = used;
        this.available = available;
        this.usage = usage;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public int getUsed() {
        return used;
    }

    public int getAvailable() {
        return available;
    }

    public String getUsage() {
        return usage;
    }
}
