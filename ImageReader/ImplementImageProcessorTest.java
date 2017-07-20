import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

public class ImplementImageProcessorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShowChanelR() throws IOException {
		FileInputStream in = new FileInputStream("/home/administrator/Desktop/grid world/bmptest/goal/1_red_goal.bmp");
		BufferedImage goal = ImageIO.read(in);
		ImplementImageIO iio = new ImplementImageIO();
		Image image = iio.myRead("/home/administrator/Desktop/grid world/bmptest/1.bmp");
		ImplementImageProcessor iip = new ImplementImageProcessor();
		Image red = iip.showChanelR(image);
		int width = red.getWidth(null);
		int height = red.getHeight(null);
		BufferedImage test = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
		test.getGraphics().drawImage(red, 0, 0, width, height, null);
		assertEquals(goal.getWidth(),red.getWidth(null));
		assertEquals(goal.getHeight(),red.getHeight(null));
		//读取图片的颜色RGB是否一致
				for (int i = 0; i < goal.getHeight(null);i++){
					for(int j = 0; j < goal.getWidth();j++){
						assertEquals(goal.getRGB(i, j),test.getRGB(i, j));
					}
				}
	}

	@Test
	public void testShowChanelG() throws IOException {
		FileInputStream in = new FileInputStream("/home/administrator/Desktop/grid world/bmptest/goal/1_green_goal.bmp");
		BufferedImage goal = ImageIO.read(in);
		ImplementImageIO iio = new ImplementImageIO();
		Image image = iio.myRead("/home/administrator/Desktop/grid world/bmptest/1.bmp");
		ImplementImageProcessor iip = new ImplementImageProcessor();
		Image green = iip.showChanelG(image);
		int width = green.getWidth(null);
		int height = green.getHeight(null);
		BufferedImage test = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
		test.getGraphics().drawImage(green, 0, 0, width, height, null);
		assertEquals(goal.getWidth(),green.getWidth(null));
		assertEquals(goal.getHeight(),green.getHeight(null));
		//读取图片的颜色RGB是否一致
				for (int i = 0; i < goal.getHeight(null);i++){
					for(int j = 0; j < goal.getWidth();j++){
						assertEquals(goal.getRGB(i, j),test.getRGB(i, j));
					}
				}
	}

	@Test
	public void testShowChanelB() throws IOException {
		FileInputStream in = new FileInputStream("/home/administrator/Desktop/grid world/bmptest/goal/1_blue_goal.bmp");
		BufferedImage goal = ImageIO.read(in);
		ImplementImageIO iio = new ImplementImageIO();
		Image image = iio.myRead("/home/administrator/Desktop/grid world/bmptest/1.bmp");
		ImplementImageProcessor iip = new ImplementImageProcessor();
		Image blue = iip.showChanelB(image);
		int width = blue.getWidth(null);
		int height = blue.getHeight(null);
		BufferedImage test = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
		test.getGraphics().drawImage(blue, 0, 0, width, height, null);
		assertEquals(goal.getWidth(),blue.getWidth(null));
		assertEquals(goal.getHeight(),blue.getHeight(null));
		//读取图片的颜色RGB是否一致
				for (int i = 0; i < goal.getHeight(null);i++){
					for(int j = 0; j < goal.getWidth();j++){
						assertEquals(goal.getRGB(i, j),test.getRGB(i, j));
					}
				}
	}

	@Test
	public void testShowGray() throws IOException {
		FileInputStream in = new FileInputStream("/home/administrator/Desktop/grid world/bmptest/goal/1_gray_goal.bmp");
		BufferedImage goal = ImageIO.read(in);
		ImplementImageIO iio = new ImplementImageIO();
		Image image = iio.myRead("/home/administrator/Desktop/grid world/bmptest/1.bmp");
		ImplementImageProcessor iip = new ImplementImageProcessor();
		Image gray = iip.showChanelB(image);
		int width = gray.getWidth(null);
		int height = gray.getHeight(null);
		BufferedImage test = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
		test.getGraphics().drawImage(gray, 0, 0, width, height, null);
		assertEquals(goal.getWidth(),gray.getWidth(null));
		assertEquals(goal.getHeight(),gray.getHeight(null));
		//读取图片的颜色RGB是否一致
				/*for (int i = 0; i < goal.getHeight(null);i++){
					for(int j = 0; j < goal.getWidth();j++){
						assertEquals(goal.getRGB(i, j),test.getRGB(i, j));
					}
				}*/
	}

}
