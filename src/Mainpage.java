import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mainpage extends JFrame implements ActionListener {
    private JButton qk, mrq, is;

    public Mainpage() {
        // Initialize the buttons
        qk = new JButton("Quick Sort");
        mrq = new JButton("Merge Sort");
        is = new JButton("Insertion Sort");

        // Set frame properties
        setTitle("Sorting Visualizer");
        setSize(800, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icon/sort1.png"));
        Image i2 = i1.getImage().getScaledInstance(400,350,Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,7,400,350);
        add(image);

        // Configure buttons
        qk.setBounds(580, 100, 120, 20);
        qk.addActionListener(this);
//        qk.setBackground(Color.white);
//        qk.setForeground(Color.black);

        mrq.setBounds(580, 150, 120, 20);
        mrq.addActionListener(this);

        is.setBounds(580, 200, 120, 20);
        is.addActionListener(this);

        JLabel lb= new JLabel("Made By: Chaman Sinha");
        JLabel lb1= new JLabel("Aryan Sharma");
        JLabel lb2= new JLabel("Tanmay Malav");
        JLabel lb3= new JLabel("Jeet");
        JLabel lb4= new JLabel("Pranav");
        lb.setBounds(600,250,150,30);
        lb1.setBounds(660,263,150,30);
        lb2.setBounds(660,277,150,30);
        lb3.setBounds(660,290,150,30);
        lb4.setBounds(660,303,150,30);
        add(lb);
        add(lb1);
        add(lb2);
        add(lb3);
        add(lb4);

        // Add buttons to the frame
        add(qk);
        add(mrq);
        add(is);

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
        }else if (e.getSource() == is) {
            // Open the Merge Sort visualizer and hide the main page
            new VisualInsertionSort();
            setVisible(false);
        }
    }
}
