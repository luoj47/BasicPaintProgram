import java.awt.*;
import java.io.Serializable;

/* Class Shape
 *
 * By Rob Nash
 * 
 * This is the superclass in a hierarchy of shapes that you have to construct
 */

//the superclass in our inheritance hierarchy
//all "common" features, functions and data should go here
//for example, all shapes in Java2D have a x,y that declares their position
//and many of the shapes exposed have a width and a height (but not all, so we didn't put width and height here)
//note that this class is mostly empty, as there is no algorithm generic enough to guess an arbitrary shape's area (future subclasses must override getArea() to provide something reasonable)
//also, the draw method is empty too, as we don't know what shape to draw here! (again, our subclasses will need to replace this method with one that actually draws things)
class Shape extends Object implements Comparable<Shape>, Cloneable, Serializable {

	private static final long serialVersionUID = -2191167012050007865L;
	protected int x = 0; // sets to protected so can be accessed by classes in the package
	protected int y = 0;
	protected Color color;
	protected boolean isFilled;
	
	public Shape( int a, int b ) {
		x=a;
		y=b;
	}
	
	public double getArea(){ return -1; }
	
	public void draw( Graphics g ){}
	
	public int getX() { return x; }
	public int getY() { return y; }

// Allows shapes to be compared by Area, returning an int based on who is bigger
	@Override
	public int compareTo(Shape s) {
		if(this.getArea() == s.getArea()) {
			return 0;
		} else if(this.getArea() > s.getArea()) {
			return 1;
		} else {
			return -1;
		}
	}
	
// Returns a clone of the current shape.
	@Override
	public Shape clone() throws CloneNotSupportedException {
			Shape copy = (Shape) super.clone();
			return copy;
	}
}