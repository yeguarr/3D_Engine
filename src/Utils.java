public class Utils {
    public static Matrix.m4x4 eye4x4() {
        return new Matrix.m4x4(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1);
    }

    public static Matrix.m4x4 rotX(double angle) {
        return new Matrix.m4x4(1, 0, 0, 0,
                0, Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)), 0,
                0, Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0,
                0, 0, 0, 1);
    }

    public static Matrix.m4x4 rotY(double angle) {
        return new Matrix.m4x4(Math.cos(Math.toRadians(angle)), 0, Math.sin(Math.toRadians(angle)), 0,
                0, 1, 0, 0,
                -Math.sin(Math.toRadians(angle)), 0, Math.cos(Math.toRadians(angle)), 0,
                0, 0, 0, 1);
    }

    public static Matrix.m4x4 rotZ(double angle) {
        return new Matrix.m4x4(Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)), 0, 0,
                Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1);
    }

    public static Matrix.m4x4 rotate(Point3D rot) {
        return Utils.rotZ(rot.getZ()).multiply(Utils.rotY(rot.getY())).multiply(Utils.rotX(rot.getX()));
    }

    public static Matrix.m4x4 move(double x, double y, double z) {
        return new Matrix.m4x4(1, 0, 0, x,
                0, 1, 0, y,
                0, 0, 1, z,
                0, 0, 0, 1);
    }

    public static Matrix.m4x4 move(Point3D pos) {
        return Utils.move(pos.getX(), pos.getY(), pos.getZ());
    }

    public static Matrix.m4x4 scale(double x, double y, double z) {
        return new Matrix.m4x4(x, 0, 0, 0,
                0, y, 0, 0,
                0, 0, z, 0,
                0, 0, 0, 1);
    }

    public static Matrix.m4x4 projection(double FOV, double aspect, double zFar, double zNear) {
        return new Matrix.m4x4((aspect / Math.tan(Math.toRadians(FOV / 2))), 0, 0, 0,
                0, (1 / Math.tan(Math.toRadians(FOV / 2))), 0, 0,
                0, 0, zFar / (zFar - zNear), 1,
                0, 0, -zFar * zNear / (zFar - zNear), 0);
    }
}
