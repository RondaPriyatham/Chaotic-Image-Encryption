package capstone;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class clientChat {
	public static int port;
	private JFrame frame;
	private JTextField tf1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		clientChat c=new clientChat();
		c.call();
	}

	/**
	 * Create the application.
	 */
	public void call() {
		clientChat window = new clientChat();
		window.frame.setVisible(true);
	}
	public clientChat() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		final File[] filetosend=new File[1];
		frame = new JFrame();
		frame.setBounds(100, 100, 583, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("FILE SENDER");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(168, 0, 244, 90);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("CONNECT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=tf1.getText();
				port=Integer.parseInt(s);
				System.out.println(port);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(346, 144, 121, 37);
		frame.getContentPane().add(btnNewButton);
		JLabel lb1 = new JLabel("");
		tf1 = new JTextField();
		tf1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tf1.setText("Enter the port");
		tf1.setBounds(116, 144, 201, 37);
		frame.getContentPane().add(tf1);
		tf1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Choose");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf=new JFileChooser();
				jf.setDialogTitle("choose File to send");
				if(jf.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
					filetosend[0]=jf.getSelectedFile();
					lb1.setText("file chosen:"+filetosend[0].getName());
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(212, 219, 121, 37);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("SEND");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent e) {
				if(filetosend[0]==null) {
					lb1.setText("please choose a file");
					
				}
				else {
					try {
					
						FileInputStream fi=new FileInputStream(filetosend[0].getAbsolutePath());
					
					Socket socket;
					
						socket=new Socket("localhost",port);
					
					DataOutputStream  da=new DataOutputStream(socket.getOutputStream());
					
					String filename=filetosend[0].getName();
					
					byte[] filenamebytes=filename.getBytes();
					byte[] fileContentBytes=new byte[(int)filetosend[0].length()];
					fi.read(fileContentBytes);
					
					da.writeInt(filenamebytes.length);
					da.write(filenamebytes);
					da.writeInt(fileContentBytes.length);
					da.write(fileContentBytes);
					}
					catch(IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_1.setBounds(212, 290, 121, 37);
		frame.getContentPane().add(btnNewButton_1_1);
		
		
		lb1.setForeground(Color.WHITE);
		lb1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lb1.setBounds(286, 346, 273, 32);
		frame.getContentPane().add(lb1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\priyatham\\Downloads\\2939771.jpg"));
		lblNewLabel.setBounds(0, 0, 569, 389);
		frame.getContentPane().add(lblNewLabel);
	}
}
