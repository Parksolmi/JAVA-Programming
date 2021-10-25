package studyCafe;

import java.io.*;
import java.util.ArrayList;

public class Management {
	ArrayList<Room> roomTable = new ArrayList<Room>(); //�� ��ü�� ��� ����Ʈ
	int roomTableSize; //roomTable����Ʈ�� ũ��
	Room room = new Room(); //�� ��ü
	
	private String managerID; //������UI�� ���� ���� ������ID
	
	//private int[] totalSales = new int[31]; //����(31��) ������ ������ �����ϴ� �迭
	
	//������
	Management()
	{
		
	}
	Management(String managerID)
	{
		this.managerID = managerID;
		
	}
	
	//getter&setter
	public int getRoomTableSize()
	{
		roomTableSize = roomTable.size();
		return roomTableSize;
	}
	
	// roomTable���� �ش� �� �̸��� ���� ���� index�� ��ȯ�ϴ� �Լ�
	public int searchRoomIndex(String roomName)
	{
		room.setRoomName(roomName);
		return roomTable.indexOf(room);
	}
	// roomTable���� �ش� �� �̸��� ���� ���� �ִ��� �˻��ϴ� �Լ�
	public boolean searchRoom (String roomName)
	{
		room.setRoomName(roomName);
		return roomTable.contains(room);
	}
	
	//manager ���-----------------------------------------------------------------------------------
	
	//managerID�˻��ϴ� �Լ�
	boolean checkManagerID(String managerID)
	{
		if(this.managerID.equals(managerID)) return true;
		else return false;
	}
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
		int roomIndex = searchRoomIndex(roomName);
		roomTable.remove(roomIndex);
	}
	
	//�� ���� ���� ����
	//�� �̸� ����
	public void changeRoomName(String orgRoomName, String changedRoomName) throws Exception
	{
		int orgRoomIndex = searchRoomIndex(orgRoomName);
		roomTable.get(orgRoomIndex).setRoomName(changedRoomName);
	}
	//���� �ο� ����
	public void changeCapacity(String roomName, int changedCapacity) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		roomTable.get(roomIndex).setCapacity(changedCapacity);
	}
	//�ð��� ���� ����
	public void changePricePerHour(String roomName, int changedPricePerHour) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		roomTable.get(roomIndex).setPricePerHour(changedPricePerHour);
	}
	//�ش� �̸��� ���� capacity���� ��ȯ�ϴ� �Լ�
	public int howManyCapacity(String roomName) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		return roomTable.get(roomIndex).getCapacity();
	}
	//�ش� �̸��� ���� ������ ��ȯ�ϴ� �Լ�
	public int howMuchPrice(String roomName) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		return roomTable.get(roomIndex).getPricePerHour();
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
	
	//roomTable���� �ش� �̸��� ���� �� ��ü�� �����ϴ� �Լ�
	public Room getRoom(String roomName) throws Exception
	{
		// ��ã��
		int roomIndex = searchRoomIndex(roomName);
		return roomTable.get(roomIndex);
	}
	// üũ��
	public boolean checkIn(String roomName, User user) throws Exception 
	{
		// ��ã��
		int roomIndex = searchRoomIndex(roomName);
		//üũ���ϱ�
		Room room = roomTable.get(roomIndex);
		if(room.getUsing()) //��� ��(üũ�� �Ұ���)
		{
			return false;
		}
		else //�����(üũ�� ����)
		{
			room.checkIn(user);
			return true;
		}
	}
	// üũ�ƿ�
	public void checkOut(String roomName) throws Exception 
	{
		// ��ã��
		int roomIndex = searchRoomIndex(roomName);
		roomTable.get(roomIndex).checkOut();
	}
	
	// üũ�ƿ� ����� ���� Ȯ��
	public boolean isRightCheckOutUser(String roomName, String userName,
											String userPhoneNum) throws Exception 
	{
		// ��ã��
		int roomIndex = searchRoomIndex(roomName);
		
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
	
	//�����ϱ�
	public int pay(String roomName) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		
		int pricePerHour = roomTable.get(roomIndex).getPricePerHour();
		int userPay = roomTable.get(roomIndex).pay(pricePerHour);
		
		return userPay;
	}
	
	
	//�� ���� ����
	void writeRoomInfo(ObjectOutputStream oos) throws Exception
	{
		oos.writeObject(roomTable);
	}
	//�� ���� �б�
	@SuppressWarnings("unchecked")
	void readRoomInfo(ObjectInputStream ois) throws Exception
	{
		roomTable = (ArrayList<Room>) ois.readObject();
	}
	
	
}
