package test;

import java.util.ArrayList;

public class test1 {
	public static void main(String args[])
	{
		ArrayList<Room> roomTable = new ArrayList<Room>();
		
		Room room = new Room("1", 1, 1);
		roomTable.add(room);
		room = new Room("2", 2, 2);
		roomTable.add(room);
		room = new Room("3", 3, 3);
		roomTable.add(room);
		
		Room room2 = new Room("2");
		System.out.println(roomTable.size());
		
		System.out.println(roomTable.contains(room2));
	}

}
