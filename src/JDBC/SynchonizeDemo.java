package JDBC;
class Print{
 synchronized static void printTable(int n) {
		for(int i=0;i<10;i++)
		{
			System.out.println(n+"*"+i+"="+n*i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
public class SynchonizeDemo extends Thread {
	@Override
	public void run() {
		//currentThread();
			// TODO Auto-generated method stub
			if(Thread.currentThread().getName().equals("t1"))
			{
				Print.printTable(10);
			}
			else
				Print.printTable(5);
		
		
	}
	public static void main(String[] args) {
		SynchonizeDemo sd=new SynchonizeDemo();
		sd.setName("t1");
		SynchonizeDemo sd1=new SynchonizeDemo();
		sd1.setName("t2");
		sd.start();
		sd1.start();
	}

}
