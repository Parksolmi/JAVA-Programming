package studyCafe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AddRoomFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnCancel;

	public AddRoomFrame(Management mg,JTable roomTable) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(600, 400, 600, 300);
		contentPane = new JPanel();
		//레이아웃 설정(위치와 크기 자유지정)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Name        :");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(104, 39, 137, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblCapacity = new JLabel("Capacity    :");
		lblCapacity.setHorizontalAlignment(SwingConstants.LEFT);
		lblCapacity.setFont(new Font("굴림", Font.BOLD, 20));
		lblCapacity.setBounds(104, 77, 137, 35);
		contentPane.add(lblCapacity);
		
		JLabel lblPrice = new JLabel("Price         :");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setFont(new Font("굴림", Font.BOLD, 20));
		lblPrice.setBounds(104, 112, 137, 35);
		contentPane.add(lblPrice);
		
		JTextField name = new JTextField();
		name.setBounds(242, 39, 221, 28);
		contentPane.add(name);
		name.setColumns(10);
		
		JTextField capacity = new JTextField();
		capacity.setColumns(10);
		capacity.setBounds(242, 77, 221, 28);
		contentPane.add(capacity);
		
		JTextField price = new JTextField();
		price.setColumns(10);
		price.setBounds(242, 118, 221, 28);
		contentPane.add(price);
		
		btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//JTextField 타입 별로 가져오기
				String roomName = name.getText();
				int roomCapacity = Integer.parseInt(capacity.getText());
				int roomPrice = Integer.parseInt(price.getText());
				
				//방 만들기
				mg.createRoom(roomName, roomCapacity, roomPrice);
				
				//테이블에 추가
				String newRowArr[] = new String[4];
				int index = 0;
				newRowArr[index] = name.getText(); index++;
				newRowArr[index] = capacity.getText(); index++;
				newRowArr[index] = price.getText(); index++;
				newRowArr[index] = "Empty";
				
				DefaultTableModel tableModel = (DefaultTableModel) roomTable.getModel();
				tableModel.addRow(newRowArr);
				
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(147, 182, 131, 35);
		contentPane.add(btnNewButton);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("굴림", Font.BOLD, 20));
		btnCancel.setBounds(310, 182, 131, 35);
		contentPane.add(btnCancel);
		
		//프레임 디스플레이
		setVisible(true);
	}

}
