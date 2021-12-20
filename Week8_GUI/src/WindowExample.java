import java.awt.*;
import javax.swing.*;

public class WindowExample {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java Program");
		
		//������ ��ġ�� ũ�� ����
		frame.setLocation(500, 400);
		frame.setPreferredSize(new Dimension(300, 200));
		
		Container contentPane = frame.getContentPane();
		//���� �ؽ�Ʈ �߾ӿ� ǥ��
		JLabel label = new JLabel("Hello, Java", SwingConstants.CENTER);
		
		contentPane.add(label);
		//X��ư�� ������ �� �ؾ� �� ���� ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
