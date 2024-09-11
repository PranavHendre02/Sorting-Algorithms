import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mainpage extends JFrame implements ActionListener {
    private JButton qk, mrq;

    public Mainpage() {
        // Initialize the buttons
        qk = new JButton("Quick Sort");
        mrq = new JButton("Merge Sort");

        // Set frame properties
        setTitle("Sorting Visualizer");
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Configure buttons
        qk.setBounds(820, 50, 100, 20);
        qk.addActionListener(this);

        mrq.setBounds(820, 100, 100, 20);
        mrq.addActionListener(this);

        // Add buttons to the frame
        add(qk);
        add(mrq);

        // Display the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Mainpage::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == qk) {
            // Open the Quick Sort visualizer and hide the main page
            new VisualQuickSort();
            setVisible(false);
        } else if (e.getSource() == mrq) {
            // Open the Merge Sort visualizer and hide the main page
            new VisualMergeSort();
            setVisible(false);
        }
    }
}
