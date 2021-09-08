package studyCafe;

import java.io.*;

public class Management {
	Room[] roomTable = new Room[100]; //�� ��ü�� ��� �迭
	int count = 0; //���� �迭�� ������ �� �ִ��� ��Ÿ���� ����
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
	
	
	// �ش� �� �̸��� ���� ���� roomTable�迭���� ã�� �Լ�
	public Room findRoom(String roomName) throws Exception 
	{
		Room room = null;

		for (int i = 0; i < count; i++) 
		{
			if (roomTable[i].getRoomName().equals(roomName)) 
			{
				room = roomTable[i];
				break;
			}
		}

		if (room == null) 
		{
			throw new Exception(roomName + "�̶�� ���� ��밡������ �ʽ��ϴ�.");
		}

		return room;
	}
	
	//manager ���-----------------------------------------------------------------------------------
	//�� ����
	public void createRoom(String roomName, int capacity, int pricePerHour)
	{
		roomTable[count] = new Room(roomName, capacity, pricePerHour);
		roomTable[count].setUsing(false);
		count++;
	}
	//�� ����
	public Room removeRoom(String roomName) throws Exception 
	{
		boolean findRoomToRemove = false;
		for(int i=0; i<count; i++)
		{
			Room room = roomTable[i];
			if(roomName.equals(room.getRoomName()))
			{
				findRoomToRemove = true;
				for(int j=i; j<count; j++)
				{
					roomTable[i] = roomTable[i+1];
				}
			}
			return room;
		}
		
		//������ ���� ã�� ������ ��
		if(!findRoomToRemove)
		{
			throw new Exception(roomName + "�̶�� ���� �������� �ʽ��ϴ�.");
		}
		return null;
	}
	//�� ���� ���� ����
	//�� �̸� ����
	public void changeRoomName(String orgRoomName, String changedRoomName)
	{
		for(int i=0; i<count; i++)
		{
			if(roomTable[i].getRoomName().equals(orgRoomName))
			{
				roomTable[i].setRoomName(changedRoomName);
			}
		}
	}
	//���� �ο� ����
	public void changeCapacity(String roomName, int changedCapacity)
	{
		for(int i=0; i<count; i++)
		{
			if(roomTable[i].getRoomName().equals(roomName))
			{
				roomTable[i].setCapacity(changedCapacity);
			}
		}
	}
	//�ð��� ���� ����
	public void changePricePerHour(String roomName, int changedPricePerHour)
	{
		for(int i=0; i<count; i++)
		{
			if(roomTable[i].getRoomName().equals(roomName))
			{
				roomTable[i].setPricePerHour(changedPricePerHour);
			}
		}
	}
	//�ش� �̸��� ���� capacity���� ��ȯ�ϴ� �Լ�
	public int howManyCapacity(String roomName)
	{
		for(int i=0; i<count; i++)
		{
			if(roomTable[i].getRoomName().equals(roomName))
			{
				return roomTable[i].getCapacity();
			}
		}
		return 0;
	}
	//�ش� �̸��� ���� ������ ��ȯ�ϴ� �Լ�
	public int howMuchPrice(String roomName)
	{
		for(int i=0; i<count; i++)
		{
			if(roomTable[i].getRoomName().equals(roomName))
			{
				return roomTable[i].getPricePerHour();
			}
		}
		return 0;
	}
	//toString �������̵�
	public String toString(Room room)
	{
		return room.getRoomName() + "| \t  " + room.getCapacity() + "\t"
				+ room.getPricePerHour() + "\t " + room.getUsing();
	}
	//������ ��ü �� ��ȸ
	public void checkAllCreatedRoom() throws Exception
	{
		for(int i=0; i<count; i++)
		{
			if(roomTable[i] == null)
			{
				throw new Exception("������ ���� �����ϴ�.");
			}
			else
				System.out.println(toString(roomTable[i]));
		}
	}

	//User ���---------------------------------------------------------------------------------------
	
	//���� �ο�(capacity)���� �� �� �˻��ϱ�(User UI���� ���)
	public Room[] searchCapacityEmptyRoom(int capacity) throws Exception
	{
		Room emptyRoom[] = new Room[100];
		int emptyRoomIndex = 0;
		for(int i=0; i<count; i++)
		{
			Room room = roomTable[i];
			if(room.getUsing()) continue;
			else 
			{
				if(room.getCapacity()>=capacity)
				{
				emptyRoom[emptyRoomIndex] = room;
				emptyRoomIndex++;
				}
			}
		}
		if(emptyRoom[0] == null)
		{
			throw new Exception("��� ������ �� ���� �����ϴ�.");
		}
		return emptyRoom;
	}
	
	// üũ��
	public void checkIn(String roomName, User user) throws Exception 
	{
		// ��ã��
		Room room = findRoom(roomName);
		
		if(room == null) //���� ��ã�Ƽ� ���� ����� ��
		{
			throw new Exception(roomName +"not exist!");
		}
		String showCheckInTime = room.checkIn(user);
		System.out.println(showCheckInTime); //�Խǽð� ����ڿ��� ������
	}

	// üũ�ƿ� ����� ���� Ȯ��
	public boolean isRightCheckOutUser(String roomName, String userName, String userPhoneNum) throws Exception 
	{
		// ��ã��
		Room room = findRoom(roomName);
		// ����� ���� ��
		User user = room.getUser();
		String roomUserName = user.getUserName();
		String roomUserphoneNum = user.getUserPhoneNum();

		if (roomUserName.equals(userName)) 
		{
			if (roomUserphoneNum.equals(userPhoneNum))
				return true; // ����� ���� ��ġ
		}
		return false; // ����� ���� ����ġ

	}

	// üũ�ƿ�
	public void checkOut(String roomName) throws Exception {
		// ��ã��
		Room room = findRoom(roomName);
		
		if(room == null) //���� ��ã�Ƽ� ���� ����� ��
		{
			throw new Exception(roomName +"not exist!");
		}
		room.checkOut();
	}
	//üũ�ƿ��ð� �����ֱ�
	public void showCheckOutTime(String roomName) throws Exception
	{
		// ��ã��
		Room room = findRoom(roomName);

		if (room == null) // ���� ��ã�Ƽ� ���� ����� ��
		{
			throw new Exception(roomName + "not exist!");
		}
		String showCheckOutTime = room.checkOutTime();
		System.out.println(showCheckOutTime); //��ǽð� ����ڿ��� ������
	}
	
	public int pay(String roomName) throws Exception
	{
		Room room = findRoom(roomName);
		int pricePerHour = room.getPricePerHour();
		int userPay = room.pay(pricePerHour);
		
		return userPay;
	}
	
	void writeRoomInfo(DataOutputStream do)
	{
		do.writeInt(count);
		for(Room room:roomTable)
		{
			room.writeRoomInfo(do);
		}
	}
	
}
