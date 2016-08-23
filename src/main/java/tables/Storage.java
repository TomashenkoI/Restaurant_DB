package tables;

/**
 * Created by 7 on 20.08.2016.
 */
public class Storage extends Table{

    private int ID;
    private int ingredient_ID;
    private int amount;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIngredient_ID() {
        return ingredient_ID;
    }

    public void setIngredient_ID(int ingredient_ID) {
        this.ingredient_ID = ingredient_ID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "ID=" + ID +
                ", ingredient_ID=" + ingredient_ID +
                ", amount=" + amount +
                '}';
    }
}
