 import java.io.Serializable;
 import java.util.*;
 
 public class Constant implements Serializable {
	static private final long serialVersionUID = 0x8a0ea9d5;
	
	public int auto_dist_strategy;
	
	private double order;
	int id;
	
	private double min_value;
	private double max_value;
	public double default_value;
	public double value;
	
	public int type; 
	// 0 start at svalue, capped between min and max
	// 1 start at svalue, capped by min, no upper cap (log)
	// 2 start at svalue, capped by min, no upper cap (linear)
	
	public Constant(int dist_strategy, double ord, double min, double max, double svalue, int t)
	{
		min_value=min;
		max_value=max;
		default_value = svalue;
		value = svalue;
		auto_dist_strategy = dist_strategy;
		type = t;
		order = ord;
	}
	
	static class SortByOrder implements Comparator<Constant> 
	{ 
    public int compare(Constant a, Constant b) 
    { 
	    if (a.order < b.order) return -1;
	    if (a.order > b.order) return 1;
	    else return 0;
	}
	}
	
	public double adjusted_value(double pts)
	{
		if(pts < 0)
		{
		double x = -pts*0.005;
		return min_value * (x/(x+1)) + default_value * (1-x/(x+1));
		}
		else
		{
			if (type==0)
			{
			double x = pts*0.005;
			return max_value * (x/(x+1)) + default_value * (1-x/(x+1));
			}
			else if (type==1)
			{
			return default_value*Math.log(pts*max_value+Math.E);
			}
			else
			{
				return default_value+pts*max_value;
			}
		}
	}
	
	public void update(double pts)
	{
		value =  adjusted_value(pts);
	}
	
 }