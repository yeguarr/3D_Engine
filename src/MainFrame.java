import javax.swing.*;
import java.awt.*;

public class MainFrame {
    JFrame frame;
    Updater updater;
    Camera camera;
    ControlsGUI controlsGUI;
    Viewer3D viewer3D;

    public MainFrame() {
        frame = new JFrame("3D Viewer");
        updater = new Updater(60);
        camera = new Camera();
        controlsGUI = new ControlsGUI(camera);
        viewer3D = new Viewer3D(controlsGUI);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setup();
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
        updater.start();
    }

    void display() {
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        viewer3D.setFocusable(true);
        viewer3D.grabFocus();

        frame.add(viewer3D, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
