//1�� �������� 100���� ������ ������(���� ��� 1,2,3,4,....��  �Ǵ� ���� ������)�� ť�� �����Ѵ�.

public class InsertThread extends Thread{
	CircularQueue cq;
	
	InsertThread(CircularQueue circularQueue)
	{
		cq = circularQueue;
	}
	
	public void run()
	{
		//1���� 100���� ������ 1�� �������� ����
		for(int cnt=1; cnt<=100; cnt++)
		{
			cq.enqueue(cnt);
			System.out.print("Enqueue : "); cq.displayQueue();
			//��� �� 1�� ���
			try {
				sleep(1000);
			}
			catch(InterruptedException ie) {
				System.out.println(ie.getMessage());
			}
		}
	}
}
