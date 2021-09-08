package studyCafe;

import java.io.*;

public class Management {
	Room[] roomTable = new Room[100]; //방 객체를 담는 배열
	int count = 0; //현재 배열이 어디까지 차 있는지 나타내는 변수
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
	
	
	// 해당 룸 이름을 가진 방을 roomTable배열에서 찾는 함수
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
			throw new Exception(roomName + "이라는 방은 사용가능하지 않습니다.");
		}

		return room;
	}
	
	//manager 기능-----------------------------------------------------------------------------------
	//룸 생성
	public void createRoom(String roomName, int capacity, int pricePerHour)
	{
		roomTable[count] = new Room(roomName, capacity, pricePerHour);
		roomTable[count].setUsing(false);
		count++;
	}
	//룸 삭제
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
		
		//삭제할 방을 찾지 못했을 때
		if(!findRoomToRemove)
		{
			throw new Exception(roomName + "이라는 방은 존재하지 않습니다.");
		}
		return null;
	}
	//룸 관련 정보 수정
	//방 이름 수정
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
	//수용 인원 수정
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
	//시간당 가격 수정
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
	//해당 이름의 방의 capacity값을 반환하는 함수
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
	//해다 이름의 방의 가격을 반환하는 함수
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
	//toString 오버라이딩
	public String toString(Room room)
	{
		return room.getRoomName() + "| \t  " + room.getCapacity() + "\t"
				+ room.getPricePerHour() + "\t " + room.getUsing();
	}
	//생성된 전체 방 조회
	public void checkAllCreatedRoom() throws Exception
	{
		for(int i=0; i<count; i++)
		{
			if(roomTable[i] == null)
			{
				throw new Exception("생성된 방이 없습니다.");
			}
			else
				System.out.println(toString(roomTable[i]));
		}
	}

	//User 기능---------------------------------------------------------------------------------------
	
	//수용 인원(capacity)으로 빈 방 검색하기(User UI에서 사용)
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
			throw new Exception("사용 가능한 빈 방이 없습니다.");
		}
		return emptyRoom;
	}
	
	// 체크인
	public void checkIn(String roomName, User user) throws Exception 
	{
		// 방찾기
		Room room = findRoom(roomName);
		
		if(room == null) //방을 못찾아서 방이 비었을 때
		{
			throw new Exception(roomName +"not exist!");
		}
		String showCheckInTime = room.checkIn(user);
		System.out.println(showCheckInTime); //입실시간 사용자에게 보여줌
	}

	// 체크아웃 사용자 정보 확인
	public boolean isRightCheckOutUser(String roomName, String userName, String userPhoneNum) throws Exception 
	{
		// 방찾기
		Room room = findRoom(roomName);
		// 사용자 정보 비교
		User user = room.getUser();
		String roomUserName = user.getUserName();
		String roomUserphoneNum = user.getUserPhoneNum();

		if (roomUserName.equals(userName)) 
		{
			if (roomUserphoneNum.equals(userPhoneNum))
				return true; // 사용자 정보 일치
		}
		return false; // 사용자 정보 불일치

	}

	// 체크아웃
	public void checkOut(String roomName) throws Exception {
		// 방찾기
		Room room = findRoom(roomName);
		
		if(room == null) //방을 못찾아서 방이 비었을 때
		{
			throw new Exception(roomName +"not exist!");
		}
		room.checkOut();
	}
	//체크아웃시간 보여주기
	public void showCheckOutTime(String roomName) throws Exception
	{
		// 방찾기
		Room room = findRoom(roomName);

		if (room == null) // 방을 못찾아서 방이 비었을 때
		{
			throw new Exception(roomName + "not exist!");
		}
		String showCheckOutTime = room.checkOutTime();
		System.out.println(showCheckOutTime); //퇴실시간 사용자에게 보여줌
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
