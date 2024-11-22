import java.awt.Graphics;

public class CircleDrawer {

    private static final int SCALE = 20;

    private void drawPixel(Graphics g, int x, int y, int offsetX, int offsetY) {
        int screenX = offsetX + x * SCALE;
        int screenY = offsetY - y * SCALE;
        g.fillRect(screenX, screenY, SCALE, SCALE);
    }

    public void drawBresenhamCircle(Graphics g, int xc, int yc, int radius, int offsetX, int offsetY) {
        int x = 0;
        int y = radius;
        int p = 3 - 2 * radius;

        drawCirclePoints(g, xc, yc, x, y, offsetX, offsetY);

        while (x <= y) {
            x++;
            if (p < 0) {
                p = p + 4 * x + 6;
            } else {
                y--;
                p = p + 4 * (x - y) + 10;
            }
            drawCirclePoints(g, xc, yc, x, y, offsetX, offsetY);
        }
    }

    private void drawCirclePoints(Graphics g, int xc, int yc, int x, int y, int offsetX, int offsetY) {
        drawPixel(g, xc + x, yc + y, offsetX, offsetY);
        drawPixel(g, xc - x, yc + y, offsetX, offsetY);
        drawPixel(g, xc + x, yc - y, offsetX, offsetY);
        drawPixel(g, xc - x, yc - y, offsetX, offsetY);
        drawPixel(g, xc + y, yc + x, offsetX, offsetY);
        drawPixel(g, xc - y, yc + x, offsetX, offsetY);
        drawPixel(g, xc + y, yc - x, offsetX, offsetY);
        drawPixel(g, xc - y, yc - x, offsetX, offsetY);
    }
}
