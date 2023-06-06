// 207404997
package exe.ex4.geo;

/*
 * translate, scale, rotate functions utilize the methods in the Point_2D class;
 * in general, simply apply the requested method to the center point.
 */

/**
 * This class represents a 2D circle in the plane. 
 * Please make sure you update it according to the GeoShape interface.
 * Ex4: you should update this class!
 * @author boaz.benmoshe
 *
 */
public class Circle_2D implements GeoShape{
	private Point_2D _center;
	private double _radius;

	/**
	 * basic constructor
	 * @param cen the circle center
	 * @param rad the circle radius
	 */
	public Circle_2D(Point_2D cen, double rad) {
		this._center = new Point_2D(cen);
		this._radius = rad;
	}

	/**
	 * copy constructor
	 * @param c circle to be copied
	 */
	public Circle_2D(Circle_2D c) {
		this(c.getCenter(), c.getRadius());
	}

	/**
	 * @return the circle's radius
	 */
	public double getRadius() {return this._radius;}

	/**
	 * @return the circle's center
	 */
	public Point_2D getCenter(){ return _center;}

	/**
	 * @return a string representing the circle
	 */
	@Override
	public String toString()
	{
		return _center.toString()+", "+_radius;
	}

	/**
	 * checks if distance from the center is smaller than radius
	 * @param ot - a query 2D point
	 * @return true if point is inside circle, otherwise false
	 */
	@Override
	public boolean contains(Point_2D ot) {
		double radius = this._radius;
		double distance = this._center.distance(ot);
		return distance <= radius;
	}

	/**
	 * @return the circle's area
	 */
	@Override
	public double area() {
		double radius = this._radius;
		return radius*radius*Math.PI;
	}

	/**
	 * @return the circle's perimeter
	 */
	@Override
	public double perimeter() {
		double radius = this._radius;
		return 2*radius*Math.PI;
	}

	/**
	 * move the circle by a given vector
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		this._center.move(vec);
	}

	/**
	 * creates copy of circle - as a generic GeoShape
	 * @return GeoShape copy of circle
	 */
	@Override
	public GeoShape copy() {
		return new Circle_2D(this);
	}

	/**
	 * scale circle - bigger or smaller
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		this._center.scale(center, ratio);
		this._radius *= ratio;
	}

	/**
	 * rotate circle around given point
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._center.rotate(center, angleDegrees);
	}

}
