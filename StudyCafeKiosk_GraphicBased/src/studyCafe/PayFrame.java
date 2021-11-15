package studyCafe;

import java.awt.*;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
class PayFrame extends JFrame {
	// 3. 결제 윈도우

	
	public PayFrame(JTable roomTable, int row, Management mg) throws Exception {
		// Pay프레임 생성
		JFrame payFrame = new JFrame("Pay");
		// 프레임 크기
		payFrame.setSize(600, 300);
		// 윈도우 위치 설정
		payFrame.setLocation(600, 400);
		// content pane 생성
		Container payCP = payFrame.getContentPane();
		// x버튼 눌렀을 때 이벤트처리
		payFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// 프레임 디스플레이
		payFrame.setVisible(true);
		// 레이아웃 설정(위치와 크기 자유지정)
		payCP.setLayout(null);

		// 결제하는 방 이름 가져오기
		DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
		String roomName = String.valueOf(model.getValueAt(row, 0));

		// 날짜 정보 포맷 설정
		SimpleDateFormat dateFormat = new SimpleDateFormat("YY년 MM월 dd일 aa hh시 mm분 ss초");
		// 날짜 가져오기
		String checkInTime = dateFormat.format(mg.searchRoom(roomName).getCheckInTime().getTime());
		String checkOutTime = dateFormat.format(mg.searchRoom(roomName).getCheckOutTime().getTime());
		// 가격 가져오기
		int pay = mg.pay(roomName);

		// JLabel 생성 : 입실 시간 & 퇴실 시간 보여주기
		JLabel time = new JLabel();
		time.setText("<html> Check-in time : " + checkInTime + "<br> Check-out time : " + checkOutTime + "</html>");
		time.setBounds(100, 30, 472, 60);
		time.setFont(new Font("Monospaced", Font.BOLD, 15));

		payCP.add(time); // content pane에 추가

		// JLabel 생성 : 지불할 가격 보여주기
		JLabel price = new JLabel();
		price.setText("<html> Price : " + pay + " won <br> Do you want to pay? </html>");
		price.setFont(new Font("Monospaced", Font.BOLD, 20));
		price.setBounds(200, 93, 372, 75);

		payCP.add(price); // content pane에 추가
		
		//버튼
		JButton btnNewButton = new JButton("Yes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//체크아웃
					mg.checkOut(roomName);
					//매출 리스트에 저장
					mg.addSalesList(roomName);
					//사용중 ->empty로 바꾸기
					model.setValueAt("Empty", row, 3);
					
					//파일 저장하기
					File roomInfoFile = new File("studyCafeRoom.dat");
					File salesInfoFile = new File("studyCafeSales.dat"); 
					ObjectOutputStream roomInfoOut = null;
					ObjectOutputStream salesInfoOut = null;
					
					//파일에 방정보&매출정보 저장
					try
					{
						roomInfoOut = new ObjectOutputStream(new FileOutputStream(roomInfoFile));
						salesInfoOut = new ObjectOutputStream(new FileOutputStream(salesInfoFile));
						// 방 정보 파일에 쓰기
						mg.writeRoomInfo(roomInfoOut);
						//매출 정보 파일에 쓰기
						mg.writeSalesInfo(salesInfoOut);
						
					} catch (FileNotFoundException fnfe) {
						System.out.println("The file could not be found.");
					} catch (IOException ioe) {
						System.out.println("The file cannot be output.");
					} catch (Exception e1) {
						e1.printStackTrace();
					} finally {
						try {
							roomInfoOut.close();
							salesInfoOut.close();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
					//이전 프레임으로 돌아가기
					payFrame.setVisible(false);
					MessageFrame ef = new MessageFrame("Check Out Success");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(134, 192, 152, 43);
		payFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("No");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//이전 프레임으로 돌아가기
				payFrame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(313, 192, 152, 43);
		payFrame.getContentPane().add(btnNewButton_1);
	}
}
