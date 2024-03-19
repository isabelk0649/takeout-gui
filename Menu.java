package cp213;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 *
 */
public class Menu {

    // Attributes.
    // define a List of MenuItem objects

    private List<MenuItem> items;

    /**
     * Creates a new Menu from an existing Collection of MenuItems. MenuItems are
     * copied into the Menu List.
     *
     * @param items an existing Collection of MenuItems.
     */
    public Menu(Collection<MenuItem> items) {

		this.items = new ArrayList<>(items);

    }

    /**
     * Constructor from a Scanner of MenuItem strings. Each line in the Scanner
     * corresponds to a MenuItem. You have to read the Scanner line by line and addc
     * each MenuItem to the List of items.
     *
     * @param fileScanner A Scanner accessing MenuItem String data.
     */
    public Menu(Scanner fileScanner) {

        this.items = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
        	
            String line = fileScanner.nextLine();
            MenuItem menuItem = parseMenuItem(line);
            
            if (menuItem != null) {
                items.add(menuItem);
            }
        }

    }
    
    private MenuItem parseMenuItem(String line) {
    	
        String[] parts = line.split("\\s+", 2);

        if (parts.length == 2) {
        	
            try {
            	
                double price = Double.parseDouble(parts[0]);
                String name = parts[1].trim();
                return new MenuItem(name, price);
                
            } catch (NumberFormatException e) {
                System.err.println("Error parsing price for line: " + line);
            }
            
        } else {
            System.err.println("Invalid line format: " + line);
        }

        return null;
    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {

	return items.get(i);
    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {


	return items.size();
    }

    /**
     * Returns the Menu items as a String in the format:
     *
     * <pre>
    5) poutine      $ 3.75
    6) pizza        $10.00
     * </pre>
     *
     * where n) is the index + 1 of the MenuItems in the List.
     */
    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < items.size(); i++) {
            MenuItem menuItem = items.get(i);
            String formattedItem = String.format("%d) %-12s $%5.2f%n", (i + 1), menuItem.getName(), menuItem.getPrice());
            result.append(formattedItem);
        }

        return result.toString();
    }
}
