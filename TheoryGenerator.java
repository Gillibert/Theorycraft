import java.util.Random;

public class TheoryGenerator {
	public Random gen;
	private int seed;
	
	public TheoryGenerator(int sd)
		{
			seed = sd;
			gen = new Random(sd);
			gen.nextDouble();
		}
		
	public double nextDouble()
		{
			if(seed!=0) return gen.nextDouble();
			return 0.5;
		}
	
	public int nextInt(int x)
		{
			if(seed!=0) return gen.nextInt(x);
			return x/2;
		}
	}