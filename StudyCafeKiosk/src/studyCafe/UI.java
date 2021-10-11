package studyCafe;
import java.io.*;
import java.util.Scanner;

public class UI 
{
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in); // scanner객체 생성
		Management mg = new Management("manager001"); // management객체 생성
		FileOutputStream roomInfoOut = null;
		FileInputStream roomInfoIn = null;
		
		try {
				// 방 정보를 읽어올 파일 불러오기
				roomInfoIn = new FileInputStream("StudyCafeRoomInfo.txt");
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
							System.out.println("ID가 일치하지 않습니다.");
						}
						break;
					case 3:
						systemExit = true;
						break;
					default:
						System.out.println("1~3중에 선택하세요.\n");
						break;
					}
				} catch (java.util.InputMismatchException ime) // 숫자가 아닌 값을 입력했을 때 예외처리
				{
					System.out.println("잘못된 값을 입력하였습니다. 1~3중에 선택하세요.\n");
					sc.nextLine(); // 무한 루핑을 막기 위해 입력
				}

				// User모드-------------------------------------------------------------------------------------
				while (userMode) {
					System.out.println();
					System.out.println("-----사용자 메뉴-----");
					System.out.println("1. 빈방 검색");
					System.out.println("2. 입실");
					System.out.println("3. 퇴실");
					System.out.println("4. 이전 메뉴로 돌아가기");
					System.out.println("5. 종료");
					System.out.print("=> ");
					try {
						int answerUserMenu = sc.nextInt();

						switch (answerUserMenu) {
						case 1: // 빈방 검색
							try {
								System.out.print("수용 인원 : "); // 검색 할 수용인원 입력하기
								int capacity = sc.nextInt();
								mg.searchEmptyRoomByCapacity(capacity);
							} catch (java.util.InputMismatchException ime) {
								System.out.print("잘못된 입력 값입니다. 숫자를 입력하세요.");
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						case 2: // 입실
							// 사용자 정보 입력 받기
							System.out.print("사용자 이름 : ");
							String userName = sc.next();
							System.out.print("사용자 전화번호 : ");
							String userPhoneNum = sc.next();
							User user = new User(userName, userPhoneNum);
							// 입실할 룸 정보 입력 받기
							System.out.print("입실할 방 이름 : ");
							String roomName = sc.next();
							// 입실하기
							try {
								mg.checkIn(roomName, user);
								System.out.println("입실되었습니다.");
							} catch (Exception e) {
								System.out.println("방을 찾을 수 없습니다.");
							}
							break;
						case 3: // 퇴실
							// 체크아웃하는 룸의 사용자 정보 조회
							System.out.print("퇴실할 방 이름 : ");
							roomName = sc.next();
							System.out.print("사용자 이름 : ");
							userName = sc.next();
							System.out.print("사용자 전화번호 : ");
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
								System.out.println("지불 금액 : " + userPay);
								System.out.print("결제하시겠습니까?\n(동의하면 y 또는 Y를 입력하세요) : ");
								String payAnswer = sc.next();
								if (payAnswer.equals("y") || payAnswer.equals("Y")) {
									mg.checkOut(roomName);
									System.out.println("퇴실되었습니다.");
								} else
									System.out.println("퇴실이 취소되었습니다.");
							} else
								System.out.println("잘못된 사용자 정보입니다.");
							break;
						case 4: // 이전 메뉴로 돌아가기
							userMode = false;
							break;
						case 5: // 종료
							System.out.println("이용해주셔서 감사합니다.\n안녕히가세요.");
							systemExit = true;
							userMode = false;
							break;
						default:
							System.out.println("1~5중에 선택하세요.\n");
							break;
						}
					} catch (java.util.InputMismatchException ime) // 숫자가 아닌 값을 입력했을 때 예외처리
					{
						System.out.println("잘못된 값을 입력하였습니다. 1~3중에 선택하세요.\n");
					}
				}
				System.out.println();

				// Manager모드-------------------------------------------------------------------------------------
				while (managerMode) {
					System.out.println();
					System.out.println("-----Manager Menu-----");
					System.out.println("1. Create a room"); // 방 생성
					System.out.println("2. Delete a room"); // 방 삭제
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
								System.out.print("생성할 방 이름 : ");
								String roomName = sc.next();
								System.out.print("생성할 방 수용 인원 : ");
								int capacity = sc.nextInt();
								System.out.print("생성할 방 시간 당 비용 : ");
								int pricePerHour = sc.nextInt();
								// 방 생성
								mg.createRoom(roomName, capacity, pricePerHour);
							} catch (java.util.InputMismatchException ime) // int형 데이터에 다른 값이 입력되었을 경우 예외처리
							{
								System.out.println("잘못된 값을 입력하였습니다. 숫자를 입력하세요.");
								sc.nextLine();
							}

							break;
						case 2: // 방 삭제
							System.out.print("삭제할 방 이름 : ");
							String roomName = sc.next();
							mg.removeRoom(roomName);
							System.out.println(roomName + "룸을 삭제하였습니다.");
							break;
						case 3: // 방 수정
							// 수정 메뉴
							System.out.println("----수정할 내용----");
							System.out.println("1. 방 이름");
							System.out.println("2. 수용 인원");
							System.out.println("3. 시간 당 가격");
							System.out.println("4. 이전 메뉴로 돌아가기");
							System.out.println("5. 종료");
							System.out.print("=> ");
							int fixMenu = sc.nextInt();

							switch (fixMenu) // 예외 처리는 아직 구현하지 않음.
							{
							case 1: // 방 이름 수정
								System.out.println("수정 전 방 이름 : ");
								String orgRoomName = sc.next();
								System.out.println("방 이름을 무엇으로 변경하시겠습니까? : ");
								String changedRoomName = sc.next();
								mg.changeRoomName(orgRoomName, changedRoomName);
								System.out.println(orgRoomName + "이 " + changedRoomName + "으로 방 이름이 변경되었습니다.");
								break;
							case 2: // 방 수용 인원 수정
								System.out.println("최대 수용 인원을 수정할 방 이름 : ");
								roomName = sc.next();
								int orgCapacity = mg.howManyCapacity(roomName);
								System.out.println(roomName + "의 수정 전 최대 수용 인원은 " + orgCapacity + "입니다.");
								System.out.println("최대 수용 인원을 몇 명으로 변경하시겠습니까? : ");
								int changedCapacity = sc.nextInt();
								mg.changeCapacity(roomName, changedCapacity);
								System.out.println(roomName + "의 최대 수용 인원이 " + changedCapacity + "으로 변경되었습니다.");
								break;
							case 3: // 시간 당 가격 수정
								System.out.println("시간 당 가격을 수정할 방 이름 : ");
								roomName = sc.next();
								int orgPricePerHour = mg.howMuchPrice(roomName);
								System.out.println(roomName + "의 수정 전 비용은 " + orgPricePerHour + "입니다.");
								System.out.println("얼마로 변경하시겠습니까? : ");
								int changedPricePerHour = sc.nextInt();
								mg.changePricePerHour(roomName, changedPricePerHour);
								System.out.println(roomName + "의 시간 당 비용이 " + changedPricePerHour + "으로 변경되었습니다.");
								break;
							case 4: // 이전 메뉴로 돌아가기
								break;
							case 5: // 종료하기
								System.out.println("프로그램을 종료합니다.");
								managerMode = false;
								systemExit = true;
								break;
							}
							break; // case3의 break문
						case 4: // 전체 방 조회
							System.out.println("방 이름\t 인원\t 가격\t 사용여부");
							System.out.println("---------------------------------");
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
						System.out.println("잘못된 값을 입력하였습니다. 1~3중에 선택하세요.\n");
						sc.nextLine();
					}
				}
				System.out.println();
			}

			try {
				// 방 정보를 저장할 파일 생성
				roomInfoOut = new FileOutputStream("studyCafeRoomInfo.txt");
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
