import java.util.ArrayList;

public class ArrayListTest {
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("¸»");
		list.add("»çÀÚ");
		list.add("Åä³¢");
		list.add("¹ì");
		
		int num = list.size();
		for(int cnt=0; cnt<num; cnt++)
		{
			String str = list.get(cnt);
			System.out.println(str);
		}
	}

}
