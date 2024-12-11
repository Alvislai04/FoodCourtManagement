# FoodCourtManagement
Developing an app about Food Ordering System for a food court management
 - Diff food outlets (stalls/vendors)

After Order completed
app will alert vendor
app will inform customers when the order ready
app own credit-based system (payment system)
Customer reload credit via administrator

The application should have the following features:
• Login access
• User Registration
• Menu
• Food order placement
• Notification
• Payment
• Order History
• Delivery System

5.1 Login access:
Design with 4 access right
- Vendor
- Customer
- Delivery runner
- Administrator

Vendor
❖ Create/Read/Update/Delete item
❖ Accept/Cancel order
❖ Update order status
❖ Check order history *daily, monthly, quarterly, etc.
❖ Read customer review
❖ Revenue Dashboard

Customer
❖ View menu
❖ Read other customer review
❖ Place/Cancel order
❖ Check order status
❖ Check order history
❖ Check transaction history
❖ Provide a review for each order
❖ Reorder using order history
❖ Customer complains
❖ Customer can choose (dine in/take away/request delivery service)

Delivery runner
❖ View task
❖ Accept/Decline task
❖ Update task status
❖ Check task history
❖ Read customer review
❖ Revenue Dashboard (Track daily, monthly, or yearly earnings)

Administrator
❖ User registration
	▪ Create/Read/Update/Delete vendor
	▪ Create/Read/Update/Delete customer
	▪ Create/Read/Update/Delete runner
❖ Top-up customer credit
❖ Generate transaction receipt
❖ Send receipt to customer through notification

Manager
❖ Monitor vendor performance through the Revenue Dashboard.
❖ Monitor Delivery Runner performance using customer feedback through points / likes (star ratings) /reviews.
❖ Resolve customer complaints or disputes.
❖ Discard / Remove vendor item listings (not appropriate items)

5.2 User Registration:
Only administrators can register customers and vendors (save db in txt file)

5.3 Menu:
The Menu should present menus from all vendors

5.4 Food Order Placement: 
Customers can choose to dine-in, takeaway or request for delivery when placing the order. ***
Additional charges will be imposed if customers request for delivery service.
Vendors can choose to accept or decline order. If order is declined, app will refund.

5.5 Payment:
The application should incorporate a digital wallet feature.
Prevent customers from placing the order if the credit is insufficient
Customers can reload their credit via administrators
Include a feature that enable administrators to update customers’ credit based on their account ID, followed by sending a digital receipt to customer via notification system.

5.6 Notification:
Designed for all types of users
Administrators can use it to send digital receipt every time customers reload their account credit.
Keep both vendors and customers updated about their order.
*Customer*
- (Dine in, Takeaway) vendor has accepted the order, kitchen is preparing order, food is ready to serve, $ had been deducted
- (Delivery) order placed, Finding delivery runner, kitchen is preparing order, delivery runner collected your order and out for delivery, delivery runner reached the destination, $ had been deducted
*Vendors*
- (Dine in, Takeaway) vendor has accepted/declined the order
- (Delivery) vendor has accepted/declined the order, Delivery runner is ready to deliver customer's order, order completed
**Delivery runner**
- You have a new order(Accept/Decline), Order is ready to be delivered, Customer has collected the order

5.7 Order History
Customers and vendors can view their orders and transactions
Customer:
Can track the status and details of their current and past orders such as
- order placement times
- order amount
- acceptance or declined status
- etc
Should have the option to reorder using their order history
Vendor:
Track of their revenue and produce report based on the order history
Can review customer feedback and ratings for every order

5.8 Delivery System
Application allocate a runner automatically and send them notification to keep them notified with the order status
Runner can choose to accept or decline the task and when the task is declined, the application must allocate next available runner
If no available runner, the application notify customers and prompt them to choose either dine-in or takeaway.
Delivery fees will be credit back to the customer’s account

** Important **
Students should use TEXT FILE for storing and retrieving data required for the system
