package studyCafe;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class SaveFrame extends JFrame {

	private JPanel contentPane;

	public SaveFrame(Management mg) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(600, 400, 600, 300);
		contentPane = new JPanel();
		//레이아웃 설정(위치와 크기 자유지정)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Do you want to save the changes?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 22));
		lblNewLabel.setBounds(87, 42, 428, 119);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Yes");
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
					System.out.println("The file could not be found.");
				} catch (IOException ioe) {
					System.out.println("The file cannot be output.");
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
