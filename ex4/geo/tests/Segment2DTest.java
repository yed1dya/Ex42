// 207404997
package exe.ex4.geo.tests;

import exe.ex4.geo.Point_2D;
import exe.ex4.geo.Segment_2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class Segment2DTest {

    final double EPS1 = 0.001;
    Point_2D pPI__E = new Point_2D(Math.PI, Math.E), p6__5 = new Point_2D(6,5),
            p4_5__4_5 = new Point_2D(4.5,4.5), p5_5__1_5 = new Point_2D(5.5,1.5),
            p0__0 =new Point_2D(0,0), p3__7 =new Point_2D(3,7), pm1__m1 =new Point_2D(-1,-1),
            p2__6 =new Point_2D(2,6), p2__1 =new Point_2D(2,1), p5__1 =new Point_2D(5,1),
            p3__3 =new Point_2D(3,3), p1__5 =new Point_2D(1,5), p6__2 =new Point_2D(6,2),
            p4_5__6=new Point_2D(4.5,6), p5_5__3=new Point_2D(5.5,3), pm3__m1=new Point_2D(-3,-1),
            p2__2=new Point_2D(2, 2), p3__4=new Point_2D(3,4), pm3__m4=new Point_2D(-3,-4),
            p4__4=new Point_2D(4,4), pm1__1=new Point_2D(-1,1), p2__m4=new Point_2D(2,-4),
            pm6_8855__4_9631=new Point_2D(-6.8855,4.9631), p7_05__4_3=new Point_2D(7.05,4.3),
            p5_14159__3_71828=new Point_2D(5.14159,3.71828), pm4_8855__5_9631=new Point_2D(-4.8855,5.9631),
            p6_35__6_4=new Point_2D(6.35,6.4), pm1_5747__3_7073=new Point_2D(-1.57467,3.7073),
            pm0_8456__m2_0778=new Point_2D(-0.8456,-2.0778);

    @Test
    // tests the basic constructor:
    public void testSegmentConstructor(){
        Segment_2D seg = new Segment_2D(pPI__E, pm6_8855__4_9631);
        // check that the points were copied, not linked (should be a new object):
        assertNotSame(seg.get_p1(), pPI__E);
        // check that the points have the expected values:
        assertEquals(seg.get_p1(), pPI__E);
        assertEquals(seg.get_p2(), pm6_8855__4_9631);
    }

    @Test
    // tests the copy constructor:
    public void testSegmentCopyConstructor(){
        Segment_2D seg = new Segment_2D(pPI__E, pm6_8855__4_9631);
        Segment_2D copy = new Segment_2D(seg);
        // check that segments are equal:
        assertTrue(equals(seg, copy));
        // check that segments are different objects:
        assertNotSame(seg, copy);
    }

    @Test
    // tests the get_p1 function:
    public void testGet_p1(){
        Segment_2D seg = new Segment_2D(pPI__E, pm6_8855__4_9631);
        assertTrue(equals(seg.get_p1(), pPI__E));
    }

    @Test
    // tests the get_p2 function:
    public void testGet_p2(){
        Segment_2D seg = new Segment_2D(pPI__E, pm6_8855__4_9631);
        assertTrue(equals(seg.get_p2(), pm6_8855__4_9631));
    }

    @Test
    // tests the contains function with expected and unexpected points:
    public void testContains(){
        Segment_2D seg = new Segment_2D(pm3__m4, p3__4);
        assertTrue(seg.contains(p0__0));
        assertFalse(seg.contains(pm1__m1));
    }

    @Test
    // tests the area function; should be 0:
    public void testArea(){
        Segment_2D seg = new Segment_2D(pPI__E, pm6_8855__4_9631);
        assertEquals(0, seg.area(), EPS1);
    }

    @Test
    // tests the perimeter function; should be twice the length:
    public void testPerimeter(){
        Segment_2D seg = new Segment_2D(pPI__E, pm6_8855__4_9631);
        assertEquals(20.550600546646308, seg.perimeter(), EPS1);
        // segment with both points at the same point should have perimeter 0:
        seg = new Segment_2D(pm3__m4, pm3__m4);
        assertEquals(0, seg.perimeter(), EPS1);
    }

    @Test
    // tests the 'translate' function:
    public void testTranslate(){
        Segment_2D seg = new Segment_2D(pPI__E, pm6_8855__4_9631),
                exp = new Segment_2D(p5_14159__3_71828, pm4_8855__5_9631);
        assertFalse(equals(exp, seg));
        // move and test:
        seg.translate(p2__1);
        assertTrue(equals(exp, seg));
    }

    @Test
    // tests the copy function:
    public void testCopy(){
        Segment_2D seg = new Segment_2D(pPI__E, pm6_8855__4_9631);
        Segment_2D copy = (Segment_2D) seg.copy();
        // check that segments are equal:
        assertTrue(equals(seg, copy));
        // check that segments are different objects:
        assertNotSame(seg, copy);
    }

    @Test
    // tests the scale function:
    public void testScale(){
        Segment_2D seg = new Segment_2D(p2__6, p3__3),
                exp = new Segment_2D(seg);
        // calibration check:
        assertTrue(equals(exp, seg));
        // scale and test:
        seg.scale(p1__5, 2);
        assertFalse(equals(exp, seg));
        exp = new Segment_2D(p3__7, p5__1);
        assertTrue(equals(exp, seg));
        // scale and test:
        seg.scale(p6__2, 0.5);
        assertFalse(equals(exp, seg));
        exp = new Segment_2D(p4_5__4_5, p5_5__1_5);
        assertTrue(equals(exp, seg));
        // scale and test:
        seg.scale(p6__2, 2);
        assertFalse(equals(exp, seg));
        exp = new Segment_2D(p3__7, p5__1);
        assertTrue(equals(exp, seg));
        // scale and test:
        seg.scale(p6__5, 0.5);
        assertFalse(equals(exp, seg));
        exp = new Segment_2D(p4_5__6, p5_5__3);
        assertTrue(equals(exp, seg));
        // scale and test:
        seg.scale(p6__5, -0.7);
        assertFalse(equals(exp, seg));
        exp = new Segment_2D(p7_05__4_3, p6_35__6_4);
        assertTrue(equals(exp, seg));
    }

    @Test
    // tests the rotate function:
    public void testRotate(){
        Segment_2D seg = new Segment_2D(p5__1, p2__6),
                exp = new Segment_2D(seg);
        // calibration check:
        assertTrue(equals(exp, seg));
        // rotation by 0 or multiples of 360 should have no effect:
        exp.rotate(p6__5, 0);
        assertTrue(equals(exp, seg));
        seg.rotate(p6__5, 360);
        assertTrue(equals(exp, seg));
        seg.rotate(p6__5, 720);
        assertTrue(equals(exp, seg));
        // rotation by 1 degree should have an effect:
        seg.rotate(p6__5, 1);
        assertFalse(equals(exp, seg));
        // negative degrees should have the opposite effect:
        seg.rotate(p6__5, -1);
        assertTrue(equals(exp, seg));
        // rotation and test:
        seg.rotate(p4__4, 180);
        assertFalse(equals(exp, seg));
        exp = new Segment_2D(p3__7, p6__2);
        assertTrue(equals(exp, seg));
        // rotate back and test:
        seg.rotate(p4__4, 180);
        assertFalse(equals(exp, seg));
        exp = new Segment_2D(p5__1, p2__6);
        assertTrue(equals(exp, seg));
        // rotate and test:
        seg.rotate(p2__1, 180);
        assertFalse(equals(exp, seg));
        exp = new Segment_2D(pm1__1, p2__m4);
        assertTrue(equals(exp, seg));
        // rotate and test:
        seg.rotate(pm1__m1, 90);
        assertFalse(equals(exp, seg));
        exp = new Segment_2D(pm3__m1, p2__2);
        assertTrue(equals(exp, seg));
        // tests negative degrees:
        seg.rotate(pm1__m1, -90);
        assertFalse(equals(exp, seg));
        exp = new Segment_2D(pm1__1, p2__m4);
        assertTrue(equals(exp, seg));
        // test with difficult values:
        seg.rotate(p5_14159__3_71828, -23.781);
        assertFalse(equals(exp, seg));
        exp = new Segment_2D(pm1_5747__3_7073, pm0_8456__m2_0778);
        assertTrue(equals(exp, seg));
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

    /**
     * auxiliary function for comparing segments;
     * checks that segments have the same points, in the same order
     * @param s1 first segment
     * @param s2 second segment
     * @return true if segments have the same points, otherwise false.
     */
    private boolean equals(Segment_2D s1, Segment_2D s2){
        return equals(s1.get_p1(), s2.get_p1()) && equals(s1.get_p2(), s2.get_p2());
    }
}
