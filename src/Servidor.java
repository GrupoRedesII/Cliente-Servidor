import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

import javax.security.auth.Subject;

public class Servidor {

	public static void main(String[] args) {

		ServerSocket servidor = null;
		Socket socket = null;
		DataInputStream reader;
		DataOutputStream writter;
		ArrayList<String> names = new ArrayList();

		final int PUERTO = 5000;

		try {

			servidor = new ServerSocket(PUERTO);
			System.out.println("Iniciando Servidor..");

		
			while (true) {
				socket = servidor.accept(); // a la espera

				
				
				  
				InetSocketAddress ipClient = (InetSocketAddress) socket.getRemoteSocketAddress();
				System.out.println("La ip del cliente es: " + (ipClient.getAddress() + "").substring(1));
				System.out.println("El puerto del cliente es : " + PUERTO);
               
				
				//byte[] ip4bytes = in4addr.getAddress(); // returns byte[4] String ip4string = in4addr.toString();
				

				reader = new DataInputStream(socket.getInputStream());
				writter = new DataOutputStream(socket.getOutputStream());

				String name = reader.readUTF(); // a la espera

				if (names.contains(name)) {
					writter.writeUTF("El usuario ya existe");
				} else {
					names.add(name);
					writter.writeUTF("Bienvenido " + name + ", usted queda registrado");
				}

				socket.close();
				
			}

		} catch (Exception e) {

		}

	}

}
