import java.io.Serializable;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Random;
 
 public class Universe implements Serializable {
	public Player joueur;
	public int seed;
	public int number_of_travel;
	private Equation[] equations;
	private double[] constantes;
	private boolean[] binVar;
	
	static private final long serialVersionUID = 42;
	
	// IAS(0) DMG(1) REDUC(2) ABS(3) ESQ(4) PRC(5) LCK(6) CRT(7)
    // VDV(8) VITA(9) CON(10) REGEN(11) RESUR(12) LOAD(13) RUN(14) 
    // RESF(15) MF(16) RF(17) QALF(18) QTYF(19) POWF(20) GF(21)
    // ED_MV(22) ED_ANI(23) ED_HUM(24) ED_PV(25) ED_DEM(26) ED_CHAMP(27)
    // ESTI(28) FLEE(29) FLEE_SPD(30) INIT(31) IMUN_FINAL(32) TIME_SPD(33) 
	// EPIN(34) REP(35) NECRO(36) CRAFT_SPD(37) CRAFT_REND(38) ECO_ORB(39) 
	// SHOP_LEVEL(40) SHOP_SIZE(41) TRAP_DET(42) TRAP_INIT(43) TRAP_RES(44)
	// RENTE(45) EDUC(46) BONUS_XP(47) BONUS_LOWLEV(48) BONUS_HLEV(49)
		
	static int IAS = 0;
	static int DMG = 1;
	static int REDUC = 2;
	static int ABS = 3;
	static int ESQ = 4;
	static int PRC = 5;
	static int LCK = 6;
	static int CRT = 7;
	static int VDV = 8;
	static int VITA = 9;
	static int CON = 10;
	static int REGEN = 11;
	static int RESUR = 12;
	static int LOAD = 13;
	static int RUN = 14;
	static int RESF = 15;
	static int MF = 16;
	static int RF = 17;
	static int QALF = 18;
	static int QTYF = 19;
	static int POWF = 20;
	static int GF = 21;
	static int ED_MV = 22;
	static int ED_ANI = 23;
	static int ED_HUM = 24;
	static int ED_PV = 25;
	static int ED_DEM = 26;
	static int ED_CHAMP = 27;
	static int ESTI = 28;
	static int FLEE = 29;
	static int FLEE_SPD = 30;
	static int INIT = 31;
	static int IMUN_FINAL = 32;
	static int TIME_SPD = 33;
	static int EPIN = 34;
	static int REP = 35;
	static int NECRO = 36;
	static int CRAFT_SPD = 37;
	static int CRAFT_REND = 38;
	static int ECO_ORB = 39;
	static int SHOP_LEVEL = 40;
	static int SHOP_SIZE = 41;
	static int TRAP_DET = 42;
	static int TRAP_INIT = 43;
	static int TRAP_RES = 44;
	static int RENTE = 45;
	static int EDUC = 46;
	static int BONUS_XP = 47;
	static int BONUS_LOWLEV = 48;
	static int BONUS_HLEV = 49;
		
	public static double classic_att_per_sec(double IAS) {return Math.pow(IAS+10,0.50)-2.16227766017;}
    public static double classic_pv_per_vita(double CON) {return Math.pow(CON+10,0.40)-1.51188643151;}
    public static double classic_crit_proba(double LCK) {return (2.0+LCK)/(LCK+100.0)-0.01;}
    public static double classic_multi_crit(double CRT) {return Math.pow(CRT+5.0,0.6)-1.3265278;}
    public static double classic_dmg_base(double DMG) {return 1.2*Math.pow(DMG+0.7,0.7);}
    public static double classic_reduc(double REDUC) {return 1.0/Math.sqrt(1.0+REDUC/4.0);}
    public static double classic_absorption(double ABS) {return Math.pow(ABS+7,0.80)/4.7432763938 -1.0;}
    public static double classic_esquive_proba(double ESQ,double PRC) {return (ESQ+20.0)/(ESQ+PRC+80.0);}
    public static double classic_vol_de_vie(double VDV) {return Math.pow(VDV,0.75)/(Math.pow(VDV,0.75)+300.0);}
    public static double classic_regen(double REGEN, double CON) {return 2.0 + (classic_pv_per_vita(CON) * REGEN)/1.5;}
    public static double classic_charge_max(double LOAD) {return 5*LOAD+25.0;}
    public static double classic_temps_traque(double RUN) {return 5.25771612961 / Math.pow(RUN+6.0,0.70);}
    public static double classic_temps_shop(double RUN) {return 11.5912359471 / Math.pow(RUN+8.0,0.65);}
    public static double classic_temps_forge(double RUN) {return 15.4549812628 / Math.pow(RUN+8.0,0.65);}
    public static double classic_temps_res(double RESUR, int niveau) {return 40.0 / (3.0+Math.pow((4.0*RESUR+4.0)/(niveau),0.70));}
    public static double classic_coeff_achat(double ESTI) {return 1.0 + 6.0 / (10.0+ESTI);}
    public static double classic_coeff_vente(double ESTI) {return (10.0+ESTI) / (20.0+ESTI);}
    public static double classic_multiplicateur_res(double RESF) {return (Math.pow(RESF+30.0,0.50) - 4.47722558)/4.0;} 
    public static double classic_chance_magique(double MF) {return (12.0+MF)/(MF+180.0);}
    public static double classic_chance_rare(double RF) {return 1.0 - 6.0/Math.pow(40.0+RF,0.50);}
    public static double classic_chance_qualite(double QALF) {return 1.0 - 3.6/Math.pow(30.0+QALF,0.40);}
    public static double classic_quantite_drop(double QTYF) {return 0.3*Math.pow(QTYF+8.0,0.50);} 
    public static double classic_puissance_ench_inf(double POWF) {return 0.25*(1.0 - 2.0/Math.pow(10.0+POWF,0.65));} 
    public static double classic_multiplicateur_or(double GF) {return Math.pow(GF+10.0,0.90)/7.94328234724;} 
    public static double classic_ed_specific_monster(double STAT) {return Math.pow(STAT+10.0,0.40) - 1.51188643151;}
    public static double classic_chance_fuite(double FLEE) {return (30.0+FLEE)/(FLEE+100.0);}
    public static double classic_temps_fuite(double FLEE_SPD)  {return 10.0 / (FLEE_SPD+10.0);}
    public static double classic_initiative(double INIT) {return 14.15891568765 / Math.pow(INIT+10.0,0.85);}
	public static double classic_proba_immunite_final(double IMUN_FINAL) {return (4.0+IMUN_FINAL)/(IMUN_FINAL+30.0)-0.133333333;}
    public static double classic_facteur_temps(double TIME_SPD) {return Math.pow(TIME_SPD+20.0,0.20)-0.8205642;}
    public static double classic_epines(double EPIN) {return Math.pow(EPIN+6.0,0.75) - 3.833658625477634855132095;}
    public static double classic_represailles(double REP) {return 2.0-29.7560651784/Math.pow(90.0+REP,0.60);}
    public static double classic_necrophagie(double NECRO) {return Math.pow(NECRO+20,0.80)/20.0-0.54928027164;}
    public static double classic_temps_craft(double CRAFT_SPD) {return 16.0 / Math.pow(CRAFT_SPD+7.0,0.70);}
    public static double classic_rendement(double CRAFT_REND) {return 1.0 - 4.0/Math.pow(12.0+CRAFT_REND,0.90);}
    public static double classic_economie_orbe(double ECO_ORB) {return 9.14610103855/Math.pow(40.0+ECO_ORB,0.60);}
    public static double classic_niveau_boutique(double SHOP_LEVEL) {return 0.8*Math.pow(SHOP_LEVEL+10.0,0.50);}
    public static double classic_taille_boutique(double SHOP_SIZE) {return Math.pow(SHOP_SIZE+8.0,0.65);}
	public static double classic_detection_piege(double TRAP_DET) {return TRAP_DET*0.4;}
	public static double classic_bonus_initiative_piege(double TRAP_INIT) {return Math.pow(TRAP_INIT+20.0,0.40) /3.31445401734;}
	public static double classic_bonus_resistance_vs_piege(double TRAP_RES) {return Math.pow(TRAP_RES+30.0,0.40) / 3.89805984092;}
	public static double classic_rente_par_seconde(double RENTE) {return Math.pow(RENTE+2.0,1.3)*0.1-0.24622888266;}
	public static double classic_points_par_niveau(double EDUC) {return Math.pow(EDUC+20.0,0.28)*2.161133550289;}
    public static double classic_bonus_xp(double BONUS_XP) {return Math.pow(BONUS_XP+10.0,0.20)/1.58489319246;}
	public static double classic_modif_exp_lowlev(double BONUS_LOWLEV, int levdiff)
		{return (1.0+BONUS_LOWLEV*0.02)/(-levdiff+BONUS_LOWLEV*0.02);}
	public static double classic_modif_exp_hlev(double BONUS_HLEV, int levdiff)
		{return (Math.pow((levdiff+1)*(BONUS_HLEV+100.0),0.3)/ 3.98107170553);}
	public static double classic_gold_drop(double level){return Math.pow(level,1.5);}
	public static double classic_boss_level(double level){return Math.pow(level,1.08)+1.0;}
	
	Universe(int sd)
	{
		number_of_travel = 0;
		Random gen = new Random(sd);
		constantes = new double[20];
		binVar = new boolean[StaticItem.nb_pos-1];
		seed = sd;
		if(seed != 0)
		{
			for(int i=0; i< StaticItem.nb_pos-1; i++) {
				if (gen.nextDouble() > 0.33) binVar[i] = true;
				else binVar[i] = false;
			}
			binVar[12] = true; // les armes existent toujours
			 
			constantes[0] = (int)(gen.nextInt(9)+2); // vie de départ;
			constantes[1] = 0.05+gen.nextDouble()*0.45; // proba_rencontrer_piege;
			constantes[2] = 0.05*(int)(4+gen.nextInt(7)); // puissance_ench_sup
			constantes[3] = (int)(gen.nextInt(25)+10.0); // points_initiaux
			constantes[4] = (gen.nextInt(23)+2)*0.01; // gold_death_penalty (proportion d'or perdu par mort)
			constantes[5] = 0.01*(gen.nextInt(18)+5.0); // proba_champion()
			constantes[6] = (int)(gen.nextInt(500)+50); // cout voyage dimensionnel
			
			equations = new Equation[50];
			
			equations[0] = new Equation(gen,1.0, true, 0); // att_per_sec
			equations[1] = new Equation(gen,1.0, true, 0); // pv_per_vita
			equations[2] = new Equation(gen,0.01, 1.0, 0); // crit_proba
			equations[3] = new Equation(gen,1.2, true, 0); // multi_crit
			equations[4] = new Equation(gen,0.5, true, 1); // classic_dmg_base
			equations[5] = new Equation(gen,1.0, 0.0, 0); // reduc
			equations[6] = new Equation(gen,0.0, true, 1); // absorption
			equations[7] = new Equation(gen,10.0, true, 1); // esquive_proba
			equations[8] = new Equation(gen,0.0, 1.0, 0); // vol_de_vie
			equations[9] = new Equation(gen,2.0, true, 1); // regen
			equations[10] = new Equation(gen,5+gen.nextInt(20), true, 2); // charge maximale
			equations[11] = new Equation(gen,0.5+gen.nextDouble()*1.0, 0.0, 1); // temps_traque
			equations[12] = new Equation(gen,0.5+gen.nextDouble()*2.0, 0.0, 1); // temps_shop
			equations[13] = new Equation(gen,0.5+gen.nextDouble()*2.0, 0.0, 1); // temps_forge
			equations[14] = new Equation(gen,4+gen.nextInt(12), 0.0, 1); // temps_res			
			equations[15] = new Equation(gen,1.5, 1.0, 1); // coeff_achat
			equations[16] = new Equation(gen,0.75, 1.0, 1); // coeff_vente
			equations[17] = new Equation(gen,1.0, true, 0); // multiplicateur_res
			equations[18] = new Equation(gen,0.02, 1.0, 0); // chance_magique
			equations[19] = new Equation(gen,0.01, 1.0, 0); // chance_rare
			equations[20] = new Equation(gen,0.02, 1.0, 0); // chance_qualite
			equations[21] = new Equation(gen,1.0, true, 0); // quantite_drop
			equations[22] = new Equation(gen,0.1, constantes[2], 0); // puissance_ench_inf
			equations[23] = new Equation(gen,1.0, true, 0); // multiplicateur_or
			equations[24] = new Equation(gen,1.0, true, 0); // ed_specific_monster
			equations[25] = new Equation(gen,0.1, 1.0, 1); // chance_fuite
			equations[26] = new Equation(gen,1.0, 0.0, 1); // temps_fuite
			equations[27] = new Equation(gen,0.5+gen.nextDouble()*2.0, 0.0, 1); // initiative
			equations[28] = new Equation(gen,0.0, 1.0, 1); // proba_immunite_final
			equations[29] = new Equation(gen,1.0, true, 0); // facteur_temps
			equations[30] = new Equation(gen,0.0, true, 1); // epines
			equations[31] = new Equation(gen,0.0, 1.0+gen.nextDouble()*4.0, 0); // represailles
			equations[32] = new Equation(gen,0.0, 1.0+gen.nextDouble()*4.0, 0); // necrophagie
			equations[33] = new Equation(gen,1.0+gen.nextDouble()*4.0, 0.0, 1); // temps_craft
			equations[34] = new Equation(gen,0.4+gen.nextDouble()*0.5, 1.0, 1); // rendement
			equations[35] = new Equation(gen,1.0, 0.0, 0); // economie_orbe
			equations[36] = new Equation(gen,gen.nextInt(5), true, 1); // niveau_boutique	
			equations[37] = new Equation(gen,1+gen.nextInt(5), true, 0); // taille_boutique
			equations[38] = new Equation(gen,0.0, true, 1); // detection_piege
			equations[39] = new Equation(gen,1.0, true, 0); // bonus_initiative_piege
			equations[40] = new Equation(gen,1.0, true, 0); // bonus_resistance_vs_piege
			equations[41] = new Equation(gen,0.0, true, 1); // rente_par_seconde
			equations[42] = new Equation(gen,3+gen.nextInt(7), true, 0); // points_par_niveau
			equations[43] = new Equation(gen,1.0, true, 0); // bonus_xp
			equations[44] = new Equation(gen,1.0, true, 0); // modif_exp_hlev
			equations[45] = new Equation(gen,0.0, 1.0, 1); // modif_exp_lowlev
			equations[46] = new Equation(gen,0.1, true, 2); // get_zone_level et get_zone_max_level
			equations[47] = new Equation(gen,1.0, true, 2); // gold_drop
			equations[48] = new Equation(gen,1.2, true, 3); // monster_level correction used in monster_points_for_level
			equations[49] = new Equation(gen,1.1, true, 0); // boss_level (for champions)
		}

		if(seed == 0)
		{
			for(int i=0; i< StaticItem.nb_pos-1; i++) binVar[i] = true;
			constantes[0] = 5.0; // vie de départ;
			constantes[1] = 0.20; // proba_rencontrer_piege
			constantes[2] = 0.25; // puissance_ench_sup
			constantes[3] = 20; // points_initiaux
			constantes[4] = 0.1; // gold_death_penalty (proportion d'or perdu par mort)
			constantes[5] = 0.1; // proba_champion()
			constantes[6] = 100; // cout voyage dimensionnel
		}
		joueur = new Player(this,false);
	}
			
	public boolean slot_est_disponible(int slot){
		return binVar[slot];
	}
	public double proba_rencontrer_piege() {
		return constantes[1];
		}
		
	public double att_per_sec(double x) {
		if(seed==0) return classic_att_per_sec(x);
		return equations[0].eval(x);
		}
		
    public double pv_per_vita(double x) {
		if(seed==0) return classic_pv_per_vita(x);
		return equations[1].eval(x);
		}
		
    public double vie_max(double VITA, double CON) {
		return constantes[0] + pv_per_vita(CON)*VITA;
		}
	
	public double crit_proba(double x) {
		if(seed==0) return classic_crit_proba(x);
		return equations[2].eval(x);
		}
		
	public double multi_crit(double x) {
		if(seed==0) return classic_multi_crit(x);
		return equations[3].eval(x);
		}
		
	public double dmg_base(double x) {
		if(seed==0) return classic_dmg_base(x);
		return equations[4].eval(x);
		}
		
	public double reduc(double x) {
		if(seed==0) return classic_reduc(x);
		return equations[5].eval(x);
		}
		
	public double absorption(double x) {
		if(seed==0) return classic_absorption(x);
		return equations[6].eval(x);
		}

    public double esquive_proba(double ESQ,double PRC) {
		if(seed==0) return classic_esquive_proba(ESQ,PRC);
		else return equations[7].eval(ESQ)/equations[7].eval(ESQ+PRC+50.0);
		}
	
	public double vol_de_vie(double x) {
		if(seed==0) return classic_vol_de_vie(x);
		return equations[8].eval(x);
		}
	
    public double regen(double REGEN, double CON){
		if(seed==0) return classic_regen(REGEN,CON);
		return equations[9].eval(REGEN) * pv_per_vita(CON);
		}

	public double charge_max(double x) {
		if(seed==0) return classic_charge_max(x);
		return equations[10].eval(x);
		}
	
	public double temps_traque(double x) {
		if(seed==0) return classic_temps_traque(x);
		return equations[11].eval(x);
		}
		
	public double temps_shop(double x) {
		if(seed==0) return classic_temps_shop(x);
		return equations[12].eval(x);
		}
		
	public double temps_forge(double x) {
		if(seed==0) return classic_temps_forge(x);
		return equations[13].eval(x);
		}
		
	public double temps_res(double x, int niveau) {
		if(seed==0) return classic_temps_res(x,niveau);
		return equations[14].eval((4.0*x)/niveau);
	}

	public double coeff_achat(double x) {
		if(seed==0) return classic_coeff_achat(x);
		return equations[15].eval(x);
		}
		
	public double coeff_vente(double x) {
		if(seed==0) return classic_coeff_vente(x);
		return equations[16].eval(x);
		}

	public double multiplicateur_res(double x) {
		if(seed==0) return classic_multiplicateur_res(x);
		return equations[17].eval(x);
		}
		
	public double chance_magique(double x) {
		if(seed==0) return classic_chance_magique(x);
		return equations[18].eval(x);
		}
		
	public double chance_rare(double x) {
		if(seed==0) return classic_chance_rare(x);
		return equations[19].eval(x);
		}
		
	public double chance_qualite(double x) {
		if(seed==0) return classic_chance_qualite(x);
		return equations[20].eval(x);
		}
		
	public double quantite_drop(double x) {
		if(seed==0) return classic_quantite_drop(x);
		return equations[21].eval(x);
		}
		
	public double puissance_ench_inf(double x) {
		if(seed==0) return classic_puissance_ench_inf(x);
		return equations[22].eval(x);
		}

	public double puissance_ench_sup(double x) {
		return constantes[2];
		}
		
	public double multiplicateur_or(double x) {
		if(seed==0) return classic_multiplicateur_or(x);
		return equations[23].eval(x);
		}
		
	public double ed_specific_monster(double x) {
		if(seed==0) return classic_ed_specific_monster(x);
		return equations[24].eval(x);
		}
		
	public double chance_fuite(double x) {
		if(seed==0) return classic_chance_fuite(x);
		return equations[25].eval(x);
		}
		
	public double temps_fuite(double x) {
		if(seed==0) return classic_temps_fuite(x);
		return equations[26].eval(x);
		}
		
	public double initiative(double x) {
		if(seed==0) return classic_initiative(x);
		return equations[27].eval(x);
		}
		
	public double proba_immunite_final(double x) {
		if(seed==0) return classic_proba_immunite_final(x);
		return equations[28].eval(x);
		}
		
	public double facteur_temps(double x) {
		if(seed==0) return classic_facteur_temps(x);
		return equations[29].eval(x);
		}
		
	public double epines(double x) {
		if(seed==0) return classic_epines(x);
		return equations[30].eval(x);
		}
		
	public double represailles(double x) {
		if(seed==0) return classic_represailles(x);
		return equations[31].eval(x);
		}

	public double necrophagie(double x) {
		if(seed==0) return classic_necrophagie(x);
		return equations[32].eval(x);
		}
		
	public double temps_craft(double x) {
		if(seed==0) return classic_temps_craft(x);
		return equations[33].eval(x);
		}
		
	public double rendement(double x) {
		if(seed==0) return classic_rendement(x);
		return equations[34].eval(x);
		}
		
	public double economie_orbe(double x) {
		if(seed==0) return classic_economie_orbe(x);
		return equations[35].eval(x);
		}
		
	public double niveau_boutique(double x) {
		if(seed==0) return classic_niveau_boutique(x);
		return equations[36].eval(x);
		}
		
	public double taille_boutique(double x) {
		if(seed==0) return classic_taille_boutique(x);
		return equations[37].eval(x);
		}
		
	public double detection_piege(double x) {
		if(seed==0) return classic_detection_piege(x);
		return equations[38].eval(x);
		}
		
	public double bonus_initiative_piege(double x) {
		if(seed==0) return classic_bonus_initiative_piege(x);
		return equations[39].eval(x);
		}
		
	public double bonus_resistance_vs_piege(double x) {
		if(seed==0) return classic_bonus_resistance_vs_piege(x);
		return equations[40].eval(x);
		}
		
	public double rente_par_seconde(double x) {
		if(seed==0) return classic_rente_par_seconde(x);
		return equations[41].eval(x);
		}

	public double points_par_niveau(double x) {
		if(seed==0) return classic_points_par_niveau(x);
		return equations[42].eval(x);
		}

	public double bonus_xp(double x) {
		if(seed==0) return classic_bonus_xp(x);
		return equations[43].eval(x);
		}

	public double modif_exp_hlev(double x,int levdiff) {
		if(seed==0) return classic_modif_exp_hlev(x,levdiff);
		return equations[44].eval(x*levdiff*0.04);
		}

	public double modif_exp_lowlev(double x,int levdiff) {
		if(seed==0) return classic_modif_exp_lowlev(x,levdiff);
		return equations[45].eval((5.0*x+50.0)/-levdiff);
		}

    public double modif_exp_lvl(double BONUS_LOWLEV, double BONUS_HLEV, int levdiff)
	{
	if(levdiff >= 1) return modif_exp_hlev(BONUS_HLEV,levdiff);
		if(levdiff <= -1) return modif_exp_lowlev(BONUS_LOWLEV,levdiff);
		return 1.0;}

	public double points_initiaux(){
		return constantes[3];
		}
		
	public double gold_death_penalty(){
		return constantes[4];
	}
	
	public double proba_champion(){
		return constantes[5];
	}
	
	public double proba_trouver_piege(double td, double hidden_lvl)
	{
		double dp = detection_piege(td);
		return dp/(hidden_lvl+dp);
	}
	
	public int get_zone_level(int zone)
	{
		if(seed==0) return 10*zone+1;
		return (int)(equations[46].eval(zone)*10.0 + 0.5);
	}
	public int get_zone_max_level(int zone)
	{
		if(seed==0) return 10*zone+10;
		return (int)(equations[46].eval(zone+1)*10);
	}
	
	public double gold_drop(double level)
	{
		if(seed==0) return classic_gold_drop(level);
		return equations[47].eval(level);
	}
	
	public double monster_points_for_level(double lvl)	
	{
		if(seed==0) return Math.pow(lvl,1.9)*0.15 + 5.0*lvl + 10.0;
		return (equations[48].eval(lvl)*points_par_niveau(lvl) + points_initiaux());
	}
	
	public double boss_level(double lvl)	
	{
		if(seed==0) return classic_boss_level(lvl);
		return lvl*equations[49].eval(lvl);
	}
	
	public double travel_cost()
	{
		return constantes[6];
	}
	
	public String infos(World monde)
	{
		String res = String.format("Univers %d"+
			"\nNombre de voyages dimensionnels : %d"+
			"\n\nConstantes de l'univers :"+
			"\nVie de départ : %d"+
			"\nPoints de compétences initiaux : %d"+
			"\nPuissance maximale des enchantements : %.3f"+
			"\nPénalité d'or à chaque mort : %.3f%%"+
			"\nProbabilité de tomber sur un piège : %.3f%%"+
			"\nProbabilité de tomber sur un champion : %.1f%%"+
			"\nCoût d'un voyage dimensionnel : %d orbes de chaque type",
			seed,
			number_of_travel,
			(int)constantes[0], // vie de départ;
			(int)constantes[3], // points_initiaux
			constantes[2], // puissance_ench_sup
			100*constantes[4], // gold_death_penalty (proportion d'or perdu par mort)
			100*constantes[1], // proba_rencontrer_piege;
			100*constantes[5],  // proba_champion()
			(int)constantes[6]  // cout voyage dimensionnel()
			);
			
			int count = 0;
			res += "\n\nObjets disponibles :\n";
			for(int i=0; i< StaticItem.nb_pos-1; i++)
			{
				if(slot_est_disponible(i))
				{
					res += StaticItem.Emplacement[i] + ", ";
					count++;
				}
				if(count > 6)
				{
					res += "\n";
					count=0;
				}
			}
			if(count==0) res = res.substring(0, res.length()-1);
			res = res.substring(0, res.length()-2);
			res += ".";
			
			res += "\n\nRépartition de base des points des monstres :\n";
			for(int i=0; i< Player.nb_stats; i++)
			{
			if(Monster.coeff_std[i]>0.001)
				res += String.format("%s : %.2f%% des points\n",Player.stats_name[i],100*Monster.coeff_std[i]);
			}
			
			res += "\nInformations sur les zones :\n";
			for(int i=0; i< 15; i++)
			{
			res += monde.zones[i].name + " (" + get_zone_level(i) + "-" + get_zone_max_level(i) + ")\n";
			res += String.format("  Niveau des monstres : minimum %d (%.0f points), moyen %.2f (%.0f points), maximum %d (%.0f points)\n",get_zone_level(i),
			monster_points_for_level(get_zone_level(i)),
			(get_zone_level(i)+get_zone_max_level(i))/2.0,
			monster_points_for_level((get_zone_level(i)+get_zone_max_level(i))/2.0),
			get_zone_max_level(i),
			monster_points_for_level(get_zone_max_level(i)));
			res += String.format("  Niveau des champions : minimum %d (%.0f points), moyen %.2f (%.0f points), maximum %d (%.0f points)\n",
			(int)boss_level(get_zone_level(i)),
			monster_points_for_level((int)boss_level(get_zone_level(i)))*1.5,
			(boss_level(get_zone_level(i))+boss_level(get_zone_max_level(i)))/2.0,
			monster_points_for_level((boss_level(get_zone_level(i))+boss_level(get_zone_max_level(i)))/2.0)*1.5,
			(int)boss_level(get_zone_max_level(i)),
			monster_points_for_level((int)boss_level(get_zone_max_level(i)))*1.5);
			}
			
		return res;
	}
	
	public void save()
    {
	try{
	    //use buffering
	    OutputStream file = new FileOutputStream( joueur.name+".theory" );
	    OutputStream buffer = new BufferedOutputStream( file );
	    ObjectOutput output = new ObjectOutputStream( buffer );
	    joueur.mob = null;
	    output.writeObject(this);
	    output.close();
	} 
	catch(Exception ex){
	    System.out.println(ex);
	    System.out.println("Can't write in \"" + joueur.name +".theory\"");
	}
    }
 }