package studyCafe;
import java.io.*;
import java.util.Scanner;

public class UI 
{
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in); // scanner객체 생성
		Management mg = new Management("manager001"); // management객체 생성
		File roomInfoFile = new File("studyCafeRoom.txt");
		FileOutputStream roomInfoOut = null;
		FileInputStream roomInfoIn = null;
		
		if(!roomInfoFile.exists())
		{
			try
			{
				// 방 정보를 저장할 파일 생성
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
				// 방 정보를 읽어올 파일 불러오기
				roomInfoIn = new FileInputStream("studyCafeRoom.txt");
				mg.readRoomInfo(roomInfoIn);

			} catch (FileNotFoundException fnfe) {
				System.out.println("The file could not be found."); // 파일을 찾을 수 없음
				System.out.println();
			} catch (EOFException eofe) {
				System.out.println("There is no room created."); // 파일이 비어있음
				System.out.println();
			} catch (IOException ioe) {
				System.out.println("The file cannot be read."); // 파일을 읽어올 수 없음
				System.out.println();
			} finally {
				try {
					roomInfoIn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			boolean systemExit = false; // 시스템을 종료 조건의 변수
			while (!systemExit) {
				System.out.println("-------MODE-------");
				System.out.println("1. User Mode");
				System.out.println("2. Manager Mode");
				System.out.println("3. Exit");
				System.out.print("=> ");

				boolean userMode = false; // 사용자 모드 종료 조건의 변수
				boolean managerMode = false; // 관리자 모드 종료 조건의 변수

				try {
					int answerMode = sc.nextInt();
					sc.nextLine();

					switch (answerMode) {
					case 1:
						userMode = true;
						break;
					case 2:
						System.out.print("Manager ID : "); // manager 아이디 입력
						String managerID = sc.next();
						if (mg.checkManagerID(managerID)) // manager 아이디가 일치하면 managerMode로 들어갈 수 있음
						{
							managerMode = true;
						} else // manager 아이디 불일치
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
				} catch (java.util.InputMismatchException ime) // 숫자가 아닌 값을 입력했을 때 예외처리
				{
					System.out.println("You have entered an invalid value.\n");
					sc.nextLine(); // 무한 루핑을 막기 위해 입력
				}

				// User모드-------------------------------------------------------------------------------------
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
						case 1: // 빈방 검색
							try {
								System.out.print("The number of users : "); // 검색 할 수용인원 입력하기
								int capacity = sc.nextInt();
								mg.searchEmptyRoomByCapacity(capacity);
							} catch (java.util.InputMismatchException ime) {
								System.out.println("You have entered an invalid value.\nPlease enter the number.");
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						case 2: // 입실
							// 사용자 정보 입력 받기
							System.out.print("User Name : ");
							String userName = sc.next();
							System.out.print("User Phone number : ");
							String userPhoneNum = sc.next();
							User user = new User(userName, userPhoneNum);
							// 입실할 룸 정보 입력 받기
							System.out.print("Name of the room to check-in : ");
							String roomName = sc.next();
							// 입실하기
							try {
								mg.checkIn(roomName, user);
								System.out.println("Check-in success.");
							} catch (Exception e) {
								System.out.println("Cannot find the room.");
							}
							break;
						case 3: // 퇴실
							// 체크아웃하는 룸의 사용자 정보 조회
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
						case 4: // 이전 메뉴로 돌아가기
							userMode = false;
							break;
						case 5: // 종료
							System.out.println("Thank you for using it.\nGoodbye!");
							systemExit = true;
							userMode = false;
							break;
						default:
							System.out.println("Choose between 1 and 5.\n");
							break;
						}
					} catch (java.util.InputMismatchException ime) // 숫자가 아닌 값을 입력했을 때 예외처리
					{
						System.out.println("You have enetered an invalid value.\n");
					}
				}
				System.out.println();

				// Manager모드-------------------------------------------------------------------------------------
				while (managerMode) {
					System.out.println();
					System.out.println("-----Manager Menu-----");
					System.out.println("1. Create a room"); // 방 생성
					System.out.println("2. Remove a room"); // 방 삭제
					System.out.println("3. Revise room info"); // 방 수정
					System.out.println("4. Check the entire room"); // 전체 방 조회
					System.out.println("5. Previous menu"); // 이전 메뉴로 돌아가기
					System.out.println("6. Exit");
					System.out.print("=> ");
					try {
						int answerManagerMenu = sc.nextInt();

						switch (answerManagerMenu) {
						case 1: // 방 생성
							try {
								// 생성할 방 정보 입력
								System.out.print("Room name to create : ");
								String roomName = sc.next();
								System.out.print("Room capacity : ");
								int capacity = sc.nextInt();
								System.out.print("Price per hour : ");
								int pricePerHour = sc.nextInt();
								// 방 생성
								mg.createRoom(roomName, capacity, pricePerHour);
							} catch (java.util.InputMismatchException ime) // int형 데이터에 다른 값이 입력되었을 경우 예외처리
							{
								System.out.println("You have entered an invalid value.");
								sc.nextLine();
							}

							break;
						case 2: // 방 삭제
							System.out.print("Room name to remove : ");
							String roomName = sc.next();
							mg.removeRoom(roomName);
							System.out.println("Successfully remove a " + roomName );
							break;
						case 3: // 방 수정
							// 수정 메뉴
							System.out.println("-----Modify-----");
							System.out.println("1. Room name");
							System.out.println("2. Room capacity");
							System.out.println("3. Price per hour");
							System.out.println("4. Previous menu");
							System.out.println("5. Exit");
							System.out.print("=> ");
							int fixMenu = sc.nextInt();

							switch (fixMenu) // 예외 처리는 아직 구현하지 않음.
							{
							case 1: // 방 이름 수정
								System.out.println("Room name to modify name : ");
								String orgRoomName = sc.next();
								System.out.println("New room name : ");
								String changedRoomName = sc.next();
								mg.changeRoomName(orgRoomName, changedRoomName); //수정 : try-catch문
								System.out.println(orgRoomName + " is changed to " + changedRoomName + ".");
								break;
							case 2: // 방 수용 인원 수정
								System.out.println("Room name to modify capacity : ");
								roomName = sc.next();
								int orgCapacity = mg.howManyCapacity(roomName);
								System.out.println("The capacity of" + roomName + " before modify is " + orgCapacity + ".");
								System.out.println("How many do you want to change? : ");
								int changedCapacity = sc.nextInt();
								mg.changeCapacity(roomName, changedCapacity);
								System.out.println("The capacity of " + roomName + " is changed to " + changedCapacity + ".");
								break;
							case 3: // 시간 당 가격 수정
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
							case 4: // 이전 메뉴로 돌아가기
								break;
							case 5: // 종료하기
								System.out.println("Exit the Program.");
								managerMode = false;
								systemExit = true;
								break;
							}
							break; // case3의 break문
						case 4: // 전체 방 조회
							System.out.println("Name\t Capacity\tPrice\t Using");
							System.out.println("----------------------------------------");
							try {
								mg.checkAllCreatedRoom(); // 전체 방 조회
							} catch (Exception e) {
								System.out.println(e.getMessage()); // 조회할 방이 없을 때 예외처리
							}
							break;
						case 5: // 이전 메뉴로 돌아가기
							managerMode = false;
							break;
						case 6: // 종료
							systemExit = true;
							managerMode = false;
							break;
						default:
							break;
						}
					} catch (java.util.InputMismatchException ime) // 숫자가 아닌 값을 입력했을 때 예외처리
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
				// 방 정보 파일에 쓰기
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
