public class Utils {
    public static Matrix.f4x4 eye4x4() {
        return new Matrix.f4x4(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1);
    }

    public static Matrix.f4x4 rotX(float angle) {
        return new Matrix.f4x4(1, 0, 0, 0,
                0, (float) Math.cos(Math.toRadians(angle)), (float)-Math.sin(Math.toRadians(angle)), 0,
                0, (float)Math.sin(Math.toRadians(angle)), (float)Math.cos(Math.toRadians(angle)), 0,
                0, 0, 0, 1);
    }

    public static Matrix.f4x4 rotY(float angle) {
        return new Matrix.f4x4((float)Math.cos(Math.toRadians(angle)), 0, (float)Math.sin(Math.toRadians(angle)), 0,
                0, 1, 0, 0,
                (float)-Math.sin(Math.toRadians(angle)), 0, (float)Math.cos(Math.toRadians(angle)), 0,
                0, 0, 0, 1);
    }

    public static Matrix.f4x4 rotZ(float angle) {
        return new Matrix.f4x4((float)Math.cos(Math.toRadians(angle)), (float)-Math.sin(Math.toRadians(angle)), 0, 0,
                (float)Math.sin(Math.toRadians(angle)), (float)Math.cos(Math.toRadians(angle)), 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1);
    }

    public static Matrix.f4x4 rotate(Point3D rot) {
        return Utils.rotX(rot.getX()).multiply(Utils.rotY(rot.getY()).multiply(Utils.rotZ(rot.getZ())));
    }

    public static Matrix.f4x4 move(float x, float y, float z) {
        return new Matrix.f4x4(1, 0, 0, x,
                0, 1, 0, y,
                0, 0, 1, z,
                0, 0, 0, 1);
    }

    public static Matrix.f4x4 move(Point3D pos) {
        return Utils.move(pos.getX(), pos.getY(), pos.getZ());
    }

    public static Matrix.f4x4 scale(float x, float y, float z) {
        return new Matrix.f4x4(x, 0, 0, 0,
                0, y, 0, 0,
                0, 0, z, 0,
                0, 0, 0, 1);
    }

    public static Matrix.f4x4 projection(float FOV, float aspect, float zFar, float zNear) {
        return new Matrix.f4x4((float)(aspect/Math.tan(Math.toRadians(FOV/2))), 0, 0, 0,
                0, (float)(1/Math.tan(Math.toRadians(FOV/2))), 0, 0,
                0, 0, zFar/(zFar-zNear), 1,
                0, 0,  -zFar*zNear/(zFar-zNear),0);
    }
}
