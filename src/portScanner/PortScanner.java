package portScanner;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class PortScanner{
	static String address;
	static int from;
	static int to;
	static PortTesterThread porttester;
	static boolean writeToFile;
	
	public static void main(String[] args) throws InterruptedException {
		
		if (args.length == 0) {
			System.out.println("PortScanner by durka1000\nInput: [ip-address] [minimal port] [maximal port] [-f]");
			Scanner scanner = new Scanner(System.in);
			address = scanner.next();
			from = scanner.nextInt();
			to = scanner.nextInt();
			if (scanner.next() == "-f") {
				writeToFile = true;
			} else {
				writeToFile = false;
			}
			scanner.close();
		} else {
			address = args[0];
			from = Integer.parseInt(args[1]);
			to = Integer.parseInt(args[2]);
			if (args[3] == "-f") {
				writeToFile = true;
			} else {
				writeToFile = false;
			}
		}
		
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
