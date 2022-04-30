import java.awt.*;

public class Triangle3D extends Shape3D {
    private Point3D first;
    private Point3D second;
    private Point3D third;

    public Triangle3D() {
        first = new Point3D();
        second = new Point3D();
        third = new Point3D();
    }

    public Triangle3D(Point3D first, Point3D second, Point3D third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public Shape3D getTransformed(Matrix.f4x4 transformation) {
        return new Triangle3D(transformation.multiply(this.first), transformation.multiply(this.second), transformation.multiply(this.third));
    }

    @Override
    public Polygon getPolygon() {
        return new Polygon(new int[]{(int) this.first.getX(), (int) this.second.getX(), (int) this.third.getX()},
                new int[]{(int) this.first.getY(), (int) this.second.getY(), (int) this.third.getY()}, 3);
    }

    public float getZCentre() {
        return (this.first.getZ() + this.second.getZ() + this.third.getZ()) / 3;
    }

    @Override
    public Point3D[] getVertices() {
        return new Point3D[]{first, second, third};
    }

    public Point3D getNormal() {
        Point3D a = second.add(-first.getX(),-first.getY(),-first.getZ());
        Point3D b = third.add(-first.getX(),-first.getY(),-first.getZ());
        return a.cross(b).normalise();
    }
}
