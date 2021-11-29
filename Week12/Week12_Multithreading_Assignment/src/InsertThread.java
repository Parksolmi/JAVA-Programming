//1초 간격으로 100개의 임의의 정수값(예를 들면 1,2,3,4,....등  또는 랜덤 정수값)을 큐에 삽입한다.

public class InsertThread extends Thread{
	CircularQueue cq;
	
	InsertThread(CircularQueue circularQueue)
	{
		cq = circularQueue;
	}
	
	public void run()
	{
		//1부터 100까지 정수값 1초 간격으로 삽입
		for(int cnt=1; cnt<=100; cnt++)
		{
			cq.enqueue(cnt);
			System.out.print("Enqueue : "); cq.displayQueue();
			//출력 후 1초 대기
			try {
				sleep(1000);
			}
			catch(InterruptedException ie) {
				System.out.println(ie.getMessage());
			}
		}
	}
}
