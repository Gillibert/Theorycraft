public class Game {

    public static MainWindow MW;
    public static boolean DEBUG = false;
    public static boolean FAST = true;
	public static HiScore HI;

	
    public static void main(String args[])
    {					
	HI = HiScore.loadScore();
	//StaticItem.init();
	Monster.initZoo();
	ChallengeList.init();
	MenuWindow MENU = new MenuWindow();
	MENU.setVisible(true);
	
	
	for(int i=0; i<10000; i++)
	{
		Universe U = new Universe(i);
		if(U.points_initiaux() < 20) continue;
		if(U.get_zone_max_level(0) < 50) continue;
		if(U.esquive_proba(0,0) > 0.5) continue;
		double dmg_lvl_50 = U.dmg_base(U.monster_points_for_level(50)*Monster.coeff_std[Universe.DMG]*0.1);
		double coeff_abs = dmg_lvl_50/U.absorption(20);
		double pv_lvl_50 = U.vie_max(U.monster_points_for_level(50)*Monster.coeff_std[Universe.VITA]*0.1,U.monster_points_for_level(50)*Monster.coeff_std[Universe.CON]*0.1);
		double nb_coups = pv_lvl_50/U.epines(10);
		if(coeff_abs > 0.5 ) continue;
		if(nb_coups > 10.0 ) continue;
		System.out.println(String.format("(%d) points: %.2f zone: %d abs: %.2f dmg: %.2f coeff_abs: %.2f epines: %.2f esq: %.2f nb_coups=%.2f" ,i,U.points_initiaux(), U.get_zone_max_level(0),U.absorption(10), U.dmg_base(10), coeff_abs, U.epines(10),U.esquive_proba(0,0),nb_coups));
	}
	
	System.out.println("DONE");	
	}
}
