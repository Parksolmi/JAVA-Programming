package studyCafe;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UI
{
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in); // scanner��ü ����
		Management mg = new Management("manager001"); // management��ü ����
		File roomInfoFile = new File("studyCafeRoom.txt");
		ObjectOutputStream roomInfoOut = null;
		ObjectInputStream roomInfoIn = null;
		
		if(!roomInfoFile.exists())
		{
			try
			{
				// �� ������ ������ ���� ����
				roomInfoOut = new ObjectOutputStream(new FileOutputStream(roomInfoFile));
			}
			catch (FileNotFoundException fnfe) {
				System.out.println("The file could not be found.");
			}
		}
		
		try {
				// �� ������ �о�� ���� �ҷ�����
				roomInfoIn = new ObjectInputStream(new FileInputStream(roomInfoFile));
				mg.readRoomInfo(roomInfoIn);
				
				int numberOfRooms = mg.getRoomTableSize();
				System.out.println("Currently, " + numberOfRooms + " rooms are created.");
				System.out.println();
				
			} catch (FileNotFoundException fnfe) {
				System.out.println("The file could not be found."); // ������ ã�� �� ����
				System.out.println();
			} catch (EOFException eofe) {
				System.out.println("The file is empty"); // ������ �������
				System.out.println();
			} catch (IOException ioe) {
				System.out.println("The file cannot be read."); // ������ �о�� �� ����
				System.out.println();
			} catch (ClassNotFoundException cnfe) {
				System.out.println("The class does not exist.");
			} finally {
				try {
					roomInfoIn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			boolean systemExit = false; // �ý����� ���� ������ ����
			while (!systemExit) {
				System.out.println("-------MODE-------");
				System.out.println("1. User Mode");
				System.out.println("2. Manager Mode");
				System.out.println("3. Exit");
				System.out.print("=> ");

				boolean userMode = false; // ����� ��� ���� ������ ����
				boolean managerMode = false; // ������ ��� ���� ������ ����

				try {
					int answerMode = sc.nextInt();
					sc.nextLine();

					switch (answerMode) {
					case 1:
						userMode = true;
						break;
					case 2:
						System.out.print("Manager ID : "); // manager ���̵� �Է�
						String managerID = sc.next();
						if (mg.checkManagerID(managerID)) // manager ���̵� ��ġ�ϸ� managerMode�� �� �� ����
						{
							managerMode = true;
						} else // manager ���̵� ����ġ
						{
							System.out.println("It's a wrong ID.");
						}
						break;
					case 3:
						systemExit = true;
						break;
					default:
						System.out.println("Choose between 1 and 3\n");
						break;
					}
				} catch (InputMismatchException ime) // ���ڰ� �ƴ� ���� �Է����� �� ����ó��
				{
					System.out.println("You have entered an invalid value.\n");
					sc.nextLine(); // ���� ������ ���� ���� �Է�
				}

				// User���-------------------------------------------------------------------------------------
				while (userMode) 
				{
					System.out.println();
					System.out.println("------User Menu------");
					System.out.println("1. Search empty room");
					System.out.println("2. Check-in");
					System.out.println("3. Check-out");
					System.out.println("4. Previous Menu");
					System.out.println("5. Exit");
					System.out.print("=> ");
					int answerUserMenu = -1;
					try 
					{
						answerUserMenu = sc.nextInt();
					} 
					catch (java.util.InputMismatchException ime) // ���ڰ� �ƴ� ���� �Է����� �� ����ó��
					{
						System.out.println("You have enetered an invalid value.\n");
						sc.nextLine(); // ���� ������ ���� ���� �Է�
					}
					
						switch (answerUserMenu) 
						{
						case 1: // ��� �˻�
							//�� �� ����Ʈ�� ������ ����
							ArrayList<Room> emptyRoomTable = new ArrayList<Room>();
							int capacity = -1; //�˻��� �ο�
							
							try 
							{
								System.out.print("The number of users : "); // �˻� �� �����ο� �Է��ϱ�
								capacity = sc.nextInt();
							} 
							catch (InputMismatchException ime) 
							{
								System.out.println("You have entered an invalid value.\nPlease enter the number.");
							}
							
							//���� �ο�(Capacity)���� ã�� �� �� ����Ʈ ����ϱ�
							emptyRoomTable = mg.searchEmptyRoomByCapacity(capacity);
							System.out.println("Name\t Capacity\tPrice");
							System.out.println("---------------------------------");
							
							if(emptyRoomTable.isEmpty()) //�� �� ����Ʈ�� ����� ��
							{
								System.out.println("There is no empty room");
								break;
							}
							else
							{
								for(Room room:emptyRoomTable)
								{
									System.out.println(room.getRoomName() + "| \t  " + room.getCapacity() + "\t\t"
											+ room.getPricePerHour());
								}
								break;
							}
							
						case 2: // �Խ�
							String userName = ""; //������̸�
							String userPhoneNum = ""; //����� ��ȭ��ȣ
							String roomName = ""; //�Խ��� �� �̸�
							
							try
							{
								// ����� ���� �Է� �ޱ�
								System.out.print("User Name : ");
								userName = sc.next();
								System.out.print("User Phone number : ");
								userPhoneNum = sc.next();
							}
							catch(InputMismatchException ime) //����� ���� �Է� ���� ����ó��
							{
								System.out.println("You have enetered an invalid value.");
								sc.nextLine();
							}
							
							User user = new User(userName, userPhoneNum); //User��ü ����
							
							try
							{
								// �Խ��� �� ���� �Է� �ޱ�
								System.out.print("Name of the room to check-in : ");
								roomName = sc.next();
							}
							catch(InputMismatchException ime)
							{
								System.out.println("You have enetered an invalid value.");
								sc.nextLine();
							} 
							
							// �Խ��ϱ�
							try 
							{
								if(mg.checkIn(roomName, user))
								{
									Room room = mg.getRoom(roomName);
									//���� �ð� ���� ����
									SimpleDateFormat dateFormat = new SimpleDateFormat("MM�� dd�� aa hh�� mm�� ss��");
									String checkInTime = dateFormat.format(room.getCheckInTime().getTime());
									System.out.println();
									System.out.println("Check-in time : " + checkInTime);
									System.out.println("Check-in success.");
								}
								else
								{
									System.out.println();
									System.out.println("This room is using.");
								}
							} 
							catch (IndexOutOfBoundsException ioe) 
							{
								System.out.println("There is no room named " + roomName);
							}
							break;
							
						case 3: // ���
							// üũ�ƿ��ϴ� ���� ����� ���� ��ȸ
							System.out.print("Name of the room to check-out : ");
							roomName = sc.next();
							boolean checkUser = false; //����� ���� ��ġ ���θ� �����ϴ� ����
							try
							{
								//���� �ִ��� �˻�
								mg.searchRoom(roomName);
								
								//����� ���� �Է�
								System.out.print("User Name : ");
								userName = sc.next();
								System.out.print("User Phone number : ");
								userPhoneNum = sc.next();
								
								//����� ���� �˻�
								checkUser = mg.isRightCheckOutUser(roomName, userName, userPhoneNum);
							}
							catch (IndexOutOfBoundsException ioe) 
							{
								System.out.println("There is no room named " + roomName);
							}
							
							//��� �� ����
							int userPay = -1; //������ ���
							if (checkUser)
							{
								try
								{
									Room room = mg.getRoom(roomName);
									//���� �ð� ���� ����
									SimpleDateFormat dateFormat = new SimpleDateFormat("MM�� dd�� aa hh�� mm�� ss��");
									String checkInTime = dateFormat.format(room.getCheckInTime().getTime());
									String checkOutTime = dateFormat.format(room.getCheckOutTime().getTime());
									
									System.out.println();
									System.out.println("Check-in time : " + checkInTime);
									System.out.println("Check-out time : " + checkOutTime);
									System.out.println();
									
									userPay = mg.pay(roomName);
								}
								catch (IndexOutOfBoundsException ioe) //�ش� �� �̸��� ���� ���
								{
									System.out.println("There is no room named " + roomName);
								}
								System.out.println("Payment amount : " + userPay);
								System.out.print("Do you want to pay?\n(If you agree, please enter 'y' or 'Y'.) : ");
								String payAnswer = sc.next();
								
								if (payAnswer.equals("y") || payAnswer.equals("Y")) 
								{
									try
									{	
										mg.checkOut(roomName);
									}
									catch (IndexOutOfBoundsException ioe) //�ش� �� �̸��� ���� ���
									{
										System.out.println("There is no room named " + roomName);
									}
									System.out.println();
									System.out.println("Check-out success.");
								} 
								else //������ �¶����� ���� ���
								{
									System.out.println();
									System.out.println("Check-out is been canceled.");
								}
							} 
							else //����� ������ ��ġ���� ���� ���
								System.out.println("Wrong user information.");
							break;
							
						case 4: // ���� �޴��� ���ư���
							userMode = false;
							break;
							
						case 5: // ����
							System.out.println("Thank you for using it.\nGoodbye!");
							systemExit = true;
							userMode = false;
							break;
						default:
							System.out.println("Choose between 1 and 5.\n");
							break;
						}
					
				}
				System.out.println();

				// Manager���-------------------------------------------------------------------------------------
				while (managerMode) {
					System.out.println();
					System.out.println("-----Manager Menu-----");
					System.out.println("1. Create a room"); // �� ����
					System.out.println("2. Remove a room"); // �� ����
					System.out.println("3. Revise room info"); // �� ����
					System.out.println("4. Check the entire rooms"); // ��ü �� ��ȸ
					System.out.println("5. Previous menu"); // ���� �޴��� ���ư���
					System.out.println("6. Exit");
					System.out.print("=> ");
					try {
						int answerManagerMenu = sc.nextInt();

						switch (answerManagerMenu) {
						case 1: // �� ����
							try {
								// ������ �� ���� �Է�
								System.out.print("Room name to create : ");
								String roomName = sc.next();
								System.out.print("Room capacity : ");
								int capacity = sc.nextInt();
								System.out.print("Price per hour : ");
								int pricePerHour = sc.nextInt();
								
								//�� �̸� �ߺ� �˻�
								if(!mg.searchRoom(roomName))
								{
									// �� ����
									mg.createRoom(roomName, capacity, pricePerHour);
									System.out.println("Successfully created! : " + roomName );
								}
								else
								{
									System.out.println(roomName + " is already existed.");
								}
							} catch (InputMismatchException ime) //�ٸ�type�� ���� �ԷµǾ��� ��� ����ó��
							{
								System.out.println("You have entered an invalid value.");
								sc.nextLine();
							}

							break;
						case 2: // �� ����
							System.out.print("Room name to remove : ");
							String roomName = sc.next();
							try
							{
								mg.removeRoom(roomName);
							}
							catch(IndexOutOfBoundsException ioe)
							{
								System.out.println("There is no room named" + roomName);
							}
							System.out.println("Successfully removed! : " + roomName );
							break;
						case 3: // �� ����
							// ���� �޴�
							System.out.println("-----Modify-----");
							System.out.println("1. Room name");
							System.out.println("2. Room capacity");
							System.out.println("3. Price per hour");
							System.out.println("4. Previous menu");
							System.out.println("5. Exit");
							System.out.print("=> ");
							int fixMenu = sc.nextInt();

							switch (fixMenu) // ���� ó���� ���� �������� ����.
							{
							case 1: // �� �̸� ����
								System.out.print("Room name to modify name : ");
								String orgRoomName = sc.next();
								System.out.print("New room name : ");
								String changedRoomName = sc.next();
								try {
									mg.changeRoomName(orgRoomName, changedRoomName);
								}
								catch(IndexOutOfBoundsException ioe) //���̸��� �������� �ʾ� �ε��� ���� -1�� ���
								{
									System.out.println("There is no room named " + orgRoomName);
								}
								System.out.println(orgRoomName + " is changed to " + changedRoomName + ".");
								break;
								
							case 2: // �� ���� �ο� ����
								System.out.print("Room name to modify capacity : ");
								roomName = sc.next();
								int orgCapacity = -1; //���� �ο�
								int changedCapacity = -1; //����� �ο�
								
								try {
									orgCapacity = mg.howManyCapacity(roomName);
									System.out.println("The capacity of" + roomName + " before modify is "
														+ orgCapacity + ".");
									System.out.print("How many do you want to change? : ");
									changedCapacity = sc.nextInt();
									
									mg.changeCapacity(roomName, changedCapacity);
								}
								catch(IndexOutOfBoundsException ioe) //���̸��� �������� �ʾ� �ε��� ���� -1�� ���
								{
									System.out.println("There is no room named " + roomName);
								}
								System.out.println("The capacity of " + roomName + " is changed to "
													+ changedCapacity + ".");
								break;
								
							case 3: // �ð� �� ���� ����
								System.out.print("Room name to modify price : ");
								roomName = sc.next();
								int orgPricePerHour = -1; //���� ����
								int changedPricePerHour = -1; //����� ����
								
								try {
									orgPricePerHour = mg.howMuchPrice(roomName);
									System.out.println("The price of " + roomName + " before modify is "
														+ orgPricePerHour + ".");
									System.out.print("How much do you want to change? : ");
									changedPricePerHour = sc.nextInt();
									
									mg.changePricePerHour(roomName, changedPricePerHour);
								}
								catch(IndexOutOfBoundsException ioe) //���̸��� �������� �ʾ� �ε��� ���� -1�� ���
								{
									System.out.println("There is no room named " + roomName);
								}
								System.out.println("The price per hour of " + roomName + " is changed to "
													+ changedPricePerHour + ".");
								break;
								
							case 4: // ���� �޴��� ���ư���
								break;
								
							case 5: // �����ϱ�
								System.out.println("Exit the Program.");
								managerMode = false;
								systemExit = true;
								break;
							}
							break; // case3�� break��
							
						case 4: // ��ü �� ��ȸ
							System.out.println("Name\tCapacity   Price   Using   User   Phone");
							System.out.println("-------------------------------------------------");
							
							ArrayList<Room> roomTable = new ArrayList<Room>();
							
							roomTable = mg.checkAllCreatedRoom(); // ��ü �� ��ȸ
							if(roomTable.isEmpty()) // roomTable�� ����� ��
							{
								System.out.println("There are no room created.");
							}
							else
							{
								for(Room room:roomTable)
								{
									System.out.println(room.getRoomName() + "| \t  " 
											+ room.getCapacity() + "\t   "
											+ room.getPricePerHour() + "\t   " 
											+ room.getUsing() + "\t   "
											+ room.getUser().getUserName() + "\t  "
											+ room.getUser().getUserPhoneNum());
								}
							}
							break;
							
						case 5: // ���� �޴��� ���ư���
							managerMode = false;
							break;
							
						case 6: // ����
							systemExit = true;
							managerMode = false;
							break;
							
						default:
							break;
						}
					} catch (java.util.InputMismatchException ime) // ���ڰ� �ƴ� ���� �Է����� �� ����ó��
					{
						System.out.println("You have entered an invalid value.\n");
						sc.nextLine();
					}
				}
				System.out.println();
			}

			try
			{
				roomInfoOut = new ObjectOutputStream(new FileOutputStream(roomInfoFile));
				// �� ���� ���Ͽ� ����
				mg.writeRoomInfo(roomInfoOut);
			} catch (FileNotFoundException fnfe) {
				System.out.println("The file could not be found.");
			} catch (IOException ioe) {
				System.out.println("The file cannot be output.");
			} finally {
				try {
					roomInfoOut.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			sc.close();
		}

}
