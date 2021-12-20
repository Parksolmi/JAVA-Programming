import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		try {
			MainFrame frame = new MainFrame();
			frame.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 550);
		setTitle("Option Window");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//채팅방 이름
		JLabel lblNewLabel = new JLabel("\uC465\uB355\uCF69\uB5A1 \uCC44\uD305\uBC29");
		lblNewLabel.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(139, 94, 442, 70);
		contentPane.add(lblNewLabel);
		
		//Server 버튼
		JButton btnNewButton = new JButton("Server");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServerFrame sf = new ServerFrame();
				sf.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton.setBounds(190, 228, 340, 52);
		contentPane.add(btnNewButton);
		
		//Client 버튼
		JButton btnClient = new JButton("Client");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientFrame cf = new ClientFrame();
				cf.setVisible(true);
			}
		});
		btnClient.setFont(new Font("Arial", Font.BOLD, 15));
		btnClient.setBounds(190, 307, 340, 52);
		contentPane.add(btnClient);
		
		
		this.setVisible(true);
	}
}
