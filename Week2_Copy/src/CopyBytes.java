import java.io.*;

public class CopyBytes {
	public static void main(String[] args)
	{
		FileOutputStream fos = null;
		FileInputStream fis = null;
		
		try
		{
			//txt���ϻ���
			fos = new FileOutputStream("example.txt"); //OutputStream���� ����
			char arr[] = {'J','A','V','A', ' ', 'P','R','O','G','R','M','M','I','N','G','\n'}; //example.txt���Ͽ� �Է��� ������ ���ڹ迭
			int arrayLength = arr.length; //�迭�� ���̸� ��Ÿ���� ����
			
			for(int cnt = 0; cnt<arrayLength; cnt++)
			{
				fos.write(arr[cnt]); //example.txt���Ͽ� �Է��ϱ�
			}
			
			//txt�������
			fis = new FileInputStream("example.txt"); //InputStream���� ����
			for(int cnt=0; cnt<arrayLength; cnt++)
			{
				char text = (char)fis.read(); //�迭���� �� ���ھ� �о����
				System.out.print(text);
			}
		}
		
		catch(FileNotFoundException fnfe) //.txt �ش� ������ ã�� �� ���� ����ó��
		{
			System.out.println("������ ã�� �� ����.");
		}
		catch(IOException ioe) //��Ʈ���� ���� ����ó��
		{
			System.out.println("������ ���� �� ����.");
		}
		
		finally
		{
			try
			{
				fos.close();
				fis.close();
			}
			catch(Exception e)
			{
				
			}
		}
		
	}

}
