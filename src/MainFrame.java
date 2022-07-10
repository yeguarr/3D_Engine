import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class MainFrame extends JFrame {
    Updater updater;
    Camera camera;
    ControlsGUI controlsGUI;
    Viewer3D viewer3D;

    public MainFrame() {
        super("3D Viewer");
        updater = new Updater(80);
        camera = new Camera();
        controlsGUI = new ControlsGUI(camera);
        viewer3D = new Viewer3D(controlsGUI);
        setup();
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.updater.start();
        mainFrame.display();
    }

    public void setup() {
        camera.setPosition(new Point3D(0,0,-30));

        Object3D teapot = new ReaderOBJ("teapot.obj").getObject();
        viewer3D.addObject3D(teapot);

        updater.addTask(controlsGUI::updateControls);
        updater.addTask(viewer3D::updateComponent);
        //updater.addTask(() -> teapot.setRotation(teapot.getRotation().add(1,1,1)));
        //updater.addTask(() -> camera.setRotation(camera.getRotation().add(0,180,0)));
        //updater.addTask(() -> System.out.println(updater.getFrames()));

        ///////// END OF TEST ZONE
    }

    void display() {
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        viewer3D.setFocusable(true);
        viewer3D.grabFocus();

        add(viewer3D, BorderLayout.CENTER);
        setVisible(true);
    }
}
