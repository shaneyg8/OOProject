package ie.gmit.sw;

import java.lang.reflect.*;

/**
 * 
 * @author Shane Gleeson
 *
 */
public class Reflection {

	private Class cls;

	/**
	 * @param cls
	 */
	public Reflection(Class cls){
		super();
		this.cls = cls;
		getPackage();
		getInterface();
		getConstructor();
		getFields();
		getMethods();
	}

	private void getMethods() {
		Method[] methods = cls.getMethods(); //Get the set of methods
		Class[] methodParams;

		//Loop over the methods and print its name and its return type
		for(Method m : methods){

			System.out.println("Method Name: " + m.getName());
			Class methodReturnType = m.getReturnType(); //Get a method return type
			System.out.println("Method Return Type: " + methodReturnType.getName());

			methodParams = m.getParameterTypes(); //Get method parameters
			//Loop over the Method parameters and print the name of each
			for(Class mp : methodParams){

				System.out.println("Method Parameter: " + mp.getName());
			}
		}
	}
	private void getFields() {
		Field[] fields = cls.getFields(); //Get the fields / attributes
		//Loop over the Fields and print the name of each
		for(Field f : fields){

			System.out.println("Field Name: " + f.getName());
		}
	}
	private void getConstructor() {
		Constructor[] cons = cls.getConstructors(); //Get the set of constructors
		Class[] constructorParams;

		//Loop over the constructors and print the name of each and its parameters
		for(Constructor c : cons){

			System.out.println("Contructor Name: " + c.getName());
			constructorParams = c.getParameterTypes(); //Get the parameters
			for(Class param : constructorParams){

				System.out.println("Constructor Parameter: " + param.getName());
			}
		}
	}
	private void getInterface() {
		boolean iface = cls.isInterface(); //Is it an interface?
		System.out.println("Is this Class an Interface?: " + iface);

		Class[] interfaces = cls.getInterfaces(); //Get the set of interface it implements
		//Loop over the interfaces and print the name of each
		for(Class i : interfaces){

			System.out.println("Implements Interface: " + i.getName());
		}
	}
	private void getPackage() {
		Package pack = cls.getPackage(); //Get the package
		System.out.println("Package Name: " + pack.getName());
	}
}