package studyCafe;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.io.*;

@SuppressWarnings("serial")
public class Room implements Serializable {
	
	private User user = new User("", ""); //사용중인 사용자 객체
	private String roomName; //방이름
	private int capacity; //방 하나 당 최대 수용인원
	private int pricePerHour; //시간 당 방가격
	private GregorianCalendar startTime; //입실 날짜
	private GregorianCalendar endTime; //퇴실 날짜
	private boolean using; //사용여부
	private Sales salesInfo = new Sales(); //방의 매출 정보를 저장할 객체
	
	//생성자
	Room()
	{
		
	}
	Room(String roomName)
	{
		this.roomName = roomName;
	}
	Room(String roomName, int capacity, int pricePerHour)
	{
		this.roomName = roomName;
		this.capacity = capacity;
		this.pricePerHour = pricePerHour;
		this.using = false;
	}
	
	//getter함수
	public String getRoomName()
	{
		return roomName;
	}
	public int getCapacity()
	{
		return capacity;
	}
	public int getPricePerHour()
	{
		return pricePerHour;
	}
	public boolean getUsing()
	{
		return using;
	}
	public User getUser()
	{
		return user;
	}
	//체크인 시간
	public GregorianCalendar getCheckInTime()
	{
		return startTime;
	}
	//체크아웃 시간
	public GregorianCalendar getCheckOutTime()
	{
		this.endTime = new GregorianCalendar();
		return endTime;
	}
	public Sales getSalesInfo()
	{
		return salesInfo;
	}

	//setter함수
	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}
	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}
	public void setPricePerHour(int pricePerHour)
	{
		this.pricePerHour = pricePerHour;
	}
	public void setUsing(boolean using)
	{
		this.using = using;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public void setStartTime(GregorianCalendar startTime)
	{
		this.startTime = startTime;
	}
	public void setEndTime(GregorianCalendar endTime)
	{
		this.endTime = endTime;
	}
	
	
	//(ArrayList API를 위한) equals함수 오버라이딩
	public boolean equals(Object obj)
	{
		Room room = (Room) obj;
		
		if(roomName.equals(room.getRoomName()))
		{
			return true;
		}
		else
			return false;
	}
	//체크인
	public void checkIn(User user)
	{
		this.user = user;
		setUsing(true);
		this.startTime = new GregorianCalendar();
	}
	//체크아웃
	public void checkOut()
	{
		salesInfo.setPayedUser(user);
		salesInfo.setPaymentDate(endTime);
		
		setUsing(false); //사용정보 false로 바꿈
	}
	
	//사용시간계산
	private int calcUsedTime()
	{
		//일 계산
		int startDate = startTime.get(Calendar.DATE);
		int endDate = endTime.get(Calendar.DATE);
		
		//시간 계산
		int startHour = startTime.get(Calendar.HOUR_OF_DAY);
		int endHour = endTime.get(Calendar.HOUR_OF_DAY);
		
		//총 시간
		int usedTime = 0; 
		if(startDate < endDate) //day가 지난 경우
		{
			if(startHour <= endHour)
			{
				usedTime = (endDate - startDate)*24 + (endHour - startHour);
			}
			else if(startHour > endHour)
			{
				usedTime = (endDate - startDate)*24 - (startHour - endHour);
			}
		}
		else //같은 day인 경우
		{
			usedTime = endHour - startHour;
		}
		
		if(usedTime == 0)
		{
			usedTime = 1;
		}
		
		return usedTime;
	}
	
	//값 지불 함수
	public int pay()
	{
		int usedTime = calcUsedTime();
		int userPay = usedTime * pricePerHour;
		salesInfo.setUsedTime(usedTime);
		salesInfo.setPayedBill(userPay);
		return userPay;
	}
}
