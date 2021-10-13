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
	
	// roomTable���� �ش� �� �̸��� ���� ���� index�� ��ȯ�ϴ� �Լ�
	public int searchRoom(String roomName)
	{
		roomTableSize = roomTable.size();
		for(int index=0; index<roomTableSize; index++)
		{
			if(roomTable.get(index).getRoomName().equals(roomName))
			{
				return index;
			}
		}
		
		return -1; //�ش� �̸��� ���� ���� ���� ��
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
		int roomIndex = searchRoom(roomName);
		roomTable.remove(roomIndex);
	}
	
	//�� ���� ���� ����
	//�� �̸� ����
	public void changeRoomName(String orgRoomName, String changedRoomName) throws Exception
	{
		int orgRoomIndex = searchRoom(orgRoomName);
		roomTable.get(orgRoomIndex).setRoomName(changedRoomName);
	}
	//���� �ο� ����
	public void changeCapacity(String roomName, int changedCapacity) throws Exception
	{
		int roomIndex = searchRoom(roomName);
		roomTable.get(roomIndex).setCapacity(changedCapacity);
	}
	//�ð��� ���� ����
	public void changePricePerHour(String roomName, int changedPricePerHour) throws Exception
	{
		int roomIndex = searchRoom(roomName);
		roomTable.get(roomIndex).setPricePerHour(changedPricePerHour);
	}
	//�ش� �̸��� ���� capacity���� ��ȯ�ϴ� �Լ�
	public int howManyCapacity(String roomName) throws Exception
	{
		int roomIndex = searchRoom(roomName);
		return roomTable.get(roomIndex).getCapacity();
	}
	//�ش� �̸��� ���� ������ ��ȯ�ϴ� �Լ�
	public int howMuchPrice(String roomName) throws Exception
	{
		int roomIndex = searchRoom(roomName);
		return roomTable.get(roomIndex).getPricePerHour();
	}
	
	
	//toString �������̵�
	public String toString(Room room)
	{
		return room.getRoomName() + "| \t  " + room.getCapacity() + "\t\t"
				+ room.getPricePerHour() + "\t " + room.getUsing();
	}
	//������ ��ü �� ��ȸ
	public ArrayList<Room> checkAllCreatedRoom()
	{
		return roomTable;
	}

	//User ���---------------------------------------------------------------------------------------
	
	//���� �ο�(capacity)���� �� �� �˻��ϱ�
	public ArrayList<Room> searchEmptyRoomByCapacity(int capacity)
	{
		roomTableSize = roomTable.size(); //roomTable�� ũ��
		ArrayList<Room> emptyRoomTable = new ArrayList<Room>(); //roomTable���� �� �游 ���� ����Ʈ
		
		for(int index = 0; index<roomTableSize; index++)
		{
			int roomCapacity = roomTable.get(index).getCapacity();
			if(capacity <= roomCapacity && roomTable.get(index).getUsing()==false)
			{
				emptyRoomTable.add(roomTable.get(index));
			}
		}
		
		return emptyRoomTable;
	}
	
	
	// üũ��
	public String checkIn(String roomName, User user) throws Exception 
	{
		// ��ã��
		int roomIndex = searchRoom(roomName);
		//üũ���ϱ�
		String showCheckInTime = roomTable.get(roomIndex).checkIn(user);
		return showCheckInTime; //����ڿ��� �Խ� �ð� �����ֱ�
	}

	// üũ�ƿ� ����� ���� Ȯ��
	public boolean isRightCheckOutUser(String roomName, String userName,
										String userPhoneNum) throws Exception 
	{
		// ��ã��
		int roomIndex = searchRoom(roomName);
		
		// ����� ���� ��
		User user = roomTable.get(roomIndex).getUser();
		if (user.getUserName().equals(userName) 
				&& user.getUserPhoneNum().equals(userPhoneNum)) 
		{
			return true; // ����� ���� ��ġ
		}
		else
		{
			return false; // ����� ���� ����ġ
		}
	}

	// üũ�ƿ�
	public void checkOut(String roomName) throws Exception 
	{
		// ��ã��
		int roomIndex = searchRoom(roomName);
		roomTable.get(roomIndex).checkOut();
	}
	//üũ�ƿ��ð� �����ֱ�
	public String showCheckOutTime(String roomName) throws Exception
	{
		// ��ã��
		int roomIndex = searchRoom(roomName);

		String showCheckOutTime = roomTable.get(roomIndex).getCheckOutTime();
		return showCheckOutTime; //��ǽð� ����ڿ��� ������
	}
	
	//�����ϱ�
	public int pay(String roomName) throws Exception
	{
		int roomIndex = searchRoom(roomName);
		
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
			Room room = new Room();
			roomTable.add(room);
			roomTable.get(index).readRoomInfo(fis);
		}
	}
	
}
