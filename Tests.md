# Test Cases

## Test 1: User Account Creation - Seller
### Steps:

User launches the application.
User clicks on the "Create Account" text box.
User selects the "Seller" option box.
User enters their name, email, and password via the keyboard.
User selects the "Enter" textbox.
### Expected result:

All necessary information (name, email, password) is filled.
No duplicate emails or passwords exist in the database.
Upon successful validation:
The application navigates the user back to the home page.
Test Status: Passed.

## Test 2: User Account Creation - Customer
### Steps:

User launches the application.
User clicks on the "Create Account" text box.
User selects the "Customer" option box.
User enters their name, email, and password via the keyboard.
User selects the "Enter" textbox.
### Expected result:

All necessary information (name, email, password) is filled.
No duplicate emails or passwords exist in the database.
Upon successful validation:
The application navigates the user back to the home page.
Test Status: Passed.

## Test 3: Navigation from Create Account to Home Page
### Steps:

User launches the application.
User selects the "Create Account" textbox.
User selects the "Go Back" textbox.
### Expected result:

The application is launched successfully.
The application navigates to the Create Account page.
Upon selecting "Go Back," the application returns to the home page.
Test Status: Passed.

## Test 4: Seller Login and Redirect to Seller Home Page
### Steps:

User opens the application.
User selects the login textbox.
User selects the seller option box.
User fills valid information into the name, email, and password textboxes.
User selects the "Enter" textbox.
User selects the "OK" textbox on the login successful popup.
### Expected result:

The application loads the home page.
On selecting the login textbox, the application navigates to the login page.
After entering valid information, the application verifies the user's existence in the database.
A popup confirms successful login.
The application redirects to the seller's home page.
Test Status: Passed.

## Test 5: Seller Store Calendar Navigation
### Steps:

User enters the seller home page.
User fills in the appropriate store name in the textbox via the keyboard.
User selects "View Current Calendars" from the dropdown option box.
User selects the "Proceed" textbox.
User selects the "Go Back" textbox.
### Expected result:

Upon entering the seller home page, the user interface is loaded successfully.
The user enters the appropriate store name in the textbox.
The dropdown menu displays all available options upon selection.
On pressing "Proceed," the calendars are listed.
Upon selecting "Go Back," the user returns to the previous window displaying the store name and the dropdown menu.
Test Status: Passed.

## Test 6: Seller Calendar Creation and Import
### Steps:

User enters the seller home page.
User fills in the appropriate store name in the textbox using the keyboard.
User selects "Create New Calendar" from the dropdown option box.
User selects the "Proceed" textbox.
User selects "Import File" textbox.
User enters the filename using the keyboard and selects the "Import" textbox.
User presses the "OK" textbox.
### Expected result:

The seller home page loads successfully.
User inputs the appropriate store name in the textbox via the keyboard.
"Create new calendar" is selected from the dropdown menu.
On selecting "Proceed," a page with three textboxes is displayed.
Choosing "Import File" loads the import page.
Upon selecting "Import," a success message popup is displayed.
On pressing "OK," the application returns to the seller home page.
Test Status: Passed.


## Test 7: Seller Calendar Creation - Manual Input and Appointment Addition
### Steps:

User enters the seller home page.
User fills in the appropriate store name in the textbox using the keyboard.
User selects "Create New Calendar" from the dropdown option box.
User selects the "Proceed" textbox.
User selects "Manually Create Calendar" textbox.
User enters the calendar name and description via the keyboard.
User selects "Add Appointment" textbox.
User fills all five fields appropriately and selects "Create Appointment" textbox.
User selects "Create Calendar" textbox.
User selects the "OK" textbox.
### Expected result:

The seller home page loads successfully.
User inputs the appropriate store name in the textbox using the keyboard.
"Create New Calendar" is selected from the dropdown menu.
On selecting "Proceed," a page with three textboxes is displayed.
Upon selecting "Manually Create Calendar," the manual calendar creation page opens.
Selecting "Add Appointment" loads the appointment creation page.
After adding the appointment, the program returns to the manual calendar page and displays the new appointment.
On selecting "Create Calendar," a popup confirms successful calendar creation.
Pressing "OK" navigates the application back to the seller home page.
Test Status: Passed.


## Test 8: Seller Calendar Editing
### Steps:

User enters the seller home page.
User fills in the appropriate store name in the textbox using the keyboard.
User selects "Edit Calendar" from the dropdown option box.
User fills all seven fields appropriately using the keyboard.
User selects the "Proceed" textbox.
User selects the "OK" textbox.
### Expected result:

The seller home page loads successfully.
User inputs the appropriate store name in the textbox using the keyboard.
"Edit Calendar" is selected from the dropdown menu.
On selecting "Proceed," the application opens the edit calendar page.
After proceeding, a success message popup is displayed.
Selecting "OK" navigates the application back to the seller home page.
Test Status: Passed.
















