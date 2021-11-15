package studyCafe;

import java.io.*;

public class GUI{
	public static void main(String[] args) throws Exception {
		
		Management mg = new Management("Manager001");
		
		//���� �о����
		File roomInfoFile = new File("studyCafeRoom.dat");
		File salesInfoFile = new File("studyCafeSales.dat");
		File ownerIDFile = new File("studyCafeOwnerID.dat");
		ObjectOutputStream roomInfoOut = null;
		ObjectInputStream roomInfoIn = null;
		ObjectOutputStream salesInfoOut = null;
		ObjectInputStream salesInfoIn = null;
		DataInputStream idIn = null;
		DataOutputStream idOut = null;
		
		if(!roomInfoFile.exists()) //�� ���� ������ ������
		{
			try
			{
				// �� ������ ������ ���� ����
				roomInfoOut = new ObjectOutputStream(new FileOutputStream(roomInfoFile));
			}
			catch (FileNotFoundException fnfe) {
				System.out.println("The Room file could not be found.");
			}
		}
		if(!salesInfoFile.exists()) //���� ���� ������ ������
		{
			try
			{
				//���� ������ ������ ���� ����
				salesInfoOut = new ObjectOutputStream(new FileOutputStream(salesInfoFile));
			}
			catch (FileNotFoundException fnfe) {
				System.out.println("The Sales file could not be found.");
			}
		}
		if(!ownerIDFile.exists()) //���̵� ���� ������ ������
		{
			try
			{
				//������ ���̵� ������ ������ ���� ����
				idOut = new DataOutputStream(new FileOutputStream(ownerIDFile));
			}
			catch (FileNotFoundException fnfe) {
				System.out.println("The Sales file could not be found.");
			}
		}
		
		try {
				// �� ������ �о�� ���� �ҷ�����
				roomInfoIn = new ObjectInputStream(new FileInputStream(roomInfoFile));
				salesInfoIn = new ObjectInputStream(new FileInputStream(salesInfoFile));
				idIn = new DataInputStream(new FileInputStream(ownerIDFile));
				mg.readRoomInfo(roomInfoIn);
				mg.readSalesInfo(salesInfoIn);
				mg.readID(idIn);
				
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
					salesInfoIn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		//��� ������ ����
		ModeFrame modeFrame = new ModeFrame(mg);				
	}
}
