package studyCafe;

import java.io.*;
import java.util.ArrayList;

public class Management {
	ArrayList<Room> roomTable = new ArrayList<Room>(); //방 객체를 담는 리스트
	int roomTableSize; //roomTable리스트의 크기
	Room room = new Room(); //룸 객체
	
	private String managerID; //관리자UI로 들어가기 위한 관리자ID
	
	//private int[] totalSales = new int[31]; //매일(31일) 각각의 매출을 저장하는 배열
	
	//생성자
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
	
	// roomTable에서 해당 룸 이름을 가진 방의 index를 반환하는 함수
	public int searchRoomIndex(String roomName)
	{
		room.setRoomName(roomName);
		return roomTable.indexOf(room);
	}
	// roomTable에서 해당 룸 이름을 가진 방이 있는지 검사하는 함수
	public boolean searchRoom (String roomName)
	{
		room.setRoomName(roomName);
		return roomTable.contains(room);
	}
	
	//manager 기능-----------------------------------------------------------------------------------
	
	//managerID검사하는 함수
	boolean checkManagerID(String managerID)
	{
		if(this.managerID.equals(managerID)) return true;
		else return false;
	}
	//룸 생성
	public void createRoom(String roomName, int capacity, int pricePerHour)
	{
		Room room = new Room(roomName, capacity, pricePerHour);
		room.setUsing(false);
		
		roomTable.add(room); //리스트에 룸 저장
	}
	//룸 삭제
	public void removeRoom(String roomName) throws Exception
	{
		//해당 이름을 가진 방의 인덱스 찾아서 삭제
		int roomIndex = searchRoomIndex(roomName);
		roomTable.remove(roomIndex);
	}
	
	//룸 관련 정보 수정
	//방 이름 수정
	public void changeRoomName(String orgRoomName, String changedRoomName) throws Exception
	{
		int orgRoomIndex = searchRoomIndex(orgRoomName);
		roomTable.get(orgRoomIndex).setRoomName(changedRoomName);
	}
	//수용 인원 수정
	public void changeCapacity(String roomName, int changedCapacity) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		roomTable.get(roomIndex).setCapacity(changedCapacity);
	}
	//시간당 가격 수정
	public void changePricePerHour(String roomName, int changedPricePerHour) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		roomTable.get(roomIndex).setPricePerHour(changedPricePerHour);
	}
	//해당 이름의 방의 capacity값을 반환하는 함수
	public int howManyCapacity(String roomName) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		return roomTable.get(roomIndex).getCapacity();
	}
	//해당 이름의 방의 가격을 반환하는 함수
	public int howMuchPrice(String roomName) throws Exception
	{
		int roomIndex = searchRoomIndex(roomName);
		return roomTable.get(roomIndex).getPricePerHour();
	}
	
	//생성된 전체 방 조회
	public ArrayList<Room> checkAllCreatedRoom()
	{
		return roomTable;
	}

	//User 기능---------------------------------------------------------------------------------------
	
	//수용 인원(capacity)으로 빈 방 검색하기
	public ArrayList<Room> searchEmptyRoomByCapacity(int capacity)
	{
		roomTableSize = roomTable.size(); //roomTable의 크기
		ArrayList<Room> emptyRoomTable = new ArrayList<Room>(); //roomTable에서 빈 방만 담은 리스트
		
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
	
	//roomTable에서 해당 이름을 가진 방 객체를 리턴하는 함수
	public Room getRoom(String roomName) throws Exception
	{
		// 방찾기
		int roomIndex = searchRoomIndex(roomName);
		return roomTable.get(roomIndex);
	}
	// 체크인
	public boolean checkIn(String roomName, User user) throws Exception 
	{
		// 방찾기
		int roomIndex = searchRoomIndex(roomName);
		//체크인하기
		Room room = roomTable.get(roomIndex);
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
		// 방찾기
		int roomIndex = searchRoomIndex(roomName);
		roomTable.get(roomIndex).checkOut();
	}
	
	// 체크아웃 사용자 정보 확인
	public boolean isRightCheckOutUser(String roomName, String userName,
											String userPhoneNum) throws Exception 
	{
		// 방찾기
		int roomIndex = searchRoomIndex(roomName);
		
		// 사용자 정보 비교
		User user = roomTable.get(roomIndex).getUser();
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
		
		int pricePerHour = roomTable.get(roomIndex).getPricePerHour();
		int userPay = roomTable.get(roomIndex).pay(pricePerHour);
		
		return userPay;
	}
	
	
	//방 정보 쓰기
	void writeRoomInfo(ObjectOutputStream oos) throws Exception
	{
		oos.writeObject(roomTable);
	}
	//방 정보 읽기
	@SuppressWarnings("unchecked")
	void readRoomInfo(ObjectInputStream ois) throws Exception
	{
		roomTable = (ArrayList<Room>) ois.readObject();
	}
	
	
}
