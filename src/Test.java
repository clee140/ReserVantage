import java.io.*;
import java.util.ArrayList;

/**
 * Project 4 - Test
 * <p>
 * This program is a test class for all the methods in the Seller.java and Customer.java classes, which are used in
 * the Main.java class. This program tests to make sure the methods are running properly with valid input. There will be
 * no invalid input to test for because the Main.java main method ensures the user is inputting proper input
 * <p>
 * There will be some extra text when you run the program, as some methods print out text when called.
 * To check to see if the method is working correctly, just check the lines that end in "test: "
 * <p>
 * The program will delete all files after running
 *
 * @author Audrey Lupton, Sreekar Gudipati, L33
 * @version November 12, 2023
 */

public class Test {
    public static void main(String[] args) throws IOException {
        //Seller Tests
        System.out.println("Seller Tests:");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("userDatabase.txt", true)));
        pw.println("Seller,sellerName,sellerUsername,sellerPassword");
        pw.flush();
        pw.close();
        //Creating a customer object
        Customer customer = new Customer("customerUsername", "customerUsername.txt");
        customer.createCustomer();

        //createHotel()
        Seller seller = new Seller("sellerUsername", "SpringHill Suites", "sellerUsernameTest.txt");
        seller.createHotel();

        String createHotelActual = "";
        BufferedReader bfr = new BufferedReader(new FileReader("hotels.txt"));
        String line = "";
        while ((line = bfr.readLine()) != null) {
            createHotelActual += line + "\n";
        }
        bfr.close();
        String creatHotelExpected = "sellerUsername,sellerUsernameTest.txt\n";
        System.out.println("createHotel() test: " + createHotelActual.equals(creatHotelExpected));
        System.out.println();

        //createCalendarWithFile(String importFilename)
        seller.createCalendarWithFile("createCalendarWithFileTest.txt");

        String createCalendarWithFileActual = "";
        BufferedReader calendarFileReader = new BufferedReader(new FileReader("sellerUsernameTest.txt"));
        line = "";
        while ((line = calendarFileReader.readLine()) != null) {
            createCalendarWithFileActual += line + "\n";
        }
        calendarFileReader.close();
        createCalendarWithFileActual = createCalendarWithFileActual.substring(0, createCalendarWithFileActual.length()
                - 29); //Removes timestamp
        String createCalendarWithFileExpected = "Store name:SpringHill Suites\nCalendar name:January\nDescription:Nice " +
                "hotel\nAppointment List: (Calendar name-Appointment Title,Max Attendees,Approved Bookings,Start Time," +
                "End Time)\nJanuary-Christopher,18,0,9:00,10:00\nJanuary-Sreekar,20,1,10:00,11:00";
        System.out.println("createCalendarWithFile(String importFilename) test: " +
                createCalendarWithFileActual.equals(createCalendarWithFileExpected));
        System.out.println();

        //Deletes the file so we can test createCalendar() without the timestamp
        File b = new File("sellerUsernameTest.txt");
        b.delete();
        seller.createHotel();

        //createCalendar();
        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment("Audrey", 5, 0, "5:00", "12:00"));
        appointments.add(new Appointment("Sruthi", 6, 1, "6:00", "1:00"));
        seller.createCalendar("February", "This is a hotel!", appointments);

        String createCalendarActual = "";
        BufferedReader calendarReader = new BufferedReader(new FileReader("sellerUsernameTest.txt"));
        line = "";
        while ((line = calendarReader.readLine()) != null) {
            createCalendarActual += line + "\n";
        }
        calendarReader.close();
        createCalendarActual = createCalendarActual.substring(0, createCalendarActual.length() - 29); //Removes timestamp
        String createCalendarExpected = "Store name:SpringHill Suites\nCalendar name:February\nDescription:This is a " +
                "hotel!\nAppointment List: (Calendar name-Appointment Title,Max Attendees,Approved Bookings,Start Time," +
                "End Time)\nFebruary-Audrey,5,0,5:00,12:00\nFebruary-Sruthi,6,1,6:00,1:00\n";
        System.out.println("createCalendar() test: " + createCalendarActual.equals(createCalendarExpected));
        System.out.println();


        //printCalendar()
        String printCalendarActual = seller.printCalendar();
        printCalendarActual = printCalendarActual.substring(0, printCalendarActual.length() - 29); //Removes timestamp
        String printCalendarExpected = "Store name:SpringHill Suites\nCalendar name:February\nDescription:This is a " +
                "hotel!\nAppointment List: (Calendar name-Appointment Title,Max Attendees,Approved Bookings,Start Time," +
                "End Time)\nFebruary-Audrey,5,0,5:00,12:00\nFebruary-Sruthi,6,1,6:00,1:00\n";
        System.out.println("printCalendar() test: " + printCalendarActual.equals(printCalendarExpected));
        System.out.println();

        //editCalendar(String calendarName, String appointmentTitle, String updatedAppointment)
        seller.editCalendar("February", "Audrey", "Drey,5,0,4:00,7:00");
        String editCalendarActual = "";
        BufferedReader editReader = new BufferedReader(new FileReader("sellerUsernameTest.txt"));
        line = "";
        while ((line = editReader.readLine()) != null) {
            editCalendarActual += line + "\n";
        }
        editReader.close();
        editCalendarActual = editCalendarActual.substring(0, editCalendarActual.length() - 58); //Deleting the timestamps
        String editCalendarExpected = "Store name:SpringHill Suites\nCalendar name:February\nDescription:This is a " +
                "hotel!\nAppointment List: (Calendar name-Appointment Title,Max Attendees,Approved Bookings,Start Time," +
                "End Time)\nFebruary-Drey,5,0,4:00,7:00\nFebruary-Sruthi,6,1,6:00,1:00";
        System.out.println("editCalendar(String calendarName, String appointmentTitle, String updatedAppointment) test: "
                + editCalendarActual.equals(editCalendarExpected));
        System.out.println();


        //deleteCalendar(String calendarName)
        seller.deleteCalendar("February");
        String deleteCalendarActual = "";
        BufferedReader deleteReader = new BufferedReader(new FileReader("sellerUsernameTest.txt"));
        line = "";
        while ((line = deleteReader.readLine()) != null) {
            deleteCalendarActual += line + "\n";
        }
        deleteReader.close();
        deleteCalendarActual = deleteCalendarActual.substring(0, deleteCalendarActual.length() - 29); //Removes timestamp
        String deleteCalendarExpected = "Store name:SpringHill Suites\n";
        System.out.println("deleteCalendar(String calendarName) test: " +
                deleteCalendarActual.equals(deleteCalendarExpected));
        System.out.println();

        //Deletes the file so we can test further methods without the timestamp
        b.delete();
        seller.createHotel();

        //getCustomerRequest()
        //Creating calendar in order for customer to make appointment
        seller.createCalendarWithFile("createCalendarWithFileTest.txt");
        //Customer making appointment
        customer.makeAppointment("January-Christopher,18,0,9:00,10:00");
        String getCustomerRequestActual = seller.getCustomerRequest();
        String getCustomerRequestExpected = "January-Christopher,18,0,9:00,10:00-customerUsername\n";
        System.out.println("getCustomerRequest() test: " + getCustomerRequestActual.equals(getCustomerRequestExpected));
        System.out.println();

        //handleCustomerRequests(String appointment, String customerUsername)
        seller.handleCustomerRequests("January-Christopher,18,0,9:00,10:00", "customerUsername", "");
        String handleCustomerRequestsActual = "";
        BufferedReader handleReader = new BufferedReader(new FileReader("awaitingApproval.txt"));
        line = "";
        while ((line = handleReader.readLine()) != null) {
            handleCustomerRequestsActual += line + "\n";
        }
        handleReader.close();
        String handleCustomerRequestsExpected = ""; //Means that the method successfully removed the appointment from awaitingApproval.txt
        System.out.println("handleCustomerRequests(String appointment, String customerUsername) test: " + handleCustomerRequestsActual.equals(handleCustomerRequestsExpected));
        System.out.println();

        //Deleting and adding a calendar to test the approved appointments and view statistics methods
        seller.deleteCalendar("January");
        seller.createCalendar("February", "This is a hotel!", appointments);

        //viewApprovedAppointments()
        String viewApprovedAppointmentsActual = seller.viewApprovedAppointments();
        String viewApprovedAppointmentsExpected = "February-Sruthi,6,1,6:00,1:00\n";
        System.out.println("viewApprovedAppointments() test: " +
                viewApprovedAppointmentsActual.equals(viewApprovedAppointmentsExpected));
        System.out.println();

        //viewStatistics()
        String viewStatisticsActual = seller.viewStatistics("Yes");
        String viewStatisticsExpected = "Approved appointments: \nFebruary-Sruthi,6,1,6:00,1:00\n\nPopular: " +
                "February-Sruthi,6,1,6:00,1:00\nStandard: February-Audrey,5,0,5:00,12:00\n";
        System.out.println("viewStatistics() test: " + viewStatisticsActual.equals(viewStatisticsExpected));
        System.out.println();

        //Deletes the files since program adds to the files as users delete/add calendars and appointments
        //TODO: Will have to remove some delete() methods for the final submission
        File g = new File("userDatabase.txt");
        g.delete();

        b.delete();
        seller.createHotel();
        seller.createCalendarWithFile("createCalendarWithFileTest.txt");


        //TODO: Customer tests
        //createCustomer()
        String createCustomerActual = "";
        BufferedReader customerReader = new BufferedReader(new FileReader("customerDatabase.txt"));
        String customerLine = "";
        while ((line = customerReader.readLine()) != null) {
            createCustomerActual += line + "\n";
        }
        customerReader.close();
        String createCustomerExpected = "customerUsername,customerUsername.txt\n";
        System.out.println("createCustomer() test: " + createCustomerActual.equals(createCustomerExpected));
        System.out.println();
        customerReader.close();

        //viewCalendars()
        String customerCalendarsActual = customer.viewCalendars();
        String customerCalendarsExpected = "";
        ArrayList<String> sellerCalendars = customer.readFile("hotels.txt");
        for (int i = 0; i < sellerCalendars.size(); i++) {
            String[] calendarFile = sellerCalendars.get(i).split(",");
            ArrayList<String> sellerFile = customer.readFile(calendarFile[1]);

            for (int j = 0; j < sellerFile.size(); j++) {
                customerCalendarsExpected += sellerFile.get(j) + "\n";
            }
        }
        System.out.println("viewCalendars() test: " + customerCalendarsActual.equals(customerCalendarsExpected));
        System.out.println();

        //makeAppointment()
        customer.makeAppointment("January-Sreekar,20,1,10:00,11:00");
        String customerAppointmentExpected = "January-Sreekar,20,1,10:00,11:00-customerUsername";
        boolean found = false;
        ArrayList<String> awaitingApprovalList = customer.readFile("awaitingApproval.txt");
        for (int i = 0; i < awaitingApprovalList.size(); i++) {
            if (awaitingApprovalList.get(i).contains(customerAppointmentExpected)) {
                found = true;
            }
        }

        System.out.println("makeAppointment() test: " + found);
        System.out.println();

        //viewApprovedAppointments()
        String viewCustomerAppointmentsActual = customer.viewApprovedAppointments();
        String viewCustomerAppointmentsExpected = "January-Christopher,18,0,9:00,10:00\n";

        System.out.println("viewApprovedAppointments() test: " + viewCustomerAppointmentsActual.equals
                (viewCustomerAppointmentsExpected));
        System.out.println();

        //viewAppointmentsWaitingApproval()
        String customerAwaitingApprovalActual = customer.viewAppointmentsWaitingApproval();
        String customerAwaitingApprovalExpected = "January-Sreekar,20,1,10:00,11:00-customerUsername\n";

        System.out.println("viewAppointmentsWaitingApproval() test: " + customerAwaitingApprovalActual.equals
                (customerAwaitingApprovalExpected));
        System.out.println();

        //cancelAppointment()
        boolean customerCancelled = customer.cancelAppointment(
                "January-Sreekar,20,1,10:00,11:00-customerUsername", 1);
        String customerCancelledAwaitingApproval = customer.viewAppointmentsWaitingApproval();

        System.out.println("cancelAppointment() for appointments awaiting approval test: "
                + customerCancelledAwaitingApproval.equals
                (""));
        System.out.println();

        customerCancelled = customer.cancelAppointment("January-Christopher,18,0,9:00,10:00",
                2);
        String customerCancelledApprovedAppointment = customer.viewApprovedAppointments();


        System.out.println("cancelAppointment() for approved appointments test: "
                + customerCancelledApprovedAppointment.equals
                ("No approved appointments."));
        System.out.println();

        //viewDashboard()
        boolean correct = false;
        String customerDashboard = customer.viewDashboard(1);
        if (customerDashboard.contains("Store name:")) {
            if (customerDashboard.contains("-") && customerDashboard.contains(",")) {
                correct = true;
            }
        }
        System.out.println("viewDashboard() test: " + correct);
        System.out.println();

        File h = new File("hotels.txt");
        h.delete();
        File a = new File("customerUsername.txt");
        a.delete();
        File z = new File("awaitingApproval.txt");
        z.delete();
        File y = new File("cancelledAppointments.txt");
        y.delete();
        File x = new File("customerDatabase.txt");
        x.delete();
        File u = new File("sellerUsernameTest.txt");
        u.delete();
    }

}