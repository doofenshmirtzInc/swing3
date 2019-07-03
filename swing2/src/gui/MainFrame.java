package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame {

	//private instance variable declarations for the main frame
	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	
	public MainFrame() {
		super("Layout1.pdf"); //default title is "Layout1.pdf"
		
		setLayout(new BorderLayout());
		
		//setting up menu bar
		setJMenuBar(createMenuBar());
		
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
		textPanel = new TextPanel();
	
		toolbar = new Toolbar();
		toolbar.setStringListener(new StringListener(){

			@Override
			public void textEmitted(String str) {
				// TODO Auto-generated method stub
				//System.out.println(str);
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
				
				textPanel.append(name + ": " + occupation + " : " + ageCat + ", " + empCat +  "\n");
				
				System.out.println(e.getGender());
			}
		});
		
		
		
		
		
		
		
		
		//Main Frame adds various components to itself
		add(formPanel, BorderLayout.WEST);
		//adds area for user to type in txt at center of frame
		add(textPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);
		//puts button at the bottom of the window 
		//add(btn, BorderLayout.SOUTH);
		
		

		setMinimumSize(new Dimension(500, 400));
		setSize(960, 540); //width, height
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		
		//this block adds a listener to the "click me!" button
				//when the button is pressed, the program inserts the string
				//"Hello\n" to the text area previously created
				/**
				btn.addActionListener(new ActionListener() {
					//'new ActionListener()' is the implementation of another
					//anonymous class
					//objects of ActionListener type must implement the void 
					//method actionPerformed
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						textPanel.append("Hello\n");
					}
					
					
				});
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
				
				//MainFrame.this must be passed so it knows to appear in the MainFrame
				//'this' will not work because we arent in the MainFrame class
				//we are currently inside and anon ActionListener() class
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());
				}
			}
			
			
		});
		
		
		exportDataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				//MainFrame.this must be passed so it knows to appear in the MainFrame
				//'this' will not work because we arent in the MainFrame class
				//we are currently inside and anon ActionListener() class
				if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());
				}
			}
			
			
		});
		
		showFormItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {
				// TODO Auto-generated method stub
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();
				
				formPanel.setVisible(menuItem.isSelected()); //if checked, form panel is visible
															 //if not, form panel invisible
			}
		});
		
		//adding a mnemonic
		
		fileMenu.setMnemonic(KeyEvent.VK_F); //Underlines the f in File menu
		exitItem.setMnemonic(KeyEvent.VK_X); //Alt + x can be pressed to select the 'Exit' item in File menu
		
		//this is called an accelerator
		//it is mapping ctrl + x to the exit item in the File menu
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		
		exitItem.addActionListener(new ActionListener() { //when in the File menu and Alt + x is pressed, program closes

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				int action = JOptionPane.showConfirmDialog(MainFrame.this, 
						"Do you really want to exit?", 
						"Confirm Exit", 
						JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
			
		});
		
		
		return menuBar; 
	}
}
