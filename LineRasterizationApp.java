import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineRasterizationApp extends JFrame {
    private JTextField x1Field, y1Field, x2Field, y2Field;
    private JComboBox<String> algorithmComboBox;
    private JPanel drawingPanel;
    private LineDrawer lineDrawer;
    private CircleDrawer circleDrawer;

    public LineRasterizationApp() {
        setTitle("Rasterization Algorithms");
        setSize(855, 598);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        lineDrawer = new LineDrawer();
        circleDrawer = new CircleDrawer();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 4));
        inputPanel.add(new JLabel("x1:"));
        x1Field = new JTextField(5);
        inputPanel.add(x1Field);
        inputPanel.add(new JLabel("y1:"));
        y1Field = new JTextField(5);
        inputPanel.add(y1Field);
        inputPanel.add(new JLabel("x2:"));
        x2Field = new JTextField(5);
        inputPanel.add(x2Field);
        inputPanel.add(new JLabel("y2:"));
        y2Field = new JTextField(5);
        inputPanel.add(y2Field);

        algorithmComboBox = new JComboBox<>(new String[]{
                "Step-by-Step", "DDA", "Bresenham (Line)", "Bresenham (Circle)"
        });
        inputPanel.add(algorithmComboBox);

        JButton drawButton = new JButton("Draw");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawLine();
            }
        });
        inputPanel.add(drawButton);

        JButton cleanButton = new JButton("Clean");
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearDrawing();
            }
        });
        inputPanel.add(cleanButton);

        add(inputPanel, BorderLayout.NORTH);

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GridDrawer.drawGrid(g, getWidth(), getHeight());
            }
        };
        add(drawingPanel, BorderLayout.CENTER);
    }

    private void drawLine() {
        try {
            int x1 = Integer.parseInt(x1Field.getText());
            int y1 = Integer.parseInt(y1Field.getText());
            int x2 = Integer.parseInt(x2Field.getText());
            int y2 = Integer.parseInt(y2Field.getText());

            String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
            Graphics g = drawingPanel.getGraphics();

            int offsetX = drawingPanel.getWidth() / 2;
            int offsetY = drawingPanel.getHeight() / 2;

            switch (selectedAlgorithm) {
                case "Step-by-Step":
                    lineDrawer.drawStepByStep(g, x1, y1, x2, y2, offsetX, offsetY);
                    break;
                case "DDA":
                    lineDrawer.drawDDA(g, x1, y1, x2, y2, offsetX, offsetY);
                    break;
                case "Bresenham (Line)":
                    lineDrawer.drawBresenhamLine(g, x1, y1, x2, y2, offsetX, offsetY);
                    break;
                case "Bresenham (Circle)":
                    circleDrawer.drawBresenhamCircle(g, x1, y1, Math.abs(x2 - x1), offsetX, offsetY);
                    break;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid coordinates.");
        }
    }

    private void clearDrawing() {
        Graphics g = drawingPanel.getGraphics();
        g.clearRect(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());
        GridDrawer.drawGrid(g, drawingPanel.getWidth(), drawingPanel.getHeight());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LineRasterizationApp().setVisible(true));
    }
}
