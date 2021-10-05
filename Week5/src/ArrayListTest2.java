import java.util.ArrayList;

public class ArrayListTest2 {
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("��");
		list.add("����");
		list.add("�䳢");
		list.add("��");
		
		System.out.println("�Է��� ������ ����մϴ�.");
		printInfo(list);
		
		System.out.println("���ڸ� ȣ���̷� �����մϴ�.");
		int indexToChange = list.indexOf("����");
		list.set(indexToChange, "ȣ����");
		printInfo(list);
		
		System.out.println("3��° �׸� �����̸� ÷���մϴ�.");
		list.add(2, "������");
		printInfo(list);
		
		System.out.println("���� �˻��Ͽ� �����մϴ�.");
		int indexToRemove = list.indexOf("��");
		list.remove(indexToRemove);
		printInfo(list);
		
	}

	public static void printInfo(ArrayList<String> list)
	{
		int num = list.size();
		for(int cnt=0; cnt<num; cnt++)
		{
			String str = list.get(cnt);
			System.out.println(str);
		}
	}
}
