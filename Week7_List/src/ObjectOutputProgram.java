import java.io.*;
import java.util.*;

public class ObjectOutputProgram {
	public static void main(String args[]) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("output2.txt"));

			// Account ���� ArrayList �� list �� �����ϰ� ��ü�� �����Ͻÿ�.
			ArrayList<Account> list = new ArrayList<>();

			list.add(new Account("333-33-333333", "�Ѹ�������", "��û", 300000));
			list.add(new Account("444-44-444444", "��������", "ȫ�浿", 10000));

			// ����Ʈ ù ��° �׸� �����͸� �߰��մϴ�.
			list.add(0, new Account("111-11-111111", "ù��°����", "����", 200000));

			System.out.println("*****���� �� ������ ��� *****");
			printInfo(list);

			// �����ָ��� ����û���� ����û�̡��� �����ϰ� ������� ���������塱 �����͸� ����Ʈ���� �����Ͻÿ�.
			int size = list.size();
			for (int cnt = 0; cnt < size; cnt++) {
				if (list.get(cnt).ownerName.equals("��û")) {
					list.get(cnt).ownerName = "��û��";
				}
				if (list.get(cnt).name.equals("��������")) {
					list.remove(list.indexOf(list.get(cnt)));
				}
			}

			System.out.println("*****���� �� ������ ��� *****");
			printInfo(list);
			out.writeObject(list);
		} catch (IOException ioe) {
			System.out.println("���Ϸ� ����� �� �����ϴ�.");
		} finally {
			try {
				out.close();
			} catch (Exception e) {
			}
		}
	}

	// ����Ʈ�� ������ ����ϴ� �޼ҵ� printInfo�Դϴ�.
	public static void printInfo(ArrayList<Account> list) {
		int num = list.size();
		for (int cnt = 0; cnt < num; cnt++) {

			// list�κ��� �׸��� ������ Account ���� ������ �Ҵ��Ͻÿ�.
			Account obj = list.get(cnt);

			System.out.println("���¹�ȣ : " + obj.accountNo);
			System.out.println("����� :" + obj.name);
			System.out.println("������ :" + obj.ownerName);
			System.out.println("�ܾ� :" + obj.balance);
		}
	}
}