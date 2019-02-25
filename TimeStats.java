import java.util.ArrayList;
import java.io.Serializable;

public class TimeStats implements Serializable {

public static int TIME_STEP = 500;

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
	
public ArrayList<double[]> data;
public ArrayList<double[]> data_money;
private double total_time;

public TimeStats()
{
	total_time = 0;
	data = new  ArrayList<double[]>();
	data.add(new double[NB_ACTIVITY]);
	
	data_money = new  ArrayList<double[]>();
	data_money.add(new double[NB_GAIN]);
}

public void addActivity(double time, int num)
{
	double todis = time;
	while(todis > 0.0001)
	{
		double restant = TIME_STEP - total_time;
		double part = Math.min(todis,restant);
		todis = todis - part;
		total_time = total_time + part;
		data.get(data.size()-1)[num] += part;
		if(total_time >= TIME_STEP-0.0001)
		{
			double[] act = data.get(data.size()-1);
			data.add(new double[NB_ACTIVITY]);
			data_money.add(new double[NB_GAIN]);
			total_time = 0;
		}
		while (data.size() > 40)
		{
			 data.remove(0);
		}
		while (data_money.size() > 40)
		{
			 data_money.remove(0);
		}
	}
}

public void addRevenue(double fric, int num)
	{
	data_money.get(data_money.size()-1)[num] += fric;
	}
	
}