import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Courbes extends javax.swing.JDialog  {
	private Player Joueur;
	private javax.swing.JPanel ivjJFrameContentPane = null;

	private JScrollPane scroll;
	private JList liste_s;
	private JLabel prompt;
	private MyDraw drawSurf;
	private int to_dis_tmp;
	private DefaultListModel stats_list;
	private ArrayList<CurveObject> curves_to_draw;
	private int zoom = 2;
	private int range;
	
    public static int[] zoomLevel = {5,10,20,50,100,200,500,1000,2000,4000,10000};
	
	public Courbes(Player J) {
		super();
		Joueur = J;
		initialize();
	}

	public void SetPlayer(Player J)
	{
		Joueur = J;
		refresh();
	}
	
	private void refreshAfterZoom()
	{
		range = zoomLevel[zoom];
		drawSurf.repaint();
		compute_the_curves(liste_s.getSelectedIndex());
		drawSurf.draw_graph(curves_to_draw);		
	}
	
	public void zoom(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		if (notches < 0) 
		{
			zoom--;
			if(zoom < 0) zoom = 0;
		}
		else 
		{
			zoom++;
			if(zoom >= zoomLevel.length) zoom = zoomLevel.length-1;
		}
		refreshAfterZoom();

	}
	
	public void montre()
	{
	refresh();
	this.setVisible(true);
	}

	public void refresh()
	{	
		prompt.setText((String)stats_list.get(liste_s.getSelectedIndex()));
		//zoom = 2;
		refreshAfterZoom();
	}
	
		private void compute_the_curves(int idx)
		{
		idx = idx-14;
		double tx;
		double max_pt = range;

		curves_to_draw = new ArrayList<CurveObject>(); 

		CurveObject curve_to_draw = new CurveObject();
		CurveObject curve_to_draw2 = new CurveObject();
		CurveObject curve_to_draw3 = new CurveObject();
		CurveObject curve_to_draw4 = new CurveObject();
		CurveObject curve_to_draw5 = new CurveObject();
		CurveObject curve_to_draw6 = new CurveObject();
		CurveObject curve_to_draw7 = new CurveObject();

		double sizar = curve_to_draw.arr_x.length;
		curves_to_draw.add(curve_to_draw);

		switch(idx)
		{
		case -14:
			{
			curve_to_draw.title = Local.FIGHT;
			curve_to_draw2.title = Local.TRAPS;
			curve_to_draw3.title = Local.RESURRECTION_REGENERATION_PENALTIES;
			curve_to_draw4.title = Local.SHOPPING_CRAFTING;
			int asize = Joueur.t_stats.data.size();
			//if (asize > 1) asize--;

			double scale = sizar/asize;

			for (int x = 0; x < sizar; x++)
				{
				curve_to_draw.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw2.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw3.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw4.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				
				curve_to_draw.arr_y[x] = 
					Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_CHERCHE_ENNEMI]+
					Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_INITIATIVE]+
					Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_COGNE]+
					Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_FUITE];
				curve_to_draw2.arr_y[x] = 
					Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_PIEGE];
				curve_to_draw3.arr_y[x] = 
					Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_RESURRECTION]+
					Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_HEAL]+
					Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_PENALTY];
				curve_to_draw4.arr_y[x] = 
					Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_CHERCHE_MARCHAND]+
					Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_CHERCHE_FORGE]+
					Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_CRAFT];
				}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			curves_to_draw.add(curve_to_draw4);
			}
		break;
		case -13:
		{
			curve_to_draw.title = Local.LOOKING_FOR_ENNEMY;
			curve_to_draw2.title = Local.INITIATIVE;
			curve_to_draw3.title = Local.ATTACK;
			curve_to_draw4.title = Local.FLEE;
			int asize = Joueur.t_stats.data.size();
			//if (asize > 1) asize--;

			double scale = sizar/asize;

			for (int x = 0; x < sizar; x++)
			{
				curve_to_draw.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw2.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw3.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw4.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				
				curve_to_draw.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_CHERCHE_ENNEMI];
				curve_to_draw2.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_INITIATIVE];
				curve_to_draw3.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_COGNE];
				curve_to_draw4.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_FUITE];
			}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			curves_to_draw.add(curve_to_draw4);
		}
		break;
		case -12:
		{
			curve_to_draw.title = Local.RESURRECTION;
			curve_to_draw2.title = Local.REGENERATION;
			curve_to_draw3.title = Local.PENALTIES;
			int asize = Joueur.t_stats.data.size();
			//if (asize > 1) asize--;

			double scale = sizar/asize;

			for (int x = 0; x < sizar; x++)
			{
				curve_to_draw.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw2.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw3.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				
				curve_to_draw.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_RESURRECTION];
				curve_to_draw2.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_HEAL];
				curve_to_draw3.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[TimeStats.ACTIVITY_PENALTY];
			}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
		}
		break;
		case -11:
		{
			curve_to_draw.title = Local.LOOKING_FOR_TRADER;
			curve_to_draw2.title = Local.LOOKING_FOR_MYSTIC_FORGE;
			curve_to_draw3.title = Local.CRAFTING;
			int asize = Joueur.t_stats.data.size();
			//if (asize > 1) asize--;

			double scale = sizar/asize;

			for (int x = 0; x < sizar; x++)
			{
				curve_to_draw.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw2.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw3.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
								
				curve_to_draw.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[5];
				curve_to_draw2.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[6];
				curve_to_draw3.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[7];
			}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
		}
		break;
		case -10:
		{
			curve_to_draw.title = Local.GOLD_FROM_MONSTERS;
			curve_to_draw2.title = Local.GOLD_FROM_LIFE_ANNUITY;
			curve_to_draw3.title = Local.GOLD_FROM_SALES;
			int asize = Joueur.t_stats.data_money.size();
			//if (asize > 1) asize--;

			double scale = sizar/asize;

			for (int x = 0; x < sizar; x++)
			{
				curve_to_draw.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw2.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw3.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				
				curve_to_draw.arr_y[x] = Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.GAIN_DROP];
				curve_to_draw2.arr_y[x] = Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.GAIN_RENTE];				
				curve_to_draw3.arr_y[x] = 
					Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.GAIN_SELL_BASE]+
					Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.GAIN_SELL_MAGIC]+
					Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.GAIN_SELL_RARE]+
					Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.GAIN_SELL_MAT]+
					Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.GAIN_SELL_OTHER];
			}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
		}
		break;
		case -9:
		{
			curve_to_draw.title = Local.GOLD_FROM_SELLING_BASIC_OBJECTS;
			curve_to_draw2.title = Local.GOLD_FROM_SELLING_MAGIC_OBJECTS;
			curve_to_draw3.title = Local.GOLD_FROM_SELLING_RARE_OBJECTS;
			curve_to_draw4.title = Local.GOLD_FROM_SELLING_RESOURCES;
			curve_to_draw5.title = Local.GOLD_FROM_SELLING_OTHER;
			
			int asize = Joueur.t_stats.data_money.size();
			//if (asize > 1) asize--;

			double scale = sizar/asize;

			for (int x = 0; x < sizar; x++)
			{
				curve_to_draw.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw2.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw3.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw4.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw5.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
					
				curve_to_draw.arr_y[x] = Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.GAIN_SELL_BASE];
				curve_to_draw2.arr_y[x] = Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.GAIN_SELL_MAGIC];
				curve_to_draw3.arr_y[x] = Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.GAIN_SELL_RARE];
				curve_to_draw4.arr_y[x] = Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.GAIN_SELL_MAT];
				curve_to_draw5.arr_y[x] = Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.GAIN_SELL_OTHER];
			}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			curves_to_draw.add(curve_to_draw4);
			curves_to_draw.add(curve_to_draw5);
		}
		break;
		case -8:
		{
			curve_to_draw.title = Local.LOSS_FROM_DEATH;
			curve_to_draw2.title = Local.LOSS_FROM_SHOPPING;
			int asize = Joueur.t_stats.data_money.size();
			//if (asize > 1) asize--;

			double scale = sizar/asize;

			for (int x = 0; x < sizar; x++)
			{
				curve_to_draw.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw2.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				
				curve_to_draw.arr_y[x] = Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.LOSS_DEATH];		
				curve_to_draw2.arr_y[x] = 
					Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.LOSS_BUY_BASE]+
					Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.LOSS_BUY_MAT]+
					Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.LOSS_BUY_ORB]+
					Joueur.t_stats.data_money.get((int)(x/scale))[TimeStats.LOSS_BUY_OTHER];
			}
			curves_to_draw.add(curve_to_draw2);
		}
		break;
		case -7:
			{
			curve_to_draw.title = Local.EXPERIENCE_FROM_MONSTERS;
			curve_to_draw2.title = Local.EXPERIENCE_FROM_TRAPS;
			curve_to_draw3.title = Local.EXPERIENCE_FROM_ORBS;
			int asize = Joueur.t_stats.data_xp.size();
			//if (asize > 1) asize--;

			double scale = sizar/asize;

			for (int x = 0; x < sizar; x++)
			{
				curve_to_draw.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw2.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw3.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				
				curve_to_draw.arr_y[x] = Joueur.t_stats.data_xp.get((int)(x/scale))[TimeStats.XP_MONSTER];
				curve_to_draw2.arr_y[x] = Joueur.t_stats.data_xp.get((int)(x/scale))[TimeStats.XP_TRAP];	
				curve_to_draw3.arr_y[x] = Joueur.t_stats.data_xp.get((int)(x/scale))[TimeStats.XP_ORB];				
			}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
		}
		break;
		case -6:
			{
			curve_to_draw.title = Local.DIVINE_POINTS_ORBS;
			curve_to_draw2.title = Local.DIVINE_POINTS_LEVEL;
			
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;

				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] = tx;
				
				curve_to_draw.arr_y[x]  = Joueur.universe.points_divins_pour_x_orbe(tx);
				curve_to_draw2.arr_y[x]  = Joueur.universe.points_divins_pour_niveau(tx);
				}
				curves_to_draw.add(curve_to_draw2);
		}
		break;
		case -5:
			{
			curve_to_draw.title = Local.BASE_MONSTER_LEVEL;
			curve_to_draw2.title = Local.CHAMPIONS_LEVEL;
			curve_to_draw3.title = Local.SUPER_CHAMPIONS_LEVEL;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;

				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x]  = curve_to_draw3.arr_x[x] = tx;
				
				curve_to_draw.arr_y[x]  = tx;
				curve_to_draw2.arr_y[x] = Joueur.universe.niveau_champion(tx);
				curve_to_draw3.arr_y[x] = Joueur.universe.niveau_super_champion(tx);
				}
				curves_to_draw.add(curve_to_draw2);
				curves_to_draw.add(curve_to_draw3);
		}
		break;
		case -4:
			{
			curve_to_draw.title = Local.MONSTERS_SKILLS_POINTS;
			curve_to_draw2.title = Local.CHAMPIONS_SKILLS_POINTS;
			curve_to_draw3.title = Local.SUPER_CHAMPIONS_SKILLS_POINTS;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;

				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] = curve_to_draw3.arr_x[x] = tx;
				curve_to_draw.arr_y[x]  = Joueur.universe.monster_points_for_level(tx);
				curve_to_draw2.arr_y[x] = Joueur.universe.monster_points_for_level(tx)*Joueur.universe.mul_points_competences_champions();
				curve_to_draw3.arr_y[x] = Joueur.universe.monster_points_for_level(tx)*Joueur.universe.mul_points_competences_super_champions();
				}
				curves_to_draw.add(curve_to_draw2);
				curves_to_draw.add(curve_to_draw3);
		}
		break;
		case -3:
			{
			curve_to_draw.title = Local.BASE_EXPERIENCE_TRAP_LEVEL;
			curve_to_draw2.title = Local.BASE_EXPERIENCE_MONSTER_LEVEL;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				
				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] = tx;
				curve_to_draw.arr_y[x]  = Joueur.universe.xp_for_level(tx)*0.75;
				curve_to_draw2.arr_y[x] = Joueur.universe.xp_for_level(tx);
				}
				curves_to_draw.add(curve_to_draw2);
		}
		break;
		case -2:
			{
			curve_to_draw.title = Local.TRAPS_DAMAGE;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;

				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] = tx;
				curve_to_draw.arr_y[x]  = Joueur.universe.traps_dmg_for_level(tx);
				}
		}
		break;
		case -1:
			{
			curve_to_draw.title = Local.GOLD_DROP_MIN;
			curve_to_draw2.title = Local.GOLD_DROP_AVERAGE;
			curve_to_draw3.title = Local.GOLD_DROP_MAX;

			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;

				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] = curve_to_draw3.arr_x[x] = tx;
				curve_to_draw.arr_y[x]  = Joueur.universe.gold_drop(tx)*(1.0-Joueur.universe.static_plage_random()*0.5);
				curve_to_draw2.arr_y[x] = Joueur.universe.gold_drop(tx);
				curve_to_draw3.arr_y[x] = Joueur.universe.gold_drop(tx)*(1.0+Joueur.universe.static_plage_random()*0.5);
				}
				curves_to_draw.add(curve_to_draw2);
				curves_to_draw.add(curve_to_draw3);
		}
		break;
		case 0:
			{
			curve_to_draw.title = Local.ATTACKS_PER_SECOND;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.att_per_sec(tx);
				}
			}
		break;
		case 1:
			{
			curve_to_draw.title = Local.AVERAGE_DAMAGE_OF_A_BASIC_ATTACK;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.dmg_base(tx);
				}
			}
		break;
		case 2:
			{
			curve_to_draw.title = Local.DAMAGE_TAKEN;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.reduc(tx);
				}
			}
		break;
		case 3:
			{
			curve_to_draw.title = Local.DAMAGE_ABSORPTION;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.absorption(tx);
				}
			}
		break;
		case 4:
			{
			int es1, es2, es3;
			es1 = zoomLevel[zoom]/5;
			es2 = zoomLevel[zoom]/2;
			es3 = zoomLevel[zoom];
			
			curve_to_draw.title = Local.PROBABILITY_TO_DODGE_AGAINST_PRECISION + es1;
			curve_to_draw2.title = Local.PROBABILITY_TO_DODGE_AGAINST_PRECISION + es2;
			curve_to_draw3.title = Local.PROBABILITY_TO_DODGE_AGAINST_PRECISION + es3;

			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] 
				= curve_to_draw3.arr_x[x] = tx;

				curve_to_draw.arr_y[x]  = Joueur.universe.esquive_proba(tx,es1);
				curve_to_draw2.arr_y[x] = Joueur.universe.esquive_proba(tx,es2);
				curve_to_draw3.arr_y[x] = Joueur.universe.esquive_proba(tx,es3);
				}

			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			}
		break;
		case 5:
			{
			int es1, es2, es3;
			es1 = zoomLevel[zoom]/5;
			es2 = zoomLevel[zoom]/2;
			es3 = zoomLevel[zoom];

			curve_to_draw.title = Local.PROBABILITY_TO_HIT_AGAINST_DODGE + es1;
			curve_to_draw2.title = Local.PROBABILITY_TO_HIT_AGAINST_DODGE + es2;
			curve_to_draw3.title = Local.PROBABILITY_TO_HIT_AGAINST_DODGE + es3;

			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] 
				= curve_to_draw3.arr_x[x] = tx;

				curve_to_draw.arr_y[x] = 1- Joueur.universe.esquive_proba(es1,tx);
				curve_to_draw2.arr_y[x] = 1- Joueur.universe.esquive_proba(es2,tx);
				curve_to_draw3.arr_y[x] = 1- Joueur.universe.esquive_proba(es3,tx);
				}

			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			}
		break;
		case 6:
			{
			curve_to_draw.title = Local.CRITICAL_STRIKE_PROBABILITY;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.crit_proba(tx);
				}
			}
		break;
		case 7:
			{
			curve_to_draw.title = Local.CRITICAL_STRIKE_MULTIPLIER;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.multi_crit(tx);
				}
			}
		break;
		case 8:
			{
			curve_to_draw.title = Local.LIFE_LEECH;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.vol_de_vie(tx);
				}
			}
		break;
		case 9:
			{
			int con1, con2, con3;
			con1 = (int)Joueur.CON();
			con2 = (int)(Joueur.CON()+5);
			con3 = (int)(Joueur.CON()+20);

			curve_to_draw.title = Local.HEALTH_POINTS_FOR_A_CONSTITUTION_OF + con1;
			curve_to_draw2.title = Local.HEALTH_POINTS_FOR_A_CONSTITUTION_OF + con2;
			curve_to_draw3.title = Local.HEALTH_POINTS_FOR_A_CONSTITUTION_OF + con3;

			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] 
				= curve_to_draw3.arr_x[x] = tx;

				curve_to_draw.arr_y[x]  = Joueur.universe.vie_max(tx,con1);
				curve_to_draw2.arr_y[x] = Joueur.universe.vie_max(tx,con2);
				curve_to_draw3.arr_y[x] = Joueur.universe.vie_max(tx,con3);
				}

			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			}
		break;
		case 10:
			{
			curve_to_draw.title = Local.HEALTH_POINTS_PER_VITALITY_POINT;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.pv_per_vita(tx);
				}
			}
		break;
		case 11:
			{
			curve_to_draw.title = Local.HEALTH_POINTS_PER_SECOND;

			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;

				curve_to_draw.arr_y[x]  = Joueur.universe.regen(tx);
				}

			}
		break;
		case 12:
			{
			curve_to_draw.title = Local.RESURRECTION_TIME_CURV;

			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x]  =  Joueur.universe.temps_res(tx);
				}

			}
		break;
		case 13:
			{
			curve_to_draw.title = Local.MAXIMUM_LOAD;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.charge_max(tx);
				}
			}
		break;
		case 14:
			{
			curve_to_draw.title = Local.FINDING_A_MONSTER;
			curve_to_draw2.title = Local.FINDING_A_TRADER;
			curve_to_draw3.title = Local.FINDING_A_FORGE;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x]  = curve_to_draw3.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.temps_traque(tx);
				curve_to_draw2.arr_y[x] =  Joueur.universe.temps_shop(tx);
				curve_to_draw3.arr_y[x] =  Joueur.universe.temps_forge(tx);
				}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			}
		break;
		case 15:
			{
			curve_to_draw.title = Local.RESOURCES_MULTIPLIER;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.multiplicateur_res(tx);
				}
			}
		break;
		case 16:
			{
			curve_to_draw.title = Local.PROBABILITY_THAT_A_BASIC_OBJECT_BECOMES_MAGIC;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.chance_magique(tx);
				}
			}
		break;
		case 17:
			{
			curve_to_draw.title = Local.PROBABILITY_THAT_A_MAGIC_OBJECT_BECOMES_RARE;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.chance_rare(tx);
				}
			}
		break;
		case 18:
			{
			curve_to_draw.title = Local.PROBABILITY_OF_AN_OBJECT_BEING_HIGH_QUALITY;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.chance_qualite(tx);
				}
			}
		break;
		case 19:
			{
			curve_to_draw.title = Local.AVERAGE_NUMBER_OF_ITEMS_ON_A_MONSTER;
			curve_to_draw2.title = Local.MINIMUM_NUMBER_OF_ITEMS_ON_A_MONSTER;
			curve_to_draw3.title = Local.MAXIMUM_NUMBER_OF_ITEMS_ON_A_MONSTER;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				double ptb = Joueur.universe.quantite_drop(tx);
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = ptb;
				curve_to_draw2.arr_x[x] = tx;
				curve_to_draw2.arr_y[x] = StaticItem.tailleMin(ptb);
				curve_to_draw3.arr_x[x] = tx;
				curve_to_draw3.arr_y[x] = StaticItem.tailleMax(ptb);
				}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			}
		break;
		case 20:
			{
			curve_to_draw.title = Local.MINIMUM_ENCHANTMENT_POWER;
			curve_to_draw2.title = Local.MAXIMUM_ENCHANTMENT_POWER;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.puissance_ench_inf(tx);
				curve_to_draw2.arr_y[x] = Joueur.universe.puissance_ench_sup();
				}
			curves_to_draw.add(curve_to_draw2);
			}
		break;
		case 21:
			{
			curve_to_draw.title = Local.GOLD_MULTIPLIER;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.multiplicateur_or(tx);
				}
			}
		break;
		case 22:
			{
			curve_to_draw.title = Local.MULTIPLIER_VERSUS + Local.TAGS_NAME[0];
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.ed_mort_vivant(tx);
				}
			}
		break;
		case 23:
			{
			curve_to_draw.title = Local.MULTIPLIER_VERSUS +  Local.TAGS_NAME[1];
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.ed_animal(tx);
				}
			}
		break;
		case 24:
			{
			curve_to_draw.title = Local.MULTIPLIER_VERSUS +  Local.TAGS_NAME[2];
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.ed_humain(tx);
				}
			}
		break;
		case 25:
			{
			curve_to_draw.title = Local.MULTIPLIER_VERSUS +  Local.TAGS_NAME[3];
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.ed_peau_verte(tx);
				}
			}
		break;
		case 26:
			{
			curve_to_draw.title = Local.MULTIPLIER_VERSUS +  Local.TAGS_NAME[4];
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.ed_demon(tx);
				}
			}
		break;
		case 27:
			{
			curve_to_draw.title = Local.MULTIPLIER_VERSUS +  Local.TAGS_NAME[5];
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.ed_champion(tx);
				}
			}
		break;
		case 28:
			{
			curve_to_draw.title = Local.BUY_PRICE_MULTIPLIER;
			curve_to_draw2.title = Local.SALE_PRICE_MULTIPLIER;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.coeff_achat(tx);
				curve_to_draw2.arr_y[x] =  Joueur.universe.coeff_vente(tx);
				}
			curves_to_draw.add(curve_to_draw2);
			}
		break;
		case 29:
			{
			curve_to_draw.title = Local.PROBABILITY_TO_FLEE;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.chance_fuite(tx);
				}
			}
		break;
		case 30:
			{
			curve_to_draw.title = Local.TIME_TO_FLEE;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.temps_fuite(tx);
				}
			}
		break;
		case 31:
			{
			curve_to_draw.title = Local.TIME_BEFORE_THE_FIRST_ATTACK;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.initiative(tx);
				}
			}
		break;
		case 32:
		{
			curve_to_draw.title = Local.PROBABILITY_OF_DODGING_A_FINAL_BLOW;
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.proba_immunite_final(tx);
			}
		}
		break;
		case 33:
			{
			curve_to_draw.title = Local.TIME_MULTIPLIER;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.facteur_temps(tx);
				}
			}
		break;
		case 34:
			{
			curve_to_draw.title = Local.DAMAGE_INFLICTED_ON_ATTACKERS;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.epines(tx);
				}
			}
		break;
		case 35:
				{
				curve_to_draw.title = Local.DAMAGE_REFLECTED_ON_ATTACKERS;
				for (int x = 0; x< sizar; x++)
					{
					tx = ((double)x/(double)(sizar-1))*max_pt;
					curve_to_draw.arr_x[x] = tx;
					curve_to_draw.arr_y[x] = Joueur.universe.represailles(tx);
					}
				}
		break;
		case 36:
			{
					curve_to_draw.title = Local.PROPORTION_OF_THE_CADAVERS_HEALTH_POINTS_ABSORBED;
					for (int x = 0; x< sizar; x++)
					{
						tx = ((double)x/(double)(sizar-1))*max_pt;
						curve_to_draw.arr_x[x] = tx;
						curve_to_draw.arr_y[x] = Joueur.universe.necrophagie(tx);
					}
				}
		break;
		case 37:
			{
				curve_to_draw.title = Local.CRAFTING_TIME;
				for (int x = 0; x< sizar; x++)
				{
					tx = ((double)x/(double)(sizar-1))*max_pt;
					curve_to_draw.arr_x[x] = tx;
					curve_to_draw.arr_y[x] = Joueur.universe.temps_craft(tx);
				}
			}
		break;
		case 38:
		{
			curve_to_draw.title = Local.CRAFTING_EFFICIENCY;
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.rendement(tx);
			}
		}
		break;
		case 39:
		{
			curve_to_draw.title = Local.COST_IN_ORBS_OF_AN_ORB;
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.economie_orbe(tx);
			}
		}
		break;
		case 40:
		{
			curve_to_draw.title = Local.MERCHANT_LEVEL_MULTIPLIER;
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				double ptb = Joueur.universe.multiplicateur_niveau_boutique(tx);
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = ptb;
			}
		}
		break;
		case 41:
		{
			curve_to_draw.title = Local.NUMBER_OF_MERCHANTS_ITEMS;
			curve_to_draw2.title = Local.MIN_NUMBER_OF_MERCHANTS_ITEMS;
			curve_to_draw3.title = Local.MAX_NUMBER_OF_MERCHANTS_ITEMS;
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				double ptb = Joueur.universe.taille_boutique(tx);
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = ptb;
				curve_to_draw2.arr_x[x] = tx;
				curve_to_draw2.arr_y[x] = StaticItem.tailleMin(ptb);
				curve_to_draw3.arr_x[x] = tx;
				curve_to_draw3.arr_y[x] = StaticItem.tailleMax(ptb);
			}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
		}
		break;
		case 42:
		{
			int lev1, lev2, lev3;
			lev1 = zoomLevel[zoom]/10;  if(lev1<10) lev1++;
			lev2 = zoomLevel[zoom]/5; if(lev2<10) lev2++;
			lev3 = zoomLevel[zoom]/2; if(lev3<10) lev3++;
			
			curve_to_draw.title = Local.PROBABILITY_OF_DETECTING_A_TRAP + Local.LOW_OF_LEVEL + lev1;
			curve_to_draw2.title = Local.PROBABILITY_OF_DETECTING_A_TRAP + Local.LOW_OF_LEVEL + lev2;
			curve_to_draw3.title = Local.PROBABILITY_OF_DETECTING_A_TRAP + Local.LOW_OF_LEVEL + lev3;
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.proba_trouver_piege(tx,lev1);
				curve_to_draw2.arr_x[x] = tx;
				curve_to_draw2.arr_y[x] = Joueur.universe.proba_trouver_piege(tx,lev2);
				curve_to_draw3.arr_x[x] = tx;
				curve_to_draw3.arr_y[x] = Joueur.universe.proba_trouver_piege(tx,lev3);
			}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
		}
		break;
		case 43:
		{
			curve_to_draw.title = Local.INITIATIVE_BONUS_AGAINST_A_TRAP;
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.bonus_initiative_piege(tx);
			}
		}
		break;
		case 44:
		{
			curve_to_draw.title = Local.TRAPS_REDUCTION_MULTIPLIER;
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.bonus_resistance_vs_piege(tx);
			}
		}
		break;
		case 45:
		{
			curve_to_draw.title = Local.INCOME_PER_SECOND;
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.rente_par_rencontre(tx);
			}
		}
		break;
		case 46:
		{
			curve_to_draw.title = Local.SKILL_POINTS_PER_LEVEL;
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.points_par_niveau(tx);
			}
		}
		break;
		case 47:
			{
			curve_to_draw.title = Local.GENERAL_EXPERIENCE_BONUS;
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.bonus_xp(tx);
				}
			}
		break;
		case 48:
			{
			double lev1, lev2, lev3;
			lev1 = -5;
			lev2 = -10;
			lev3 = -(int)(Math.max(max_pt/10.0,20.0));
			
			curve_to_draw.title = String.format(Local.EXPERIENCE_SPECIFIC_MULTIPLIER,lev1);
			curve_to_draw2.title = String.format(Local.EXPERIENCE_SPECIFIC_MULTIPLIER,lev2);
			curve_to_draw3.title = String.format(Local.EXPERIENCE_SPECIFIC_MULTIPLIER,lev3);
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.modif_exp_lvl(tx,0,lev1);
				curve_to_draw2.arr_x[x] = tx;
				curve_to_draw2.arr_y[x] = Joueur.universe.modif_exp_lvl(tx,0,lev2);
				curve_to_draw3.arr_x[x] = tx;
				curve_to_draw3.arr_y[x] = Joueur.universe.modif_exp_lvl(tx,0,lev3);
			}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			}
		break;
		case 49:
			{
			double lev1, lev2, lev3;
			lev1 = 5;
			lev2 = 10;
			lev3 = (int)(Math.max(max_pt/10.0,20.0));
			
			curve_to_draw.title = String.format(Local.EXPERIENCE_SPECIFIC_MULTIPLIER,lev1);
			curve_to_draw2.title = String.format(Local.EXPERIENCE_SPECIFIC_MULTIPLIER,lev2);
			curve_to_draw3.title = String.format(Local.EXPERIENCE_SPECIFIC_MULTIPLIER,lev3);
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.modif_exp_lvl(0,tx,lev1);
				curve_to_draw2.arr_x[x] = tx;
				curve_to_draw2.arr_y[x] = Joueur.universe.modif_exp_lvl(0,tx,lev2);
				curve_to_draw3.arr_x[x] = tx;
				curve_to_draw3.arr_y[x] = Joueur.universe.modif_exp_lvl(0,tx,lev3);
			}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			}
		break;
		case 50:
			{

			curve_to_draw.title = Local.PENALTY_MULTIPLIER;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.penalty_reduction(tx);
			}
			}
		break;
		case 51:
			{

			curve_to_draw.title = Local.ZONE_LEVEL_MULTIPLIER;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.zone_multiplier(tx);
			}
			}
		break;
		case 52:
			{

			curve_to_draw.title = Local.DIVINE_POINTS_MULTIPLIER;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.points_divins_multiplier(tx);
			}
			}
		break;
		case 53:
			{

			curve_to_draw.title = Local.FIRST_STRIKE_MULTIPLIER;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.multi_premier_coup(tx);
			}
			}
		break;
		case 54:
			{

			curve_to_draw.title = Local.EQUATION_CAP_MULTIPLIER;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.divine_cap_eq(tx);
			}
			}
		break;
		case 55:
			{

			curve_to_draw.title = Local.CONST_CAP_MULTIPLIER;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.divine_cap_const(tx);
			}
			}
		break;
		case 56:
			{

			curve_to_draw.title = Local.RESOURCES_WEIGHT_MULTIPLIER;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.resources_weight_multiplier(tx);
			}
			}
		break;
		case 57:
			{

			curve_to_draw.title = Local.EQUIPMENT_WEIGHT_MULTIPLIER;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.equipment_weight_multiplier(tx);
			}
			}
		break;
		case 58:
			{

			curve_to_draw.title = Local.COLD_RESISTANCE;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.resistance_froid(tx);
			}
			}
		break;
		case 59:
			{

			curve_to_draw.title = Local.HEAT_RESISTANCE;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.resistance_chaud(tx);
			}
			}
		break;
		case 60:
			{

			curve_to_draw.title = Local.PRECIPITATION_RESISTANCE;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.resistance_precipitations(tx);
			}
			}
		break;
		case 61:
			{

			curve_to_draw.title = Local.COLD_AFFINITY;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.affinite_froid(tx);
			}
			}
		break;
		case 62:
			{

			curve_to_draw.title = Local.HEAT_AFFINITY;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.affinite_chaud(tx);
			}
			}
		break;
		case 63:
			{

			curve_to_draw.title = Local.PRECIPITATION_AFFINITY;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.affinite_precipitations(tx);
			}
			}
		break;
		case 64:
			{

			curve_to_draw.title = Local.OVERLOAD_RESISTANCE;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.resistance_surcharge(tx);
			}
			}
		break;
		case 65:
			{
			curve_to_draw.title = Local.UNDERLOAD_AFFINITY;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.affinite_souscharge(tx);
			}
			}
		break;
		case 66:
			{
			curve_to_draw.title = Local.ACHIEVEMENTS_AFFINITY;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.affinite_hautfaits(tx);
			}
			}
		break;
		case 67:
			{

			curve_to_draw.title = Local.CLEARANCE_SALE_INVENTORY_MULTIPLIER;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.clearance_sale_inventory_multiplier(tx);
			}
			}
		break;
		case 68:
			{

			curve_to_draw.title = Local.DISCOUNT_PRICE_MULTIPLIER;
			
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.discount_multiplier(tx);
			}
			}
		break;
	}
	}
	
	private javax.swing.JPanel getJFrameContentPane() {
		if (ivjJFrameContentPane == null) {

			prompt = new JLabel();
			prompt.setBounds(new Rectangle(10, 3, 400, 23));
			prompt.setFont(new Font(Local.FONT_DIALOG, Font.BOLD, 16));

			stats_list = new DefaultListModel();

			stats_list.addElement(Local.TIME_GLOBAL);
			stats_list.addElement(Local.TIME_FIGHT_TRAPS);
			stats_list.addElement(Local.TIME_LIFE_MANAGEMENT);
			stats_list.addElement(Local.TIME_SHOPPING_CRAFTING);
			stats_list.addElement(Local.INCOME_GLOBAL);
			stats_list.addElement(Local.INCOME_SALES);
			stats_list.addElement(Local.LOSSES_AND_EXPENSES);
			stats_list.addElement(Local.EXPERIENCE);
			stats_list.addElement(Local.DIVINE_POINTS);
			stats_list.addElement(Local.MONSTER_LEVEL);
			stats_list.addElement(Local.MONSTERS_SKILLS_POINTS);
			stats_list.addElement(Local.BASE_EXPERIENCE);
			stats_list.addElement(Local.TRAPS_DAMAGE);
			stats_list.addElement(Local.MONSTER_GOLD_LOOT);
			
			for (int i=0; i< Joueur.nb_stats ; i++)
				stats_list.addElement(Joueur.stats_name[i]);

			liste_s = new JList(stats_list);
			liste_s.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
			liste_s.setSelectedIndex(0);
			liste_s.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
				public void valueChanged(javax.swing.event.ListSelectionEvent e) {refresh();
				}
			});

			scroll = new JScrollPane(liste_s);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setBounds(new Rectangle(10, 32, 220, 320));

			drawSurf = new MyDraw();
			drawSurf.setBounds(new Rectangle(235, 32, 420, 320));

			drawSurf.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
				public void mouseWheelMoved(java.awt.event.MouseWheelEvent e) {zoom(e);
				}
			});

			prompt.setFont(new Font(Local.FONT_TIMES, Font.BOLD, 16));
			liste_s.setFont(new Font(Local.FONT_TIMES, Font.BOLD, 11));

			ivjJFrameContentPane = new javax.swing.JPanel();
			ivjJFrameContentPane.setLayout(null);

			ivjJFrameContentPane.add(scroll);
			ivjJFrameContentPane.add(drawSurf);
			ivjJFrameContentPane.add(prompt);
		}
		return ivjJFrameContentPane;
	}



	private void initialize() {
		this.setLocation(new Point(15, 15));
		this.setSize(new Dimension(670, 420));
		this.setResizable(false);
		this.setModal(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle(Local.WINDOW_CURVES);
		this.setContentPane(getJFrameContentPane());
		
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
			KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");

		getRootPane().getActionMap().put("Cancel", new AbstractAction(){
	            public void actionPerformed(ActionEvent e) {setVisible(false);}});
	
		refresh();
	}

} 