public class Object3D {
    private Point3D position;
    private Point3D rotation;
    private Point3D centre;
    private Shape3D[] faces;

    public Object3D(Shape3D[] faces) {
        this.faces = faces;
        position = new Point3D();
        rotation = new Point3D();
        generateCentre();
    }

    public Object3D(Shape3D[] faces, Point3D position, Point3D rotation) {
        this.faces = faces;
        this.position = position;
        this.rotation = rotation;
        generateCentre();
    }

    private void generateCentre() {
        float minX = Float.MAX_VALUE;
        float maxX = -Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxY = -Float.MAX_VALUE;
        float minZ = Float.MAX_VALUE;
        float maxZ = -Float.MAX_VALUE;

        for (Shape3D shape : faces) {
            for (Point3D vertex : shape.getVertices()){
                if (vertex.getY() > maxY)
                    maxY = vertex.getY();
                if (vertex.getY() < minY)
                    minY = vertex.getY();

                if (vertex.getX() > maxX)
                    maxX = vertex.getX();
                if (vertex.getX() < minX)
                    minX = vertex.getX();

                if (vertex.getZ() > maxZ)
                    maxZ = vertex.getZ();
                if (vertex.getZ() < minZ)
                    minZ = vertex.getZ();
            }
        }
        centre = new Point3D((maxX+minX)/2,(maxY+minY)/2,(maxZ+minZ)/2);
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

    public Point3D getCentre() {
        return centre;
    }

    public Shape3D[] getFaces() {
        return faces;
    }

    public int getFacesNumber() {
        return faces.length;
    }

}
