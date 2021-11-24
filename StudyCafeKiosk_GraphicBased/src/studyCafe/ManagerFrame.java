package studyCafe;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

class ManagerFrame {
	
	//2. 매니저 기능 윈도우
	
	
	private JFrame managerFrame;
	private JButton homeButton;
	private JTable roomTable;

	
	ManagerFrame(Management mg) throws Exception
	{
		//매니저 프레임 생성
		managerFrame = new JFrame("Manager Mode");
		managerFrame.getContentPane().setBackground(new Color(176, 196, 222));
		//프레임 크기
		managerFrame.setSize(900, 700);
		//윈도우 위치 설정
		managerFrame.setLocation(500, 200);
		//content pane 생성
		Container managerCP = managerFrame.getContentPane();
		//x버튼 눌렀을 때 이벤트처리
		managerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//프레임 디스플레이
		managerFrame.setVisible(true);
		//레이아웃 설정(위치와 크기 자유지정)
		managerCP.setLayout(null);
		
		//1) 홈 버튼
		homeButton = new JButton("Home");
		homeButton.setBackground(new Color(119, 136, 153));
		homeButton.setForeground(Color.WHITE);
		homeButton.setFont(new Font("굴림", Font.BOLD, 14));
		homeButton.setBounds(20, 33, 84, 37);
		managerCP.add(homeButton); //content Pane에 추가
		//이벤트
		homeButton.addActionListener(new HomeButtonAction());
		
		
		//2) roomList Table
		String colNames[] = { "Name" , "Capacity" , "Price", "Availability"}; //타이틀바
		DefaultTableModel defaultModel = new DefaultTableModel(colNames, 0);
		roomTable = new JTable(defaultModel);
		roomTable.setBackground(SystemColor.info);
		//스크롤 페인에 테이블 넣기
		JScrollPane roomTablePane = new JScrollPane(roomTable);
		roomTablePane.setBounds(20, 91, 548, 543); //크기 조정
		
		//룸 정보 리스트 테이블에 넣기
		if(!mg.getRoomList().isEmpty()) //비어 있지 않다면
		{
			String rowArr[] = new String[4];
			for(Room room : mg.getRoomList())
			{
				int i=0;
				rowArr[i] = room.getRoomName(); i++;
				rowArr[i] = Integer.toString(room.getCapacity()); i++;
				rowArr[i] = Integer.toString(room.getPricePerHour()); i++;
				if(room.getUsing()) //사용중이면
				{
					rowArr[i] = "Occupied";
				}
				else //비었으면
				{
					rowArr[i] = "Empty";
				}
				
				defaultModel.addRow(rowArr);
			}
		}
		
		managerCP.add(roomTablePane);
		
		//3) 상단메뉴
		JLabel capacityLabel = new JLabel("Capacity : ");
		capacityLabel.setFont(new Font("굴림", Font.BOLD, 18));
		capacityLabel.setBounds(193, 36, 103, 33);
		managerFrame.getContentPane().add(capacityLabel);
		
		JTextField capacity = new JTextField(8);
		capacity.setBounds(293, 38, 94, 31);
		managerFrame.getContentPane().add(capacity);
		
		JButton button = new JButton("Search");
		button.setBackground(new Color(119, 136, 153));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("굴림", Font.BOLD, 14));
		// search버튼 이벤트
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 입력한 인원
				int capacityToSearch;
				if (capacity.getText().equals("")) // 아무 것도 입력하지 않으면 0으로 간주
				{
					capacityToSearch = 0;
				} else {
					capacityToSearch = Integer.parseInt(capacity.getText());
				}

				// 해당 인원의 빈 방을 찾아서 리스트에 저장
				ArrayList<Room> emptyRoomList = new ArrayList<Room>();
				emptyRoomList = mg.searchRoomByCapacity(capacityToSearch);

				if (!emptyRoomList.isEmpty()) // 비어 있지 않다면
				{
					// 테이블의 모든 행 삭제
					for (int row = defaultModel.getRowCount() - 1; row >= 0; row--) {
						defaultModel.removeRow(row);
					}

					String rowArr[] = new String[4];
					for (Room room : emptyRoomList) {
						int i = 0;
						rowArr[i] = room.getRoomName();
						i++;
						rowArr[i] = Integer.toString(room.getCapacity());
						i++;
						rowArr[i] = Integer.toString(room.getPricePerHour());
						i++;
						if (room.getUsing()) // 사용중이면
						{
							rowArr[i] = "Occupied";
						} else // 비었으면
						{
							rowArr[i] = "Empty";
						}

						defaultModel.addRow(rowArr);
					}

				} else // 비어있다면
				{
					MessageFrame ef = new MessageFrame("There is no room for " + capacityToSearch);
				}
			}
		});
		
		button.setBounds(396, 33, 84, 37);
		managerFrame.getContentPane().add(button);
		
		JButton btnAll = new JButton("All");
		btnAll.setBackground(new Color(119, 136, 153));
		btnAll.setForeground(Color.WHITE);
		btnAll.setFont(new Font("굴림", Font.BOLD, 14));
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capacity.setText("");
				
				// 테이블의 모든 행 삭제
				for (int row = defaultModel.getRowCount() - 1; row >= 0; row--) {
					defaultModel.removeRow(row);
				}
				
				//전체 룸 정보 리스트 테이블에 넣기
				if(!mg.getRoomList().isEmpty()) //비어 있지 않다면
				{
					String rowArr[] = new String[4];
					for(Room room : mg.getRoomList())
					{
						int i=0;
						rowArr[i] = room.getRoomName(); i++;
						rowArr[i] = Integer.toString(room.getCapacity()); i++;
						rowArr[i] = Integer.toString(room.getPricePerHour()); i++;
						if(room.getUsing()) //사용중이면
						{
							rowArr[i] = "Occupied";
						}
						else //비었으면
						{
							rowArr[i] = "Empty";
						}
						
						defaultModel.addRow(rowArr);
					}
				}
			}
		});
		btnAll.setBounds(486, 34, 84, 37);
		managerFrame.getContentPane().add(btnAll);
		
		
		//4) 사용자 정보 입력
		//이름
		JLabel lblNewLabel = new JLabel("Name           :");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(584, 483, 152, 37);
		managerFrame.getContentPane().add(lblNewLabel);
		
		JTextField enteredName = new JTextField();
		enteredName.setBounds(739, 483, 133, 33);
		managerFrame.getContentPane().add(enteredName);
		enteredName.setColumns(10);
		
		//전화번호
		JLabel lblPhoneNumber = new JLabel("Phone Number : ");
		lblPhoneNumber.setFont(new Font("굴림", Font.BOLD, 18));
		lblPhoneNumber.setBounds(581, 513, 152, 46);
		managerFrame.getContentPane().add(lblPhoneNumber);
		
		JTextField enteredPhone = new JTextField();
		enteredPhone.setColumns(10);
		enteredPhone.setBounds(739, 520, 133, 33);
		managerFrame.getContentPane().add(enteredPhone);
		
		
		//5) check-in/out 버튼
		//체크 인 버튼
		JButton checkInButton = new JButton("Check-In");
		checkInButton.setBackground(new Color(119, 136, 153));
		checkInButton.setForeground(Color.WHITE);
		checkInButton.setFont(new Font("굴림", Font.BOLD, 15));
		checkInButton.setBounds(584, 591, 138, 43);
		
		//체크 인 버튼 이벤트
		checkInButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
				
				int row = roomTable.getSelectedRow(); //선택된 row
				
				if (row == -1) // 선택된 row가 없을 때
				{
					MessageFrame ef = new MessageFrame("Please select the row");
				}
				else
				{
					if(enteredName.getText().equals("") || enteredPhone.getText().equals("")) //사용자 정보를 입력하지 않은 경우
					{
						MessageFrame ef = new MessageFrame("Please enter the user infromation");
					}
					else //사용자 정보를 입력 한 경우
					{
						// 방 이름 String으로 가져오기
						String roomName = String.valueOf(model.getValueAt(row, 0));
						// 사용자 객체 생성
						User user = new User(enteredName.getText(), enteredPhone.getText());

						// 체크인
						try {
							if (!mg.searchRoom(roomName).getUsing()) // 사용 중이 아니라면
							{
								if (mg.checkIn(roomName, user)) {
									MessageFrame ef = new MessageFrame("Check In Success");
									// 사용 중으로 바꾸기
									model.setValueAt("Occupied", row, 3);
								} else {
									MessageFrame ef = new MessageFrame("Check In Failed");
								}
							} else {
								MessageFrame ef = new MessageFrame("This room is occupied");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
				//파일 저장하기
				File roomInfoFile = new File("studyCafeRoom.dat");
				ObjectOutputStream roomInfoOut = null;
				
				if(!roomInfoFile.exists()) //방 정보 파일이 없으면
				{
					try
					{
						// 방 정보를 저장할 파일 생성
						roomInfoOut = new ObjectOutputStream(new FileOutputStream(roomInfoFile));
					}
					catch (IOException fnfe) {
						System.out.println("The Room file could not be found.");
					}
				}
				
				//파일에 방정보&매출정보 저장
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
					e1.printStackTrace();
				} finally {
					try {
						roomInfoOut.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
				//텍스트 필드(사용자 정보) 지우기
				enteredName.setText("");
				enteredPhone.setText("");
			}
		});
		
		managerCP.add(checkInButton); //content pane에 추가
		
		//체크 아웃 버튼
		JButton checkOutButton = new JButton("Check-Out");
		checkOutButton.setBackground(new Color(119, 136, 153));
		checkOutButton.setForeground(Color.WHITE);
		checkOutButton.setFont(new Font("굴림", Font.BOLD, 15));
		checkOutButton.setBounds(732, 591, 138, 43);
		
		//체크 아웃 버튼 이벤트
		checkOutButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
				int row = roomTable.getSelectedRow(); //선택된 row
				
				if(row == -1) //선택된 row가 없을 때
				{
					MessageFrame ef = new MessageFrame("Please select the row");
				}
				else
				{
					// 선택된 row의 방 이름 String으로 가져오기
					String roomName = String.valueOf(model.getValueAt(row, 0));
					// 사용자 객체 생성
					User user = new User(enteredName.getText(), enteredPhone.getText());

					if (mg.searchRoom(roomName).getUsing()) // 사용중인 방일 때
					{
						try {
							// 사용자 정보 확인
							if (mg.isRightCheckOutUser(roomName, user.getUserName(), user.getUserPhoneNum())) {
								PayFrame pf = new PayFrame(roomTable, row, mg);
							} else {
								MessageFrame ef = new MessageFrame("Wrong User Information");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else // 빈 방일 때
					{
						MessageFrame ef = new MessageFrame("This room is Empty.");
					}
					
				}
				
				//텍스트 필드(사용자 정보) 지우기
				enteredName.setText("");
				enteredPhone.setText("");
			}
		});

		managerCP.add(checkOutButton);
		
		
		
		

	}
	
	//홈 버튼 이벤트 구현
	private class HomeButtonAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// 홈 버튼 이벤트
			if (e.getSource() == homeButton) {
				managerFrame.setVisible(false); // 이전 화면으로 돌아가기
			}
		}
	}
}

