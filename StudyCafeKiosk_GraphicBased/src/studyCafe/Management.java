package studyCafe;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class Management implements Serializable{
	
	private ArrayList<Room> roomList = new ArrayList<Room>(); //�� ��ü�� ��� ����Ʈ
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
	public boolean checkManagerID(String managerID)
	{
		if(this.managerID.equals(managerID)) return true;
		else return false;
	}
	//managerID�����ϴ� �Լ�
	public void reviseManagerID(String newManagerID)
	{
		this.managerID = newManagerID;
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
	//���� �α� ��¥�� ã��
	public ArrayList<Sales> salesForTheDay(int theYear, int theMonth, int theDay)
	{
		ArrayList<Sales> salesListForTheDay = new ArrayList<Sales>();
		
		for(Sales sales : salesList)
		{
			//���� ����Ʈ�� ����� ��ü�� ��¥
			int sYear = sales.getPaymentDate().get(Calendar.YEAR);
			int sMonth = sales.getPaymentDate().get(Calendar.MONTH) + 1;
			int sDate = sales.getPaymentDate().get(Calendar.DATE);
			
			if(theYear==sYear && theMonth==sMonth && theDay==sDate)
			{
				salesListForTheDay.add(sales);
			}
		}
		
		return salesListForTheDay;
	}
	//�� ���� ���� ���
	public int salesForDay(GregorianCalendar day)
	{
		int salesForday = 0;
		
		int dayYear = day.get(Calendar.YEAR);
		int dayMonth = day.get(Calendar.MONTH);
		int dayDate = day.get(Calendar.DATE);
		
		for(Sales sales : salesList)
		{
			int sYear = sales.getPaymentDate().get(Calendar.YEAR);
			int sMonth = sales.getPaymentDate().get(Calendar.MONTH);
			int sDate = sales.getPaymentDate().get(Calendar.DATE);
			
			if(dayYear==sYear && dayMonth==sMonth && dayDate==sDate)
			{
				salesForday += sales.getPayedBill();
			}
		}
		
		return salesForday;
	}

	//�� ���� ���� ���
	public int salesForMonth(GregorianCalendar month)
	{
		int salesForMonth = 0;
		
		int monthYear = month.get(Calendar.YEAR);
		int monthMonth = month.get(Calendar.MONTH);
		
		for(Sales sales : salesList)
		{
			int sYear = sales.getPaymentDate().get(Calendar.YEAR);
			int sMonth = sales.getPaymentDate().get(Calendar.MONTH);
			
			if(monthYear == sYear && monthMonth == sMonth)
			{
				salesForMonth += sales.getPayedBill();
			}
		}
		
		return salesForMonth;
	}
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
	public ArrayList<Room> searchRoomByCapacity(int capacity)
	{
		int roomTableSize = roomList.size(); //roomTable�� ũ��
		ArrayList<Room> emptyRoomTable = new ArrayList<Room>(); //roomTable���� �� �游 ���� ����Ʈ
		
		for(int index = 0; index<roomTableSize; index++)
		{
			int roomCapacity = roomList.get(index).getCapacity();
			if(capacity <= roomCapacity)
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
		return searchRoom(roomName);
	}
	// üũ��
	public boolean checkIn(String roomName, User user) throws Exception 
	{
		//üũ���ϱ�
		Room room = searchRoom(roomName);
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
	
	//����
	//�� ���� ����
	public void writeRoomInfo(ObjectOutputStream oos) throws Exception
	{
		oos.writeObject(roomList);
	}
	//�� ���� �б�
	@SuppressWarnings("unchecked")
	public void readRoomInfo(ObjectInputStream ois) throws Exception
	{
		roomList = (ArrayList<Room>) ois.readObject();
	}
	
	//�Ŵ�����Ʈ ID ���� ����
	public void writeID(DataOutputStream dos) throws Exception
	{
		dos.writeUTF(managerID);
	}
	//�Ŵ�����Ʈ ID ���� �б�
	public void readID(DataInputStream dis) throws Exception
	{
		managerID = dis.readUTF();
	}
	
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
}
