package studyCafe;

import java.io.*;

public class GUI{
	public static void main(String[] args) throws Exception {
		
		Management mg = new Management("Manager001");
		
		//모드 프레임 생성
		ModeFrame modeFrame = new ModeFrame(mg);		
		
		//파일 읽어오기
		File roomInfoFile = new File("studyCafeRoom.dat");
		File salesInfoFile = new File("studyCafeSales.dat");
		File ownerIDFile = new File("studyCafeOwnerID.dat");
		ObjectOutputStream roomInfoOut = null;
		ObjectInputStream roomInfoIn = null;
		ObjectOutputStream salesInfoOut = null;
		ObjectInputStream salesInfoIn = null;
		DataInputStream idIn = null;
		DataOutputStream idOut = null;

		if (!roomInfoFile.exists()) // 방 정보 파일이 없으면
		{
			try {
				// 방 정보를 저장할 파일 생성
				roomInfoOut = new ObjectOutputStream(new FileOutputStream(roomInfoFile));
			} catch (FileNotFoundException fnfe) {
				MessageFrame mf = new MessageFrame("The Room file could not be found.");
			}
		}
		if (!salesInfoFile.exists()) // 매출 정보 파일이 없으면
		{
			try {
				// 매출 정보를 저장할 파일 생성
				salesInfoOut = new ObjectOutputStream(new FileOutputStream(salesInfoFile));
			} catch (FileNotFoundException fnfe) {
				MessageFrame mf = new MessageFrame("The Sales file could not be found.");
			}
		}
		if (!ownerIDFile.exists()) // 아이디 정보 파일이 없으면
		{
			try {
				// 오너의 아이디 정보를 저장할 파일 생성
				idOut = new DataOutputStream(new FileOutputStream(ownerIDFile));
			} catch (FileNotFoundException fnfe) {
				MessageFrame mf = new MessageFrame("The Sales file could not be found.");
			}
		}

		try {
			// 방 정보를 읽어올 파일 불러오기
			roomInfoIn = new ObjectInputStream(new FileInputStream(roomInfoFile));
			salesInfoIn = new ObjectInputStream(new FileInputStream(salesInfoFile));
			idIn = new DataInputStream(new FileInputStream(ownerIDFile));
			mg.readRoomInfo(roomInfoIn);
			mg.readSalesInfo(salesInfoIn);
			mg.readID(idIn);

		} catch (FileNotFoundException fnfe) {
			MessageFrame mf = new MessageFrame("The file could not be found.");
		} catch (EOFException eofe) {
			MessageFrame mf = new MessageFrame("The file is empty"); // 파일이 비어있음
		} catch (IOException ioe) {
			MessageFrame mf = new MessageFrame("The file cannot be read."); // 파일을 읽어올 수 없음
		} catch (ClassNotFoundException cnfe) {
			MessageFrame mf = new MessageFrame("The class does not exist.");
		} finally {
			try {
				roomInfoIn.close();
				salesInfoIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
	}
}
