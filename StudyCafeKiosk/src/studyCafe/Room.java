package studyCafe;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.io.*;

public class Room {
	private User user = new User("", ""); //������� ����� ��ü
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
	Room()
	{
		
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
	
	//üũ��
	public boolean checkIn(User user)
	{
		if(!getUsing()) //������� �ƴ� ��
		{
			this.user = user;
			setUsing(true);
			this.startTime = new GregorianCalendar();
			return true;
		}
		else //������� ��
		{
			return false;
		}
		
	}
	//üũ�� �ð�
	public String getCheckInTime()
	{
		//���� �ð� ���� ����
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM�� dd�� aa hh�� mm�� ss��");
		showCheckInTime = dateFormat.format(startTime.getTime());
		return showCheckInTime; // �Խ� �ð� ����ڿ��� �����ֱ� ���� return
	}
	//üũ�ƿ�
	public void checkOut()
	{
		this.user = null;
		setUsing(false);
	}
	
	//üũ�ƿ� �ð�
	public String getCheckOutTime()
	{
		this.endTime = new GregorianCalendar();
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
	
	//�� ���� �Է�
	void writeRoomInfo(FileOutputStream fos) throws Exception
	{
		DataOutputStream dos = new DataOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		dos.writeUTF(roomName); //���̸�
		dos.writeInt(capacity); //�����ο�
		dos.writeInt(pricePerHour); //�ð��簡��
		dos.writeBoolean(using); //��뿩��
		if(using)
		{
			oos.writeObject(startTime); //�Խǽð�
			dos.writeUTF(user.getUserName()); //����� �̸�
			dos.writeUTF(user.getUserPhoneNum()); //����� ��ȣ
		}
	}
	
	//�� ���� �б�
	void readRoomInfo(FileInputStream fis) throws Exception
	{
		DataInputStream dis = new DataInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		roomName = dis.readUTF();
		capacity = dis.readInt();
		pricePerHour = dis.readInt();
		using = dis.readBoolean();
		if(using)
		{
			startTime = (GregorianCalendar) ois.readObject();
			user.setUserName(dis.readUTF());
			user.setUserPhoneNum(dis.readUTF());
		}
	}
	
}
