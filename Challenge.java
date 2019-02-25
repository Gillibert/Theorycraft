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
		String res = name + "\nObjectif : ";
		if (type == 0)
			res += "atteindre le niveau " + (int)param;
		if (type == 1)
			res += "gagner " + (int)param + " pièces d'or";
		if (type == 2)
			res += "vaincre " + boss_name;
		return res;
	}

	public boolean isCond()
	{
		return (type == 0 || type == 1);
	}
	
	public boolean isTrue(Player p)
	{
	if  (type == 0)
		{
		if(p.level >= param)
			Game.MW.addLog(String.format("Niveau %d atteint, victoire !",(int)param));
		else
			Game.MW.addLog(String.format("Vous devez être au moins niveau %d pour gagner.",(int)param));
		return (p.level >= param);
		}
	if  (type == 1)
		{
		if(p.money >= param)
			Game.MW.addLog(String.format("Vous possédez %d pièces d'or, victoire !",(int)p.money));
		else
			Game.MW.addLog(String.format("Vous devez posséder au moins %d pièces d'or pour gagner.",(int)param));
		return (p.money >= param);
		}
	return false;
	}
}