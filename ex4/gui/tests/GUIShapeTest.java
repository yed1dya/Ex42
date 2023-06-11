// 207404997
package exe.ex4.gui.tests;

import exe.ex4.geo.GeoShape;
import exe.ex4.geo.Point_2D;
import exe.ex4.geo.Polygon_2D;
import exe.ex4.gui.GUIShape;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;
import java.util.Arrays;

public class GUIShapeTest {

    Point_2D[] pointsArr = {new Point_2D(7.7,1.2), new Point_2D(11.7,1.2), new Point_2D(12.7,-0.8),
            new Point_2D(12.7,-2.8), new Point_2D(11.7,-4.8), new Point_2D(9.7,-4.8),
            new Point_2D(8.7,-2.8), new Point_2D(8.7,-1.8), new Point_2D(9.7,-0.8),
            new Point_2D(10.7,-2.8), new Point_2D(9.7,-1.8), new Point_2D(9.7,-3.8),
            new Point_2D(11.7,-3.8), new Point_2D(11.7,0.2), new Point_2D(10.7,0.2),
            new Point_2D(10.7,-0.8), new Point_2D(9.7,0.2), new Point_2D(7.7,0.2),
            new Point_2D(5.7,-0.8), new Point_2D(5.7,-2.8), new Point_2D(7.7,-0.8),
            new Point_2D(8.7,-0.8), new Point_2D(7.7,-1.8), new Point_2D(7.7,-3.8),
            new Point_2D(8.7,-4.8), new Point_2D(7.7,-4.8), new Point_2D(6.7,-2.8),
            new Point_2D(5.7,-4.8), new Point_2D(4.7,-3.8), new Point_2D(4.7,0.2),
            new Point_2D(5.7,0.2)};

    Polygon_2D poly = new Polygon_2D();

    @Test
    // tests the getShape method:
    public void testGetShape(){
        poly = new Polygon_2D();
        for(Point_2D p : pointsArr){
            poly.add(p);
        }
        GeoShape geo = poly.copy();
        GUIShape gui = new GUIShape(geo, false, Color.blue, 0);
        Point_2D[] geoPoints = ((Polygon_2D)geo).getAllPoints(),
                guiPoints = ((Polygon_2D)gui.getShape()).getAllPoints();
        assertArrayEquals(geoPoints, guiPoints);
    }

    @Test
    // tests the isFilled method:
    public void testIsFilled(){
        poly = new Polygon_2D();
        GeoShape geo = poly.copy();
        GUIShape gui = new GUIShape(geo, true, Color.blue, 0);
        assertTrue(gui.isFilled());
        gui = new GUIShape(geo, false, Color.blue, 0);
        assertFalse(gui.isFilled());
    }

    @Test
    // tests the setFilled method:
    public void testSetFilled(){
        poly = new Polygon_2D();
        GeoShape geo = poly.copy();
        GUIShape gui = new GUIShape(geo, true, Color.blue, 0);
        assertTrue(gui.isFilled());
        gui.setFilled(false);
        assertFalse(gui.isFilled());
    }

    @Test
    // tests the getColor method:
    public void testGetColor(){
        poly = new Polygon_2D();
        GeoShape geo = poly.copy();
        GUIShape gui = new GUIShape(geo, true, Color.blue, 0);
        assertEquals(gui.getColor(), Color.blue);
    }

    @Test
    // tests the setColor method:
    public void testSetColor(){
        poly = new Polygon_2D();
        GeoShape geo = poly.copy();
        GUIShape gui = new GUIShape(geo, true, Color.blue, 0);
        assertEquals(gui.getColor(), Color.blue);
        gui.setColor(Color.RED);
        assertEquals(gui.getColor(), Color.RED);
    }

    @Test
    // tests the getTag method:
    public void testGetTag(){
        poly = new Polygon_2D();
        GeoShape geo = poly.copy();
        GUIShape gui = new GUIShape(geo, true, Color.blue, 23);
        assertEquals(gui.getTag(), 23);
    }

    @Test
    // tests the setTag method:
    public void testSetTag(){
        poly = new Polygon_2D();
        GeoShape geo = poly.copy();
        GUIShape gui = new GUIShape(geo, true, Color.blue, 24);
        assertNotEquals(gui.getTag(), 23);
        gui.setTag(23);
        assertEquals(gui.getTag(), 23);
    }

    @Test
    // tests the copy method:
    public void testCopy(){
        poly = new Polygon_2D();
        for(Point_2D p : pointsArr){
            poly.add(p);
        }
        GeoShape geo = poly.copy();
        GUIShape gui = new GUIShape(geo, true, Color.blue, 23);
        GUIShape gui2 = (GUIShape) gui.copy();
        Point_2D[] geoPoints = ((Polygon_2D)geo).getAllPoints(),
                guiPoints = ((Polygon_2D)gui2.getShape()).getAllPoints();
        assertArrayEquals(geoPoints, guiPoints);
    }

    @Test
// tests the toString method:
    public void testToString(){
        poly = new Polygon_2D();
        for(Point_2D p : pointsArr){
            poly.add(p);
        }
        GeoShape geo = poly.copy();
        GUIShape gui = new GUIShape(geo, true, Color.blue, 23);
        String expected = "GUIShape,-16776961,true,23,Polygon_2D, 7.7,1.2, 11.7,1.2, 12.7,-0.8, 12.7,-2.8, 11.7,-4.8, " +
                "9.7,-4.8, 8.7,-2.8, 8.7,-1.8, 9.7,-0.8, 10.7,-2.8, 9.7,-1.8, 9.7,-3.8, 11.7,-3.8, 11.7,0.2, 10.7,0.2, " +
                "10.7,-0.8, 9.7,0.2, 7.7,0.2, 5.7,-0.8, 5.7,-2.8, 7.7,-0.8, 8.7,-0.8, 7.7,-1.8, 7.7,-3.8, 8.7,-4.8, 7.7," +
                "-4.8, 6.7,-2.8, 5.7,-4.8, 4.7,-3.8, 4.7,0.2, 5.7,0.2";
        assertEquals(expected, gui.toString());
    }

    @Test
    // tests the isSelected method:
    public void testIsSelected(){
        poly = new Polygon_2D();
        GeoShape geo = poly.copy();
        GUIShape gui = new GUIShape(geo, true, Color.blue, 23);
        assertFalse(gui.isSelected());
    }

    @Test
    // tests the setSelected method:
    public void testSetSelected(){
        poly = new Polygon_2D();
        GeoShape geo = poly.copy();
        GUIShape gui = new GUIShape(geo, true, Color.blue, 23);
        assertFalse(gui.isSelected());
        gui.setSelected(true);
        assertTrue(gui.isSelected());
    }

    @Test
    // tests the setShape method:
    public void testSetShape(){
        poly = new Polygon_2D();
        Polygon_2D poly2 = new Polygon_2D();
        for(Point_2D p : pointsArr){
            poly.add(p);
        }
        GeoShape geo = poly2.copy();
        GUIShape gui = new GUIShape(geo, true, Color.blue, 23);
        Point_2D[] guiPoints = ((Polygon_2D)gui.getShape()).getAllPoints();
        assertFalse(Arrays.equals(guiPoints, pointsArr));
        gui.setShape(poly.copy());
        guiPoints = ((Polygon_2D)gui.getShape()).getAllPoints();
        assertArrayEquals(pointsArr, guiPoints);
    }
}
