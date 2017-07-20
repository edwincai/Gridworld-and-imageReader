import static org.junit.Assert.*;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

import org.junit.Before;
import org.junit.Test;

public class ImplementImageIOTest {

	@Before
	public void setUp() throws Exception {
	}
	//测试是否能成功读取文件
	@Test
	public void testMyRead() throws IOException {
		FileInputStream in = new FileInputStream("/home/administrator/Desktop/"
				+ "grid world/bmptest/1.bmp");
		BufferedImage goal = ImageIO.read(in);
		ImplementImageIO iio = new ImplementImageIO();
		Image image = iio.myRead("/home/administrator/Desktop/"
				+ "grid world/bmptest/1.bmp");
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		BufferedImage test = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
		test.getGraphics().drawImage(image, 0, 0, width, height, null);
		
		//读取图片的大小是否一致
		assertEquals(goal.getWidth(null),image.getWidth(null));
		assertEquals(goal.getHeight(null),image.getHeight(null));
		//读取图片的颜色RGB是否一致
		for (int i = 0; i < goal.getHeight(null);i++){
			for(int j = 0; j < goal.getWidth();j++){
				assertEquals(goal.getRGB(i, j),test.getRGB(i, j));
			}
		}
		
	}

	
}
