import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class ControlsGUI extends MouseAdapter implements KeyListener {
    private int oldX = 0;
    private int oldY = 0;
    private Camera camera;
    private Point3D setRotation;
    private Point3D currentRotation;
    private final Set<Integer> pressedKeys = new HashSet<>();

    public ControlsGUI(Camera camera) {
        this.camera = camera;
        setRotation = new Point3D(camera.getRotation());
        currentRotation = new Point3D(camera.getRotation());
    }
    
    public void updateControls() {
        updateKeys();
        updateMouse();
    }

    private void updateMouse() {
        camera.setRotation(currentRotation);
    }

    private void updateKeys() {
        if (!pressedKeys.isEmpty()) {
            for (Integer pressedKey : pressedKeys) {
                switch (pressedKey) {
                    case KeyEvent.VK_W:
                        camera.setPosition(camera.getPosition().add((float)(-Math.sin(Math.toRadians(camera.getRotation().getY()))*Math.cos(Math.toRadians(camera.getRotation().getX()))),
                                (float)Math.sin(Math.toRadians(camera.getRotation().getX())),
                                (float)(Math.cos(Math.toRadians(camera.getRotation().getY()))*Math.cos(Math.toRadians(camera.getRotation().getX())))));
                        break;
                    case KeyEvent.VK_S:
                        camera.setPosition(camera.getPosition().add((float)(Math.sin(Math.toRadians(camera.getRotation().getY()))*Math.cos(Math.toRadians(camera.getRotation().getX()))),
                                (float)-Math.sin(Math.toRadians(camera.getRotation().getX())),
                                (float)(-Math.cos(Math.toRadians(camera.getRotation().getY()))*Math.cos(Math.toRadians(camera.getRotation().getX())))));
                        break;
                    case KeyEvent.VK_A:
                        camera.setPosition(camera.getPosition().add((float)Math.cos(Math.toRadians(camera.getRotation().getY())),0,(float)Math.sin(Math.toRadians(camera.getRotation().getY()))));
                        break;
                    case KeyEvent.VK_D:
                        camera.setPosition(camera.getPosition().add((float)-Math.cos(Math.toRadians(camera.getRotation().getY())),0,(float)-Math.sin(Math.toRadians(camera.getRotation().getY()))));
                        break;
                }
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        oldX = -e.getY()/2;
        oldY = e.getX()/2;
    }

    public void mouseDragged(MouseEvent e) {
        currentRotation = new Point3D(Math.min(Math.max(setRotation.getX() - e.getY()/2.f - oldX,-90),90),setRotation.getY() + e.getX()/2.f - oldY,camera.getRotation().getZ());
    }

    public void mouseReleased(MouseEvent e) {
        setRotation = new Point3D(camera.getRotation().getX(),camera.getRotation().getY(),setRotation.getZ());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    public Camera getCamera() {
        return camera;
    }
}
