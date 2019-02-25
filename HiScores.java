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
	    System.out.println("Can't write in hiscores.data");
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
			System.out.println("Erreur lors du chargement des hiscores.");
			res= new HiScore();
	    }
	return res;
}

public HiScore()
{
	list = new  ArrayList<Score>();
}

public boolean addScore(Score news)
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
		Game.MW.addLog("Félicitations, vous entrez dans les hiscores !");
		save();
		}
	return res;
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
		res = res + s.name + " (" + (int)s.time + " secondes, graine "+ (int)s.seed +")\n";
	return res;
}
}