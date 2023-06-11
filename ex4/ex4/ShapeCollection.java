package exe.ex4.ex4;

import exe.ex4.gui.GUIShape;
import exe.ex4.gui.GUI_Shape;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
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
		//////////add your code below ///////////
// TODO sort
		//////////////////////////////////////////
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
		//////////add your code below ///////////
// TODO save
		//////////////////////////////////////////
	}

	@Override
	public void load(String file) {
		////////// add your code below ///////////

// TODO load
		//////////////////////////////////////////
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
