package studyCafe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class DeleteRoomFrame extends JFrame {

	private JPanel contentPane;
	private OwnerFrame of; //OwnerFrame ��ü

	public DeleteRoomFrame(Management mg, JTable roomTable) {
		
		//������ Ÿ��Ʋ ����
		this.setTitle("Delete Room");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(600, 400, 600, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		//���̾ƿ� ����(��ġ�� ũ�� ��������)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Do you really want to delete the room?");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 22));
		lblNewLabel.setBounds(87, 42, 428, 119);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Yes");
		btnNewButton.setBackground(new Color(25, 25, 112));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel defaultModel = (DefaultTableModel) roomTable.getModel();
				int row = roomTable.getSelectedRow();
				
				//�� ������ �����
				String roomName = String.valueOf(defaultModel.getValueAt(row, 0));
				mg.removeRoom(roomName);
				
				//�� �����
				defaultModel.removeRow(row);
				
				setVisible(false);
				
				//saveFlag true�� ����
				if(OwnerFrame.getSaveFlag() == false)
				{
					OwnerFrame.setSaveFlagTrue();
				}
			}
		});
		btnNewButton.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton.setBounds(144, 154, 136, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNo = new JButton("No");
		btnNo.setBackground(new Color(25, 25, 112));
		btnNo.setForeground(Color.WHITE);
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNo.setFont(new Font("����", Font.BOLD, 20));
		btnNo.setBounds(314, 154, 136, 40);
		contentPane.add(btnNo);
		
		//���÷���
		setVisible(true);
	}

}
