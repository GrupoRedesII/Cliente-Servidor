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

import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class VentanaPrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtIpCliente;
	private JTextField txtPuerto;
	private JTextField txtNombreCliente;
	private JButton btnEnviar;
	private JButton btnRegistrarCliente;
	private Servidor servidor;
	private JFileChooser chooser;
	private JButton btnSubirArchivo , btnActualizar;
	private JComboBox cmbOrigen ,  cmbCliente;
	private String ruta ,  nombreArchivo;

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
		setBounds(100, 100, 499, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(84, 226, 46, 14);
		contentPane.add(lblOrigen);

		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setBounds(366, 226, 46, 14);
		contentPane.add(lblDestino);

		 cmbOrigen = new JComboBox();
		cmbOrigen.setBounds(31, 251, 145, 22);
		contentPane.add(cmbOrigen);

		JComboBox cmbDestino = new JComboBox();
		cmbDestino.addItem("Descargas");
		cmbDestino.setBounds(335, 251, 98, 22);
		contentPane.add(cmbDestino);

		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(this);
		btnEnviar.setBounds(210, 251, 89, 23);
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
		txtPuerto.setEnabled(false);
		txtPuerto.setEditable(false);
		txtPuerto.setText("5000");
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

		chooser = new JFileChooser();

		btnRegistrarCliente = new JButton("Registrar Cliente");
		btnRegistrarCliente.addActionListener(this);
		btnRegistrarCliente.setBounds(169, 64, 184, 23);
		contentPane.add(btnRegistrarCliente);

		JLabel lblNewLabel_2 = new JLabel("Transferencia de archivos");
		lblNewLabel_2.setBounds(174, 202, 179, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Cliente");
		lblNewLabel_3.setBounds(60, 102, 46, 14);
		contentPane.add(lblNewLabel_3);

		cmbCliente = new JComboBox();
		cmbCliente.setBounds(108, 98, 98, 22);
	
		contentPane.add(cmbCliente);

		btnSubirArchivo = new JButton("Subir Archivo");
		btnSubirArchivo.addActionListener(this);
		btnSubirArchivo.setBounds(288, 103, 179, 23);
		contentPane.add(btnSubirArchivo);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(191, 150, 122, 23);
		btnActualizar.addActionListener(this);
		contentPane.add(btnActualizar);

	}

	@Override
	public void actionPerformed(ActionEvent ev) {

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
				
				
				cmbCliente.addItem(name);
				
/*
				File directorio = new File("src/Compartida"); // Ruta de la carpeta con archivos
				String archivos[] = directorio.list(); // aca cargas todos los nombres de los archivos

				for (int i = 0; i < archivos.length; i++)
					
*/
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		if (ev.getSource() == btnSubirArchivo) {
			int seleccion = chooser.showOpenDialog(contentPane);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File fichero = chooser.getSelectedFile();
				 ruta = fichero.getAbsolutePath();
				
				String[] secciones = ruta.split("\\\\");
				
				 nombreArchivo = secciones[secciones.length - 1];
				File fileTmp = new File("src/" + cmbCliente.getSelectedItem().toString() +"/"+ nombreArchivo);
				pasarArchivos(fichero, fileTmp);
			}
		}
		if (ev.getSource() == btnEnviar) {

			File archivoOriginal = new File("src/"+cmbCliente.getSelectedItem().toString() + "/"+ cmbOrigen.getSelectedItem().toString());
			
			File archivoCopia = new File("src/Descargas/" + cmbOrigen.getSelectedItem().toString());

			if (!archivoOriginal.exists()) {
				System.out.println("original no existe|| Entro");
				return;
			}
			if (!archivoCopia.exists()) {
				try {
					System.out.println("copia no existe|| Entro");
					archivoCopia.createNewFile();
					pasarArchivos(archivoOriginal, archivoCopia);

				} catch (Exception e) {

				}

			} else {
				System.out.println("No se pudo enviar los archivos");
			}
		}
		
		if(ev.getSource() == btnActualizar) {
			String seleccionado = cmbCliente.getSelectedItem().toString();
			File carpeta = new File("src/"+ seleccionado);
			File[] archivos = carpeta.listFiles();
			cmbOrigen.removeAllItems();
			
			for (int i = 0; i < archivos.length; i++) {
				cmbOrigen.addItem(archivos[i].getName());
			}
		}

	}

	private static void pasarArchivos(File archivoOriginal, File archivoCopia) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
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

	}
}
