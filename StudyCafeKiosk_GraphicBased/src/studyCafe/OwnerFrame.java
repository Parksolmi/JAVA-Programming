package studyCafe;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
class OwnerFrame extends JFrame {
	
	private JPanel contentPane;
	
	//���� ������ �ִ��� üũ�ϴ� ��ü
	private static boolean saveFlag;
	private JButton btnNewButton_1;
	
	public static boolean getSaveFlag()
	{
		return saveFlag;
	}
	public static void setSaveFlagTrue()
	{
		saveFlag = true;
	}
	
	OwnerFrame(Management mg)
	{	
		//false�� �ʱ�ȭ
		saveFlag = false;
		
		//3. ���� ��� ������
		//������ Ÿ��Ʋ ����
		this.setTitle("Owner Mode");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(500, 200, 900, 700);
		contentPane = new JPanel();
		//���̾ƿ� ����(��ġ�� ũ�� ��������)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//�� ����
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("����", Font.BOLD, 20));
		//�г� ����
		//1> �� ���� �г�
		JPanel roomMgPannel = new JPanel();
		roomMgPannel.setBackground(new Color(176, 196, 222));
		roomMgPannel.setLayout(null);
		
		// 1) �Ŵ��� ID ���� ��ư
		JButton btnNewButton = new JButton("Revise ID");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(119, 136, 153));
		btnNewButton.setBounds(663, 23, 104, 40);
		roomMgPannel.add(btnNewButton);
		btnNewButton.setFont(new Font("����", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReviseIDFrame rif = new ReviseIDFrame(mg);
			}
		});

		// 2) �α׾ƿ� ��ư
		JButton btnNewButton_1_1 = new JButton("Log out");
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setBackground(new Color(119, 136, 153));
		btnNewButton_1_1.setBounds(772, 23, 94, 40);
		roomMgPannel.add(btnNewButton_1_1);
		btnNewButton_1_1.setFont(new Font("����", Font.BOLD, 13));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// saveFlag�� true�̸� saveFrame�� ����
				if (saveFlag == true) {
					SaveFrame sf = new SaveFrame(mg);
				}
				// �α׾ƿ�
				setVisible(false);
			}
		});
		
		
		// 3) �� ���� ���̺�
		String table1colNames[] = { "Name", "Capacity", "Price", "Availability", "User", "Phone" }; // Ÿ��Ʋ��
		DefaultTableModel defaultModel1 = new DefaultTableModel(table1colNames, 0);
		JTable roomTable = new JTable(defaultModel1);

		JScrollPane scrollPane = new JScrollPane(roomTable);
		scrollPane.setBounds(23, 23, 631, 590);

		// table�� ���� �ֱ�
		// �� ���� ����Ʈ ���̺� �ֱ�
		if (!mg.getRoomList().isEmpty()) // ��� ���� �ʴٸ�
		{
			String rowArr[] = new String[6];
			for (Room room : mg.getRoomList()) {
				int i = 0;
				rowArr[i] = room.getRoomName();
				i++;
				rowArr[i] = Integer.toString(room.getCapacity());
				i++;
				rowArr[i] = Integer.toString(room.getPricePerHour());
				i++;
				if (room.getUsing()) // ������̸�
				{
					rowArr[i] = "Occupied";
					i++;
					rowArr[i] = room.getUser().getUserName();
					i++;
					rowArr[i] = room.getUser().getUserPhoneNum();

				} else // �������
				{
					rowArr[i] = "Empty";
					i++;
					rowArr[i] = null;
					i++;
					rowArr[i] = null;
				}

				defaultModel1.addRow(rowArr);
			}
		}
		roomMgPannel.add(scrollPane);
		
		//4) �� ���� ��ư 3��(add/revise/delete)
		//Add
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(119, 136, 153));
		btnAdd.setFont(new Font("����", Font.BOLD, 16));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRoomFrame arf = new AddRoomFrame(mg, roomTable);
			}
		});
		btnAdd.setBounds(680, 396, 180, 40);
		roomMgPannel.add(btnAdd);

		//Revise
		JButton btnNewButton_1_2 = new JButton("Revise");
		btnNewButton_1_2.setForeground(Color.WHITE);
		btnNewButton_1_2.setBackground(new Color(119, 136, 153));
		btnNewButton_1_2.setFont(new Font("����", Font.BOLD, 16));
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = roomTable.getSelectedRow();

				if (row == -1) {
					MessageFrame mf = new MessageFrame("Please select the row.");
				} else {
					// ������̸�
					if (String.valueOf(defaultModel1.getValueAt(row, 3)).equals("Occupied")) {
						MessageFrame mf = new MessageFrame(
								"<html>This room is using. <br>You cannot revise it.</html>");
					} else // ����ٸ�
					{
						ReviseRoomFrame rrf = new ReviseRoomFrame(mg, roomTable);
					}
				}
			}
		});
		btnNewButton_1_2.setBounds(680, 446, 180, 40);
		roomMgPannel.add(btnNewButton_1_2);

		//Delete
		JButton btnNewButton_1_2_1 = new JButton("Delete");
		btnNewButton_1_2_1.setForeground(Color.WHITE);
		btnNewButton_1_2_1.setBackground(new Color(119, 136, 153));
		btnNewButton_1_2_1.setFont(new Font("����", Font.BOLD, 16));
		btnNewButton_1_2_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int row = roomTable.getSelectedRow();

				if (row == -1) // �� ������ �� �ߴٸ�
				{
					MessageFrame mf = new MessageFrame("Please select the row.");
				} else // �� ������ �ߴٸ�
				{
					// ������̸�
					if (String.valueOf(defaultModel1.getValueAt(row, 3)).equals("Occupied")) {
						MessageFrame mf = new MessageFrame(
								"<html>This room is using. <br>You cannot delete it.</html>");
					} else // ����ٸ�
					{
						DeleteRoomFrame drf = new DeleteRoomFrame(mg, roomTable);
					}

				}
			}
		});
		btnNewButton_1_2_1.setBounds(680, 495, 180, 40);
		roomMgPannel.add(btnNewButton_1_2_1);
		
		// 5) ���� ��ư
		JButton btnSave = new JButton("Save");
		btnSave.setForeground(Color.WHITE);
		btnSave.setBackground(new Color(119, 136, 153));
		btnSave.setFont(new Font("����", Font.BOLD, 16));
		btnSave.setBounds(680, 567, 180, 40);
		// ��ư�̺�Ʈ
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveFrame sf = new SaveFrame(mg);
				saveFlag = false;
			}
		});
		roomMgPannel.add(btnSave);
		
		
		//2> ���� ���� �г�
		JPanel salesMgPannel = new JPanel();
		salesMgPannel.setBackground(new Color(176, 196, 222));
		salesMgPannel.setLayout(null);
		
		// 1) �Ŵ��� ID ���� ��ư
		JButton btnNewButton2 = new JButton("Revise ID");
		btnNewButton2.setForeground(Color.WHITE);
		btnNewButton2.setBackground(new Color(119, 136, 153));
		btnNewButton2.setBounds(663, 23, 104, 40);
		salesMgPannel.add(btnNewButton2);
		btnNewButton2.setFont(new Font("����", Font.BOLD, 14));
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReviseIDFrame rif = new ReviseIDFrame(mg);
			}
		});

		// 2) �α׾ƿ� ��ư
		JButton btnNewButton_2_2 = new JButton("Log out");
		btnNewButton_2_2.setForeground(Color.WHITE);
		btnNewButton_2_2.setBackground(new Color(119, 136, 153));
		btnNewButton_2_2.setBounds(772, 23, 94, 40);
		salesMgPannel.add(btnNewButton_2_2);
		btnNewButton_2_2.setFont(new Font("����", Font.BOLD, 13));
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// saveFlag�� true�̸� saveFrame�� ����
				if (saveFlag == true) {
					SaveFrame sf = new SaveFrame(mg);
				}
				// �α׾ƿ�
				setVisible(false);
			}
		});
		
		// 3) ���� ���� ���̺�
		// �߾� Table
		String table2colNames[] = { "Date", "Name", "Phone number", "Price" }; // Ÿ��Ʋ��
		DefaultTableModel defaultModel2 = new DefaultTableModel(table2colNames, 0);
		JTable salesTable = new JTable(defaultModel2);

		// Table �� ũ�� ����
		salesTable.getColumn("Date").setPreferredWidth(140);
		salesTable.getColumn("Name").setPreferredWidth(40);
		salesTable.getColumn("Phone number").setPreferredWidth(100);
		salesTable.getColumn("Price").setPreferredWidth(60);
		// ����
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		salesTable.getColumn("Date").setCellRenderer(celAlignCenter);
		salesTable.getColumn("Name").setCellRenderer(celAlignCenter);
		salesTable.getColumn("Phone number").setCellRenderer(celAlignCenter);
		salesTable.getColumn("Price").setCellRenderer(celAlignRight);

		scrollPane = new JScrollPane(salesTable);
		scrollPane.setBounds(23, 74, 629, 506);
		salesMgPannel.add(scrollPane);

		// table�� ���� �ֱ�
		// ���� ���� ���̺� �ֱ�
		if (!mg.getSalesList().isEmpty()) // ��� ���� �ʴٸ�
		{
			String rowArr[] = new String[4];

			// ��¥ ���� ����
			SimpleDateFormat dateFormat = new SimpleDateFormat("YY.MM.dd aa hh:mm:ss");
			String date;

			for (Sales sales : mg.getSalesList()) {
				int i = 0;
				date = dateFormat.format(sales.getPaymentDate().getTime());
				rowArr[i] = date;
				i++;
				rowArr[i] = sales.getPayedUser().getUserName();
				i++;
				rowArr[i] = sales.getPayedUser().getUserPhoneNum();
				i++;
				rowArr[i] = Integer.toString(sales.getPayedBill());

				defaultModel2.addRow(rowArr);
			}
		}

		// �� �Ʒ� �ջ� ����
		JLabel lblNewLabel_9 = new JLabel("Accumulated Sales");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(23, 580, 631, 33);
		salesMgPannel.add(lblNewLabel_9);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_3.setBounds(23, 580, 631, 33);

		salesMgPannel.add(panel_3);

		// search ���
		JLabel lblNewLabel = new JLabel("Year");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel.setBounds(64, 27, 46, 29);
		salesMgPannel.add(lblNewLabel);

		JTextField year = new JTextField();
		year.setBounds(103, 27, 68, 29);
		salesMgPannel.add(year);
		year.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Month");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_1.setBounds(182, 27, 55, 29);
		salesMgPannel.add(lblNewLabel_1);

		JTextField month = new JTextField();
		month.setColumns(10);
		month.setBounds(233, 27, 55, 29);
		salesMgPannel.add(month);

		JLabel lblNewLabel_2 = new JLabel("Day");
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_2.setBounds(298, 27, 46, 29);
		salesMgPannel.add(lblNewLabel_2);

		JTextField day = new JTextField();
		day.setColumns(10);
		day.setBounds(331, 27, 55, 29);
		salesMgPannel.add(day);

		btnNewButton_1 = new JButton("Search");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(119, 136, 153));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (year.getText().equals("") || month.getText().equals("") || day.getText().equals("")) {
					// ��ü ���� ���
					// ���̺��� ��� �� ����
					for (int row = defaultModel2.getRowCount() - 1; row >= 0; row--) {
						defaultModel2.removeRow(row);
					}

					if (!mg.getSalesList().isEmpty()) {
						String rowArr[] = new String[4];

						// ��¥ ���� ����
						SimpleDateFormat dateFormat = new SimpleDateFormat("YY.MM.dd aa hh:mm:ss");
						String date;

						for (Sales sales : mg.getSalesList()) {
							int i = 0;
							date = dateFormat.format(sales.getPaymentDate().getTime());
							rowArr[i] = date;
							i++;
							rowArr[i] = sales.getPayedUser().getUserName();
							i++;
							rowArr[i] = sales.getPayedUser().getUserPhoneNum();
							i++;
							rowArr[i] = Integer.toString(sales.getPayedBill());

							defaultModel2.addRow(rowArr);
						}
					}
				} else // �ش� ��¥ ���븸 ���
				{
					int theYear = Integer.parseInt(year.getText());
					int theMonth = Integer.parseInt(month.getText());
					int theDay = Integer.parseInt(day.getText());

					// ǥ�� ����Ʈ ���� �ֱ�
					// ���̺��� ��� �� ����
					for (int row = defaultModel2.getRowCount() - 1; row >= 0; row--) {
						defaultModel2.removeRow(row);
					}

					// �ش� ��¥�� ���� ����Ʈ ��������
					ArrayList<Sales> searchSalesList = mg.salesForTheDay(theYear, theMonth, theDay);

					if (!searchSalesList.isEmpty()) // ��� ���� �ʴٸ�
					{
						// ���̺��� ��� �� ����
						for (int row = defaultModel2.getRowCount() - 1; row >= 0; row--) {
							defaultModel2.removeRow(row);
						}

						String rowArr[] = new String[4];

						// ��¥ ���� ����
						SimpleDateFormat dateFormat = new SimpleDateFormat("YY.MM.dd aa hh:mm:ss");
						String date;

						for (Sales sales : searchSalesList) {
							int i = 0;
							date = dateFormat.format(sales.getPaymentDate().getTime());
							rowArr[i] = date;
							i++;
							rowArr[i] = sales.getPayedUser().getUserName();
							i++;
							rowArr[i] = sales.getPayedUser().getUserPhoneNum();
							i++;
							rowArr[i] = Integer.toString(sales.getPayedBill());

							defaultModel2.addRow(rowArr);
						}

					} else // ����ִٸ�
					{
						// ���̺��� ��� �� ����
						for (int row = defaultModel2.getRowCount() - 1; row >= 0; row--) {
							defaultModel2.removeRow(row);
						}
					}
				}
				// ���̺��� ��� ���� ���� �ջ�
				int accumulatedSales = 0;
				for (int row = defaultModel2.getRowCount() - 1; row >= 0; row--) {
					int price = Integer.parseInt(defaultModel2.getValueAt(row, 3).toString());
					accumulatedSales += price;
				}
				lblNewLabel_9.setText(String.valueOf(accumulatedSales));
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.BOLD, 15));
		btnNewButton_1.setBounds(404, 23, 89, 40);
		salesMgPannel.add(btnNewButton_1);

		// �⺻ ���� ����

		JLabel lblNewLabel_3 = new JLabel("[ Sales for yesterday ]");
		lblNewLabel_3.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(669, 128, 199, 29);
		salesMgPannel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("0");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setBounds(698, 166, 146, 29);
		// ���� ���� ���
		GregorianCalendar today = new GregorianCalendar();

		int dayYear = today.get(Calendar.YEAR);
		int dayMonth = today.get(Calendar.MONTH);
		int dayDate = today.get(Calendar.DATE);
		GregorianCalendar yesterday = new GregorianCalendar(dayYear, dayMonth, dayDate - 1);
		String salesForYesterday = Integer.toString(mg.salesForDay(yesterday));
		lblNewLabel_4.setText(salesForYesterday);
		salesMgPannel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("[ Sales for today ]");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_5.setBounds(688, 215, 167, 29);
		salesMgPannel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("0");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBackground(Color.WHITE);
		lblNewLabel_6.setBounds(698, 249, 146, 29);

		// ���� ���� ���
		today = new GregorianCalendar();
		String salesForToday = Integer.toString(mg.salesForDay(today));
		lblNewLabel_6.setText(salesForToday);
		salesMgPannel.add(lblNewLabel_4);
		salesMgPannel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("[ Sales for this month ]");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_7.setBounds(671, 525, 196, 29);
		salesMgPannel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("0");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBackground(Color.WHITE);
		lblNewLabel_8.setBounds(702, 564, 146, 29);

		// �̹� �� ���� ���
		today = new GregorianCalendar();
		String salesForThisMonth = Integer.toString(mg.salesForMonth(today));
		lblNewLabel_8.setText(salesForThisMonth);
		salesMgPannel.add(lblNewLabel_8);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(698, 166, 146, 29);
		salesMgPannel.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(698, 249, 146, 29);
		salesMgPannel.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBounds(701, 562, 146, 29);
		salesMgPannel.add(panel_2);

		JButton btnAll = new JButton("All");
		btnAll.setForeground(Color.WHITE);
		btnAll.setBackground(new Color(119, 136, 153));
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				year.setText("");
				month.setText("");
				day.setText("");

				// ���̺��� ��� �� ����
				for (int row = defaultModel2.getRowCount() - 1; row >= 0; row--) {
					defaultModel2.removeRow(row);
				}

				// ���̺� ��ü ���� ���� �ֱ�
				if (!mg.getSalesList().isEmpty()) // ��� ���� �ʴٸ�
				{
					String rowArr[] = new String[4];

					// ��¥ ���� ����
					SimpleDateFormat dateFormat = new SimpleDateFormat("YY.MM.dd aa hh:mm:ss");
					String date;

					for (Sales sales : mg.getSalesList()) {
						int i = 0;
						date = dateFormat.format(sales.getPaymentDate().getTime());
						rowArr[i] = date;
						i++;
						rowArr[i] = sales.getPayedUser().getUserName();
						i++;
						rowArr[i] = sales.getPayedUser().getUserPhoneNum();
						i++;
						rowArr[i] = Integer.toString(sales.getPayedBill());

						defaultModel2.addRow(rowArr);
					}
					
					// ���̺��� ��� ���� ���� �ջ�
					int accumulatedSales = 0;
					for (int row = defaultModel2.getRowCount() - 1; row >= 0; row--) {
						int price = Integer.parseInt(defaultModel2.getValueAt(row, 3).toString());
						accumulatedSales += price;
					}
					lblNewLabel_9.setText(String.valueOf(accumulatedSales));
				}
			}
		});
		btnAll.setFont(new Font("����", Font.BOLD, 15));
		btnAll.setBounds(503, 23, 89, 40);
		salesMgPannel.add(btnAll);

		
		
		//�� �޴� ����
		tabbedPane.addTab(" Room Management  " , roomMgPannel);
		tabbedPane.addTab(" Sales Management  " , salesMgPannel);
		
		tabbedPane.setBounds(0, 0, 884, 661);
		contentPane.add(tabbedPane);
		
		//������ ���÷���
		setVisible(true);
	}
}
