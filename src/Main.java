/* ================================== L2T07 Capstone Project: Main.java Description =================================

    This is the 'Main.java' file, which:
        (a) uses a 'try..catch' method with File and Scanner classes to get and read the file 'drivers.txt',
            and add the text content to a string.
        (b) uses a scanner object to get user input for restaurant and customer details, with a 'while' loop and
            nested 'if..else if' statement to allow the user to enter additional meals.
        (c) uses a method that returns the required Restaurant information for the bottom section of the invoice.
        (d) creates Customer and Restaurant instances by calling their class constructor methods.
        (e) gets details of Customer and Restaurant instances by calling 'get..DetailsFor..' methods on instances.
        (f) uses a 2D array to store driver details, to be compared with the inputted restaurant location.
        (g) uses a 'for' loop, nested 'if..else' statement, and 'try..catch' method to:
            - check if the driver location matches the restaurant location.
            - If true, create an invoice text file with the order information, using Formatter class.
            - Else, create an invoice text file with a message that the delivery can't be made, using Formatter class.

    *** NOTE FOR REVIEWER: BUG NOTE (see line 160 below) ***

    I had to create three separate scanner objects in order to get the additional meals loop to work properly.
    I couldn't use the original scanner for all the input because when it reaches the additional meals loop,
    it prints multiple output sections of the loop as one output, without waiting to get user input for each,
    so I had to insert a new scanner ('newMealAmount') inside the loop, then add a third scanner
    ('specialInstructionsScanner') because the loop breaks the first two. This works, but I had to edit the output
    instruction for the user to enter "N" twice to exit, because it's creating a bug where the user has to enter
    'N' twice before it exits the loop. I think it's because it's reading both the 'inputtedDetails' and
    'newMealAmount' scanner objects to it requires the 'N' input twice. I tried multiple ways of editing and closing
    the scanners, but nothing worked besides what I have now. If you have a workaround/solution for this, please let
    me know in the feedback.

    Please see the 'Restaurant' and 'Customer' classes for descriptions of their methods and attributes.
    Please see 'planning.txt' for the planning and description of this task.
    Please see 'L2T07_References.txt' for the list of references I used in this task.

=================================================================================================================== */
// Import Java packages.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        // ========================================= VARIABLES =======================================================

        // Declare variables for Customer input.
        String customerName;
        String customerEmail;
        String customerContactNumber;
        String customerStreetAddress;
        String customerSuburbAddress;
        String customerLocation;

        // Declare variables for Restaurant input.
        String restaurantName;
        String restaurantLocation;
        String restaurantContactNumber;
        String mealType;
        double mealPrice;
        int mealAmount;
        ArrayList<String> mealsOrderedList = new ArrayList<String>();
        ArrayList<Double> priceOfMealsList = new ArrayList<Double>();
        ArrayList<Integer> mealAmountList = new ArrayList<Integer>();
        String specialInstructions = "";

        // Declare variables for invoice information.
        String driverName;
        String driverDetails;

        //============================= GET AND READ DRIVERS TEXT FILE ===============================================

        // Declare and initialise variables.
        String inputFromDriversTextFile = "";

        // Get file 'drivers.txt' and add text content to 'inputFromDriversTextFile'.
        try
        {
            File driversTextFile = new File("../L2T07_Capstone_Project_1/drivers.txt");
            Scanner textScanner = new Scanner(driversTextFile);
            while (textScanner.hasNextLine())
            {
                inputFromDriversTextFile += textScanner.nextLine() + "\n";
            }
            textScanner.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error: File not found.");
        }

        //=========================== INPUT SCANNER: RESTAURANT & CUSTOMER INFO =====================================

        //Initialise scanner object.
        Scanner inputtedDetails = new Scanner(System.in);

        //------------------------------- Customer Information -----------------------------------------
        System.out.println("Enter your full name: ");
        customerName = inputtedDetails.nextLine();

        System.out.println("Enter your email address: ");
        customerEmail = inputtedDetails.nextLine();

        System.out.println("Enter your contact number: ");
        customerContactNumber = inputtedDetails.nextLine();

        System.out.println("Enter your street number and street name: ");
        customerStreetAddress = inputtedDetails.nextLine();

        System.out.println("Enter your suburb: ");
        customerSuburbAddress = inputtedDetails.nextLine();

        System.out.println("Enter your city: ");
        customerLocation = inputtedDetails.nextLine();

        //------------------------------- Restaurant Information -----------------------------------------
        System.out.println("\nEnter the restaurant name: ");
        restaurantName = inputtedDetails.nextLine();

        System.out.println("Enter the restaurant's city: ");
        restaurantLocation = inputtedDetails.nextLine();

        System.out.println("Enter the restaurant's contact number: ");
        restaurantContactNumber = inputtedDetails.nextLine();

        System.out.println("Enter the name of a meal you want to order: ");
        mealType = inputtedDetails.nextLine();
        mealsOrderedList.add(mealType);

        System.out.println("Enter the  price of this meal:  ");
        mealPrice = Double.parseDouble(inputtedDetails.next());
        priceOfMealsList.add(mealPrice);

        System.out.println("Enter how many of this meal you want to order: ");
        mealAmount = Integer.parseInt(inputtedDetails.next());
        mealAmountList.add(mealAmount);

        // Create loop for user to enter additional meals.
        while (true)
        {
            System.out.println
                    ("Would you like to order another meal? " +
                    "\nEnter Y to add a new meal or enter N twice to checkout: ");

            if ("Y".equalsIgnoreCase(inputtedDetails.next()))
            {
                Scanner newMealDetails = new Scanner(System.in);

                System.out.println("Enter the name of the next meal you want to order: ");
                String newMealType = newMealDetails.nextLine();
                mealsOrderedList.add(newMealType);

                System.out.println("Enter the  price of this meal:  ");
                Double newMealPrice = Double.parseDouble(newMealDetails.next());
                priceOfMealsList.add(newMealPrice);

                System.out.println("Enter how many of this meal you want to order: ");
                int newMealAmount = Integer.parseInt(newMealDetails.next());
                mealAmountList.add(newMealAmount);
            }
            //*** BUG NOTE: see description at top of page, requires entering 'N' twice. ***
            else if ("N".equalsIgnoreCase(inputtedDetails.next()))
            {
                break;
            }
        }

        //Create new scanner for special instructions (because break in loop closes scanners).
        Scanner specialInstructionsScanner = new Scanner(System.in);
        System.out.println("Enter special instructions or dietary requirements: ");
        specialInstructions = specialInstructionsScanner.nextLine();

        //============================== CREATE CUSTOMER & RESTAURANT INSTANCES =====================================

        // Create Customer & Restaurant instances by calling constructor method.
        Customer newCustomer = new Customer
            (customerName, customerEmail, customerContactNumber,
                    customerStreetAddress, customerSuburbAddress, customerLocation);

        Restaurant newRestaurant = new Restaurant
            (restaurantName, restaurantLocation, restaurantContactNumber,
            mealsOrderedList, priceOfMealsList, mealAmountList, specialInstructions);

        // ========================= CALL METHODS ON CUSTOMER & RESTAURANT INSTANCES ===============================

        // Get details of customer and restaurant instances by calling 'get..DetailsFor..' methods on instances.
        String customerDetailsTop = newCustomer.getCustomerDetailsForInvoiceTop();
        String restaurantDetailsTop = newRestaurant.getRestaurantDetailsForInvoiceTop();
        String customerDetailsBottom = newCustomer.getCustomerDetailsForInvoiceBottom();
        String restaurantDetailsBottom = newRestaurant.getRestaurantDetailsForInvoiceBottom();

        //========================= COMPARE LOCATIONS & CREATE INVOICE TEXT FILE ====================================

        // Declare and initialise variables to store and compare driver details with restaurant location.
        String[] inputStringArray = inputFromDriversTextFile.split("\n");
        String[][] driverDetailsArray = new String[inputStringArray.length][3];

        // If locations match, create file 'invoice.txt' with order information.
        // Else, create file 'invoice.txt' with message that delivery can't be made.
        for (int i = 0; i < driverDetailsArray.length; i++)
        {
            driverDetails = inputStringArray[i];
            driverDetailsArray[i] = driverDetails.split(", ");

            if (driverDetailsArray[i][1].equalsIgnoreCase(restaurantLocation))
            {
                driverName = driverDetailsArray[i][0];
                try
                {
                    Formatter customerInvoice = new Formatter("../L2T07_Capstone_Project_1/src/invoice.txt");
                    customerInvoice.format("%s", customerDetailsTop);
                    customerInvoice.format("%s", restaurantDetailsTop);
                    // *** SPECIFICATIONS TYPO: the Task Instructions only specified "he" in this message, and the
                    // drivers.txt file does not specify driver gender, so if the selected driver is female it will
                    // still print "he". I didn't edit it, to adhere to specifications, but I think it should print
                    // "s/he" to account for male and female drivers. ***
                    customerInvoice.format("%s", "\n" + driverName +" is nearest to the restaurant and so " +
                            "he will be delivering your order to you at: \n");
                    customerInvoice.format("%s", customerDetailsBottom);
                    customerInvoice.format("%s", restaurantDetailsBottom);
                    customerInvoice.close();
                }
                catch (Exception e) {System.out.println("Error: file not formatted.");}
                break;
            }
            else
            {
                try
                {
                    Formatter customerInvoice = new Formatter("../L2T07_Capstone_Project_1/src/invoice.txt");
                    customerInvoice.format
                            ("%s", "\nSorry! Our drivers are too far away from you to " +
                            "be able to deliver to your location.");
                    customerInvoice.close();
                }
                catch (FileNotFoundException e) {System.out.println("Error: file not formatted.");}
            }
        }
    }
}
