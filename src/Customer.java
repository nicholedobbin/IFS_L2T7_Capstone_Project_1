/* ============================= L2T07 Capstone Project: Customer.java Description =================================

    This file creates a Customer class, using:
        (a) a constructor method which adds attributes to the 'Customer' instance.
        (b) a method that returns the required Restaurant information for the top section of the invoice.
        (c) a method that returns the required Restaurant information for the bottom section of the invoice.

    Please see 'planning.txt' for the planning and description of this task.
    Please see 'L2T07_References.txt' for the list of references I used in this task.

=================================================================================================================== */
public class Customer
{
    // ======================================= VARIABLES ===========================================================

    //Declare instance variables for Customer Constructor and 'getDetails' methods.
    int orderNumber = 1001;
    String customerName;
    String customerEmail;
    String customerContactNumber;
    String customerStreetAddress;
    String customerSuburbAddress;
    String customerLocation;

    // ======================================= METHODS ===========================================================

    // ----------------------------- Customer Constructor method ----------------------------------
    public Customer(String customerName, String customerEmail, String customerContactNumber,
                    String customerStreetAddress, String customerSuburbAddress, String customerLocation)
    {
        orderNumber++;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerContactNumber = customerContactNumber;
        this.customerStreetAddress = customerStreetAddress;
        this.customerSuburbAddress = customerSuburbAddress;
        this.customerLocation = customerLocation;
    }

    // ----------------------------- 'getCustomerDetailsForInvoiceTop' method ----------------------------------
    // Returns customer details for top section of invoice.
    public String getCustomerDetailsForInvoiceTop()
    {
        return "Order Number: " + orderNumber +
                "\nCustomer: " + customerName +
                "\nEmail: " + customerEmail +
                "\nPhone number: " + customerContactNumber +
                "\nLocation: " + customerLocation + "\n ";
    }

    // ----------------------------- 'getCustomerDetailsForInvoiceTBottom' method ----------------------------------
    // Returns customer details for bottom section of invoice.
    public String getCustomerDetailsForInvoiceBottom()
    {
        return ("\n" + customerStreetAddress + "\n" + customerSuburbAddress + "\n");
    }
}
