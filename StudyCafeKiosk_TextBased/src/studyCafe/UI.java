package studyCafe;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UI
{
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in); // scanner객체 생성
		Management mg = new Management("manager001"); // management객체 생성
		File roomInfoFile = new File("studyCafeRoom.dat");
		File salesInfoFile = new File("studyCafeSales.dat"); 
		ObjectOutputStream roomInfoOut = null;
		ObjectInputStream roomInfoIn = null;
		ObjectOutputStream salesInfoOut = null;
		ObjectInputStream salesInfoIn = null;
		
		if(!roomInfoFile.exists()) //방 정보 파일이 없으면
		{
			try
			{
				// 방 정보를 저장할 파일 생성
				roomInfoOut = new ObjectOutputStream(new FileOutputStream(roomInfoFile));
			}
			catch (FileNotFoundException fnfe) {
				System.out.println("The Room file could not be found.");
			}
		}
		if(!salesInfoFile.exists()) //매출 정보 파일이 없으면
		{
			try
			{
				//매출 정보를 저장할 파일 생성
				salesInfoOut = new ObjectOutputStream(new FileOutputStream(salesInfoFile));
			}
			catch (FileNotFoundException fnfe) {
				System.out.println("The Sales file could not be found.");
			}
			
		}
		
		
		try {
				// 방 정보를 읽어올 파일 불러오기
				roomInfoIn = new ObjectInputStream(new FileInputStream(roomInfoFile));
				salesInfoIn = new ObjectInputStream(new FileInputStream(salesInfoFile));
				mg.readRoomInfo(roomInfoIn);
				mg.readSalesInfo(salesInfoIn);
				
				int numberOfRooms = mg.getRoomTableSize();
				System.out.println("Currently, " + numberOfRooms + " rooms are created.");
				System.out.println();
				
			} catch (FileNotFoundException fnfe) {
				System.out.println("The file could not be found."); // 파일을 찾을 수 없음
				System.out.println();
			} catch (EOFException eofe) {
				System.out.println("The file is empty"); // 파일이 비어있음
				System.out.println();
			} catch (IOException ioe) {
				System.out.println("The file cannot be read."); // 파일을 읽어올 수 없음
				System.out.println();
			} catch (ClassNotFoundException cnfe) {
				System.out.println("The class does not exist.");
			} finally {
				try {
					roomInfoIn.close();
					salesInfoIn.close();
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
				} catch (InputMismatchException ime) // 숫자가 아닌 값을 입력했을 때 예외처리
				{
					System.out.println("You have entered an invalid value.\n");
					sc.nextLine(); // 무한 루핑을 막기 위해 입력
				}

				// User모드-------------------------------------------------------------------------------------
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
					catch (java.util.InputMismatchException ime) // 숫자가 아닌 값을 입력했을 때 예외처리
					{
						System.out.println("You have enetered an invalid value.\n");
						sc.nextLine(); // 무한 루핑을 막기 위해 입력
					}
					
						switch (answerUserMenu) 
						{
						case 1: // 빈방 검색
							//빈 방 리스트를 저장할 변수
							ArrayList<Room> emptyRoomTable = new ArrayList<Room>();
							int capacity = -1; //검색할 인원
							
							try 
							{
								System.out.print("The number of users : "); // 검색 할 수용인원 입력하기
								capacity = sc.nextInt();
							} 
							catch (InputMismatchException ime) 
							{
								System.out.println("You have entered an invalid value.\nPlease enter the number.");
							}
							
							//수용 인원(Capacity)으로 찾은 빈 방 리스트 출력하기
							emptyRoomTable = mg.searchEmptyRoomByCapacity(capacity);
							System.out.println("Name\t Capacity\tPrice");
							System.out.println("---------------------------------");
							
							if(emptyRoomTable.isEmpty()) //빈 방 리스트가 비었을 때
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
							
						case 2: // 입실
							String userName = ""; //사용자이름
							String userPhoneNum = ""; //사용자 전화번호
							String roomName = ""; //입실할 방 이름
							
							try
							{
								// 사용자 정보 입력 받기
								System.out.print("User Name : ");
								userName = sc.next();
								System.out.print("User Phone number : ");
								userPhoneNum = sc.next();
							}
							catch(InputMismatchException ime) //사용자 정보 입력 관련 예외처린
							{
								System.out.println("You have enetered an invalid value.");
								sc.nextLine();
							}
							
							User user = new User(userName, userPhoneNum); //User객체 생성
							
							try
							{
								// 입실할 룸 정보 입력 받기
								System.out.print("Name of the room to check-in : ");
								roomName = sc.next();
							}
							catch(InputMismatchException ime)
							{
								System.out.println("You have enetered an invalid value.");
								sc.nextLine();
							} 
							
							// 입실하기
							try 
							{
								if(mg.checkIn(roomName, user))
								{
									Room room = mg.getRoom(roomName);
									//시작 시간 포맷 설정
									SimpleDateFormat dateFormat = new SimpleDateFormat("MM월 dd일 aa hh시 mm분 ss초");
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
							
						case 3: // 퇴실
							// 체크아웃하는 룸의 사용자 정보 조회
							System.out.print("Name of the room to check-out : ");
							roomName = sc.next();
							boolean checkUser = false; //사용자 정보 일치 여부를 저장하는 변수
							try
							{
								//방이 있는지 검사
								mg.searchRoom(roomName);
								
								//사용자 정보 입력
								System.out.print("User Name : ");
								userName = sc.next();
								System.out.print("User Phone number : ");
								userPhoneNum = sc.next();
								
								//사용자 정보 검사
								checkUser = mg.isRightCheckOutUser(roomName, userName, userPhoneNum);
							}
							catch (IndexOutOfBoundsException ioe) 
							{
								System.out.println("There is no room named " + roomName);
							}
							
							//퇴실 및 결제
							int userPay = -1; //결제할 비용
							if (checkUser)
							{
								try
								{
									Room room = mg.getRoom(roomName);
									//시작 시간 포맷 설정
									SimpleDateFormat dateFormat = new SimpleDateFormat("MM월 dd일 aa hh시 mm분 ss초");
									String checkInTime = dateFormat.format(room.getCheckInTime().getTime());
									String checkOutTime = dateFormat.format(room.getCheckOutTime().getTime());
									
									System.out.println();
									System.out.println("Check-in time : " + checkInTime);
									System.out.println("Check-out time : " + checkOutTime);
									System.out.println();
									
									userPay = mg.pay(roomName);
								}
								catch (IndexOutOfBoundsException ioe) //해당 방 이름이 없을 경우
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
									catch (IndexOutOfBoundsException ioe) //해당 방 이름이 없을 경우
									{
										System.out.println("There is no room named " + roomName);
									}
									System.out.println();
									System.out.println("Check-out success.");
									mg.addSalesList(roomName); //매출 정보 저장
								} 
								else //결제를 승락하지 않은 경우
								{
									System.out.println();
									System.out.println("Check-out is been canceled.");
								}
							} 
							else //사용자 정보가 일치하지 않을 경우
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
					
				}
				System.out.println();

				// Manager모드-------------------------------------------------------------------------------------
				while (managerMode) {
					System.out.println();
					System.out.println("-----Manager Menu-----");
					System.out.println("1. Create a room"); // 방 생성
					System.out.println("2. Remove a room"); // 방 삭제
					System.out.println("3. Revise room info"); // 방 수정
					System.out.println("4. Check the entire rooms"); // 전체 방 조회
					System.out.println("5. Inquire sales infromation"); //매출 정보 조회
					System.out.println("6. Previous menu"); // 이전 메뉴로 돌아가기
					System.out.println("7. Exit");
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
								
								//방 이름 중복 검사
								if(!mg.isRoomExist(roomName))
								{
									// 방 생성
									mg.createRoom(roomName, capacity, pricePerHour);
									System.out.println("Successfully created! : " + roomName );
								}
								else
								{
									System.out.println(roomName + " is already existed.");
								}
							} catch (InputMismatchException ime) //다른type의 값이 입력되었을 경우 예외처리
							{
								System.out.println("You have entered an invalid value.");
								sc.nextLine();
							}

							break;
						case 2: // 방 삭제
							System.out.print("Room name to remove : ");
							String roomName = sc.next();
							
							if (mg.removeRoom(roomName)) // 해당 방 이름이 존재할 때
							{
								if (!mg.searchRoom(roomName).getUsing()) // 사용 중이지 않을 때
								{
									System.out.println("Successfully removed! : " + roomName);
								} else // 사용 중일 때
								{
									System.out.println("This room is using.");
								}
							} else // 해당 방 이름이 존재하지 않을 때
							{
								System.out.println("There is no room named " + roomName);
							}
							
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
								System.out.print("Room name to modify name : ");
								String orgRoomName = sc.next();
								System.out.print("New room name : ");
								String changedRoomName = sc.next();
								try {
									mg.changeRoomName(orgRoomName, changedRoomName);
								}
								catch(IndexOutOfBoundsException ioe) //방이름이 존재하지 않아 인덱스 값이 -1인 경우
								{
									System.out.println("There is no room named " + orgRoomName);
								}
								System.out.println(orgRoomName + " is changed to " + changedRoomName + ".");
								break;
								
							case 2: // 방 수용 인원 수정
								System.out.print("Room name to modify capacity : ");
								roomName = sc.next();
								int orgCapacity = -1; //원래 인원
								int changedCapacity = -1; //변경된 인원
								
								try {
									orgCapacity = mg.howManyCapacity(roomName);
									System.out.println("The capacity of" + roomName + " before modify is "
														+ orgCapacity + ".");
									System.out.print("How many do you want to change? : ");
									changedCapacity = sc.nextInt();
									
									mg.changeCapacity(roomName, changedCapacity);
								}
								catch(IndexOutOfBoundsException ioe) //방이름이 존재하지 않아 인덱스 값이 -1인 경우
								{
									System.out.println("There is no room named " + roomName);
								}
								System.out.println("The capacity of " + roomName + " is changed to "
													+ changedCapacity + ".");
								break;
								
							case 3: // 시간 당 가격 수정
								System.out.print("Room name to modify price : ");
								roomName = sc.next();
								int orgPricePerHour = -1; //원래 가격
								int changedPricePerHour = -1; //변경된 가격
								
								try {
									orgPricePerHour = mg.howMuchPrice(roomName);
									System.out.println("The price of " + roomName + " before modify is "
														+ orgPricePerHour + ".");
									System.out.print("How much do you want to change? : ");
									changedPricePerHour = sc.nextInt();
									
									mg.changePricePerHour(roomName, changedPricePerHour);
								}
								catch(IndexOutOfBoundsException ioe) //방이름이 존재하지 않아 인덱스 값이 -1인 경우
								{
									System.out.println("There is no room named " + roomName);
								}
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
							
							ArrayList<Room> roomList = new ArrayList<Room>();
							
							roomList = mg.getRoomList(); //방 리스트 받아오기
							if(roomList.isEmpty()) // roomList가 비었을 때
							{
								System.out.println("There are no room created.");
							}
							else
							{
								System.out.println("[ All Rooms Information ]");
								System.out.println("Name\tCapacity   Price   Using   User   Phone");
								System.out.println("-------------------------------------------------");
								
								for(Room room:roomList)
								{
									
									System.out.print(room.getRoomName() + "| \t  " 
											+ room.getCapacity() + "\t   "
											+ room.getPricePerHour() + "\t   " 
											+ room.getUsing() + "   ");
									if(room.getUsing()) //사용중이면
									{
											System.out.print(room.getUser().getUserName() + "\t  "
											+ room.getUser().getUserPhoneNum());
									}
									System.out.println();
								}
							}
							break;
							
						case 5: //매출 정보 보기
							
							ArrayList<Sales> salesList = new ArrayList<Sales>();
							
							//날짜 정보 포맷 설정
							SimpleDateFormat dateFormat = new SimpleDateFormat("MM월 dd일 aa hh시 mm분 ss초");
							
							salesList = mg.getSalesList(); //매출 리스트 받아오기
							if(salesList.isEmpty()) //salesList가 비었을 때
							{
								System.out.println("There are no sales information registered.");
							}
							else //salesList에 정보가 있을 때
							{
								System.out.println("[ Room sales Infomation ]");
								System.out.println("Payment Date    \t   User Name    User Phone    Used Time    Bill");
								System.out.println("------------------------------------------------------------------------");
								
								for(Sales sales:salesList)
								{
									//결제일 날짜 포맷에 맞도록 출력
									String paymentDate = dateFormat.format(sales.getPaymentDate().getTime());
									System.out.println(paymentDate + "   "
											+ sales.getPayedUser().getUserName() + "\t\t"
											+ sales.getPayedUser().getUserPhoneNum() + "\t\t"
											+ sales.getUsedTime() + "\t   "
											+ sales.getPayedBill());
								}
							}
							break;
							
						case 6: // 이전 메뉴로 돌아가기
							managerMode = false;
							break;
							
						case 7: // 종료
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

			//파일에 방정보&매출정보 저장
			try
			{
				roomInfoOut = new ObjectOutputStream(new FileOutputStream(roomInfoFile));
				salesInfoOut = new ObjectOutputStream(new FileOutputStream(salesInfoFile));
				// 방 정보 파일에 쓰기
				mg.writeRoomInfo(roomInfoOut);
				//매출 정보 파일에 쓰기
				mg.writeSalesInfo(salesInfoOut);
				
			} catch (FileNotFoundException fnfe) {
				System.out.println("The file could not be found.");
			} catch (IOException ioe) {
				System.out.println("The file cannot be output.");
			} finally {
				try {
					roomInfoOut.close();
					salesInfoOut.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			sc.close();
		}

}
