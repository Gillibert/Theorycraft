import javax.swing.table.AbstractTableModel;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class InfoWindow extends javax.swing.JDialog  {
    private Player Joueur;
    private javax.swing.JPanel ivjJFrameContentPane = null;

    private JEditorPane infos;
    private JScrollPane scroll;
	public int displayType;
	
    public InfoWindow(Player J) {
	super();
	Joueur = J;
	initialize();
    }
	
	public void SetPlayer(Player J)
	{
		Joueur = J;
		refresh();
	}
	public String infoHTML()
	{
		if(displayType==0) return infoCraftHTML();
		if(displayType==1) return infoPlayerStatsHTML();
		if(displayType==2) return infoUniverseHTML();
		if(displayType==3) return infoMonstresHTML();
		return infoAchievementsHTML();
	}
	

	public String infoAchievementsHTML()
	{
		String res = Local.HTML_BODY;
		res += Achievements.refreshAchievements(Joueur,true);
		res += Local.BODY_HTML_END;
		return res;
	}
	
	public String infoMonstresHTML()
	{
		String res = Local.HTML_BODY;
		res += Monster.infoZooHTML(Joueur);
		res += Local.BODY_HTML_END;
		return res;
	}
	
	public String infoCraftHTML()
	{
		String res = Local.HTML_BODY;
		res += Local.H2_CRAFTING_H2;
		res += Item.CraftDesc(Joueur);
		res += Local.BODY_HTML_END;
		return res;
	}
	
	private String best_dist()
	{
		double pts = Joueur.total_skill_points();
		double max_gps = 0.0;
		double max_i = 0.0;
		for(double i=0.01; i< 1.0; i += 0.01)
			{
			double gps = Joueur.universe.rente_par_rencontre(pts*i)/Joueur.universe.economie_orbe(pts*(1.0-i));
			if (gps > max_gps)
			{
				max_i=i;
				max_gps = gps;
			}
			}
		return String.format("Répartition optimale (Rente viagère / Économie d'orbes) : %g" ,max_i);
	}
	
	public String infoPlayerStatsHTML()
	{
		String res = Local.HTML_BODY;
		//res += best_dist();
		res += Local.H2S + Local.PLAYER_STATISTICS + Local.H2E;
		res +=  String.format(Local.PLAYER_STATISTICS_LIST,Joueur.name, Joueur.level, Joueur.defi.name,
		Joueur.temps_total,Joueur.money,Joueur.charge,Joueur.charge_max(),Joueur.xp_pt,Joueur.total_skill_points(),Joueur.orbes_investits_en_points_competence,Joueur.points_divins_totaux(),Joueur.orbes_investits_en_points_divins);
		res += Joueur.t_stats.eventStats();
		res += Local.BODY_HTML_END;
		return res;
	}
	public String infoUniverseHTML()
	{
		String res = Local.HTML_BODY;
		res += String.format(Local.UNIVERSE_INFORMATION_LIST,
			Joueur.universe.seed,
			Joueur.universe.numberOfTravels,
			Joueur.MAX_LEVEL);
		res += Local.UNIVERSE_CONST;
		for(int i=0; i< Local.UNIVERSE_STATS_NAME.length; i++)
		{
		if(i == 8)
			res += String.format("<li>" + Local.UNIVERSE_STATS_NAME[i] + Local.COLON + " %g [%g %g]",
			Joueur.universe.penalty_for_bad_material(),
			Joueur.universe.penalty_for_bad_material()*(1.0 - 0.5 * Joueur.universe.static_plage_random()),
			Joueur.universe.penalty_for_bad_material()*(1.0 + 0.5 * Joueur.universe.static_plage_random()));
		else if(i == 14)
			res += String.format("<li>" + Local.UNIVERSE_STATS_NAME[i] + Local.COLON + " %g [%g %g]",
			Joueur.universe.static_plage_random(),1.0 - 0.5 * Joueur.universe.static_plage_random(), 1.0 + 0.5 * Joueur.universe.static_plage_random());
		else
			res += String.format("<li>" + Local.UNIVERSE_STATS_NAME[i] + Local.COLON + " %g",Joueur.universe.adjusted_constant(i));
		}
		res += "</ul>";
			res += Local.H3_AVAILABLE_OBJECTS_H3;

			for(int i=0; i< Joueur.universe.nuberSlotsAvailable; i++)
			{
					res += Local.SLOT_NAME[Joueur.universe.ItemUnlockOrder.get(i)] + ", ";
			}
			res = res.substring(0, res.length()-2);
			res += ".";
			
			res += Local.H3_UNAVAILABLE_OBJECTS_H3;
			for(int i=0; i< StaticItem.nb_pos-1; i++)
			{
				if(!Joueur.universe.slot_est_disponible(i))
				{
					res += Local.SLOT_NAME[i] + ", ";
				}
			}
			res = res.substring(0, res.length()-2);
			res += ".";
			
			res += Local.BASE_DISTRIBUTION_OF_MONSTER_SKILL_POINTS;
			for(int i=0; i< Player.nb_stats; i++)
			{
			if(Monster.coeff_std[i]>0.001)
				res += String.format(Local.LI_SKILL_POINTS,Player.stats_name[i],100*Monster.coeff_std[i]);
			}
			res += Local.LI_END;
				
			res += Local.H3_ZONE_INFORMATION_H3;
			res += Local.NUMBER_OF_ZONES + (int)Joueur.universe.nombre_zones();
			res += Local.DL;
			for(int i=0; i< Joueur.universe.nombre_zones()-1.0; i++)
			{
			String no_loot = "";
			if (i<2) no_loot = Local.NO_LOOT_Z;
			res += String.format(Local.DT_ZONE,Joueur.universe.map.zonesName.get(i),Joueur.universe.get_zone_level(i),Joueur.universe.get_zone_max_level(i),no_loot);
			res += String.format(Local.PRECIPITATION_AND_TEMPERATURE,Joueur.universe.get_temperature(i),Joueur.universe.get_precipitation(i)*100);
			res += String.format(Local.PRECIPITATION_AND_TEMPERATURE_NOW,Joueur.universe.get_current_temperature(i),Joueur.universe.get_current_precipitation(i)*100);
			
			double pbc = Joueur.universe.proba_champion()*Joueur.universe.map.boss_coeff.get(i);
			res += String.format(Local.TRAPS_AND_CHAMPION_PROBABILITY,
			100*Joueur.universe.proba_rencontrer_piege()*Joueur.universe.map.trap_coeff.get(i),100*pbc,
			100*pbc*Joueur.universe.proba_super_champion());
			
			double mpc = Joueur.universe.mul_points_competences_champions();
			double mpsc = Joueur.universe.mul_points_competences_super_champions();
			double zmin = Joueur.universe.get_zone_level(i);
			double zmax = Joueur.universe.get_zone_max_level(i);
			
			res += String.format(Local.MONSTER_LEVEL_MINIMUM_AVERAGE_MAXIMUM,
			zmin,
			zmax,
			Joueur.universe.monster_points_for_level(zmin),
			Joueur.universe.monster_points_for_level(zmax),
			(zmin+zmax)/2.0,
			Joueur.universe.monster_points_for_level((zmin+zmax)/2.0));
			
			res += String.format(Local.CHAMPION_LEVEL_MINIMUM_AVERAGE_MAXIMUM,
			Joueur.universe.niveau_champion(zmin),
			Joueur.universe.niveau_champion(zmax),
			Joueur.universe.monster_points_for_level(Joueur.universe.niveau_champion(zmin))*mpc,
			Joueur.universe.monster_points_for_level(Joueur.universe.niveau_champion(zmax))*mpc,
			(Joueur.universe.niveau_champion(zmin)+Joueur.universe.niveau_champion(zmax))/2.0,
			Joueur.universe.monster_points_for_level((Joueur.universe.niveau_champion(zmin)+Joueur.universe.niveau_champion(zmax))/2.0)*mpc);
			
			res += String.format(Local.SUPER_CHAMPION_LEVEL_MINIMUM_AVERAGE_MAXIMUM,
			Joueur.universe.niveau_super_champion(zmin),
			Joueur.universe.niveau_super_champion(zmax),
			Joueur.universe.monster_points_for_level(Joueur.universe.niveau_super_champion(zmin))*mpsc,
			Joueur.universe.monster_points_for_level(Joueur.universe.niveau_super_champion(zmax))*mpsc,
			(Joueur.universe.niveau_super_champion(zmin)+Joueur.universe.niveau_super_champion(zmax))/2.0,
			Joueur.universe.monster_points_for_level((Joueur.universe.niveau_super_champion(zmin)+Joueur.universe.niveau_super_champion(zmax))/2.0)*mpsc);
			
			}
			res += Local.DL_END;
		
		res += Local.BODY_HTML_END;
		return res;
	}
	
	public void montreInfoCraft()
	{
	displayType = 0;
	refresh();
	this.setVisible(true);
	}
	
	public void montrePlayerStats()
	{
	displayType = 1;
	refresh();
	this.setVisible(true);
	}
	
	public void montreInfoUniverse()
	{
	displayType = 2;
	refresh();
	this.setVisible(true);
	}
	
	public void montreMonstres()
	{
	displayType = 3;
	refresh();
	this.setVisible(true);
	}

	public void montreAchievements()
	{
	displayType = 4;
	refresh();
	this.setVisible(true);
	}
		
    public void refresh()
    {
	SwingUtilities.invokeLater(new Runnable() {
        public void run() {
	JViewport viewport = scroll.getViewport();
	Point backupPos = viewport.getViewPosition();
	try{infos.setText(infoHTML());}
	catch (Exception e){System.out.println(e);}
	viewport.setViewPosition(backupPos);
      }
    });
	}
	
    private javax.swing.JPanel getJFrameContentPane() {
	if (ivjJFrameContentPane == null) {
		
	    infos = new JEditorPane();
	    infos.setEditable(false);
		infos.setContentType("text/html");
	    DefaultCaret caret = (DefaultCaret)infos.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		infos.setSelectionColor(infos.getBackground());

	    scroll = new JScrollPane(infos);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setBounds(new Rectangle(5, 5, 655+120, 386));
	    infos.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 11));

	    ivjJFrameContentPane = new javax.swing.JPanel();
	    ivjJFrameContentPane.setLayout(null);

	    ivjJFrameContentPane.add(scroll);
	}
	return ivjJFrameContentPane;
    }
	
    private void initialize() {

	this.setLocation(new Point(15, 15));
	this.setSize(new Dimension(670+120, 420));
	this.setResizable(false);
	//this.setModal(true);
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setTitle(Local.INFORMATION_AND_STATISTICS);
	this.setContentPane(getJFrameContentPane());

	getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), Local.CANCEL);

	getRootPane().getActionMap().put(Local.CANCEL, new AbstractAction(){
	    public void actionPerformed(ActionEvent e) {setVisible(false);}});
		
	refresh();
    }


} 
