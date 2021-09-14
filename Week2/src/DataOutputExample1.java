import java.io.*;

public class DataOutputExample1 {
	public static void main(String[] args)
	{
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new FileOutputStream("output2.txt"));
			int arr[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
			for(int cnt = 0; cnt < arr.length; cnt++)
			{
				out.writeInt(arr[cnt]);
			}
			
		}
		catch(IOException ioe)
		{
			System.out.println("파일로 출력할 수 없음");
		}
		finally
		{
			try
			{
				out.close();
			}
			catch(Exception e)
			{}
		}
	}
}
