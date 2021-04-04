
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;
 

class Helper extends TimerTask
{
	public static int i = 0;
	public void run()
	{
		System.out.println( + ++i);
		if(i == 30)
		{
			synchronized(timer.obj)
			{
				timer.obj.notify();
			}
		}
	}
	
}


public class timer
{
	protected static timer obj;
	public static void main(String[] args) throws InterruptedException
	{
		obj = new timer();
		Timer timer = new Timer();
		TimerTask task = new Helper();
		Date date = new Date();

		timer.scheduleAtFixedRate(task, date, 1000);
		
		System.out.println("Timer running");
		synchronized(obj)
		{
			obj.wait();
			timer.cancel();
			
			System.out.println(timer.purge());
		}
	}
}
