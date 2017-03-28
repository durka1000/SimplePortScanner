package portScanner;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class PortScanner{
	static String address;
	static int from;
	static int to;
	static PortTesterThread porttester;
	
	public static void main(String[] args) {
		System.out.println("Input: [ip-address] [minimal port] [maximal port]");
		Scanner scanner = new Scanner(System.in);
		address = scanner.next();
		from = scanner.nextInt();
		to = scanner.nextInt();
		scanner.close();
		
		Instant anfang = Instant.now();
		for (int i = from; i < to+1; i++) {
			porttester = new PortTesterThread(address, i, i+1);
			porttester.start();
		}
		if (porttester.test() != null) {
			Instant ende = Instant.now();
			System.out.println(Duration.between(anfang, ende));
			System.out.println("End");
		}
		
	}
}
