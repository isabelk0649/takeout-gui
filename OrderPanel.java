package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    private Menu menu = null;
    private final Order order = new Order();
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("Subtotal: $0.00");
    private final JLabel taxLabel = new JLabel("Tax: $0.00");
    private final JLabel totalLabel = new JLabel("Total: $0.00");

    private JLabel nameLabels[] = null;
    private JLabel priceLabels[] = null;
    private JTextField quantityFields[] = null;

    public OrderPanel(final Menu menu) {
        this.menu = menu;
        this.nameLabels = new JLabel[this.menu.size()];
        this.priceLabels = new JLabel[this.menu.size()];
        this.quantityFields = new JTextField[this.menu.size()];
        this.layoutView();
        this.registerListeners();
    }

    private class PrintListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            // Call the print method in your OrderPanel class to print the order
            // You might want to implement a print method in OrderPanel or Order class
            // to handle the printing logic.
            printOrder();
        }
    }

    private class QuantityListener implements FocusListener {
        private MenuItem item = null;

        QuantityListener(final MenuItem item) {
            this.item = item;
        }

        @Override
        public void focusGained(FocusEvent e) {
            // Not needed for this example
        }

        @Override
        public void focusLost(FocusEvent e) {
            JTextField source = (JTextField) e.getSource();
            try {
                int quantity = Integer.parseInt(source.getText());
                if (quantity < 0) {
                    quantity = 0;
                }
                order.update(item, quantity);
                updateLabels();
            } catch (NumberFormatException ex) {
                // Handle the case where the input is not a valid integer
                source.setText("0");
            }
        }
    }

    private void layoutView() {
        this.setLayout(new GridLayout(menu.size() + 5, 3));

        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            nameLabels[i] = new JLabel(menuItem.getName());
            priceLabels[i] = new JLabel(String.format("$%.2f", menuItem.getPrice()));
            quantityFields[i] = new JTextField("0");
            quantityFields[i].addFocusListener(new QuantityListener(menuItem));

            this.add(nameLabels[i]);
            this.add(priceLabels[i]);
            this.add(quantityFields[i]);
        }

        this.add(new JLabel("")); // Empty space
        this.add(printButton);
        this.add(new JLabel("")); // Empty space
        this.add(subtotalLabel);
        this.add(taxLabel);
        this.add(totalLabel);
    }

    private void updateLabels() {
        subtotalLabel.setText("Subtotal: $" + order.getSubTotal());
        taxLabel.setText("Tax: $" + order.getTaxes());
        totalLabel.setText("Total: $" + order.getTotal());
    }

    private void printOrder() {
        // Implement the logic to print the order.
        // You might want to use a PrinterJob or any other printing mechanism.
        // This depends on your specific requirements.
        // Example:
        System.out.println(order.toString());
    }

    private void registerListeners() {
        this.printButton.addActionListener(new PrintListener());
    }
}
