import java.io.*;

public class CopyImage {
	public static void main(String[] args)
	{
		//1. FileInputStream/OutputStream으로 jpg파일 읽어서 복사하기
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		long startTime = System.currentTimeMillis(); //시작시간 밀리세컨드 단위로 불러옴
		try {
			fis = new FileInputStream("test.jpg"); //복사할 test.jpg파일 읽어오기
			fos = new FileOutputStream("output.jpg"); //output.jpg파일로 출력하기
			int c;
			
			while((c=fis.read())!=-1) //파일이 끝날 때까지
			{
				fos.write(c);
			}
		}
		
		catch(FileNotFoundException fnfe)
		{
			System.out.println("파일이 존재하지 않습니다.");
		}
		catch(EOFException eofe)
		{
			System.out.println("끝");
		}
		catch(IOException ioe)
		{
			System.out.println("파일 입출력 오류가 발생했습니다.");
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
		long endTime = System.currentTimeMillis(); //끝난 시간 밀리세컨드 단위로 불러옴
		
		System.out.println("FileInput/Output Stream 실행 시간 : " + (endTime - startTime)); //13352
		
		//2. BufferedInputStream/OutputStream으로 jpg파일 읽어서 복사하기
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		startTime = System.currentTimeMillis(); //시작시간 밀리세컨드 단위로 불러옴
		try {
			fis = new FileInputStream("test.jpg"); //복사할 test.jpg파일 읽어오기
			fos = new FileOutputStream("output.jpg"); //output.jpg파일로 출력하기
			
			bis = new BufferedInputStream (fis, 1024); 
			bos = new BufferedOutputStream (fos, 1024); 
			
			int c;
			
			while((c=bis.read())!=-1) //파일이 끝날 때까지
			{
				fos.write(c);
			}
		}
		
		catch(FileNotFoundException fnfe)
		{
			System.out.println("파일이 존재하지 않습니다.");
		}
		catch(EOFException eofe)
		{
			System.out.println("끝");
		}
		catch(IOException ioe)
		{
			System.out.println("파일 입출력 오류가 발생했습니다.");
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
		endTime = System.currentTimeMillis(); //끝난 시간 밀리세컨드 단위로 불러옴
		
		System.out.println("BufferedInput/Output Stream 실행 시간 : " + (endTime - startTime));
	}
}
