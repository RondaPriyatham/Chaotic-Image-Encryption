package capstone;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Scramble {
	public static BufferedImage scrambleim(String image) throws IOException{
		BufferedImage img = null;
    	img=ImageIO.read(new File(image));

		int x=img.getWidth();
		int y=img.getHeight();
		//scramble in left to right... every 2 pixel gap... interchange from left to right
		for(int i=0;i<y;i++){
			for(int j=0;j<(x/2);j++){
				if(j%2==0){
					int leftPixel=img.getRGB(j,i);
					int rightPixel=img.getRGB((x-j)-1,i);

					img.setRGB(j,i,rightPixel);
					img.setRGB((x-j)-1,i,leftPixel);
				}
			}	
		}//interchange for top and bottom pixels evry 2 pixel gap
		for(int i=0;i<x;i++){
			for(int j=0;j<(y/2);j++){
				if(j%2==0){
					int topPixel=img.getRGB(i, j);
					int bottomPixel=img.getRGB(i,(y-j)-1);

					img.setRGB(i,j,bottomPixel);
					img.setRGB(i,((y-j)-1),topPixel);
				}
			}
		}	

		File outputfile = new File("D:\\scramble.jpg");
		ImageIO.write(img, "jpg", outputfile);
		BufferedImage scram=img;
		return scram;
	}
	//method overloading----two same methods for scramble image but different arguments for diff cases
	public static BufferedImage scrambleim(BufferedImage img) throws IOException{
		

		int x=img.getWidth();
		int y=img.getHeight();
		//scramble in left to right... every 2 pixel gap... interchange from left to right
		for(int i=0;i<y;i++){
			for(int j=0;j<(x/2);j++){
				if(j%2==0){
					int leftPixel=img.getRGB(j,i);
					int rightPixel=img.getRGB((x-j)-1,i);

					img.setRGB(j,i,rightPixel);
					img.setRGB((x-j)-1,i,leftPixel);
				}
			}	
		}//interchange for top and bottom pixels evry 2 pixel gap
		for(int i=0;i<x;i++){
			for(int j=0;j<(y/2);j++){
				if(j%2==0){
					int topPixel=img.getRGB(i, j);
					int bottomPixel=img.getRGB(i,(y-j)-1);

					img.setRGB(i,j,bottomPixel);
					img.setRGB(i,((y-j)-1),topPixel);
				}
			}
		}	

		File outputfile = new File("D:\\scramble.jpg");
		ImageIO.write(img, "jpg", outputfile);
		BufferedImage scram=img;
		return scram;
	}
	public static BufferedImage unscrambleim(BufferedImage img) throws IOException{
		

    	int x=img.getWidth();
		int y=img.getHeight();

		for(int i=0;i<x;i++){
			for(int j=0;j<(y/2);j++){
				if(j%2==0){
					int topPixel=img.getRGB(i, j);
					int bottomPixel=img.getRGB(i,(y-j)-1);

					img.setRGB(i, j,bottomPixel);
					img.setRGB(i,((y-j)-1),topPixel);
				}
			}
		}
		for(int i=0;i<y;i++){
			for(int j=0;j<(x/2);j++){
				if(j%2==0){
					int leftPixel=img.getRGB(j, i);
					int rightPixel=img.getRGB((x- j)-1,i);
					img.setRGB(j,i,rightPixel);
					img.setRGB((x-j)-1,i,leftPixel);
				}
			}
		} 	
		File outputfile=new File("unscramble.jpg");
		ImageIO.write(img,"jpg", outputfile);
		return img;
	}
}

