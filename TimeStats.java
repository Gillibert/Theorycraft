import java.util.ArrayList;
import java.io.Serializable;

public class TimeStats implements Serializable {

public static int NB_BUCKETS = 50;
public static int TIME_STEP = 100;

public static int NB_ACTIVITY = 11;
public static int ACTIVITY_CHERCHE_ENNEMI = 0;
public static int ACTIVITY_INITIATIVE = 1;
public static int ACTIVITY_COGNE = 2;
public static int ACTIVITY_RESURRECTION = 3;
public static int ACTIVITY_HEAL = 4;
public static int ACTIVITY_CHERCHE_MARCHAND = 5;
public static int ACTIVITY_CHERCHE_FORGE = 6;
public static int ACTIVITY_CRAFT = 7;
public static int ACTIVITY_PIEGE = 8;
public static int ACTIVITY_FUITE = 9;
public static int ACTIVITY_PENALTY = 10;

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

public static int NB_EVENT = 19;
public static int EVENT_FLEE_ATTEMPT = 0;
public static int EVENT_FLEE_SUCCESS = 1;
public static int EVENT_ATTACK_ATTEMPT = 2;
public static int EVENT_ATTACK_SUCCESS = 3;
public static int EVENT_FIGHT_ATTEMPT = 4;
public static int EVENT_FIGHT_SUCCESS = 5;
public static int EVENT_FIGHT_DEATH = 6;
public static int EVENT_TRAP_ATTEMPT = 7;
public static int EVENT_TRAP_AVOIDED = 8;
public static int EVENT_TRAP_DEATH = 9;
public static int EVENT_DROP_NORMAL = 10;
public static int EVENT_DROP_MAGIC = 11;
public static int EVENT_DROP_RARE = 12;
public static int EVENT_DROP_MAT = 13;
public static int EVENT_DROP_ORB = 14;
public static int EVENT_FIND_MONSTER = 15;
public static int EVENT_FIND_SHOP = 16;
public static int EVENT_FIND_FORGE = 17;
public static int EVENT_CRAFT = 18;

public double events_base[];
public double events_money[];

public ArrayList<double[]> data;
public ArrayList<double[]> data_money;
public ArrayList<double[]> data_xp;

private double total_time;
private double true_total_time;


public TimeStats()
{
	events_base = new double[NB_EVENT];
	events_money = new double[NB_GAIN];
	
	true_total_time = 0;
	total_time = 0;
	data = new  ArrayList<double[]>();
	data.add(new double[NB_ACTIVITY]);
	
	data_money = new  ArrayList<double[]>();
	data_money.add(new double[NB_GAIN]);
	
	data_xp = new  ArrayList<double[]>();
	data_xp.add(new double[NB_SOURCE_XP]);
}

public String eventStats()
{
	double gain_total = events_money[GAIN_DROP]+events_money[GAIN_RENTE]+events_money[GAIN_SELL_BASE]+events_money[GAIN_SELL_MAGIC]+
		events_money[GAIN_SELL_RARE]+events_money[GAIN_SELL_MAT]+events_money[GAIN_SELL_OTHER];
	String res = Local.FIGHT_STATS;
	res +=String.format(Local.FIGHT_STATS_LIST,
	events_base[EVENT_FIGHT_ATTEMPT],events_base[EVENT_FIND_MONSTER],
	100.0*events_base[EVENT_FIGHT_ATTEMPT]/events_base[EVENT_FIND_MONSTER],
	
	events_base[EVENT_FIGHT_SUCCESS],events_base[EVENT_FIGHT_ATTEMPT],
	100.0*events_base[EVENT_FIGHT_SUCCESS]/events_base[EVENT_FIGHT_ATTEMPT],
	
	events_base[EVENT_FLEE_SUCCESS],events_base[EVENT_FIGHT_ATTEMPT],
	100.0*events_base[EVENT_FLEE_SUCCESS]/events_base[EVENT_FIGHT_ATTEMPT],
	
	events_base[EVENT_FIGHT_DEATH],events_base[EVENT_FIGHT_ATTEMPT],
	100.0*events_base[EVENT_FIGHT_DEATH]/events_base[EVENT_FIGHT_ATTEMPT],
	
	events_base[EVENT_ATTACK_SUCCESS],events_base[EVENT_ATTACK_ATTEMPT],
	100.0*events_base[EVENT_ATTACK_SUCCESS]/events_base[EVENT_ATTACK_ATTEMPT],
	
	events_base[EVENT_FLEE_SUCCESS],events_base[EVENT_FLEE_ATTEMPT],
	100.0*events_base[EVENT_FLEE_SUCCESS]/events_base[EVENT_FLEE_ATTEMPT],
	
	(events_base[EVENT_TRAP_ATTEMPT]-events_base[EVENT_TRAP_DEATH]),events_base[EVENT_TRAP_ATTEMPT],
	100.0*(events_base[EVENT_TRAP_ATTEMPT]-events_base[EVENT_TRAP_DEATH])/events_base[EVENT_TRAP_ATTEMPT],
	
	events_base[EVENT_TRAP_AVOIDED],events_base[EVENT_TRAP_ATTEMPT],
	100.0*events_base[EVENT_TRAP_AVOIDED]/events_base[EVENT_TRAP_ATTEMPT],
	
	events_base[EVENT_FIND_SHOP],events_base[EVENT_FIND_FORGE],events_base[EVENT_CRAFT],
	
	events_base[EVENT_DROP_NORMAL],events_base[EVENT_DROP_MAGIC],events_base[EVENT_DROP_RARE],
	events_base[EVENT_DROP_MAT],events_base[EVENT_DROP_ORB],
	
	gain_total,
	events_money[GAIN_DROP], 100.0*events_money[GAIN_DROP]/gain_total,
	events_money[GAIN_RENTE], 100.0*events_money[GAIN_RENTE]/gain_total,
	events_money[GAIN_SELL_BASE], 100.0*events_money[GAIN_SELL_BASE]/gain_total,
	events_money[GAIN_SELL_MAGIC], 100.0*events_money[GAIN_SELL_MAGIC]/gain_total,
	events_money[GAIN_SELL_RARE], 100.0*events_money[GAIN_SELL_RARE]/gain_total,
	events_money[GAIN_SELL_MAT], 100.0*events_money[GAIN_SELL_MAT]/gain_total,
	events_money[GAIN_SELL_OTHER], 100.0*events_money[GAIN_SELL_OTHER]/gain_total
	);
	res += Local.UL_END;
	return res;
}

public void addEvent(double nb, int num)
{
	events_base[num] += nb;
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
	events_money[num] += fric;
	}

public void addXp(double xp, int num)
	{
	data_xp.get(data_xp.size()-1)[num] += xp;
	}
}