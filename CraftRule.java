import java.util.ArrayList;

class CraftRule {
public int[][] cond;
public int size;
public int id;
public String desc;

public static CraftRule[] CraftRules1 = new CraftRule[]{
new CraftRule(10, new int[]{0,1}),
new CraftRule(11, new int[]{2,3}),
new CraftRule(12, new int[]{4,5}),
new CraftRule(13, new int[]{6,6})
};


// Les bornes doivent êtres triées par ordre croissant
// les bornes sont incluses
public CraftRule(int ts, int ti)
{
	size = ts;
	id = ti;
	cond = new int[3][2];
	for(int i=0; i<3; i++)
		for(int j=0; j<2; j++)
			cond[i][j]=0;
}

public CraftRule(int i, int[] c1)
{
	this(1,i);
	cond[0][0] = c1[0];
	cond[0][1] = c1[1];
}

public CraftRule(int i, int[] c1, int[] c2)
{
	this(2,i);
	cond[0][0] = c1[0];
	cond[0][1] = c1[1];
	cond[1][0] = c2[0];
	cond[1][1] = c2[1];
}

public CraftRule(int i, int[] c1, int[] c2, int[] c3)
{
	this(3,i);
	cond[0][0] = c1[0];
	cond[0][1] = c1[1];
	cond[1][0] = c2[0];
	cond[1][1] = c2[1];
	cond[2][0] = c3[0];
	cond[2][1] = c3[1];
}

public boolean isPlayable(ArrayList<Item> LI)
{
	return false;
}

public void apply(ArrayList<Item> LI)
{
	return;
}

}