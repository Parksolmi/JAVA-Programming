import java.io.*;

public class Account implements Serializable {
	static final long serialVersionUID = 100;
	String accountNo; // ���¹�ȣ�� ���ڿ��� ǥ���ϴ� accountNo ����
	transient String name; // ��ǰ������ ����ȭ ���� �ʴ� name ����
	String ownerName; // �����ָ��� ���ڿ��� ǥ���ϴ� ownerName ����
	int balance; // �ܾ��� ���������� ǥ���ϴ� balance ����

	// ���¹�ȣ, �����ָ�, �ܾ��� �Է¹޾� �����ϴ� ������
	Account(String accountNo, String name, String ownerName, int balance) {

		this.accountNo = accountNo;
		this.name = name;
		this.ownerName = ownerName;
		this.balance = balance;
	}

	public Account(Account account) {
		// TODO Auto-generated constructor stub
	}

	// amount ��ŭ�� ���� �����ϴ� �޼ҵ� : deposit
	void deposit(int amount) {
		balance += amount;
	}

	// amount ��ŭ�� ���� �����ϴ� �޼ҵ� : withdraw
	int withdraw(int amount) throws Exception {
		if (balance > amount)
			throw new Exception("�ܾ��� �����մϴ�.");
		balance -= amount;
		return amount;
	}
}
