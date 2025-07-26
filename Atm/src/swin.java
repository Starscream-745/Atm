import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class swin {

    private JFrame frame;
    private JPanel panel;
    private ArrayList<User> users;
    private User currentUser;

    public swin() {
        users = new ArrayList<>();
        users.add(new User(23212323, 19222, 1234));
        users.add(new User(32467532, 100200, 5678));
        users.add(new User(23962467, 2001200, 9898));
        users.add(new User(23566542, 19000010, 1224));

        showLoginScreen();
    }

    public void showLoginScreen() {
        frame = new JFrame("ATM Login");
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel accLabel = new JLabel("Account Number:");
        JTextField accField = new JTextField();

        JLabel pinLabel = new JLabel("PIN:");
        JPasswordField pinField = new JPasswordField();

        JButton loginBtn = new JButton("Login");
        JLabel status = new JLabel("");

        loginBtn.addActionListener(e -> {
            int acc = Integer.parseInt(accField.getText());
            int pin = Integer.parseInt(String.valueOf(pinField.getPassword()));
            for (User u : users) {
                if (u.getAccountNumber() == acc && u.getPin() == pin) {
                    currentUser = u;
                    frame.dispose();
                    showMenu();
                    return;
                }
            }
            status.setText("Login failed. Try again.");
        });

        panel.add(accLabel); panel.add(accField);
        panel.add(pinLabel); panel.add(pinField);
        panel.add(loginBtn); panel.add(status);

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void showMenu() {
        JFrame menuFrame = new JFrame("ATM Menu");
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton checkBal = new JButton("Check Balance");
        JButton deposit = new JButton("Deposit");
        JButton withdraw = new JButton("Withdraw");
        JButton exit = new JButton("Exit");

        checkBal.addActionListener(e ->
                JOptionPane.showMessageDialog(null, "Balance: ₹" + currentUser.getBalance())
        );

        deposit.addActionListener(e -> {
            String amt = JOptionPane.showInputDialog("Enter amount to deposit:");
            int amount = Integer.parseInt(amt);
            currentUser.setBalance(currentUser.getBalance() + amount);
            JOptionPane.showMessageDialog(null, "Deposited ₹" + amount);
        });

        withdraw.addActionListener(e -> {
            String amt = JOptionPane.showInputDialog("Enter amount to withdraw:");
            int amount = Integer.parseInt(amt);
            if (amount <= currentUser.getBalance()) {
                currentUser.setBalance(currentUser.getBalance() - amount);
                JOptionPane.showMessageDialog(null, "Withdrew ₹" + amount);
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient Balance!");
            }
        });

        exit.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Thank you for using the ATM.");
            menuFrame.dispose();
        });

        menuPanel.add(checkBal);
        menuPanel.add(deposit);
        menuPanel.add(withdraw);
        menuPanel.add(exit);

        menuFrame.add(menuPanel);
        menuFrame.setSize(300, 200);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new swin();
    }
}
