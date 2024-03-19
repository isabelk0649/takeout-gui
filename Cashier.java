package cp213;

import java.util.Scanner;
/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-09-06
 */
public class Cashier {

    // Attributes
    private Menu menu = null;

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }

    /**
     * Prints the menu.
     */
    private void printCommands() {
	System.out.println("\nMenu:");
	System.out.println(menu.toString());
	System.out.println("Press 0 when done.");
	System.out.println("Press any other key to see the menu again.\n");
    }

    private void printOrderSummary(Order order) {
        System.out.println("\nOrder Summary:");
        System.out.println(order.toString());
    }

    private int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter menu number: ");
        return scanner.nextInt();
    }

    public Order takeOrder() {
        System.out.println("Welcome to WLU Foodorama!");

        Order order = new Order();
        boolean done = false;

        do {
            printCommands();
            int menuNumber = getUserInput();

            if (menuNumber == 0) {
                done = true;
            } else if (menuNumber > 0 && menuNumber <= menu.size()) {
                MenuItem menuItem = menu.getItem(menuNumber - 1);
                int quantity = askForQuantity();

                if (quantity > 0) {
                    order.add(menuItem, quantity);
                }
            } else {
                System.out.println("Invalid menu number. Please try again.");
            }

        } while (!done);

        printOrderSummary(order);

        return order;
    }

    private int askForQuantity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter quantity: ");
        return scanner.nextInt();
    }
}