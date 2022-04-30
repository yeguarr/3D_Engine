import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ReaderOBJ {
    public List<Point3D> vertices = new LinkedList<>();
    public List<Shape3D> faces = new LinkedList<>();
    public ReaderOBJ(String file) {
        File text = new File(file);
        try {
            Scanner reader = new Scanner(text);
            int lineNumber = 1;
            while(reader.hasNextLine()){
                String[] data = reader.nextLine().trim().replaceAll(" +", " ").split(" ");
                if (data[0].equals("v")) {
                    if (data.length == 5) {
                        float x = Float.parseFloat(data[1]);
                        float y = -Float.parseFloat(data[2]);
                        float z = Float.parseFloat(data[3]);
                        float w = Float.parseFloat(data[4]);
                        vertices.add(new Point3D(x,y,z,w));
                    } else if (data.length == 4){
                        float x = Float.parseFloat(data[1]);
                        float y = -Float.parseFloat(data[2]);
                        float z = Float.parseFloat(data[3]);
                        vertices.add(new Point3D(x,y,z));
                    }
                } else if (data[0].equals("f")) {
                    if (data.length == 4){
                        int first = getCorrectV(Integer.parseInt(data[1].split("/")[0])) - 1;
                        int second = getCorrectV(Integer.parseInt(data[2].split("/")[0])) - 1;
                        int third = getCorrectV(Integer.parseInt(data[3].split("/")[0])) - 1;
                        faces.add(new Triangle3D(vertices.get(first), vertices.get(second), vertices.get(third)));
                    } /*else {
                        List<Point3D> buff = new LinkedList<>();
                        for (int i = 1; i < data.length; i++) {
                            int pos = getCorrectV(Integer.parseInt(data[i].split("/")[0])) - 1;
                            buff.add(vertices.get(pos));
                        }
                        Polygon3D pol = new Polygon3D(buff.toArray(new Point3D[0]));
                        Color color = Color.getHSBColor((float) ((pol.getNormal().dot(new Point3D(0, 0, 1)))),1,1);
                        faces.add(new Polygon3D(buff.toArray(new Point3D[0]), color));
                    }*/
                }
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Object3D getObject() {
        return new Object3D(faces.toArray(new Shape3D[0]));
    }

    private int getCorrectV(int i) {
        if (i > 0)
            return i;
        else
            return vertices.size() + i + 1;
    }
}
