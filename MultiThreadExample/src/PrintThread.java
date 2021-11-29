class PrintThread extends Thread {
	SharedArea sharedArea;

	PrintThread(SharedArea area) {
		sharedArea = area;
	}

	public void run() {
		for (int cnt = 0; cnt < 3; cnt++) {
			int sum = sharedArea.getTotal();
			System.out.println("°èÁÂ ÀÜ¾× ÇÕ°è: " + sum);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}