import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorFrame extends JFrame {

	public ErrorFrame(String aName) {

		JOptionPane.showMessageDialog(null, aName + " does not have an airport.");

	}
}
