 import java.io.Serializable;
 import java.util.*;
 
 public class Equation implements Serializable {
	static private final long serialVersionUID = 0xe91e2b04;
	
	double order;
	int id;
	public int auto_dist_strategy; // 0=minimize, 1=maximize, 2=don't change
	double divine_cap=50;
	
	double exponent_num_max;
	double x_add_num_max;
	double x_mul_num_max;
	double exponent_denom_max;
	double x_add_denom_max;
	double x_mul_denom_max;
	double result_add_max;
	
	double exponent_num_min;
	double x_add_num_min;
	double x_mul_num_min;
	double exponent_denom_min;
	double x_add_denom_min;
	double x_mul_denom_min;
	double result_add_min;
	
	double exponent_num;
	double x_add_num;
	double x_mul_num;
	double exponent_denom;
	double x_add_denom;
	double x_mul_denom;
	double result_add;
	
	double exponent_num_adj;
	double x_add_num_adj;
	double x_mul_num_adj;
	double exponent_denom_adj;
	double x_add_denom_adj;
	double x_mul_denom_adj;
	double result_add_adj;
	
	double start = 0.0;
	double end = 0.0;
	int type;
	
	static class SortByOrder implements Comparator<Equation> 
	{ 
    public int compare(Equation a, Equation b) 
    { 
	    if (a.order < b.order) return -1;
	    if (a.order > b.order) return 1;
	    else return 0;
	}
	}
	
	//  type=3 exp, type=4 log, invalid for other types **** WIP TODO log
	public Equation(TheoryGenerator gen, int auto_dist, double ord, int typei, double lstart, double linearSpeed, double expSpeed)
	{
		type = typei;
		auto_dist_strategy = auto_dist;
		order = ord;
		
		double exp_min, exp_max;
		double mul_max, mul_min;
		
		exp_min = expSpeed*(1-0.25);
		exp_max = expSpeed*(1+0.25);
		
		x_mul_num_max = linearSpeed*(1+0.5);
		x_mul_num_min = linearSpeed*(1-0.5);
		x_mul_num = (x_mul_num_max-x_mul_num_min)*gen.nextDouble()+x_mul_num_min;
		
		exponent_num_max = exp_max;
		exponent_num_min = exp_min;
		exponent_num = (exp_max-exp_min)*gen.nextDouble()+exp_min;
	
		x_add_denom_max = 100;
		x_add_denom_min = 100;
		x_add_denom = gen.nextDouble()*(x_add_denom_min-x_add_denom_max)+x_add_denom_min;
	
		start = lstart;
		if (type==3) x_add_num = start-x_mul_num;
		else if (type==4) x_add_num = start-x_mul_num*Math.log(x_add_denom);
		update(0.0);
	}
	
	public Equation(TheoryGenerator gen, int auto_dist, double ord, double lstart, boolean croissant, double linearSpeed, double expSpeed)
	{
		this(gen, auto_dist, ord, lstart, croissant, linearSpeed, expSpeed, 0.25);
	}
	public Equation(TheoryGenerator gen, int auto_dist, double ord, double lstart, boolean croissant, double linearSpeed, double expSpeed, double margin)
	{
		auto_dist_strategy = auto_dist;
		order = ord;
		double exp_min, exp_max;
		double add_min, add_max;
		double mul_max, mul_min;
		
		exp_min = expSpeed*(1-margin);
		exp_max = expSpeed*(1+margin);
		
		mul_min = linearSpeed*0.60;
		mul_max = linearSpeed*1.40;
		
		add_min = 70;
		add_max = 20;
		start = lstart;
		
		if(croissant)
		{
			exponent_num_max = exp_max;
			exponent_num_min = exp_min;
			exponent_num = (exp_max-exp_min)*gen.nextDouble()+exp_min;
			
			x_add_num_max = add_max;
			x_add_num_min = add_min;
			x_add_num = gen.nextDouble()*(add_min-add_max)+add_max;
			
			x_mul_num_max = mul_max;
			x_mul_num_min = mul_min;
			x_mul_num = (mul_max-mul_min)*gen.nextDouble()+mul_min;
			
			result_add_max = start-1.0;
			result_add_min = start-1.0;
			result_add = start-1.0;
			
			exponent_denom_max = exponent_denom_min = exponent_denom = 1.0;
			x_mul_denom_max = x_mul_denom_min = x_mul_denom = 0.0;
						
			x_add_denom_max =  Math.pow(x_add_num_max,exponent_num_max);
			x_add_denom_min =  Math.pow(x_add_num_min,exponent_num_min);
			x_add_denom = Math.pow(x_add_num,exponent_num);
			type=0;
		}
		if(!croissant)
		{
			exponent_denom_max = exp_max;
			exponent_denom_min = exp_min;
			exponent_denom = (exp_max-exp_min)*gen.nextDouble()+exp_min;;
			
			x_add_denom_max = add_max;
			x_add_denom_min = add_min;
			x_add_denom = gen.nextDouble()*(add_min-add_max)+add_max;;
			
			x_mul_denom_max = mul_max;
			x_mul_denom_min = mul_min;
			x_mul_denom = (mul_max-mul_min)*gen.nextDouble()+mul_min;
			
			result_add_min = result_add_max = result_add = 0.0;
			exponent_num_max = exponent_num_min = exponent_num = 1.0;
			x_mul_num_max = x_mul_num_min = x_mul_num = 0.0;

			x_add_num_max = Math.pow(x_add_denom_max,exponent_denom_max)*start;
			x_add_num_min = Math.pow(x_add_denom_min,exponent_denom_min)*start;
			x_add_num = Math.pow(x_add_denom,exponent_denom)*start;
			type=1;
		}
		update(0);
	}
	
	public Equation(TheoryGenerator gen, int auto_dist, double ord, double lstart, double lend, double linearSpeed, double expSpeed)
	{
		auto_dist_strategy = auto_dist;
		order = ord;
		start = lstart;
		end = lend;
		double exp, add;
		double exp_min, exp_max, add_min, add_max;
		
		exp_min = 0.75*expSpeed;
		exp_max = 1.25*expSpeed;
		
		add_min = 1.25*linearSpeed;
		add_max = 0.75*linearSpeed;

		exponent_denom_max = exp_max;
		exponent_denom_min = exp_min;
		exponent_denom =  (exp_max-exp_min)*gen.nextDouble()+exp_min;;
			
		x_add_denom_max = add_max;
		x_add_denom_min = add_min;
		x_add_denom = gen.nextDouble()*(add_min-add_max)+add_max;;
			
		x_mul_denom_max = 1.0;
		x_mul_denom_min = 0.5;
		x_mul_denom = 0.5*gen.nextDouble()+0.5;
			
		result_add_min = result_add_max = result_add = end;
		exponent_num_max = exponent_num_min = exponent_num = 1.0;
		x_mul_num_max = x_mul_num_min = x_mul_num = 0.0;

		x_add_num_max = Math.pow(x_add_denom_max,exponent_denom_max)*(start-end);
		x_add_num_min = Math.pow(x_add_denom_min,exponent_denom_min)*(start-end);
		x_add_num = Math.pow(x_add_denom,exponent_denom)*(start-end);
		type=2;
		update(0.0);
	}
	
	public void disp()
	{
		System.out.println(String.format("(x*%f+%f)**%f / (x*%f+%f)**%f) + %f\n",x_mul_num,x_add_num,exponent_num,x_mul_denom,x_add_denom,exponent_denom,result_add));
		System.out.println("f(" + 0 +")=" + eval(0)+"\n");
		System.out.println("f(" + 10 +")=" + eval(10)+"\n");
		System.out.println("f(" + 100 +")=" + eval(100)+"\n");
		System.out.println("f(" + 1000 +")=" + eval(1000)+"\n");
		System.out.println("f(" + 10000 +")=" + eval(10000)+"\n");
	}
	
	
	public double evalStatic(double x)
	{
		if (type == 3) return x_add_num+x_mul_num*Math.pow(exponent_num,x);
		else if (type == 4) return x_add_num+x_mul_num*Math.log(exponent_num*x+x_add_denom);
		else return Math.max(0.0,(Math.pow(x*x_mul_num+x_add_num,exponent_num) / Math.pow(x*x_mul_denom+x_add_denom,exponent_denom)) + result_add);
	}
	
	public double eval(double x)
	{
		if (type == 3) return x_add_num_adj+x_mul_num_adj*Math.pow(exponent_num_adj,x);
		else if (type == 4) return x_add_num_adj+x_mul_num_adj*Math.log(exponent_num_adj*x+x_add_denom_adj);
		else return Math.max(0.0,(Math.pow(x*x_mul_num_adj+x_add_num_adj,exponent_num_adj) / Math.pow(x*x_mul_denom_adj+x_add_denom_adj,exponent_denom_adj)) + result_add_adj);
	}
	
	public void update(double adj)
	{
		if(adj > 0)
		{
		double x = adj*0.005;
		exponent_num_adj = exponent_num_max * (x/(x+1)) + exponent_num * (1-x/(x+1));
		if (type==0) x_add_num_adj = x_add_num_max * (x/(x+1)) + x_add_num * (1-x/(x+1));
		x_mul_num_adj = x_mul_num_max * (x/(x+1)) + x_mul_num * (1-x/(x+1));
		exponent_denom_adj = exponent_denom_max * (x/(x+1)) + exponent_denom * (1-x/(x+1));
		if (type!=0) x_add_denom_adj = x_add_denom_max * (x/(x+1)) + x_add_denom * (1-x/(x+1));
		x_mul_denom_adj = x_mul_denom_max * (x/(x+1)) + x_mul_denom * (1-x/(x+1));
		result_add_adj = result_add_max * (x/(x+1)) + result_add * (1-x/(x+1));
		}
		else
		{
		double x = -adj*0.005;
		exponent_num_adj = exponent_num_min * (x/(x+1)) + exponent_num * (1-x/(x+1));
		if (type==0) x_add_num_adj = x_add_num_min * (x/(x+1)) + x_add_num * (1-x/(x+1));
		x_mul_num_adj = x_mul_num_min * (x/(x+1)) + x_mul_num * (1-x/(x+1));
		exponent_denom_adj = exponent_denom_min * (x/(x+1)) + exponent_denom * (1-x/(x+1));
		if (type!=0) x_add_denom_adj = x_add_denom_min * (x/(x+1)) + x_add_denom * (1-x/(x+1));
		x_mul_denom_adj = x_mul_denom_min * (x/(x+1)) + x_mul_denom * (1-x/(x+1));
		result_add_adj = result_add_min * (x/(x+1)) + result_add * (1-x/(x+1));
		}
		
		if (type==0) x_add_denom_adj = Math.pow(x_add_num_adj,exponent_num_adj);
		else if (type==1) x_add_num_adj = Math.pow(x_add_denom_adj,exponent_denom_adj)*start;
		else if (type==2) x_add_num_adj = Math.pow(x_add_denom_adj,exponent_denom_adj)*(start-end);
		else if (type==3) x_add_num_adj = start-x_mul_num_adj;
		else if (type==4) x_add_num_adj = start-x_mul_num_adj*Math.log(x_add_denom_adj);
	}
	
	public String dispHTMLadj(boolean includeBalise)
	{
		if (type==3 || type==4)
		{
			String num = "";
			if(Math.abs(x_add_num_adj) > 0.0001) num += String.format("%.3f+",x_add_num_adj);
			if(type==3) num +=String.format("%.3f×%.3f<sup>x</sup>",x_mul_num_adj,exponent_num_adj);
			if(type==4) num +=String.format("%.3f×<i>Log</i>(%.3fx+%.3f)",x_mul_num_adj,exponent_num_adj,x_add_denom_adj);
			if(includeBalise) return "<html>" + num + "</html>";
			else return num;
		}
		else
		{
		String exp1 = "";
		String exp2 = "";
		if (Math.abs(exponent_num_adj-1.0) > 0.0001) exp1 = String.format("<sup>%.3f</sup>",exponent_num_adj);
		if (Math.abs(exponent_denom_adj-1.0) > 0.0001) exp2 = String.format("<sup>%.3f</sup>",exponent_denom_adj);	
		
		String plus1 = String.format("+%.2f", x_add_num_adj);
		if(x_add_num_adj<0) plus1 = String.format("%.2f", x_add_num_adj);
		if (Math.abs(x_add_num_adj) < 0.001) plus1 = "";
		
		String plus2 = String.format("+%.2f", x_add_denom_adj);
		if(x_add_denom_adj<0) plus2 = String.format("%.2f", x_add_denom_adj);
		if (Math.abs(x_add_denom_adj) < 0.001) plus2 = "";
		
		String plus3 = String.format("+%.2f", result_add_adj);
		if(result_add_adj<0) plus3 = String.format("%.2f", result_add_adj);
		if (Math.abs(result_add_adj) < 0.001) plus3 = "";
		
		String num = String.format("(%.3fx%s)%s",x_mul_num_adj, plus1, exp1);
		if (Math.abs(x_mul_num_adj) < 0.0001) num = String.format("%.2f",x_add_num_adj);
		if (Math.abs(exponent_num_adj) < 0.0001) num = "1.0";
		
		String denom = String.format("/(%.3fx%s)%s", x_mul_denom_adj, plus2, exp2);
		if (Math.abs(x_mul_denom_adj) < 0.0001) denom = String.format("/%.3f%s", x_add_denom_adj, exp2);
		if (Math.abs(exponent_denom_adj) < 0.0001) denom = "";
	
		if(includeBalise) return "<html>" + num + denom + plus3 + "</html>";
		else return num + denom + plus3;
		}
	}
	
	public String dispHTML(boolean includeBalise)
	{
		if (type==3 || type==4)
		{
			String num = "";
			if(Math.abs(x_add_num) > 0.0001) num += String.format("%.3f+",x_add_num);
			if(type==3) num +=String.format("%.3f×%.3f<sup>x</sup>",x_mul_num,exponent_num);
			if(type==4) num +=String.format("%.3f×<i>Log</i>(%.3fx+%.3f)",x_mul_num,exponent_num,x_add_denom);
			if(includeBalise) return "<html>" + num + "</html>";
			else return num;
		}
		else
		{
		String exp1 = "";
		String exp2 = "";
		if (Math.abs(exponent_num-1.0) > 0.0001) exp1 = String.format("<sup>%.3f</sup>",exponent_num);
		if (Math.abs(exponent_denom-1.0) > 0.0001) exp2 = String.format("<sup>%.3f</sup>",exponent_denom);	
		
		String plus1 = String.format("+%.2f", x_add_num);
		if(x_add_num<0) plus1 = String.format("%.2f", x_add_num);
		if (Math.abs(x_add_num) < 0.001) plus1 = "";
		
		String plus2 = String.format("+%.2f", x_add_denom);
		if(x_add_denom<0) plus2 = String.format("%.2f", x_add_denom);
		if (Math.abs(x_add_denom) < 0.001) plus2 = "";
		
		String plus3 = String.format("+%.2f", result_add);
		if(result_add<0) plus3 = String.format("%.2f", result_add);
		if (Math.abs(result_add) < 0.001) plus3 = "";
		
		String num = String.format("(%.3fx%s)%s",x_mul_num, plus1, exp1);
		if (Math.abs(x_mul_num) < 0.0001) num = String.format("%.2f",x_add_num);
		if (Math.abs(exponent_num) < 0.0001) num = "1.0";
		
		String denom = String.format("/(%.3fx%s)%s", x_mul_denom, plus2, exp2);
		if (Math.abs(x_mul_denom) < 0.0001) denom = String.format("/%.3f%s", x_add_denom, exp2);
		if (Math.abs(exponent_denom) < 0.0001) denom = "";
		
		if(includeBalise) return "<html>" + num + denom + plus3 + "</html>";
		else return num + denom + plus3;
		}
	}
	
}