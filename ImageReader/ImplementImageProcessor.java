import imagereader.IImageProcessor;
import java.awt.*;
import java.awt.image.*;

public class ImplementImageProcessor implements IImageProcessor {
	private Toolkit kit = Toolkit.getDefaultToolkit();
	//红色通道
	class RedSwapFilter extends RGBImageFilter {
		public RedSwapFilter() {
			canFilterIndexColorModel = true;
		}
		//像素单元由3个字节存储三色，一个字节存储透明度
		//顺序为透明度-红-绿-蓝
		//可以用颜色过滤器与色素相与得到对应的通道，过滤器颜色：
		//红色 0xffff0000
		//绿色 0xff00ff00
		//蓝色 0xff0000ff
		public int filterRGB(int x,int y,int rgb){
			return (rgb & 0xffff0000);
		}
		
	}
	//绿色通道
	class GreenSwapFilter extends RGBImageFilter {
		public GreenSwapFilter() {
			canFilterIndexColorModel = true;
		}
		public int filterRGB(int x,int y,int rgb){
			return(rgb & 0xff00ff00);
		}
	}
	//蓝色通道
	class BlueSwapFilter extends RGBImageFilter {
		public BlueSwapFilter(){
			canFilterIndexColorModel = true;
		}
		public int filterRGB(int x,int y, int rgb){
			return(rgb & 0xff0000ff);
		}
	}
	//灰色通道
	class GraySwapFilter extends RGBImageFilter {
		public GraySwapFilter(){
			canFilterIndexColorModel = true;
		}
		// I = 0.299 * R + 0.587 * G + 0.114 * B
		public int filterRGB(int x,int y, int rgb){
			int red = (rgb & 0x00ff0000) >> 16;
			int green = (rgb & 0x0000ff00) >> 8;
			int blue = (rgb & 0x000000ff);
			int gray = (int)((double)(0.299 * red) + (double)(0.587 * green) + (double)(0.114 * blue));
			return (rgb & 0xff000000) + (gray << 16) + (gray << 8) + gray;
		}
	}
	
	//显示红色通道
	public Image showChanelR(Image sourceImage){
		RedSwapFilter red = new RedSwapFilter();
		return kit.createImage(new FilteredImageSource(sourceImage.getSource(),red));

	}
	
	//显示绿色通道
	public Image showChanelG(Image sourceImage){
		GreenSwapFilter green = new GreenSwapFilter();
		return kit.createImage(new FilteredImageSource(sourceImage.getSource(),green));
	}
	//显示蓝色通道
	public Image showChanelB(Image sourceImage){
		BlueSwapFilter blue = new BlueSwapFilter();
		return kit.createImage(new FilteredImageSource(sourceImage.getSource(),blue));
	}
	//显示灰色通道
	public Image showGray(Image sourceImage){
		GraySwapFilter gray = new GraySwapFilter();
		return kit.createImage(new FilteredImageSource(sourceImage.getSource(),gray));
	}
}