import java.util.ArrayList;

class Trap {

	public String name;
	public double level;
	public double speed;
	public double damage;
	public double hidden;

	public Trap(String n)
	{
		name = n;
	}

public static String[] trap_zoo_names = Local.TRAP_NAMES;

public static int trap_zoo_size = trap_zoo_names.length;

public static Trap getTrap(Player p)
{
	double lev = p.get_mob_level();
	double max_thorical_level = p.universe.get_zone_max_level((int)p.universe.nombre_zones()-2);
	int normalized_lev = (int)(trap_zoo_size*(lev/max_thorical_level));
	if(normalized_lev >= trap_zoo_size-1) normalized_lev = trap_zoo_size-1;
	
	Trap res = new Trap(trap_zoo_names[normalized_lev]);
	
	res.level = p.universe.niveau_pieges(lev);
	res.speed = Math.pow(res.level*2,1.3);
	res.damage = p.universe.traps_dmg_for_level(res.level);
	res.hidden = res.level * p.universe.plage_random();
	return res;
}

public boolean trapEncounter(Player p)
{
	String lower_case_name = NameGenerator.firstCharLowercase(name);
	p.t_stats.addEvent(1.0,TimeStats.EVENT_TRAP_ATTEMPT);
	boolean res = true;
	if(p.disp) Game.MW.addLog(String.format(Local.YOU_COME_ACROSS,p.name,lower_case_name,level));
	double trap_find_proba = p.proba_trouver_piege(hidden);
	if (Math.random() < trap_find_proba)
		{
			if(p.disp) Game.MW.addLog(String.format(Local.SUCCESSFUL_TRAP_DETECTION,hidden, 100*trap_find_proba));
			if(p.disp) Game.MW.addLog(String.format(Local.COMPLETELY_AVOIDS,p.name,lower_case_name));
			p.t_stats.addEvent(1.0,TimeStats.EVENT_TRAP_AVOIDED);
		}
	else
	{
		if(p.disp) Game.MW.addLog(String.format(Local.FAILED_DETECTION,hidden, 100*trap_find_proba));
		double dmg_base = damage * p.universe.plage_random();
		double bonus = p.bonus_initiative_piege();
		double trap_init = p.universe.initiative(speed) ;
		double player_init = p.universe.plage_random()*p.initiative_piege();
		if(p.disp) Game.MW.addLog(String.format(Local.INITIATIVES,player_init,p.name,trap_init,lower_case_name));
		p.personal_wait(player_init,TimeStats.ACTIVITY_PIEGE);
		double dmg_red = p.resistance_vs_piege();
		if (player_init < trap_init)
			{
				if(p.disp) Game.MW.addLog(Local.TRAP_RESISTANCE_DOUBLED);
				dmg_red = dmg_red/2;
			}
		double dmg_abs = p.absorption();
			
		double dmg = Math.max(dmg_base*dmg_red - dmg_abs,0.0); // no negative damages
		String trapRes = String.format(Local.DAMAGE_INFLICTED,this.name,dmg,p.name,dmg_base,100*(1.0-dmg_red),dmg_abs);
		if(p.disp) Game.MW.addLog(trapRes);
		p.vie -= dmg;
	}
	if (p.vie <= 0) {
		p.t_stats.addEvent(1.0,TimeStats.EVENT_TRAP_DEATH);
		if(p.disp) Game.MW.addLog(String.format(Local.IS_DEAD,p.name));
		p.meurt();
	}
	else {
		p.gain_xp(p.universe.xp_for_level(level)*0.75, TimeStats.XP_TRAP, level);
		res = false;
		}
	return res;
}
}
