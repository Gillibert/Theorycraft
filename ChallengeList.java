import java.util.ArrayList;

class ChallengeList {
public static ArrayList<Challenge> list;

static void init()
	{
	list = new ArrayList<Challenge>();
	Challenge tmp;
	
	list.add(new Challenge("On commence en douceur", 0,10));
	list.add(new Challenge("Donjons et dragons", 0,20));
	list.add(new Challenge("Inferno", 0,60));
	list.add(new Challenge("Le ciel est la limite", 0,100));
	list.add(new Challenge("This Is Sparta", 0,300));
	
	list.add(new Challenge("Mendiant", 1,			10000));
	list.add(new Challenge("Épicier", 1, 			100000));
	list.add(new Challenge("Trader", 1,  			1000000));
	list.add(new Challenge("Banquier", 1,			10000000));
	list.add(new Challenge("Bill Gates", 1,			100000000));
	list.add(new Challenge("Wirt The Peg-Leg", 1,	1000000000));
	
	tmp = new Challenge("Lapinou doit mourir", 2,	0);
	tmp.boss_name = "Lapinou";
	tmp.boss_level = 20; tmp.boss_tag = 1;
	tmp.boss_p_stats = new int[]{0,0,0};
	list.add(tmp);
	
	tmp = new Challenge("Un porc-épic qui pique", 2, 0);
	tmp.boss_name = "The Porc-épique";
	tmp.boss_level = 50; tmp.boss_tag = 1;
	tmp.boss_p_stats = new int[]{34,34,34};
	list.add(tmp);
	
	tmp = new Challenge("Casser Casper", 2,	0);
	tmp.boss_name = "Casper";
	tmp.boss_level = 70; tmp.boss_tag = 0;
	tmp.boss_p_stats = new int[]{2,4,9};
	list.add(tmp);
	
	
	tmp = new Challenge("Kill Bill", 2,	0);
	tmp.boss_name = "Bill";
	tmp.boss_level = 200; tmp.boss_tag = 2;
	tmp.boss_p_stats = new int[]{0,1,1};
	list.add(tmp);
	}
}