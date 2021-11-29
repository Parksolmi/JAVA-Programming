//1�� �������� 100�� ť���� �׸��� �����Ѵ�.

public class DeleteThread extends Thread{
	CircularQueue cq;
	
	DeleteThread(CircularQueue circularQueue)
	{
		cq = circularQueue;
	}

	public void run()
	{
		//100�� �ݺ�
		for (int cnt = 1; cnt <= 100; cnt++) 
		{
			// 1�� �������� ť���� �׸� ����
			cq.dequeue();
			System.out.print("Dequeue : "); cq.displayQueue();
			// ��� �� 1�� ���
			try 
			{
				sleep(1000);
			} catch (InterruptedException ie) {
				System.out.println(ie.getMessage());
			}
		}
	}
}
