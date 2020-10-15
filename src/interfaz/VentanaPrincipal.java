package interfaz;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import servidor.Servidor;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class VentanaPrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtIpCliente;
	private JTextField txtPuerto;
	private JTextField txtNombreCliente;
	private JButton btnServidorOn;
	private JButton btnEnviar;
	private JButton btnRegistrarCliente;
	private JButton btnServidorOf;
	private Servidor servidor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnServidorOn = new JButton("Iniciar Servidor");
		btnServidorOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnServidorOn.setBounds(111, 227, 124, 23);
		contentPane.add(btnServidorOn);
		btnServidorOn.addActionListener(this);

		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(85, 117, 46, 14);
		contentPane.add(lblOrigen);

		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setBounds(318, 117, 46, 14);
		contentPane.add(lblDestino);

		JComboBox cmbOrigen = new JComboBox();
		cmbOrigen.setBounds(58, 142, 98, 22);
		contentPane.add(cmbOrigen);

		JComboBox cmbDestino = new JComboBox();
		cmbDestino.setBounds(297, 142, 98, 22);
		contentPane.add(cmbDestino);

		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(this);
		btnEnviar.setBounds(180, 142, 89, 23);
		contentPane.add(btnEnviar);

		JLabel lblNewLabel = new JLabel("IP Cliente");
		lblNewLabel.setBounds(10, 36, 55, 14);
		contentPane.add(lblNewLabel);

		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(281, 11, 55, 14);
		contentPane.add(lblPuerto);

		txtIpCliente = new JTextField();
		txtIpCliente.setText("127.0.0.1");
		txtIpCliente.setEnabled(false);
		txtIpCliente.setEditable(false);
		txtIpCliente.setBounds(119, 33, 119, 20);
		contentPane.add(txtIpCliente);
		txtIpCliente.setColumns(10);

		txtPuerto = new JTextField();
		txtPuerto.setBounds(335, 8, 89, 20);
		contentPane.add(txtPuerto);
		txtPuerto.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombre del cliente");
		lblNewLabel_1.setBounds(10, 11, 175, 14);
		contentPane.add(lblNewLabel_1);

		txtNombreCliente = new JTextField();
		txtNombreCliente.setColumns(10);
		txtNombreCliente.setBounds(121, 8, 119, 20);
		contentPane.add(txtNombreCliente);

		btnRegistrarCliente = new JButton("Registrar Cliente");
		btnRegistrarCliente.addActionListener(this);
		btnRegistrarCliente.setBounds(151, 64, 184, 23);
		contentPane.add(btnRegistrarCliente);

		JLabel lblNewLabel_2 = new JLabel("Transferencia de archivos");
		lblNewLabel_2.setBounds(175, 98, 139, 14);
		contentPane.add(lblNewLabel_2);

		btnServidorOf = new JButton("Detener Servidor");
		btnServidorOf.setBounds(244, 227, 120, 23);
		contentPane.add(btnServidorOf);

	}

	@Override
	public void actionPerformed(ActionEvent ev) {

		if (ev.getSource() == btnServidorOn) {

		}
		if (ev.getSource() == btnServidorOf) {
			servidor = null;
		}
		if (ev.getSource() == btnRegistrarCliente) {
			final String HOST = "localhost";
			final int PUERTO = Integer.parseInt(txtPuerto.getText().toString());
			DataInputStream reader;
			DataOutputStream writter;

			try {
				Socket socket = new Socket(HOST, PUERTO);

				reader = new DataInputStream(socket.getInputStream());
				writter = new DataOutputStream(socket.getOutputStream());

				String name = txtNombreCliente.getText().toString();
				writter.writeUTF(name);

				System.out.println(reader.readUTF());

				socket.close();

			} catch (IOException e) {

				e.printStackTrace();
			}
			
			

			
		}
		if(ev.getSource() == btnEnviar) {
			
			File archivoOriginal = new File("src/Compartida/Manuel.txt");
			File archivoCopia = new File("src/Descargas/Manuel.txt");

			if(!archivoOriginal.exists()) {
				System.out.println("original no existe|| Entro");
				return;
			}
			if (!archivoCopia.exists()) {
				try {
					System.out.println("copia no existe|| Entro");
					archivoCopia.createNewFile();

					InputStream inputStream = null;
					OutputStream outputStream = null;

					inputStream = new FileInputStream(archivoOriginal);
					outputStream = new FileOutputStream(archivoCopia);
					byte[] buffer = new byte[1024];
					int length;
					while ((length = inputStream.read(buffer)) > 0) {
						outputStream.write(buffer, 0, length);
					}
					inputStream.close();
					outputStream.close();
					System.out.println("Archivo descargado.");
				} catch (Exception e) {
					
				}

			}else {
				System.out.println("No se pudo enviar los archivos");
			}
		}

	}
}
