import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

	public static void main(String[] args) {
		
		ServerSocket servidor = null;
		Socket socket = null;
		DataInputStream reader;
		DataOutputStream writter;
		ArrayList <String> names = new ArrayList();
		
		final int PUERTO = 5000;
		
		try {
			
			servidor = new ServerSocket(PUERTO);
			System.out.println("Servidor iniciando..");
			
			
			while(true) {
				socket = servidor.accept(); // a la espera
				
				System.out.println("Entra Cliente");
				
				reader = new DataInputStream(socket.getInputStream());
				writter = new DataOutputStream(socket.getOutputStream());
				
				String name = reader.readUTF(); // a la espera
				
				if(names.contains(name)) {
					writter.writeUTF("El usuario ya existe");
				}else {
					names.add(name);
					writter.writeUTF("Bienvenido " + name + ", usted queda registrado");
				}
				
				socket.close();
				System.out.println("Sale Cliente");
			}
			
		} catch (Exception e) {
			
		}

	}

}
