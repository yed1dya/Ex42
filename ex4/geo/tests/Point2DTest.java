// 207404997
package exe.ex4.geo.tests;

import exe.ex4.geo.Point_2D;
import exe.ex4.geo.Segment_2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class Point2DTest {
    public static final double EPS1 = 0.001;
    Point_2D pPI__E = new Point_2D(Math.PI, Math.E), pmPI__mE = new Point_2D(-1*Math.PI, -1*Math.E),
            pmPI__E = new Point_2D(-1*Math.PI, Math.E), pPI__mE = new Point_2D(Math.PI, -1*Math.E),
            p0__0 =new Point_2D(0,0), p1__1 =new Point_2D(1,1), pm1__m1 =new Point_2D(-1,-1),
            pm1__m3 =new Point_2D(-1,-3), p2__1 =new Point_2D(2,1), p2__m2 =new Point_2D(2,-2),
            pm1__2 =new Point_2D(-1,2), pmPI__0 =new Point_2D(-1*Math.PI,0), p0__mE =new Point_2D(0,-1*Math.E),
            p2_5__3_7=new Point_2D(2.5,3.7), p1_3__6_8=new Point_2D(1.3,6.8), p3_8__10_5=new Point_2D(3.8,10.5),
            p3_14159__2_71828=new Point_2D(3.14159, 2.71828), p3__4=new Point_2D(3,4), pm3__m4=new Point_2D(-3,-4),
            p0__3=new Point_2D(0,3), pm3__4=new Point_2D(-3,4), pm3__m1=new Point_2D(-3,-1),
            pm6_8855__4_9631=new Point_2D(-6.8855,4.9631);
    String SpPI_E="3.14159,2.71828", SpmPI_mE="-3.14159,-2.71828", SpmPI_E="-3.14159,2.71828", SpPI_mE="3.14159,-2.71828";

    @Test
    // tests the constructor:
    public void testConstructor(){
        // test with 4 options of positive-negative values:
        Point_2D p = new Point_2D(Math.PI, Math.E); assertTrue(equals(pPI__E, p)); assertFalse(equals(pmPI__E, p));
        p = new Point_2D(-1*Math.PI, -1*Math.E); assertTrue(equals(pmPI__mE, p)); assertFalse(equals(pmPI__E, p));
        p = new Point_2D(-1*Math.PI, Math.E); assertTrue(equals(pmPI__E, p)); assertFalse(equals(pPI__E, p));
        p = new Point_2D(Math.PI, -1*Math.E); assertTrue(equals(pPI__mE, p)); assertFalse(equals(pmPI__E, p));
    }

    @Test
    // tests the copy constructor:
    public void testCopyConstructor(){
        // test with 4 options of positive-negative values:
        Point_2D p = new Point_2D(pPI__E); assertTrue(equals(pPI__E, p)); assertFalse(equals(pmPI__E, p));
        p = new Point_2D(pmPI__mE); assertTrue(equals(pmPI__mE, p)); assertFalse(equals(pmPI__E, p));
        p = new Point_2D(pmPI__E); assertTrue(equals(pmPI__E, p)); assertFalse(equals(pmPI__mE, p));
        p = new Point_2D(pPI__mE); assertTrue(equals(pPI__mE, p)); assertFalse(equals(pmPI__E, p));
        assertNotSame(p, pPI__mE);
    }

    @Test
    // tests the string constructor:
    public void testStringConstructor(){
        // test with 4 options of positive-negative values:
        Point_2D p = new Point_2D(SpPI_E); assertTrue(equals(pPI__E, p)); assertFalse(equals(pmPI__E, p));
        p = new Point_2D(SpmPI_mE); assertTrue(equals(pmPI__mE, p)); assertFalse(equals(pmPI__E, p));
        p = new Point_2D(SpmPI_E); assertTrue(equals(pmPI__E, p)); assertFalse(equals(pmPI__mE, p));
        p = new Point_2D(SpPI_mE); assertTrue(equals(pPI__mE, p)); assertFalse(equals(pmPI__E, p));
    }

    @Test
    // tests the x function:
    public void testX(){
        // test with positive-negative values:
        assertEquals(Math.PI, pPI__mE.x(), EPS1); assertEquals(Math.PI*-1, pmPI__mE.x(), EPS1);
    }

    @Test
    // tests the y function:
    public void testY(){
        // test with positive-negative values:
        assertEquals(Math.E*-1, pPI__mE.y(), EPS1); assertEquals(Math.E, pmPI__E.y(), EPS1);
    }

    @Test
    // tests the ix function:
    public void testIntX(){
        // test with positive-negative values:
        assertEquals(3, pPI__mE.ix(), EPS1);
        assertEquals(-3, pmPI__mE.ix(), EPS1);
    }

    @Test
    // tests the iy function:
    public void testIntY(){
        // test with positive-negative values:
        assertEquals(-2, pPI__mE.iy(), EPS1);
        assertEquals(2, pmPI__E.iy(), EPS1);
    }

    @Test
    // test the add function
    public void testAdd(){
        assertTrue(equals(p3_8__10_5, p2_5__3_7.add(p1_3__6_8)));
        // opposite points should add to zero:
        assertTrue(equals(p0__0, p1__1.add(pm1__m1)));
    }

    @Test
    // test the toString function:
    public void testToString(){
        // test with 4 options of positive-negative values:
        Point_2D p = new Point_2D(SpPI_E); assertEquals(SpPI_E, p.toString());
        p = new Point_2D(SpmPI_mE); assertEquals(SpmPI_mE, p.toString());
        p = new Point_2D(SpmPI_E); assertEquals(SpmPI_E, p.toString());
        p = new Point_2D(SpPI_mE); assertEquals(SpPI_mE, p.toString());
    }

    @Test
    // tests the distance from origin function:
    public void testDistanceOg(){
        // test with 4 options of positive-negative values:
        assertEquals(Math.sqrt(2), p1__1.distance(), EPS1);
        assertEquals(Math.sqrt(5), pm1__2.distance(), EPS1);
        assertEquals(Math.sqrt(10), pm1__m3.distance(), EPS1);
        assertEquals(Math.sqrt(8), p2__m2.distance(), EPS1);
        // test points on x,y axis:
        assertEquals(Math.PI, pmPI__0.distance(), EPS1); assertEquals(Math.E, p0__mE.distance(), EPS1);
    }

    @Test
    // tests the distance function (2 points):
    public void testDistance(){
        // test positive negative combos & symmetry:
        assertEquals(5, pm1__m3.distance(p2__1), EPS1);
        assertEquals(5, p2__1.distance(pm1__m3), EPS1);
        assertEquals(5, p2__m2.distance(pm1__2), EPS1);
        assertEquals(Math.E*2, pmPI__mE.distance(pmPI__E), EPS1);
        assertEquals(Math.sqrt(Math.PI*Math.PI*4+Math.E*Math.E*4), pmPI__mE.distance(pPI__E), EPS1);
        // distance from self should be zero:
        assertEquals(0, pmPI__mE.distance(pmPI__mE), EPS1*EPS1);
    }

    @Test
    // test the equals function:
    public void testEquals(){
        assertNotEquals(p2__1, new Segment_2D(p2__1, p2__1));
        assertEquals(pmPI__E, new Point_2D(Math.PI * -1, Math.E));
    }

    @Test
    // tests the close2equals function:
    public void testClose2equals(){
        assertTrue(pPI__E.close2equals(p3_14159__2_71828, EPS1));
        assertFalse(pPI__E.close2equals(p3_14159__2_71828, 0));
    }

    @Test
    // tests the vector function:
    public void testVector(){
        // test with options of positive-negative values:
        assertEquals(p0__3, p2__m2.vector(p2__1));
        assertEquals(p3__4, pm1__m3.vector(p2__1));
        assertEquals(pm3__4, p2__m2.vector(pm1__2));
        assertEquals(pm3__m1, p2__m2.vector(pm1__m3));
        // test opposite vectors:
        assertEquals(pm3__m4, p2__1.vector(pm1__m3));
    }

    @Test
    // tests the move function:
    public void testMove(){
        Point_2D p = new Point_2D(pm1__m3);
        p.move(p3__4); assertEquals(p2__1, p);
        p.move(p0__0); assertEquals(p2__1, p);
    }

    @Test
    // tests the rotate function:
    public void testRotate(){
        Point_2D p = new Point_2D(pm1__m3);
        // calibration check: rotate by 0:
        p.rotate(p0__0, 0);
        assertTrue(equals(p, pm1__m3));
        // rotate and test:
        p.rotate(pm1__m1, -90);
        assertTrue(equals(p, pm3__m1));
        // rotate and test:
        p = new Point_2D(p2_5__3_7);
        p.rotate(pmPI__mE, 67.3);
        assertTrue(equals(p, pm6_8855__4_9631));

    }

    /**
     * auxiliary function for comparing points;
     * checks that the x,y values of 2 points are closer than epsilon
     * @param p1 first point
     * @param p2 second point
     * @return true if points are closer than epsilon, otherwise false.
     */
    private boolean equals(Point_2D p1, Point_2D p2){
        return (Math.abs(p1.x()-p2.x())<EPS1 && Math.abs(p1.y()-p2.y())<EPS1);
    }
}
