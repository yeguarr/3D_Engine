import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Viewer3D extends JComponent {
    private List<Shape3D> drawShapes;
    private List<Object3D> objects;
    private Camera camera;

    public Viewer3D(ControlsGUI controlsGUI) {
        addMouseListener(controlsGUI);
        addMouseMotionListener(controlsGUI);
        addKeyListener(controlsGUI);
        this.camera = controlsGUI.getCamera();
        this.objects = new LinkedList<>();
        this.drawShapes = new LinkedList<>();
    }

    void addObject3D(Object3D object3D) {
        this.objects.add(object3D);
    }

    int countShapes() {
        int count = 0;
        for(Object3D ob : objects) {
            count += ob.getFacesNumber();
        }
        return count;
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.BLACK);
        Rectangle2D background = new Rectangle2D.Float();
        background.setFrame(0,0,this.getBounds().width,this.getBounds().height);
        g2d.fill(background);

        g2d.setPaint(Color.WHITE);
        for (Shape3D shape : drawShapes) {
            g2d.fillPolygon(shape.getPolygon());
        }
    }

    public void updateComponent() {
        drawShapes.clear();

        Matrix.f4x4 viewerTransform = Utils.projection(90, 1, 1000, 0.1f).multiply(camera.getTransformMatrix());
        viewerTransform = Utils.scale(100, 100, 100).multiply(viewerTransform);
        viewerTransform = Utils.move((float)(this.getBounds().width / 2.), (float)(this.getBounds().height / 2.), 0).multiply(viewerTransform);

        for (Object3D obj : objects) {
            Matrix.f4x4 transform = Utils.eye4x4();

            transform = Utils.rotate(obj.getRotation()).multiply(transform);
            transform = Utils.move(obj.getCentre()).multiply(transform);
            transform = Utils.move(obj.getPosition()).multiply(transform);

            transform = viewerTransform.multiply(transform);

            for (Shape3D shape : obj.getFaces()) {
                Shape3D transformed = shape.getTransformed(transform);
                if (transformed.getNormal().dot(new Point3D(0, 0, 1)) < 0) {
                    drawShapes.add(transformed);
                }
            }
        }
        Collections.sort(drawShapes);
        repaint();
    }
}
