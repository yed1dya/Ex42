// 207404997
package exe.ex4.geo.tests;

import exe.ex4.geo.Circle_2D;
import exe.ex4.geo.Point_2D;
import org.junit.Test;

import static org.junit.Assert.*;
public class Circle2DTest {
    final double EPS1 = 0.001;
    Point_2D p0__0 =new Point_2D(0,0), p2__1 =new Point_2D(2,1), p5__1 =new Point_2D(5,1),
            p2__2=new Point_2D(2, 2), p3_7__m5_8=new Point_2D(3.7,-5.8), p1_3__6_8=new Point_2D(1.3,6.8),
            p3__4=new Point_2D(3,4), pm7__m9=new Point_2D(-7,-9), p0__5=new Point_2D(0,5),
            p2_2__2_7 =new Point_2D(2.2,2.7), p3_1__4_1 =new Point_2D(3.1,4.1), p4__5=new Point_2D(4,5);
    @Test
    // tests the basic constructor:
    public void testCircleConstructor(){
        Circle_2D circle = new Circle_2D(p4__5, 5);
        // check that the point is a deep copy and not linked (should be a different object):
        assertNotSame(circle.getCenter(), p4__5);
        // check that circle has the correct point & radius:
        assertTrue(equals(circle.getCenter(), p4__5));
        assertEquals(5, circle.getRadius(), 0);
    }

    @Test
    // tests the getRadius function:
    public void testGetRadius(){
        Circle_2D c = new Circle_2D(p0__0, Math.PI);
        assertEquals(Math.PI, c.getRadius(), 0);
    }

    @Test
    // tests the toString function:
    public void testToString(){
        Circle_2D c = new Circle_2D(p1_3__6_8, 4.78);
        String exp = "1.3,6.8, 4.78";
        assertEquals(exp, c.toString());
    }

    @Test
    // tests the contains function:
    public void testContains(){
        Circle_2D c = new Circle_2D(p0__0, 5);
        assertTrue(c.contains(p2__1));
        assertFalse(c.contains(p5__1));
        assertTrue(c.contains(p0__5));
        assertTrue(c.contains(p3__4));
        assertFalse(c.contains(p3_1__4_1));
    }

    @Test
    // tests the area function:
    public void testArea(){
        Circle_2D c = new Circle_2D(p1_3__6_8, Math.PI);
        assertEquals(Math.pow(Math.PI, 3), c.area(), 0);
        c = new Circle_2D(p2_2__2_7, 37.843);
        assertEquals(Math.PI*37.843*37.843, c.area(), EPS1);
    }

    @Test
    // tests the perimeter function:
    public void testPerimeter(){
        Circle_2D c = new Circle_2D(p1_3__6_8, Math.PI);
        assertEquals(Math.PI*Math.PI*2, c.perimeter(), 0);
        c = new Circle_2D(p2_2__2_7, 37.843);
        assertEquals(Math.PI*2*37.843, c.perimeter(), EPS1);
    }

    @Test
    // tests the 'translate' function:
    public void testTranslate(){
        Circle_2D c = new Circle_2D(p1_3__6_8, 7.63),
                exp = new Circle_2D(p1_3__6_8, 7.63);
        // calibration check: translate by 0:
        c.translate(p0__0);
        assertTrue(equals(exp, c));
        // move and test:
        exp = new Circle_2D(new Point_2D(-5.7,-2.2), 7.63);
        c.translate(pm7__m9);assertTrue(equals(exp, c));
    }

    @Test
    // tests the copy function:
    public void testCopy(){
        Circle_2D c = new Circle_2D(p1_3__6_8, 7.63);
        Circle_2D copy = (Circle_2D) c.copy();
        // check that circles are equal:
        assertTrue(equals(c, copy));
        // check that circles are different objects:
        assertNotSame(c, copy);
    }

    @Test
    // tests the scale function:
    public void testScale(){
        Circle_2D c = new Circle_2D(p1_3__6_8, 7.63),
                exp = new Circle_2D(new Point_2D(2.2789,11.92039), 13.37539);
        c.scale(p0__0, 1.753);
        assertTrue(equals(exp, c));
        c.scale(p2_2__2_7, -2.1);
        exp = new Circle_2D(new Point_2D(2.03431,-16.6628), -28.088319);
        assertTrue(equals(exp, c));
        c.scale(p0__5, 0.23);
        exp = new Circle_2D(new Point_2D(0.4678,0.0175),-6.4603);
        assertTrue(equals(exp, c));
    }

    @Test
    // tests the rotate function:
    public void testRotate(){
        Circle_2D c = new Circle_2D(p3_7__m5_8, 5.62),
                exp = new Circle_2D(p3_7__m5_8, 5.62);
        // calibration check; rotate by multiple of 360 should have no effect:
        c.rotate(p0__5, 0);
        assertTrue(equals(exp, c));
        c.rotate(p2_2__2_7, 360);
        assertTrue(equals(exp, c));
        c.rotate(p0__0, 720);
        assertTrue(equals(exp, c));
        // rotate and check:
        c.rotate(p2__2, 38.7);
        exp = new Circle_2D(new Point_2D(8.2036,-3.0244), 5.62);
        assertTrue(equals(exp, c));
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
     * helper function for comparing circles;
     * checks that circles have the same center points, and equal radius
     * @param c1 first circle
     * @param c2 second circle
     * @return true if circles have the same points and radius, otherwise false.
     */
    private boolean equals(Circle_2D c1, Circle_2D c2){
        return equals(c1.getCenter(), c2.getCenter()) && Math.abs(c1.getRadius()-c2.getRadius())<EPS1;
    }
}