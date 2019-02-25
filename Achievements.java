import java.io.*;
import java.util.*; 
 
 public class Achievements {
	 
	 private static int achievementPts(double p1)
	 {
		int rg = (int)Math.max(Math.floor(Math.log10(p1/10)),0);
		int cr = (int)Math.floor(Math.pow(rg,1.2));
		return cr;
	 }
	 
	 private static String achievementStr(double p1)
	 {
		String res;
		int rg = (int)Math.max(Math.floor(Math.log10(p1/10)),0);
		int cr = (int)Math.floor(Math.pow(rg,1.2));
		res = String.format("Rang actuel : %d<br>Progression pour le prochain rang : %g / %g<br>Récompense cumulée : %d points de récompense",rg,p1,Math.pow(10,rg+2),cr);
		return res;
	 }
	 
	 public static String refreshAchievements(Player p, boolean outs)
	 {
		double pts=0.0;
		String res = null;
		if(outs) { res = Local.DL; }
		
		double ptmp;
		
		ptmp = p.points_totaux();
		if(outs)
		{
			res += "<dt><b>Héros compétent</b><dd>Accumulez des points de compétences<br>";
			res += achievementStr(ptmp);
		}
		pts += achievementPts(ptmp);
		
		ptmp = p.t_stats.events_base[TimeStats.EVENT_FIGHT_SUCCESS];
		if(outs)
		{
			res += "<dt><b>Tueur sanguinaire</b><dd>Tuez des monstres<br>";
			res += achievementStr(ptmp);
		}
		pts += achievementPts(ptmp);
		
		ptmp = p.t_stats.events_base[TimeStats.EVENT_TRAP_ATTEMPT]-p.t_stats.events_base[TimeStats.EVENT_TRAP_DEATH];
		if(outs)
		{
			res += "<dt><b>Esquiveur</b><dd>Survivez à des pièges<br>";
			res += achievementStr(ptmp);
		}
		pts += achievementPts(ptmp);
		
		ptmp = p.xp_pt;
		if(outs)
		{
			res += "<dt><b>Vétéran expérimenté</b><dd>Accumulez des points d'expérience<br>";
			res += achievementStr(ptmp);
		}
		pts += achievementPts(ptmp);

		ptmp = p.t_stats.events_base[TimeStats.EVENT_DROP_ORB];
		if(outs)
		{
			res += "<dt><b>Chercheur de gemmes</b><dd>Ramassez des orbes sur les monstres<br>";
			res += achievementStr(ptmp);
		}
		pts += achievementPts(ptmp);
		
		ptmp = p.t_stats.events_money[TimeStats.GAIN_DROP]+p.t_stats.events_money[TimeStats.GAIN_RENTE]+
			p.t_stats.events_money[TimeStats.GAIN_SELL_BASE]+p.t_stats.events_money[TimeStats.GAIN_SELL_MAGIC]+
			p.t_stats.events_money[TimeStats.GAIN_SELL_RARE]+p.t_stats.events_money[TimeStats.GAIN_SELL_MAT]+
			p.t_stats.events_money[TimeStats.GAIN_SELL_OTHER];
		if(outs)
		{
			res += "<dt><b>Banquier</b><dd>Accumulez de l'or<br>";
			res += achievementStr(ptmp);
		}
		pts += achievementPts(ptmp);

		ptmp = p.points_divins_totaux();
		if(outs)
		{
			res += "<dt><b>Divinité</b><dd>Accumulez des points divins<br>";
			res += achievementStr(ptmp);
		}
		pts += achievementPts(ptmp);
		
		p.points_haut_faits = pts;
		
		if(outs) {
			res += Local.DL_END;
			res = Local.H2_ACHIEVEMENTS_H2 + String.format("<b>%.0f points de récompense</b>",pts) + res;
			}
			
		return res;
	 }
	 
 }