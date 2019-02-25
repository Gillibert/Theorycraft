import java.util.*;

/*
* Thanks to Simon Heinen for his Simple Random Name Generator for Java
* Source http://andrdev.blogspot.de/2011/02/simple-random-name-generator.html
*/

public class NameGenerator {

private List<String> vocals = new ArrayList<String>();
private List<String> startConsonants = new ArrayList<String>();
private List<String> endConsonants = new ArrayList<String>();
private List<String> nameInstructions = new ArrayList<String>();
private Random rg;

public NameGenerator(boolean MAT) {
rg = new Random();
String demoVocals[] = { "a", "e", "i", "o", "u", "in", "ei", "ai", "ou", "j", "y", "oi", "au" };

String demoStartConsonants[] = { "b", "c", "d", "f", "g", "h", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z", "ch", "bl", "br", "fl", "gl", "gr", "kl", "pr", "st", "sh", "th" };

String demoEndConsonants[] = { "b", "d", "f", "g", "h", "k", "l", "m",
"n", "p", "r", "s", "t", "v", "w", "z", "ch", "gh", "nn", "st",
"sh", "th", "tt", "ss", "pf", "nt" };

if(!MAT)
    {
	this.nameInstructions.add("vd");
	this.nameInstructions.add("cvdvd");
	this.nameInstructions.add("cvd");
	this.nameInstructions.add("vdvd") ;
    }
else
    {
	this.nameInstructions.add("cvdvdvd") ;
    }

this.vocals.addAll(Arrays.asList(demoVocals));
this.startConsonants.addAll(Arrays.asList(demoStartConsonants));
this.endConsonants.addAll(Arrays.asList(demoEndConsonants));
}

/**
*
* The names will look like this
* (v=vocal,c=startConsonsonant,d=endConsonants): vd, cvdvd, cvd, vdvd
*
* @param vocals
* pass something like {"a","e","ou",..}
* @param startConsonants
* pass something like {"s","f","kl",..}
* @param endConsonants
* pass something like {"th","sh","f",..}
*/
public NameGenerator(String[] vocals, String[] startConsonants,
String[] endConsonants) {
this.vocals.addAll(Arrays.asList(vocals));
this.startConsonants.addAll(Arrays.asList(startConsonants));
this.endConsonants.addAll(Arrays.asList(endConsonants));
}

/**
* see {@link NameGenerator#NameGenerator(String[], String[], String[])}
*
* @param vocals
* @param startConsonants
* @param endConsonants
* @param nameInstructions
* Use only the following letters:
* (v=vocal,c=startConsonsonant,d=endConsonants)! Pass something
* like {"vd", "cvdvd", "cvd", "vdvd"}
*/
public NameGenerator(String[] vocals, String[] startConsonants,
String[] endConsonants, String[] nameInstructions) {
this(vocals, startConsonants, endConsonants);
this.nameInstructions.addAll(Arrays.asList(nameInstructions));
}

public String getName() {
return firstCharUppercase(getNameByInstructions(getRandomElementFrom(nameInstructions)));
}

public String getMatName(long hash) {
rg.setSeed(hash);
return getNameByInstructions(getRandomElementFrom(nameInstructions));
}


private String getNameByInstructions(String nameInstructions) {
String name = "";
int l = nameInstructions.length();

for (int i = 0; i < l; i++) { char x = nameInstructions.charAt(0); switch (x) { case 'v': name += getRandomElementFrom(vocals); break; case 'c': name += getRandomElementFrom(startConsonants); break; case 'd': name += getRandomElementFrom(endConsonants); break; } nameInstructions = nameInstructions.substring(1); } return name;
}

public String getNameOf()
	{
	    String tmp = getName();
	    char fc = tmp.charAt(0);
	    if (fc == 'A' || fc == 'E' || fc == 'I' || fc == 'O' || fc == 'U')
		return "d'"+tmp;
	     else
		return "de "+tmp;
	}

public static String firstCharLowercase(String name) 
{ return Character.toString(name.charAt(0)).toLowerCase() + name.substring(1); }

public static String firstCharUppercase(String name) 
{ return Character.toString(name.charAt(0)).toUpperCase() + name.substring(1); }

private String getRandomElementFrom(List v) {
return (String) v.get((int)(rg.nextDouble()*v.size()));}

}
