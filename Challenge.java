import java.io.Serializable;

class Challenge implements Serializable {

    public String name;
    public int type; // 0 niveau, 1 gold, 2 monstre
	public double param;
	
	public String boss_name;
	public int boss_level;
	public int boss_tag;
	public int[] boss_p_stats;
	
    public Challenge(String n, int ty, double p)
    {
	name=n;
	type = ty;
	param = p;
    }

	public String desc()
	{
		String res = name + Local.GOAL;
		if (type == 0)
			res += String.format(Local.REACH_LEVEL,(int)param);
		if (type == 1)
			res += String.format(Local.WIN_DOLLARS,(int)param);
		if (type == 2)
			res += String.format(Local.DEFEAT_BOSS,boss_name);
		return res;
	}

	public boolean isCond()
	{
		return (type == 0 || type == 1);
	}
	
	public boolean isTrue(Player p, boolean disp)
	{
	if  (type == 0)
		{
		if(p.level >= param)
			if(disp) Game.MW.addLog(String.format(Local.LEVEL_REACHED_VICTORY,(int)param));
		else
			if(disp) Game.MW.addLog(String.format(Local.YOU_MUST_BE_AT_LEAST_LEVEL,(int)param));
		return (p.level >= param);
		}
	if  (type == 1)
		{
		if(p.money >= param)
			if(disp) Game.MW.addLog(String.format(Local.YOU_POSSESS_DOLLARS_VICTORY,(int)p.money));
		else
			if(disp) Game.MW.addLog(String.format(Local.YOU_MUST_POSSESS_AT_LEAST,(int)param));
		return (p.money >= param);
		}
	return false;
	}
}