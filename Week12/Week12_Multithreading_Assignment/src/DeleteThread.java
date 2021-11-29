//1초 간격으로 100번 큐에서 항목을 삭제한다.

public class DeleteThread extends Thread{
	CircularQueue cq;
	
	DeleteThread(CircularQueue circularQueue)
	{
		cq = circularQueue;
	}

	public void run()
	{
		//100번 반복
		for (int cnt = 1; cnt <= 100; cnt++) 
		{
			// 1초 간격으로 큐에서 항목 삭제
			cq.dequeue();
			System.out.print("Dequeue : "); cq.displayQueue();
			// 출력 후 1초 대기
			try 
			{
				sleep(1000);
			} catch (InterruptedException ie) {
				System.out.println(ie.getMessage());
			}
		}
	}
}
