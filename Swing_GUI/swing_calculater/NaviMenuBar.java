package swing_calculater;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class NaviMenuBar extends JMenuBar {// extends JMenuBar {

	public NaviMenuBar() {
		// Create menus
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu helpMenu = new JMenu("Help");

		// Create menu items
		JMenuItem newItem = new JMenuItem("new");
		JMenuItem openItem = new JMenuItem("open");
		JMenuItem closeItem = new JMenuItem("close");

		JMenuItem copyItem = new JMenuItem("Copy");
		JMenuItem pasteItem = new JMenuItem("Paste");
		JMenuItem cutItem = new JMenuItem("Cut");

		JMenuItem documentItem = new JMenuItem("Document");
		JMenuItem websiteItem = new JMenuItem("website");
		JMenuItem shortcutItem = new JMenuItem("shortcut");
		JMenuItem aboutItem = new JMenuItem("About");

		// Add menu items to menus
		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.addSeparator();
		fileMenu.add(closeItem);

		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.add(cutItem);
		editMenu.addSeparator();

		helpMenu.add(shortcutItem);
		helpMenu.add(websiteItem);
		helpMenu.add(documentItem);
		helpMenu.add(aboutItem);

		// nested menu
		editMenu.add(helpMenu);

		// add to JMenuBar panel
		add(fileMenu);
		add(editMenu);

		// custmers not effect never
		// setPreferredSize(new Dimension(900, 35));

		// setFont(new Font("Arial", Font.BOLD, 10));
		// setForeground(Color.blue);
		// setLayout(new FlowLayout());

		// Set the desired RGB color values
		// setBackground(new Color(255, 0, 0));//or
		// UIManager.put("MenuBar.background", Color.BLACK);
	}

	public static void main(String[] args) {
		// Code for creating and displaying the GUI
		NaviMenuBar menuBar = new NaviMenuBar();
		JFrame frame = new JFrame("Swing Application");
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300); // Set the desired width and height
		frame.setVisible(true);
	}
}
