import java.io.*;
import java.util.*;

public class ObjectOutputProgram {
	public static void main(String args[]) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("output2.txt"));

			// Account 형의 ArrayList 인 list 를 선언하고 객체를 생성하시오.
			ArrayList<Account> list = new ArrayList<>();

			list.add(new Account("333-33-333333", "한마음통장", "심청", 300000));
			list.add(new Account("444-44-444444", "부자통장", "홍길동", 10000));

			// 리스트 첫 번째 항목에 데이터를 추가합니다.
			list.add(0, new Account("111-11-111111", "첫번째통장", "콩쥐", 200000));

			System.out.println("*****변경 전 데이터 출력 *****");
			printInfo(list);

			// 예금주명이 “심청”을 “심청이”로 변경하고 통장명이 “부자통장” 데이터를 리스트에서 삭제하시오.
			int size = list.size();
			for (int cnt = 0; cnt < size; cnt++) {
				if (list.get(cnt).ownerName.equals("심청")) {
					list.get(cnt).ownerName = "심청이";
				}
				if (list.get(cnt).name.equals("부자통장")) {
					list.remove(list.indexOf(list.get(cnt)));
				}
			}

			System.out.println("*****변경 후 데이터 출력 *****");
			printInfo(list);
			out.writeObject(list);
		} catch (IOException ioe) {
			System.out.println("파일로 출력할 수 없습니다.");
		} finally {
			try {
				out.close();
			} catch (Exception e) {
			}
		}
	}

	// 리스트의 내용을 출력하는 메소드 printInfo입니다.
	public static void printInfo(ArrayList<Account> list) {
		int num = list.size();
		for (int cnt = 0; cnt < num; cnt++) {

			// list로부터 항목을 가져와 Account 형의 변수에 할당하시오.
			Account obj = list.get(cnt);

			System.out.println("계좌번호 : " + obj.accountNo);
			System.out.println("통장명 :" + obj.name);
			System.out.println("예금주 :" + obj.ownerName);
			System.out.println("잔액 :" + obj.balance);
		}
	}
}