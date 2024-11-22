import java.awt.Graphics;

public class LineDrawer {

    public static final int SCALE = 20;

    private void drawPixel(Graphics g, int x, int y, int offsetX, int offsetY) {
        int screenX = offsetX + x * SCALE;
        int screenY = offsetY - y * SCALE;
        g.fillRect(screenX, screenY, SCALE, SCALE);
    }

    public void drawStepByStep(Graphics g, int x1, int y1, int x2, int y2, int offsetX, int offsetY) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            drawPixel(g, Math.round(x), Math.round(y), offsetX, offsetY);
            x += xIncrement;
            y += yIncrement;
        }
    }

    public void drawDDA(Graphics g, int x1, int y1, int x2, int y2, int offsetX, int offsetY) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = dx / (float) steps;
        float yIncrement = dy / (float) steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            drawPixel(g, Math.round(x), Math.round(y), offsetX, offsetY);
            x += xIncrement;
            y += yIncrement;
        }
    }

    public void drawBresenhamLine(Graphics g, int x1, int y1, int x2, int y2, int offsetX, int offsetY) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            drawPixel(g, x1, y1, offsetX, offsetY);
            if (x1 == x2 && y1 == y2) break;
            int e2 = err * 2;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }
}
