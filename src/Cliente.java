import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		

		final String HOST = "localhost";
		final int PUERTO = 5000;
		DataInputStream reader;
		DataOutputStream writter;
		Scanner scan = new Scanner(System.in);
		
		try {
			Socket socket = new Socket(HOST , PUERTO);
			
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
