import java.io.*;

public class DataInputExample1 {
	public static void main(String[] args)
	{
		DataInputStream in = null;
		try
		{
			in = new DataInputStream(new FileInputStream("output2.txt"));
			
			while(true)
			{
				int data = in.readInt();
				System.out.println(data);
			}
		}
		
		catch(FileNotFoundException fnfe)
		{
			System.out.println("������ �������� ����.");
		}
		catch(EOFException eofe)
		{
			System.out.println("��!");
		}
		catch(IOException ioe)
		{
			System.out.println("������ ���� �� ����.");
		}
		finally
		{
			try {
				in.close();
			}
			catch(Exception e)
			{
				
			}
		}
	}

}
