package studyCafe;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;

@SuppressWarnings("serial")
public class SaveFrame extends JFrame {

	private JPanel contentPane;

	public SaveFrame(Management mg) {
		//윈도우 타이틀 설정
		this.setTitle("Save");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(680, 420, 600, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		//레이아웃 설정(위치와 크기 자유지정)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Do you want to save the changes?");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 22));
		lblNewLabel.setBounds(87, 42, 428, 119);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Yes");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(25, 25, 112));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//파일 저장하기
				File roomInfoFile = new File("studyCafeRoom.dat");
				ObjectOutputStream roomInfoOut = null;
				
				//파일에 방정보 저장
				try
				{
					roomInfoOut = new ObjectOutputStream(new FileOutputStream(roomInfoFile));
					// 방 정보 파일에 쓰기
					mg.writeRoomInfo(roomInfoOut);
					
				} catch (FileNotFoundException fnfe) {
					MessageFrame mf = new MessageFrame("The file could not be found.");
				} catch (IOException ioe) {
					MessageFrame mf = new MessageFrame("The file cannot be output.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						roomInfoOut.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
				MessageFrame ef = new MessageFrame("Save Success");
				
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(144, 154, 136, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNo = new JButton("No");
		btnNo.setForeground(Color.WHITE);
		btnNo.setBackground(new Color(25, 25, 112));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNo.setFont(new Font("굴림", Font.BOLD, 20));
		btnNo.setBounds(314, 154, 136, 40);
		contentPane.add(btnNo);
		
		//디스플레이
		setVisible(true);
	}

}
