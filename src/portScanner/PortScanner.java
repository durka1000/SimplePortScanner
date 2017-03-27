package portScanner;

import java.time.Duration;
import java.time.Instant;

public class PortScanner{
	static String address = "188.94.254.93";
	static int von = 1;
	static int bis = 1000;
	static PortTesterThread porttester;
	
	public static void main(String[] args) {
		/*ArrayList<Integer> em = new ArrayList<>();
		System.out.println(em);*/
		Instant anfang = Instant.now();
		for (int i = von; i < bis+1; i++) {
			porttester = new PortTesterThread(address, i, i+1);
			porttester.start();
		}
		if (porttester.test() != null) {
			Instant ende = Instant.now();
			System.out.println(Duration.between(anfang, ende));
		}
		
	}
}
