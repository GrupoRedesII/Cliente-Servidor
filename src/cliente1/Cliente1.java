package cliente1;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class Cliente1 {

	public static void main(String[] args) {

		final String HOST = "192.168.0.111";
		final int PUERTO = 5000;
		DataInputStream reader;
		DataOutputStream writter;
		Scanner scan = new Scanner(System.in);

		try {
			Socket socket = new Socket(HOST, PUERTO);

			reader = new DataInputStream(socket.getInputStream());
			writter = new DataOutputStream(socket.getOutputStream());
			System.out.println("Ingrese nombre");
			String name = scan.nextLine();
			writter.writeUTF(name);

			System.out.println(reader.readUTF());
			

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
