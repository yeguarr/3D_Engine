public class Point3D {

    private final Matrix.f4x1 coordinate;

    Point3D() {
        coordinate = new Matrix.f4x1(0,0,0,1);
    }
    Point3D(float x, float y, float z) {
        coordinate = new Matrix.f4x1(x,y,z,1);
    }
    Point3D(float x, float y, float z,float w) {
        coordinate = new Matrix.f4x1(x,y,z,w);
    }
    Point3D(Point3D point3D) {
        coordinate = new Matrix.f4x1(point3D.coordinate.getA(),point3D.coordinate.getB(),point3D.coordinate.getC(),point3D.coordinate.getD());
    }
    Point3D(Matrix.f4x1 coordinate) {
        this.coordinate = new Matrix.f4x1(coordinate.getA(),coordinate.getB(),coordinate.getC(),coordinate.getD());
    }

    public Matrix.f4x1 getCoordinate() {
        return coordinate;
    }

    public float getX() {
        return coordinate.getA() / coordinate.getD();
    }

    public float getY() {
        return coordinate.getB() / coordinate.getD();
    }

    public float getZ() {
        return coordinate.getC() / coordinate.getD();
    }

    public Point3D add(Point3D point) {
        return point.add(point.getX(),point.getY(),point.getZ());
    }

    public Point3D add(float x, float y, float z) {
        float xp = coordinate.getD() * (getX() + x);
        float yp = coordinate.getD() * (getY() + y);
        float zp = coordinate.getD() * (getZ() + z);
        return new Point3D(xp,yp,zp,coordinate.getD());
    }

    public float dot(Point3D second) {
        return this.getX() * second.getX() + this.getY() * second.getY() + this.getZ() * second.getZ();
    }
    public Point3D cross(Point3D second) {
        return new Point3D(this.getY()*second.getZ()-this.getZ()*second.getY(),this.getZ()*second.getX()-this.getX()*second.getZ(),this.getX()*second.getY()-this.getY()*second.getX());
    }

    public Point3D normalise() {
        float r = (float) Math.sqrt(this.dot(this));
        return new Point3D(getX()/r,getY()/r,getZ()/r);
    }
}
