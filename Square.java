// Jesse Luo
// Instructor Rob Nash
// CSS 162
// 15 December 2012
// Paint Program, Sqaure

// Represents a square that can be drawn to the drawing panel in paint.


import java.awt.Graphics;

public class Square extends Shape {
	private static final long serialVersionUID = -7770225106306159169L;
	private int width;
	private int height; // dimensions of rectangle
	
// Constructs a Rectangle with the specified x and y location, and the specified height and width.	
	public Square(int x, int y, int width, int height) {
		super(x, y); // Calls superclass Shape to store x and y.
		this.width = width;
		this.height = height;
	}
	
// Returns the area of the rectangle, height times width.
	public double getArea() {
		return width * height;
	}
	
// Draws the rectangle at the specified x and y coordinate with the right dimensions, 
// in the color specified by the user aand if it is filled.
	public void draw(Graphics g) {
		final int x = getX();
		final int y = getY();
		g.setColor(color);
		if(!isFilled) {
			g.drawRect(x, y, width, height); 
		} else {
			g.fillRect(x, y, width, height); 
		}
	}
	
// Returns the width of the rectangle.
	public int getWidth() {
		return width;
	}
	
// Returns the height of the rectangle.
	public int getHeight() {
		return height;
	}
	
// Returns a deep clone of the current square.
	@Override
	public Square clone() throws CloneNotSupportedException {
		Square copy = (Square) super.clone();
		copy.width = this.width;
		copy.height = this.height;
		return copy;
	}
}
