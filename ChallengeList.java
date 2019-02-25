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
	list.add(new Challenge("Make it double", 0,200));
	
	list.add(new Challenge("Mendiant", 1,			10000));
	list.add(new Challenge("Épicier", 1, 			100000));
	list.add(new Challenge("Trader", 1,  			1000000));
	list.add(new Challenge("Banquier", 1,			10000000));
	list.add(new Challenge("Bill Gates", 1,			100000000));
	list.add(new Challenge("Wirt The Peg-Leg", 1,	1000000000));
	
	tmp = new Challenge("Lapinou doit mourir", 2,	0);
	tmp.boss = new Monster("Lapinou", 10, 1, new int[]{0,0,0});
	list.add(tmp);
	
	tmp = new Challenge("Un porc-épic qui pique", 2,	0);
	tmp.boss = new Monster("The Porc-épique", 20, 1, new int[]{34,34,34});
	list.add(tmp);
	
	tmp = new Challenge("Casser Casper", 2,	0);
	tmp.boss = new Monster("Casper", 60, 0, new int[]{2,4,9});
	list.add(tmp);
	
	tmp = new Challenge("Solide comme un roc", 2,	0);
	tmp.boss = new Monster("The Rock", 70, 2, new int[]{2,9,10});
	list.add(tmp);
	
	tmp = new Challenge("Kill Bill", 2,	0);
	tmp.boss = new Monster("Bill", 80, 2, new int[]{0,1,1});
	list.add(tmp);
	}
}