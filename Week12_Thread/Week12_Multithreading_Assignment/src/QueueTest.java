
public class QueueTest {
	public static void main(String args[])
	{
		CircularQueue cq = new CircularQueue();
		
		InsertThread insertThrd = new InsertThread(cq);
		DeleteThread deleteThrd = new DeleteThread(cq);
		
		insertThrd.start();
		deleteThrd.start();
	}

}
