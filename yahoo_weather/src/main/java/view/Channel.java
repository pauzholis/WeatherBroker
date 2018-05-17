package view;

public class Channel {
    private Item item;
    private Location location;

    public Channel() {
    }

    public Channel(Item item, Location location) {
        this.item = item;
        this.location = location;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
