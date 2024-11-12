package tutorial;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;

public class swing3_LayoutManagers {

	static void runGui(JFrame frame) {
		// JFrame frame=new JFrame("Title");

		// frame.setLayout(null);//change layout wrong // sometimes other classes use it focus
		frame.pack();
		frame.setSize(600, 400);

		frame.setTitle(frame.getClass().getCanonicalName().toString());
		// frame.setDefaultCloseOperation(
		// JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}// end func runGui()

	public static void main(String[] args) {
		showFlowLayout fl = new showFlowLayout();
		fl.runGui();
		showGridLayout gl = new showGridLayout();
		runGui(gl);
		showBorderLayout bl = new showBorderLayout();
		runGui(bl);
	}
}

/*
 * Layout Managers*
 *  Java’s layout managers provide a level of
 * abstraction to automatically map your user
 * interface on all window systems.
 *  The UI components are placed in containers.
 * Each container has a layout manager to arrange
 * the UI components within the container.
 *  Layout managers are set in containers using the
 * setLayout(LayoutManager) method in a
 * container.
 * Kinds of Layout Managers
 *  FlowLayout
 *  GridLayout
 *  BorderLayout
 *  & other layout managers
 */

/*
 * The FlowLayout Class: UML
 * java.awt.Flo wLayout: CLass
 * 
 * -alignment: int
 * -hgap: int
 * -vgap: int
 * //The get and set methods for thes e data fields are provided in the class,
 * but omitted in the UML diagram for brevity
 * 
 * +FlowLayout()//Creat es a default FlowLayout manager.
 * +FlowLayout(alignment: int)//Creat es a Flow Layout manager with a specified
 * alignment.
 * +FlowLayout(alignment: int hgap: int, vgap: int)//Creat es a Flow Layout
 * manager with a specified alignment, horizontal gap, and vertical gap.
 * 
 * The alignment of this layout manager (default: CENTER).
 * The horizontal gap between the components (default: 5 pixels).
 * The vertical gap between the components (defaul t: 5 pixels).
 * 
 */

/*
 * Write a program that adds three labels and text fields into the content pane
 * of a
 * frame with a FlowLayout manager.
 */
class showFlowLayout extends JFrame {
	showFlowLayout() {
		super("setTitleSuper(showFlowLayout)");
		add(new Label("name:"));
		add(new TextField(10));

		add(new Label("age:"));
		add(new TextField(1));

		add(new Label("your phone:"));
		add(new TextField(10));
		// runGui();
	}

	void runGui() {
		/*
		 * set FlowLayout.aligned between components from left, with horizontonal gap
		 * 40, and vertical gap 10
		 */
		setLayout(new FlowLayout());
		setLayout(new FlowLayout(FlowLayout.LEFT,40,10));
		// setLayout(new FlowLayout(FlowLayout.RIGHT,40,10));
		// setTitle(getClass().toString());
		// setDefaultCloseOperation(
		// 		JFrame.EXIT_ON_CLOSE);
		setSize(500, 150);
		setVisible(true);
		setLocationRelativeTo(null);
	}// end func runGui()
}

/*
 * The GridLayout Class
 * java.awt. GridLayout
 * -rows: int
 * -columns: int
 * -hgap: int
 * -vgap: int
 * +GridLayou t()
 * +GridLayou t(row s: in t, col umns: int)
 * +GridLayou t(row s: in t, col umns: int,
 * hgap: int, vgap: int)
 * The number of rows in the grid ( def ault: 1).
 * The number of columns in the grid (de fault: 1).
 * The horizonta l gap be tween the components (default: 0).
 * The ve rtical gap between the compone nts ( defa ult: 0).
 * Cre ates a default GridLayo ut manage r.
 * Cre ates a Gr idLayout with a specified number of rows and c olumns.
 * Cre ates a Gr idLayout manager w ith a specified number of rows a nd
 * columns, horizontal gap, and ve rtical gap.
 * 
 * The g et and s et methods for the se data fields are provided in the
 * class, but omitted in the UML diagra m for brevity.
 */

/*
 * Rewrite the program in the preceding example using a GridLayout manager
 * instead of a
 * FlowLayout manager to display the labels and text fields.
 */

class showGridLayout extends JFrame {
	showGridLayout() {
		setLayout(new GridLayout(3, 3));//normal

		add(new Label("name:"));
		add(new TextField(10));

		add(new Label("age:"));
		add(new TextField(1));

		add(new Label("your phone:"));
		add(new TextField(10));

		// setLayout(new GridLayout());
		setLayout(new GridLayout(3, 3));//normal
		// setLayout(new GridLayout(3, 3, 5, 15));
		// setLayout(new GridLayout(3, 3, 5, 3));
		setLayout(new GridLayout(3, 3, 50, 130));

		setLayout(new GridLayout());
		// setLayout(new GridLayout());
	}

}

/*
 * The BorderLayout Manager
 * The BorderLayout
 * manager divides the
 * container into five
 * areas: East, South,
 * West, North, and
 * Center. Components are
 * added to a
 * BorderLayout by using
 * the add method.
 * 
 * add(Component,
 * constraint), where
 * constraint is
 * BorderLayout.EAST,
 * BorderLayout.SOUTH,
 * BorderLayout.WEST,
 * BorderLayout.NORTH, or
 * BorderLayout.CENTER
 */

class showBorderLayout extends JFrame {
	showBorderLayout() {
		// sameProgram();
		program2();
	}

	void sameProgram() {
		// must write it before add componentes
		setLayout(new BorderLayout());
		// setLayout(new BorderLayout(5, 15));

		// add(new Label("name:"), BorderLayout.PAGE_START);
		add(new TextField(10), BorderLayout.NORTH);

		// add(new Label("age:"), BorderLayout.CENTER);
		add(new TextField(1), BorderLayout.CENTER);

		// add(new Label("your phone:"), BorderLayout.SOUTH);
		add(new TextField(10), BorderLayout.SOUTH);
	}

	void program2() {
		// must write it before add componentes
		setLayout(new BorderLayout(10, 5));

		add(new JButton("NORTH"), BorderLayout.NORTH);
		add(new Button("CENTER"), BorderLayout.CENTER);
		add(new JButton("SOUTH"), BorderLayout.SOUTH);
		add(new JButton("WEST"), BorderLayout.WEST);
		add(new Button("EAST"), BorderLayout.EAST);
		
	}
}