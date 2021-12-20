import java.util.ArrayList;

public class ArrayListTest2 {
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("말");
		list.add("사자");
		list.add("토끼");
		list.add("뱀");
		
		System.out.println("입력한 동물을 출력합니다.");
		printInfo(list);
		
		System.out.println("사자를 호랑이로 변경합니다.");
		int indexToChange = list.indexOf("사자");
		list.set(indexToChange, "호랑이");
		printInfo(list);
		
		System.out.println("3번째 항목에 원숭이를 첨가합니다.");
		list.add(2, "원숭이");
		printInfo(list);
		
		System.out.println("뱀을 검색하여 삭제합니다.");
		int indexToRemove = list.indexOf("뱀");
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
