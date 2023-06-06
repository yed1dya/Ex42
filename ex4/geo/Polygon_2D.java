// 207404997
package exe.ex4.geo;
import java.util.ArrayList;

/*
 * translate, scale, rotate functions utilize the methods in the Point_2D class;
 * in general, simply apply the requested method to all points of the polygon.
 * uses distance function from Point_2D as well.
 */

public class Polygon_2D implements GeoShape{
	private final ArrayList<Point_2D> _points;

	/**
	 * basic constructor:
	 */
	public Polygon_2D() {
		this._points = new ArrayList<>();
	}

	/**
	 * copy constructor
	 * @param po polygon to be copied
	 */
	public Polygon_2D(Polygon_2D po) {
		this._points = new ArrayList<>(po._points);
	}

	/**
	 * @return array of the polygon's points
	 */
	public Point_2D[] getAllPoints() {
		int j=0;
		Point_2D[] ans = new Point_2D[this._points.size()];
		for(Point_2D p : this._points){
			ans[j++] = new Point_2D(p);
		}
		return ans;
	}

	/**
	 * adds a point to the polygon
	 * @param p point to be added
	 */
	public void add(Point_2D p) {
		this._points.add(new Point_2D(p));
	}

	/**
	 * @return a string representing the polygon
	 */
	@Override
	public String toString() {
		StringBuilder ans = new StringBuilder();
		for (Point_2D p : this._points){
			ans.append("(").append(p.toString()).append(")").append(", ");
		}
		if (ans.toString().endsWith(", ")){
			ans = new StringBuilder(ans.substring(0, ans.length() - 2));
		}
		return ans.toString();
	}

	/**
	 * uses ray casting algorithm: (source: <a href="https://en.wikipedia.org/wiki/Point_in_polygon">wikipedia</a>)
	 * @param ot - a query 2D point
	 * @return true if the point is inside the polygon, otherwise false
	 */
	@Override
	public boolean contains(Point_2D ot) {
		// check if the point is relevant - if it's inside a 'box' surrounding the polygon
		if(!isInBox(this, ot)){
			return false;
		}
		Segment_2D[] lines = lines(this.getAllPoints());
		// check if point is on one of the lines:
		for (Segment_2D line : lines) {
			if (line.contains(ot)) {
				return true;
			}
		}
		int count=0;
		// check how many times the line from query point will intersect with the lines of the polygon:
		for (Segment_2D line : lines) {
			if (intersects(ot, line)) {
				count++;
			}
		}
		// if intersects odd amount of times, point is inside:
		return count % 2 == 1;
	}

	/**
	 * helper function for contains:
	 * @param p query point
	 * @param s a segment of the polynom
	 * @return true if a line from the point (parallel to x-axis) intersects the segment
	 */
	private static boolean intersects(Point_2D p, Segment_2D s){
		double x1=s.get_p1().x(), y1=s.get_p1().y(), x2=s.get_p2().x(), y2=s.get_p2().y(), pX=p.x(), pY=p.y();
		// if segment is vertical; check if it's to the right of query point,
		// and not all above or below the y value of the query point:
		if(x1==x2){
			return pX<=x1 && pY>=Math.min(y1,y2) && pY<=Math.max(y1,y2);
		}
		else{
			// if segment slope isn't 0; find its equation;
			// find x value of intersection between line and segment;
			// check if x value is to the right of query point and between x values of segment:
			double m = (y2-y1)/(x2-x1);
			if(m!=0){
				double x = (pY - y1)/m + x1;
				return x>=pX && x>=Math.min(x1,x2) && x<=Math.max(x1,x2);
			}
			// if segment slope is 0;
			// check if y values of segment and line are equal,
			// and segment is to the right of query point:
			else{
				return pY==y1 && pX<=Math.min(x1,x2);
			}
		}
	}

	/**
	 * helper function for contains:
	 * @param points array of the polygon points
	 * @return array of the segments that are the polygon's lines
	 */
	private static Segment_2D[] lines(Point_2D[] points){
		int i=1, j=0;
		Segment_2D[] ans = new Segment_2D[points.length];
		Point_2D p2;
		for (Point_2D p1 : points){
			if(i==points.length){
				p2 = points[0];
			}
			else{
				p2 = points[i++];
			}
			ans[j++] = new Segment_2D(p1, p2);
		}
		return ans;
	}

	/**
	 * helper function for contains:
	 * @param poly the polygon
	 * @param p the query point
	 * @return true if the point is in the 'box' surrounding the polygon
	 */
	private static boolean isInBox(Polygon_2D poly, Point_2D p){
		return p.y()<=maxY(poly) && p.y()>=minY(poly) && p.x()<=maxX(poly) && p.x()>=minX(poly);
	}

	/**
	 * helper function for 'isInBox'
	 * @param poly the polynom
	 * @return minimum x value of the polynom
	 */
	private static double minX(Polygon_2D poly){
		Point_2D[] points = poly.getAllPoints();
		if(points==null || points.length==0){
			return 0;
		}
		double min = points[0].x();
		for (Point_2D p : points){
			if(p.x()<min){
				min = p.x();
			}
		}
		return min;
	}

	/**
	 * helper function for 'isInBox'
	 * @param poly the polynom
	 * @return maximum x value of the polynom
	 */
	private static double maxX(Polygon_2D poly){
		Point_2D[] points = poly.getAllPoints();
		if(points==null || points.length==0){
			return 0;
		}
		double max = points[0].x();
		for (Point_2D p : points){
			if(p.x()>max){
				max = p.x();
			}
		}
		return max;
	}

	/**
	 * helper function for 'isInBox'
	 * @param poly the polynom
	 * @return minimum y value of the polynom
	 */
	private static double minY(Polygon_2D poly){
		Point_2D[] points = poly.getAllPoints();
		if(points==null || points.length==0){
			return 0;
		}
		double min = points[0].y();
		for (Point_2D p : points){
			if(p.y()<min){
				min = p.y();
			}
		}
		return min;
	}

	/**
	 * helper function for 'isInBox'
	 * @param poly the polynom
	 * @return maximum y value of the polynom
	 */
	private static double maxY(Polygon_2D poly){
		Point_2D[] points = poly.getAllPoints();
		if(points==null || points.length==0){
			return 0;
		}
		double max = points[0].y();
		for (Point_2D p : points){
			if(p.y()>max){
				max = p.y();
			}
		}
		return max;
	}

	/**
	 * uses the shoelace formula (source: <a href="https://en.wikipedia.org/wiki/Polygon">wikipedia</a>)
	 * @return the polygon's area
	 */
	@Override
	public double area() {
		ArrayList<Point_2D> points = this._points;
		int i = 1;
		double ans = 0;
		Point_2D p2;
		for (Point_2D p1 : points){
			if(i == points.size()){ p2 = points.get(0); }
			else{ p2 = points.get(i++); }
			ans += (p1.x()*p2.y() - p2.x()*p1.y());
		}
		return Math.abs(ans/2);
	}

	/**
	 * @return the polygon's perimeter
	 */
	@Override
	public double perimeter() {
		ArrayList<Point_2D> points = this._points;
		int i = 1;
		double ans = 0;
		Point_2D p2;
		for (Point_2D p1 : points){
			if(i == points.size()){ p2 = points.get(0); }
			else{ p2 = points.get(i++); }
			ans += p1.distance(p2);
		}
		return ans;
	}

	/**
	 * moves the polygon by a given vector
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		for (Point_2D p : this._points){
			p.move(vec);
		}
	}

	/**
	 * creates generic GeoShape copy of a polygon
	 * @return a GeoShape copy of the polygon
	 */
	@Override
	public GeoShape copy() {
		return new Polygon_2D(this);
	}

	/**
	 * scales the polygon - bigger or smaller
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		for (Point_2D p : this._points){
			p.scale(center, ratio);
		}
	}

	/**
	 * rotates the polygon
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		for(Point_2D p : this._points){
			p.rotate(center, angleDegrees);
		}
	}

}
