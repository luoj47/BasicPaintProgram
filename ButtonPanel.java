// Jesse Luo
// Instructor Rob Nash
// CSS 162
// 15 December 2012
// Paint Program, ButtonPanel

// A class that holds a ButtonPanel that can be used as in interface with a paint program.

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ButtonPanel extends JPanel implements ActionListener {
	private Application owner;
	// Buttons that will be implemented on the Buttonpanel.
	private JButton newFile = new JButton("New");
	private JButton square = new JButton("Square");
	private JButton circle = new JButton("Circle");
	private JButton triangle = new JButton("Triangle");
	private JButton color = new JButton("Color");
	private JButton save = new JButton("Save");
	private JButton load = new JButton("Load");
	private JButton undo = new JButton("Undo");
	private JButton redo = new JButton("Redo");
	private JButton sort = new JButton("Sort");
	
// Constructs a ButtonPanel with a reference to the parent Application. An
// interface is created for the panel, and the shape, color, save, load, undo
// redo and sort buttons are added to the buttonpanel.
	public ButtonPanel(Application parent) {
		owner = parent;
		setLayout(new GridLayout(10,1));
		add(newFile);
		add(square);
		add(circle);
		add(triangle);
		add(color);
		add(save);
		add(load);
		add(undo);
		add(redo);
		add(sort);
		
		// Adds an ActionListener to the ButtonPanel's buttons so they perform
		// an action when pressed.
		newFile.addActionListener(this);
		square.addActionListener(this);
		circle.addActionListener(this);
		triangle.addActionListener(this);
		color.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		undo.addActionListener(this);
		redo.addActionListener(this);
		sort.addActionListener(this);
	}

// Performs various actions depending on which button is pressed.
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		// to produce random sized shapes each click
		final int w = (int) (Math.random() * 20);
		final int h = (int) (Math.random() * 20);
		//Allows user to start new Paint file.
		if(source == newFile) {
			int file = JOptionPane.showConfirmDialog(null, "Would you like to " +
					"create a new picture? (Erases currently unsaved changes!)");
			owner.loadNew(file);
		// sets the currentBrush to a square so a square will be created when the
		// drawingPanel is clicked.
		} else if(source == square) {
			owner.setCurrentBrush(new Square(0,0,w,h));
		// sets the currentBrush to a square so a circle will be created when the
		// drawingPanel is clicked.
		} else if(source == circle) {
			owner.setCurrentBrush(new Circle(0,0,w));
		// sets the currentBrush to a square so a triangle will be created when the
		// drawingPanel is clicked.
		} else if(source == triangle) {
			owner.setCurrentBrush(new Triangle(0,0,w,h));
		// Sets the color of every shape to be printed afterwards. Also allows the user
		// to choose if he wants the shapes to be filled or not.
		} else if(source == color) {
			Color selected = JColorChooser.showDialog(this, "Choose a color", Color.WHITE);
			int fill = JOptionPane.showConfirmDialog(null, "Would you like shapes to be " +
					"filled from now on? (Cancel to keep last setting)");
			owner.setColor(selected, fill);
		// Sends the name of the file that the shapes in the drawingPanel will be saved to
		// to the parent Application to pass into the drawingPanel.
		}else if(source == save) {
			JFileChooser a = new JFileChooser();
			a.showDialog(this, "Choose a file");
			try {
				owner.save(a.getSelectedFile().toString());
			} catch (FileNotFoundException e1) {
				throw new Error("File does not exist!");
			} catch (IOException e1) {
				throw new Error("Problem with IO!");
			}
		// Sends the name of the file that will be loaded so that the shapes in
		// that file will be painted to the drawingPanel.
		} else if(source == load) {
			JFileChooser a = new JFileChooser();
			a.showDialog(this, "Choose a file");
			try {
				owner.load(a.getSelectedFile().toString());
			} catch (FileNotFoundException e1) {
				throw new Error("File does not exist!");
			} catch (IOException e1) {
				throw new Error("Problem with IO!");
			} catch (ClassNotFoundException e1) {
				throw new Error("Class not found!");
			}
		// Sends a command to the parent Application to undo the last shape drawn.
		} else if(source == undo) {
			owner.undoDraw();
		// Sends a command to the parent Application to redo the last shape undone.
		} else if(source == redo) {
			owner.redoDraw();
		// Sends a command to the parent Application to sort the shapes in the drawing panel.
		} else if(source == sort) {
			owner.sortShapes();
		}
	}
}
