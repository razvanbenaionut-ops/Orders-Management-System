# Orders-Management_System

 ## Project description
A desktop application in **Java** designed to manage orders.
The application follows a **Layered Architecture** pattern 
(Controller, Service, Data Access Object - DAO) and implements 
advanced programming concepts such as the **Java Reflection API** 
for UI automation and dynamic object handling. The project was created
as part of an assignment for the course **Programming Technologies**
at the Faculty of Automation and Computer Science of the Technical
University of Cluj-Napoca.


## Key features
* **Client & Product Management:** Complete CRUD (Create, Read, 
Update, Delete) operations handled dynamically.
* **Order Processing:** Real-time order placement with automated
stock validation.
* **Bill Generation:** Automatic creation of a transaction log/bill
saved directly into the database as a Java Record.
* **Dynamic UI (Reflection API):** Graphic tables (`JTable`) are 
generated and populated dynamically using the *Reflection API* to 
automatically extract object properties (Client, Product, Order).



## Technologies & Concepts Used
*   **Language:** Java (Version 17)
*   **Database:** PostgreSQL 18
*   **Java Reflection API:** Used for dynamic parsing of model 
classes and automated UI table generation.
*   **JavaDoc:** Full structural API documentation generated 
directly from the source code.
*   **GUI Framework:** Java Swing

##  Database Structure
The relational database consists of 4 main tables:
1.  `client` - Stores client identification details.
2.  `product` - Manages product inventory details, stock levels, 
and prices.
3.  `orders` - Connects clients and products, saving quantities and
calculated total prices.
4.  `log` - Keeps an immutable history/journal of all generated bills.

##  Quick Start
1. Create a PostgreSQL database named `orders_management`.
2. Execute the SQL scripts in the following order:
    * First, run `dump.sql` to create the tables.
    * Next, run `datainsert.sql` to populate the tables with test data.
3. Update `ConnectionFactory.java` with your local PostgreSQL username and password.
4. Run `Main.java` to start the application.


##  Application Usage Guide

When you launch the application, you can navigate between three
main management views: **Clients**, **Products**, and **Orders**.

###  Client Management
*   **Add Client:** Enter the client's name in the text field and 
click **Add**.
    *   *Note on IDs:* The `ID` is generated automatically by the 
    database sequence. It increments sequentially and does not 
    reuse previously deleted IDs (e.g., if the highest ID was 15
    and it gets deleted, the next added client will automatically 
    receive ID 16).
*   **Update Client:** Select the desired client directly from the
table, edit the name in the text field, and click **Edit**. 
The `ID` field is read-only and cannot be modified.
*   **Delete Client:** Select the client from the table and click
the **Delete** button.

### Product Management
*   **Add Product:** Fill in the **Name**, **Stock**, and **Price**
text fields, then click **Add**.
    *   *Note on IDs:* Just like the Client section, the `ID` is 
    handled entirely by the database. It automatically increments
    to the next available number without reusing IDs from deleted
    records.
*   **Update Product:** Select the target product from the table,
input the new values, and click **Edit** to save the changes.
*   **Delete Product:** Select the specific product you wish to
remove from the table and click the **Delete** button.

###  Order Processing
*   To place an order, you must select exactly **one client** 
from the client table and **one product** from the product table.
*   Enter the desired quantity in the text field and click 
**order**.
*   The system will automatically validate the stock, calculate
the total price, update the product's remaining stock, and
instantly generate a bill (saved in the log history).