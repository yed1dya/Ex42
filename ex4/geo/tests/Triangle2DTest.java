// 207404997
package exe.ex4.geo.tests;
import exe.ex4.geo.Point_2D;
import exe.ex4.geo.Triangle_2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class Triangle2DTest {

    final double EPS1 = 0.001;
    Point_2D pPI__E = new Point_2D(Math.PI, Math.E), p2__5 = new Point_2D(2,5), p6__2 = new Point_2D(6,2),
            p8_1416__5_7183 = new Point_2D(8.1416,5.7183), p0__0 =new Point_2D(0,0),
            p3__7 =new Point_2D(3,7), pm18_2084__m30_6817 =new Point_2D(-18.2084,-30.6817),
            p2__6 =new Point_2D(2,6), p2__1 =new Point_2D(2,1), p5__1 =new Point_2D(5,1),
            p3__3 =new Point_2D(3,3), p1__5 =new Point_2D(1,5), p3__2 =new Point_2D(3,2),
            pm15_2084__m30_6817=new Point_2D(-15.2084,-30.6817), pm18_2084__m29_6817=new Point_2D(-18.2084,-29.6817),
            pm3__m1=new Point_2D(-3,-1), p2__2=new Point_2D(2, 2), p3_7__m5_8=new Point_2D(3.7,-5.8),
            pm3__m4=new Point_2D(-3,-4), p4__4=new Point_2D(4,4), p11_8__m5_8=new Point_2D(11.8,-5.8),
            p2__m4=new Point_2D(2,-4), pm6_8855__4_9631=new Point_2D(-6.8855,4.9631), p6_4__m0_4=new Point_2D(6.4,-0.4),
            p5_14159__3_71828=new Point_2D(5.14159,3.71828), pm4_8855__5_9631=new Point_2D(-4.8855,5.9631),
            pm26_35__m36_4=new Point_2D(-26.35,-36.4), p13_9157__20_6921=new Point_2D(13.9157,20.6921),
            p8_1416__6_7183=new Point_2D(8.1416,6.7183), pmPI__mE = new Point_2D(-1*Math.PI, -1*Math.E),
            p3_3857__20_6921 = new Point_2D(3.3857,20.6921), p10_4057__13_6721 = new Point_2D(10.4057,13.6721),
            p1__1 =new Point_2D(1,1), p9_8738__10_2104 =new Point_2D(9.8738,10.2104),
            p6_7148__10_2104 =new Point_2D(6.7148,10.2104), p8_8208__8_1044 =new Point_2D(8.8208,8.1044),
            p7_127__6_3804 =new Point_2D(7.127,6.3804), p7_6994__9_3253 =new Point_2D(7.6994,9.3253),
            p5_3545__7_7437=new Point_2D(5.3545,7.7437), p1_3__6_8=new Point_2D(1.3,6.8),
            p3_8__10_5=new Point_2D(3.8,10.5), p11_1416__5_7183=new Point_2D(11.1416,5.7183);

    @Test
    // tests the basic constructor:
    public void testTriangleConstructor(){
        Triangle_2D tri = new Triangle_2D(p1__5, p2__m4, pm3__m4);
        // check that the points were copied, not linked (should be a new object):
        Point_2D[] points = tri.getAllPoints();
        assertNotSame(points[0], p1__5);
        assertNotSame(points[1], p2__m4);
        assertNotSame(points[2], pm3__m4);
        // check that points are as expected:
        assertEquals(points[0], p1__5);
        assertEquals(points[1], p2__m4);
        assertEquals(points[2], pm3__m4);
    }

    @Test
    // tests the copy constructor:
    public void testTriangleCopyConstructor(){
        Triangle_2D tri = new Triangle_2D(pPI__E, pm6_8855__4_9631, pm3__m4);
        Triangle_2D copy = new Triangle_2D(tri);
        // check that triangles are equal:
        assertTrue(equals(tri, copy));
        // check that triangles are different objects:
        assertNotSame(tri, copy);
    }

    @Test
    // test the getAllPoints function:
    public void testGetAllPoints(){
        Triangle_2D tri = new Triangle_2D(pPI__E, pm6_8855__4_9631, pm3__m4);
        Point_2D[] points = tri.getAllPoints();
        // check that the points were copied, not linked (should be a new object):
        assertNotSame(points[0], pPI__E);
        assertNotSame(points[1], pm6_8855__4_9631);
        assertNotSame(points[2], pm3__m4);
        // check that points are as expected:
        assertEquals(points[0], pPI__E);
        assertEquals(points[1], pm6_8855__4_9631);
        assertEquals(points[2], pm3__m4);
    }

    @Test
    // test the contains function:
    public void testContains(){
        Triangle_2D tri = new Triangle_2D(pm3__m1, p5__1, p3__7);
        assertTrue(tri.contains(p2__2));
        assertTrue(tri.contains(p4__4));
        assertFalse(tri.contains(p2__6));
        assertTrue(tri.contains(pm3__m1));
    }

    @Test
    // tests the area function:
    public void testArea(){
        Triangle_2D tri = new Triangle_2D(p2__1, p5__1, p2__6);
        assertEquals(7.5, tri.area(), EPS1);
        tri = new Triangle_2D(pm3__m1, p5__1, p3__7);
        assertEquals(25.9999, tri.area(), EPS1);
        // triangle with 2 same points should have area 0:
        tri = new Triangle_2D(p3_8__10_5, pPI__E, pPI__E);
        assertEquals(0, tri.area(), EPS1);
    }

    @Test
    // tests the perimeter function:
    public void testPerimeter(){
        Triangle_2D tri = new Triangle_2D(p2__1, p5__1, p2__5);
        assertEquals(12, tri.perimeter(), EPS1);
        // triangle with 3 corners on the same point should have perimeter 0:
        tri = new Triangle_2D(p2__1, p2__1, p2__1);
        assertEquals(0, tri.perimeter(), EPS1);
        // test with difficult values:
        tri = new Triangle_2D(pm4_8855__5_9631, pmPI__mE, p3_8__10_5);
        assertEquals(33.5840, tri.perimeter(), EPS1);
    }

    @Test
    // tests the 'translate' function:
    public void testTranslate(){
        Triangle_2D tri = new Triangle_2D(p2__1, p5__1, p2__2),
                exp = new Triangle_2D(tri);
        // calibration check: move by 0 should have no effect:
        tri.translate(p0__0);
        assertTrue(equals(exp, tri));
        // move and test:
        tri.translate(p1__1);
        assertFalse(equals(exp, tri));
        exp = new Triangle_2D(p3__2, p6__2, p3__3);
        assertTrue(equals(exp, tri));
        // check with difficult values:
        tri.translate(p5_14159__3_71828);
        assertFalse(equals(exp, tri));
        exp = new Triangle_2D(p8_1416__5_7183, p11_1416__5_7183, p8_1416__6_7183);
        assertTrue(equals(exp, tri));
        // check with negative values:
        tri.translate(pm26_35__m36_4);
        assertFalse(equals(exp, tri));
        exp = new Triangle_2D(pm18_2084__m30_6817, pm15_2084__m30_6817, pm18_2084__m29_6817);
        assertTrue(equals(exp, tri));
    }

    @Test
    // tests the copy function:
    public void testCopy(){
        Triangle_2D tri = new Triangle_2D(pPI__E, pm6_8855__4_9631, pm18_2084__m29_6817);
        Triangle_2D copy = (Triangle_2D) tri.copy();
        // check that triangles are equal:
        assertTrue(equals(tri, copy));
        // check that triangles are different objects:
        assertNotSame(tri, copy);
    }

    @Test
    // tests the scale function:
    public void testScale(){
        Triangle_2D tri = new Triangle_2D(p2__1, p5__1, p3__3),
                exp = new Triangle_2D(tri);
        // scale and test:
        tri.scale(p1__5, 2.7);
        assertFalse(equals(exp, tri));
        exp = new Triangle_2D(p3_7__m5_8, p11_8__m5_8, p6_4__m0_4);
        assertTrue(equals(exp, tri));
        // test negative values:
        tri.scale(p8_1416__5_7183, -1.3);
        assertFalse(equals(exp, tri));
        exp = new Triangle_2D(p13_9157__20_6921, p3_3857__20_6921, p10_4057__13_6721);
        assertTrue(equals(exp, tri));
        // test value smaller than 1:
        tri.scale(p8_1416__5_7183, 0.3);
        assertFalse(equals(exp, tri));
        exp = new Triangle_2D(p9_8738__10_2104, p6_7148__10_2104, p8_8208__8_1044);
        assertTrue(equals(exp, tri));
    }

    @Test
    // test the rotate function:
    public void testRotate(){
        Triangle_2D tri = new Triangle_2D(p2__1, p5__1, p3__3),
                exp = new Triangle_2D(tri);
        // calibration check; rotate by multiple of 360 should have no effect:
        tri.rotate(p8_1416__5_7183, 0);
        assertTrue(equals(exp, tri));
        tri.rotate(p10_4057__13_6721, 360);
        assertTrue(equals(exp, tri));
        tri.rotate(pm15_2084__m30_6817, 720);
        // rotate by 1 degree:
        tri.rotate(pm18_2084__m30_6817, 1);
        assertFalse(equals(exp, tri));
        // negative degrees should have the opposite effect:
        tri.rotate(pm18_2084__m30_6817, -1);
        assertTrue(equals(exp, tri));
        // rotate and test:
        tri.rotate(p1_3__6_8, 79);
        assertFalse(equals(exp, tri));
        exp = new Triangle_2D(p7_127__6_3804, p7_6994__9_3253, p5_3545__7_7437);
        assertTrue(equals(exp, tri));
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
     * helper function for comparing triangles;
     * @param t1 first triangle
     * @param t2 second triangle
     * @return true if triangles have the same points, otherwise false.
     */
    private boolean equals(Triangle_2D t1, Triangle_2D t2){
        Point_2D[] t1a = t1.getAllPoints(), t2a = t2.getAllPoints();
        return equals(t1a[0], t2a[0]) && equals(t1a[1], t2a[1]) && equals(t1a[2], t2a[2]);
    }
}
