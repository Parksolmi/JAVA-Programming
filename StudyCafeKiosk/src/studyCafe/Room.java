package studyCafe;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.io.*;

@SuppressWarnings("serial")
public class Room implements Serializable {
	
	private User user = new User("", ""); //������� ����� ��ü
	private String roomName; //���̸�
	private int capacity; //�� �ϳ� �� �ִ� �����ο�
	private int pricePerHour; //�ð� �� �氡��
	private GregorianCalendar startTime; //�Խ� ��¥
	private GregorianCalendar endTime; //��� ��¥
	transient private int usedTime; //�̿�ð� (����:�ð�)
	private boolean using; //��뿩��
	
	//private boolean Reserve; //���࿩��
	//private int reserveTime; //����ð�
	
	//������
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
	}
	
	//getter�Լ�
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
	User getUser()
	{
		return user;
	}
	//üũ�� �ð�
	public GregorianCalendar getCheckInTime()
	{
		return startTime;
	}
	//üũ�ƿ� �ð�
	public GregorianCalendar getCheckOutTime()
	{
		this.endTime = new GregorianCalendar();
		return endTime;
	}

	//setter�Լ�
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
	void setUser(User user)
	{
		this.user = user;
	}
	void setStartTime(GregorianCalendar startTime)
	{
		this.startTime = startTime;
	}
	void setEndTime(GregorianCalendar endTime)
	{
		this.endTime = endTime;
	}
	
	
	//(ArrayList API�� ����) equals�Լ� �������̵�
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
	//üũ��
	public void checkIn(User user)
	{
		this.user = user;
		setUsing(true);
		this.startTime = new GregorianCalendar();
	}
	//üũ�ƿ�
	public void checkOut()
	{
		this.user = null;
		setUsing(false);
	}
	
	//���ð����
	private int calacUsedTime()
	{
		//�� ���
		int startDate = startTime.get(Calendar.DATE);
		int endDate = endTime.get(Calendar.DATE);
		
		//�ð� ���
		int startHour = startTime.get(Calendar.HOUR_OF_DAY);
		int endHour = endTime.get(Calendar.HOUR_OF_DAY);
		
		//�� �ð�
		usedTime = 0; 
		if(startDate < endDate) //day�� ���� ���
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
		else //���� day�� ���
		{
			usedTime = endHour - startHour;
		}
		
		if(usedTime == 0)
		{
			usedTime = 1;
		}
		
		return usedTime;
	}
	
	//�� ���� �Լ�
	public int pay(int pricePerHour)
	{
		setPricePerHour(pricePerHour);
		usedTime = calacUsedTime();
		int userPay = usedTime * pricePerHour;
		
		return userPay;
	}
	
}
