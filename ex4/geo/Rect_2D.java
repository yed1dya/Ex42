// 207404997
package exe.ex4.geo;

/*
 * translate, scale, rotate functions utilize the methods in the Point_2D class;
 * in general, simply apply the requested method to the points of the rectangle.
 * uses distance function from Point_2D as well.
 */

import java.util.Arrays;

/**
 * This class represents a 2D axis parallel rectangle.
 * Ex4: you should implement this class!
 * @author I2CS
 */

public class Rect_2D implements GeoShape {
	private final Point_2D _p1, _p2, _p3, _p4;
	public static final double EPS1 = 0.001;

	/**
	 * basic constructor. gets 2 points;
	 * constructs the 4 corners based on the x,y values of the given points
	 * @param p1 first point
	 * @param p2 second point
	 */
	public Rect_2D(Point_2D p1, Point_2D p2) {
		this._p1 = new Point_2D(Math.min(p1.x(),p2.x()), Math.max(p1.y(),p2.y()));
		this._p4 = new Point_2D(Math.min(p1.x(),p2.x()), Math.min(p1.y(),p2.y()));
		this._p2 = new Point_2D(Math.max(p1.x(),p2.x()), Math.max(p1.y(),p2.y()));
		this._p3 = new Point_2D(Math.max(p1.x(),p2.x()), Math.min(p1.y(),p2.y()));
	}

	/**
	 * copy constructor
	 * @param t1 rectangle to be copied
	 */
	public Rect_2D(Rect_2D t1) {
		this._p1 = new Point_2D(t1._p1);
		this._p2 = new Point_2D(t1._p2);
		this._p3 = new Point_2D(t1._p3);
		this._p4 = new Point_2D(t1._p4);
	}

	/**
	 * checks if a point is inside a rectangle;
	 * creates 4 new triangle - between the given point and every 2 points of the rectangle.
	 * calculates area of all 4 new triangles. if equal to main rectangle area (up to given epsilon) -
	 * - the point is inside the rectangle. if sum of areas is more than main area - point is outside.
	 * @param ot - a query 2D point
	 * @return true if point is inside rectangle, otherwise false
	 */
	@Override
	public boolean contains(Point_2D ot) {
		Point_2D p1=this._p1, p2=this._p2, p3=this._p3, p4=this._p4;
		Triangle_2D tri1 = new Triangle_2D(p1, p2, ot),
				tri2 = new Triangle_2D(p2, p3, ot),
				tri3 = new Triangle_2D(p3, p4, ot),
				tri4 = new Triangle_2D(p4, p1, ot);
		double tri1Area = tri1.area(),
				tri2Area = tri2.area(),
				tri3Area = tri3.area(),
				tri4Area = tri4.area(),
				origArea = this.area(),
				allArea = tri1Area+tri2Area+tri3Area+tri4Area;
		return Math.abs(allArea-origArea)<EPS1;
	}

	/**
	 * uses the distance function to get the distances between a point and the 2 points next to it,
	 * and multiplies the distances
	 * @return the rectangle's area
	 */
	@Override
	public double area() {
		double ans;
		double _xLength = this._p4.distance(this._p3),
				_yLength = this._p4.distance(this._p1);
		ans=_yLength*_xLength;
		return ans;
	}

	/**
	 * uses the distance function to get the distances between a point and the 2 points next to it,
	 * and combines the distances
	 * @return the rectangle's perimeter
	 */
	@Override
	public double perimeter() {
		double _xLength = this._p4.distance(this._p3),
				_yLength = this._p4.distance(this._p1);
		return (_xLength+_xLength+_yLength+_yLength);
	}

	/**
	 * moves the rectangle by a given vector
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		this._p1.move(vec);
		this._p2.move(vec);
		this._p4.move(vec);
		this._p3.move(vec);
	}

	/**
	 * creates copy of the rectangle - as a generic GeoShape
	 * @return GeoShape copy of the rectangle
	 */
	@Override
	public GeoShape copy() {
		return new Rect_2D(this);
	}

	/**
	 * scales rectangle - bigger or smaller
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		this._p1.scale(center, ratio);
		this._p4.scale(center, ratio);
		this._p2.scale(center, ratio);
		this._p3.scale(center, ratio);
	}

	/**
	 * rotates rectangle
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._p1.rotate(center, angleDegrees);
		this._p2.rotate(center, angleDegrees);
		this._p3.rotate(center, angleDegrees);
		this._p4.rotate(center, angleDegrees);
	}

	/**
	 *
	 * @return a string representing the rectangle
	 */
	@Override
	public String toString(){
		String ans = Arrays.toString(this.points());
		return ans.substring(1, ans.length()-1);
	}

	/**
	 * helper function
	 * @return array of the rectangle's points
	 */
	public Point_2D[] points(){
		return new Point_2D[]{this._p1, this._p2, this._p3, this._p4};
	}
}
