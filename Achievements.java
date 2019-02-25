import java.io.*;
import java.util.*; 
 
 public class Achievements {
	 static String[] adjectif = 
		{
		"Néophyte",
		"Novice",
		"Débutant",
		"Passable",
		"Moyen",
		"Intermédiaire",
		"Convenable",
		"Avancé",
		"Diplomé",
		"Semi-professionnel",
		"Notoire",
		"Professionnel",
		"Aguerri",
		"Expérimenté",
		"Expert",
		"Talentueux",
		"Virtuose",
		"Glorieux",
		"Fabuleux",
		"Héroïque",
		"Proverbial",
		"Surnaturel",
		"Super-héroïque",
		"Légendaire",
		"Extraordinaire",
		"Mythique",
		"Divin",
		"Plus-que-divin",
		"Suprême",
		"Ultime"
		};
	 
	 
	 public static String refreshAchievements(Player p, boolean outs)
	 {
		int nbhf = 0;
		int nbhfok = 0;
		double pts=0.0;
		String res="";
		if(outs) { res = Local.DL; }
		String fc;
		for(int i=1; i< 17; i++)
		{
			double p1 = p.points_totaux();
			double p2 = Math.pow(10,2*i+2);
			if (p1 >= p2) {fc = "#0000ee"; pts +=i; nbhfok+=1;}
			else fc = "#ee0000";
			if(outs)
				{
				res += String.format("<dt><font color=\"%s\"><b>Héros %s</b></font><dd>Accumulez des points de compétences : <font color=\"%s\">%g/%g</font><br>",fc,adjectif[i],fc,p1,p2);
				res += String.format("Récompense : %d points de récompense",i);
				}
			nbhf +=1;
		}
		for(int i=0; i< 7; i++)
		{
			double p1 = p.t_stats.events_base[TimeStats.EVENT_FIGHT_SUCCESS];
			double p2 = Math.pow(10,i+1);
			if (p1 >= p2) {fc = "#0000ee"; pts +=i+1; nbhfok+=1;}
			else fc = "#ee0000";
			if(outs)
				{
				res += String.format("<dt><font color=\"%s\"><b>Tueur %s</b></font><dd>Tuez des monstres : <font color=\"%s\">%g/%g</font></b><br>",fc,adjectif[3*i+2],fc,p1, p2);
				res += String.format("Récompense : %d points de récompense",i+1);
				}
			nbhf +=1;
		}
		for(int i=0; i< 8; i++)
		{
			double p1 = p.t_stats.events_base[TimeStats.EVENT_TRAP_ATTEMPT]-p.t_stats.events_base[TimeStats.EVENT_TRAP_DEATH];
			double p2 = Math.pow(10,i+1);
			if (p1 >= p2) {fc = "#0000ee"; pts +=i+1; nbhfok+=1;}
			else fc = "#ee0000";
			if(outs)
				{
				res += String.format("<dt><font color=\"%s\"><b>Esquiveur %s</b></font><dd>Survivez à des pièges : <font color=\"%s\">%g/%g</font></b><br>",fc,adjectif[3*i+2],fc,p1, p2);
				res += String.format("Récompense : %d points de récompense",i+1);
				}
			nbhf +=1;
		}
		for(int i=0; i< 22; i++)
		{
			double p1 = p.xp_pt;
			double p2 = Math.pow(10,3*i+4);
			if (p1 >= p2) {fc = "#0000ee"; pts +=i+1; nbhfok+=1;}
			else fc = "#ee0000";
			if(outs)
				{
				res += String.format("<dt><font color=\"%s\"><b>Vétéran %s</b></font><dd>Accumulez des points d'expérience : <font color=\"%s\">%g/%g</font><br>",fc,adjectif[i],fc,p1,p2);
				res += String.format("Récompense : %d points de récompense",i+1);
				}
			nbhf +=1;
		}
		for(int i=0; i< 13; i++)
		{
			double p1 = p.t_stats.events_base[TimeStats.EVENT_DROP_ORB];
			double p2 = Math.pow(10,2*i);
			if (p1 >= p2) {fc = "#0000ee"; pts +=i+1; nbhfok+=1;}
			else fc = "#ee0000";
			if(outs)
				{
				res += String.format("<dt><font color=\"%s\"><b>Gemmologue %s</b></font><dd>Ramassez des orbes sur les monstres : <font color=\"%s\">%g/%g</font><br>",fc,adjectif[2*i],fc,p1,p2);
				res += String.format("Récompense : %d points de récompense",i+1);
				}
			nbhf +=1;
		}
		for(int i=0; i< 21; i++)
		{
			double p1 = p.t_stats.events_money[TimeStats.GAIN_DROP]+p.t_stats.events_money[TimeStats.GAIN_RENTE]+
			p.t_stats.events_money[TimeStats.GAIN_SELL_BASE]+p.t_stats.events_money[TimeStats.GAIN_SELL_MAGIC]+
			p.t_stats.events_money[TimeStats.GAIN_SELL_RARE]+p.t_stats.events_money[TimeStats.GAIN_SELL_MAT]+
			p.t_stats.events_money[TimeStats.GAIN_SELL_OTHER];
			double p2 = Math.pow(10,2*i+2);
			if (p1 >= p2) {fc = "#0000ee"; pts +=i+1; nbhfok+=1;}
			else fc = "#ee0000";
			if(outs)
				{
				res += String.format("<dt><font color=\"%s\"><b>Banquier %s</b></font><dd>Accumulez de l'or : <font color=\"%s\">%g/%g</font><br>",fc,adjectif[i+5],fc,p1,p2);
				res += String.format("Récompense : %d points de récompense",i+1);
				}
			nbhf +=1;
		}
		for(int i=0; i< 18; i++)
		{
			double p1 = p.points_divins_totaux();
			double p2 = Math.pow(10,2*i+1);
			if (p1 >= p2) {fc = "#0000ee"; pts +=i+1; nbhfok+=1;}
			else fc = "#ee0000";
			if(outs)
				{
				res += String.format("<dt><font color=\"%s\"><b>Divinité %s</b></font><dd>Accumulez des points divins : <font color=\"%s\">%g/%g</font><br>",fc,adjectif[i+5],fc,p1,p2);
				res += String.format("Récompense : %d points de récompense",i+1);
				}
			nbhf +=1;
		}
		if(outs) {
			res += Local.DL_END;
			res = String.format(Local.H2_ACHIEVEMENTS_H2,nbhfok, nbhf) +
			   String.format("<b>%.0f points de récompense</b>",pts) + res;
			}
		p.points_haut_faits = pts;
		return res;
	 }
 }