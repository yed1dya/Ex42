// 207404997
package exe.ex4.geo.tests;

import exe.ex4.geo.Point_2D;
import exe.ex4.geo.Rect_2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class Rect2DTest {
    final double EPS1 = 0.001;
    Point_2D p0__0=new Point_2D(0,0), p1__0=new Point_2D(1,0), pm3__4_01=new Point_2D(-3,4.01),
            p0__1=new Point_2D(0,1), p1__1=new Point_2D(1,1), p5__5=new Point_2D(5,5),
            p5__2=new Point_2D(5,2), p3__0=new Point_2D(3,0), p2__2=new Point_2D(2,2),
            p3__4=new Point_2D(3,4), pm7__m9=new Point_2D(-7,-9), pm2__m1=new Point_2D(-2,-1),
            p2_2__2_7 =new Point_2D(2.2,2.7), p1__4=new Point_2D(1,4), p6__4=new Point_2D(6,4),
            p3__1 =new Point_2D(3,1), p6__6 =new Point_2D(6,6), p0__2=new Point_2D(0,2),
            p0__5=new Point_2D(0,5), pm5__m2=new Point_2D(-5,-2), pm2_9__6_2=new Point_2D(-2.9,6.2),
            p1_5842__0_0437=new Point_2D(1.5842,0.0437), p2_6237__4_9344=new Point_2D(2.6237,4.9344),
            p3_6019__4_7265=new Point_2D(3.6019,4.7265), p2_5623__m0_1642=new Point_2D(2.5623,-0.1642),
            p8__6=new Point_2D(8,6), p8__3=new Point_2D(8,3), p6__3=new Point_2D(6,3),
            pm2__5=new Point_2D(-2,5), p5__m1=new Point_2D(5,-1), p6_2__6_2=new Point_2D(6.2, 6.2),
            p6_2__m1_6=new Point_2D(6.2,-1.6), pm2_9__m1_6=new Point_2D(-2.9, -1.6),
            pm9_4599__m18_1199=new Point_2D(-9.4599, -18.1199), pm14_9199__m18_1199=new Point_2D(-14.9199, -18.1199),
            pm14_9199__m13_44=new Point_2D(-14.9199, -13.44), pm9_4599__m13_44=new Point_2D(-9.4599, -13.44),
            p5__1=new Point_2D(5,1);

    @Test
    // tests the basic constructor:
    public void testRecConstructor(){
        Rect_2D rec1 = new Rect_2D(pm2__m1, p2_2__2_7),
                rec2 = new Rect_2D(p2_2__2_7, pm2__m1);
        // test that same points in opposite order create the same rectangle:
        assertTrue(equals(rec1, rec2));
        // check that the points were copied, not linked (should be a new object):
        Point_2D[] points1 = rec1.points();
        assertTrue(equals(points1[3], pm2__m1));
        assertNotSame(points1[3], pm2__m1);
        assertTrue(equals(points1[1], p2_2__2_7));
        assertNotSame(points1[1], p2_2__2_7);
    }

    @Test
    // tests the copy constructor:
    public void testRecCopyConstructor(){
        Rect_2D rec = new Rect_2D(pm2__m1, p2_2__2_7),
                copy = new Rect_2D(rec);
        // check that rectangles are equal:
        assertTrue(equals(rec, copy));
        // check that rectangles are different objects:
        assertNotSame(rec, copy);
    }

    @Test
    // tests the contains function:
    public void testContains(){
        Rect_2D rec = new Rect_2D(pm7__m9,p3__4);
        assertTrue(rec.contains(p1__0));
        assertTrue(rec.contains(p3__4));
        assertTrue(rec.contains(p3__0));
        assertFalse(rec.contains(pm3__4_01));
    }

    @Test
    // tests the area function:
    public void testArea(){
        Rect_2D rec1 = new Rect_2D(p1__1,p3__4);
        // expected area
        assertEquals(6, rec1.area(), EPS1);
        // rotate; area should stay constant:
        rec1.rotate(p2__2, 47);
        assertEquals(6, rec1.area(), EPS1);
        // rescale by 2; area should grow by 4:
        rec1.scale(p1__1, 2);
        assertEquals(24, rec1.area(), EPS1);
        // rescale by 1.7; area should grow by (1.7)^2:
        rec1.scale(p1__1, 1.7);
        assertEquals(24*1.7*1.7, rec1.area(), EPS1);
        // rescale by 0.6; area should change by (0.6)^2:
        rec1.scale(p1__1, 0.6);
        assertEquals(24*1.7*1.7*0.6*0.6, rec1.area(), EPS1);
        // test that the sum of areas of two rectangles is equal to the area of the rectangle that is them combined:
        Rect_2D rec2 = new Rect_2D(p0__0, p5__2),
                rec3 = new Rect_2D(p0__2, p5__5);
        rec1 = new Rect_2D(p0__0, p5__5);
        assertEquals(rec1.area(), rec2.area()+rec3.area(), EPS1);
        // rotate and test again:
        rec1.rotate(p6__4, 37);
        rec2.rotate(p0__5, 293);
        rec3.rotate(p2_2__2_7, 109);
        assertEquals(rec1.area(), rec2.area()+rec3.area(), EPS1);
        // test a rectangle with same y values:
        rec1 = new Rect_2D(p0__2, p5__2);
        assertEquals(0, rec1.area(), EPS1);
        // test a rectangle with same x values:
        rec1 = new Rect_2D(p5__2, p5__5);
        assertEquals(0, rec1.area(), EPS1);
    }
    
    @Test
    // tests the perimeter function:
    public void testPerimeter(){
        Rect_2D rec1 = new Rect_2D(p1__1,p3__4);
        // expected perimeter
        assertEquals(10, rec1.perimeter(), EPS1);
        // rotate; perimeter should stay constant:
        rec1.rotate(p2__2, 47);
        assertEquals(10, rec1.perimeter(), EPS1);
        // rescale by 2; perimeter should grow by 2:
        rec1.scale(p1__1, 2);
        assertEquals(20, rec1.perimeter(), EPS1);
        // rescale by 1.7; perimeter should grow by 1.7:
        rec1.scale(p1__1, 1.7);
        assertEquals(20*1.7, rec1.perimeter(), EPS1);
        // rescale by 0.2; perimeter should change by 0.2:
        rec1.scale(p1__1, 0.2);
        assertEquals(20*1.7*0.2, rec1.perimeter(), EPS1);
        // test a rectangle with same y values:
        rec1 = new Rect_2D(p0__2, p5__2);
        assertEquals(10, rec1.perimeter(), EPS1);
        // test a rectangle with same x values:
        rec1 = new Rect_2D(p5__2, p5__5);
        assertEquals(6, rec1.perimeter(), EPS1);
    }

    @Test
    // tests the 'translate' function:
    public void testTranslate(){
        Rect_2D rec = new Rect_2D(p1__1,p3__4);
        Point_2D[] exp = {p1__4, p3__4, p3__1, p1__1};
        // calibration check; original rectangle:
        assertTrue(equals(rec.points(), exp));
        // move and test that the points are where they should be:
        rec.translate(p5__2);
        assertFalse(equals(rec.points(), exp));
        exp = new Point_2D[]{p6__6, p8__6, p8__3, p6__3};
        assertTrue(equals(rec.points(), exp));
        // move with opposite vector; points should be back in the original spot:
        rec.translate(pm5__m2);
        assertFalse(equals(rec.points(), exp));
        exp = new Point_2D[]{p1__4, p3__4, p3__1, p1__1};
        assertTrue(equals(rec.points(), exp));
    }

    @Test
    // tests the copy function:
    public void testCopy(){
        Rect_2D rec = new Rect_2D(p1__1,p3__4);
        Rect_2D copy = (Rect_2D) rec.copy();
        // check that rectangles are equal:
        assertTrue(equals(rec, copy));
        // check that rectangles are different objects:
        assertNotSame(rec, copy);
    }

    @Test
    // tests the scale function:
    public void testScale(){
        Rect_2D rec = new Rect_2D(pm2__m1, p5__5);
        Point_2D[] exp = {pm2__5, p5__5, p5__m1, pm2__m1};
        // calibration check:
        assertTrue(equals(rec.points(), exp));
        // scale and test:
        rec.scale(p1__1, 1.3);
        assertFalse(equals(rec.points(), exp));
        exp = new Point_2D[]{pm2_9__6_2, p6_2__6_2, p6_2__m1_6, pm2_9__m1_6};
        assertTrue(equals(rec.points(), exp));
        // scale and test:
        rec.scale(pm7__m9, -0.6);
        assertFalse(equals(rec.points(), exp));
        System.out.println(rec);
        exp = new Point_2D[]{pm9_4599__m18_1199, pm14_9199__m18_1199, pm14_9199__m13_44, pm9_4599__m13_44};
        assertTrue(equals(rec.points(), exp));
    }

    @Test
    // test the rotate function:
    public void testRotate(){
        Rect_2D rec = new Rect_2D(p0__1, p5__2);
        Point_2D[] exp = {p0__2, p5__2, p5__1, p0__1};
        // calibration check:
        assertTrue(equals(exp, rec.points()));
        // calibration check; rotate by multiple of 360 should have no effect:
        Rect_2D rec2 = new Rect_2D(rec);
        rec2.rotate(pm7__m9, 0);
        assertTrue(equals(rec, rec2));
        rec2.rotate(pm7__m9, 360);
        assertTrue(equals(rec, rec2));
        rec2.rotate(pm7__m9, 720);
        assertTrue(equals(rec, rec2));
        // rotate and check:
        rec.rotate(p2__2, 78);
        exp = new Point_2D[]{p1_5842__0_0437, p2_6237__4_9344, p3_6019__4_7265, p2_5623__m0_1642};
        assertTrue(equals(rec.points(), exp));
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
     * helper function for comparing rectangles
     * @param rec1 first rectangle
     * @param rec2 second rectangle
     * @return true if rectangles have the same points, otherwise false.
     */
    private boolean equals(Rect_2D rec1, Rect_2D rec2){
        Point_2D[] a = rec1.points(), b = rec2.points();
        for(int i=0; i<4; i++){
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
