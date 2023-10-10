package capstone;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class RecieverChat {
	static ArrayList<MyFile> myfiles=new ArrayList<>();
	private JFrame frame;
	private JTextField tf1;
	public static int port=1234;
	private JTextField tf12;
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		call();
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public static void call() throws IOException {
		RecieverChat window = new RecieverChat();
		window.frame.setVisible(true);
	}
	public RecieverChat() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		int fileId=0;
		JFrame jframe=new JFrame("reciever");
		jframe.setSize(642,602);;
		
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		JPanel jpanel=new JPanel();
		jpanel.setLayout(new BoxLayout(jpanel,BoxLayout.Y_AXIS));
		
		JScrollPane jscrollpane =new JScrollPane(jpanel);
		jscrollpane.setBounds(0, 83, 628, 482);
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jframe.getContentPane().setLayout(null);
		
		JLabel jtitle=new JLabel("reciever");
		jtitle.setBounds(250, 0, 97, 60);
		jtitle.setFont(new Font("Arial",Font.BOLD,25));
		jtitle.setBorder(new EmptyBorder(20,0,10,0));
		jtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		jframe.getContentPane().add(jtitle);
		tf12 = new JTextField();
		JButton btnNewButton = new JButton("port");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=tf12.getText();
				port=Integer.parseInt(s);
				System.out.println("Port connected with "+s);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(517, 28, 89, 23);
		jframe.getContentPane().add(btnNewButton);
		jframe.getContentPane().add(jscrollpane);
		
		
		tf12.setBounds(357, 23, 152, 33);
		jframe.getContentPane().add(tf12);
		tf12.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("decrypt");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				decryptionside dec=new decryptionside();
				dec.call();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(10, 23, 142, 32);
		jframe.getContentPane().add(btnNewButton_1);
		jframe.setVisible(true);
		
		
		@SuppressWarnings("resource")
		ServerSocket serversocket=new ServerSocket(port);
		
		while(true) {
			try {
				Socket socket=serversocket.accept();
				DataInputStream data=new DataInputStream(socket.getInputStream());
				int filenamelength=data.readInt();
				if(filenamelength>0) {
					byte[] filenames=new byte[filenamelength];
					data.readFully(filenames,0,filenames.length);
					String filename=new String(filenames);
					int fileContentLength=data.readInt();
					if(fileContentLength>0) {
						byte[] filecontentbytes=new byte[fileContentLength];
						
						data.readFully(filecontentbytes,0,fileContentLength);
						
						JPanel jp=new JPanel();
						jp.setLayout(new BoxLayout(jp,BoxLayout.Y_AXIS));
						
						
						JLabel jfilename=new JLabel(filename);
						jfilename.setFont(new Font("Arial",Font.BOLD,20));
						jfilename.setBorder(new EmptyBorder(10,0,10,0));
						
						if(getFileExtension(filename).equalsIgnoreCase("txt")) {
							jp.setName(String.valueOf(fileId));
							jp.addMouseListener(getMyMouseListener());
							jp.add(jfilename);
							jpanel.add(jp);
							jframe.validate();
							
							
						}
						else {
							jp.setName(String.valueOf(fileId));
							jp.addMouseListener(getMyMouseListener());
							
							
							jp.add(jfilename);
							jpanel.add(jp);
							
							jframe.validate();
						}
						myfiles.add(new MyFile(fileId,filename,filecontentbytes,getFileExtension(filename)));
					}
					
					
				}
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	public static String getFileExtension(String n) {
		int i=n.lastIndexOf('.');
		if(i>0) {
			return n.substring(i+1);
		}
		else {
			return "no extension found";
		}
	}
	public static JFrame createFrame(String filename,byte[] filedata,String fileExtension) {
		JFrame jframe=new JFrame("File downloader");
		jframe.setSize(400,400);
		JPanel jpanel=new JPanel();
		jpanel.setLayout(new BoxLayout(jpanel,BoxLayout.Y_AXIS));
		
		JLabel jtitle=new JLabel("File downloader");
		jtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		jtitle.setFont(new Font("Arial",Font.BOLD,25));
		jtitle.setBorder(new EmptyBorder(20,0,10,0));
		
		JLabel jprompt =new JLabel("you want to download the file "+filename);
		jprompt.setFont(new Font("Arial",Font.BOLD,25));
		jprompt.setBorder(new EmptyBorder(20,0,10,0));
		jprompt.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton jyes=new JButton("yes");
		jyes.setPreferredSize(new Dimension(150,75));
		jyes.setFont(new Font("Arial",Font.BOLD,20));
		
		JButton jno=new JButton("no");
		jno.setPreferredSize(new Dimension(150,75));
		jno.setFont(new Font("Arial",Font.BOLD,20));
		JLabel jfilecontent=new JLabel();
		jfilecontent.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel jpbuttons=new JPanel();
		jpbuttons.setBorder(new EmptyBorder(20,0,10,0));
		jpbuttons.add(jyes);
		jpbuttons.add(jno);
		
		if(fileExtension.equalsIgnoreCase("txt")) {
			jfilecontent.setText("<html>"+new String(filedata)+"</html>");
		}
		else {
			jfilecontent.setIcon(new ImageIcon(filedata));
		}
		
		jyes.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				File filetoDownload=new File(filename);
				try {
					FileOutputStream fileoutputstream=new FileOutputStream(filetoDownload);
					fileoutputstream.write(filedata);
					fileoutputstream.close();
					jframe.dispose();
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		jno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jframe.dispose();
			}
		});
		jpanel.add(jtitle);
		jpanel.add(jfilecontent);
		jpanel.add(jprompt); 
		jpanel.add(jpbuttons);
		jframe.getContentPane().add(jpanel);
		return jframe;
	}
	public static MouseListener getMyMouseListener() {
		return new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel jpanel=(JPanel)e.getSource();
				int fileId=Integer.parseInt(jpanel.getName());
				for(MyFile myfile:myfiles) {
					if(myfile.getId()==fileId) {
						JFrame jfpreview=createFrame(myfile.getName(),myfile.getdata(),myfile.getExtension());
						jfpreview.setVisible(true);
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
