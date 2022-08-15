/* ============================= L2T07 Capstone Project: Restaurant.java Description =================================

    This file creates a Restaurant class, using:
        (a) a constructor method which adds attributes to the 'Restaurant' instance and calls methods (b) and (c).
        (b) a method with a 'for' loop and 2D array that gets and concatenates all the meal information entered
            by the user (i.e. meal type, price and number of meals), called in Constructor.
        (c) a method with a 'for' loop that calculates the total price of all the meals entered by the user, called
            in Constructor.
        (d) a method that returns the required Restaurant information for the top section of the invoice.
        (e) a method that returns the required Restaurant information for the bottom section of the invoice.

    Please see 'planning.txt' for the planning and description of this task.
    Please see 'L2T07_References.txt' for the list of references I used in this task.

=================================================================================================================== */

import java.util.ArrayList;

public class Restaurant
{
    // =================================== VARIABLES ===============================================================

    //Declare instance variables for Restaurant Constructor method.
    String restaurantName;
    String restaurantLocation;
    String restaurantContactNumber;
    String specialInstructions;
    ArrayList<String> mealsOrderedList = new ArrayList<String>();
    ArrayList<Double> priceOfMealsList = new ArrayList<Double>();
    ArrayList<Integer> mealAmountList = new ArrayList<Integer>();

    // Declare variables for 'getTotalMealsList' method.
    String totalMealsString = new String();

    // Declare variables for 'calculateTotalPrice' method.
    String[][] finalMealsList;
    double totalPrice;

    // =================================== METHODS ===============================================================

    // ----------------------------- Restaurant Constructor method ----------------------------------
    public Restaurant(String restaurantName, String restaurantLocation, String restaurantContactNumber,
                      ArrayList<String> mealsOrderedList, ArrayList<Double> priceOfMealsList,
                      ArrayList<Integer> mealAmountList, String specialInstructions)
    {
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
        this.restaurantContactNumber = restaurantContactNumber;
        this.mealsOrderedList = mealsOrderedList;
        this.priceOfMealsList = priceOfMealsList;
        this.mealAmountList = mealAmountList;
        this.specialInstructions = specialInstructions;

        // Call methods to get list of total meals and calculate total price.
        getTotalMealsList();
        calculateTotalPrice();
    }

    // --------------------- 'getTotalMealsList' method. --------------------------------------------
    private void getTotalMealsList()
    {
        // Create and initialise multidimensional array with row size of 'mealsOrderedList' and 3 columns.
        String[][] totalMealsList = new String[mealsOrderedList.size()][3];

        // Add values from 'mealsOrderedList', 'priceOfMealsList' and 'mealAmountList' to
        // 'totalMealsList' and 'totalMealsString'.
        for (int i = 0; i < totalMealsList.length; i++ )
        {
            totalMealsList[i][0] = String.valueOf(mealAmountList.get(i));
            totalMealsString += totalMealsList[i][0] + " x ";
            totalMealsList[i][1] = mealsOrderedList.get(i);
            totalMealsString += totalMealsList[i][1] + " ";
            totalMealsList[i][2] = String.valueOf(priceOfMealsList.get(i));
            totalMealsString += "(R" + totalMealsList[i][2] + ") \n";
        }
        // Add totalMealsList values to finalMealsList (to access values outside this method).
        finalMealsList = totalMealsList;
    }

    // --------------------- 'calculateTotalPrice' method. --------------------------------------------
    private void calculateTotalPrice()
    {
        //*** BUG NOTE: can't get double to print both decimal places when total has 0 in last place. ***
        // E.g.: if 'total = 52.50', it prints 'total = 52.5'.
        for (int i = 0; i<finalMealsList.length; i++)
        {
            double mealPrice = (Double.parseDouble(finalMealsList[i][0])) * (Double.parseDouble(finalMealsList[i][2]));
            totalPrice += mealPrice;
        }
    }

    // --------------------- 'restaurantDetails' method. --------------------------------------------

    public String getRestaurantDetailsForInvoiceTop()
    {
        return "\nYou have ordered the following from " + restaurantName + " in " + restaurantLocation + ": \n" +
                "\n" + totalMealsString +
                "\nSpecial Instructions: " + specialInstructions + "\n" +
                "\nTotal: R" + totalPrice + "\n";
    }

    public String getRestaurantDetailsForInvoiceBottom() {
        return "\nIf you need to contact the restaurant, their number is " + restaurantContactNumber + ".";
    }

}
