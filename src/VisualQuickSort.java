//Best till now

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualQuickSort extends JFrame {
    private static final int BLOCK_SIZE = 50;
    private static final int DELAY = 500; // milliseconds
    private int[] array;
    private JLabel[] labels;
    private JTextField inputField;
    private JButton startButton;
    private JButton infoButton;
    private JButton mergeSort;
    private JPanel inputPanel;
    private JPanel sortingPanel;

    public VisualQuickSort() {
        setTitle("Visual Quick Sort");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Set up the input field and buttons
        inputField = new JTextField(20);
        startButton = new JButton("Quick Sort");
        infoButton = new JButton("Show Info");
        mergeSort = new JButton("Merge Sort");
        inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter numbers separated by commas:"));
        inputPanel.add(inputField);
        inputPanel.add(startButton);
        inputPanel.add(infoButton);
        inputPanel.add(mergeSort);
        add(inputPanel, BorderLayout.NORTH);

        // Set up the sorting panel
        sortingPanel = new JPanel();
        sortingPanel.setLayout(null);
        add(sortingPanel, BorderLayout.CENTER);

        mergeSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualMergeSort mergeSortFrame = new VisualMergeSort();
                mergeSortFrame.setVisible(true);
                setVisible(false);
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                String[] inputNumbers = input.split(",");
                array = new int[inputNumbers.length];
                labels = new JLabel[inputNumbers.length];

                int panelWidth = sortingPanel.getWidth();
                int totalBlocksWidth = inputNumbers.length * BLOCK_SIZE;
                int startX = (panelWidth - totalBlocksWidth) / 2;

                for (int i = 0; i < inputNumbers.length; i++) {
                    array[i] = Integer.parseInt(inputNumbers[i].trim());
                    labels[i] = new JLabel(String.valueOf(array[i]), SwingConstants.CENTER);
                    labels[i].setOpaque(true);
                    labels[i].setBackground(Color.CYAN);
                    labels[i].setBorder(new LineBorder(Color.BLACK)); // Add border to each block
                    labels[i].setBounds(startX + i * BLOCK_SIZE, 100, BLOCK_SIZE, BLOCK_SIZE);
                    sortingPanel.add(labels[i]);
                }
                sortingPanel.revalidate();
                sortingPanel.repaint();
                startSorting();
            }
        });

        // Set up the info button action
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInfoDialog();
            }
        });
    }

    private void showInfoDialog() {
        String info = "<html><body>"
                + "<h2>Quick Sort Algorithm</h2>"
                + "<p>Quick Sort is a highly efficient sorting algorithm that uses a divide-and-conquer approach to sort elements.</p>"
                + "<h3>Time Complexity:</h3>"
                + "<ul>"
                + "<li>Best Case: O(n log n)</li>"
                + "<li>Average Case: O(n log n)</li>"
                + "<li>Worst Case: O(n^2)</li>"
                + "</ul>"
                + "<h3>Space Complexity:</h3>"
                + "<ul>"
                + "<li>O(log n) due to the recursive stack.</li>"
                + "</ul>"
                + "</body></html>";

        JOptionPane.showMessageDialog(this, info, "Quick Sort Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void startSorting() {
        new Thread(() -> {
            try {
                quickSort(array, 0, array.length - 1);
                highlightSorted();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void quickSort(int[] arr, int low, int high) throws InterruptedException {
        if (low < high) {
            int partitionIndex = partition(arr, low, high);
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) throws InterruptedException {
        int pivot = arr[high];
        int i = low - 1;

        labels[high].setBackground(Color.RED); // Highlight pivot
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
                updateLabels();
                Thread.sleep(DELAY);
            }
        }
        swap(arr, i + 1, high);
        updateLabels();
        Thread.sleep(DELAY);
        labels[high].setBackground(Color.CYAN); // Reset pivot color
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        // Swap labels
        String tempText = labels[i].getText();
        labels[i].setText(labels[j].getText());
        labels[j].setText(tempText);
    }

    private void updateLabels() {
        int panelWidth = sortingPanel.getWidth();
        int totalBlocksWidth = array.length * BLOCK_SIZE;
        int startX = (panelWidth - totalBlocksWidth) / 2;

        for (int i = 0; i < array.length; i++) {
            labels[i].setBounds(startX + i * BLOCK_SIZE, 100, BLOCK_SIZE, BLOCK_SIZE);
        }
        sortingPanel.revalidate();
        sortingPanel.repaint();
    }

    private void highlightSorted() {
        for (JLabel label : labels) {
            label.setBackground(Color.GREEN);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VisualQuickSort frame = new VisualQuickSort();
            frame.setVisible(true);
        });
    }
}
