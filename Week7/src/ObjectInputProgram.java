import java.io.*;
import java.util.*;

public class ObjectInputProgram {
	public static void main(String args[]) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("output2.txt"));
			// ���Ϸκ��� ����ȭ�� �о� ArrayList ���� �Ҵ��Ͻÿ�.
			ArrayList<Account> list = (ArrayList<Account>)in.readObject();
			
			int num = list.size();
			System.out.println(num);
			for (int cnt = 0; cnt < num; cnt++) {
				// list�κ��� �׸��� ������ Account ���� ������ �Ҵ��Ͻÿ�
				Account obj = list.get(cnt);
				
				System.out.println("���¹�ȣ : " + obj.accountNo);
				System.out.println("����� : " + obj.name);
				System.out.println("������ : " + obj.ownerName);
				System.out.println("�ܾ� : " + obj.balance);
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("������ �������� �ʽ��ϴ�.");
		} catch (EOFException eofe) {
			System.out.println("���� ��...");
		} catch (IOException ioe) {
			System.out.println("������ ���� �� �����ϴ�.");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("�ش� Ŭ������ �������� �ʽ��ϴ�.");
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}
}