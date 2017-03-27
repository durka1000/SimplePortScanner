package portScanner;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;

public class PortTesterThread extends Thread {
	String address;
	int min;
	int max;
	InputStream in;
	
	public PortTesterThread(String address, int min, int max) {
		this.address = address;
		this.min = min;
		this.max = max;
	}
	
	public ArrayList<Integer> test() {
		ArrayList<Integer> openPorts = new ArrayList<>();
		for(int count = min;count<max;count++) {
			//String ret = Integer.toString(count);
			try {
				Socket socket = new Socket(address, count);
				in = socket.getInputStream();
				int readData = in.read();
				socket.close();
				if(readData != -1) {
					openPorts.add(count);
				}
			} catch (IOException e) {
				//System.out.println(ret + "closed");
			}
		}
		return openPorts;
		
	}
	
	@Override
	public void run() {
		try {
			sleep((int) Math.random()*1000);
		} catch (InterruptedException e) {}
		ArrayList<Integer> port = test();
		ArrayList<Integer> empty = new ArrayList<>();
		byte[] b = null;
		if (!port.equals(empty)) {
			System.out.println(port);
			/*try {
				in.read(b);
				System.out.println(b.toString());
			} catch (IOException e) {
			}*/
		}
		
		
		
	}
	
}
