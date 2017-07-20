import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

import imagereader.*;

public class ImplementImageIO implements IImageIO {
	public Image myRead(String filePath){
		Image img;
		
		try{
			FileInputStream fis = new FileInputStream(filePath);
			//位图头
			byte bmpHead[] = new byte[14];
			fis.read(bmpHead,0,14);
			//位图信息
			byte bmpInfo[] = new byte[40];
			fis.read(bmpInfo,0,40);
			
			//位图宽度，进制转换对齐
			//字节18-21表示宽度
			int width = (( (int)bmpInfo[7]&0xff) << 24)| (((int)bmpInfo[6]&0xff) << 16)|
(((int)bmpInfo[5]&0xff) << 8)| (((int)bmpInfo[4]&0xff));
			//位图高度
			//字节22-25表示高度
			int height = (( (int)bmpInfo[11]&0xff) << 24)| (((int)bmpInfo[10]&0xff) << 16)|
					(((int)bmpInfo[9]&0xff) << 8)| (((int)bmpInfo[8]&0xff));
			//位图大小
			//字节34-37表示图像大小
			int bitsize = (( (int)bmpInfo[23]&0xff) << 24)| (((int)bmpInfo[22]&0xff) << 16)|
					(((int)bmpInfo[21]&0xff) << 8)| (((int)bmpInfo[20]&0xff));
			//字节28-29表示图像的颜色深度，1、4、8灰阶，24彩阶
			int bitcount = ((int)bmpInfo[15]&0xff) << 8|(int)bmpInfo[14] & 0xff;
			//如果是彩色
			if(bitcount == 24){
				//如果像素用字节不是4的倍数，会有空白填充
				int npad = ((bitsize / height) - width * 3);
				//填充4个空白相当于不用填充
				if(npad == 4){
					npad = 0;
				}
				int data[] = new int[height * width];
				//存放像素数据的数组
				byte RGB[] = new byte[bitsize];
				
				fis.read(RGB, 0, bitsize);
			
			
				int index = 0;
			    for(int i = 0; i < height;i++){
				    for(int j = 0; j < width;j++){
				    	
					    int t = width * (height - i - 1) + j;
					    //255表示透明度
					    data[t] = (255&0xff)<< 24|
						(((int)RGB[index + 2] & 0xff) << 16)
						|(((int)RGB[index + 1] & 0xff) << 8)|
						(((int)RGB[index] & 0xff));
					    //三色
					    index += 3;
				    }
				    index += npad;
			    }
			    //使用RGB ColorModel的构造方法（w,h,pix,off,scan)
			    //分别表示宽度，高度，像素数组，存储首个像素的便宜量，一行像素到下一行像素的距离
			   img = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(
					   width,height,data,0,width));
			}
			else{
				img = (Image)null;
			}
			fis.close();
			return img;
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return (Image)null;
	}

	//使用java的API
	public Image myWrite(Image image, String filePath){
		try{
			File imgfile = new File(filePath + ".jpg");
			//长，宽，图像类型
			BufferedImage bi = new BufferedImage(image.getWidth(null),image.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			
			Graphics2D g2 = bi.createGraphics();
			//图，横纵向坐标,转换类型
			g2.drawImage(image,0,0,null);
			//g2.dispose();
			ImageIO.write(bi, "jpg", imgfile);
			return image;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return image;
	}
}