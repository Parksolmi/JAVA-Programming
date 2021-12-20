import java.io.*;

public class CopyBytes {
	public static void main(String[] args)
	{
		FileOutputStream fos = null;
		FileInputStream fis = null;
		
		try
		{
			//txt파일생성
			fos = new FileOutputStream("example.txt"); //OutputStream파일 생성
			char arr[] = {'J','A','V','A', ' ', 'P','R','O','G','R','M','M','I','N','G','\n'}; //example.txt파일에 입력할 내용의 문자배열
			int arrayLength = arr.length; //배열의 길이를 나타내는 변수
			
			for(int cnt = 0; cnt<arrayLength; cnt++)
			{
				fos.write(arr[cnt]); //example.txt파일에 입력하기
			}
			
			//txt파일출력
			fis = new FileInputStream("example.txt"); //InputStream파일 생성
			for(int cnt=0; cnt<arrayLength; cnt++)
			{
				char text = (char)fis.read(); //배열에서 한 글자씩 읽어오기
				System.out.print(text);
			}
		}
		
		catch(FileNotFoundException fnfe) //.txt 해당 파일을 찾을 수 없는 예외처리
		{
			System.out.println("파일을 찾을 수 없음.");
		}
		catch(IOException ioe) //스트림에 대한 예외처리
		{
			System.out.println("파일을 읽을 수 없음.");
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
