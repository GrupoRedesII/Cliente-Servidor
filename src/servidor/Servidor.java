package servidor;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

	public static void main(String[] args) throws IOException{

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
				
               
				
				//byte[] ip4bytes = in4addr.getAddress(); // returns byte[4] String ip4string = in4addr.toString();
				

				reader = new DataInputStream(socket.getInputStream());
				writter = new DataOutputStream(socket.getOutputStream());

				String name = reader.readUTF(); // a la espera

				if (names.contains(name)) {
					writter.writeUTF("El usuario ya existe");
				} else {
					names.add(name);
					writter.writeUTF("Bienvenido " + name + ", usted queda registrado");
					System.out.println("El Nombre del cliente es : " + name);
					System.out.println("La ip del cliente es: " + (ipClient.getAddress() + "").substring(1));
					System.out.println("El puerto del cliente es : " + PUERTO);
					
					File file = new File("src/Compartida/"+name+".txt");
					if (!file.exists()) {
			      
							file.createNewFile();
							
							PrintWriter writer = new PrintWriter("src/Compartida/"+name+".txt", "UTF-8");
				            writer.println("Cliente: " + name);
				            writer.println("IP: " + (ipClient.getAddress() + "").substring(1));
				            writer.println("PUERTO: " +PUERTO);
				            writer.close();
				            
				            
						
			        }
					
				}

				socket.close();
				
			}

		} catch (Exception e) {

		}

	}

}
