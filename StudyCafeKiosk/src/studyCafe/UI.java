package studyCafe;
import java.io.*;
import java.util.Scanner;

public class UI 
{

	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in); //scanner��ü ����
		
		FileOutputStream out = new FileOutputStream("studyCafeUsers");
		Management mg = new Management("manager001"); //management��ü ����
		
		boolean systemExit = false; //�ý����� ���� ������ ����
		while(!systemExit)
		{
			System.out.println("-----��� ����-----");
			System.out.println("1. ����� ���");
			System.out.println("2. ������ ���");
			System.out.println("3. ����");
			System.out.print("=> ");
			
			boolean userMode = false; //����� ��� ���� ������ ����
			boolean managerMode = false; //������ ��� ���� ������ ����
			
			try 
			{
				int answerMode = sc.nextInt();
				sc.nextLine();
				
				switch(answerMode)
				{
				case 1 : 
					userMode = true; break;
				case 2 : 
					System.out.print("Manager ID : "); //manager ���̵� �Է�
					String managerID = sc.next();
					if(mg.checkManagerID(managerID)) //manager ���̵� ��ġ�ϸ� managerMode�� �� �� ����
					{
						managerMode = true;
					}
					else //manager ���̵� ����ġ
					{
						System.out.println("ID�� ��ġ���� �ʽ��ϴ�.");
					}
					break;
				case 3 : 
					systemExit = true; break;
				default : 
					System.out.println("1~3�߿� �����ϼ���.\n"); break;
				}
			}
			catch (java.util.InputMismatchException ime) //���ڰ� �ƴ� ���� �Է����� �� ����ó��
			{
				System.out.println("�߸��� ���� �Է��Ͽ����ϴ�. 1~3�߿� �����ϼ���.\n");
				sc.nextLine(); //���� ������ ���� ���� �Է�
			}
			
			
			
			//User���-------------------------------------------------------------------------------------
			while(userMode)
			{
				System.out.println();
				System.out.println("-----����� �޴�-----");
				System.out.println("1. ��� �˻�");
				System.out.println("2. �Խ�");
				System.out.println("3. ���");
				System.out.println("4. ���� �޴��� ���ư���");
				System.out.println("5. ����");
				System.out.print("=> ");
				try
				{
					int answerUserMenu = sc.nextInt();
					
					switch(answerUserMenu)
					{
					case 1: //��� �˻�
						try 
						{
							System.out.print("���� �ο� : "); //�˻� �� �����ο� �Է��ϱ�
							int capacity = sc.nextInt();
							//��� ����Ʈ ��ȸ�ؼ� emptyRoom�迭�� ����
							Room[] emptyRoom = mg.searchCapacityEmptyRoom(capacity);
							System.out.println("���̸�\t �ο�\t ����");
							int length = emptyRoom.length;
							for(int i=0; i<length; i++)
							{
								if(emptyRoom[i] == null)
									break;
								else
								{
									//��� ����Ʈ ����ϱ�
									
									System.out.println(emptyRoom[i].getRoomName() + "|\t" 
													  + emptyRoom[i].getCapacity() + "\t"
													  + emptyRoom[i].getPricePerHour());
								}
							}
						}
						catch (java.util.InputMismatchException ime) 
						{
							System.out.print("�߸��� �Է� ���Դϴ�. ���ڸ� �Է��ϼ���.");
						}
						catch(Exception e)
						{
							System.out.println(e.getMessage());
						}
					break;
					case 2: //�Խ�
						//����� ���� �Է� �ޱ�
						System.out.print("����� �̸� : ");
						String userName = sc.next();
						System.out.print("����� ��ȭ��ȣ : ");
						String userPhoneNum = sc.next();
						User user = new User(userName, userPhoneNum);
						//�Խ��� �� ���� �Է� �ޱ�
						System.out.print("�Խ��� �� �̸� : ");
						String roomName = sc.next();
						//�Խ��ϱ�
						try
						{
							mg.checkIn(roomName, user);
							System.out.println("�ԽǵǾ����ϴ�.");
						}
						catch(Exception e)
						{
							System.out.println("���� ã�� �� �����ϴ�.");
						}
						break;
					case 3: //���
						//üũ�ƿ��ϴ� ���� ����� ���� ��ȸ
						System.out.print("����� �� �̸� : ");
						roomName = sc.next();
						System.out.print("����� �̸� : ");
						userName = sc.next();
						System.out.print("����� ��ȭ��ȣ : ");
						userPhoneNum = sc.next();
						
						boolean checkUser = mg.isRightCheckOutUser(roomName, userName, userPhoneNum);
						if(checkUser)
						{
							mg.showCheckOutTime(roomName);
							int userPay = mg.pay(roomName);
							System.out.println("���� �ݾ� : " + userPay);
							System.out.print("�����Ͻðڽ��ϱ�?(y||Y) : ");
							String payAnswer = sc.next();
							if(payAnswer.equals("y") || payAnswer.equals("Y"))
							{
								mg.checkOut(roomName);
								System.out.println("��ǵǾ����ϴ�.");
							}
							else System.out.println("����� ��ҵǾ����ϴ�.");
						}
						else
							System.out.println("�߸��� ����� �����Դϴ�.");
						break;
					case 4 : //���� �޴��� ���ư���
						userMode = false;
						break;
					case 5 : //����
						systemExit = true;
						userMode = false;
						break;
					default : 
						System.out.println("1~5�߿� �����ϼ���.\n");
						break;
					}
				}
				catch (java.util.InputMismatchException ime) //���ڰ� �ƴ� ���� �Է����� �� ����ó��
				{
					System.out.println("�߸��� ���� �Է��Ͽ����ϴ�. 1~3�߿� �����ϼ���.\n");
				}
			}
			
			//Manager���-------------------------------------------------------------------------------------
			while(managerMode)
			{
				System.out.println();
				System.out.println("-----������ �޴�-----");
				System.out.println("1. �� ����");
				System.out.println("2. �� ����");
				System.out.println("3. �� ����");
				System.out.println("4. ��ü �� ��ȸ");
				System.out.println("5. ���� �޴��� ���ư���");
				System.out.println("6. ����");
				System.out.print("=> ");
				try
				{
					int answerManagerMenu = sc.nextInt();
					
					switch(answerManagerMenu)
					{
					case 1 : //�� ����
						try 
						{
							//������ �� ���� �Է�
							System.out.print("������ �� �̸� : ");
							String roomName = sc.next();
							System.out.print("������ �� ���� �ο� : ");
							int capacity = sc.nextInt();
							System.out.print("������ �� �ð� �� ��� : ");
							int pricePerHour = sc.nextInt();
							//�� ����
							mg.createRoom(roomName, capacity, pricePerHour);
						}
						catch(java.util.InputMismatchException ime) //int�� �����Ϳ� �ٸ� ���� �ԷµǾ��� ��� ����ó��
						{
							System.out.println("�߸��� ���� �Է��Ͽ����ϴ�. ���ڸ� �Է��ϼ���.");
							sc.nextLine();
						}
;						break;
					case 2 : //�� ����
						System.out.print("������ �� �̸� : ");
						String roomName = sc.next();
						Room room = mg.removeRoom(roomName);
						System.out.println(room.getRoomName() + "���� �����Ͽ����ϴ�.");
						break;
					case 3 : //�� ����
						//���� �޴�
						System.out.println("----������ ����----");
						System.out.println("1. �� �̸�");
						System.out.println("2. ���� �ο�");
						System.out.println("3. �ð� �� ����");
						System.out.println("4. ���� �޴��� ���ư���");
						System.out.println("5. ����");
						System.out.print("=> ");
						int fixMenu = sc.nextInt();
						
						switch(fixMenu) //���� ó���� ���� �������� ����.
						{
						case 1 : //�� �̸� ����
							System.out.println("���� �� �� �̸� : ");
							String orgRoomName = sc.next();
							System.out.println("�� �̸��� �������� �����Ͻðڽ��ϱ�? : ");
							String changedRoomName = sc.next();
							mg.changeRoomName(orgRoomName, changedRoomName);
							System.out.println(orgRoomName + "�� " + changedRoomName 
												+ "���� �� �̸��� ����Ǿ����ϴ�.");
							break;
						case 2 : //�� ���� �ο� ����
							System.out.println("�ִ� ���� �ο��� ������ �� �̸� : ");
							roomName = sc.next();
							int orgCapacity = mg.howManyCapacity(roomName);
							System.out.println(roomName + "�� ���� �� �ִ� ���� �ο��� "+ orgCapacity + "�Դϴ�.");
							System.out.println("�ִ� ���� �ο��� �� ������ �����Ͻðڽ��ϱ�? : ");
							int changedCapacity = sc.nextInt();
							mg.changeCapacity(roomName, changedCapacity);
							System.out.println(roomName + "�� �ִ� ���� �ο��� " + changedCapacity + "���� ����Ǿ����ϴ�.");
							break;
						case 3 : //�ð� �� ���� ����
							System.out.println("�ð� �� ������ ������ �� �̸� : ");
							roomName = sc.next();
							int orgPricePerHour = mg.howMuchPrice(roomName);
							System.out.println(roomName + "�� ���� �� ����� "+ orgPricePerHour + "�Դϴ�.");
							System.out.println("�󸶷� �����Ͻðڽ��ϱ�? : ");
							int changedPricePerHour = sc.nextInt();
							mg.changePricePerHour(roomName, changedPricePerHour);
							System.out.println(roomName + "�� �ð� �� ����� " + changedPricePerHour + "���� ����Ǿ����ϴ�.");
							break;
						case 4 : //���� �޴��� ���ư���
							break;
						case 5 : //�����ϱ�
							managerMode = false;
							systemExit = true;
							break;
						}
						break; //case3�� break��
					case 4 : //��ü �� ��ȸ
						System.out.println("�� �̸�\t �ο�\t ����\t ��뿩��");
						System.out.println("---------------------------------");
						try 
						{
							mg.checkAllCreatedRoom(); //��ü �� ��ȸ
						}
						catch(Exception e)
						{
							System.out.println(e.getMessage()); //��ȸ�� ���� ���� �� ����ó��
						}
						break;
					case 5 : //���� �޴��� ���ư���
						managerMode = false;
						break;
					case 6 : //����
						systemExit = true;
						managerMode = false;
						break;
					default : 
						break;
					}
				}
				catch (java.util.InputMismatchException ime) //���ڰ� �ƴ� ���� �Է����� �� ����ó��
				{
					System.out.println("�߸��� ���� �Է��Ͽ����ϴ�. 1~3�߿� �����ϼ���.\n");
					sc.nextLine();
				}
			}
		}
	}
}
