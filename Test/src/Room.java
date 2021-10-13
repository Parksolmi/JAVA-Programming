import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.io.*;

public class Room {
	private String roomName; //방이름
	private int capacity; //방 하나 당 최대 수용인원
	private int pricePerHour; //시간 당 방가격
	private GregorianCalendar startTime; //입실 날짜
	String showCheckInTime; //체크인 시간(정해진 포맷에 맞춰 보여줌)
	private GregorianCalendar endTime; //퇴실 날짜
	private int usedTime; //이용시간 (단위:시간)
	private boolean using; //사용여부
	
	private boolean Reserve; //예약여부
	private int reserveTime; //예약시간
	
	//생성자
	Room(String roomName, int capacity, int pricePerHour)
	{
		this.roomName = roomName;
		this.capacity = capacity;
		this.pricePerHour = pricePerHour;
	}

	
	//getter함수
	String getRoomName()
	{
		return roomName;
	}
	int getCapacity()
	{
		return capacity;
	}
	int getPricePerHour()
	{
		return pricePerHour;
	}
	boolean getUsing()
	{
		return using;
	}

	//setter함수
	void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}
	void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}
	void setPricePerHour(int pricePerHour)
	{
		this.pricePerHour = pricePerHour;
	}
	void setUsing(boolean using)
	{
		this.using = using;
	}

	void setStartTime(GregorianCalendar startTime)
	{
		this.startTime = startTime;
	}
	void setEndTime(GregorianCalendar endTime)
	{
		this.endTime = endTime;
	}

	//체크아웃 시간
	public String getCheckOutTime()
	{
		endTime = new GregorianCalendar();
		// 퇴실 시간 포맷 설정
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM월 dd일 aa hh시 mm분 ss초");
		String showCheckOutTime = dateFormat.format(endTime.getTime());
		return showCheckOutTime; // 퇴실 시간 사용자에게 보여주기 위해 return
	}

	//사용시간계산
	private int calacUsedTime()
	{
		//일 계산
		int startDate = startTime.get(Calendar.DATE);
		int endDate = endTime.get(Calendar.DATE);
		
		//시간 계산
		int startHour = startTime.get(Calendar.HOUR_OF_DAY);
		int endHour = endTime.get(Calendar.HOUR_OF_DAY);
		
		//총 시간
		usedTime = 0; 
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
	public int pay(int pricePerHour)
	{
		setPricePerHour(pricePerHour);
		usedTime = calacUsedTime();
		int userPay = usedTime * pricePerHour;
		
		return userPay;
	}
	
	//equals함수 오버라이딩
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Room)) //객체타입비교
		{
			return false;
		}
		Room room = (Room) obj;
		
		if(roomName == room.roomName) //방이름비교
		{
			return true;
		}
		else
			return false;
	}
	
}
