import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PolygonFillDemo extends Frame implements MouseListener, KeyListener {

    ArrayList<Point> points = new ArrayList<>();
    boolean scanlineFillActive = false;
    boolean floodFillActive = false;
    boolean boundaryFillActive = false;

    int width = 500, height = 500;
    int[][] pixels = new int[width][height]; // 0=empty, 1=boundary, 2=filled(scanline), 3=filled(flood), 4=filled(boundary)

    public PolygonFillDemo() {
        super("Polygon Fill Demo - Draw polygon by clicking points\n" +
                "Press F: Scanline fill | 1: Flood fill | 2: Boundary fill | R: Reset");
        setSize(width, height);
        setLocationRelativeTo(null);
        addMouseListener(this);
        addKeyListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Draw polygon edges and fill pixels boundary array for flood and boundary fill
    private void drawPolygonBoundary() {
        // Reset pixels array
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                pixels[x][y] = 0;

        int n = points.size();
        for (int i = 0; i < n; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % n);
            drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    // Bresenham line algorithm to mark boundary pixels
    private void drawLine(int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0), sx = x0 < x1 ? 1 : -1;
        int dy = -Math.abs(y1 - y0), sy = y0 < y1 ? 1 : -1;
        int err = dx + dy;

        while (true) {
            if (x0 >= 0 && x0 < width && y0 >= 0 && y0 < height)
                pixels[x0][y0] = 1; // boundary

            if (x0 == x1 && y0 == y1) break;
            int e2 = 2 * err;
            if (e2 >= dy) {
                err += dy;
                x0 += sx;
            }
            if (e2 <= dx) {
                err += dx;
                y0 += sy;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        // Clear background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // Draw polygon edges
        g.setColor(Color.BLACK);
        if (points.size() > 1) {
            for (int i = 0; i < points.size(); i++) {
                Point p1 = points.get(i);
                Point p2 = points.get((i + 1) % points.size());
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }

        // Draw filled pixels for scanline fill (2), flood fill (3), boundary fill (4)
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                switch (pixels[x][y]) {
                    case 1:
                        g.setColor(Color.BLACK);
                        g.drawLine(x, y, x, y);
                        break;
                    case 2:
                        g.setColor(Color.ORANGE); // scanline fill color
                        g.drawLine(x, y, x, y);
                        break;
                    case 3:
                        g.setColor(Color.RED); // flood fill color
                        g.drawLine(x, y, x, y);
                        break;
                    case 4:
                        g.setColor(Color.BLUE); // boundary fill color
                        g.drawLine(x, y, x, y);
                        break;
                }
            }
        }
    }

    // Scanline fill algorithm
    private void scanlineFill() {
        int n = points.size();
        if (n < 3) return;

        int[] px = new int[n];
        int[] py = new int[n];
        for (int i = 0; i < n; i++) {
            px[i] = points.get(i).x;
            py[i] = points.get(i).y;
        }

        int ymin = py[0], ymax = py[0];
        for (int i = 1; i < n; i++) {
            if (py[i] < ymin) ymin = py[i];
            if (py[i] > ymax) ymax = py[i];
        }

        for (int y = ymin; y <= ymax; y++) {
            ArrayList<Integer> nodes = new ArrayList<>();

            int j = n - 1;
            for (int i = 0; i < n; i++) {
                if ((py[i] < y && py[j] >= y) || (py[j] < y && py[i] >= y)) {
                    int x = px[i] + (y - py[i]) * (px[j] - px[i]) / (py[j] - py[i]);
                    nodes.add(x);
                }
                j = i;
            }

            nodes.sort(Integer::compareTo);

            for (int i = 0; i + 1 < nodes.size(); i += 2) {
                int xStart = nodes.get(i);
                int xEnd = nodes.get(i + 1);
                for (int x = xStart; x <= xEnd; x++) {
                    if (x >= 0 && x < width && y >= 0 && y < height && pixels[x][y] != 1) {
                        pixels[x][y] = 2; // fill color for scanline
                    }
                }
            }
        }
    }

    // Flood fill (4-connected)
    private void floodFill(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) return;
        if (pixels[x][y] != 0) return;

        pixels[x][y] = 3; // flood fill color

        floodFill(x + 1, y);
        floodFill(x - 1, y);
        floodFill(x, y + 1);
        floodFill(x, y - 1);
    }

    // Boundary fill (4-connected)
    private void boundaryFill(int x, int y, int fillColor, int boundaryColor) {
        if (x < 0 || x >= width || y < 0 || y >= height) return;
        if (pixels[x][y] == boundaryColor || pixels[x][y] == fillColor) return;

        pixels[x][y] = fillColor;

        boundaryFill(x + 1, y, fillColor, boundaryColor);
        boundaryFill(x - 1, y, fillColor, boundaryColor);
        boundaryFill(x, y + 1, fillColor, boundaryColor);
        boundaryFill(x, y - 1, fillColor, boundaryColor);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (!scanlineFillActive && !floodFillActive && !boundaryFillActive) {
            // Add points for polygon
            points.add(new Point(x, y));
            repaint();
        } else if (floodFillActive) {
            floodFill(x, y);
            floodFillActive = false;
            repaint();
        } else if (boundaryFillActive) {
            boundaryFill(x, y, 4, 1);
            boundaryFillActive = false;
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_F) { // Scanline fill
            if (points.size() >= 3) {
                drawPolygonBoundary();
                scanlineFill();
                scanlineFillActive = true;
                floodFillActive = false;
                boundaryFillActive = false;
                repaint();
            }
        } else if (code == KeyEvent.VK_1) { // Flood fill mode
            if (points.size() >= 3) {
                drawPolygonBoundary();
                scanlineFillActive = false;
                floodFillActive = true;
                boundaryFillActive = false;
                repaint();
                System.out.println("Flood fill: Click inside polygon to fill.");
            }
        } else if (code == KeyEvent.VK_2) { // Boundary fill mode
            if (points.size() >= 3) {
                drawPolygonBoundary();
                scanlineFillActive = false;
                floodFillActive = false;
                boundaryFillActive = true;
                repaint();
                System.out.println("Boundary fill: Click inside polygon to fill.");
            }
        } else if (code == KeyEvent.VK_R) { // Reset polygon and fills
            points.clear();
            scanlineFillActive = false;
            floodFillActive = false;
            boundaryFillActive = false;
            // Clear pixels
            for (int i = 0; i < width; i++)
                for (int j = 0; j < height; j++)
                    pixels[i][j] = 0;
            repaint();
            System.out.println("Reset polygon.");
        }
    }

    // Unused interface methods
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        new PolygonFillDemo();
    }
}