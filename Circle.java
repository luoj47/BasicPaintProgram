// Jesse Luo
// Instructor Rob Nash
// CSS 162
// 15 December 2012
// Paint Program, Circle

// This class represents a circle that can be used in Paint and printed to the window.

import java.awt.Graphics;

public class Circle extends Shape {
	private static final long serialVersionUID = -6816747644371638511L;
	private static final double PI = 3.14159265359; // PI for area calculations
	private int radius;

// Constructs a circle at the specified x and y coordinate with the specified radius.
	public Circle(int x, int y, int radius) {
		super(x, y); // Calls superclass Shape to store x and y.
		this.radius = radius;
	}
	
// Returns the area of the circle.
	public double getArea() {
		return (PI * (radius * radius)); // Circle area formula, PI times radius squared.
	}
	
// Returns the circumference of the circle.
	public double getCircumference() {
		return 2 * radius * PI; // Circumference is diameter (or radius times 2) times PI.
	}
	
// Returns the radius of the circle.
	public int getRadius() {
		return radius;
	}
	
// Draws the circle at the specified x and y coordinates. Is filled depending
// on if filled was chosen by the user.
	public void draw(Graphics g) {
		final int x = getX();
		final int y = getY();
		g.setColor(color);
		if(!isFilled) {
			g.drawOval(x, y, radius * 2, radius * 2);
		} else {
			g.fillOval(x, y, radius * 2, radius * 2);
		}
	}
	
// Allows a circle to be be cloned into a deep clone.
	@Override
	public Circle clone() throws CloneNotSupportedException {
			Circle copy = (Circle) super.clone();
			copy.radius = this.radius;
			return copy;
	}
}
