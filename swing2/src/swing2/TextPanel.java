package swing2;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel {
	
	private JTextArea textArea;
	
	public TextPanel() { //constructor for TextPanel
		
		//super();
		textArea = new JTextArea();
		
		setLayout(new BorderLayout());
		//new JScrollPane(textArea) wraps the text area in scroll bars (appear on screen when needed)
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
	
	public void append(String str) {
		textArea.append(str);
	}

}
