
public class SharedArea {
	Account account1; // ÀÌ¸ù·æÀÇ °èÁÂ
	Account account2; // ¼ºÃáÇâÀÇ °èÁÂ

	synchronized void transfer(int amount) {
		account1.withdraw(1000000);
		System.out.print("ÀÌ¸ù·æ °èÁÂ: 100¸¸¿ø ÀÎÃâ,");
		account2.deposit(1000000);
		System.out.println("¼ºÃáÇâ °èÁÂ: 100¸¸¿ø ÀÔ±İ");
	}

	synchronized int getTotal() {
		return account1.balance + account2.balance;
	}
}
