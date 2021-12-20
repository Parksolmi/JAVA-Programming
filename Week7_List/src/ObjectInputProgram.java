import java.io.*;
import java.util.*;

public class ObjectInputProgram {
	public static void main(String args[]) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("output2.txt"));
			// 파일로부터 직렬화로 읽어 ArrayList 형에 할당하시오.
			ArrayList<Account> list = (ArrayList<Account>)in.readObject();
			
			int num = list.size();
			System.out.println(num);
			for (int cnt = 0; cnt < num; cnt++) {
				// list로부터 항목을 가져와 Account 형의 변수에 할당하시오
				Account obj = list.get(cnt);
				
				System.out.println("계좌번호 : " + obj.accountNo);
				System.out.println("통장명 : " + obj.name);
				System.out.println("예금주 : " + obj.ownerName);
				System.out.println("잔액 : " + obj.balance);
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("파일이 존재하지 않습니다.");
		} catch (EOFException eofe) {
			System.out.println("파일 끝...");
		} catch (IOException ioe) {
			System.out.println("파일을 읽을 수 없습니다.");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스가 존재하지 않습니다.");
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}
}