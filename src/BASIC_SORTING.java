import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BASIC_SORTING extends JPanel {
    private int[] data;
    private int currentIndex = -1;
    private int comparingIndex = -1;

    public BASIC_SORTING(int size) {
        if (size > 10) size = 10; // limit to 10
        data = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            data[i] = rand.nextInt(100) + 1; // values between 1 and 100
        }
        setPreferredSize(new Dimension(500, 300));
        new Thread(this::bubbleSort).start();
    }

    private void bubbleSort() {
        try {
            for (int i = 0; i < data.length - 1; i++) {
                for (int j = 0; j < data.length - i - 1; j++) {
                    currentIndex = j;
                    comparingIndex = j + 1;
                    repaint();
                    Thread.sleep(500);
                    if (data[j] > data[j + 1]) {
                        int temp = data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = temp;
                        repaint();
                        Thread.sleep(500);
                    }
                }
            }
            currentIndex = -1;
            comparingIndex = -1;
            repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth() / data.length;
        for (int i = 0; i < data.length; i++) {
            if (i == currentIndex || i == comparingIndex) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLUE);
            }
            int height = data[i] * 2;
            g.fillRect(i * width, getHeight() - height, width - 2, height);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(data[i]), i * width + width / 3, getHeight() - height - 5);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorting Visualizer - Bubble Sort");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SortingVisualizer(10));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
