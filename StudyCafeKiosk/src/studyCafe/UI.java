package studyCafe;
import java.io.*;
import java.util.Scanner;

public class UI 
{
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in); // scanner��ü ����
		Management mg = new Management("manager001"); // management��ü ����
		File roomInfoFile = new File("studyCafeRoom.txt");
		FileOutputStream roomInfoOut = null;
		FileInputStream roomInfoIn = null;
		
		if(!roomInfoFile.exists())
		{
			try
			{
				// �� ������ ������ ���� ����
				roomInfoOut = new FileOutputStream("studyCafeRoom.txt");
			}
			catch (FileNotFoundException fnfe) {
				System.out.println("The file could not be found.");
			} 
			catch (IOException ioe) {
				System.out.println("The file cannot be read.");
			} 
		}
		
		try {
				// �� ������ �о�� ���� �ҷ�����
				roomInfoIn = new FileInputStream("studyCafeRoom.txt");
				mg.readRoomInfo(roomInfoIn);

			} catch (FileNotFoundException fnfe) {
				System.out.println("The file could not be found."); // ������ ã�� �� ����
				System.out.println();
			} catch (EOFException eofe) {
				System.out.println("There is no room created."); // ������ �������
				System.out.println();
			} catch (IOException ioe) {
				System.out.println("The file cannot be read."); // ������ �о�� �� ����
				System.out.println();
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
				} catch (java.util.InputMismatchException ime) // ���ڰ� �ƴ� ���� �Է����� �� ����ó��
				{
					System.out.println("You have entered an invalid value.\n");
					sc.nextLine(); // ���� ������ ���� ���� �Է�
				}

				// User���-------------------------------------------------------------------------------------
				while (userMode) {
					System.out.println();
					System.out.println("------User Menu------");
					System.out.println("1. Search empty room");
					System.out.println("2. Check-in");
					System.out.println("3. Check-out");
					System.out.println("4. Previous Menu");
					System.out.println("5. Exit");
					System.out.print("=> ");
					try {
						int answerUserMenu = sc.nextInt();

						switch (answerUserMenu) {
						case 1: // ��� �˻�
							try {
								System.out.print("The number of users : "); // �˻� �� �����ο� �Է��ϱ�
								int capacity = sc.nextInt();
								mg.searchEmptyRoomByCapacity(capacity);
							} catch (java.util.InputMismatchException ime) {
								System.out.println("You have entered an invalid value.\nPlease enter the number.");
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						case 2: // �Խ�
							// ����� ���� �Է� �ޱ�
							System.out.print("User Name : ");
							String userName = sc.next();
							System.out.print("User Phone number : ");
							String userPhoneNum = sc.next();
							User user = new User(userName, userPhoneNum);
							// �Խ��� �� ���� �Է� �ޱ�
							System.out.print("Name of the room to check-in : ");
							String roomName = sc.next();
							// �Խ��ϱ�
							try {
								mg.checkIn(roomName, user);
								System.out.println("Check-in success.");
							} catch (Exception e) {
								System.out.println("Cannot find the room.");
							}
							break;
						case 3: // ���
							// üũ�ƿ��ϴ� ���� ����� ���� ��ȸ
							System.out.print("Name of the room to check-out : ");
							roomName = sc.next();
							System.out.print("User Name : ");
							userName = sc.next();
							System.out.print("User Phone number : ");
							userPhoneNum = sc.next();

							boolean checkUser = false;
							try {
								checkUser = mg.isRightCheckOutUser(roomName, userName, userPhoneNum);
							} catch (Exception e) {
								e.getMessage();
							}

							if (checkUser) {
								mg.showCheckOutTime(roomName);
								int userPay = mg.pay(roomName);
								System.out.println("Payment amount : " + userPay);
								System.out.print("Do you want to pay?\n(If you agree, please enter 'y' or 'Y'.) : ");
								String payAnswer = sc.next();
								if (payAnswer.equals("y") || payAnswer.equals("Y")) {
									mg.checkOut(roomName);
									System.out.println("Check-out success.");
								} else
									System.out.println("Check-out is been canceled.");
							} else
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
					} catch (java.util.InputMismatchException ime) // ���ڰ� �ƴ� ���� �Է����� �� ����ó��
					{
						System.out.println("You have enetered an invalid value.\n");
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
					System.out.println("4. Check the entire room"); // ��ü �� ��ȸ
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
								// �� ����
								mg.createRoom(roomName, capacity, pricePerHour);
							} catch (java.util.InputMismatchException ime) // int�� �����Ϳ� �ٸ� ���� �ԷµǾ��� ��� ����ó��
							{
								System.out.println("You have entered an invalid value.");
								sc.nextLine();
							}

							break;
						case 2: // �� ����
							System.out.print("Room name to remove : ");
							String roomName = sc.next();
							mg.removeRoom(roomName);
							System.out.println("Successfully remove a " + roomName );
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
								System.out.println("Room name to modify name : ");
								String orgRoomName = sc.next();
								System.out.println("New room name : ");
								String changedRoomName = sc.next();
								mg.changeRoomName(orgRoomName, changedRoomName); //���� : try-catch��
								System.out.println(orgRoomName + " is changed to " + changedRoomName + ".");
								break;
							case 2: // �� ���� �ο� ����
								System.out.println("Room name to modify capacity : ");
								roomName = sc.next();
								int orgCapacity = mg.howManyCapacity(roomName);
								System.out.println("The capacity of" + roomName + " before modify is " + orgCapacity + ".");
								System.out.println("How many do you want to change? : ");
								int changedCapacity = sc.nextInt();
								mg.changeCapacity(roomName, changedCapacity);
								System.out.println("The capacity of " + roomName + " is changed to " + changedCapacity + ".");
								break;
							case 3: // �ð� �� ���� ����
								System.out.println("Room name to modify price : ");
								roomName = sc.next();
								int orgPricePerHour = mg.howMuchPrice(roomName);
								System.out.println("The price of " + roomName + " before modify is " + orgPricePerHour + ".");
								System.out.println("How much do you want to change? : ");
								int changedPricePerHour = sc.nextInt();
								mg.changePricePerHour(roomName, changedPricePerHour);
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
							System.out.println("Name\t Capacity\tPrice\t Using");
							System.out.println("----------------------------------------");
							try {
								mg.checkAllCreatedRoom(); // ��ü �� ��ȸ
							} catch (Exception e) {
								System.out.println(e.getMessage()); // ��ȸ�� ���� ���� �� ����ó��
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
				roomInfoOut = new FileOutputStream("studyCafeRoom.txt");
				// �� ���� ���Ͽ� ����
				mg.writeRoomInfo(roomInfoOut);
			} catch (FileNotFoundException fnfe) {
				System.out.println("The file could not be found.");
			} catch (IOException ioe) {
				System.out.println("The file cannot be read.");
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
