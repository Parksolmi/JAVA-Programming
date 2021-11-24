package studyCafe;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class Management implements Serializable{
	
	private ArrayList<Room> roomList = new ArrayList<Room>(); //방 객체를 담는 리스트
	private String managerID; //관리자UI로 들어가기 위한 관리자ID;
	private ArrayList<Sales> salesList = new ArrayList<Sales>(); //매출 리스트
	
	//생성자
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
	
	
	// roomTable에서 해당 룸 이름을 가진 방의 index를 반환하는 함수
	public int searchRoomIndex(String roomName)
	{
		Room room = new Room(roomName);
		return roomList.indexOf(room);
	}
	
	// roomTable에서 해당 룸 이름을 가진 방이 있는지 검사하는 함수
	public boolean isRoomExist (String roomName)
	{
		Room room = new Room(roomName);
		return roomList.contains(room);
	}
	// roomTable에서 해당 룸 이름을 가진 룸 객체 반환
	public Room searchRoom (String roomName)
	{
		Room room = new Room(roomName);
		return roomList.get(roomList.indexOf(room));
	}
	
	//manager 기능
	//managerID검사하는 함수
	public boolean checkManagerID(String managerID)
	{
		if(this.managerID.equals(managerID)) return true;
		else return false;
	}
	//managerID변경하는 함수
	public void reviseManagerID(String newManagerID)
	{
		this.managerID = newManagerID;
	}
	//룸 생성
	public void createRoom(String roomName, int capacity, int pricePerHour)
	{
		Room room = new Room(roomName, capacity, pricePerHour);
		roomList.add(room); //리스트에 룸 저장
	}
	//룸 삭제
	public boolean removeRoom(String roomName)
	{
		Room room = new Room(roomName);
		return roomList.remove(room);
	}
	
	//룸 관련 정보 수정
	//방 이름 수정
	public void changeRoomName(String orgRoomName, String changedRoomName) throws Exception
	{
		int orgRoomIndex = searchRoomIndex(orgRoomName);
		roomList.get(orgRoomIndex).setRoomName(changedRoomName);
	}
	//수용 인원 수정
	public void changeCapacity(String roomName, int changedCapacity) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		roomList.get(roomIndex).setCapacity(changedCapacity);
	}
	//시간당 가격 수정
	public void changePricePerHour(String roomName, int changedPricePerHour) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		roomList.get(roomIndex).setPricePerHour(changedPricePerHour);
	}
	//해당 이름의 방의 capacity값을 반환하는 함수
	public int howManyCapacity(String roomName) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		return roomList.get(roomIndex).getCapacity();
	}
	//해당 이름의 방의 가격을 반환하는 함수
	public int howMuchPrice(String roomName) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		return roomList.get(roomIndex).getPricePerHour();
	}
	
	//매출
	//결제 로그 날짜로 찾기
	public ArrayList<Sales> salesForTheDay(int theYear, int theMonth, int theDay)
	{
		ArrayList<Sales> salesListForTheDay = new ArrayList<Sales>();
		
		for(Sales sales : salesList)
		{
			//매출 리스트에 저장된 객체의 날짜
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
	//일 누적 매출 계산
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

	//월 누적 매출 계산
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
	//매출 리스트에 매출 정보 추가
	public void addSalesList(String roomName)
	{
		Room room = new Room(roomName); //임시 방
		//해당 방 이름을 가진 객체 찾기
		Room usedRoom = roomList.get(roomList.indexOf(room));
		salesList.add(usedRoom.getSalesInfo());
	}
	
	
	//User 기능	
	//수용 인원(capacity)으로 빈 방 검색하기
	public ArrayList<Room> searchRoomByCapacity(int capacity)
	{
		int roomTableSize = roomList.size(); //roomTable의 크기
		ArrayList<Room> emptyRoomTable = new ArrayList<Room>(); //roomTable에서 빈 방만 담은 리스트
		
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
	
	//roomTable에서 해당 이름을 가진 방 객체를 리턴하는 함수
	public Room getRoom(String roomName) throws Exception
	{
		// 방찾기
		return searchRoom(roomName);
	}
	// 체크인
	public boolean checkIn(String roomName, User user) throws Exception 
	{
		//체크인하기
		Room room = searchRoom(roomName);
		if(room.getUsing()) //사용 중(체크인 불가능)
		{
			return false;
		}
		else //비었음(체크인 가능)
		{
			room.checkIn(user);
			return true;
		}
	}
	// 체크아웃
	public void checkOut(String roomName) throws Exception 
	{
		Room room = new Room(roomName);
		roomList.get(roomList.indexOf(room)).checkOut();
	}
	
	// 체크아웃 사용자 정보 확인
	public boolean isRightCheckOutUser(String roomName, String userName,
											String userPhoneNum) throws Exception 
	{
		// 방찾기
		int roomIndex = searchRoomIndex(roomName);
		
		// 사용자 정보 비교
		User user = roomList.get(roomIndex).getUser();
		if (user.getUserName().equals(userName)
				&& user.getUserPhoneNum().equals(userPhoneNum)) 
		{
			return true; // 사용자 정보 일치
		}
		else
		{
			return false; // 사용자 정보 불일치
		}
	}
	
	//결제하기
	public int pay(String roomName) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		int userPay = roomList.get(roomIndex).pay();
		
		return userPay;
	}
	
	//파일
	//방 정보 쓰기
	public void writeRoomInfo(ObjectOutputStream oos) throws Exception
	{
		oos.writeObject(roomList);
	}
	//방 정보 읽기
	@SuppressWarnings("unchecked")
	public void readRoomInfo(ObjectInputStream ois) throws Exception
	{
		roomList = (ArrayList<Room>) ois.readObject();
	}
	
	//매니지먼트 ID 정보 쓰기
	public void writeID(DataOutputStream dos) throws Exception
	{
		dos.writeUTF(managerID);
	}
	//매니지먼트 ID 정보 읽기
	public void readID(DataInputStream dis) throws Exception
	{
		managerID = dis.readUTF();
	}
	
	//매출 로그 기록하기
	public void writeSalesInfo(ObjectOutputStream oos) throws Exception 
	{
		oos.writeObject(salesList);
	}

	//매출 로그 읽어오기
	@SuppressWarnings("unchecked")
	public void readSalesInfo(ObjectInputStream ois) throws Exception 
	{
		salesList = (ArrayList<Sales>) ois.readObject();
	}
}
