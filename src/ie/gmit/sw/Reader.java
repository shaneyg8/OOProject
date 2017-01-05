package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;


/**
 * 
 * 
 *
 */

public class Reader {

	List<Class> cls = new ArrayList<Class>();

	public Reader() {}

	/**
	 * Retrieves the jar from the specified jar
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void readJarFile(String jarFile) throws FileNotFoundException, IOException{

		JarInputStream in = new JarInputStream(new FileInputStream(new File(jarFile)));
		JarEntry next = in.getNextJarEntry();

		while (next != null) {
			if (next.getName().endsWith(".class")) {
				String name = next.getName().replaceAll("/", "\\.");
				name = name.replaceAll(".class", "");
				if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
				System.out.println(name);

				Class queryClass;
				try {
					queryClass = Class.forName(name);
					cls.add(queryClass);
					new Reflection(queryClass);
				} 
				catch (ClassNotFoundException e) {
					System.out.println("Couldn't find class '" + name + "'");
					System.exit(1);
				} 
			}
			next = in.getNextJarEntry();
		}
		in.close();
	}
}