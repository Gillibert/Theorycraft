import java.util.ArrayList;

class Trap {

	public boolean masculin;
	public String name;
	public int level;
	public double speed;
	public double damage;
	public double hidden;

	public Trap(String n, int lev, boolean g)
	{
		level = lev;
		masculin = g;
		name = n;
		speed = Math.pow(lev*2,1.2);
		damage = Math.pow(lev*2.5,2.3)*0.03;
		hidden = lev*0.9 + 0.2*Math.random(); // Doit être centré sur le niveau du piège
	}

public static Trap[] trap_zoo =
{
new Trap("Toile d'araignée",        3*1,false),
new Trap("Papier tue mouche",       3*2,true),
new Trap("Pic anti-pigeons",        3*3,true),
new Trap("Piège à souris",          3*4,true),
new Trap("Caillou pointu",          3*5,true),
new Trap("Collet à lapins",         3*6,true),
new Trap("Brouillard",    			3*7,true),
new Trap("Pierre d'achoppement",    3*8,false),
new Trap("Piège à rats",            3*9,true),
new Trap("Pluie acide",             3*10,false),
new Trap("Lit de clous",            3*11,true),
new Trap("Cactus",                  3*12,true),
new Trap("Fil barbelé",             3*13,true),
new Trap("Makibishi",               3*14,true),
new Trap("Crevasse",                3*15,false),
new Trap("Glissement de terrain",   3*16,true),
new Trap("Feu de brousse",          3*17,true),
new Trap("Petit séisme",            3*18,true),
new Trap("Incendie de forêt",       3*19,true),
new Trap("Chausse-trape",           3*20,false),
new Trap("Fosse profonde",          3*21,false),
new Trap("Fosse piégée",            3*22,false),
new Trap("Fil barbelé concertina",  3*23,true),
new Trap("Dard empoisonné",         3*24,true),
new Trap("Fléchette empoisonnée",   3*25,false),
new Trap("Fosse d'acide",           3*26,false),
new Trap("Note explosive",          3*27,false),
new Trap("Scie circulaire",         3*28,false),
new Trap("Toile d'araignée géante", 3*29,false),
new Trap("Liane étrangleuse",       3*30,false),
new Trap("Piège à loup",            3*31,true),
new Trap("Piège à ours",            3*32,true),
new Trap("Piège à lion",            3*33,true),
new Trap("Colis piégé",             3*34,true),
new Trap("Arbalète automatique",    3*35,false),
new Trap("Fosse de lave",           3*36,false),
new Trap("Sable mouvant",           3*37,true),
new Trap("Tempête de glace",        3*38,false),
new Trap("Mine antipersonnel",      3*39,false),
new Trap("Mine antichar",      		3*40,false),
new Trap("Pluie de feu",            3*41,false),
new Trap("Lac de lave",             3*42,true),
new Trap("Avalanche",               3*43,false),
new Trap("Tornade",                 3*44,false),
new Trap("Avalanche de rochers",    3*45,false),
new Trap("Tempête de sable",        3*46,false),
new Trap("Tornade de sable",        3*47,false),
new Trap("Tempête de feu",          3*48,false),
new Trap("Geyser de lave",          3*49,true),
new Trap("Éruption volcanique",     3*50,false),
new Trap("Chute de météorite",      3*51,false),
new Trap("Chute de comète",         3*52,false),
new Trap("Tremblement de terre",    3*53,true),
new Trap("Tsunami",    		        3*54,true),
new Trap("Mine atomique",      		3*55,false)
};

public static int trap_zoo_size = trap_zoo.length;

public static Trap getTrap(int lev)
{
	int loclev = (int)Math.max(lev - 5.0 + Math.random()*10.0,1.0);
	int trapidx = loclev/3;
	if(trapidx >= trap_zoo_size) return trap_zoo[trap_zoo_size-1];
	Trap res = trap_zoo[trapidx];
	return res;
}

private String encounterText()
{
	String gender = "une ";
	if(masculin) gender = "un ";
	return "Vous tombez sur " + gender + NameGenerator.firstCharLowercase(name) + " (niveau " + level +").";
}
public boolean trapEncounter(Player p, boolean disp)
{
	boolean res = true;
	String gender = "la";
	if(masculin) gender = "le";
	if(disp) Game.MW.addLog(encounterText());
	double trap_find_proba = p.proba_trouver_piege(hidden);
	if (Math.random() < trap_find_proba)
		{
			if(disp) Game.MW.addLog(String.format("Réussite de la détection (score du piège : %.3f, probabilité de détection : %.2f%%).",hidden, 100*trap_find_proba));
			if(disp) Game.MW.addLog(String.format("%s évite entièrement %s.",p.name,NameGenerator.firstCharLowercase(name)));
		}
	else
	{
		if(disp) Game.MW.addLog(String.format("Échec de la détection (score du piège : %.2f, probabilité de détection : %.2f%%).",hidden, 100*trap_find_proba));
		double dmg_base = damage * (0.9+Math.random()*0.2);
		double bonus = p.bonus_initiative_piege();
		double trap_init = Player.initiative(speed) ;
		double player_init = p.plage_random()*p.initiative_piege();
		if(disp) Game.MW.addLog(String.format("Initiative face à un piège : %.2f secondes versus %.2f secondes.",player_init,trap_init));
		p.personal_wait(player_init,TimeStats.ACTIVITY_PIEGE);
		double dmg_red = p.resistance_vs_piege();
		if (player_init < trap_init)
			{
				if(disp) Game.MW.addLog("Résistance aux pièges doublée.");
				dmg_red = dmg_red/2;
			}
		double dmg_abs = p.absorption();
			
		double dmg = Math.max(dmg_base*dmg_red - dmg_abs,0.0); // pas de dégâts négatifs
		String trapRes = String.format("   %s inflige %.3f points de dégâts à %s (%.2f de base, %.2f%% de réduction, %.2f d'absorption)",this.name,dmg,p.name,dmg_base,100*(1.0-dmg_red),dmg_abs);
		if(disp) Game.MW.addLog(trapRes);
		p.vie -= dmg;
	}
	if (p.vie <= 0) {
		if(disp) Game.MW.addLog(p.name + " est mort.");
		p.meurt();
	}
	else {
		p.gain_xp((int)Math.pow(50*level/Math.max(1,p.level-level),0.85));
		res = false;
		}
	return res;
}
}
