package portScanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class PortScanner{
	static String address;
	static int from;
	static int to;
	static PortTesterThread porttester;
	//static boolean writeToFile;
	
	public static void main(String[] args) throws InterruptedException {
		
		if (args.length == 0) {
			System.out.println("PortScanner by durka1000\nInput: [ip-address] [minimal port] [maximal port]");
			Scanner scanner = new Scanner(System.in);
			address = scanner.next();
			from = scanner.nextInt();
			to = scanner.nextInt();
			/*if (scanner.next() == "-f") {
				writeToFile = true;
			} else {
				writeToFile = false;
			}*/
			scanner.close();
		} else {
			address = args[0];
			from = Integer.parseInt(args[1]);
			to = Integer.parseInt(args[2]);
			/*if (args[3] == "-f") {
				writeToFile = true;
			} else {
				writeToFile = false;
			}*/
		}
		
		Instant anfang = Instant.now();
		for (int i = from; i < to+1; i++) {
			porttester = new PortTesterThread(address, i, i+1);
			porttester.run();
			
			/*if (writeToFile) {
				File file = new File("ports.txt");
				System.out.println("File is saved at: " + file.getAbsolutePath());
				try {
					file.createNewFile();
					BufferedWriter out = new BufferedWriter(new FileWriter(file));
					out.write(porttester.test().toString());
					out.write(new String(porttester.b, "UTF-8"));
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}*/
		}
		if (porttester.test() != null) {
			Instant ende = Instant.now();
			System.out.println(Duration.between(anfang, ende));
			System.out.println("End");
		}
		
	}
}
