import java.awt.*;
import javax.swing.*;

public class WindowExample {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java Program");
		
		//프레임 위치와 크기 지정
		frame.setLocation(500, 400);
		frame.setPreferredSize(new Dimension(300, 200));
		
		Container contentPane = frame.getContentPane();
		//라벨의 텍스트 중앙에 표시
		JLabel label = new JLabel("Hello, Java", SwingConstants.CENTER);
		
		contentPane.add(label);
		//X버튼을 눌렀을 때 해야 할 일을 지정
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
