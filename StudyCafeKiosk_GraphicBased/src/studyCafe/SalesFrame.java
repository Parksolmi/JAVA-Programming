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
import javax.swing.table.DefaultTableModel;

public class SalesFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTextField textField_3;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;

	public SalesFrame(Management mg) throws Exception {
		setTitle("Sales");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(600, 200, 600, 600);
		contentPane = new JPanel();
		//레이아웃 설정(위치와 크기 자유지정)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Year");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel.setBounds(23, 21, 46, 29);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(62, 21, 68, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Month");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_1.setBounds(141, 21, 55, 29);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(192, 21, 55, 29);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("Day");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_2.setBounds(257, 21, 46, 29);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(290, 21, 55, 29);
		contentPane.add(textField_2);
		
		btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton.setBounds(363, 21, 102, 29);
		contentPane.add(btnNewButton);
		
		textField_3 = new JTextField();
		textField_3.setBounds(23, 508, 373, 36);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		lblNewLabel_3 = new JLabel("[ Sales for yesterday ]");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(405, 86, 167, 29);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("0");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setBounds(415, 118, 146, 29);
		//어제 매출 계산
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("[ Sales for today ]");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_5.setBounds(409, 169, 167, 29);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("0");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBackground(Color.WHITE);
		lblNewLabel_6.setBounds(415, 201, 146, 29);
		
		// 오늘 매출 계산
		GregorianCalendar today = new GregorianCalendar();
		String salesForToday = Integer.toString(mg.salesForDay(today));
		lblNewLabel_6.setText(salesForToday);
		contentPane.add(lblNewLabel_4);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("[ Sales for this month ]");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_7.setBounds(409, 483, 167, 29);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBackground(Color.WHITE);
		lblNewLabel_8.setBounds(419, 515, 142, 29);
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
		panel_2.setBounds(419, 515, 142, 29);
		contentPane.add(panel_2);
		
		//중앙 Table
		String colNames[] = { "Date", "Name", "Phone number", "price"}; // 타이틀바
		DefaultTableModel defaultModel = new DefaultTableModel(colNames, 0);
		JTable roomTable = new JTable(defaultModel);
		
		scrollPane = new JScrollPane(roomTable);
		scrollPane.setBounds(23, 73, 373, 434);
		contentPane.add(scrollPane);
		
		//table에 내용 넣기
		//매출 정보 테이블에 넣기
		if (!mg.getSalesList().isEmpty()) // 비어 있지 않다면
		{
			String rowArr[] = new String[4];
			
			//날짜 정보 포맷
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
		
		setVisible(true);
	}

}
