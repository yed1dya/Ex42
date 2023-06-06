// 207404997
package exe.ex4.geo;

/*
 * translate, scale, rotate functions utilize the methods in the Point_2D class;
 * in general, simply apply the requested method to the points of the triangle.
 * uses distance function from Point_2D as well.
 */

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle_2D implements GeoShape{
	public static final double EPS1 = 0.001; // "close enough" threshold for the functions
	private Point_2D _p1, _p2, _p3;

	/**
	 * basic constructor
	 * @param p1 first point
	 * @param p2 second point
	 * @param p3 third point
	 */
	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {
		this._p1 = new Point_2D(p1);
		this._p2 = new Point_2D(p2);
		this._p3 = new Point_2D(p3);
	}

	/**
	 * copy constructor
	 * @param t1 triangle to be copied
	 */
	public Triangle_2D(Triangle_2D t1) {
		this(t1.get_p1(), t1.get_p2(), t1.get_p3());
	}

	/**
	 * returns the triangle's points
	 * @return array of the triangle's points
	 */
	public Point_2D[] getAllPoints() {
		Point_2D[] ans = new Point_2D[3];
		ans[0] = new Point_2D(this._p1);
		ans[1] = new Point_2D(this._p2);
		ans[2] = new Point_2D(this._p3);
		return ans;
	}

	/**
	 * returns first point
	 * @return the triangle's _p1
	 */
	private Point_2D get_p1(){
		return this._p1;
	}

	/**
	 * returns second point
	 * @return the triangle's _p2
	 */
	private Point_2D get_p2(){
		return this._p2;
	}

	/**
	 * returns third point
	 * @return the triangle's _p3
	 */
	private Point_2D get_p3(){
		return this._p3;
	}

	/**
	 * checks if a point is inside a triangle;
	 * creates 3 new triangle - between the given point and every 2 points of the triangle.
	 * calculates area of all 3 new triangles. if equal to main triangle area (up to given epsilon) -
	 * - the point is inside the triangle. if sum of areas is more than main area - point is outside.
	 * @param ot - a query 2D point
	 * @return true if point is inside triangle, otherwise false.
	 */
	@Override
	public boolean contains(Point_2D ot) {
		Point_2D p1 = this._p1, p2 = this._p2, p3 = this._p3;
		Triangle_2D tri1 = new Triangle_2D(p1, p2, ot),
				tri2 = new Triangle_2D(p2, p3, ot),
				tri3 = new Triangle_2D(p3, p1, ot);
		double allArea = tri1.area()+tri2.area()+tri3.area(),
			origArea = this.area();
		return (Math.abs(allArea-origArea)<EPS1);
	}

	/**
	 * calculates the triangle's area, using <a href="https://en.m.wikipedia.org/wiki/Heron%27s_formula">Heron's formula.</a>
	 * @return triangle's area
	 */
	@Override
	public double area() {
		Point_2D p1 = this._p1, p2 = this._p2, p3 = this._p3;
		double a = p1.distance(p2),
				b = p2.distance(p3),
				c = p3.distance(p1),
				s = (a+b+c)/2;
		return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}

	/**
	 * calculates the triangle's perimeter - sum of distance between each 2 points
	 * @return triangle's perimeter
	 */
	@Override
	public double perimeter() {
		Point_2D p1 = this._p1, p2 = this._p2, p3 = this._p3;
		double a = p1.distance(p2),
				b = p2.distance(p3),
				c = p3.distance(p1);
		return a+b+c;
	}

	/**
	 * moves the triangle according to given vector
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		this._p1.move(vec); this._p2.move(vec); this._p3.move(vec);
	}

	/**
	 * creates copy of the triangle - as a generic GeoShape
	 * @return GeoShape copy of triangle
	 */
	@Override
	public GeoShape copy() {
		return new Triangle_2D(this);
	}

	/**
	 * scales triangle - bigger or smaller
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		this._p1.scale(center, ratio);
		this._p2.scale(center, ratio);
		this._p3.scale(center, ratio);
	}

	/**
	 * rotates triangle
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._p1.rotate(center, angleDegrees);
		this._p2.rotate(center, angleDegrees);
		this._p3.rotate(center, angleDegrees);
	}
}
