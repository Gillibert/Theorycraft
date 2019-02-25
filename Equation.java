 import java.util.Random;
 import java.io.Serializable;
 
 public class Equation implements Serializable {
	static private final long serialVersionUID = 0xe91e2b04;
	double exponent_num;
	double x_add_num;
	double x_nul_num;
	double exponent_denom;
	double x_add_denom;
	double x_nul_denom;
	double result_add;
	
	public Equation(Random gen, double start, boolean croissant, int fast)
	{
		double exp, add;
		if (fast == 0)
		{
			exp = 0.2*gen.nextDouble()+0.25;
			add = (int)(gen.nextDouble()*70)+20;
		}
		else if (fast==1)
		{
			exp = 0.7*gen.nextDouble()+0.4;
			add = (int)(gen.nextDouble()*30)+20;
		}
		else if (fast==2)
		{
			exp = 0.4*gen.nextDouble()+0.9;
			add = (int)(gen.nextDouble()*20)+15;
		}
		else
		{
			exp = 0.4*gen.nextDouble()+1.1;
			add = (int)(gen.nextDouble()*20)+15;
		}
		if(croissant)
		{
			exponent_num = exp;
			x_add_num = add;
			x_nul_num = 0.5*gen.nextDouble()+0.5;
			result_add = start-Math.pow(x_add_num,exponent_num);
			exponent_denom = 0.0;
			x_add_denom = 0.0;
			x_nul_denom = 0.0;
		}
		if(!croissant)
		{
			exponent_denom = exp;
			x_add_denom = add;
			x_nul_denom = 0.5*gen.nextDouble()+0.5;
			result_add = 0.0;
			exponent_num = 1.0;
			x_nul_num = 0.0;
			x_add_num = Math.pow(x_add_denom,exponent_denom)*start;
		}
	}
	
	public void disp()
	{
		System.out.println(String.format("(x*%f+%f)**%f / (x*%f+%f)**%f) + %f\n",x_nul_num,x_add_num,exponent_num,x_nul_denom,x_add_denom,exponent_denom,result_add));
		System.out.println("f(" + 0 +")=" + eval(0)+"\n");
		System.out.println("f(" + 10 +")=" + eval(10)+"\n");
		System.out.println("f(" + 100 +")=" + eval(100)+"\n");
		System.out.println("f(" + 1000 +")=" + eval(1000)+"\n");
		System.out.println("f(" + 10000 +")=" + eval(10000)+"\n");
	}
	
	public Equation(Random gen, double start, double end, int fast)
	{
			double exp, add;
			if(fast == 0)
			{
				exp = 0.3*gen.nextDouble()+0.25;
				add = (int)(gen.nextDouble()*70)+25;
			}
			else if(fast == 1)
			{
				exp = 0.5*gen.nextDouble()+0.5;
				add = (int)(gen.nextDouble()*30)+10;
			}
			else
			{
				exp = 0.8*gen.nextDouble()+0.6;
				add = (int)(gen.nextDouble()*20)+10;
			}
			
			exponent_denom = exp;
			x_add_denom = add;
			x_nul_denom = 1.0*gen.nextDouble()+0.5;
			result_add = end;
			exponent_num = 1.0;
			x_nul_num = 0.0;
			x_add_num = Math.pow(x_add_denom,exponent_denom)*(start-end);
		
	}
	
	public double eval(double x)
	{
		return (Math.pow(x*x_nul_num+x_add_num,exponent_num) / Math.pow(x*x_nul_denom+x_add_denom,exponent_denom)) + result_add;
	}
	
}