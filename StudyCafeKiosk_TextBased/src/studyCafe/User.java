package studyCafe;

@SuppressWarnings("serial")
public class User implements java.io.Serializable {
	
	private String userPhoneNum; //����� ��ȭ��ȣ
	private String userName; //����� �̸�
	
	//������
	User (String userName, String userPhoneNum)
	{
		this.userName = userName;
		this.userPhoneNum = userPhoneNum;
	}
	
	//getter�� setter�Լ�
	public String getUserPhoneNum()
	{
		return userPhoneNum;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserPhoneNum(String userPhoneNum)
	{
		this.userPhoneNum = userPhoneNum;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	
}
