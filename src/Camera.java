public class Camera {
    private Point3D position;
    private Point3D rotation;

    public Camera(Point3D position, Point3D rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Matrix.f4x4 getTransformMatrix() {
        return Utils.rotate(new Point3D(rotation.getX(),rotation.getY(),-rotation.getZ())).multiply(Utils.move(position));
    }

    public Point3D getPosition() {
        return position;
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public Point3D getRotation() {
        return rotation;
    }

    public void setRotation(Point3D rotation) {
        this.rotation = rotation;
    }

}
