package studyCafe;

import java.util.GregorianCalendar;

//��ü ����ȭ ���� Ŭ����
@SuppressWarnings("serial")
public class Sales implements java.io.Serializable {
	
	private GregorianCalendar paymentDate; //������
	private User payedUser; //�����
	private int usedTime; //���ð�
	private int payedBill; //�������
	
	//������
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
