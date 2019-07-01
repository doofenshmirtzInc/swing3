package swing2;

import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SwingUtilities.invokeLater(new Runnable() {
			//argument passed to invokeLater is an
			//anonymous class of type Runnable which requires
			//that the method run() be implemented.
			//invokeLater requires a Runnable type
			public void run() {
				new MainFrame();
			}
		});
	}

}
