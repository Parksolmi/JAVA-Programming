import javax.swing.*;
import java.awt.event.*;

class ConfirmButtonActionListener implements ActionListener {
	JTextField text;
	JLabel label;

	ConfirmButtonActionListener(JTextField text, JLabel label) {
		this.text = text;
		this.label = label;
	}

	public void actionPerformed(ActionEvent e) {
		String name = text.getText();
		label.setText("Hello, " + name);
	}
}