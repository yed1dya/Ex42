// 207404997
package exe.ex4.geo;

/*
 * translate, scale, rotate functions utilize the methods in the Point_2D class;
 * in general, simply apply the requested method to both points of the segment.
 * uses distance function from Point_2D as well.
 */

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment_2D implements GeoShape{
	public static final double EPS1 = 0.001; // "close enough" threshold for the functions
	private final Point_2D _p1, _p2;
	private final double _length;

	/**
	 * basic constructor
	 * @param a first point
	 * @param b second point
	 */
	public Segment_2D(Point_2D a, Point_2D b) {
		this._p1 = new Point_2D(a); this._p2 = new Point_2D(b);
		this._length = a.distance(b);
	}

	/**
	 * copy constructor
	 * @param t1 segment to copy
	 */
	public Segment_2D(Segment_2D t1) {
		this(t1.get_p1(), t1.get_p2());
	}

	/**
	 * get p1
	 * @return the first point defining the segment
	 */
	public Point_2D get_p1() {
		return this._p1;
	}

	/**
	 * get p2
	 * @return the second point defining the segment
	 */
	public Point_2D get_p2() {
		return this._p2;
	}

	/**
	 * check if a point is on the segment;
	 * if the sum of distances to both segment points is equal to the distance between the points
	 * @param ot - a query 2D point
	 * @return true if point is on segment, otherwise false.
	 */
	@Override
	public boolean contains(Point_2D ot) {
		double distP1 = this._p1.distance(ot),
				distP2 = this._p2.distance(ot);
		return distP2+distP1 <= this._length+EPS1;
	}

	/**
	 * area function for segment - defined as 0
	 * @return segment area
	 */
	@Override
	public double area() {
		return 0;
	}

	/**
	 * perimeter function for segment - defined as length*2
	 * @return segment perimeter
	 */
	@Override
	public double perimeter() {
		return this._length*2;
	}

	/**
	 * moves the segment according to given vector
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		this._p1.move(vec);
		this._p2.move(vec);
	}

	/**
	 * creates copy of segment - as a generic GeoShape
	 * @return GeoShape copy of segment
	 */
	@Override
	public GeoShape copy() {
		return new Segment_2D(this);
	}

	/**
	 * scales segment - bigger or smaller
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		this._p1.scale(center, ratio);
		this._p2.scale(center, ratio);
	}

	/**
	 * rotates segment
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._p1.rotate(center, angleDegrees);
		this._p2.rotate(center, angleDegrees);
	}
}