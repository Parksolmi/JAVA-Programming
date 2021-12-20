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
		
		JPanel panel = new JPanel(); //패널 생성
		
		panel.setLayout(new GridLayout(3, 2));
		
		panel.add(new JLabel("ID:"));
		panel.add(new JTextField(10));
		
		//레이블과 텍스트 필드, 버튼 생성
		panel.add(new JLabel("학번:"));
		panel.add(new JTextField(10));
		panel.add(new JLabel("학과:"));
		panel.add(new JTextField(10));
		
		add(panel, BorderLayout.CENTER);
		panel.add(new JButton("등록"), BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}
}
