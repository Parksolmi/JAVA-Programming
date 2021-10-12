package studyCafe;

import java.io.*;
import java.util.ArrayList;

public class Management {
	ArrayList<Room> roomTable = new ArrayList<Room>(); //�� ��ü�� ��� ����Ʈ
	int roomTableSize; //roomTable����Ʈ�� ũ��
	private String managerID; //������UI�� ���� ���� ������ID
	
	private int[] totalSales = new int[31]; //����(31��) ������ ������ �����ϴ� �迭
	
	//������
	Management(String managerID)
	{
		this.managerID = managerID;
		
	}
	Management()
	{
		
	}
	//managerID�˻��ϴ� �Լ�
	boolean checkManagerID(String managerID)
	{
		if(this.managerID.equals(managerID)) return true;
		else return false;
	}

	// roomTable���� �ش� �� �̸��� ���� ���� index�� ã�� �Լ� - ����
	public int findRoom(String roomName) throws Exception 
	{
		roomTableSize = roomTable.size();
		int roomIndex = -1;
		String name;
		for(int index = 0; index<roomTableSize; index++)
		{
			name = roomTable.get(index).getRoomName();
			if(name.equals(roomName))
			{
				roomIndex = index;
			}
		}
		
		if (roomIndex == -1) 
		{
			throw new Exception("There is no room named " + roomName + "."); //�޼��������������
		}

		return roomIndex;
	}
	
	//manager ���-----------------------------------------------------------------------------------
	//�� ����
	public void createRoom(String roomName, int capacity, int pricePerHour)
	{
		Room room = new Room(roomName, capacity, pricePerHour);
		room.setUsing(false);
		
		roomTable.add(room); //����Ʈ�� �� ����
	}
	//�� ����
	public void removeRoom(String roomName) throws Exception 
	{
		//�ش� �̸��� ���� ���� �ε��� ã�Ƽ� ����
		int roomIndex = findRoom(roomName);
		roomTable.remove(roomIndex);
		
		//������ ���� ã�� ������ ��
		if(roomIndex == -1)
		{
			throw new Exception(roomName + " doesn't exist!");
		}
	}
	
	
	//�� ���� ���� ����
	//�� �̸� ����
	public void changeRoomName(String orgRoomName, String changedRoomName) throws Exception
	{
		int orgRoomIndex = findRoom(orgRoomName);
		
		roomTable.get(orgRoomIndex).setRoomName(changedRoomName);
	}
	//���� �ο� ����
	public void changeCapacity(String roomName, int changedCapacity) throws Exception
	{
		int roomIndex = findRoom(roomName);
		
		roomTable.get(roomIndex).setCapacity(changedCapacity);
	}
	//�ð��� ���� ����
	public void changePricePerHour(String roomName, int changedPricePerHour) throws Exception
	{
		int roomIndex = findRoom(roomName);
		
		roomTable.get(roomIndex).setPricePerHour(changedPricePerHour);
	}
	
	
	//�ش� �̸��� ���� capacity���� ��ȯ�ϴ� �Լ�
	public int howManyCapacity(String roomName) throws Exception
	{
		int roomIndex = findRoom(roomName);
		
		return roomTable.get(roomIndex).getCapacity();
	}
	//�ش� �̸��� ���� ������ ��ȯ�ϴ� �Լ�
	public int howMuchPrice(String roomName) throws Exception
	{
		int roomIndex = findRoom(roomName);
		
		return roomTable.get(roomIndex).getPricePerHour();
	}
	
	//���� : ��¹� - UI
	//toString �������̵�
	public String toString(Room room)
	{
		return room.getRoomName() + "| \t  " + room.getCapacity() + "\t\t"
				+ room.getPricePerHour() + "\t " + room.getUsing();
	}
	//������ ��ü �� ��ȸ
	public void checkAllCreatedRoom() //���� ����
	{
		roomTableSize = roomTable.size();
		for(int index = 0; index<roomTableSize; index++)
		{
			System.out.println(toString(roomTable.get(index)));
		}
	}

	//User ���---------------------------------------------------------------------------------------
	
	//���� �ο�(capacity)���� �� �� �˻��ϱ�
	public void searchEmptyRoomByCapacity(int capacity) throws Exception //���� : Arraylist/Array ��ȯ
	{
		roomTableSize = roomTable.size(); //roomTable�� ũ��
		
		ArrayList<Room> emptyRoomTable = new ArrayList<Room>(); //roomTable���� �� �游 ���� ����Ʈ
		int emptyRoomTableSize = emptyRoomTable.size(); //�� �� ��
		System.out.println();
		
		for(int index = 0; index<roomTableSize; index++)
		{
			int roomCapacity = roomTable.get(index).getCapacity();
			if(capacity <= roomCapacity && roomTable.get(index).getUsing()==false)
			{
				emptyRoomTable.add(roomTable.get(index));
			}
		}
		
		emptyRoomTableSize = emptyRoomTable.size();
		if(emptyRoomTableSize == 0)
		{
			throw new Exception("There are no vacancies for the number of people.");
		}
		
		//���� �ο�(Capacity)���� ã�� �� �� ����ϱ�
		System.out.println("���̸�\t �ο�\t ����");
		System.out.println("---------------------------------");
		for(int index = 0; index<emptyRoomTableSize; index++)
		{
			System.out.println(toString(emptyRoomTable.get(index)));
		}
	}
	
	
	// üũ��
	public void checkIn(String roomName, User user) throws Exception 
	{
		// ��ã��
		int roomIndex = findRoom(roomName);
		//üũ���ϱ�
		String showCheckInTime = roomTable.get(roomIndex).checkIn(user);
		System.out.println(showCheckInTime); //�Խǽð� ����ڿ��� �����ֱ� - ���� : �ð� ��ü�� �Ѱ��ֱ�
	}

	// üũ�ƿ� ����� ���� Ȯ��
	public boolean isRightCheckOutUser(String roomName, String userName, String userPhoneNum) throws Exception 
	{
		// ��ã��
		int roomIndex = findRoom(roomName);
		
		// ����� ���� ��
		User user = roomTable.get(roomIndex).getUser();
		if (user.getUserName().equals(userName) 
				&& user.getUserPhoneNum().equals(userPhoneNum)) 
		{
			return true; // ����� ���� ��ġ
		}
		
		return false; // ����� ���� ����ġ
	}

	// üũ�ƿ�
	public void checkOut(String roomName) throws Exception 
	{
		// ��ã��
		int roomIndex = findRoom(roomName);
		roomTable.get(roomIndex).checkOut();
	}
	
	//üũ�ƿ��ð� �����ֱ�
	public void showCheckOutTime(String roomName) throws Exception
	{
		// ��ã��
		int roomIndex = findRoom(roomName); //���� - ���� �� ã���� �� : ���α׷� �����ǵ���(try-catch)

		String showCheckOutTime = roomTable.get(roomIndex).getCheckOutTime();
		System.out.println(showCheckOutTime); //��ǽð� ����ڿ��� ������
	}
	
	public int pay(String roomName) throws Exception
	{
		int roomIndex = findRoom(roomName);
		
		int pricePerHour = roomTable.get(roomIndex).getPricePerHour();
		int userPay = roomTable.get(roomIndex).pay(pricePerHour);
		
		return userPay;
	}
	
	//�� ���� �Է� - RoomŬ�������� �޾ƿͼ� ����
	void writeRoomInfo(FileOutputStream fos) throws Exception
	{
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.writeInt(roomTableSize);
		for(Room room:roomTable)
		{
			room.writeRoomInfo(fos);
		}
		
	}
	
	//�� ���� �о ������ �����ϱ�
	void readRoomInfo(FileInputStream fis) throws Exception
	{
		DataInputStream dis = new DataInputStream(fis);
		
		roomTableSize = dis.readInt();
		System.out.println("Currently, " + roomTableSize + " rooms are created."); //����
		System.out.println();
		
		for(int index=0; index<roomTableSize; index++)
		{
			Room room = new Room("temp", 0, 0, "empty", "empty");
			roomTable.add(room);
			roomTable.get(index).readRoomInfo(fis);
		}
	}
	
}
