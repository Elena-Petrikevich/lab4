import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Main extends JPanel {

     double angle = 0;
     Point2D centerGravity;
     Polygon polygon;

    public Main() {
        int[] xPoints = {80, 50, -40, -60};
        int[] yPoints = {20, -50, -70, 100};
        polygon = new Polygon(xPoints, yPoints, 4);


        centerGravity = CenterOfGravity(xPoints, yPoints);

        Timer timer = new Timer(50, e -> {
            angle += 0.07;
            repaint();
        });
        timer.start();
    }

    Point2D CenterOfGravity(int[] x, int[] y) {
        double cx1 = (x[0] + x[1] + x[2]) / 3.0;
        double cy1 = (y[0] + y[1] + y[2]) / 3.0;

        double cx2 = (x[0] + x[2] + x[3]) / 3.0;
        double cy2 = (y[0] + y[2] + y[3]) / 3.0;

        double cx3 = (x[1] + x[0] + x[3]) / 3.0;
        double cy3 = (y[1] + y[0] + y[3]) / 3.0;

        double cx4 = (x[1] + x[3] + x[2]) / 3.0;
        double cy4 = (y[1] + y[3] + y[2]) / 3.0;


        return new Point2D.Double((cx1 + cx2 + cx3 + cx4) / 4, (cy1 + cy2 + cy3 + cy4) / 4);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g.translate(150, 150);

        AffineTransform transform = AffineTransform.getRotateInstance(angle, centerGravity.getX(), centerGravity.getY());
        g2d.transform(transform);

        g2d.draw(polygon);
    }

    public static void main(String[] args) {
        JFrame fr = new JFrame("Вращение четырёхугольника");
        fr.add(new Main());
        fr.setSize(300, 300);
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);

    }
}
