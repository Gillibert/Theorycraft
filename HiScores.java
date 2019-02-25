import java.util.*;
import java.io.*;
// challenge_name; name; time;

class HiScore implements Serializable {
	
public ArrayList<Score> list;

static class ComparateurScore implements Comparator<Score> {
	public int compare(Score s1, Score s2){
	    return (int)(s1.time - s2.time);
	} 
}

public void save()
{
	try{
	    //use buffering
	    OutputStream file = new FileOutputStream("hiscores.data");
	    OutputStream buffer = new BufferedOutputStream( file );
	    ObjectOutput output = new ObjectOutputStream( buffer );
	    output.writeObject(this);
	    output.close();
	} 
	catch(Exception ex){
	    System.out.println(Local.CANT_WRITE_IN_HISCORES);
	}
}

public static HiScore loadScore()
{
	HiScore res;
	try{
	    InputStream file = new FileInputStream("hiscores.data");
	    InputStream buffer = new BufferedInputStream( file );
	    ObjectInput input = new ObjectInputStream ( buffer );
	    res = (HiScore)input.readObject();
	    input.close();
	}
	catch(Exception ex)
	    {
			System.out.println(Local.ERROR_LOADING_HISCORES);
			res= new HiScore();
	    }
	return res;
}

public HiScore()
{
	list = new  ArrayList<Score>();
}

public boolean addScore(Score news,boolean disp)
{
	boolean res = false;
	ArrayList<Score> tmp = bestScores(news.challenge_name);
	if(tmp.size() >= 10)
	{
		Score rem = tmp.get(tmp.size() - 1);
		if(news.time < rem.time)
		{
		res = true;
		list.remove(list.indexOf(rem));
		}
	}
	else
		res = true;
	if(res)
		{
		list.add(news);
		if(disp) Game.MW.addLog(Local.CONGRATULATIONS_HISCORES);
		save();
		}
	return res;
}

public void cleanScores()
{
	System.out.println("cleanScores");
	int clsize = ChallengeList.list.size();
	
	ArrayList<ArrayList<Score>> asc =  new ArrayList<ArrayList<Score>>();
	
	for(int i=0; i<clsize; i++)
		asc.add(new ArrayList<Score>());
	
	for(Score s : list)
		for(int i=0; i<clsize; i++)
			if(s.challenge_name.equals(ChallengeList.list.get(i).name))
				asc.get(i).add(s);
	
	list.clear();
	for(ArrayList<Score> als : asc)
	{
		Collections.sort(als, new ComparateurScore());
		Score ts = als.get(0);
		als.clear();
		list.add(ts);
		for(int i=0; i<5; i++)
		{
			Score ts2 = new Score(ts.challenge_name,StaticItem.nameGenerator.getName(),ts.time*Math.pow(2,0.5*i+1), ts.seed);
			list.add(ts2);
		}
	}
	
	save();
}

public ArrayList<Score> bestScores(String challenge_name)
{
	ArrayList<Score> tmp =  new  ArrayList<Score>();	
	for(Score s : list)
		if(s.challenge_name.equals(challenge_name))
			tmp.add(s);
	Collections.sort(tmp, new ComparateurScore());
	return tmp;
}

public String bestScoresString(String challenge_name)
{
	ArrayList<Score> tmp = bestScores(challenge_name);
	String res = "";
	for(Score s : tmp)
		res += String.format(Local.HISCORES_LINE,s.name,(int)s.time,(int)s.seed);
	return res;
}
}