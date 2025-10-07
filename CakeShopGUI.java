import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CakeShopGUI {
    // Cake and Topping data
    String[] cakes = {
        "Chocolate Truffle - ₹450.00",
        "Black Forest - ₹400.00",
        "Red Velvet - ₹500.00",
        "Pineapple - ₹350.00",
        "Butterscotch - ₹380.00"
    };
    double[] cakePrices = {450.0, 400.0, 500.0, 350.0, 380.0};

    String[] toppings = {
        "No Topping - ₹0.00",
        "Choco Chips - ₹50.00",
        "Whipped Cream - ₹40.00",
        "Cherries - ₹30.00",
        "Nuts - ₹60.00"
    };
    double[] toppingPrices = {0.0, 50.0, 40.0, 30.0, 60.0};

    double totalBill = 0.0;
    final double GST_RATE = 0.18; // 18% GST
    JTextArea cartArea;

    public CakeShopGUI() {
        JFrame frame = new JFrame("Sweet Treats Cake Shop");
        frame.setSize(550, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Title Label
        JLabel titleLabel = new JLabel("🍰 Welcome to Sweet Treats Cake Shop 🍰");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 22));
        titleLabel.setForeground(new Color(128, 0, 128)); // Purple
        frame.add(titleLabel);

        // Cake selection
        JLabel cakeLabel = new JLabel("Select Cake:");
        JComboBox<String> cakeBox = new JComboBox<>(cakes);

        // Topping selection
        JLabel toppingLabel = new JLabel("Select Topping:");
        JComboBox<String> toppingBox = new JComboBox<>(toppings);

        // Quantity selection
        JLabel qtyLabel = new JLabel("Quantity:");
        JSpinner qtySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

        // Buttons and cart area
        JButton addButton = new JButton("Add to Cart");
        cartArea = new JTextArea(10, 45);
        cartArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartArea);

        JButton billButton = new JButton("Show Final Bill");

        // Add to cart action
        addButton.addActionListener(e -> {
            int cakeIndex = cakeBox.getSelectedIndex();
            int toppingIndex = toppingBox.getSelectedIndex();
            int qty = (Integer) qtySpinner.getValue();

            double cost = (cakePrices[cakeIndex] + toppingPrices[toppingIndex]) * qty;
            totalBill += cost;

            cartArea.append(qty + " x " + cakes[cakeIndex].split(" -")[0] +
                    " with " + toppings[toppingIndex].split(" -")[0] +
                    " → ₹" + String.format("%.2f", cost) + "\n");
        });

        // Show bill action with GST
        billButton.addActionListener(e -> {
            double gstAmount = totalBill * GST_RATE;
            double finalAmount = totalBill + gstAmount;

            cartArea.append("\n============================\n");
            cartArea.append("🧁 Sweet Treats Cake Shop 🧁\n");
            cartArea.append(String.format("Subtotal: ₹%.2f\n", totalBill));
            cartArea.append(String.format("GST (18%%): ₹%.2f\n", gstAmount));
            cartArea.append(String.format("Final Bill: ₹%.2f\n", finalAmount));
            cartArea.append("Thank you for visiting Sweet Treats!\n");
            cartArea.append("============================\n");
        });

        // Add components to frame
        frame.add(cakeLabel);
        frame.add(cakeBox);
        frame.add(toppingLabel);
        frame.add(toppingBox);
        frame.add(qtyLabel);
        frame.add(qtySpinner);
        frame.add(addButton);
        frame.add(scrollPane);
        frame.add(billButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CakeShopGUI();
    }
}