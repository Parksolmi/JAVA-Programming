package studyCafe;

@SuppressWarnings("serial")
public class User implements java.io.Serializable {
	
	private String userPhoneNum; //사용자 전화번호
	private String userName; //사용자 이름
	
	//생성자
	User (String userName, String userPhoneNum)
	{
		this.userName = userName;
		this.userPhoneNum = userPhoneNum;
	}
	
	//getter와 setter함수
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
