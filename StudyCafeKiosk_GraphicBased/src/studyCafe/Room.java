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
	private boolean using; //��뿩��
	private Sales salesInfo = new Sales(); //���� ���� ������ ������ ��ü
	
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
		this.using = false;
	}
	
	//getter�Լ�
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
	public Sales getSalesInfo()
	{
		return salesInfo;
	}

	//setter�Լ�
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
		salesInfo.setPayedUser(user);
		salesInfo.setPaymentDate(endTime);
		
		setUsing(false); //������� false�� �ٲ�
	}
	
	//���ð����
	private int calcUsedTime()
	{
		//�� ���
		int startDate = startTime.get(Calendar.DATE);
		int endDate = endTime.get(Calendar.DATE);
		
		//�ð� ���
		int startHour = startTime.get(Calendar.HOUR_OF_DAY);
		int endHour = endTime.get(Calendar.HOUR_OF_DAY);
		
		//�� �ð�
		int usedTime = 0; 
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
	public int pay()
	{
		int usedTime = calcUsedTime();
		int userPay = usedTime * pricePerHour;
		salesInfo.setUsedTime(usedTime);
		salesInfo.setPayedBill(userPay);
		return userPay;
	}
}
