package swing2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {

	//private instance variable declarations for the main frame
	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	
	public MainFrame() {
		super("Layout1.pdf"); //default title is "Layout1.pdf"
		
		setLayout(new BorderLayout());
		
		//initializing new text area and new button when 
		//constructor for the frame is called
		textPanel = new TextPanel();
		//btn = new JButton("Click Me!");
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
				
				textPanel.append(name + ": " + occupation + "\n");
			}
		});
		
		
		
		
		
		
		
		
		//Main Frame adds various components to itself
		add(formPanel, BorderLayout.WEST);
		//adds area for user to type in txt at center of frame
		add(textPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);
		//puts button at the bottom of the window 
		//add(btn, BorderLayout.SOUTH);
		
		

		
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
}
