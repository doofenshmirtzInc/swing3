package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;

public class MainFrame extends JFrame {

	// private instance variable declarations for the main frame
	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private TablePanel tablePanel;

	// creates controller object for shuffling data from front-end to back-end
	private Controller controller;

	public MainFrame() {
		super("Layout1.pdf"); // default title is "Layout1.pdf"

		setLayout(new BorderLayout());

		// setting up menu bar
		setJMenuBar(createMenuBar());

		controller = new Controller();

		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
		textPanel = new TextPanel();
		
		tablePanel = new TablePanel();
		tablePanel.setData(controller.getPeople());
		tablePanel.setPersonTableListener(new PersonTableListener() {
			
			public void rowDeleted(int row) {
				controller.removePerson(row);
				
				//would work
				//Better way to do it is shown in TablePanel.java
				//tablePanel.refresh();
			}
		});

		toolbar = new Toolbar();
		toolbar.setStringListener(new StringListener() {

			@Override
			public void textEmitted(String str) {
				// TODO Auto-generated method stub
				// System.out.println(str);
				textPanel.append(str);
			}

		});

		formPanel = new FormPanel();
		formPanel.setFormListener(new FormListener() {

			public void formEventOccurred(FormEvent e) {
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCat = e.getAgeCategory();
				String empCat = e.getEmploymentCategory();
				boolean isUs = e.isUSCitizen();
				String taxId = e.getTaxID();
				String gender = e.getGender();

				textPanel.append(name + ": " + occupation + " : " + ageCat + ", " + empCat + "\n");

				// controller.addPerson(e);
				controller.addPerson(name, occupation, ageCat, empCat, isUs, taxId, gender);

				tablePanel.refresh();

			}
		});

		// Main Frame adds various components to itself
		add(formPanel, BorderLayout.WEST);
		// adds area for user to type in txt at center of frame
		add(textPanel, BorderLayout.SOUTH);
		add(toolbar, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
		// puts button at the bottom of the window
		// add(btn, BorderLayout.SOUTH);

		setMinimumSize(new Dimension(500, 400));
		setSize(960, 540); // width, height
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// this block adds a listener to the "click me!" button
		// when the button is pressed, the program inserts the string
		// "Hello\n" to the text area previously created
		/**
		 * btn.addActionListener(new ActionListener() { //'new ActionListener()' is the
		 * implementation of another //anonymous class //objects of ActionListener type
		 * must implement the void //method actionPerformed
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) { // TODO
		 *           Auto-generated method stub textPanel.append("Hello\n"); }
		 * 
		 * 
		 *           });
		 */
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenu windowMenu = new JMenu("Window");

		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		JMenu showMenu = new JMenu("Show");
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
		showFormItem.setSelected(true);

		showMenu.add(showFormItem);
		windowMenu.add(showMenu);

		menuBar.add(fileMenu);
		menuBar.add(windowMenu);

		importDataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				// MainFrame.this must be passed so it knows to appear in the MainFrame
				// 'this' will not work because we arent in the MainFrame class
				// we are currently inside and anon ActionListener() class
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(MainFrame.this, "Load Operation Failed.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});

		exportDataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				// MainFrame.this must be passed so it knows to appear in the MainFrame
				// 'this' will not work because we arent in the MainFrame class
				// we are currently inside and anon ActionListener() class
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(MainFrame.this, "Save Operation Failed.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});

		showFormItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {
				// TODO Auto-generated method stub
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();

				formPanel.setVisible(menuItem.isSelected()); // if checked, form panel is visible
																// if not, form panel invisible
			}
		});

		// adding a mnemonic

		fileMenu.setMnemonic(KeyEvent.VK_F); // Underlines the f in File menu
		exitItem.setMnemonic(KeyEvent.VK_X); // Alt + x can be pressed to select the 'Exit' item in File menu

		// this is called an accelerator
		// it is mapping ctrl + x to the exit item in the File menu
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

		exitItem.addActionListener(new ActionListener() { // when in the File menu and Alt + x is pressed, program
															// closes

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}

		});

		return menuBar;
	}
}
