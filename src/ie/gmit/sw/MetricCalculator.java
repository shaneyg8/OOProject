package ie.gmit.sw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
* @author Shane Gleeson
*/

public class MetricCalculator{

	private Map<String, Metric> graph = new HashMap();
	private List<Class> jarClasses;
	private String jar;
	private JarReader jr = new JarReader();

	public MetricCalculator(String jarFile) throws FileNotFoundException, IOException{
		this.jar = jarFile;
		this.jarClasses = jr.readJarFile(jar);
		addClassToMap();
		calculateMetric();
	}

	public void addClassToMap(){
		System.out.println("Adding Classes to Map!\n");
		System.out.println("Classes to be Added:");

		for(int i = 0; i < jarClasses.size(); i++)
		{
			graph.put(jarClasses.get(i).getName(), new Metric());
			graph.get(jarClasses.get(i).getName()).setClassName(jarClasses.get(i).getName());
			System.out.println(jarClasses.get(i).getName());
		}
		System.out.println("\nThe Map contains " + graph.size() + " Classes.");
		System.out.println(graph.keySet());
	}

	public void calculateMetric(){

		try {
			File file  = new File(jar);
			URL url = file.toURI().toURL();
			URL[] urls = new URL[]{url};

			ClassLoader cl = new URLClassLoader(urls);

			for (String className : graph.keySet()) {

				Class cls = Class.forName(className, false, cl);
				reflection(cls);

			}
		} catch (Exception e){

			e.printStackTrace();
		}
	}
	
	public Object[][] getData(){
		
		int i = 0;
		Object[][] data = new Object[graph.size()][4];
		
		for(Metric m : graph.values()){
			data[i][0] = m.getClassName();
			data[i][1] = m.getInDegree();
			data[i][2] = m.getOutDegree();
			data[i][3] = m.getStability();
			i++;
		}
		
		return data;
}

	public void reflection(Class cls){
		List<String> classList = new ArrayList<String>();
		int outdegree = 0;

		Class[] interfaces = cls.getInterfaces();
		for(Class i : interfaces){
			if(graph.containsKey(i.getName())) {
				if(!classList.contains(i.getName())){
					classList.add(i.getName());
					outdegree++;
					Metric m = graph.get(i.getName());
					m.setInDegree(m.getInDegree() + 1);
				}
			}
		}
		
		//Get the set of constructors
		Constructor[] cons = cls.getConstructors(); 
		Class[] constructorParams;

		for(Constructor c : cons){

			constructorParams = c.getParameterTypes();
			for(Class param : constructorParams){
				if(graph.containsKey(param.getName())){
					if(!classList.contains(param.getName())){
						classList.add(param.getName());
						outdegree++;
						Metric m = graph.get(param.getName());
						m.setInDegree(m.getInDegree() + 1);
					}
				}
			}
		}

		Field[] fields = cls.getFields();

		for(Field f : fields)
		{
			Type type = f.getType();
			if(graph.containsKey(type.getTypeName())){
				if(!classList.contains(type.getTypeName())){
					classList.add(type.getTypeName());
					outdegree++;
					Metric m = graph.get(type.getTypeName());
					m.setInDegree(m.getInDegree() + 1);
				}
			}
		}
		
		//Getting set of methods
		Method[] methods = cls.getDeclaredMethods(); 
		Class[] methodParams;

		for(Method m : methods){

			Class methodReturnType = m.getReturnType();
			if(graph.containsKey(methodReturnType.getName())){
				if(!classList.contains(methodReturnType.getName())){
					classList.add(methodReturnType.getName());
					outdegree++;
					Metric mc = graph.get(methodReturnType.getName());
					mc.setInDegree(mc.getInDegree() + 1);
				}
			}
			
			//Get method params
			methodParams = m.getParameterTypes(); 
			for(Class mp : methodParams){
				if(graph.containsKey(mp.getName())){
					if(!classList.contains(mp.getName())){
						classList.add(mp.getName());
						outdegree++;
						Metric bm = graph.get(mp.getName());
						bm.setInDegree(bm.getInDegree() + 1);
					}
				}
			} 
		}
		System.out.println();
		System.out.println("Class: " + cls.getName());
		graph.get(cls.getName()).setOutDegree(outdegree);
		System.out.println("Indegree: " + graph.get(cls.getName()).getInDegree());		
		System.out.println("Outdegree: " + graph.get(cls.getName()).getOutDegree());		
		System.out.println("Stability: " + graph.get(cls.getName()).getStability());
	}
}