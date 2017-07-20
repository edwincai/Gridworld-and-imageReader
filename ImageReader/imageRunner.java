import imagereader.Runner;

public class imageRunner {
	public static void main(String[] args) {
		ImplementImageIO imageIO = new ImplementImageIO();
		ImplementImageProcessor processor = new ImplementImageProcessor();
		Runner.run(imageIO, processor);

	}
}