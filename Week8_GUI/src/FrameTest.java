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
		
		JPanel panel = new JPanel(); //�г� ����
		
		panel.setLayout(new GridLayout(3, 2));
		
		panel.add(new JLabel("ID:"));
		panel.add(new JTextField(10));
		
		//���̺�� �ؽ�Ʈ �ʵ�, ��ư ����
		panel.add(new JLabel("�й�:"));
		panel.add(new JTextField(10));
		panel.add(new JLabel("�а�:"));
		panel.add(new JTextField(10));
		
		add(panel, BorderLayout.CENTER);
		panel.add(new JButton("���"), BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}
}
