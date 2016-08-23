package tables;

/**
 * Created by 7 on 12.08.2016.
 */
public class Dish extends Table{

    private int ID;
    private int categoryOfDishes_ID;
    private int listOfIngredients_ID;
    private String nameOfTheDish;
    private double price;
    private int weight;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameOfTheDish() {
        return nameOfTheDish;
    }

    public void setNameOfTheDish(String nameOfTheDish) {
        this.nameOfTheDish = nameOfTheDish;
    }

    public int getCategoryOfDishes_ID() {
        return categoryOfDishes_ID;
    }

    public void setCategoryOfDishes_ID(int categoryOfDishes_ID) {
        this.categoryOfDishes_ID = categoryOfDishes_ID;
    }

    public int getListOfIngredients_ID() {
        return listOfIngredients_ID;
    }

    public void setListOfIngredients_ID(int listOfIngredients_ID) {
        this.listOfIngredients_ID = listOfIngredients_ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "ID=" + ID +
                " Dish_category_ID =" + categoryOfDishes_ID + "\t" +
                " list_of_ingredients_ID =" + listOfIngredients_ID + "\t" +
                " name_of_the_dish= " + nameOfTheDish + "\t" +
                " price=" + price + "\t" +
                " weight=" + weight +
                '}';
    }
}
