// 207404997
package exe.ex4.geo.tests;

import exe.ex4.geo.Point_2D;
import exe.ex4.geo.Polygon_2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class Polygon2DTest {
    final double EPS1 = 0.001;
    Point_2D p2__5 = new Point_2D(2,5), p6__2 = new Point_2D(6,2), p0__0 =new Point_2D(0,0),
            p2__6 =new Point_2D(2,6), p2__1 =new Point_2D(2,1), p5__1 =new Point_2D(5,1),
            p3__3 =new Point_2D(3,3), p3_7__m5_8=new Point_2D(3.7,-5.8), p4__4=new Point_2D(4,4),
            p1_3__6_8=new Point_2D(1.3,6.8), p3_8__10_5=new Point_2D(3.8,10.5), p6__5 = new Point_2D(6,5),
            p5__5=new Point_2D(5,5), p1__2=new Point_2D(1,2), p0__5=new Point_2D(0,5),
            p2_2__2_7 =new Point_2D(2.2,2.7), p3_1__4_1 =new Point_2D(3.1,4.1), p6__4=new Point_2D(6,4),
            p5__3 =new Point_2D(5,3), p4__2 =new Point_2D(4,2), p8__1=new Point_2D(8,1),
            p4__7=new Point_2D(4,7), p8__7=new Point_2D(8,7), p9__5=new Point_2D(9,5),
            p9__3=new Point_2D(9,3), p6__1=new Point_2D(6,1), p5__4=new Point_2D(5,4),
            p7__3=new Point_2D(7,3), p8__2=new Point_2D(8,2), p8__6=new Point_2D(8,6),
            p7__6=new Point_2D(7,6), p7__5=new Point_2D(7,5), p6__6=new Point_2D(6,6),
            p4__6=new Point_2D(4,6), p2__3=new Point_2D(2,3), p1__6=new Point_2D(1,6),
            p4__5=new Point_2D(4,5), p4__1=new Point_2D(4,1), p7__4=new Point_2D(7,4);

    Point_2D[] pointsArr1 = {p4__7, p8__7, p9__5, p9__3, p8__1, p6__1, p5__3, p5__4, p6__5, p7__3,
            p6__4, p6__2, p8__2, p8__6, p7__6, p7__5, p6__6, p4__6, p2__5, p2__3, p4__5, p5__5,
            p4__4, p4__2, p5__1, p4__1, p3__3, p2__1, p1__2, p1__6, p2__6},
            pointsArr2 = {p4__7, p8__7, p9__5}, pointsArr3 = {p1__2, p1__6, p6__6, p6__2},
            pointsArr4 = {new Point_2D(7.7,1.2), new Point_2D(11.7,1.2), new Point_2D(12.7,-0.8),
                    new Point_2D(12.7,-2.8), new Point_2D(11.7,-4.8), new Point_2D(9.7,-4.8),
                    new Point_2D(8.7,-2.8), new Point_2D(8.7,-1.8), new Point_2D(9.7,-0.8),
                    new Point_2D(10.7,-2.8), new Point_2D(9.7,-1.8), new Point_2D(9.7,-3.8),
                    new Point_2D(11.7,-3.8), new Point_2D(11.7,0.2), new Point_2D(10.7,0.2),
                    new Point_2D(10.7,-0.8), new Point_2D(9.7,0.2), new Point_2D(7.7,0.2),
                    new Point_2D(5.7,-0.8), new Point_2D(5.7,-2.8), new Point_2D(7.7,-0.8),
                    new Point_2D(8.7,-0.8), new Point_2D(7.7,-1.8), new Point_2D(7.7,-3.8),
                    new Point_2D(8.7,-4.8), new Point_2D(7.7,-4.8), new Point_2D(6.7,-2.8),
                    new Point_2D(5.7,-4.8), new Point_2D(4.7,-3.8), new Point_2D(4.7,0.2),
                    new Point_2D(5.7,0.2)},
            pointsArr5 = {p6__2, p8__2, p8__6, p7__6, p7__5, p6__6,},
            pointsArr6 = {new Point_2D(4.59,3.44), new Point_2D(5.9899,3.44), new Point_2D(5.9899,6.24),
                    new Point_2D(5.29,6.24), new Point_2D(5.29,5.54), new Point_2D(4.59,6.24)},
            pointsArr7 = {new Point_2D(10.0153,5.1198), new Point_2D(14.5933,5.1198), new Point_2D(14.5933,14.2758),
                    new Point_2D(12.3043,14.2758), new Point_2D(12.3043,11.9868), new Point_2D(10.0153,14.2758)},
            pointsArr8 = {new Point_2D(-7.7254,-0.3731), new Point_2D(-13.5395,-0.3731), new Point_2D(-13.5395,-12.0013),
                    new Point_2D(-10.6324,-12.0013), new Point_2D(-10.6324,-9.0942), new Point_2D(-7.7254,-12.0013)},
            pointsArr9 = {new Point_2D(6.0362,2.0509), new Point_2D(8.0359,2.0858), new Point_2D(7.9661,6.0852),
                    new Point_2D(6.9662,6.0677), new Point_2D(6.9836,5.0679), new Point_2D(5.9663,6.0503)};

    @Test
    // tests the basic constructor:
    public void testPolygonConstructor(){
        Polygon_2D poly = new Polygon_2D();
        String s = "";
        // calibration check:
        assertEquals(s, poly.toString());
    }

    @Test
    // tests the copy constructor:
    public void testPolygonCopyConstructor(){
        Polygon_2D poly = new Polygon_2D();
        poly.add(p0__0);
        Polygon_2D copy = new Polygon_2D(poly);
        // check that polygons are equal:
        assertTrue(equals(poly, copy));
        // check that polygons are different objects:
        assertNotSame(poly, copy);
    }

    @Test
    // tests the getAllPoints function:
    public void testGetAllPoints(){
        Polygon_2D poly = new Polygon_2D();
        for (Point_2D p : pointsArr1){
            poly.add(p);
        }
        Point_2D[] points = poly.getAllPoints();
        assertTrue(equals(poly.getAllPoints(), points));
        poly.add(p0__0);
        assertFalse(equals(poly.getAllPoints(), points));
    }

    @Test
    // tests the add function:
    public void testAdd(){
        Polygon_2D poly = new Polygon_2D();
        for (Point_2D p : pointsArr1){
            poly.add(p);
        }
        Point_2D[] points = poly.getAllPoints();
        assertTrue(equals(poly.getAllPoints(), points));
        poly.add(p0__0);
        assertFalse(equals(poly.getAllPoints(), points));
    }

    @Test
    // tests the toString function
    public void testToString(){
        Polygon_2D poly = new Polygon_2D();
        String exp = "";
        assertEquals(exp, poly.toString());
        for (Point_2D p : pointsArr2){
            poly.add(p);
        }
        exp = "(4.0,7.0), (8.0,7.0), (9.0,5.0)";
        assertEquals(exp, poly.toString());
    }

    @Test
    // test the contains function:
    public void testContains(){
        Polygon_2D poly = new Polygon_2D();
        for (Point_2D p : pointsArr1){
            poly.add(p);
        }
        assertTrue(poly.contains(p7__3));
        assertTrue(poly.contains(p2_2__2_7));
        assertFalse(poly.contains(p1_3__6_8));
        assertFalse(poly.contains(p7__4));
        poly.add(new Point_2D(2,8));
        poly.add(new Point_2D(3,8));
        assertTrue(poly.contains(new Point_2D(3,7)));
        //assertFalse(poly.contains(new Point_2D(1,7)));
        poly = new Polygon_2D();
        for (Point_2D p : pointsArr5){
            poly.add(p);
        }
        //assertFalse(poly.contains(new Point_2D(6.5,6)));
    }

    @Test
    // tests the area function:
    public void testArea(){
        Polygon_2D poly = new Polygon_2D();
        assertEquals(0, poly.area(), EPS1);
        for (Point_2D p : pointsArr1){
            poly.add(p);
        }
        assertEquals(25, poly.area(), EPS1);
        // check with points in counter-clockwise order
        Point_2D[] points = new Point_2D[pointsArr1.length];
        poly = new Polygon_2D();
        int i= pointsArr1.length-1;
        for(Point_2D p : pointsArr1){
            points[i--] = new Point_2D(p);
        }
        for (Point_2D p : points){
            poly.add(p);
        }
        assertEquals(25, poly.area(), EPS1);
        poly.translate(p3_7__m5_8);
        assertEquals(25, poly.area(), EPS1);
        poly.scale(p0__0, 2);
        assertEquals(100, poly.area(), EPS1);
        poly.scale(p0__0, 1.5);
        assertEquals(225, poly.area(), EPS1);
        poly = new Polygon_2D();
        for (Point_2D p : pointsArr3){
            poly.add(p);
        }
        assertEquals(20, poly.area(), EPS1);
        poly.scale(p0__0, 2);
        assertEquals(80, poly.area(), EPS1);
    }

    @Test
    // tests the perimeter function:
    public void testPerimeter(){
        Polygon_2D poly = new Polygon_2D();
        assertEquals(0, poly.area(), EPS1);
        for (Point_2D p : pointsArr1){
            poly.add(p);
        }
        assertEquals(61.20225231898308, poly.perimeter(), EPS1);
    }

    @Test
    // tests the 'translate' function:
    public void testTranslate(){
        Polygon_2D poly = new Polygon_2D();
        for (Point_2D p : pointsArr1){
            poly.add(p);
        }
        poly.translate(p3_7__m5_8);
        assertTrue(equals(poly.getAllPoints(), pointsArr4));
    }
    
    @Test
    // tests the copy function:
    public void testCopy(){
        Polygon_2D poly = new Polygon_2D();
        for (Point_2D p : pointsArr1){
            poly.add(p);
        }
        Polygon_2D copy = (Polygon_2D) poly.copy();
        // check that polygons are equal:
        assertTrue(equals(poly, copy));
        // check that polygons are different objects:
        assertNotSame(poly, copy);
    }

    @Test
    // tests the  scale function:
    public void testScale(){
        Polygon_2D poly = new Polygon_2D();
        for (Point_2D p : pointsArr5){
            poly.add(p);
        }
        // calibration check; scale by 1:
        poly.scale(p3_8__10_5, 1);
        assertTrue(equals(poly.getAllPoints(), pointsArr5));
        // scale by positive fraction:
        poly.scale(p1_3__6_8, 0.7);
        assertFalse(equals(poly.getAllPoints(), pointsArr5));
        assertTrue(equals(poly.getAllPoints(), pointsArr6));
        // scale by positive number:
        poly.scale(p2_2__2_7, 3.27);
        assertFalse(equals(poly.getAllPoints(), pointsArr6));
        assertTrue(equals(poly.getAllPoints(), pointsArr7));
        // scale by negative number:
        poly.scale(p2_2__2_7, -1.27);
        assertFalse(equals(poly.getAllPoints(), pointsArr7));
        assertTrue(equals(poly.getAllPoints(), pointsArr8));
        // scale by 0 makes all points the same point:
        poly.scale(p3_8__10_5, 0);
        for(Point_2D p : poly.getAllPoints()){
            assertTrue(equals(p3_8__10_5, p));
        }
    }

    @Test
    // tests the rotate function:
    public void testRotate(){
        Polygon_2D poly = new Polygon_2D();
        for (Point_2D p : pointsArr5){
            poly.add(p);
        }
        // calibration check; rotate by multiple of 360 should have no effect:
        poly.rotate(p1_3__6_8, 0);
        assertTrue(equals(poly.getAllPoints(), pointsArr5));
        poly.rotate(p3_8__10_5, 360);
        assertTrue(equals(poly.getAllPoints(), pointsArr5));
        poly.rotate(p0__5, 720);
        assertTrue(equals(poly.getAllPoints(), pointsArr5));
        // rotate by 1 should have an effect:
        poly.rotate(p3_1__4_1, 1);
        assertFalse(equals(poly.getAllPoints(), pointsArr5));
        assertTrue(equals(poly.getAllPoints(), pointsArr9));
        // rotate by negative value should have opposite effect:
        poly.rotate(p3_1__4_1, -1);
        assertFalse(equals(poly.getAllPoints(), pointsArr9));
        assertTrue(equals(poly.getAllPoints(), pointsArr5));
    }

    /**
     * helper function for comparing points;
     * checks that the x,y values of 2 points are closer than epsilon
     * @param p1 first point
     * @param p2 second point
     * @return true if points are closer than epsilon, otherwise false.
     */
    private boolean equals(Point_2D p1, Point_2D p2){
        return (Math.abs(p1.x()-p2.x())<EPS1 && Math.abs(p1.y()-p2.y())<EPS1);
    }

    /**
     * helper function for comparing polygons
     * @param poly1 first polygon
     * @param poly2 second polygon
     * @return true if polygons have the same points, otherwise false.
     */
    private boolean equals(Polygon_2D poly1, Polygon_2D poly2){
        Point_2D[] a = poly1.getAllPoints(), b = poly2.getAllPoints();
        for(int i=0; a.length==b.length && i<a.length; i++){
            if(!equals(a[i], b[i])){ return false; }
        }
        return a.length==b.length;
    }

    /**
     * helper function for comparing arrays of points
     * @param a first array
     * @param b second array
     * @return true if arrays have the same points, otherwise false
     */
    private boolean equals(Point_2D[] a, Point_2D[] b){
        for(int i=0; a.length == b.length && i < a.length; i++){
            if(!equals(a[i], b[i])){ return false; }
        }
        return a.length==b.length;
    }
}
