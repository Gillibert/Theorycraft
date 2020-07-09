import java.util.ArrayList;

class ChallengeList {
public static ArrayList<Challenge> list;

static void init()
	{
	list = new ArrayList<Challenge>();
	Challenge tmp;
	

	list.add(new Challenge(Local.WE_START_GENTLY, 0,10));
	list.add(new Challenge(Local.DUNGEONS_AND_DRAGONS, 0,20));
	list.add(new Challenge(Local.INFERNO, 0,60));
	list.add(new Challenge(Local.THIS_IS_SPARTA, 0,300));
	list.add(new Challenge(Local.OVER_9000, 0,9000));
	list.add(new Challenge(Local.SKY_IS_THE_LIMIT, 0,2000000000.0));
	list.add(new Challenge(Local.BEGGAR, 1,				1000.0));
	list.add(new Challenge(Local.GROCER, 1,				100000.0));
	list.add(new Challenge(Local.BANKER, 1, 			10000000.0));
	list.add(new Challenge(Local.BILL_GATES, 1,			1000000000.0));
	list.add(new Challenge(Local.WIRT_THE_PEG_LEG, 1,	100000000000.0));
	
	tmp = new Challenge(Local.THE_BUNNY_MUST_DIE, 2,	0);
	tmp.boss_name = Local.BUNNY_BOSS;
	tmp.boss_level = 20; tmp.boss_tag = 1;
	tmp.boss_p_stats = new int[]{0,0,0};
	list.add(tmp);
	
	tmp = new Challenge(Local.A_PORCUPINE_THAT_STINGS, 2, 0);
	tmp.boss_name = Local.THE_PORCUPINE_BOSS;
	tmp.boss_level = 50; tmp.boss_tag = 1;
	tmp.boss_p_stats = new int[]{34,34,34};
	list.add(tmp);
	
	tmp = new Challenge(Local.GHOSTBUSTERS, 2,	0);
	tmp.boss_name = Local.CASPER_BOSS;
	tmp.boss_level = 70; tmp.boss_tag = 0;
	tmp.boss_p_stats = new int[]{2,4,9};
	list.add(tmp);
	
	
	tmp = new Challenge(Local.KILL_BILL, 2,	0);
	tmp.boss_name = Local.BILL_BOSS;
	tmp.boss_level = 200; tmp.boss_tag = 2;
	tmp.boss_p_stats = new int[]{0,1,1};
	list.add(tmp);
	}
}