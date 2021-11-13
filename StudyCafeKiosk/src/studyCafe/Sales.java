package studyCafe;

import java.util.GregorianCalendar;

//객체 직렬화 가능 클랙스
@SuppressWarnings("serial")
public class Sales implements java.io.Serializable {
	
	private GregorianCalendar paymentDate; //결제일
	private User payedUser; //사용자
	private int usedTime; //사용시간
	private int payedBill; //결제비용
	
	//생성자
	Sales()
	{
		
	}
	
	//getter&setter
	public GregorianCalendar getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(GregorianCalendar paymentDate) {
		this.paymentDate = paymentDate;
	}
	public User getPayedUser() {
		return payedUser;
	}
	public void setPayedUser(User payedUser) {
		this.payedUser = payedUser;
	}
	public int getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(int usedTime) {
		this.usedTime = usedTime;
	}
	public int getPayedBill() {
		return payedBill;
	}
	public void setPayedBill(int paymentBill) {
		this.payedBill = paymentBill;
	}
}
