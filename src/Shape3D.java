import java.awt.*;

public abstract class Shape3D implements Comparable<Shape3D> {

    public abstract Shape3D getTransformed(Matrix.f4x4 transformation);

    public abstract Polygon getPolygon();

    public abstract float getZCentre();
    public abstract Point3D[] getVertices();

    public int compareTo(Shape3D o) {
        double comp = this.getZCentre() - (o).getZCentre();
        if (comp == 0)
            return 0;
        return comp > 0 ? 1 : -1;
    }

    public abstract Point3D getNormal();

}
