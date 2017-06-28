// Jesse Luo
// Instructor Rob Nash
// CSS 162
// 15 December 2012
// Paint Program, Drawing panel

// Represents the blank canvas or drawing panel that is used in paint. This class
// is used in Paint so that shapes may be created on the window in various manners.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class DrawPanel extends JPanel implements MouseListener {
	private ArrayList<Shape> picture; // holds the shapes
	private Application owner;
	private Stack<Shape> undoStack; // holds the shapes undone.
	private Shape currentDrawn;
	
// Creates a new Drawing panel with a reference to the parent Application.
// Initializes the list of shapes and the stack of undrawn shapes.
	public DrawPanel(Application parent) {
		owner = parent;
		// sets background white, and adds an action listener to the drawing panel.
		addMouseListener(this);
		this.setBackground(Color.WHITE);
		picture = new ArrayList<Shape>();
		undoStack = new Stack<Shape>();
	}
	
// Undoes the last shape drawn to the picture and paints the new picture.
	public void undo() {
		// Moves the shapes from the ArrayList to the stack.
		if(!picture.isEmpty()) {
			undoStack.push(picture.remove(picture.size() - 1));
		}
		repaint();
	}
	
// Redoes the last shape undrawn and paints the new picture.
// Opens a dialog message if there are no more shapes to redo.
	public void redo() {
		// Moves the shape from the stack to the ArrayList.
		if(!undoStack.isEmpty()) {
			picture.add(undoStack.pop());
		} else {
			JOptionPane.showMessageDialog(null, "No more Shapes to redo!");
		}
		repaint();
	}
	
// Sorts the shapes in the ArrayList by size.
	public void sort() {
		// uses insertion sort to sort the shapes by size.
		for(int i = 0; i < picture.size(); i++) {
			int hole = i;
			Shape neighbor = picture.get(i);
			while(hole > 0 && picture.get(hole -1).compareTo(neighbor) == 1) {
				picture.set(hole, picture.get(hole - 1));
				hole--;
			}
			picture.set(hole, neighbor);
		}
		repaint();
	}
	
// Paints the shapes in the ArrayList to the Drawing panel.
	@Override
	public void paint(Graphics g) {
			super.paint(g);
			for(Shape current : picture) {
				current.draw(g);
			}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

// Paints a clone of the currentBrush's shape to the drawing Panel with the
// top left of the shape at the x and y coordinates the mouse pointer is at.
	@Override
	public void mousePressed(MouseEvent e){
		if(owner.getBrush() != null) {
			try {
				currentDrawn = owner.getBrush().clone();
				currentDrawn.x = e.getX();
				currentDrawn.y = e.getY();
				picture.add(currentDrawn);
			} catch (CloneNotSupportedException e1) {
			throw new Error("Clone not supported");
			}	
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {		
	}
	
// Saves the ArrayList of Shapes to the given filename.
	public void saveShapes(String filename) throws FileNotFoundException, IOException {
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
		os.writeObject(picture);
		os.close();
	}
	
// Loads an ArrayList of shapes from a file and paints them to the screen.
	public void loadShapes(String filename) throws FileNotFoundException, IOException, 
											ClassNotFoundException {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
		ArrayList<Shape> newArray = (ArrayList<Shape>) is.readObject();
		picture = new ArrayList<Shape>(newArray);
		is.close();
		repaint();
	}

// Loads new Paint file.
	public void loadNew() {
		picture = new ArrayList<Shape>();
		repaint();
	}

}
