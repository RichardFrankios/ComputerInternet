package wsj.thinkinjava.code;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetDemo1 {

	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName(null);
			System.out.println("��ַ : " + address.getHostAddress());
			System.out.println("��ַ : " + address.getHostName());
		} catch (UnknownHostException e) {
			System.out.println("-----");
		}
	}

}
