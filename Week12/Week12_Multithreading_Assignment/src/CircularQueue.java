
public class CircularQueue {
	
	//입출력을 담당하는 변수 선언
	private int front;
	private int rear;
	//큐 사이즈 10으로 설정
	private final int MAX_SIZE = 10; 
	
	private int queue[];
	private int data;
	
	CircularQueue()
	{
		front = 0;
		rear = 0;
		queue = new int[MAX_SIZE];
	}
	
	synchronized void enqueue(int data){
		if((rear+1)%MAX_SIZE == front)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		rear = (rear + 1) % MAX_SIZE;
		queue[rear] = data;
		notifyAll();
		
	}
	
	synchronized int dequeue() {
		if(front == rear)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		front = (front + 1) % MAX_SIZE;
		data = queue[front];
		notifyAll();
		
		return data;
	}
	
	//큐 값을 display해주는 함수
	public void displayQueue()
	{
		int maxi = (front <= rear) ? rear : rear + MAX_SIZE;
		
		for(int i=front+1; i<=maxi; i++)
		{
			System.out.print(queue[i%MAX_SIZE] + "  ");
		}
		System.out.println();
	}
}
