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
	// 3. ���� ������

	
	public PayFrame(JTable roomTable, int row, Management mg) throws Exception {
		// Pay������ ����
		JFrame payFrame = new JFrame("Pay");
		// ������ ũ��
		payFrame.setSize(600, 300);
		// ������ ��ġ ����
		payFrame.setLocation(600, 400);
		// content pane ����
		Container payCP = payFrame.getContentPane();
		// x��ư ������ �� �̺�Ʈó��
		payFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// ������ ���÷���
		payFrame.setVisible(true);
		// ���̾ƿ� ����(��ġ�� ũ�� ��������)
		payCP.setLayout(null);

		// �����ϴ� �� �̸� ��������
		DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
		String roomName = String.valueOf(model.getValueAt(row, 0));

		// ��¥ ���� ���� ����
		SimpleDateFormat dateFormat = new SimpleDateFormat("YY�� MM�� dd�� aa hh�� mm�� ss��");
		// ��¥ ��������
		String checkInTime = dateFormat.format(mg.searchRoom(roomName).getCheckInTime().getTime());
		String checkOutTime = dateFormat.format(mg.searchRoom(roomName).getCheckOutTime().getTime());
		// ���� ��������
		int pay = mg.pay(roomName);

		// JLabel ���� : �Խ� �ð� & ��� �ð� �����ֱ�
		JLabel time = new JLabel();
		time.setText("<html> Check-in time : " + checkInTime + "<br> Check-out time : " + checkOutTime + "</html>");
		time.setBounds(100, 30, 472, 60);
		time.setFont(new Font("Monospaced", Font.BOLD, 15));

		payCP.add(time); // content pane�� �߰�

		// JLabel ���� : ������ ���� �����ֱ�
		JLabel price = new JLabel();
		price.setText("<html> Price : " + pay + " won <br> Do you want to pay? </html>");
		price.setFont(new Font("Monospaced", Font.BOLD, 20));
		price.setBounds(200, 93, 372, 75);

		payCP.add(price); // content pane�� �߰�
		
		//��ư
		JButton btnNewButton = new JButton("Yes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//üũ�ƿ�
					mg.checkOut(roomName);
					//���� ����Ʈ�� ����
					mg.addSalesList(roomName);
					//����� ->empty�� �ٲٱ�
					model.setValueAt("Empty", row, 3);
					
					//���� �����ϱ�
					File roomInfoFile = new File("studyCafeRoom.dat");
					File salesInfoFile = new File("studyCafeSales.dat"); 
					ObjectOutputStream roomInfoOut = null;
					ObjectOutputStream salesInfoOut = null;
					
					//���Ͽ� ������&�������� ����
					try
					{
						roomInfoOut = new ObjectOutputStream(new FileOutputStream(roomInfoFile));
						salesInfoOut = new ObjectOutputStream(new FileOutputStream(salesInfoFile));
						// �� ���� ���Ͽ� ����
						mg.writeRoomInfo(roomInfoOut);
						//���� ���� ���Ͽ� ����
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
					
					//���� ���������� ���ư���
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
				//���� ���������� ���ư���
				payFrame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(313, 192, 152, 43);
		payFrame.getContentPane().add(btnNewButton_1);
	}
}
