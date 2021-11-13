package studyCafe;

import java.io.*;
import java.util.ArrayList;

public class Management {
	private ArrayList<Room> roomList = new ArrayList<Room>(); //�� ��ü�� ��� ����Ʈ
	private int roomTableSize; //roomTable����Ʈ�� ũ��
	private String managerID; //������UI�� ���� ���� ������ID;
	private ArrayList<Sales> salesList = new ArrayList<Sales>(); //���� ����Ʈ
	
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
		roomTableSize = roomList.size();
		return roomTableSize;
	}
	public ArrayList<Room> getRoomList()
	{
		return roomList;
	}
	public ArrayList<Sales> getSalesList()
	{
		return salesList;
	}
	
	
	
	// roomTable���� �ش� �� �̸��� ���� ���� index�� ��ȯ�ϴ� �Լ�
	public int searchRoomIndex(String roomName)
	{
		Room room = new Room(roomName);
		return roomList.indexOf(room);
	}
	// roomTable���� �ش� �� �̸��� ���� ���� �ִ��� �˻��ϴ� �Լ�
	public boolean isRoomExist (String roomName)
	{
		Room room = new Room(roomName);
		return roomList.contains(room);
	}
	// roomTable���� �ش� �� �̸��� ���� �� ��ü ��ȯ
	public Room searchRoom (String roomName)
	{
		Room room = new Room(roomName);
		return roomList.get(roomList.indexOf(room));
	}
	
	//manager ���
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
		roomList.add(room); //����Ʈ�� �� ����
	}
	//�� ����
	public boolean removeRoom(String roomName)
	{
		Room room = new Room(roomName);
		return roomList.remove(room);
	}
	
	//�� ���� ���� ����
	//�� �̸� ����
	public void changeRoomName(String orgRoomName, String changedRoomName) throws Exception
	{
		int orgRoomIndex = searchRoomIndex(orgRoomName);
		roomList.get(orgRoomIndex).setRoomName(changedRoomName);
	}
	//���� �ο� ����
	public void changeCapacity(String roomName, int changedCapacity) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		roomList.get(roomIndex).setCapacity(changedCapacity);
	}
	//�ð��� ���� ����
	public void changePricePerHour(String roomName, int changedPricePerHour) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		roomList.get(roomIndex).setPricePerHour(changedPricePerHour);
	}
	//�ش� �̸��� ���� capacity���� ��ȯ�ϴ� �Լ�
	public int howManyCapacity(String roomName) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		return roomList.get(roomIndex).getCapacity();
	}
	//�ش� �̸��� ���� ������ ��ȯ�ϴ� �Լ�
	public int howMuchPrice(String roomName) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		return roomList.get(roomIndex).getPricePerHour();
	}
	
	
	//����
	//���� �α� ����ϱ�
	public void writeSalesInfo(ObjectOutputStream oos) throws Exception
	{
		oos.writeObject(salesList);
	}
	//���� �α� �о����
	@SuppressWarnings("unchecked")
	public void readSalesInfo(ObjectInputStream ois) throws Exception
	{
		salesList = (ArrayList<Sales>) ois.readObject();
	}
	//���� �α� ��¥�� ã��
	
	//�� ���� ���� ���
	
	//�� ���� ���� ���
	
	//���� ����Ʈ�� ���� ���� �߰�
	public void addSalesList(String roomName)
	{
		Room room = new Room(roomName); //�ӽ� ��
		//�ش� �� �̸��� ���� ��ü ã��
		Room usedRoom = roomList.get(roomList.indexOf(room));
		salesList.add(usedRoom.getSalesInfo());
	}
	
	
	//User ���	
	//���� �ο�(capacity)���� �� �� �˻��ϱ�
	public ArrayList<Room> searchEmptyRoomByCapacity(int capacity)
	{
		roomTableSize = roomList.size(); //roomTable�� ũ��
		ArrayList<Room> emptyRoomTable = new ArrayList<Room>(); //roomTable���� �� �游 ���� ����Ʈ
		
		for(int index = 0; index<roomTableSize; index++)
		{
			int roomCapacity = roomList.get(index).getCapacity();
			if(capacity <= roomCapacity && roomList.get(index).getUsing()==false)
			{
				emptyRoomTable.add(roomList.get(index));
			}
		}
		
		return emptyRoomTable;
	}
	
	//roomTable���� �ش� �̸��� ���� �� ��ü�� �����ϴ� �Լ�
	public Room getRoom(String roomName) throws Exception
	{
		// ��ã��
		int roomIndex = searchRoomIndex(roomName);
		return roomList.get(roomIndex);
	}
	// üũ��
	public boolean checkIn(String roomName, User user) throws Exception 
	{
		// ��ã��
		int roomIndex = searchRoomIndex(roomName);
		//üũ���ϱ�
		Room room = roomList.get(roomIndex);
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
		Room room = new Room(roomName);
		roomList.get(roomList.indexOf(room)).checkOut();
	}
	
	// üũ�ƿ� ����� ���� Ȯ��
	public boolean isRightCheckOutUser(String roomName, String userName,
											String userPhoneNum) throws Exception 
	{
		// ��ã��
		int roomIndex = searchRoomIndex(roomName);
		
		// ����� ���� ��
		User user = roomList.get(roomIndex).getUser();
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
		int userPay = roomList.get(roomIndex).pay();
		
		return userPay;
	}
	
	//�� ���� ����
	void writeRoomInfo(ObjectOutputStream oos) throws Exception
	{
		oos.writeObject(roomList);
	}
	//�� ���� �б�
	@SuppressWarnings("unchecked")
	void readRoomInfo(ObjectInputStream ois) throws Exception
	{
		roomList = (ArrayList<Room>) ois.readObject();
	}
	
	
}
