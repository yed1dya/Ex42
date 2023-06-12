// 207404997
package exe.ex4.ex4;

import exe.ex4.geo.*;
import exe.ex4.gui.Ex4_GUI;
import exe.ex4.gui.GUIShape;
import exe.ex4.gui.GUI_Shape;
import exe.ex4.gui.StdDraw_Ex4;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Comparator;

/**
 *
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI {
	private  GUI_Shape_Collection _shapes = new ShapeCollection();
	private GUI_Shape _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private Point_2D _p1, _rotateCenter;
	private Polygon_2D _poly;
	private Rect_2D _rect;
	private static int _tag=0;
	private static Ex4 _winEx4 = null;

	boolean printMode = false, printDegrees = true, printRightClick = false, printAll = true;

	private Ex4() {
		init(null);
	}
	public void init(GUI_Shape_Collection s) {
		if(s==null) {_shapes = new ShapeCollection();
		}
		else {_shapes = s.copy();} //should be s.copy();}
		_gs = null;
		_color = Color.blue;
		_fill = true;
		_mode = "";
		// last point clicked
		_p1 = null;
		_poly = null;
	}
	public void show(double d) {
		// UI
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}
	// makes so there can only be one instance
	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	private String type(GUI_Shape g){
		System.out.println(g.getShape().toString().trim());
		return g.getShape().toString().trim();
	}

	public void drawShapes() {
		// clear the window:
		StdDraw_Ex4.clear();
		if(_mode.equals("Remove")) {
			remove();
		}
		if(_mode.equals("Anti")){
			anti();
			_mode = "";
		}
		if(_mode.equals("All")){
			all();
		}
		if(_mode.equals("None")){
			none();
		}
		if(_mode.equals("Info")){
			info();
		}
		Comparator<GUI_Shape> areaComparator = Comparator.comparingDouble(o -> o.getShape().area());
		if(_mode.equals("ByArea")){
			_shapes.sort(areaComparator);
		}
		Comparator<GUI_Shape> antiAreaComparator = areaComparator.reversed();
		if(_mode.equals("ByAntiArea")){
			_shapes.sort(antiAreaComparator);
		}
		Comparator<GUI_Shape> perimeterComparator = Comparator.comparingDouble(o -> o.getShape().perimeter());
		if(_mode.equals("ByPerimeter")){
			_shapes.sort(perimeterComparator);
		}
		Comparator<GUI_Shape> antiPerimeterComparator = perimeterComparator.reversed();
		if(_mode.equals("ByAntiPerimeter")){
			_shapes.sort(antiPerimeterComparator);
		}
		Comparator<GUI_Shape> toStringComparator = (Comparator.comparing(this::type));
		if(_mode.equals("ByToString")){
            _shapes.sort(toStringComparator);
        }
		Comparator<GUI_Shape> antiToStringComparator = toStringComparator.reversed();
		if(_mode.equals("ByAntiToString")){
            _shapes.sort(antiToStringComparator);
        }
		Comparator<GUI_Shape> tagComparator = Comparator.comparingInt(GUI_Shape::getTag);
		if(_mode.equals("ByTag")){
			_shapes.sort(tagComparator);
		}
		Comparator<GUI_Shape> antiTagComparator = tagComparator.reversed();
		if(_mode.equals("ByAntiTag")){
            _shapes.sort(antiTagComparator);
        }

		// draw the shapes saved in collection:
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape sh = _shapes.get(i);
			drawShape(sh);
		}
		// draw the current shape, if exists:
		if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}
	private static void drawShape(GUI_Shape g) {
		// main method for drawing the shapes:
		StdDraw_Ex4.setPenColor(g.getColor());
		// 'selected' shapes should be gray:
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}
		GeoShape gs = g.getShape();
		boolean isFill = g.isFilled();
		// use the appropriate StdDraw method; (triangle uses polygon):
		if(gs instanceof Polygon_2D){
			Polygon_2D p = (Polygon_2D)gs;
			Point_2D[] points = p.getAllPoints();
			// make arrays of x and y coordinates
			int len = points.length;
			double[] xVals = new double[len];
			double[] yVals = new double[len];
			for(int i = 0; i < len; i++){
				xVals[i] = points[i].x();
				yVals[i] = points[i].y();
			}
			if(isFill){
				StdDraw_Ex4.filledPolygon(xVals, yVals);
			}
			else {
				StdDraw_Ex4.polygon(xVals, yVals);
			}
		}
		if(gs instanceof Circle_2D) {
			Circle_2D c = (Circle_2D)gs;
			Point_2D cen = c.getCenter();
			double rad = c.getRadius();
			if(isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			}
			else {
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
		if(gs instanceof Segment_2D) {
			Segment_2D s = (Segment_2D) gs;
			Point_2D p1 = s.get_p1(), p2 = s.get_p2();
			StdDraw_Ex4.line(p1.x(), p1.y(), p2.x(), p2.y());
		}
		if(gs instanceof Rect_2D) {
			Rect_2D r = (Rect_2D)gs;
			Point_2D[] rp = r.points();
			double[] x = {rp[0].x(), rp[1].x(), rp[2].x(), rp[3].x()},
					y = {rp[0].y(), rp[1].y(), rp[2].y(), rp[3].y()};
			if(isFill) {
				StdDraw_Ex4.filledPolygon(x, y);
			}else {
				StdDraw_Ex4.polygon(x, y);
			}
		}
	}
	private void setColor(Color c) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}
	private void setFill() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		if(p.equals("Clear")) {_shapes.removeAll();}
		drawShapes();
	}


	public void mouseClicked(Point_2D p) {
		if(printMode || printAll){System.out.println("Mode: "+_mode+"  "+p);}
		// actions according to selected mode:
		if(_mode.equals("Segment")) {
			if(_gs==null) {
				// first click - save the point
				_p1 = new Point_2D(p);
			}
			else {
				// second click - set the segment, clear the saved point
				_gs.setColor(_color);
				_gs.setFilled(true);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		if(_mode.equals("Circle")) {
			if(_gs==null) {
				// first click - save the point
				_p1 = new Point_2D(p);
			}
			else {
				// second click - set the circle, clear the saved point
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		if(_mode.equals("Rect")) {
			if(_p1==null) {
				// first click - save the point
				_p1 = new Point_2D(p);
			}
			else {
				// second click - set the rectangle, clear the saved point
				_rect = new Rect_2D(_p1, p);
				_gs = new GUIShape(_rect, this._fill, this._color, _tag);
				_tag++;
				this._shapes.add(_gs);
				_p1 = null;
				_gs = null;
			}
		}
		if(_mode.equals("Polygon") || _mode.equals("Triangle")) {
			if(_gs==null || _poly == null) {
				// first click - save the point, create the shape
				_poly = new Polygon_2D();
				_poly.add(p);
				_p1 = new Point_2D(p);
			}
			if(_poly!=null) {
				_poly.add(p);
			}

		}
		if(_mode.equals("Triangle")) {
			// limits the number of points:
			if(_poly!=null && _poly.getAllPoints().length==4) {
				System.out.println("3 points");
				_gs = new GUIShape(_poly, _fill, _color, _tag);
				_tag++;
				this._shapes.add(_gs);
				_p1 = null;
				_gs = null;
			}
		}
		if(_mode.equals("Move")) {
			// sets first point:
			if(_p1==null) {_p1 = new Point_2D(p);}
			else {
				// moves according to second point:
				_p1 = new Point_2D(p.x()-_p1.x(), p.y()-_p1.y());
				move();
				_p1 = null;
			}
		}
		// sets point and calls scale method:
		if (_mode.equals("Scale_90%")){
			_p1 = new Point_2D(p);
			scale(0.9);
		}
		if (_mode.equals("Scale_110%")){
			_p1 = new Point_2D(p);
			scale(1.1);
		}
		if (_mode.equals("Copy")){
			// set first point:
			if(_p1==null){
				_p1 = new Point_2D(p);
			}
			else{
				// set second point and create a copy in the required place:
				_p1 = new Point_2D(p.x()-_p1.x(), p.y()-_p1.y());
				copy();
				_p1 = null;
			}
		}
		if (_mode.equals("Rotate")){
			if(_p1==null){
				// set first point:
				_p1 = new Point_2D(p);
			}
			else{
				double degrees;
				// calculate angle by the 2 points; source: chatGPT
				_rotateCenter = new Point_2D(_p1);
				_p1 = new Point_2D(p.x()-_p1.x(), p.y()-_p1.y());
				double x = _p1.x(), y = _p1.y();
				if(x==0){
					if(y>0){
						degrees = Math.PI/2;
					}else if(y<0){
						degrees = (3*Math.PI)/2;
					}else{
						degrees = 0;
					}
				}
				else {
					degrees = Math.atan2(y,x);
				}
				degrees = Math.toDegrees(degrees);
				while (degrees<0){
					degrees += 360;
				}
				if(printDegrees || printAll){System.out.println("degrees: "+degrees);}
				// call rotate method:
				rotate(degrees);
				// reset point:
				_p1 = null;
			}
		}
		if(_mode.equals("Point")) {
			select(p);
		}
		drawShapes();
	}

	public void mouseRightClicked(Point_2D p) {
		if(printRightClick || printAll){System.out.println("right click!");}
		if(_mode.equals("Polygon") && _poly!=null) {
			// add clicked point to polygon:
			_poly.add(p);
			_gs = new GUIShape(_poly, this._fill, this._color, _tag);
			_tag++;
			this._shapes.add(_gs);
			_p1 = null;
			_gs = null;
		}
		drawShapes();
	}
	public void mouseMoved(MouseEvent e) {
		if(_p1!=null) {
			// get mouse x,y coordinates and set point:
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			Point_2D p = new Point_2D(x1,y1);
			GeoShape gs = null;
			if(_mode.equals("Circle")) {
				// set circle according to current point:
				double r = _p1.distance(p);
				gs = new Circle_2D(_p1,r);
			}
			if((_mode.equals("Polygon") || _mode.equals("Triangle")) && _poly!=null) {
				// set polygon according to current point
				Polygon_2D poly = _poly;
				// remove last point from polygon:
				Point_2D[] pa = poly.getAllPoints();
				if(pa.length>0) {
					// remove last point from pa:
					pa[pa.length-1] = new Point_2D(p);
				}
				// add current point to polygon:
				poly = new Polygon_2D();
				for(Point_2D p1 : pa) {
					poly.add(p1);
				}
				gs = new Polygon_2D(_poly);
				_poly = new Polygon_2D();
				for(Point_2D p1 : pa) {
					_poly.add(p1);
				}
			}
			if(_mode.equals("Segment")) {
				// set segment according to current point:
				gs = new Segment_2D(_p1,p);
			}
			if(_mode.equals("Rect")) {
				// set rectangle according to current point:
				gs = new Rect_2D(_p1,p);
			}
			// set shape to draw (the pink outline):
			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
	}

	private void select(Point_2D p) {
		// invert selection on shape (click selects, click unselects):
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	private void anti() {
		// invert selection on all shapes:
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			s.setSelected(!s.isSelected());
		}
	}
	private void remove() {
		// removes selected shapes:
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) {
				_shapes.removeElementAt(i);
				i--;
			}
		}
	}
	private void info(){
		// prints shape information:
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			System.out.println(s.toString());
		}
	}
	private void all() {
		// select all:
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			s.setSelected(true);
		}
	}
	private void none() {
		// select none:
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			s.setSelected(false);
		}
	}
	/*
		use the methods in Point_2D to change the selected shapes:
	 */
	private void move() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.translate(_p1);
			}
		}
	}
	private void scale(double ratio) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.scale(_p1, ratio);
			}
		}
	}
	private void copy(){
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) {
				GeoShape copy = g.copy();
				copy.translate(_p1);
				_gs = new GUIShape(copy, s.isFilled(), s.getColor(), _tag);
				_tag++;
				this._shapes.add(_gs);
				_gs = null;
			}
		}
	}
	private void rotate(double degrees) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.rotate(_rotateCenter, degrees);
			}
		}
	}

	@Override
	public GUI_Shape_Collection getShape_Collection() {
		return this._shapes;
	}
	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }
	@Override
	public String getInfo() {
		StringBuilder ans = new StringBuilder();
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			ans.append(s.toString()).append("\n");
		}
		return ans.toString();
	}
}
