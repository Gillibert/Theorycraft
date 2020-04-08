import java.io.*;
import java.util.*; 
 
 public class Universe implements Serializable {
	private TheoryGenerator gen;
	public NameGenerator nameGenerator;
	public Player joueur;
	public int seed;
	public int numberOfTravels;
	public int nuberSlotsAvailable;
	public Equation[] equations;
	public Equation[] sortedEquations;
	
	public double[] divine_cap;
	public double[] constantes;
	public double[] constantes_min;
	public double[] constantes_max;
	
	public double[] points_divins; // répartition des points divins
	public static int nb_universe_stats = Local.UNIVERSE_STATS_NAME.length;  // nombre de paramètres modifiables de l'univers
	public static int nb_universe_equations = Local.UNIVERSE_EQUATIONS_NAME.length;
			
	public ArrayList<Integer> ItemUnlockOrder;
	private boolean[] binVar;
	public Map map;
	static private final long serialVersionUID = 42;
	

	
	// IAS(0) DMG(1) REDUC(2) ABS(3) ESQ(4) PRC(5) LCK(6) CRT(7)
    // VDV(8) VITA(9) CON(10) REGEN(11) RESUR(12) LOAD(13) RUN(14) 
    // RESF(15) MF(16) RF(17) QALF(18) QTYF(19) POWF(20) GF(21)
    // ED_MV(22) ED_ANI(23) ED_HUM(24) ED_PV(25) ED_DEM(26) ED_CHAMP(27)
    // ESTI(28) FLEE(29) FLEE_SPD(30) INIT(31) IMUN_FINAL(32) TIME_SPD(33) 
	// EPIN(34) REP(35) NECRO(36) CRAFT_SPD(37) CRAFT_REND(38) ECO_ORB(39) 
	// SHOP_LEVEL(40) SHOP_SIZE(41) TRAP_DET(42) TRAP_INIT(43) TRAP_RES(44)
	// RENTE(45) EDUC(46) BONUS_XP(47) BONUS_LOWLEV(48) BONUS_HLEV(49)
	// REDUC_PEN(50) ZONE_ACCESS (51) DIEU(52) FIRST_STRIKE(53)
	// EQ_MASTER(54) CONST_MASTER(55) LIGHTER_RES(56) LIGHTER_EQP(57)
	// COLD_RES(58) HOT_RES(59) PRECI_RES(60) 
	// COLD_BONUS(61) HOT_BONUS(62) PRECI_BONUS(63) OVERLOAD_RES(64) 
	// UNDERLOAD_BONUS(65) ACHI_BONUS(66) HOLIDAY_BONUS(67)
	// SHOPPING_ADDICT(68) DISCOUNT_SPEC(69)

	
	public static int IAS = 0;
	public static int DMG = 1;
	public static int REDUC = 2;
	public static int ABS = 3;
	public static int ESQ = 4;
	public static int PRC = 5;
	public static int LCK = 6;
	public static int CRT = 7;
	public static int VDV = 8;
	public static int VITA = 9;
	public static int CON = 10;
	public static int REGEN = 11;
	public static int RESUR = 12;
	public static int LOAD = 13;
	public static int RUN = 14;
	public static int RESF = 15;
	public static int MF = 16;
	public static int RF = 17;
	public static int QALF = 18;
	public static int QTYF = 19;
	public static int POWF = 20;
	public static int GF = 21;
	public static int ED_MV = 22;
	public static int ED_ANI = 23;
	public static int ED_HUM = 24;
	public static int ED_PV = 25;
	public static int ED_DEM = 26;
	public static int ED_CHAMP = 27;
	public static int ESTI = 28;
	public static int FLEE = 29;
	public static int FLEE_SPD = 30;
	public static int INIT = 31;
	public static int IMUN_FINAL = 32;
	public static int TIME_SPD = 33;
	public static int EPIN = 34;
	public static int REP = 35;
	public static int NECRO = 36;
	public static int CRAFT_SPD = 37;
	public static int CRAFT_REND = 38;
	public static int ECO_ORB = 39;
	public static int SHOP_LEVEL = 40;
	public static int SHOP_SIZE = 41;
	public static int TRAP_DET = 42;
	public static int TRAP_INIT = 43;
	public static int TRAP_RES = 44;
	public static int RENTE = 45;
	public static int EDUC = 46;
	public static int BONUS_XP = 47;
	public static int BONUS_LOWLEV = 48;
	public static int BONUS_HLEV = 49;
	public static int REDUC_PEN = 50;
	public static int ZONE_ACCESS = 51;
	public static int DIEU = 52;
	public static int FIRST_STRIKE = 53;
	public static int EQ_MASTER = 54;
	public static int CONST_MASTER = 55;
	public static int LIGHTER_RES = 56;
	public static int LIGHTER_EQP = 57;
	public static int COLD_RES = 58;
	public static int HOT_RES = 59;
	public static int PRECI_RES = 60;
	public static int COLD_BONUS = 61;
	public static int HOT_BONUS = 62;
	public static int PRECI_BONUS = 63;	
	public static int OVERLOAD_RES = 64;
	public static int UNDERLOAD_BONUS = 65;
	public static int ACHI_BONUS = 66;
	public static int HOLIDAY_BONUS = 67;
	public static int SHOPPING_ADDICT = 68;
	public static int DISCOUNT_SPEC = 69;

	
	Universe(int sd)
	{
		numberOfTravels = 0;
		gen = new TheoryGenerator(sd);
		
		points_divins = new double[nb_universe_stats+nb_universe_equations];
		for(int i=0; i<nb_universe_stats+nb_universe_equations; i++) 
			{points_divins[i]=0;}
	
		constantes = new double[Local.UNIVERSE_STATS_NAME.length];
		constantes_min = new double[Local.UNIVERSE_STATS_NAME.length];
		constantes_max = new double[Local.UNIVERSE_STATS_NAME.length];
		
		binVar = new boolean[StaticItem.nb_pos-1];
		seed = sd;
		
		constantes_min[0] = 1.0; // vie de départ()
		constantes_min[1] = 5.0; // points_initiaux()
		constantes_min[2] = 0.0; // proba_rencontrer_piege()
		constantes_min[3] = 0.0; // proba_promo()
		constantes_min[4] = 0.0; // proba_clearance_sale()
		constantes_min[5] = 0.0; // proba_champion()
		constantes_min[6] = 0.0; // proba_super_champion()
		constantes_min[7] = 0.0; // proba_ressource()
		constantes_min[8] = 0.0; // penalty_for_bad_material()
		constantes_min[9] = 0.0; // penalty_for_new_challenge()
		constantes_min[10] = 0.0; // penalty_for_travel
		constantes_min[11] = 0.0; // penalty_for_dimensional_travel()
		constantes_min[12] = 0.0; // penalty_for_death()
		constantes_min[13] = 0.0; // base_gold_penalty_for_death()
		constantes_min[14] = 0.0; // plage_random()
		constantes_min[15] = 0.0; // qualite_max()
		constantes_min[16] = 0.0; // experience_orbe()
		constantes_min[17] = -273.15; // temperature_ideale()
		constantes_min[18] = 0.0; // niveau_boutique_pour_niveau_zone()
		constantes_min[19] = 0.0; // quantite_ressource_base_drops()
		constantes_min[20] = 0.0; // quantite_ressource_base_marchands()
		constantes_min[21] = 10.0; // nombre_zones()
		constantes_min[22] = 0.0; // travel_cost()
		constantes_min[23] = 1.5; // mul_points_competences_champions()
		constantes_min[24] = 20.0; // mul_points_competences_super_champions()
		constantes_min[25] = 0.0; // efficacite_base()
		constantes_min[26] = 0.0; // puissance_ench_sup()
		constantes_min[27] = 3.0; // nombre_maximal_coup()
		
		constantes_max[0] = 100; // vie de départ()
		constantes_max[1] = 200; // points_initiaux()
		constantes_max[2] = 1.0; // proba_rencontrer_piege()
		constantes_max[3] = 1.0; // proba_promo()
		constantes_max[4] = 1.0; // proba_clearance_sale()
		constantes_max[5] = 1.0; // proba_champion()
		constantes_max[6] = 1.0; // proba_super_champion()
		constantes_max[7] = 1.0; // proba_ressource()
		constantes_max[8] = 0.6; // penalty_for_bad_material()
		constantes_max[9] = 200; // penalty_for_new_challenge()
		constantes_max[10] = 10; // penalty_for_travel
		constantes_max[11] = 200; // penalty_for_dimensional_travel()
		constantes_max[12] = 10; // penalty_for_death()
		constantes_max[13] = 1.0; // base_gold_penalty_for_death()
		constantes_max[14] = 1.6; // plage_random()
		constantes_max[15] = 2.0; // qualite_max()
		constantes_max[16] = 500.0; // experience_orbe()
		constantes_max[17] = 500.0; // temperature_ideale()
		constantes_max[18] = 3.0; // niveau_boutique_pour_niveau_zone()
		constantes_max[19] = 4.0; // quantite_ressource_base_drops()
		constantes_max[20] = 100.0; // quantite_ressource_base_marchands()
		constantes_max[21] = 42.2; // nombre_zones()
		constantes_max[22] = 500; // travel_cost()
		constantes_max[23] = 5.0; // mul_points_competences_champions()
		constantes_max[24] = 50.0; // mul_points_competences_super_champions()
		constantes_max[25] = 5.0; // efficacite_base()
		constantes_max[26] = 0.6; // puissance_ench_sup()
		constantes_max[27] = 10000; // nombre_maximal_coup()
		
		constantes[0] = (int)(gen.nextInt(8)+6); // vie de départ()
		constantes[1] = (int)(gen.nextInt(25)+8.0); // points_initiaux()
		constantes[2] = 0.05+gen.nextDouble()*0.40; // proba_rencontrer_piege()
		constantes[3] = 0.05+gen.nextDouble()*0.20; // proba_promo()
		constantes[4] = 0.01*(gen.nextInt(6)+2.0); // proba_clearance_sale()
		constantes[5] = 0.01*(gen.nextInt(10)+5.0); // proba_champion()
		constantes[6] = 0.001*(gen.nextInt(10)+2.0); // proba_super_champion()
		constantes[7] = 0.15+gen.nextDouble()*0.3; // proba_ressource()
		constantes[8] = (0.2+gen.nextDouble()*0.4); // penalty_for_bad_material()
		constantes[9] = (int)(gen.nextInt(131)+15); // penalty_for_new_challenge()
		constantes[10] = (int)(gen.nextInt(4)+1); // penalty_for_travel
		constantes[11] = (int)(gen.nextInt(151)+5); // penalty_for_dimensional_travel()
		constantes[12] = 1.0+gen.nextDouble()*4.0; // penalty_for_death()
		constantes[13] = (gen.nextInt(16)+2)*0.01; // base_gold_penalty_for_death()	
		constantes[14] = 0.01*(gen.nextInt(76)+2.0); // plage_random()
		constantes[15] = 0.4+gen.nextDouble()*0.2; // qualite_max()
		constantes[16] = (int)(gen.nextInt(100)+250); // experience_orbe()
		constantes[17] = 5.0+gen.nextDouble()*20.0; // temperature_ideale()
		constantes[18] = 0.4+gen.nextDouble()*0.6; // niveau_boutique_pour_niveau_zone()
		constantes[19] = 0.7+gen.nextDouble()*0.6; // quantite_ressource_base_drops()
		constantes[20] = 2.0+gen.nextDouble()*4.0; // quantite_ressource_base_marchands()
		constantes[21] = (int)(gen.nextInt(15)+12); // nombre_zones()
		constantes[22] = (int)(gen.nextInt(300)+50); // travel_cost()
		constantes[23] = 1.5+gen.nextDouble()*2.0; // mul_points_competences_champions()
		constantes[24] = 20.0+gen.nextDouble()*20.0; // mul_points_competences_super_champions()
		constantes[25] = 0.2+gen.nextDouble()*0.8; // efficacite_base()
		constantes[26] = 0.20+gen.nextDouble()*0.30; // puissance_ench_sup()
		constantes[27] = (int)(gen.nextInt(960)+20); // nombre_maximal_coup()
		
		equations = new Equation[nb_universe_equations];
			
		equations[0] = new Equation(gen, 1, 10, 1.0, true, 1.5, 0.8); // att_per_sec
		equations[1] = new Equation(gen, 1, 11, 1.0, true, 1.0, 0.5); // pv_per_vita 
		equations[2] = new Equation(gen, 1, 12, 0.02, 1.0, 100.0, 0.6); // crit_proba
		equations[3] = new Equation(gen, 1, 13, 1.25, true, 2.5, 0.65); // multi_crit
		equations[4] = new Equation(gen, 1, 14, 0.5, true, 10.0, 0.9); // dmg_base
		equations[5] = new Equation(gen, 1, 15, 1.0, false, 1.2,0.7); // reduc
		equations[6] = new Equation(gen, 1, 16, 0.0, true, 10.0, 0.65); // absorption
		equations[7] = new Equation(gen, 1, 17, 10.0, true, 20.0, 1.0); // used in esquive_proba
		equations[8] = new Equation(gen, 1, 18, 0.0, 1.0, 100.0, 0.35); // vol_de_vie
		equations[9] = new Equation(gen, 1, 19, 2.5, true, 8.0, 0.9); // regen
		equations[10] = new Equation(gen, 1, 20, 10+gen.nextInt(10), true, 200.0, 0.6); // charge maximale
		equations[11] = new Equation(gen, 1, 21, 0.5+gen.nextDouble()*1.5, 0.0, 30.0, 0.5); // temps_traque
		equations[12] = new Equation(gen, 1, 22, 0.5+gen.nextDouble()*2.0, 0.0, 35.0, 0.5); // temps_shop
		equations[13] = new Equation(gen, 1, 23, 0.5+gen.nextDouble()*2.5, 0.0, 35.0, 0.5); // temps_forge
		equations[14] = new Equation(gen, 1, 24, 4+gen.nextInt(12), 0.0, 60.0, 0.65); // temps_res			
		equations[15] = new Equation(gen, 1, 25, 1.5, 1.0, 30.0, 0.6); // coeff_achat
		equations[16] = new Equation(gen, 1, 26, 0.75, 1.0, 30.0, 0.6); // coeff_vente
		equations[17] = new Equation(gen, 1, 27, 1.0, true, 3.0, 0.4); // multiplicateur_res
		equations[18] = new Equation(gen, 1, 28, 0.02, 1.0, 60.0, 0.5); // chance_magique
		equations[19] = new Equation(gen, 1, 29, 0.01, 1.0, 80.0, 0.5); // chance_rare
		equations[20] = new Equation(gen, 1, 30, 0.02, 1.0, 70.0, 0.5); // chance_qualite
		equations[21] = new Equation(gen, 1, 31, 2.0, 50.0, 300, 0.3); // quantite_drop
		equations[22] = new Equation(gen, 1, 32, 0.1, 1.0, 30.0, 0.75); // puissance_ench_inf
		equations[23] = new Equation(gen, 1, 33, 1.0, true, 5.0, 0.35); // multiplicateur_or
		equations[24] = new Equation(gen, 1, 34, 1.0, true, 1.2, 0.55); // ed_mort_vivant
		equations[25] = new Equation(gen, 1, 35, 1.0, true, 1.2, 0.55); // ed_animal
		equations[26] = new Equation(gen, 1, 36, 1.0, true, 1.4, 0.6); // ed_humain
		equations[27] = new Equation(gen, 1, 37, 1.0, true, 1.2, 0.55); // ed_peau_verte
		equations[28] = new Equation(gen, 1, 38, 1.0, true, 1.0, 0.55); // ed_demon
		equations[29] = new Equation(gen, 1, 39, 1.0, true, 1.0, 0.45); // ed_champion
		equations[30] = new Equation(gen, 1, 40, 0.1, 1.0, 50.0, 0.8); // chance_fuite
		equations[31] = new Equation(gen, 1, 41, 1.0, false, 3.0, 0.8); // temps_fuite
		equations[32] = new Equation(gen, 1, 42, 0.5+gen.nextDouble()*2.0, 0.0,  75.0, 0.7); // initiative
		equations[33] = new Equation(gen, 1, 43, 0.0, 1.0,  50.0, 0.75); // proba_immunite_final
		equations[34] = new Equation(gen, 1, 44, 1.0, true, 2.0, 0.5); // facteur_temps
		equations[35] = new Equation(gen, 1, 45, 0.0, true, 10.0, 0.7); // epines
		equations[36] = new Equation(gen, 1, 46, 0.0, 1.0+gen.nextDouble()*4.0, 180.0, 0.4); // represailles
		equations[37] = new Equation(gen, 1, 47, 0.0, 1.0+gen.nextDouble()*4.0, 170.0, 0.4); // necrophagie
		equations[38] = new Equation(gen, 1, 48, 0.5+gen.nextDouble()*1.5, 0.0, 30, 1.0); // temps_craft
		equations[39] = new Equation(gen, 1, 49, 0.4+gen.nextDouble()*0.5, 1.0,  75.0, 0.7); // rendement
		equations[40] = new Equation(gen, 1, 50, 1.0, false, 2.0, 0.25); // economie_orbe
		equations[41] = new Equation(gen, 1, 51, 1.0, true, 5.0, 0.4); // niveau_boutique	
		equations[42] = new Equation(gen, 1, 52, 3+gen.nextInt(3), 50.0, 400.0, 0.5); // taille_boutique
		equations[43] = new Equation(gen, 1, 53, 0.0, true, 4.0, 1.4); // detection_piege
		equations[44] = new Equation(gen, 1, 54, 1.0, true, 1.2,0.35); // bonus_initiative_piege
		equations[45] = new Equation(gen, 1, 55, 1.0, true, 0.8,0.3); // bonus_resistance_vs_piege
		equations[46] = new Equation(gen, 1, 56, 0.0, true, 300.0, 0.65); // rente_par_rencontre
		equations[47] = new Equation(gen, 1, 57, 4+gen.nextInt(3), true, 4.0,0.3); // points_par_niveau
		equations[48] = new Equation(gen, 1, 58, 1.0, true, 1.8,0.35); // bonus_xp
		equations[49] = new Equation(gen, 1, 59, 1.0, true, 0.03,0.3); // modif_exp_hlev
		equations[50] = new Equation(gen, 1, 60, 0.0, 1.0, 50, 0.5); // modif_exp_lowlev
		equations[51] = new Equation(gen, 1, 0.61, 0.0, true, 350.0, 1.2); // get_zone_level et get_zone_max_level
		equations[52] = new Equation(gen, 1, 0.62, 1.0, true, 12.0, 1.1); // gold_drop
		equations[53] = new Equation(gen, 0, 0.63, 10.0, true, 75.0, 1.45,0.1); //monster_points_for_level
		equations[54] = new Equation(gen, 1, 0.64, 2.0, true, 20.0, 1.45,0.1); // niveau_champion
		equations[55] = new Equation(gen, 0, 0.65, 1.0, true, 12.0, 2.6); // traps_dmg_for_level
		equations[56] = new Equation(gen, 1, 66, 1.0, false, 0.5, 0.6); // penalty_reduction
		equations[57] = new Equation(gen, 1, 67, 1.0, true, 4.0, 0.3); // zone_multiplier
		equations[58] = new Equation(gen, 1, 68, 1.0, true, 0.8, 0.35); // points_divins_multiplier
		equations[59] = new Equation(gen, 1, 0.69, 0.0, true, 30.0, 0.4); // points_divins_pour_x_orbe
		equations[60] = new Equation(gen, 1, 70, 1.0, true, 1.5, 0.5); // multi_premier_coup
		equations[61] = new Equation(gen, 1, 0.71, 0.0, true, 2500, 0.9,0.1); // xp_for_level
		equations[62] = new Equation(gen, 1, 72, 1.0, true, 1.5, 0.28); // divine_cap_eq
		equations[63] = new Equation(gen, 1, 73, 1.0, true, 1.8, 0.28); // divine_cap_const
		equations[64] = new Equation(gen, 1, 0.74, 4.5, true, 80, 0.4); // points_divins_pour_niveau
		equations[65] = new Equation(gen, 1, 75, 1.0, false, 2.0, 0.6); // resources_weight_multiplier
		equations[66] = new Equation(gen, 1, 76, 1.0, false, 0.8, 0.3); // equipment_weight_multiplier
		equations[67] = new Equation(gen, 1, 0.77, -0.075, 1.0,  400.0, 1.0); // proba_orbe_niveau_drop
		equations[68] = new Equation(gen, 1, 0.75, 0.0, true, 75.0, 0.5); // points_competence_orbes
		equations[69] = new Equation(gen, 1, 79, 1.0, false, 0.7, 0.6); // resistance_froid
		equations[70] = new Equation(gen, 1, 80, 1.0, false, 0.6, 0.6); // resistance_chaud
		equations[71] = new Equation(gen, 1, 81, 1.0, false, 0.8, 0.55); // resistance_precipitations
		equations[72] = new Equation(gen, 1, 82, 1.0, true, 0.5, 0.35); // affinite_froid
		equations[73] = new Equation(gen, 1, 83, 1.0, true, 0.55, 0.35); // affinite_chaud
		equations[74] = new Equation(gen, 1, 84, 1.0, true, 1.0, 0.4); // affinite_precipitations
		equations[75] = new Equation(gen, 1, 0.85, 1.0, true, 1.5, 0.3); // get_current_temperature
		equations[76] = new Equation(gen, 1, 0.86, 1.0, true, 5.0, 1.0); // get_current_precipitation
		equations[77] = new Equation(gen, 0, 0.87, 1.0, false, 50.0, 1.0); // base_overload_penalty
		equations[78] = new Equation(gen, 1, 88, 1.0, false, 1.0, 0.7); // resistance_surcharge
		equations[79] = new Equation(gen, 1, 89, 1.0, true, 0.65, 0.45); // affinite_souscharge
		equations[80] = new Equation(gen, 1, 90, 1.0, true, 0.6, 0.4); // affinite_hautfaits
		equations[81] = new Equation(gen, 1, 0.91, 1.0, true, 4.0, 0.6); // multiplicateur_res_marchands
		equations[82] = new Equation(gen, 1, 0.645, 2.0, true, 20.0, 2.10,0.1); // niveau_super_champion
		equations[83] = new Equation(gen, 1, 93, 1.5, true, 1.0, 0.55); // clearance_sale_inventory_multiplier
		equations[84] = new Equation(gen, 1, 0.94, 1.0, true, 5.0, 0.3); // bonus_haut_faits_base
		equations[85] = new Equation(gen, 1, 0.95, 0.0, true, 120.0, 0.75); // niveau_efficacite
		equations[86] = new Equation(gen, 1, 0.96, 0.0, true, 80.0, 0.80); // niveau_enchantement
		equations[87] = new Equation(gen, 1, 0.875, 1.0, true, 1.0, 0.4); // base_underload_bonus
		equations[88] = new Equation(gen, 1, 98, 0.8, false, 0.5, 0.2); // discount_multiplier
		equations[89] = new Equation(gen, 1, 0.646, 2.0, true, 5.0, 2.2); // niveau_pieges
		equations[90] = new Equation(gen, 1, 90.5, 1.0, true, 2.0, 0.2); // affinite_vacances
		
		for(int i=0; i< nb_universe_equations; i++)
			equations[i].id = i;
		
		int preciousEquation[] = {40,48,52,53,55,58,59,61,62,64,68};
		for(int i : preciousEquation)
			equations[i].divine_cap=15+gen.nextInt(4)*5;

		sortedEquations = Arrays.copyOf(equations, equations.length);
		Arrays.sort(sortedEquations, new Equation.SortByOrder());
		
		divine_cap = new double[nb_universe_stats+nb_universe_equations];
		
		for(int i=0; i< nb_universe_stats; i++)
			divine_cap[i]=50;
		
		for(int i=0; i< nb_universe_equations; i++)
			divine_cap[i+nb_universe_stats]=sortedEquations[i].divine_cap;
		
		map = new Map(gen);
		
		nuberSlotsAvailable = 0;
		if(seed != 0)
		{
			for(int i=0; i< StaticItem.nb_pos-1; i++) {
				if (gen.nextDouble() > 0.33 && i!=12) {binVar[i] = true; nuberSlotsAvailable+=1;}
				else binVar[i] = false;
			}
			binVar[12] = true; // les armes existent toujours
			nuberSlotsAvailable+=1;
		}
		if(seed == 0)
		{
			for(int i=0; i< StaticItem.nb_pos-1; i++) {binVar[i] = true; nuberSlotsAvailable+=1;}
			binVar[7] = false; 
			nuberSlotsAvailable=nuberSlotsAvailable-1;
		}
		
		ItemUnlockOrder = new ArrayList<Integer>(nuberSlotsAvailable);
	
		for(int i=0; i< StaticItem.nb_pos-1; i++)
			if (binVar[i] && i!=12)
				ItemUnlockOrder.add(i);
		
		Collections.shuffle(ItemUnlockOrder,gen.gen);
		ItemUnlockOrder.add(0, 12);
		
		joueur = new Player(this,false);
	}
			
	public boolean slot_est_disponible(int slot){
		return binVar[slot];
	}
	public double att_per_sec(double x) {
		return equations[0].eval(x);
		}
		
    public double pv_per_vita(double x) {
		return equations[1].eval(x);
		}
	
		
	public double crit_proba(double x) {
		return equations[2].eval(x);
		}
		
	public double multi_crit(double x) {
		return equations[3].eval(x);
		}
		
	public double dmg_base(double x) {
		return equations[4].eval(x);
		}
		
	public double reduc(double x) {
		return equations[5].eval(x);
		}
		
	public double absorption(double x) {
		return equations[6].eval(x);
		}

    public double esquive_proba(double ESQ,double PRC) {
		return equations[7].eval(ESQ)/equations[7].eval(ESQ+PRC+50.0);
		}
	
	public double vol_de_vie(double x) {
		return equations[8].eval(x);
		}
	
    public double regen(double REGEN){
		return equations[9].eval(REGEN);
		}

	public double charge_max(double x) {
		return equations[10].eval(x);
		}
	
	public double temps_traque(double x) {
		return equations[11].eval(x);
		}
		
	public double temps_shop(double x) {
		return equations[12].eval(x);
		}
		
	public double temps_forge(double x) {
		return equations[13].eval(x);
		}
		
	public double temps_res(double x) {
		return equations[14].eval(x);
	}

	public double coeff_achat(double x) {
		return equations[15].eval(x);
		}
		
	public double coeff_vente(double x) {
		return equations[16].eval(x);
		}

	public double multiplicateur_res(double x) {
		return equations[17].eval(x);
		}
		
	public double chance_magique(double x) {
		return equations[18].eval(x);
		}
		
	public double chance_rare(double x) {
		return equations[19].eval(x);
		}
		
	public double chance_qualite(double x) {
		return equations[20].eval(x);
		}
		
	public double quantite_drop(double x) {
		return equations[21].eval(x);
		}
		
	public double puissance_ench_inf(double x) {
		return equations[22].eval(x)*puissance_ench_sup();
		}
		
	public double multiplicateur_or(double x) {
		return equations[23].eval(x);
		}
		
	public double ed_specific_monster(double x, int tag) {
		return equations[24+tag].eval(x);
		}
		
	public double ed_mort_vivant(double x) {
		return equations[24].eval(x);
		}
		
	public double ed_animal(double x) {
		return equations[25].eval(x);
		}
		
	public double ed_humain(double x) {
		return equations[26].eval(x);
		}
		
	public double ed_peau_verte(double x) {
		return equations[27].eval(x);
		}
		
	public double ed_demon(double x) {
		return equations[28].eval(x);
		}
		
	public double ed_champion(double x) {
		return equations[29].eval(x);
		}
				
	public double chance_fuite(double x) {
		return equations[30].eval(x);
		}
		
	public double temps_fuite(double x) {
		return equations[31].eval(x);
		}
		
	public double initiative(double x) {
		return equations[32].eval(x);
		}
		
	public double proba_immunite_final(double x) {
		return equations[33].eval(x);
		}
		
	public double facteur_temps(double x) {
		return equations[34].eval(x);
		}
		
	public double epines(double x) {
		return equations[35].eval(x);
		}
		
	public double represailles(double x) {
		return equations[36].eval(x);
		}

	public double necrophagie(double x) {
		return equations[37].eval(x);
		}
		
	public double temps_craft(double x) {
		return equations[38].eval(x);
		}
		
	public double rendement(double x) {
		return equations[39].eval(x);
		}
		
	public double economie_orbe(double x) {
		return equations[40].eval(x);
		}
		
	public double multiplicateur_niveau_boutique(double x) {
		return equations[41].eval(x);
		}
		
	public double taille_boutique(double x) {
		return equations[42].eval(x);
		}
		
	public double detection_piege(double x) {
		return equations[43].eval(x);
		}
		
	public double bonus_initiative_piege(double x) {
		return equations[44].eval(x);
		}
		
	public double bonus_resistance_vs_piege(double x) {
		return equations[45].eval(x);
		}
		
	public double rente_par_rencontre(double x) {
		return equations[46].eval(x);
		}

	public double points_par_niveau(double x) {
		return equations[47].eval(x);
		}

	public double bonus_xp(double x) {
		return equations[48].eval(x);
		}

	public double modif_exp_hlev(double x,double levdiff) {
		return equations[49].eval(x*levdiff);
		}

	public double modif_exp_lowlev(double x,double levdiff) {
		return equations[50].eval((5.0*x+50.0)/-levdiff);
		}

	
	public double get_zone_level(int zone)
	{
		if(zone==0) return 100;
		if(zone==1) return 2000;
		return Math.floor(equations[51].eval(zone-2)+1.0);
	}
	public double get_zone_max_level(int zone)
	{
		if(zone==0) return 200;
		if(zone==1) return 2500;
		return Math.floor(equations[51].eval(zone-1));
	}
	
	public double gold_drop(double level)
	{
		return equations[52].eval(level);
	}
	
	public double monster_points_for_level(double lvl)	
	{
		return (equations[53].eval(lvl) + points_initiaux());
	}
	
	public double niveau_champion(double lvl)	
	{
		return equations[54].eval(lvl);
	}
	
	public double traps_dmg_for_level(double lvl)
	{
		return equations[55].eval(lvl);
	}
	
	public double penalty_reduction(double x) {
		return equations[56].eval(x);
	}
	
	public double zone_multiplier(double x) {
		return equations[57].eval(x);
	}
	
	public double points_divins_multiplier(double x) {
		return equations[58].eval(x);
	}
	
	public double points_divins_pour_x_orbe(double x) {
		return equations[59].eval(x);
	}
	
	public double multi_premier_coup(double x) {
		return equations[60].eval(x);
	}
	
	public double xp_for_level(double x) {
		return equations[61].eval(x);
	}
	
	public double divine_cap_eq(double x) {
		return equations[62].eval(x);
	}
	
	public double divine_cap_const(double x) {
		return equations[63].eval(x);
	}
	
	public double points_divins_pour_niveau(double x) {
		return equations[64].eval(x);
	}
	
	public double resources_weight_multiplier(double x) {
		return equations[65].eval(x);
	}
	
	public double equipment_weight_multiplier(double x) {
		return equations[66].eval(x);
	}
	
	public double proba_orbe_niveau_drop(double x) {
		return equations[67].eval(x);
	}
	
	public double points_competence_orbes(double x) {
		return equations[68].eval(x);
	}
	
	public double resistance_froid(double x) {
		return equations[69].eval(x);
	}
	
	public double resistance_chaud(double x) {
		return equations[70].eval(x);
	}
	
	public double resistance_precipitations(double x) {
		return equations[71].eval(x);
	}	
	
	public double affinite_froid(double x) {
		return equations[72].eval(x);
	}
	
	public double affinite_chaud(double x) {
		return equations[73].eval(x);
	}
	
	public double affinite_precipitations(double x) {
		return equations[74].eval(x);
	}
	
	public double base_overload_penalty(double x) {
		return equations[77].eval(x);
	}
	
	public double resistance_surcharge(double x) {
		return equations[78].eval(x);
	}
	
	public double affinite_souscharge(double x) {
		return equations[79].eval(x);
	}
	
	public double affinite_hautfaits(double x) {
		return equations[80].eval(x);
	}
	
	public double multiplicateur_res_marchands(double x) {
		return equations[81].eval(x);
	}
	
	public double niveau_super_champion(double lvl)	{
		return equations[82].eval(lvl);
	}
	
	public double clearance_sale_inventory_multiplier(double x) {
		return equations[83].eval(x);
	}
	
	public double bonus_haut_faits_base(double x) {
		return equations[84].eval(x);
	}
	
	public double niveau_efficacite(double x) {
		return equations[85].eval(x);
	}
	
	public double niveau_enchantement(double x) {
		return equations[86].eval(x);
	}
	
	public double base_underload_bonus(double x) {
		return equations[87].eval(x);
	}
		
	public double discount_multiplier(double x) {
		return equations[88].eval(x);
	}
	
	public double niveau_pieges(double x) {
		return equations[89].eval(x);
	}
	
	public double affinite_vacances(double x) {
		return equations[90].eval(x);
	}
	
	public double points_divins_totaux(double x, double y, double lvl) {
		return points_divins_multiplier(x)*(points_divins_pour_x_orbe(y)+points_divins_pour_niveau(lvl));
	}
	
	public double modif_exp_lvl(double BONUS_LOWLEV, double BONUS_HLEV, double levdiff)
	{
	if(levdiff >= 1) return modif_exp_hlev(BONUS_HLEV,levdiff);
		if(levdiff <= -1) return modif_exp_lowlev(BONUS_LOWLEV,levdiff);
		return 1.0;}

	public double vie_depart() 
	{ return adjusted_constant(0); }
		
    public double vie_max(double VITA, double CON)
	{ return adjusted_constant(0) + pv_per_vita(CON)*VITA; }
	
	public double points_initiaux()
	{ return adjusted_constant(1); }
		
	public double proba_rencontrer_piege() 
	{ return adjusted_constant(2); }
	
	public double proba_promo()
	{ return adjusted_constant(3); }
	
	public double proba_clearance_sale()
	{ return adjusted_constant(4); }
	
	public double proba_champion()
	{ return adjusted_constant(5); }
	
	public double proba_super_champion()
	{ return adjusted_constant(6); }
	
	
	public double proba_trouver_piege(double td, double hidden_lvl)
	{
		double dp = detection_piege(td);
		return dp/(hidden_lvl+dp);
	}

	double proba_ressource()
	{ return adjusted_constant(7); }
	
	double penalty_for_bad_material()
    { return adjusted_constant(8); }
	
	double base_penalty_for_new_challenge()
	{ return adjusted_constant(9); }
	
	double base_penalty_for_travel()
	{ return adjusted_constant(10); }
	
	double base_penalty_for_dimensional_travel()
	{ return adjusted_constant(11); }
	
	double base_penalty_for_death()
	{ return adjusted_constant(12); }
	
	public double base_gold_penalty_for_death()
	{ return adjusted_constant(13); }
	
	double static_plage_random()
    { return adjusted_constant(14); }
	
	double plage_random()
    { return 1.0 + (Math.random() - 0.5) * adjusted_constant(14); }
	
	double qualite_max()
	{ return adjusted_constant(15); }
	
	double experience_orbe()
	{ return adjusted_constant(16); }

	double temperature_ideale()
	{ return adjusted_constant(17); }
	
	double niveau_boutique_pour_niveau_zone()
	{ return adjusted_constant(18); }
	
	double quantite_ressource_base_drops()
	{ return adjusted_constant(19); }
	
	double quantite_ressource_base_marchands()
	{ return adjusted_constant(20); }
	
	double nombre_zones()
	{ return adjusted_constant(21); }
	
	public double travel_cost()
	{ return adjusted_constant(22); }
	
	public double mul_points_competences_champions()
	{ return adjusted_constant(23); }
	
	public double mul_points_competences_super_champions()
	{ return adjusted_constant(24); }
	
	double efficacite_base()
	{ return adjusted_constant(25); }
	
	public double puissance_ench_sup() 
	{ return adjusted_constant(26); }
	
	public double nombre_maximal_coup() 
	{ return adjusted_constant(27); }
	
	double adjusted_constant(int idx)
	{ return adjust_constant(points_divins[idx],constantes[idx],constantes_min[idx], constantes_max[idx]); }
	
	
	public double get_adjusted_temperature(int i, double temperature_modifier)
	{
		double tk = map.temperature.get(i)+273.15;
		if(temperature_modifier > 0) tk = tk*equations[75].eval(temperature_modifier);
		else  tk = tk/equations[75].eval(-temperature_modifier);
		return tk-273.15;
	}
	
	public double get_current_temperature(int i)
	{
		return get_adjusted_temperature(i,map.current_temperature_modifier.get(i));
	}
	
	public double get_temperature(int i)
	{
		return map.temperature.get(i);
	}
	
	public double get_adjusted_precipitation(int i, double modif)
	{
		double res = map.precipitation.get(i);
		if(modif > 0) res = res*equations[76].eval(modif);
		else  res = res/equations[76].eval(-modif);
		return Math.min(res,1.0);
	}
	
	public double get_current_precipitation(int i)
	{
		return get_adjusted_precipitation(i,map.current_precipitation_modifier.get(i));
	}
	
	public double get_precipitation(int i)
	{
		return map.precipitation.get(i);
	}
	
	
	double adjust_constant(double pts,double default_value, double constantes_min, double constantes_max)
	{
		if(pts < 0)
		{
		double x = -pts*0.005;
		return constantes_min * (x/(x+1)) + default_value * (1-x/(x+1));
		}
		else
		{
		double x = pts*0.005;
		return constantes_max * (x/(x+1)) + default_value * (1-x/(x+1));
		}
	}
	
	public void save()
    {
	joueur.mob = null;
	System.out.println(Local.SAVING_GAME);
	try{
	    OutputStream file = new FileOutputStream( "heroes/" + joueur.name+".theory" );
	    OutputStream buffer = new BufferedOutputStream( file );
	    ObjectOutput output = new ObjectOutputStream( buffer );
	    joueur.mob = null;
	    output.writeObject(this);
	    output.close();
	} 
	catch(Exception ex){
		try{
	    OutputStream file = new FileOutputStream( "heroes/" + joueur.name+".back.theory" );
	    OutputStream buffer = new BufferedOutputStream( file );
	    ObjectOutput output = new ObjectOutputStream( buffer );
	    joueur.mob = null;
	    output.writeObject(this);
	    output.close();
		}
		catch(Exception ex2){
	    System.out.println(ex2);
	    System.out.println(String.format(Local.CANT_WRITE,joueur.name));
		}
	}
    }
 }