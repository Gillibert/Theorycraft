import java.io.Serializable;

class Score implements Serializable {
	public String challenge_name;
	public String name;
	public double time;
	public double seed;
	
	public Score(String l_challenge_name,String l_name,double l_time, double l_seed)
	{
		challenge_name = l_challenge_name;
		name = l_name;
		time = l_time;
		seed = l_seed;
	}
}