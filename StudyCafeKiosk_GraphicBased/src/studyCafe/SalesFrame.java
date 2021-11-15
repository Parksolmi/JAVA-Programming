package studyCafe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SalesFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel_9;
	private JPanel panel_3;

	public SalesFrame(Management mg) throws Exception {
		setTitle("Sales");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(600, 200, 600, 600);
		contentPane = new JPanel();
		//���̾ƿ� ����(��ġ�� ũ�� ��������)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		// �߾� Table
		String colNames[] = { "Date", "Name", "Phone number", "Price" }; // Ÿ��Ʋ��
		DefaultTableModel defaultModel = new DefaultTableModel(colNames, 0);
		JTable roomTable = new JTable(defaultModel);

		// Table �� ũ�� ����
		roomTable.getColumn("Date").setPreferredWidth(140);
		roomTable.getColumn("Name").setPreferredWidth(40);
		roomTable.getColumn("Phone number").setPreferredWidth(100);
		roomTable.getColumn("Price").setPreferredWidth(60);
		// ����
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		roomTable.getColumn("Date").setCellRenderer(celAlignCenter);
		roomTable.getColumn("Name").setCellRenderer(celAlignCenter);
		roomTable.getColumn("Phone number").setCellRenderer(celAlignCenter);
		roomTable.getColumn("Price").setCellRenderer(celAlignRight);

		scrollPane = new JScrollPane(roomTable);
		scrollPane.setBounds(23, 73, 373, 434);
		contentPane.add(scrollPane);

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
				rowArr[i] = date; i++;
				rowArr[i] = sales.getPayedUser().getUserName(); i++;
				rowArr[i] = sales.getPayedUser().getUserPhoneNum(); i++;
				rowArr[i] = Integer.toString(sales.getPayedBill());

				defaultModel.addRow(rowArr);
			}
		}

		//�� �Ʒ� �ջ� ����
		lblNewLabel_9 = new JLabel("Accumulated Sales");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(23, 509, 373, 29);
		contentPane.add(lblNewLabel_9);

		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_3.setBounds(23, 509, 373, 29);
		
		contentPane.add(panel_3);

		
		//search ���
		JLabel lblNewLabel = new JLabel("Year");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel.setBounds(23, 21, 46, 29);
		contentPane.add(lblNewLabel);
		
		JTextField year = new JTextField();
		year.setBounds(62, 21, 68, 29);
		contentPane.add(year);
		year.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Month");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_1.setBounds(141, 21, 55, 29);
		contentPane.add(lblNewLabel_1);
		
		JTextField month = new JTextField();
		month.setColumns(10);
		month.setBounds(192, 21, 55, 29);
		contentPane.add(month);
		
		JLabel lblNewLabel_2 = new JLabel("Day");
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_2.setBounds(257, 21, 46, 29);
		contentPane.add(lblNewLabel_2);
		
		JTextField day = new JTextField();
		day.setColumns(10);
		day.setBounds(290, 21, 55, 29);
		contentPane.add(day);
		
		btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(year.getText().equals("") || month.getText().equals("") || day.getText().equals(""))
				{
					//��ü ���� ���
					//���̺��� ��� �� ����
					for(int row=defaultModel.getRowCount()-1; row >=0; row--)
					{
						defaultModel.removeRow(row);
					}
					
					if (!mg.getSalesList().isEmpty())
					{
						String rowArr[] = new String[4];

						// ��¥ ���� ����
						SimpleDateFormat dateFormat = new SimpleDateFormat("YY.MM.dd aa hh:mm:ss");
						String date;

						for (Sales sales : mg.getSalesList()) {
							int i = 0;
							date = dateFormat.format(sales.getPaymentDate().getTime());
							rowArr[i] = date; i++;
							rowArr[i] = sales.getPayedUser().getUserName(); i++;
							rowArr[i] = sales.getPayedUser().getUserPhoneNum(); i++;
							rowArr[i] = Integer.toString(sales.getPayedBill());

							defaultModel.addRow(rowArr);
						}
					}
				}
				else //�ش� ��¥ ���븸 ���
				{
					int theYear = Integer.parseInt(year.getText());
					int theMonth = Integer.parseInt(month.getText());
					int theDay = Integer.parseInt(day.getText());
					
					
					//ǥ�� ����Ʈ ���� �ֱ�
					//���̺��� ��� �� ����
					for(int row=defaultModel.getRowCount()-1; row >=0; row--)
					{
						defaultModel.removeRow(row);
					}
					
					//�ش� ��¥�� ���� ����Ʈ ��������
					ArrayList<Sales> searchSalesList = mg.salesForTheDay(theYear, theMonth, theDay);
					
					if(!searchSalesList.isEmpty()) //��� ���� �ʴٸ�
					{
						//���̺��� ��� �� ����
						for(int row=defaultModel.getRowCount()-1; row >=0; row--)
						{
							defaultModel.removeRow(row);
						}
						
						String rowArr[] = new String[4];
	
						// ��¥ ���� ����
						SimpleDateFormat dateFormat = new SimpleDateFormat("YY.MM.dd aa hh:mm:ss");
						String date;
	
						for (Sales sales : searchSalesList) {
							int i = 0;
							date = dateFormat.format(sales.getPaymentDate().getTime());
							rowArr[i] = date; i++;
							rowArr[i] = sales.getPayedUser().getUserName(); i++;
							rowArr[i] = sales.getPayedUser().getUserPhoneNum(); i++;
							rowArr[i] = Integer.toString(sales.getPayedBill());
							
							defaultModel.addRow(rowArr);
						}
						
					}
					else //����ִٸ�
					{
						//���̺��� ��� �� ����
						for(int row=defaultModel.getRowCount()-1; row >=0; row--)
						{
							defaultModel.removeRow(row);
						}
					}
				}
				//���̺��� ��� ���� ���� �ջ�
				int accumulatedSales = 0;
				for(int row=defaultModel.getRowCount()-1; row >=0; row--)
				{
					System.out.println(defaultModel.getValueAt(row, 3));
					int price = Integer.parseInt(defaultModel.getValueAt(row, 3).toString());
					accumulatedSales += price;
				}
				lblNewLabel_9.setText(String.valueOf(accumulatedSales));
			}
		});
		btnNewButton.setFont(new Font("����", Font.BOLD, 15));
		btnNewButton.setBounds(363, 21, 102, 29);
		contentPane.add(btnNewButton);
		
		
		//�⺻ ���� ����
		
		lblNewLabel_3 = new JLabel("[ Sales for yesterday ]");
		lblNewLabel_3.setFont(new Font("����", Font.BOLD, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(405, 86, 167, 29);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("0");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setBounds(415, 118, 146, 29);
		//���� ���� ���
		GregorianCalendar today = new GregorianCalendar();
		
		int dayYear = today.get(Calendar.YEAR);
		int dayMonth = today.get(Calendar.MONTH);
		int dayDate = today.get(Calendar.DATE);
		GregorianCalendar yesterday = new GregorianCalendar(dayYear, dayMonth, dayDate-1);
		String salesForYesterday = Integer.toString(mg.salesForDay(yesterday));
		lblNewLabel_4.setText(salesForYesterday);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("[ Sales for today ]");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("����", Font.BOLD, 14));
		lblNewLabel_5.setBounds(409, 169, 167, 29);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("0");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBackground(Color.WHITE);
		lblNewLabel_6.setBounds(415, 201, 146, 29);
		
		// ���� ���� ���
		today = new GregorianCalendar();
		String salesForToday = Integer.toString(mg.salesForDay(today));
		lblNewLabel_6.setText(salesForToday);
		contentPane.add(lblNewLabel_4);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("[ Sales for this month ]");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("����", Font.BOLD, 14));
		lblNewLabel_7.setBounds(409, 477, 167, 29);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("0");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBackground(Color.WHITE);
		lblNewLabel_8.setBounds(419, 509, 142, 29);
		
		//�̹� �� ���� ���
		today = new GregorianCalendar();
		String salesForThisMonth = Integer.toString(mg.salesForMonth(today));
		lblNewLabel_8.setText(salesForThisMonth);
		contentPane.add(lblNewLabel_8);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(415, 118, 146, 29);
		contentPane.add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(415, 201, 146, 29);
		contentPane.add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBounds(417, 509, 142, 29);
		contentPane.add(panel_2);
		
		setVisible(true);
	}
}
