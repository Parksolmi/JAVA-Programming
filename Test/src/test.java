import java.util.ArrayList;

public class test {
	public static void main(String args[])
	{
		Room room = new Room("temp", 0, 0);
		ArrayList<Room> roomTable = new ArrayList<Room>();
		roomTable.add(room);
		
		int index = -1;
		roomTable.get(index);
		System.out.println(roomTable.size());
	}
	

}
