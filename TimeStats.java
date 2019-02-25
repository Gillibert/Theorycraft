import java.util.ArrayList;
import java.io.Serializable;

public class TimeStats implements Serializable {

public static int NB_BUCKETS = 50;
public static int TIME_STEP = 100;

public static int NB_ACTIVITY = 9;

public static int ACTIVITY_CHERCHE_ENNEMI = 0;
public static int ACTIVITY_INITIATIVE = 1;
public static int ACTIVITY_COGNE = 2;
public static int ACTIVITY_RESURRECTION = 3;
public static int ACTIVITY_HEAL = 4;
public static int ACTIVITY_CHERCHE_MARCHAND = 5;
public static int ACTIVITY_CHERCHE_FORGE = 6;
public static int ACTIVITY_CRAFT = 7;
public static int ACTIVITY_PIEGE = 8;

public static int NB_GAIN = 7;
public static int GAIN_DROP = 0;
public static int GAIN_RENTE = 1;
public static int GAIN_SELL_BASE = 2;
public static int GAIN_SELL_MAGIC = 3;
public static int GAIN_SELL_RARE = 4;
public static int GAIN_SELL_MAT = 5;
public static int GAIN_SELL_OTHER = 6;

public static int NB_SOURCE_XP = 3;
public static int XP_MONSTER = 0;
public static int XP_TRAP = 1;
public static int XP_ORB = 2;

public ArrayList<double[]> data;
public ArrayList<double[]> data_money;
public ArrayList<double[]> data_xp;

private double total_time;
private double true_total_time;

public TimeStats()
{
	true_total_time = 0;
	total_time = 0;
	data = new  ArrayList<double[]>();
	data.add(new double[NB_ACTIVITY]);
	
	data_money = new  ArrayList<double[]>();
	data_money.add(new double[NB_GAIN]);
	
	data_xp = new  ArrayList<double[]>();
	data_xp.add(new double[NB_SOURCE_XP]);
}

public void addActivity(double time, int num)
{
	true_total_time += time;
	double todis = time;
	while(todis > 0.000001)
	{
		double restant = TIME_STEP - total_time;
		double part = Math.min(todis,restant);
		todis = todis - part;
		total_time = total_time + part;
		data.get(data.size()-1)[num] += part;
		if(total_time >= TIME_STEP-0.000001)
		{
			double[] act = data.get(data.size()-1);
			data.add(new double[NB_ACTIVITY]);
			data_money.add(new double[NB_GAIN]);
			data_xp.add(new double[NB_SOURCE_XP]);
			total_time = 0;
		}
		while (data.size() > NB_BUCKETS)
		{
			 data.remove(0);
		}
		while (data_money.size() > NB_BUCKETS)
		{
			 data_money.remove(0);
		}
		while (data_xp.size() > NB_BUCKETS)
		{
			 data_xp.remove(0);
		}
	}
}

public void addRevenue(double fric, int num)
	{
	data_money.get(data_money.size()-1)[num] += fric;
	}

public void addXp(double xp, int num)
	{
	data_xp.get(data_xp.size()-1)[num] += xp;
	}
}