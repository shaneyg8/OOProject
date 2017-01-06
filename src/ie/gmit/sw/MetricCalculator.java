package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class MetricCalculator {


	private HashMap<String, Metric> graph = new HashMap<>();
	private List<Class> jarClass;
	private String jar;


	public MetricCalculator(List<Class> cls, String jarName) {

		this.jarClass = cls;
		this.jar = jarName;
		addClass();
		calculateMetric();
	}


	//addClass
	public void addClass(){
		for (int i = 0; i < jarClass.size(); i++) {
			graph.put(jarClass.get(i).getName(), new Metric());
			System.out.println(jarClass.get(i).getName());
		}
		System.out.println(graph.keySet());
		System.out.println("sixe" + graph.size());
	}

	//calculateMetric
	public void calculateMetric(){
		try {
			File file  = new File(jar);
			URL url = file.toURI().toURL();
			URL[] urls = new URL[]{url};

			ClassLoader cl = new URLClassLoader(urls);

			for(String name : graph.keySet()){
				
			Class cls = Class.forName(name, false, cl);//cl loads data
			System.out.println(name);
			new Reflection(cls);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}