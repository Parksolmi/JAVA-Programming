import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.io.*;

public class Room {
	private String roomName; //���̸�
	private int capacity; //�� �ϳ� �� �ִ� �����ο�
	private int pricePerHour; //�ð� �� �氡��
	private GregorianCalendar startTime; //�Խ� ��¥
	String showCheckInTime; //üũ�� �ð�(������ ���˿� ���� ������)
	private GregorianCalendar endTime; //��� ��¥
	private int usedTime; //�̿�ð� (����:�ð�)
	private boolean using; //��뿩��
	
	private boolean Reserve; //���࿩��
	private int reserveTime; //����ð�
	
	//������
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

	void setStartTime(GregorianCalendar startTime)
	{
		this.startTime = startTime;
	}
	void setEndTime(GregorianCalendar endTime)
	{
		this.endTime = endTime;
	}

	//üũ�ƿ� �ð�
	public String getCheckOutTime()
	{
		endTime = new GregorianCalendar();
		// ��� �ð� ���� ����
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM�� dd�� aa hh�� mm�� ss��");
		String showCheckOutTime = dateFormat.format(endTime.getTime());
		return showCheckOutTime; // ��� �ð� ����ڿ��� �����ֱ� ���� return
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
	
	//equals�Լ� �������̵�
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Room)) //��üŸ�Ժ�
		{
			return false;
		}
		Room room = (Room) obj;
		
		if(roomName == room.roomName) //���̸���
		{
			return true;
		}
		else
			return false;
	}
	
}
