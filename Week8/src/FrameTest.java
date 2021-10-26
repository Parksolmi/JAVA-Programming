import java.awt.*;
import javax.swing.*;

public class FrameTest extends JFrame{
	public static void main(String[] args)
	{
		//FrameTest1 frame = new FrameTest1();
		FrameTest2 frame = new FrameTest2();
	}
}

class FrameTest2 extends JFrame {
	FrameTest2() {
		setSize(220, 200);
		
		setTitle("Registration"); //setTitle
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();

		JPanel panel = new JPanel(); //�г� ����
		//panel.setLayout(new GridLayout(3, 2));
		
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(new JLabel("ID:"), BorderLayout.WEST);
		contentPane.add(new JTextField(10), BorderLayout.EAST);
		
		//���̺�� �ؽ�Ʈ �ʵ�, ��ư ����
		
		contentPane.add(new JLabel("�й�:"), BorderLayout.WEST);
		contentPane.add(new JTextField(13), BorderLayout.EAST);
		contentPane.add(new JLabel("�а�:"), BorderLayout.WEST);
		contentPane.add(new JTextField(13), BorderLayout.EAST);
		contentPane.add(new JButton("���"), BorderLayout.SOUTH);
		
		add(panel);
		this.pack();
		setVisible(true);
	}
}
