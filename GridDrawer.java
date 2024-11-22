import java.awt.Graphics;
import java.awt.Color;

public class GridDrawer {

    private static final int GRID_SIZE = 20;

    public static void drawGrid(Graphics g, int width, int height) {
        g.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i < width; i += GRID_SIZE) {
            g.drawLine(i, 0, i, height);
        }

        for (int i = 0; i < height; i += GRID_SIZE) {
            g.drawLine(0, i, width, i);
        }

        // Оси
        g.setColor(Color.BLACK);

        int shiftedOffsetY = height / 2 + GRID_SIZE;

        g.drawLine(width / 2, 0, width / 2, height);
        g.drawLine(0, shiftedOffsetY, width, shiftedOffsetY);
    }
}
