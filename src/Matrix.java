public class Matrix {
    static public class f4x1 {
        private final float a, b, c, d;

        public float getA() {
            return a;
        }

        public float getB() {
            return b;
        }

        public float getC() {
            return c;
        }

        public float getD() {
            return d;
        }

        public f4x1(float a, float b, float c, float d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
    }
    static public class f4x4 {
        private final float
                aa, ab, ac, ad,
                ba, bb, bc, bd,
                ca, cb, cc, cd,
                da, db, dc, dd;

        public f4x4(float aa, float ab, float ac, float ad, float ba, float bb, float bc, float bd, float ca, float cb, float cc, float cd, float da, float db, float dc, float dd) {
            this.aa = aa;
            this.ab = ab;
            this.ac = ac;
            this.ad = ad;
            this.ba = ba;
            this.bb = bb;
            this.bc = bc;
            this.bd = bd;
            this.ca = ca;
            this.cb = cb;
            this.cc = cc;
            this.cd = cd;
            this.da = da;
            this.db = db;
            this.dc = dc;
            this.dd = dd;
        }

        public f4x4 multiply(f4x4 second) {
            return new f4x4(
                    this.aa * second.aa + this.ab * second.ba + this.ac * second.ca + this.ad * second.da,
                    this.aa * second.ab + this.ab * second.bb + this.ac * second.cb + this.ad * second.db,
                    this.aa * second.ac + this.ab * second.bc + this.ac * second.cc + this.ad * second.dc,
                    this.aa * second.ad + this.ab * second.bd + this.ac * second.cd + this.ad * second.dd,
                    this.ba * second.aa + this.bb * second.ba + this.bc * second.ca + this.bd * second.da,
                    this.ba * second.ab + this.bb * second.bb + this.bc * second.cb + this.bd * second.db,
                    this.ba * second.ac + this.bb * second.bc + this.bc * second.cc + this.bd * second.dc,
                    this.ba * second.ad + this.bb * second.bd + this.bc * second.cd + this.bd * second.dd,
                    this.ca * second.aa + this.cb * second.ba + this.cc * second.ca + this.cd * second.da,
                    this.ca * second.ab + this.cb * second.bb + this.cc * second.cb + this.cd * second.db,
                    this.ca * second.ac + this.cb * second.bc + this.cc * second.cc + this.cd * second.dc,
                    this.ca * second.ad + this.cb * second.bd + this.cc * second.cd + this.cd * second.dd,
                    this.da * second.aa + this.db * second.ba + this.dc * second.ca + this.dd * second.da,
                    this.da * second.ab + this.db * second.bb + this.dc * second.cb + this.dd * second.db,
                    this.da * second.ac + this.db * second.bc + this.dc * second.cc + this.dd * second.dc,
                    this.da * second.ad + this.db * second.bd + this.dc * second.cd + this.dd * second.dd);
        }

        public f4x1 multiply(f4x1 second) {
            return new f4x1(
                    this.aa * second.getA() + this.ab * second.getB() + this.ac * second.getC() + this.ad * second.getD(),
                    this.ba * second.getA() + this.bb * second.getB() + this.bc * second.getC() + this.bd * second.getD(),
                    this.ca * second.getA() + this.cb * second.getB() + this.cc * second.getC() + this.cd * second.getD(),
                    this.da * second.getA() + this.db * second.getB() + this.dc * second.getC() + this.dd * second.getD());
        }

        public Point3D multiply(Point3D second) {
            return new Point3D(this.multiply(second.getCoordinate()));
        }

        public float[][] getMatrixArray() {
            return new float[][]{
                    {aa, ab, ac, ad},
                    {ba, bb, bc, bd},
                    {ca, cb, cc, cd},
                    {da, db, dc, dd}};
        }
    }
}
