import java.io.*;

public class Account implements Serializable {
	static final long serialVersionUID = 100;
	String accountNo; // 계좌번호를 문자열로 표시하는 accountNo 변수
	transient String name; // 상품명으로 직렬화 되지 않는 name 변수
	String ownerName; // 예금주명을 문자열로 표시하는 ownerName 변수
	int balance; // 잔액을 정수형으로 표시하는 balance 변수

	// 계좌번호, 소유주명, 잔액을 입력받아 생성하는 생성자
	Account(String accountNo, String name, String ownerName, int balance) {

		this.accountNo = accountNo;
		this.name = name;
		this.ownerName = ownerName;
		this.balance = balance;
	}

	public Account(Account account) {
		// TODO Auto-generated constructor stub
	}

	// amount 만큼의 돈을 예금하는 메소드 : deposit
	void deposit(int amount) {
		balance += amount;
	}

	// amount 만큼의 돈을 인출하는 메소드 : withdraw
	int withdraw(int amount) throws Exception {
		if (balance > amount)
			throw new Exception("잔액이 부족합니다.");
		balance -= amount;
		return amount;
	}
}
