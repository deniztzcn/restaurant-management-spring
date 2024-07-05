INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;
INSERT INTO Tables DEFAULT VALUES;

INSERT INTO
    UserRole (name, description)
VALUES
    ('ADMIN', 'Administrative privileges'),
    ('USER', 'Regular user privileges');

INSERT INTO AppUser (firstName, lastName, email, password) VALUES ('Admin', 'LastName', 'admin@example.com', '$2a$10$U/O6ymL2m/EnCNADwtwQRO4BoJna2cJzhABl5x2u1vs4VyRSSc8NW');
INSERT INTO AppUser_roles (AppUser_id,roles_id) VALUES (1,1);

INSERT INTO AppUser (firstName, lastName, email, password) VALUES ('User', 'LastName', 'user@example.com', '$2a$10$6U4gF1COk97/5xgXO1StAeKkQs7IuFWDuLWoDW188P45dSmcjosHS');
INSERT INTO AppUser_roles (AppUser_id,roles_id) VALUES (2,2);


INSERT INTO Category (name)
SELECT 'Starters';

INSERT INTO Category (name)
SELECT 'Pasta';

INSERT INTO Category (name)
SELECT 'Pizza';

INSERT INTO Category (name)
SELECT 'Seafood';

INSERT INTO Category (name)
SELECT 'Salads';

INSERT INTO Category (name)
SELECT 'Meat Dishes';

INSERT INTO Category (name)
SELECT 'Hot Beverages';

INSERT INTO Category (name)
SELECT 'Cold Beverages';

INSERT INTO Category (name)
SELECT 'Wines';

INSERT INTO Category (name)
SELECT 'Beers';

INSERT INTO Category (name)
SELECT 'Cocktails';



INSERT INTO Department (name)
SELECT 'Manager';

INSERT INTO Department (name)
SELECT 'Waitstaff';

INSERT INTO Department (name)
SELECT 'Kitchen staff';

INSERT INTO Department (name)
SELECT 'Bartender';



INSERT INTO Employee (name, surname, supervisor_id, department_id)
SELECT 'Mario', 'Rossi', NULL, 1;

INSERT INTO Employee (name, surname, supervisor_id, department_id)
SELECT 'Luigi', 'Verdi', (SELECT id FROM Employee WHERE name = 'Mario' AND surname = 'Rossi'), 4;

INSERT INTO Employee (name, surname, supervisor_id, department_id)
SELECT 'Anna', 'Bianchi', (SELECT id FROM Employee WHERE name = 'Mario' AND surname = 'Rossi'), 2;

INSERT INTO Employee (name, surname, supervisor_id, department_id)
SELECT 'Giorgio', 'Neri', (SELECT id FROM Employee WHERE name = 'Anna' AND surname = 'Bianchi'), 3;

INSERT INTO Employee (name, surname, supervisor_id, department_id)
SELECT 'Lucia', 'Gialli', (SELECT id FROM Employee WHERE name = 'Anna' AND surname = 'Bianchi'), 2;



INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Bruschetta', 'Toasted bread with tomato and basil', 6.50, (SELECT id FROM Category WHERE name = 'Starters');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Spaghetti Carbonara', 'Spaghetti with eggs, cheese, pancetta, and pepper', 14.00, (SELECT id FROM Category WHERE name = 'Pasta');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Fettuccine Alfredo', 'Pasta with creamy Alfredo sauce', 13.00, (SELECT id FROM Category WHERE name = 'Pasta');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Insalata Caprese', 'Tomato, mozzarella, and basil salad', 8.00, (SELECT id FROM Category WHERE name = 'Salads');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Margherita', 'Classic pizza with tomato, mozzarella, and basil', 10.00, (SELECT id FROM Category WHERE name = 'Pizza');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Frittura di Calamari', 'Fried calamari with lemon', 12.00, (SELECT id FROM Category WHERE name = 'Seafood');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Ossobuco alla Milanese', 'Braised veal shanks with vegetables', 18.00, (SELECT id FROM Category WHERE name = 'Meat Dishes');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Garlic Bread', 'Bread with garlic and butter', 5.00, (SELECT id FROM Category WHERE name = 'Starters');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Caesar Salad', 'Salad with romaine lettuce and croutons', 9.00, (SELECT id FROM Category WHERE name = 'Salads');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Lasagna', 'Layered pasta with meat and cheese', 15.00, (SELECT id FROM Category WHERE name = 'Pasta');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Pepperoni Pizza', 'Pizza with pepperoni', 11.00, (SELECT id FROM Category WHERE name = 'Pizza');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Grilled Salmon', 'Salmon with herbs and lemon', 20.00, (SELECT id FROM Category WHERE name = 'Seafood');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Chicken Parmesan', 'Breaded chicken with marinara and cheese', 17.00, (SELECT id FROM Category WHERE name = 'Meat Dishes');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Cappuccino', 'Italian coffee drink', 4.50, (SELECT id FROM Category WHERE name = 'Hot Beverages');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Iced Tea', 'Cold tea with lemon', 3.50, (SELECT id FROM Category WHERE name = 'Cold Beverages');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Red Wine', 'Glass of red wine', 8.00, (SELECT id FROM Category WHERE name = 'Wines');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'White Wine', 'Glass of white wine', 8.00, (SELECT id FROM Category WHERE name = 'Wines');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Beer', 'Glass of beer', 5.00, (SELECT id FROM Category WHERE name = 'Beers');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Mojito', 'Cocktail with rum, lime, and mint', 7.00, (SELECT id FROM Category WHERE name = 'Cocktails');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Espresso', 'Strong coffee', 3.00, (SELECT id FROM Category WHERE name = 'Hot Beverages');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Limoncello', 'Italian lemon liqueur', 6.00, (SELECT id FROM Category WHERE name = 'Cold Beverages');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Garlic Shrimp', 'Shrimp sautéed with garlic and herbs', 12.00, (SELECT id FROM Category WHERE name = 'Seafood');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Chicken Alfredo', 'Pasta with creamy Alfredo sauce and chicken', 15.00, (SELECT id FROM Category WHERE name = 'Pasta');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Greek Salad', 'Salad with tomatoes, cucumber, olives, and feta', 9.00, (SELECT id FROM Category WHERE name = 'Salads');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'BBQ Ribs', 'Grilled ribs with BBQ sauce', 18.00, (SELECT id FROM Category WHERE name = 'Meat Dishes');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Vegetarian Pizza', 'Pizza with mixed vegetables', 11.00, (SELECT id FROM Category WHERE name = 'Pizza');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Lobster Bisque', 'Creamy lobster soup', 14.00, (SELECT id FROM Category WHERE name = 'Starters');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Chicken Nuggets', 'Crispy fried chicken pieces served with your choice of dipping sauce.', 4.99,
       (SELECT id FROM Category WHERE name = 'Meat Dishes');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Berry Smoothie', 'Smoothie with mixed berries', 5.50, (SELECT id FROM Category WHERE name = 'Cold Beverages');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Minestrone Soup', 'Vegetable soup with pasta', 8.00, (SELECT id FROM Category WHERE name = 'Starters');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Beef Tacos', 'Tacos with seasoned beef', 10.00, (SELECT id FROM Category WHERE name = 'Meat Dishes');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Margarita', 'Classic cocktail with tequila, lime, and orange liqueur', 8.00, (SELECT id FROM Category WHERE name = 'Cocktails');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Matcha Latte', 'Green tea latte', 4.50, (SELECT id FROM Category WHERE name = 'Hot Beverages');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Cranberry Juice', 'Glass of cranberry juice', 3.50, (SELECT id FROM Category WHERE name = 'Cold Beverages');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Roasted Chicken', 'Chicken roasted with herbs', 16.00, (SELECT id FROM Category WHERE name = 'Meat Dishes');

INSERT INTO MenuItem (name, description, price, category_id)
SELECT 'Cheese Platter', 'Assorted cheeses with crackers', 12.00, (SELECT id FROM Category WHERE name = 'Starters');



INSERT INTO Product (name, measurement)
SELECT 'Tomato', 'pcs';

INSERT INTO Product (name, measurement)
SELECT 'Basil', 'bunch';

INSERT INTO Product (name, measurement)
SELECT 'Bread', 'loaf';

INSERT INTO Product (name, measurement)
SELECT 'Calamari', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Lemon', 'pcs';

INSERT INTO Product (name, measurement)
SELECT 'Spaghetti', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Pancetta', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Mozzarella', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Veal Shanks', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Garlic', 'cloves';

INSERT INTO Product (name, measurement)
SELECT 'Romaine Lettuce', 'heads';

INSERT INTO Product (name, measurement)
SELECT 'Ground Beef', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Pepperoni', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Salmon', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Chicken Breast', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Coffee Beans', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Tea Leaves', 'g';

INSERT INTO Product (name, measurement)
SELECT 'Red Wine Bottle', 'bottle';

INSERT INTO Product (name, measurement)
SELECT 'White Wine Bottle', 'bottle';

INSERT INTO Product (name, measurement)
SELECT 'Beer Keg', 'keg';

INSERT INTO Product (name, measurement)
SELECT 'Rum Bottle', 'bottle';

INSERT INTO Product (name, measurement)
SELECT 'Mint', 'bunch';

INSERT INTO Product (name, measurement)
SELECT 'Mascarpone', 'kg';


INSERT INTO Product (name, measurement)
SELECT 'Cream', 'liter';

INSERT INTO Product (name, measurement)
SELECT 'Chocolate', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Lemon Zest', 'g';

INSERT INTO Product (name, measurement)
SELECT 'Shrimp', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Feta Cheese', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'BBQ Sauce', 'liter';

INSERT INTO Product (name, measurement)
SELECT 'Mixed Vegetables', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Lobster', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Potatoes', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Cheddar Cheese', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Vegetarian Patty', 'pcs';

INSERT INTO Product (name, measurement)
SELECT 'Berries', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Chicken Pieces', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Pasta', 'kg';

INSERT INTO Product (name, measurement)
SELECT 'Taco Shells', 'pcs';

INSERT INTO Product (name, measurement)
SELECT 'Tequila', 'bottle';

INSERT INTO Product (name, measurement)
SELECT 'Matcha Powder', 'g';

INSERT INTO Product (name, measurement)
SELECT 'Cranberry Juice', 'liter';

INSERT INTO Product (name, measurement)
SELECT 'Herbs', 'bunch';

INSERT INTO Product (name, measurement)
SELECT 'Cucumber', 'kg';





INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Bruschetta', (SELECT id FROM Product WHERE name = 'Tomato'), (SELECT id FROM MenuItem WHERE name = 'Bruschetta'), 2;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Bruschetta', (SELECT id FROM Product WHERE name = 'Basil'), (SELECT id FROM MenuItem WHERE name = 'Bruschetta'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Bruschetta', (SELECT id FROM Product WHERE name = 'Bread'), (SELECT id FROM MenuItem WHERE name = 'Bruschetta'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Spaghetti Carbonara', (SELECT id FROM Product WHERE name = 'Spaghetti'), (SELECT id FROM MenuItem WHERE name = 'Spaghetti Carbonara'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Spaghetti Carbonara', (SELECT id FROM Product WHERE name = 'Pancetta'), (SELECT id FROM MenuItem WHERE name = 'Spaghetti Carbonara'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Fettuccine Alfredo', (SELECT id FROM Product WHERE name = 'Spaghetti'), (SELECT id FROM MenuItem WHERE name = 'Fettuccine Alfredo'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Insalata Caprese', (SELECT id FROM Product WHERE name = 'Tomato'), (SELECT id FROM MenuItem WHERE name = 'Insalata Caprese'), 2;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Insalata Caprese', (SELECT id FROM Product WHERE name = 'Mozzarella'), (SELECT id FROM MenuItem WHERE name = 'Insalata Caprese'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Margherita', (SELECT id FROM Product WHERE name = 'Tomato'), (SELECT id FROM MenuItem WHERE name = 'Margherita'), 2;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Margherita', (SELECT id FROM Product WHERE name = 'Mozzarella'), (SELECT id FROM MenuItem WHERE name = 'Margherita'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Frittura di Calamari', (SELECT id FROM Product WHERE name = 'Calamari'), (SELECT id FROM MenuItem WHERE name = 'Frittura di Calamari'), 3;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Frittura di Calamari', (SELECT id FROM Product WHERE name = 'Lemon'), (SELECT id FROM MenuItem WHERE name = 'Frittura di Calamari'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Ossobuco alla Milanese', (SELECT id FROM Product WHERE name = 'Veal Shanks'), (SELECT id FROM MenuItem WHERE name = 'Ossobuco alla Milanese'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Garlic Bread', (SELECT id FROM Product WHERE name = 'Bread'), (SELECT id FROM MenuItem WHERE name = 'Garlic Bread'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Garlic Bread', (SELECT id FROM Product WHERE name = 'Garlic'), (SELECT id FROM MenuItem WHERE name = 'Garlic Bread'), 2;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Caesar Salad', (SELECT id FROM Product WHERE name = 'Romaine Lettuce'), (SELECT id FROM MenuItem WHERE name = 'Caesar Salad'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Caesar Salad', (SELECT id FROM Product WHERE name = 'Bread'), (SELECT id FROM MenuItem WHERE name = 'Caesar Salad'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Lasagna', (SELECT id FROM Product WHERE name = 'Ground Beef'), (SELECT id FROM MenuItem WHERE name = 'Lasagna'), 2;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Lasagna', (SELECT id FROM Product WHERE name = 'Tomato'), (SELECT id FROM MenuItem WHERE name = 'Lasagna'), 2;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Pepperoni Pizza', (SELECT id FROM Product WHERE name = 'Tomato'), (SELECT id FROM MenuItem WHERE name = 'Pepperoni Pizza'), 2;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Pepperoni Pizza', (SELECT id FROM Product WHERE name = 'Mozzarella'), (SELECT id FROM MenuItem WHERE name = 'Pepperoni Pizza'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Pepperoni Pizza', (SELECT id FROM Product WHERE name = 'Pepperoni'), (SELECT id FROM MenuItem WHERE name = 'Pepperoni Pizza'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Garlic Shrimp', (SELECT id FROM Product WHERE name = 'Shrimp'), (SELECT id FROM MenuItem WHERE name = 'Garlic Shrimp'), 3;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Garlic Shrimp', (SELECT id FROM Product WHERE name = 'Garlic'), (SELECT id FROM MenuItem WHERE name = 'Garlic Shrimp'), 2;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Greek Salad', (SELECT id FROM Product WHERE name = 'Tomato'), (SELECT id FROM MenuItem WHERE name = 'Greek Salad'), 2;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Greek Salad', (SELECT id FROM Product WHERE name = 'Cucumber'), (SELECT id FROM MenuItem WHERE name = 'Greek Salad'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Greek Salad', (SELECT id FROM Product WHERE name = 'Feta Cheese'), (SELECT id FROM MenuItem WHERE name = 'Greek Salad'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'BBQ Ribs', (SELECT id FROM Product WHERE name = 'BBQ Sauce'), (SELECT id FROM MenuItem WHERE name = 'BBQ Ribs'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'BBQ Ribs', (SELECT id FROM Product WHERE name = 'Mixed Vegetables'), (SELECT id FROM MenuItem WHERE name = 'BBQ Ribs'), 2;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Lobster Bisque', (SELECT id FROM Product WHERE name = 'Lobster'), (SELECT id FROM MenuItem WHERE name = 'Lobster Bisque'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Berry Smoothie', (SELECT id FROM Product WHERE name = 'Berries'), (SELECT id FROM MenuItem WHERE name = 'Berry Smoothie'), 2;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Chicken Nuggets', (SELECT id FROM Product WHERE name = 'Chicken Pieces'), (SELECT id FROM MenuItem WHERE name = 'Chicken Nuggets'), 2;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Minestrone Soup', (SELECT id FROM Product WHERE name = 'Mixed Vegetables'), (SELECT id FROM MenuItem WHERE name = 'Minestrone Soup'), 2;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Minestrone Soup', (SELECT id FROM Product WHERE name = 'Pasta'), (SELECT id FROM MenuItem WHERE name = 'Minestrone Soup'), 1;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Beef Tacos', (SELECT id FROM Product WHERE name = 'Ground Beef'), (SELECT id FROM MenuItem WHERE name = 'Beef Tacos'), 2;

INSERT INTO Recipe (name, product_id, menuItem_id, quantity)
SELECT 'Beef Tacos', (SELECT id FROM Product WHERE name = 'Taco Shells'), (SELECT id FROM MenuItem WHERE name = 'Beef Tacos'), 3;



INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Alice', 'Smith', '456 Oak St', 9876543210);
INSERT INTO AppUser (firstName, lastName, email,customer_id, password) VALUES ('Alice', 'Smith', 'alice@example.com',1 ,'$2a$10$6U4gF1COk97/5xgXO1StAeKkQs7IuFWDuLWoDW188P45dSmcjosHS');
INSERT INTO AppUser_roles (AppUser_id,roles_id) VALUES (3,2);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Bob', 'Brown', '789 Pine St', 2345678901);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Charlie', 'Davis', '123 Elm St', 3456789012);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('David', 'Wilson', '456 Maple St', 4567890123);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Eve', 'Taylor', '789 Birch St', 5678901234);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Frank', 'Harris', '321 Cedar St', 6789012345);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Grace', 'Clark', '654 Spruce St', 7890123456);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Hank', 'Lee', '987 Walnut St', 8901234567);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Ivy', 'Walker', '159 Pine St', 9012345678);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Jack', 'Hall', '753 Oak St', 1234567890);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Karen', 'Young', '357 Elm St', 2345678901);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Leo', 'King', '951 Maple St', 3456789012);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Mia', 'Scott', '753 Birch St', 4567890123);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Nina', 'Adams', '159 Cedar St', 5678901234);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Oscar', 'Baker', '357 Spruce St', 6789012345);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Paul', 'Carter', '951 Walnut St', 7890123456);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Quinn', 'Evans', '753 Oak St', 8901234567);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Rose', 'Foster', '159 Pine St', 9012345678);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Sam', 'Gray', '357 Elm St', 1234567890);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Tina', 'Howard', '951 Maple St', 2345678901);
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Samuel', 'Ricci', '123 Main St', '5551234456');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Davide', 'Calabria', '476 Oak St', '5555678123');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Matteo', 'Politano', '189 Pine St', '5558765321');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Scamacca', 'Davis', '202 Birch St', '5558976789');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Totti', 'Wilson', '303 Cedar St', '5551289876');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Lorenzo', 'Miller', '404 Spruce St', '5557413456');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Chiellini', 'Garcia', '505 Elm St', '5558527890');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Buffon', 'Martinez', '606 Cherry St', '5559636543');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Luka', 'Rodriguez', '707 Walnut St', '5553693210');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Toni', 'Martinez', '808 Poplar St', '5552589087');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Federico', 'Clark', '909 Redwood St', '5557534567');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Veratti', 'Lewis', '111 Aspen St', '5559517654');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Ciro', 'Walker', '222 Palm St', '5551252345');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Nicolo', 'Zaniolo', '333 Cypress St', '5555898765');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Lorenzo', 'Pelegrini', '444 Fir St', '5559655432');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Sandro', 'Tonali', '555 Willow St', '5557857890');
INSERT INTO Customer (name, surname, address, phoneNumber) VALUES ('Moise', 'Kean', '666 Pine St', '5551482109');



INSERT INTO Visit (customer_id, date, startTime, endTime,tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Alice' AND surname = 'Smith'), '2024-06-20', '18:30:00', '20:30:00',1;

INSERT INTO Visit (customer_id, date, startTime, endTime,tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Bob' AND surname = 'Brown'), '2024-06-21', '18:30:00', '20:30:00',2;
/*
INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Charlie' AND surname = 'Davis'), '2024-06-22', '2024-06-22 19:00:00', '2024-06-22 21:00:00', 3;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'David' AND surname = 'Wilson'), '2024-06-23', '2024-06-23 18:00:00', '2024-06-23 20:00:00', 4;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Eve' AND surname = 'Taylor'), '2024-06-24', '2024-06-24 19:30:00', '2024-06-24 21:30:00', 5;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Frank' AND surname = 'Harris'), '2024-06-25', '2024-06-25 18:30:00', '2024-06-25 20:30:00', 6;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Grace' AND surname = 'Clark'), '2024-06-26', '2024-06-26 19:00:00', '2024-06-26 21:00:00', 7;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Hank' AND surname = 'Lee'), '2024-06-27', '2024-06-27 18:00:00', '2024-06-27 20:00:00', 8;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Ivy' AND surname = 'Walker'), '2024-06-28', '2024-06-28 19:30:00', '2024-06-28 21:30:00', 9;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Jack' AND surname = 'Hall'), '2024-06-29', '2024-06-29 18:30:00', '2024-06-29 20:30:00', 10;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Karen' AND surname = 'Young'), '2024-06-30', '2024-06-30 19:00:00', '2024-06-30 21:00:00', 11;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Leo' AND surname = 'King'), '2024-07-01', '2024-07-01 18:00:00', '2024-07-01 20:00:00', 12;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Mia' AND surname = 'Scott'), '2024-07-02', '2024-07-02 19:30:00', '2024-07-02 21:30:00', 13;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Nina' AND surname = 'Adams'), '2024-07-03', '2024-07-03 18:30:00', '2024-07-03 20:30:00', 14;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Oscar' AND surname = 'Baker'), '2024-07-04', '2024-07-04 19:00:00', '2024-07-04 21:00:00', 15;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Paul' AND surname = 'Carter'), '2024-07-05', '2024-07-05 18:00:00', '2024-07-05 20:00:00', 16;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Quinn' AND surname = 'Evans'), '2024-07-06', '2024-07-06 19:30:00', '2024-07-06 21:30:00', 17;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Rose' AND surname = 'Foster'), '2024-07-07', '2024-07-07 18:30:00', '2024-07-07 20:30:00', 18;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Sam' AND surname = 'Gray'), '2024-07-08', '2024-07-08 19:00:00', '2024-07-08 21:00:00', 19;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Tina' AND surname = 'Howard'), '2024-07-09', '2024-07-09 18:00:00', '2024-07-09 20:00:00', 20;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Samuel' AND surname = 'Ricci'), '2024-06-30', '2024-06-30 19:00:00', '2024-06-30 21:00:00', 1;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Davide' AND surname = 'Calabria'), '2024-07-01', '2024-07-01 18:30:00', '2024-07-01 20:30:00', 2;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Matteo' AND surname = 'Politano'), '2024-07-02', '2024-07-02 19:00:00', '2024-07-02 21:00:00', 3;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Scamacca' AND surname = 'Davis'), '2024-07-03', '2024-07-03 18:00:00', '2024-07-03 20:00:00', 4;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Totti' AND surname = 'Wilson'), '2024-07-04', '2024-07-04 19:30:00', '2024-07-04 21:30:00', 5;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Lorenzo' AND surname = 'Miller'), '2024-07-05', '2024-07-05 18:30:00', '2024-07-05 20:30:00', 6;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Chiellini' AND surname = 'Garcia'), '2024-07-06', '2024-07-06 19:00:00', '2024-07-06 21:00:00', 7;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Buffon' AND surname = 'Martinez'), '2024-07-07', '2024-07-07 18:00:00', '2024-07-07 20:00:00', 8;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Luka' AND surname = 'Rodriguez'), '2024-07-08', '2024-07-08 19:30:00', '2024-07-08 21:30:00', 9;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Toni' AND surname = 'Martinez'), '2024-07-09', '2024-07-09 18:30:00', '2024-07-09 20:30:00', 10;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Federico' AND surname = 'Clark'), '2024-07-10', '2024-07-10 19:00:00', '2024-07-10 21:00:00', 11;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Veratti' AND surname = 'Lewis'), '2024-07-11', '2024-07-11 18:00:00', '2024-07-11 20:00:00', 12;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Ciro' AND surname = 'Walker'), '2024-07-12', '2024-07-12 19:30:00', '2024-07-12 21:30:00', 13;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Nicolo' AND surname = 'Zaniolo'), '2024-07-13', '2024-07-13 18:30:00', '2024-07-13 20:30:00', 14;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Lorenzo' AND surname = 'Pelegrini'), '2024-07-14', '2024-07-14 19:00:00', '2024-07-14 21:00:00', 15;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Sandro' AND surname = 'Tonali'), '2024-07-15', '2024-07-15 18:00:00', '2024-07-15 20:00:00', 16;

INSERT INTO Visit (customer_id, date, startTime, endTime, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Moise' AND surname = 'Kean'), '2024-07-16', '2024-07-16 19:30:00', '2024-07-16 21:30:00', 17;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 1, (SELECT id FROM MenuItem WHERE name = 'Bruschetta'), '2023-06-15', 3;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 2, (SELECT id FROM MenuItem WHERE name = 'Bruschetta'), '2023-06-15', 2;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 3, (SELECT id FROM MenuItem WHERE name = 'Fettuccine Alfredo'), '2023-06-16', 1;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 4, (SELECT id FROM MenuItem WHERE name = 'Margherita'), '2023-06-16', 2;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 5, (SELECT id FROM MenuItem WHERE name = 'Ossobuco alla Milanese'), '2023-06-17', 1;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 6, (SELECT id FROM MenuItem WHERE name = 'Spaghetti Carbonara'), '2023-06-17', 3;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 7, (SELECT id FROM MenuItem WHERE name = 'Insalata Caprese'), '2023-06-18', 2;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 8, (SELECT id FROM MenuItem WHERE name = 'Lasagna'), '2023-06-18', 4;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 9, (SELECT id FROM MenuItem WHERE name = 'Ravioli'), '2023-06-19', 2;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 10, (SELECT id FROM MenuItem WHERE name = 'Lasagna'), '2023-06-19', 5;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 11, (SELECT id FROM MenuItem WHERE name = 'Minestrone'), '2023-06-20', 3;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 12, (SELECT id FROM MenuItem WHERE name = 'Gelato'), '2023-06-20', 4;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 13, (SELECT id FROM MenuItem WHERE name = 'Panna Cotta'), '2023-06-21', 1;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 14, (SELECT id FROM MenuItem WHERE name = 'Risotto'), '2023-06-21', 2;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 15, (SELECT id FROM MenuItem WHERE name = 'Polenta'), '2023-06-22', 3;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 16, (SELECT id FROM MenuItem WHERE name = 'Gnocchi'), '2023-06-22', 4;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 17, (SELECT id FROM MenuItem WHERE name = 'Arancini'), '2023-06-23', 2;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 18, (SELECT id FROM MenuItem WHERE name = 'Bistecca Fiorentina'), '2023-06-23', 1;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 19, (SELECT id FROM MenuItem WHERE name = 'Prosciutto e Melone'), '2023-06-24', 2;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 20, (SELECT id FROM MenuItem WHERE name = 'Caponata'), '2023-06-24', 3;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 21, (SELECT id FROM MenuItem WHERE name = 'Bruschetta'), '2024-06-30', 3;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 22, (SELECT id FROM MenuItem WHERE name = 'Fettuccine Alfredo'), '2024-07-01', 2;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 23, (SELECT id FROM MenuItem WHERE name = 'Margherita'), '2024-07-02', 1;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 24, (SELECT id FROM MenuItem WHERE name = 'Ossobuco alla Milanese'), '2024-07-03', 4;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 25, (SELECT id FROM MenuItem WHERE name = 'Spaghetti Carbonara'), '2024-07-04', 3;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 26, (SELECT id FROM MenuItem WHERE name = 'Insalata Caprese'), '2024-07-05', 1;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 27, (SELECT id FROM MenuItem WHERE name = 'Lasagna'), '2024-07-06', 5;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 28, (SELECT id FROM MenuItem WHERE name = 'Ravioli'), '2024-07-07', 2;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 29, (SELECT id FROM MenuItem WHERE name = 'Lasagna'), '2024-07-08', 3;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 30, (SELECT id FROM MenuItem WHERE name = 'Minestrone'), '2024-07-09', 4;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 31, (SELECT id FROM MenuItem WHERE name = 'Gelato'), '2024-07-10', 2;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 32, (SELECT id FROM MenuItem WHERE name = 'Minestrone'), '2024-07-11', 3;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 33, (SELECT id FROM MenuItem WHERE name = 'Risotto'), '2024-07-12', 1;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 34, (SELECT id FROM MenuItem WHERE name = 'Polenta'), '2024-07-13', 2;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 35, (SELECT id FROM MenuItem WHERE name = 'Margherita'), '2024-07-14', 3;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 36, (SELECT id FROM MenuItem WHERE name = 'Risotto'), '2024-07-15', 1;

INSERT INTO Orders (visit_id, menuItem_id, date, quantity)
SELECT 37, (SELECT id FROM MenuItem WHERE name = 'Bruschetta'), '2024-07-16', 2;
*/
INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Alice' AND surname = 'Smith'), '2024-07-01', '19:00:00', 1;
/*
INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Bob' AND surname = 'Brown'), '2024-07-01', '2024-07-01 19:00:00', 2;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Charlie' AND surname = 'Davis'), '2024-07-02', '2024-07-02 18:30:00', 3;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'David' AND surname = 'Wilson'), '2024-07-02', '2024-07-02 19:00:00', 4;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Eve' AND surname = 'Taylor'), '2024-07-03', '2024-07-03 18:00:00', 5;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Frank' AND surname = 'Harris'), '2024-07-03', '2024-07-03 19:30:00', 6;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Grace' AND surname = 'Clark'), '2024-07-04', '2024-07-04 18:00:00', 7;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Hank' AND surname = 'Lee'), '2024-07-04', '2024-07-04 19:30:00', 8;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Ivy' AND surname = 'Walker'), '2024-07-05', '2024-07-05 18:00:00', 9;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Jack' AND surname = 'Hall'), '2024-07-05', '2024-07-05 19:30:00', 10;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Karen' AND surname = 'Young'), '2024-07-06', '2024-07-06 18:00:00', 11;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Leo' AND surname = 'King'), '2024-07-06', '2024-07-06 19:30:00', 12;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Mia' AND surname = 'Scott'), '2024-07-07', '2024-07-07 18:00:00', 13;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Nina' AND surname = 'Adams'), '2024-07-07', '2024-07-07 19:30:00', 14;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Oscar' AND surname = 'Baker'), '2024-07-08', '2024-07-08 18:00:00', 15;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Paul' AND surname = 'Carter'), '2024-07-08', '2024-07-08 19:30:00', 16;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Quinn' AND surname = 'Evans'), '2024-07-09', '2024-07-09 18:00:00', 17;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Rose' AND surname = 'Foster'), '2024-07-09', '2024-07-09 19:30:00', 18;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Sam' AND surname = 'Gray'), '2024-07-09', '2024-07-09 19:30:00', 19;

INSERT INTO Reservation (customer_id, date, startAt, tables_id)
SELECT (SELECT id FROM Customer WHERE name = 'Tina' AND surname = 'Howard'), '2024-07-09', '2024-07-09 19:30:00', 20;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Samuel' AND surname = 'Ricci'), '2024-06-30', '2024-06-30 19:00:00', '2024-06-30 21:00:00', 21;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Davide' AND surname = 'Calabria'), '2024-07-01', '2024-07-01 18:30:00', '2024-07-01 20:30:00', 22;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Matteo' AND surname = 'Politano'), '2024-07-02', '2024-07-02 19:00:00', '2024-07-02 21:00:00', 23;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Scamacca' AND surname = 'Davis'), '2024-07-03', '2024-07-03 18:00:00', '2024-07-03 20:00:00', 24;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Totti' AND surname = 'Wilson'), '2024-07-04', '2024-07-04 19:30:00', '2024-07-04 21:30:00', 25;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Lorenzo' AND surname = 'Miller'), '2024-07-05', '2024-07-05 18:30:00', '2024-07-05 20:30:00', 26;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Chiellini' AND surname = 'Garcia'), '2024-07-06', '2024-07-06 19:00:00', '2024-07-06 21:00:00', 27;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Buffon' AND surname = 'Martinez'), '2024-07-07', '2024-07-07 18:00:00', '2024-07-07 20:00:00', 28;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Luka' AND surname = 'Rodriguez'), '2024-07-08', '2024-07-08 19:30:00', '2024-07-08 21:30:00', 29;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Toni' AND surname = 'Martinez'), '2024-07-09', '2024-07-09 18:30:00', '2024-07-09 20:30:00', 30;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Federico' AND surname = 'Clark'), '2024-07-10', '2024-07-10 19:00:00', '2024-07-10 21:00:00', 31;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Veratti' AND surname = 'Lewis'), '2024-07-11', '2024-07-11 18:00:00', '2024-07-11 20:00:00', 32;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Ciro' AND surname = 'Walker'), '2024-07-12', '2024-07-12 19:30:00', '2024-07-12 21:30:00', 33;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Nicolo' AND surname = 'Zaniolo'), '2024-07-13', '2024-07-13 18:30:00', '2024-07-13 20:30:00', 34;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Lorenzo' AND surname = 'Pelegrini'), '2024-07-14', '2024-07-14 19:00:00', '2024-07-14 21:00:00', 35;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Sandro' AND surname = 'Tonali'), '2024-07-15', '2024-07-15 18:00:00', '2024-07-15 20:00:00', 36;

INSERT INTO Reservation (customer_id, reservation_date, start_time, end_time, table_id)
SELECT (SELECT id FROM Customer WHERE name = 'Moise' AND surname = 'Kean'), '2024-07-16', '2024-07-16 19:30:00', '2024-07-16 21:30:00', 37;
*/