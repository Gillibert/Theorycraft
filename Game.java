import java.util.*;
import java.io.*;

public class Game {

    public static MainWindow MW;
	public static MenuWindow MENU;
	public static ChallengeWindow CW;
	public static boolean DEBUG_MODE_MAX_LOAD = false;
    public static boolean DEBUG_MODE_GIFT = false;
    public static boolean REAL_TIME = true;
	public static boolean FILL_SCORE = false;
	public static boolean LOG_IN_FILE = true;
	public static int LOG_WINDOW_MAX_LINES = 50;
	public static int HOLIDAY = 0;
	public static int LANG = 0;
	public static int ADJUST_SIZE_X = 12;
	public static int ADJUST_SIZE_Y = 14;
	public static HiScore HI;

	
	public static void fillScores()
	{
		Player bestPlayer = new Player();
		
		for(int nlop=0; nlop<1; nlop++)
		{
		System.out.println("nlop=" + nlop);
		double best_time[] = new double[ChallengeList.list.size()];
		for(int i=0; i< ChallengeList.list.size(); i++) best_time[i]=100000;
		best_time[0]=20000;
		best_time[5]=900000;
		
		for(int i=0; i<50000; i++)
		{
		//if(i%100==0) System.out.println(i);
		Universe U = new Universe(i);
		StaticItem.init(U);
		Monster.SetOptimalDistribution(U);
		if (i>0 && U.proba_orbe_niveau_drop(50)<0.04) continue;
		
		boolean must_end = false;
		//for (int cli = 0; cli < ChallengeList.list.size() && !must_end; cli++)
		for (int cli = 0; cli < 1 && !must_end; cli++)
		{
		Player joueur = new Player(U, false);
		joueur.disp = false;
		joueur.defi = ChallengeList.list.get(cli);
		double previous_level = joueur.level;
		int defaite_count = 0;
		int victory_count = 0;
		int trap_death_count = 0;
		int trap_count = 0;
		joueur.zone = 2;
		
		while(joueur.temps_total < best_time[cli] && 
			((!joueur.defi.isCond() && joueur.defi.boss_level/2 > joueur.level) || 
			(joueur.defi.isCond() && !joueur.defi.isTrue(joueur,false))))
			{
			// TODO MEILLEUR CHOIX DE LA ZONE EN TENANT COMPTE DES ARENES		
			for(int zi=joueur.zone+1; zi < joueur.universe.nombre_zones()-1.0; zi++)
				{
				if (joueur.max_zone_level() >= joueur.universe.get_zone_level(zi))
				{
					joueur.zone = zi;
				}
				}
			if(joueur.charge_max()-joueur.charge < 1.0)
			{
				joueur.auto_equip();
				joueur.get_shop();
				ArrayList<Item> tmplst = new ArrayList<Item>();
				for(Item the_object : joueur.inventory)
				if(!the_object.equiped) tmplst.add(the_object);
				joueur.sell(tmplst);
			}
			if(joueur.points_a_distribuer() >= 2)
			{
				while (joueur.points_a_distribuer() > 1.0)
				{
					double pad = joueur.points_a_distribuer();
					joueur.stats[Universe.EDUC]++;
					joueur.refresh_stats_with_bonus();
					if (joueur.points_a_distribuer() < pad)
					{
						joueur.stats[Universe.EDUC]--;
						joueur.refresh_stats_with_bonus();
						break;
					}
				}
				while (joueur.points_a_distribuer() > 1.0)
				{
				if (Math.random() < 0.1 ) joueur.stats[Universe.REDUC]++;
				else if (Math.random() < 0.4 ) joueur.stats[Universe.ABS]++;
				else if (Math.random() < 0.4 ) joueur.stats[Universe.EPIN]++;
				else if (Math.random() < 0.1 ) joueur.stats[Universe.VITA]++;
				else if (Math.random() < 0.3 )joueur.stats[(int)(joueur.nb_stats*Math.random())]++;
				joueur.refresh_stats_with_bonus();
				}
				joueur.refresh();
			}
			if(Math.random()< joueur.proba_rencontrer_piege())
			{
			Trap theTrap = Trap.getTrap(joueur);
			if (theTrap.trapEncounter(joueur)) trap_death_count++;
			else trap_count++;
			joueur.heal();
			}
			else
			{
			joueur.get_mob();
			if(joueur.combat(true)) defaite_count++;
			else victory_count++;
			joueur.heal();
			}
			}
		if (joueur.temps_total <  best_time[cli])  best_time[cli] = joueur.temps_total;
		if(joueur.defi.isCond() && joueur.defi.isTrue(joueur,false))
			{
			System.out.println(String.format("(%d) temps_total=%.2f level=%f (defaite=%d/%d) [%s]",i,joueur.temps_total,joueur.level,defaite_count,defaite_count+victory_count,joueur.defi.name));
			joueur.name = StaticItem.nameGenerator.getName();
			joueur.victory();
			if(joueur.level >= bestPlayer.level) bestPlayer = joueur;
		}
		if(!joueur.defi.isCond() && joueur.defi.boss_level/2 <= joueur.level)
		{
			joueur.name = StaticItem.nameGenerator.getName();
			joueur.end_game();
			System.out.println(String.format("(%d) temps_total=%.2f level=%f (defaite=%d/%d) [%s]",i,joueur.temps_total,joueur.level,defaite_count,defaite_count+victory_count,joueur.defi.name));
		}
		if(joueur.defi.isCond() &&!joueur.defi.isTrue(joueur,false) && (cli==0 || cli==1))
		{
			must_end = true;
		}
		}
		}
		}
		//joueur.defi = ChallengeList.list.get(0);
		StaticItem.init(bestPlayer.universe);
		bestPlayer.disp = true;
		bestPlayer.universe.joueur = bestPlayer;
		bestPlayer.universe.save();
		Game.MW = new MainWindow(bestPlayer);
		Game.MW.setVisible(true);
		
	}
	
    public static void main(String args[])
    {
	Calendar cal = Calendar.getInstance();
	int month = cal.get(Calendar.MONTH);
	int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
	
	if(month==0 && dayOfMonth==1) HOLIDAY = 1; // New year
	if(month==4 && dayOfMonth==1) HOLIDAY = 2; // International Workers' Day
	if(month==9 && dayOfMonth==31) HOLIDAY = 3; // Halloween
	if(month==11 && dayOfMonth==25) HOLIDAY = 4; //Christmas
	if(month==2 && dayOfMonth==20) HOLIDAY = 5; // Spring equinox
	
	try{
	    InputStream fileProp = new FileInputStream("theorycraft.config");
		Properties prop = new Properties();
		prop.load(fileProp);
	    fileProp.close();
		int fo = Integer.parseInt(prop.getProperty("FORCE_HOLIDAY"));
		if(fo!=0) HOLIDAY=fo;
		REAL_TIME = Boolean.parseBoolean(prop.getProperty("REAL_TIME"));
		DEBUG_MODE_MAX_LOAD = Boolean.parseBoolean(prop.getProperty("DEBUG_MODE_MAX_LOAD"));
		DEBUG_MODE_GIFT = Boolean.parseBoolean(prop.getProperty("DEBUG_MODE_GIFT"));
		LOG_IN_FILE = Boolean.parseBoolean(prop.getProperty("LOG_IN_FILE"));
		LANG = Integer.parseInt(prop.getProperty("LANG"));
		LOG_WINDOW_MAX_LINES = Integer.parseInt(prop.getProperty("LOG_WINDOW_MAX_LINES"));
	}
	catch(Exception ex)
	    {
			System.out.println("can't load theorycraft.config");
	    }
	
	Local.init(LANG);
	HI = HiScore.loadScore();
	Monster.initZoo();
	ChallengeList.init();
	CW = new ChallengeWindow();
	MENU = new MenuWindow();
	if(FILL_SCORE==false)
		MENU.montre_menu();
	else 
	{
		//HI.cleanScore();
		//fillScores();
		HI.fillScoresFromTheBest();
	}
	}
}
