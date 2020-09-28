import java.util.ArrayList;
import java.io.Serializable;

public class ClassRPG implements Serializable {

public int[] bonus;
public int[] malus;
public String name;

public ClassRPG(String n)
{
	name = n;
	bonus = new int[Local.SKILLS_NAME.length];
	malus = new int[Local.SKILLS_NAME.length];
	for(int i=0; i<bonus.length; i++) 
		bonus[i]=0;
	for(int i=0; i<malus.length; i++) 
		malus[i]=0;
}

public void setBonus(int[] bon)
{
	for(int i=0; i<bon.length; i++) 
	{
		bonus[bon[i]]=1;
	}
}

public void setMalus(int[] mal)
{
	for(int i=0; i<mal.length; i++) 
	{
		malus[mal[i]]=1;
	}
}

}