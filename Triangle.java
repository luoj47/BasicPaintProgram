// Jesse Luo
// Instructor Rob Nash
// CSS 162
// 15 December 2012
// Paint Program, Triangle

// This class represents a triangle that can be painted to a drawing Panel in paint.

import java.awt.Graphics;

public class Triangle extends Shape {
	private static final long serialVersionUID = 5938509300495614221L;
	private int base;
	private int height; // dimensions of a triangle.
	
// Constructs a RightTriangle with the specified x,y location, and specified base and height.
	public Triangle(int x, int y, int base, int height) {
		super(x, y); // Calls superclass Shape to store x and y.
		this.base = base;
		this.height = height;
	}
	
// Returns the area of the triangle.
	public double getArea() {
		return ((base * height) / 2); // Area of a triangle is (base * height) / 2.
	}
	
// Draws the triangle at the given coordinates in the specified color and whether or not
// it is filled.
	public void draw(Graphics g) {
		final int x = getX();
		final int y = getY();
		g.setColor(color);
		int xCo[] = {x, base, x};
		int yCo[] = {y, y, height};
		if(!isFilled) {
			g.drawPolygon(xCo, yCo, 3);
		} else {
			g.fillPolygon(xCo, yCo, 3);
		}
	}
	
// Returns the width of the rectangle.
	public int getBase() {
		return base;
	}
	
// Returns the height of the rectangle.
	public int getHeight() {
		return height;
	}
	
// Returns the hypotenuse of the right triangle.
	public double getHypotenuse() {
		// Hypotenuse is the square root of base ^2 plus height^2.
		return Math.sqrt((base * base) + (height * height)); 
	}
	
// Returns a deep clone of the current triangle.
	@Override
	public Triangle clone() throws CloneNotSupportedException {
			Triangle copy = (Triangle) super.clone();
			copy.base = this.base;
			copy.height = this.height;
			return copy;
	}
}
