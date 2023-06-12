package exe.ex4.ex4;

import exe.ex4.geo.*;
import exe.ex4.gui.GUIShape;
import exe.ex4.gui.GUI_Shape;
import java.awt.*;

/**
 * This class is a very simple main for starting with Ex4(2)/
 */
public class Ex4_main {

	public static void main(String[] args) {
		simpleShapes();
		loadDemo();
	}

	// Two simple GeoShapes
	public static void simpleShapes() {
		/*
		if the class has a private constructor - can use get instance to open if its already open
		 */
		Ex4 ex4 = Ex4.getInstance();
		GUI_Shape_Collection shapes = ex4.getShape_Collection();
		Point_2D p1 = new Point_2D(3,4);
		Point_2D p2 = new Point_2D(6,8);
		Point_2D p3 = new Point_2D(0,0);
		Point_2D p4 = new Point_2D(3,1);
		Circle_2D c1 = new Circle_2D(p1,2);
		Circle_2D c2 = new Circle_2D(p2,1.4);
		Circle_2D s1 = new Circle_2D(p1,3);
		Polygon_2D poly1 = new Polygon_2D();
		poly1.add(p1); poly1.add(p2); poly1.add(p3); poly1.add(p4);
		GUI_Shape gs1 = new GUIShape(c1, true, Color.black, 1);
		GUI_Shape gs2 = new GUIShape(c2, true, Color.yellow, 2);
		GUI_Shape gs3 = new GUIShape(poly1, true, Color.blue, 3);
		String s = gs1.toString();
		//GUI_Shape g1 = new GUIShape(s);
		//gs3.setSelected(true);
		shapes.add(gs1);
		shapes.add(gs2);
		shapes.add(gs3);
		ex4.init(shapes);
		ex4.show();
	}
	// Loads a file from file a0.txt (Circles only).
	public static void loadDemo() {
		Ex4 ex4 = Ex4.getInstance();
		GUI_Shape_Collection shapes = ex4.getShape_Collection();
		String file = "a0.txt"; //make sure the file is your working directory.
		shapes.load(file);
		ex4.init(shapes);
		ex4.show();
	}

}
