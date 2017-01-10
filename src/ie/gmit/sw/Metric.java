package ie.gmit.sw;

import java.util.Map;

/**
 * 
* @author Shane Gleeson
*  Getters and Setters 
*  Indegree & Outdegree
*/
public class Metric {

	private int inDegree;
	private int outDegree;
	private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

	public int getInDegree() {
		return inDegree;
	}
	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}
	public int getOutDegree() {
		return outDegree;
	}
	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}

	public double getStability(){
		float stability = 1f;
		
		if(getOutDegree() > 0)
		{
			//Stability calculation
			stability = ((float)getOutDegree() /( (float)getInDegree() + (float)getOutDegree()));
		}
		else{
			stability = 0f;
		}
		return Math.round(stability * 100d) / 100d;
		
	}	
}