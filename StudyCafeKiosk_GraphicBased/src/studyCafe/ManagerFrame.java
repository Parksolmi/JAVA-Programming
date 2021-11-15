package studyCafe;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

class ManagerFrame {
	
	//2. �Ŵ��� ��� ������
	
	
	private JFrame managerFrame;
	private JButton homeButton;
	private JTable roomTable;

	
	ManagerFrame(Management mg) throws Exception
	{
		//�Ŵ��� ������ ����
		managerFrame = new JFrame("Manager Mode");
		//������ ũ��
		managerFrame.setSize(600, 600);
		//������ ��ġ ����
		managerFrame.setLocation(600, 200);
		//content pane ����
		Container managerCP = managerFrame.getContentPane();
		//x��ư ������ �� �̺�Ʈó��
		managerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//������ ���÷���
		managerFrame.setVisible(true);
		//���̾ƿ� ����(��ġ�� ũ�� ��������)
		managerCP.setLayout(null);
		
		//1) Ȩ ��ư
		homeButton = new JButton("Home");
		homeButton.setBounds(500, 20, 70, 40);
		managerCP.add(homeButton); //content Pane�� �߰�
		//�̺�Ʈ
		homeButton.addActionListener(new HomeButtonAction());
		
		
		//2) roomList Table
		String colNames[] = { "Name" , "Capacity" , "Price", "Availability"}; //Ÿ��Ʋ��
		DefaultTableModel defaultModel = new DefaultTableModel(colNames, 0);
		roomTable = new JTable(defaultModel);
		//��ũ�� ���ο� ���̺� �ֱ�
		JScrollPane roomTablePane = new JScrollPane(roomTable);
		roomTablePane.setBounds(20, 80, 550, 350); //ũ�� ����
		
		//�� ���� ����Ʈ ���̺� �ֱ�
		if(!mg.getRoomList().isEmpty()) //��� ���� �ʴٸ�
		{
			String rowArr[] = new String[4];
			for(Room room : mg.getRoomList())
			{
				int i=0;
				rowArr[i] = room.getRoomName(); i++;
				rowArr[i] = Integer.toString(room.getCapacity()); i++;
				rowArr[i] = Integer.toString(room.getPricePerHour()); i++;
				if(room.getUsing()) //������̸�
				{
					rowArr[i] = "Occupied";
				}
				else //�������
				{
					rowArr[i] = "Empty";
				}
				
				defaultModel.addRow(rowArr);
			}
		}
		
		managerCP.add(roomTablePane); //content Pane�� �߰�
		
		// 3) capacity �˻�
		// �г� ����
		JPanel capacityPanel = new JPanel();

		JLabel capacityLabel = new JLabel("Capacity : ");
		capacityLabel.setFont(new Font("����", Font.PLAIN, 13));
		capacityPanel.add(capacityLabel);
		JTextField capacity = new JTextField(8);
		capacityPanel.add(capacity);
		JButton button = new JButton("Search");
		// search��ư �̺�Ʈ
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//�Է��� �ο�
				int capacityToSearch;
				if(capacity.getText().equals("")) //�ƹ� �͵� �Է����� ������ 0���� ����
				{
					capacityToSearch = 0;
				}
				else
				{
					capacityToSearch = Integer.parseInt(capacity.getText());
				}
				
				//�ش� �ο��� �� ���� ã�Ƽ� ����Ʈ�� ����
				ArrayList<Room> emptyRoomList = new ArrayList<Room>();
				emptyRoomList = mg.searchRoomByCapacity(capacityToSearch);
				
				if(!emptyRoomList.isEmpty()) //��� ���� �ʴٸ�
				{
					//���̺��� ��� �� ����
					for(int row=defaultModel.getRowCount()-1; row >=0; row--)
					{
						defaultModel.removeRow(row);
					}
					
					String rowArr[] = new String[4];
					for(Room room : emptyRoomList)
					{
						int i=0;
						rowArr[i] = room.getRoomName(); i++;
						rowArr[i] = Integer.toString(room.getCapacity()); i++;
						rowArr[i] = Integer.toString(room.getPricePerHour()); i++;
						if(room.getUsing()) //������̸�
						{
							rowArr[i] = "Occupied";
						}
						else //�������
						{
							rowArr[i] = "Empty";
						}
						
						defaultModel.addRow(rowArr);
					}
					
				}
				else //����ִٸ�
				{
					MessageFrame ef = new MessageFrame("There is no room for " + capacityToSearch);
				}
			}
		});
		capacityPanel.add(button);
		capacityPanel.setBounds(20, 20, 253, 40); // �г� ũ�� ����

		managerCP.add(capacityPanel); // content Pane�� �߰�
		
		//4) ����� ���� �Է�
		//�г� ����
		JPanel userInfoPanel = new JPanel();
		userInfoPanel.setBounds(20, 450, 550, 40);
		
		//�̸�
		JLabel name = new JLabel("Name : ");
		name.setFont(new Font("Monospaced", Font.BOLD, 20));
		userInfoPanel.add(name);
		JTextField enteredName = new JTextField(10);
		userInfoPanel.add(enteredName);
		
		//�ڵ��� ��ȣ
		JLabel phoneNum = new JLabel("  Phone Number : ");
		phoneNum.setFont(new Font("Monospaced", Font.BOLD, 20));
		userInfoPanel.add(phoneNum);
		JTextField enteredPhone = new JTextField(10);
		userInfoPanel.add(enteredPhone);
		
		managerCP.add(userInfoPanel); //content Pane�� �߰�
		
		//5) check-in/out ��ư
		//üũ �� ��ư
		JButton checkInButton = new JButton("Check-In");
		checkInButton.setBounds(140, 500, 150, 40);
		
		//üũ �� ��ư �̺�Ʈ
		checkInButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
				
				int row = roomTable.getSelectedRow(); //���õ� row
				
				if (row == -1) // ���õ� row�� ���� ��
				{
					MessageFrame ef = new MessageFrame("Please select the row");
				}
				else
				{
					if(enteredName.getText().equals("") || enteredPhone.getText().equals("")) //����� ������ �Է����� ���� ���
					{
						MessageFrame ef = new MessageFrame("Please enter the use infromation");
					}
					else //����� ������ �Է� �� ���
					{
						// �� �̸� String���� ��������
						String roomName = String.valueOf(model.getValueAt(row, 0));
						// ����� ��ü ����
						User user = new User(enteredName.getText(), enteredPhone.getText());

						// üũ��
						try {
							if (!mg.searchRoom(roomName).getUsing()) // ��� ���� �ƴ϶��
							{
								if (mg.checkIn(roomName, user)) {
									MessageFrame ef = new MessageFrame("Check In Success");
									// ��� ������ �ٲٱ�
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
				//���� �����ϱ�
				File roomInfoFile = new File("studyCafeRoom.dat");
				ObjectOutputStream roomInfoOut = null;
				
				if(!roomInfoFile.exists()) //�� ���� ������ ������
				{
					try
					{
						// �� ������ ������ ���� ����
						roomInfoOut = new ObjectOutputStream(new FileOutputStream(roomInfoFile));
					}
					catch (IOException fnfe) {
						System.out.println("The Room file could not be found.");
					}
				}
				
				//���Ͽ� ������&�������� ����
				try
				{
					roomInfoOut = new ObjectOutputStream(new FileOutputStream(roomInfoFile));
					// �� ���� ���Ͽ� ����
					mg.writeRoomInfo(roomInfoOut);
					
				} catch (FileNotFoundException fnfe) {
					System.out.println("The file could not be found.");
				} catch (IOException ioe) {
					System.out.println("The file cannot be output.");
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					try {
						roomInfoOut.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
				//�ؽ�Ʈ �ʵ�(����� ����) �����
				enteredName.setText("");
				enteredPhone.setText("");
			}
		});
		
		managerCP.add(checkInButton); //content pane�� �߰�
		
		//üũ �ƿ� ��ư
		JButton checkOutButton = new JButton("Check-Out");
		checkOutButton.setBounds(310, 500, 150, 40);
		
		//üũ �ƿ� ��ư �̺�Ʈ
		checkOutButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
				int row = roomTable.getSelectedRow(); //���õ� row
				
				if(row == -1) //���õ� row�� ���� ��
				{
					MessageFrame ef = new MessageFrame("Please select the row");
				}
				else
				{
					// ���õ� row�� �� �̸� String���� ��������
					String roomName = String.valueOf(model.getValueAt(row, 0));
					// ����� ��ü ����
					User user = new User(enteredName.getText(), enteredPhone.getText());

					if (mg.searchRoom(roomName).getUsing()) // ������� ���� ��
					{
						try {
							// ����� ���� Ȯ��
							if (mg.isRightCheckOutUser(roomName, user.getUserName(), user.getUserPhoneNum())) {
								PayFrame pf = new PayFrame(roomTable, row, mg);
							} else {
								MessageFrame ef = new MessageFrame("Wrong User Information");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else // �� ���� ��
					{
						MessageFrame ef = new MessageFrame("This room is Empty.");
					}
					
				}
				
				//�ؽ�Ʈ �ʵ�(����� ����) �����
				enteredName.setText("");
				enteredPhone.setText("");
			}
		});

		managerCP.add(checkOutButton);

	}
	
	//Ȩ ��ư �̺�Ʈ ����
	private class HomeButtonAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// Ȩ ��ư �̺�Ʈ
			if (e.getSource() == homeButton) {
				managerFrame.setVisible(false); // ���� ȭ������ ���ư���
			}
		}
	}
}

