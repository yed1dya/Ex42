package exe.ex4.ex4;

import exe.ex4.geo.*;
import exe.ex4.gui.GUIShape;
import exe.ex4.gui.GUI_Shape;
import exe.ex4.geo.Polygon_2D;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements GUI_Shape_Collection{
	private ArrayList<GUI_Shape> _shapes;

	/**
	 * Basic constructor.
	 */
	public ShapeCollection() {
		_shapes = new ArrayList<>();
	}

	/**
	 * @param i - the index of the element
	 * @return the shape at index 'i'
	 */
	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}

	/**
	 * @return the size of the shape collection
	 */
	@Override
	public int size() {
		return _shapes.size();
	}

	/**
	 * @param i - the index of the element to be removed.
	 * @return the element that was removed
	 */
	@Override
	public GUI_Shape removeElementAt(int i) {
		GUI_Shape ans = null;
		if(i>=0 && i<this._shapes.size()){
			ans = this._shapes.remove(i);
		}
		return ans;
	}

	/**
	 * @param s - the gui_shape
	 * @param i - the location (index) in which s should be added
	 */
	@Override
	public void addAt(GUI_Shape s, int i) {
		if(i>=0 && i<this._shapes.size()){
			this._shapes.add(i, s);
		}
	}

	/**
	 * adds shape to collection
	 * @param s - the gui_shape
	 */
	@Override
	public void add(GUI_Shape s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}

	/**
	 * @return a deep copy of the collection
	 */
	@Override
	public GUI_Shape_Collection copy() {
		ShapeCollection ans = new ShapeCollection();
		ans._shapes.addAll(this._shapes);
		return ans;
	}

	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		_shapes.sort(comp);
	}

	/**
	 * empties the collection
	 */
	@Override
	public void removeAll() {
		_shapes.removeAll(_shapes);
	}

	@Override
	public void save(String file) {
		try {
			FileWriter writer = new FileWriter(file);
			for(GUI_Shape g : _shapes){
				writer.write(g.toString()+"\n");
			}
			writer.close();
			System.out.println("File saved successfully!");
		} catch (IOException e) {
			System.out.println("An error occurred while saving the file.");
			e.printStackTrace();
		}
	}

	@Override
	public void load(String file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			GeoShape g;
			while ((line = reader.readLine()) != null) {
				String[] s = line.split(",");
				if(s[4].equals("Circle_2D")){
					double x = Double.parseDouble(s[5]),
							y = Double.parseDouble(s[6]),
							r = Double.parseDouble(s[7]);
					Color c = new Color(Integer.parseInt(s[1]));
					boolean f = Boolean.parseBoolean(s[2]);
					int t = Integer.parseInt(s[3]);
					g = new Circle_2D(new Point_2D(x,y), r);
					_shapes.add(new GUIShape(g, f, c, t));
				}
				if(s[4].equals("Segment_2D")){
					double x1 = Double.parseDouble(s[5]),
                            y1 = Double.parseDouble(s[6]),
                            x2 = Double.parseDouble(s[7]),
                            y2 = Double.parseDouble(s[8]);
                    Color c = new Color(Integer.parseInt(s[1]));
                    boolean f = Boolean.parseBoolean(s[2]);
                    int t = Integer.parseInt(s[3]);
                    g = new Segment_2D(new Point_2D(x1,y1), new Point_2D(x2,y2));
                    _shapes.add(new GUIShape(g, f, c, t));
				}
				if(s[4].equals("Rect_2D")){
					double x1 = Double.parseDouble(s[5]),
							y1 = Double.parseDouble(s[6]),
							x2 = Double.parseDouble(s[7]),
							y2 = Double.parseDouble(s[8]),
							x3 = Double.parseDouble(s[9]),
							y3 = Double.parseDouble(s[10]),
							x4 = Double.parseDouble(s[11]),
							y4 = Double.parseDouble(s[12]);
					double[] pt = {x1, y1, x2, y2, x3, y3, x4, y4};
					int t = Integer.parseInt(s[3]);
					Polygon_2D p = new Polygon_2D();
					for(int i=0; i<7; i+=2) {
						p.add(new Point_2D(pt[i], pt[i+1]));
					}
					g = p.copy();
					Color c = new Color(Integer.parseInt(s[1]));
					boolean f = Boolean.parseBoolean(s[2]);
					_shapes.add(new GUIShape(g, f, c, t));
				}
				if(s[4].equals("Polygon_2D") || s[4].equals("Triangle_2D")){
					double[] pt = new double[s.length-5];
					for(int i=5; i<s.length; i++){
						pt[i-5] = Double.parseDouble(s[i]);
					}
					int t = Integer.parseInt(s[3]);
					Polygon_2D p = new Polygon_2D();
					for(int i=0; i<pt.length-1; i++) {
						p.add(new Point_2D(pt[i], pt[i+=1]));
					}
					g = p.copy();
					Color c = new Color(Integer.parseInt(s[1]));
					boolean f = Boolean.parseBoolean(s[2]);
					_shapes.add(new GUIShape(g, f, c, t));
				}
			}
			System.out.println(line);
		} catch (IOException e) {
			System.out.println("An error occurred while reading the file.");
			e.printStackTrace();
		}
	}

	/**
	 * @return a string representing the collection
	 */
	@Override
	public String toString() {
		StringBuilder ans = new StringBuilder();
		for(int i=0;i<size();i=i+1) {
			ans.append(this.get(i));
		}
		return ans.toString();
	}


}
