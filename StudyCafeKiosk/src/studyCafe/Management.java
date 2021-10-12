package studyCafe;

import java.io.*;
import java.util.ArrayList;

public class Management {
	ArrayList<Room> roomTable = new ArrayList<Room>(); //방 객체를 담는 리스트
	int roomTableSize; //roomTable리스트의 크기
	private String managerID; //관리자UI로 들어가기 위한 관리자ID
	
	private int[] totalSales = new int[31]; //매일(31일) 각각의 매출을 저장하는 배열
	
	//생성자
	Management(String managerID)
	{
		this.managerID = managerID;
		
	}
	Management()
	{
		
	}
	//managerID검사하는 함수
	boolean checkManagerID(String managerID)
	{
		if(this.managerID.equals(managerID)) return true;
		else return false;
	}

	// roomTable에서 해당 룸 이름을 가진 방의 index를 찾는 함수 - 수정
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
			throw new Exception("There is no room named " + roomName + "."); //메세지길게하지말기
		}

		return roomIndex;
	}
	
	//manager 기능-----------------------------------------------------------------------------------
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
		int roomIndex = findRoom(roomName);
		roomTable.remove(roomIndex);
		
		//삭제할 방을 찾지 못했을 때
		if(roomIndex == -1)
		{
			throw new Exception(roomName + " doesn't exist!");
		}
	}
	
	
	//룸 관련 정보 수정
	//방 이름 수정
	public void changeRoomName(String orgRoomName, String changedRoomName) throws Exception
	{
		int orgRoomIndex = findRoom(orgRoomName);
		
		roomTable.get(orgRoomIndex).setRoomName(changedRoomName);
	}
	//수용 인원 수정
	public void changeCapacity(String roomName, int changedCapacity) throws Exception
	{
		int roomIndex = findRoom(roomName);
		
		roomTable.get(roomIndex).setCapacity(changedCapacity);
	}
	//시간당 가격 수정
	public void changePricePerHour(String roomName, int changedPricePerHour) throws Exception
	{
		int roomIndex = findRoom(roomName);
		
		roomTable.get(roomIndex).setPricePerHour(changedPricePerHour);
	}
	
	
	//해당 이름의 방의 capacity값을 반환하는 함수
	public int howManyCapacity(String roomName) throws Exception
	{
		int roomIndex = findRoom(roomName);
		
		return roomTable.get(roomIndex).getCapacity();
	}
	//해당 이름의 방의 가격을 반환하는 함수
	public int howMuchPrice(String roomName) throws Exception
	{
		int roomIndex = findRoom(roomName);
		
		return roomTable.get(roomIndex).getPricePerHour();
	}
	
	//수정 : 출력문 - UI
	//toString 오버라이딩
	public String toString(Room room)
	{
		return room.getRoomName() + "| \t  " + room.getCapacity() + "\t\t"
				+ room.getPricePerHour() + "\t " + room.getUsing();
	}
	//생성된 전체 방 조회
	public void checkAllCreatedRoom() //룸을 리턴
	{
		roomTableSize = roomTable.size();
		for(int index = 0; index<roomTableSize; index++)
		{
			System.out.println(toString(roomTable.get(index)));
		}
	}

	//User 기능---------------------------------------------------------------------------------------
	
	//수용 인원(capacity)으로 빈 방 검색하기
	public void searchEmptyRoomByCapacity(int capacity) throws Exception //수정 : Arraylist/Array 반환
	{
		roomTableSize = roomTable.size(); //roomTable의 크기
		
		ArrayList<Room> emptyRoomTable = new ArrayList<Room>(); //roomTable에서 빈 방만 담은 리스트
		int emptyRoomTableSize = emptyRoomTable.size(); //빈 방 수
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
		
		//수용 인원(Capacity)으로 찾은 빈 방 출력하기
		System.out.println("방이름\t 인원\t 가격");
		System.out.println("---------------------------------");
		for(int index = 0; index<emptyRoomTableSize; index++)
		{
			System.out.println(toString(emptyRoomTable.get(index)));
		}
	}
	
	
	// 체크인
	public void checkIn(String roomName, User user) throws Exception 
	{
		// 방찾기
		int roomIndex = findRoom(roomName);
		//체크인하기
		String showCheckInTime = roomTable.get(roomIndex).checkIn(user);
		System.out.println(showCheckInTime); //입실시간 사용자에게 보여주기 - 수정 : 시간 객체를 넘겨주기
	}

	// 체크아웃 사용자 정보 확인
	public boolean isRightCheckOutUser(String roomName, String userName, String userPhoneNum) throws Exception 
	{
		// 방찾기
		int roomIndex = findRoom(roomName);
		
		// 사용자 정보 비교
		User user = roomTable.get(roomIndex).getUser();
		if (user.getUserName().equals(userName) 
				&& user.getUserPhoneNum().equals(userPhoneNum)) 
		{
			return true; // 사용자 정보 일치
		}
		
		return false; // 사용자 정보 불일치
	}

	// 체크아웃
	public void checkOut(String roomName) throws Exception 
	{
		// 방찾기
		int roomIndex = findRoom(roomName);
		roomTable.get(roomIndex).checkOut();
	}
	
	//체크아웃시간 보여주기
	public void showCheckOutTime(String roomName) throws Exception
	{
		// 방찾기
		int roomIndex = findRoom(roomName); //수정 - 방을 못 찾았을 때 : 프로그램 유지되도록(try-catch)

		String showCheckOutTime = roomTable.get(roomIndex).getCheckOutTime();
		System.out.println(showCheckOutTime); //퇴실시간 사용자에게 보여줌
	}
	
	public int pay(String roomName) throws Exception
	{
		int roomIndex = findRoom(roomName);
		
		int pricePerHour = roomTable.get(roomIndex).getPricePerHour();
		int userPay = roomTable.get(roomIndex).pay(pricePerHour);
		
		return userPay;
	}
	
	//방 정보 입력 - Room클래스에서 받아와서 쓰기
	void writeRoomInfo(FileOutputStream fos) throws Exception
	{
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.writeInt(roomTableSize);
		for(Room room:roomTable)
		{
			room.writeRoomInfo(fos);
		}
		
	}
	
	//방 정보 읽어서 변수에 저장하기
	void readRoomInfo(FileInputStream fis) throws Exception
	{
		DataInputStream dis = new DataInputStream(fis);
		
		roomTableSize = dis.readInt();
		System.out.println("Currently, " + roomTableSize + " rooms are created."); //빼기
		System.out.println();
		
		for(int index=0; index<roomTableSize; index++)
		{
			Room room = new Room("temp", 0, 0, "empty", "empty");
			roomTable.add(room);
			roomTable.get(index).readRoomInfo(fis);
		}
	}
	
}
