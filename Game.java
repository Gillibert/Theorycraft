import java.util.ArrayList;

public class Game {

    public static MainWindow MW;
	public static MenuWindow MENU;
	public static ChallengeWindow CW;
	
    public static boolean DEBUG = false;
    public static boolean FAST = true;
	public static boolean FILL_SCORE = false;
	public static int LANG = 0;
	public static HiScore HI;

	public static void findCoolUniverse()
	{
		for(int i=0; i<10000; i++)
		{
		Universe U = new Universe(i);
		if(U.points_initiaux() < 20) continue;
		if(U.get_zone_max_level(0) < 20) continue;
		
		Player play = new Player(U, true);
		play.stats_with_bonus = new double[Player.nb_stats];
		play.stats_with_bonus[Universe.REDUC] = 10*U.classic_points_par_niveau(0);
		play.stats_with_bonus[Universe.ABS] = 40*U.classic_points_par_niveau(0);
		play.stats_with_bonus[Universe.EPIN] = 20*U.classic_points_par_niveau(0);
		play.stats_with_bonus[Universe.TRAP_DET] =10*U.classic_points_par_niveau(0);
		
		play.tags = new boolean[Player.nb_tags];
	    for(int t=0; t<Player.nb_tags; t++) play.tags[t]=false;
	    play.tags[2]=true; // Humain
		//double pp = (1.0-play.proba_trouver_piege(50))*U.proba_rencontrer_piege();
		
		double epinedmg = play.epines();
		//double dph = Monster.AverageDPH(mob,play);
		//if(pp > 0.3) continue;
		//if(dph > 0) continue;
		if(epinedmg < 10) continue;
		
		double totalMonsterVictory = 0;
		for(int j=0; j<50; j++)
		{
			Monster mob = new Monster(200, U);
			play.mob = mob;
			totalMonsterVictory += Monster.EstimateMonsterVictory(play,100);
		}
		totalMonsterVictory = totalMonsterVictory/50.0;
		double survivalRate = (1.0-U.proba_rencontrer_piege())*(1.0-totalMonsterVictory) + U.proba_rencontrer_piege()*(play.proba_trouver_piege(50));
		if(survivalRate < 0.6) continue;
		System.out.println(String.format("(%d) points=%.2f zone=%d epines=%f totalMonsterVictory=%.2f survivalRate=%.2f" ,i,U.points_initiaux(), U.get_zone_max_level(0),epinedmg,totalMonsterVictory,survivalRate));
		}
	}
	
	public static void fillScores()
	{
		Player bestPlayer = new Player();
		
		for(int nlop=0; nlop<5; nlop++)
		{
		System.out.println("nlop=" + nlop);
		double best_time[] = new double[ChallengeList.list.size()];
		for(int i=0; i< ChallengeList.list.size(); i++) best_time[i]=100000;
		best_time[0]=2000;
		best_time[5]=500000;
		
		for(int i=0; i<5000; i++)
		{
		Universe U = new Universe(i);
		StaticItem.init(U);
		Monster.SetOptimalDistribution(U);
		if(U.points_initiaux() < 20) continue;
		//if(U.get_zone_max_level(0) < 20) continue;
		boolean must_end = false;
		for (int cli = 0; cli < ChallengeList.list.size() && !must_end; cli++)
		{
		Player joueur = new Player(U, false);
		joueur.disp = false;
		joueur.defi = ChallengeList.list.get(cli);
		int previous_level = joueur.level;
		int defaite_count = 0;
		int victory_count = 0;
		int trap_death_count = 0;
		int trap_count = 0;
		joueur.zone = 0;
		
		while(joueur.temps_total < best_time[cli] && 
			((!joueur.defi.isCond() && joueur.defi.boss_level/2 > joueur.level) || 
			(joueur.defi.isCond() && !joueur.defi.isTrue(joueur,false))))
			{
			for(int zi=joueur.zone+1; zi < joueur.universe.map.zonesR.size(); zi++)
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
			if(joueur.points_a_distribuer() > 4)
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
			System.out.println(String.format("(%d) temps_total=%.2f level=%d (defaite=%d/%d) [%s]",i,joueur.temps_total,joueur.level,defaite_count,defaite_count+victory_count,joueur.defi.name));
			joueur.name = StaticItem.nameGenerator.getName();
			joueur.victory();
			if(joueur.level >= bestPlayer.level) bestPlayer = joueur;
		}
		if(!joueur.defi.isCond() && joueur.defi.boss_level/2 <= joueur.level)
		{
			joueur.name = StaticItem.nameGenerator.getName();
			joueur.end_game();
			System.out.println(String.format("(%d) temps_total=%.2f level=%d (defaite=%d/%d) [%s]",i,joueur.temps_total,joueur.level,defaite_count,defaite_count+victory_count,joueur.defi.name));
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
	Local.init(LANG);
	HI = HiScore.loadScore();
	Monster.initZoo();
	ChallengeList.init();
	CW = new ChallengeWindow();
	MENU = new MenuWindow();
	if(FILL_SCORE==false)
		MENU.montre_menu();
	else 
		fillScores();

	//HI.cleanScores();
	}
}
