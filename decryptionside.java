package capstone;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


class Encryption1{
public void decrypt(BufferedImage img,double a,double b,double c) throws IOException {
		
		//img=imgres;
		int w=img.getWidth();
	    int h=img.getHeight();
	    System.out.println("height of dec image"+w+" "+h);
		//System.out.println(w);
		double r=1.5;
		ArrayList<Double> x1=new ArrayList<Double>();
		ArrayList<Double> y1=new ArrayList<Double>();
		ArrayList<Double> z1=new ArrayList<Double>();
		double x[]=new double[w*h+1];
		double y[]=new double[w*h+1];
		double z[]=new double[w*h+1];
		x[0]=a;
		y[0]=b;
		z[0]=c;
		x1.add(x[0]);
		y1.add(y[0]);
		z1.add(z[0]);
		for(int i=1;i<w*h+1;i++) {
			//x=(r**2*(x**2-5)*(1-r*(x**2-5)))%1
			
			x1.add((r*r*(y1.get(i-1)*y1.get(i-1)-5)*(1-r*(z1.get(i-1)*z1.get(i-1)-5)))%1);
			
			y1.add((r*r*(z1.get(i-1)*z1.get(i-1)-5)*(1-r*(x1.get(i-1)*x1.get(i-1)-5)))%1);
			z1.add((r*r*(x1.get(i-1)*x1.get(i-1)-5)*(1-r*(y1.get(i-1)*y1.get(i-1)-5)))%1);
			//dec.add(x1.get(i));
			//System.out.println(x[i]);
		}
		
		int sum=0;
		
		
		
		double power=Math.pow(10, 23);
		//int arr[][]=new int[h][w];
		int I=0;
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				 int pixel = img.getRGB(i,j);
				 Color color = new Color(pixel, true);
				 	int red = color.getRed();
		            int green = color.getGreen();
		            int blue = color.getBlue();
		            
		            sum=sum+red;
		            
		            int v=(int) Math.floor(Math.floorMod((int) ((sum/Math.pow(256, 5)*x1.get(i))*Math.pow(10, 23)), 256));
		            int p=(int) Math.floor(Math.floorMod((int) ((sum/Math.pow(256, 5)*y1.get(i))*Math.pow(10, 23)), 256));
		            int q=(int) Math.floor(Math.floorMod((int) ((sum/Math.pow(256, 5)*z1.get(i))*Math.pow(10, 23)), 256));
		            //System.out.println(v);
		            int X=(int) ((int)power*Math.abs(x1.get(I++)));
		           
		            int redkey=Math.floorMod(X,256);
		            //System.out.println(redkey);
		            int Y=(int) ((int)power*Math.abs(y1.get(I)));
		            int greenkey=Math.floorMod(Y,256);
		            int Z=(int) ((int)power*Math.abs(z1.get(I)));
		            int bluekey=Math.floorMod(Z,256);
		            
		            int red1=red^redkey^v;
		            //d.add(red);
		            int blue1=blue^bluekey;
		            int green1=green^greenkey;
		            color = new Color(red1, red1, red1);
		            //Setting new Color object to the image
		            img.setRGB(i, j, color.getRGB());
			}
		
		}
		
		
		
		BufferedImage img1=Scramble.unscrambleim(img);
		File outputfile = new File("D:\\decrypted.png");
		ImageIO.write(img1, "png", outputfile);
		
	}
}







public class decryptionside {

	private JFrame frame;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;

	/**
	 * Launch the application.
	 */
	static BufferedImage org;
	public static void main(String[] args) {
		decryptionside d=new decryptionside();
		d.call();
	}

	/**
	 * Create the application.
	 */
	public void call() {
		decryptionside dec=new decryptionside();
		dec.frame.setVisible(true);
	}
	public decryptionside() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		frame = new JFrame();
		JLabel lb1 = new JLabel("");
		frame.setBounds(100, 100, 776, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JOptionPane.showMessageDialog(null,"Please enter the key values greater than 4 digits","Instructions",JOptionPane.INFORMATION_MESSAGE);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the First key:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(127, 170, 218, 72);
		frame.getContentPane().add(lblNewLabel_1);
		
		tf1 = new JTextField();
		tf1.setBounds(337, 187, 256, 40);
		frame.getContentPane().add(tf1);
		tf1.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter the second key:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(109, 238, 218, 72);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		tf2 = new JTextField();
		tf2.setColumns(10);
		tf2.setBounds(337, 253, 256, 40);
		frame.getContentPane().add(tf2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Enter the Third key:");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(119, 321, 218, 72);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		tf3 = new JTextField();
		tf3.setColumns(10);
		tf3.setBounds(337, 337, 256, 40);
		frame.getContentPane().add(tf3);
		
		JButton btnNewButton = new JButton("Choose");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc=new JFileChooser();
				int res=fc.showOpenDialog(null);
				if(res==JFileChooser.APPROVE_OPTION) {
					File file=new File(fc.getSelectedFile().getAbsolutePath());
					BufferedImage a = null;
					try {
						a=ImageIO.read(file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					org=a;
					lb1.setText("file path:"+file);
				}
				
			}
		});
		btnNewButton.setBounds(127, 435, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		lb1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lb1.setForeground(new Color(255, 255, 255));
		lb1.setBounds(31, 490, 314, 23);
		frame.getContentPane().add(lb1);
		Encryption1 ec=new Encryption1();
		JButton btnNewButton_1 = new JButton("Decrypt");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				long a=Integer.valueOf(tf1.getText());
				long b=Integer.valueOf(tf2.getText());
				long c=Integer.valueOf(tf1.getText());
				int length = String.valueOf(a).length();
				
				double p=a/Math.pow(10, length);
				length = String.valueOf(b).length();
				double q=b/Math.pow(10, length);
				length = String.valueOf(c).length();
				double r=c/Math.pow(10, length);
				try {
					
					ec.decrypt(org, p, q, r);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(532, 432, 141, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("How to use");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Please enter the key values greater than 4 digits","Instructions",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_2.setBounds(532, 490, 141, 40);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\priyatham\\Downloads\\pic.jpg"));
		lblNewLabel.setBounds(-84, -11, 959, 601);
		frame.getContentPane().add(lblNewLabel);
	}
}