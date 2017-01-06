package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * 
 * @author Shane Gleeson
 *
 */

public class JarReader {

	List<Class> jarClass = new ArrayList<Class>();

	public JarReader() {
		
	}

	/**
	 * Retrieves the jar from the specified jar
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void getJar(String jarName) throws FileNotFoundException, IOException {
		File file  = new File(jarName);
        URL url = file.toURI().toURL();
        URL[] urls = new URL[]{url};

        ClassLoader cl = new URLClassLoader(urls);
		
		JarInputStream in = new JarInputStream(new FileInputStream(new File(jarName)));
		JarEntry next = in.getNextJarEntry();
		while (next != null) {
			if (next.getName().endsWith(".class")) {
				String name = next.getName().replaceAll("/", "\\.");
				name = name.replaceAll(".class", "");
				if (!name.contains("$"))
					name.substring(0, name.length() - ".class".length());
				System.out.println(name);
				
				Class cls;
				try {
					cls = Class.forName(name, false, cl);//cl loads data
					jarClass.add(cls);
					//new Reflection(queryClass);
					//System.exit(0);
				} 
				catch (ClassNotFoundException e) {
					System.out.println("Couldn't find class '" + name + "'");
					System.exit(0);
				} 
				 //System.out.println(cls.size());
			}
			next = in.getNextJarEntry();
		}
		new MetricCalculator(jarClass, jarName);
		in.close();
		
	}

}