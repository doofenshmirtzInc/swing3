package swing2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener{

	private JButton helloButton;
	private JButton goodbyeButton;
	private StringListener strListener;
	
	public Toolbar() {
		//super();
		setBorder(BorderFactory.createEtchedBorder());
		helloButton = new JButton("Hello\n");
		goodbyeButton = new JButton("Goodbye\n");
		
		helloButton.addActionListener(this);
		goodbyeButton.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT)); //FlowLayout layout manager allows you to place buttons next to each other
		
		add(helloButton);
		add(goodbyeButton);
	}
	
	public void setStringListener(StringListener listener) {
		this.strListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == helloButton) { //checking that the references point to same object
			if(strListener != null) {
				strListener.textEmitted("Hello, world!\n");
			}
		}else if(clicked == goodbyeButton) {
			if(strListener !=  null) {
				strListener.textEmitted("Goodbye, cruel world!\n");
			}
		}

	}
	
}
