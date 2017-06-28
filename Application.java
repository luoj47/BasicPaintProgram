// Jesse Luo
// Instructor Rob Nash
// CSS 162
// 15 December 2012
// Paint Program, Application

// This is the main running driver for the Application paint. Opens up a window and
// Displays a typical "Paint" interface.

// Edit: Assignment late because I decided to add a New File button! But I did 
// finish up everything on time which is why you can see everything else is turned
// in before 12.

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;

public class Application extends JFrame {
	private Shape currentBrush;
	private DrawPanel dp;
	private ButtonPanel bp;
	
// Constructs a new Application that creates a "Paint" window with a ButtonPanel and
// a DrawinPanel.
	public Application() {
		super("Paint"); // Names the window paint
		
		this.setLayout(new BorderLayout());
		//add buttonpanel and drawingpanel, as well as setting locations.
		bp = new ButtonPanel(this);
		dp = new DrawPanel(this);
		this.add(bp, BorderLayout.WEST);
		this.add(dp, BorderLayout.CENTER);
		// setting size of window, and the ability to end the program by closing
		// the window.
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
// Sets the currentBrush to the inputed brush. Basically points to a reference
// to the shape that the brush is so it will print that shape to the window.
	public void setCurrentBrush(Shape s) {
		if(currentBrush != null && currentBrush.isFilled) {
			s.isFilled = true;
		}
		currentBrush = s;
	}
	
// Sets the color of the currentBrush so that when shown in the window will either
// have that color outline, or that color fill.
	public void setColor(Color c, int fill) {
		if(fill == 0) {
			currentBrush.isFilled = true;
		} else if(fill == 1) {
			currentBrush.isFilled = false;
		}
		currentBrush.color = c;
	}
	
// Returns the Shape the currentBrush represents.
	public Shape getBrush() {
		return currentBrush;
	}
	
// Calls the drawingPanel to undo the last shape drawn.
	public void undoDraw() {
		dp.undo();
	}
	
// Calls the drawingPanel to redo any shapes that have been undone.
	public void redoDraw() {
		dp.redo();
	}

// Calls the drawingPanel to sort the shapes.
	public void sortShapes() {
		dp.sort();
	}
	
// Calls the drawingPanel to save the file to the specified name.
	public void save(String filename) throws FileNotFoundException, IOException {
		dp.saveShapes(filename);
	}
	
// Calls the drawingPanel to load the specified file name.
	public void load(String filename) throws FileNotFoundException, IOException, 
									  ClassNotFoundException {
		dp.loadShapes(filename);
	}
	
// Driver to start the Application.
	public static void main(String[] args) {
		JFrame app = new Application();
	}

// Allows user to start new paint file.
	public void loadNew(int file) {
		if(file == 0) {
			dp.loadNew();
		} 
	}
}
