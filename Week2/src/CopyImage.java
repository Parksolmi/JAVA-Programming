import java.io.*;

public class CopyImage {
	public static void main(String[] args)
	{
		//1. FileInputStream/OutputStream���� jpg���� �о �����ϱ�
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		long startTime = System.currentTimeMillis(); //���۽ð� �и������� ������ �ҷ���
		try {
			fis = new FileInputStream("test.jpg"); //������ test.jpg���� �о����
			fos = new FileOutputStream("output.jpg"); //output.jpg���Ϸ� ����ϱ�
			int c;
			
			while((c=fis.read())!=-1) //������ ���� ������
			{
				fos.write(c);
			}
		}
		
		catch(FileNotFoundException fnfe)
		{
			System.out.println("������ �������� �ʽ��ϴ�.");
		}
		catch(EOFException eofe)
		{
			System.out.println("��");
		}
		catch(IOException ioe)
		{
			System.out.println("���� ����� ������ �߻��߽��ϴ�.");
		}
		
		finally
		{
			try
			{
				fis.close();
			}
			catch(Exception e)
			{ 
				
			}
		}
		long endTime = System.currentTimeMillis(); //���� �ð� �и������� ������ �ҷ���
		
		System.out.println("FileInput/Output Stream ���� �ð� : " + (endTime - startTime)); //13352
		
		//2. BufferedInputStream/OutputStream���� jpg���� �о �����ϱ�
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		startTime = System.currentTimeMillis(); //���۽ð� �и������� ������ �ҷ���
		try {
			fis = new FileInputStream("test.jpg"); //������ test.jpg���� �о����
			fos = new FileOutputStream("output.jpg"); //output.jpg���Ϸ� ����ϱ�
			
			bis = new BufferedInputStream (fis, 1024); 
			bos = new BufferedOutputStream (fos, 1024); 
			
			int c;
			
			while((c=bis.read())!=-1) //������ ���� ������
			{
				fos.write(c);
			}
		}
		
		catch(FileNotFoundException fnfe)
		{
			System.out.println("������ �������� �ʽ��ϴ�.");
		}
		catch(EOFException eofe)
		{
			System.out.println("��");
		}
		catch(IOException ioe)
		{
			System.out.println("���� ����� ������ �߻��߽��ϴ�.");
		}
		
		finally
		{
			try
			{
				bis.close();
			}
			catch(Exception e)
			{ 
				
			}
		}
		endTime = System.currentTimeMillis(); //���� �ð� �и������� ������ �ҷ���
		
		System.out.println("BufferedInput/Output Stream ���� �ð� : " + (endTime - startTime));
	}
}
