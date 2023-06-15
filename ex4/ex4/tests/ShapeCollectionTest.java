// 207404997
package exe.ex4.ex4.tests;

import exe.ex4.ex4.ShapeCollection;
import exe.ex4.geo.*;
import exe.ex4.gui.GUIShape;
import exe.ex4.gui.GUI_Shape;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.io.*;
import java.util.Comparator;

public class ShapeCollectionTest {

    ShapeCollection myCollection = new ShapeCollection();
    Point_2D[] p = {new Point_2D(0,0), new Point_2D(1,1), new Point_2D(10,10),
            new Point_2D(5,5), new Point_2D(0,2), new Point_2D(3,7),
            new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,6)};
    Segment_2D seg = new Segment_2D(p[1], p[2]);
    Circle_2D cir = new Circle_2D(p[3], 5);
    Rect_2D rec = new Rect_2D(p[4], p[5]);
    Triangle_2D tri = new Triangle_2D(p[6], p[7], p[8]);
    Polygon_2D pol = new Polygon_2D();
    GUIShape gsSeg = new GUIShape(seg, false, Color.BLACK, 0);
    GUIShape gsCir = new GUIShape(cir, true, Color.RED, 1);
    GUIShape gsRec = new GUIShape(rec, false, Color.BLUE, 2);
    GUIShape gsTri = new GUIShape(tri, true, Color.YELLOW, 3);
    GUIShape[] g;
    Comparator<GUI_Shape> areaComparator = Comparator.comparingDouble(o -> o.getShape().area());
    Comparator<GUI_Shape> antiAreaComparator = areaComparator.reversed();
    Comparator<GUI_Shape> perimeterComparator = Comparator.comparingDouble(o -> o.getShape().perimeter());
    Comparator<GUI_Shape> antiPerimeterComparator = perimeterComparator.reversed();
    Comparator<GUI_Shape> antiToStringComparator = Comparator.comparing(o -> o.getShape().toString().trim());
    Comparator<GUI_Shape> toStringComparator = antiToStringComparator.reversed();
    Comparator<GUI_Shape> tagComparator = Comparator.comparingInt(GUI_Shape::getTag);
    Comparator<GUI_Shape> antiTagComparator = tagComparator.reversed();

    @BeforeEach
    public void poly(){
        for(Point_2D p : p){
            pol.add(p);
        }
        GUIShape gsPol = new GUIShape(pol, false, Color.GREEN, 4);
        g = new GUIShape[]{gsSeg, gsCir, gsRec, gsTri, gsPol};
        for(GUIShape gs : g){
            myCollection.add(gs);
        }
    }

    @Test
    // tests the get function:
    public void testGet(){
        for(int i = 0; i < g.length; i++){
            assertEquals(myCollection.get(i), g[i]);
        }
        myCollection.removeElementAt(0);
        assertEquals(myCollection.get(0), g[1]);
    }

    @Test
    // tests the size function:
    public void testSize(){
        assertEquals(5, myCollection.size());
        myCollection.removeElementAt(0);
        assertEquals(4, myCollection.size());
        myCollection.removeAll();
        assertEquals(0, myCollection.size());
    }

    @Test
    // tests the removeElementAt function:
    public void testRemoveElementAt(){
        for(int i = 0; i < g.length; i++){
            assertEquals(myCollection.get(i), g[i]);
        }
        myCollection.removeElementAt(0);
        assertEquals(g[1], myCollection.get(0));
        myCollection.removeElementAt(3);
        assertEquals(g[3], myCollection.get(2));
    }

    @Test
    // tests the addAt function:
    public void testAddAt(){
        myCollection.removeElementAt(0);
        myCollection.removeElementAt(3);
        myCollection.addAt(gsSeg, 0);
        myCollection.addAt(gsTri,4);
        assertEquals(g[2], myCollection.get(2));
    }

    @Test
    // tests the add function:
    public void testAdd(){
        myCollection.add(g[0]);
        assertEquals(g[0], myCollection.get(5));
    }

    @Test
    // tests the copy function:
    public void testCopy(){
        ShapeCollection copy = (ShapeCollection) myCollection.copy();
        for(int i=0; i<copy.size(); i++){
            assertEquals(copy.get(i), myCollection.get(i));
        }
    }

    @Test
    // tests the sort function:
    public void testSort(){
        myCollection.sort(areaComparator);
        assertEquals(g[1], myCollection.get(4));
        myCollection.sort(antiAreaComparator);
        assertEquals(g[1], myCollection.get(0));
        myCollection.sort(perimeterComparator);
        assertEquals(g[2], myCollection.get(0));
        myCollection.sort(antiPerimeterComparator);
        assertEquals(g[4], myCollection.get(0));
        myCollection.sort(toStringComparator);
        assertEquals(g[3], myCollection.get(0));
        myCollection.sort(antiToStringComparator);
        assertEquals(g[0], myCollection.get(3));
        myCollection.sort(tagComparator);
        assertEquals(g[2], myCollection.get(2));
        myCollection.sort(antiTagComparator);
        assertEquals(g[3], myCollection.get(1));
    }

    @Test
    // tests the removeAll function:
    public void testRemoveAll(){
        assertNotEquals(0, myCollection.size());
        myCollection.removeAll();
        assertEquals(0, myCollection.size());
    }

    @Test
    // tests the save and load functions:
    public void testSaveAndLoad() throws IOException {
        String fileName = "saveTest.txt";
        // save to file:
        myCollection.save(fileName);
        File file = new File(fileName);
        // check that the file exists:
        assertTrue(file.exists());
        // get the content of the file
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        StringBuilder content = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line);
            content.append("\n");
        }
        bufferedReader.close();
        reader.close();
        // check the content of the file with the expected content
        String expected = g[0].toString()+"\n"+g[1].toString()+"\n"+g[2].toString()+"\n"+g[3].toString()+"\n"+g[4].toString()+"\n";
        assertEquals(expected, content.toString());
        // empty collection:
        myCollection.removeAll();
        assertEquals(0, myCollection.size());
        // load to collection:
        myCollection.load(fileName);
        assertEquals(expected, content.toString());
    }

    @Test
    // tests the toString function:
    public void testToString(){
        String expected = "GUIShape,-16777216,false,0,Segment_2D, 1.0,1.0,10.0,10.0" +
                "GUIShape,-65536,true,1,Circle_2D, 5.0,5.0, 5.0" +
                "GUIShape,-16776961,false,2,Rect_2D, 0.0,7.0, 3.0,7.0, 3.0,2.0, 0.0,2.0" +
                "GUIShape,-256,true,3,Triangle_2D, 0.0,0.0, 10.0,0.0, 5.0,6.0" +
                "GUIShape,-16711936,false,4,Polygon_2D, 0.0,0.0, 1.0,1.0, 10.0,10.0," +
                " 5.0,5.0, 0.0,2.0, 3.0,7.0, 0.0,0.0, 10.0,0.0, 5.0,6.0";
        assertEquals(expected, myCollection.toString());
    }
}
