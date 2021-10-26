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

		JPanel panel = new JPanel(); //패널 생성
		//panel.setLayout(new GridLayout(3, 2));
		
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(new JLabel("ID:"), BorderLayout.WEST);
		contentPane.add(new JTextField(10), BorderLayout.EAST);
		
		//레이블과 텍스트 필드, 버튼 생성
		
		contentPane.add(new JLabel("학번:"), BorderLayout.WEST);
		contentPane.add(new JTextField(13), BorderLayout.EAST);
		contentPane.add(new JLabel("학과:"), BorderLayout.WEST);
		contentPane.add(new JTextField(13), BorderLayout.EAST);
		contentPane.add(new JButton("등록"), BorderLayout.SOUTH);
		
		add(panel);
		this.pack();
		setVisible(true);
	}
}
