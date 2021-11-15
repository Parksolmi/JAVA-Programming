package studyCafe;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

@SuppressWarnings("serial")
class OwnerFrame extends JFrame {
	
	private JPanel contentPane;
	
	OwnerFrame(Management mg)
	{
		//3. 오너 기능 윈도우
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(600, 200, 600, 600);
		contentPane = new JPanel();
		//레이아웃 설정(위치와 크기 자유지정)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//상단 버튼
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveFrame sf = new SaveFrame(mg);
			}
		});
		btnNewButton.setBounds(22, 22, 106, 40);
		contentPane.add(btnNewButton);
		
		JButton btnSales = new JButton("Sales");
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SalesFrame sf = new SalesFrame(mg);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSales.setBounds(133, 22, 106, 40);
		contentPane.add(btnSales);
		
		JButton btnNewButton_1_1_1 = new JButton("Revise ID");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReviseIDFrame rif = new ReviseIDFrame(mg);
			}
		});
		btnNewButton_1_1_1.setBounds(343, 22, 106, 40);
		contentPane.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1 = new JButton("Log out");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveFrame sf = new SaveFrame(mg);
				setVisible(false);
			}
		});
		btnNewButton_1_1.setBounds(453, 22, 106, 40);
		contentPane.add(btnNewButton_1_1);
		
		//중앙 Table
		String colNames[] = { "Name" , "Capacity" , "Price", "Availability", "User", "Phone"}; //타이틀바
		DefaultTableModel defaultModel = new DefaultTableModel(colNames, 0);
		JTable roomTable = new JTable(defaultModel);
		
		JScrollPane scrollPane = new JScrollPane(roomTable);
		scrollPane.setBounds(22, 87, 537, 377);
		
		//table에 내용 넣기
		//룸 정보 리스트 테이블에 넣기
		if (!mg.getRoomList().isEmpty()) // 비어 있지 않다면
		{
			String rowArr[] = new String[6];
			for (Room room : mg.getRoomList()) {
				int i = 0;
				rowArr[i] = room.getRoomName(); i++;
				rowArr[i] = Integer.toString(room.getCapacity()); i++;
				rowArr[i] = Integer.toString(room.getPricePerHour()); i++;
				if (room.getUsing()) // 사용중이면
				{
					rowArr[i] = "Occupied"; i++;
					rowArr[i] = room.getUser().getUserName(); i++;
					rowArr[i] = room.getUser().getUserPhoneNum();
					
				} else // 비었으면
				{
					rowArr[i] = "Empty"; i++;
					rowArr[i] = null; i++;
					rowArr[i] = null;
				}

				defaultModel.addRow(rowArr);
			}
		}
		contentPane.add(scrollPane);
		
		//하단 버튼
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRoomFrame arf = new AddRoomFrame(mg, roomTable);
			}
		});
		btnAdd.setBounds(71, 489, 136, 40);
		contentPane.add(btnAdd);
		
		JButton btnNewButton_1_2 = new JButton("Revise");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = roomTable.getSelectedRow();
				
				if(row == -1)
				{
					MessageFrame mf = new MessageFrame("Please select the row.");
				}
				else
				{
					//사용중이면
					if(String.valueOf(defaultModel.getValueAt(row, 3)).equals("Occupied"))
					{
						MessageFrame mf = new MessageFrame("<html>This room is using. <br>You cannot revise it.</html>");
					}
					else //비었다면
					{
						ReviseRoomFrame rrf = new ReviseRoomFrame(mg, roomTable);
					}
				}
			}
		});
		btnNewButton_1_2.setBounds(232, 489, 136, 40);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_2_1 = new JButton("Delete");
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int row = roomTable.getSelectedRow();
				
				if(row == -1) //행 선택을 안 했다면
				{
					MessageFrame mf = new MessageFrame("Please select the row.");
				}
				else //행 선택을 했다면
				{
					//사용중이면
					if(String.valueOf(defaultModel.getValueAt(row, 3)).equals("Occupied"))
					{
						MessageFrame mf = new MessageFrame("<html>This room is using. <br>You cannot delete it.</html>");
					}
					else //비었다면
					{
						DeleteRoomFrame drf = new DeleteRoomFrame(mg, roomTable);
					}
					
				}
			}
		});
		btnNewButton_1_2_1.setBounds(391, 489, 136, 40);
		contentPane.add(btnNewButton_1_2_1);
		

		
		//프레임 디스플레이
		setVisible(true);
	}
}
