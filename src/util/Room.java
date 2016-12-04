package util;


public class Room {
    private String originalName, name, checksum;
    private int id;

    public Room(String originalName, String name, String checksum, int id) {
        this.originalName = originalName;
        this.name = name;
        this.checksum = checksum;
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getName() {
        return name;
    }

    public String getChecksum() {
        return checksum;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", checksum='" + checksum + '\'' +
                ", id=" + id +
                '}';
    }
}
