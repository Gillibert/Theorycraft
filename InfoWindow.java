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
	private int displayType;
	
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
		return infoUniverseHTML();
	}
	
	public String infoCraftHTML()
	{
		String res = Local.HTML_BODY;
		res += Local.H2_CRAFTING_H2;
		res += Item.CraftDesc(Joueur);
		res += Local.BODY_HTML_END;
		return res;
	}
	
	public String infoPlayerStatsHTML()
	{
		String res = Local.HTML_BODY;
		res += Local.H2S + Local.PLAYER_STATISTICS + Local.H2E;
		res +=  String.format(Local.PLAYER_STATISTICS_LIST,Joueur.name, Joueur.level, Joueur.defi.name,
		Joueur.temps_total,Joueur.money,Joueur.charge,Joueur.charge_max(),Joueur.total_skill_points(),Joueur.points_divins_totaux(),Joueur.orbes_investits_en_points_divins);
		res += Joueur.t_stats.eventStats();
		res += Local.BODY_HTML_END;
		return res;
	}
	public String infoUniverseHTML()
	{
		String res = Local.HTML_BODY;
		res += String.format(Local.UNIVERSE_INFORMATION_LIST,
			Joueur.universe.seed,
			Joueur.universe.number_of_travel,
			Joueur.MAX_LEVEL,
			Joueur.universe.vie_depart(),
			Joueur.universe.points_initiaux(),
			Joueur.universe.efficacite_base(),
			Joueur.universe.puissance_ench_sup(),
			100 * Joueur.universe.qualite_max(),
			-100 * Joueur.universe.penalty_for_bad_material()*(1.0 - 0.5 * Joueur.universe.static_plage_random()),
			-100 * Joueur.universe.penalty_for_bad_material()*(1.0 + 0.5 * Joueur.universe.static_plage_random()),
			-100 * Joueur.universe.penalty_for_bad_material(),
			1.0 - 0.5 * Joueur.universe.static_plage_random(), 
			1.0 + 0.5 * Joueur.universe.static_plage_random(),
			100*Joueur.universe.base_gold_penalty_for_death(),
			Joueur.universe.base_penalty_for_death(),
			100*Joueur.universe.proba_rencontrer_piege(),
			100*Joueur.universe.proba_champion(),
			Joueur.universe.base_penalty_for_new_challenge(),
			Joueur.universe.travel_cost(),
			Joueur.universe.base_penalty_for_travel(),
			Joueur.universe.base_penalty_for_dimensional_travel(),
			100*Joueur.universe.proba_ressource()
			);
			
			res += Local.H3_AVAILABLE_OBJECTS_H3;
			for(int i=0; i< StaticItem.nb_pos-1; i++)
			{
				if(Joueur.universe.slot_est_disponible(i))
				{
					res += StaticItem.Emplacement[i] + ", ";
				}
			}
			res = res.substring(0, res.length()-2);
			res += ".";
			
			res += Local.H3_UNAVAILABLE_OBJECTS_H3;
			for(int i=0; i< StaticItem.nb_pos-1; i++)
			{
				if(!Joueur.universe.slot_est_disponible(i))
				{
					res += StaticItem.Emplacement[i] + ", ";
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
			res += Local.NUMBER_OF_ZONES + Joueur.universe.map.zonesR.size();
			res += Local.DL;
			for(int i=0; i< Joueur.universe.map.zonesR.size(); i++)
			{
			String no_loot = "";
			if (i<2) no_loot = Local.NO_LOOT_Z;
			res += String.format(Local.DT_ZONE,Joueur.universe.map.zonesName.get(i),Joueur.universe.get_zone_level(i),Joueur.universe.get_zone_max_level(i),no_loot);
			res += String.format(Local.TRAPS_AND_CHAMPION_PROBABILITY,100*Joueur.universe.proba_rencontrer_piege()*Joueur.universe.map.trap_coeff.get(i),100*Joueur.universe.proba_champion()*Joueur.universe.map.boss_coeff.get(i));
			res += String.format(Local.MONSTER_LEVEL_MINIMUM_AVERAGE_MAXIMUM,Joueur.universe.get_zone_level(i),
			Joueur.universe.monster_points_for_level(Joueur.universe.get_zone_level(i)),
			(Joueur.universe.get_zone_level(i)+Joueur.universe.get_zone_max_level(i))/2.0,
			Joueur.universe.monster_points_for_level((Joueur.universe.get_zone_level(i)+Joueur.universe.get_zone_max_level(i))/2.0),
			Joueur.universe.get_zone_max_level(i),
			Joueur.universe.monster_points_for_level(Joueur.universe.get_zone_max_level(i)));
			res += String.format(Local.CHAMPION_LEVEL_MINIMUM_AVERAGE_MAXIMUM,
			(int)Joueur.universe.niveau_champion(Joueur.universe.get_zone_level(i)),
			Joueur.universe.monster_points_for_level((int)Joueur.universe.niveau_champion(Joueur.universe.get_zone_level(i)))*1.5,
			(Joueur.universe.niveau_champion(Joueur.universe.get_zone_level(i))+Joueur.universe.niveau_champion(Joueur.universe.get_zone_max_level(i)))/2.0,
			Joueur.universe.monster_points_for_level((Joueur.universe.niveau_champion(Joueur.universe.get_zone_level(i))+Joueur.universe.niveau_champion(Joueur.universe.get_zone_max_level(i)))/2.0)*1.5,
			(int)Joueur.universe.niveau_champion(Joueur.universe.get_zone_max_level(i)),
			Joueur.universe.monster_points_for_level((int)Joueur.universe.niveau_champion(Joueur.universe.get_zone_max_level(i)))*1.5);
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
