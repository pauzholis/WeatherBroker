package view;


public class Item {
    private Condition condition;

    public Item() {
    }

    public Item(Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
