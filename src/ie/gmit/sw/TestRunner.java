package ie.gmit.sw;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestRunner {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		JarReader reader = new JarReader();
		reader.getJar("C:/Users/SHANE/Desktop/workspace/OOProject/test.jar");
	}
}