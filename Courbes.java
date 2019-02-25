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
	private int zoom;
	private int range;
	
    public static int[] zoomLevel = {5,10,20,50,100,200,500,1000,2000,5000};
	
	public Courbes(Player JJ) {
		super();
		Joueur = JJ;
		initialize();
	}

	public void refreshAfterZoom()
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

	private void refresh()
	{	
		prompt.setText((String)stats_list.get(liste_s.getSelectedIndex()));
		zoom = 2;
		refreshAfterZoom();
	}
	
		private void compute_the_curves(int idx)
		{
		idx = idx-6;
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
		case -6:
			{
			curve_to_draw.title = "Combat/pièges";
			curve_to_draw2.title = "Résurrection/régénération";
			curve_to_draw3.title = "Shopping/crafting";
			int asize = Joueur.t_stats.data.size();
			if (asize > 1) asize--;

			double scale = sizar/asize;

			for (int x = 0; x < sizar; x++)
				{
				curve_to_draw.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw2.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw3.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);

				curve_to_draw.arr_y[x] = 
					Joueur.t_stats.data.get((int)(x/scale))[0]+
					Joueur.t_stats.data.get((int)(x/scale))[1]+
					Joueur.t_stats.data.get((int)(x/scale))[2]+
					Joueur.t_stats.data.get((int)(x/scale))[8];
				curve_to_draw2.arr_y[x] = 
					Joueur.t_stats.data.get((int)(x/scale))[3]+
					Joueur.t_stats.data.get((int)(x/scale))[4];
				curve_to_draw3.arr_y[x] = 
					Joueur.t_stats.data.get((int)(x/scale))[5]+
					Joueur.t_stats.data.get((int)(x/scale))[6]+
					Joueur.t_stats.data.get((int)(x/scale))[7];
				}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			}
		break;
		case -5:
		{
			curve_to_draw.title = "Recherche d'ennemi";
			curve_to_draw2.title = "Initiative";
			curve_to_draw3.title = "Attaque";
			curve_to_draw4.title = "Pièges";
			int asize = Joueur.t_stats.data.size();
			if (asize > 1) asize--;

			double scale = sizar/asize;

			for (int x = 0; x < sizar; x++)
			{
				curve_to_draw.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw2.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw3.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw4.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				
				curve_to_draw.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[0];
				curve_to_draw2.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[1];
				curve_to_draw3.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[2];
				curve_to_draw4.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[8];
			}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			curves_to_draw.add(curve_to_draw4);
		}
		break;
		case -4:
		{
			curve_to_draw.title = "Résurrection";
			curve_to_draw2.title = "Régénération";
			int asize = Joueur.t_stats.data.size();
			if (asize > 1) asize--;

			double scale = sizar/asize;

			for (int x = 0; x < sizar; x++)
			{
				curve_to_draw.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				curve_to_draw2.arr_x[x] = (double)x*(asize*TimeStats.TIME_STEP)/(double)(sizar-1);
				
				curve_to_draw.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[3];
				curve_to_draw2.arr_y[x] = Joueur.t_stats.data.get((int)(x/scale))[4];
			}
			curves_to_draw.add(curve_to_draw2);
		}
		break;
		case -3:
		{
			curve_to_draw.title = "Recherche d'un marchand";
			curve_to_draw2.title = "Recherche d'une forge";
			curve_to_draw3.title = "Crafting";
			int asize = Joueur.t_stats.data.size();
			if (asize > 1) asize--;

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
		case -2:
		{
			curve_to_draw.title = "Or sur les monstres";
			curve_to_draw2.title = "Rente viagère";
			curve_to_draw3.title = "Vente d'objets";
			int asize = Joueur.t_stats.data_money.size();
			if (asize > 1) asize--;

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
		case -1:
		{
			curve_to_draw.title = "Vente d'objets normaux";
			curve_to_draw2.title = "Vente d'objets magiques";
			curve_to_draw3.title = "Vente d'objets rares";
			curve_to_draw4.title = "Vente de ressources";
			curve_to_draw5.title = "Autres ventes";
			
			int asize = Joueur.t_stats.data_money.size();
			if (asize > 1) asize--;

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
		case 0:
			{
			curve_to_draw.title = "Nombre d'attaques par seconde";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.att_per_sec(tx);
				}
			}
		break;
		case 1:
			{
			curve_to_draw.title = "Dégâts moyens d'une attaque basique";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.dmg_base(tx);
				}
			}
		break;
		case 2:
			{
			curve_to_draw.title = "Dégâts encaissés";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.reduc(tx);
				}
			}
		break;
		case 3:
			{
			curve_to_draw.title = "Quantité de dégâts absorbés";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.absorption(tx);
				}
			}
		break;
		case 4:
			{
			int es1, es2, es3;
			es1 = zoomLevel[zoom]/5;
			es2 = zoomLevel[zoom]/2;
			es3 = zoomLevel[zoom];
			
			curve_to_draw.title = "Probabilité d'esquiver contre une précision de " + es1;
			curve_to_draw2.title = "Probabilité d'esquiver contre une précision de " + es2;
			curve_to_draw3.title = "Probabilité d'esquiver contre une précision de " + es3;

			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] 
				= curve_to_draw3.arr_x[x] = tx;

				curve_to_draw.arr_y[x]  = Player.esquive_proba(tx,es1);
				curve_to_draw2.arr_y[x] = Player.esquive_proba(tx,es2);
				curve_to_draw3.arr_y[x] = Player.esquive_proba(tx,es3);
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

			curve_to_draw.title = "Probabilité de toucher contre une esquive de " + es1;
			curve_to_draw2.title = "Probabilité de toucher contre une esquive de " + es2;
			curve_to_draw3.title = "Probabilité de toucher contre une esquive de " + es3;

			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] 
				= curve_to_draw3.arr_x[x] = tx;

				curve_to_draw.arr_y[x] = 1- Player.esquive_proba(es1,tx);
				curve_to_draw2.arr_y[x] = 1- Player.esquive_proba(es2,tx);
				curve_to_draw3.arr_y[x] = 1- Player.esquive_proba(es3,tx);
				}

			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			}
		break;
		case 6:
			{
			curve_to_draw.title = "Probabilité d'infliger un coup critique";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.crit_proba(tx);
				}
			}
		break;
		case 7:
			{
			curve_to_draw.title = "Multiplicateur de dégât des coups critiques";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.multi_crit(tx);
				}
			}
		break;
		case 8:
			{
			curve_to_draw.title = "Fraction des dégâts infligés convertis en vie";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.vol_de_vie(tx);
				}
			}
		break;
		case 9:
			{
			int con1, con2, con3;
			con1 = (int)Joueur.CON();
			con2 = (int)(Joueur.CON()+5);
			con3 = (int)(Joueur.CON()+20);

			curve_to_draw.title = "Points de vie pour une constitution de " + con1;
			curve_to_draw2.title = "Points de vie pour une constitution de " + con2;
			curve_to_draw3.title = "Points de vie pour une constitution de " + con3;

			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] 
				= curve_to_draw3.arr_x[x] = tx;

				curve_to_draw.arr_y[x]  = Player.vie_max(tx,con1);
				curve_to_draw2.arr_y[x] = Player.vie_max(tx,con2);
				curve_to_draw3.arr_y[x] = Player.vie_max(tx,con3);
				}

			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			}
		break;
		case 10:
			{
			curve_to_draw.title = "Points de vie par point de vitalité";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.pv_per_vita(tx);
				}
			}
		break;
		case 11:
			{
			int con1, con2, con3;
			con1 = (int)Joueur.CON();
			con2 = (int)(Joueur.CON()+5);
			con3 = (int)(Joueur.CON()+20);

			curve_to_draw.title = "Régénération par seconde pour " + con1 + " de constitution";
			curve_to_draw2.title = "Régénération par seconde pour " + con2 + " de constitution";
			curve_to_draw3.title = "Régénération par seconde pour " + con3 + " de constitution";

			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] 
				= curve_to_draw3.arr_x[x] = tx;

				curve_to_draw.arr_y[x]  = Player.regen(tx,con1);
				curve_to_draw2.arr_y[x] = Player.regen(tx,con2);
				curve_to_draw3.arr_y[x] = Player.regen(tx,con3);
				}

			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			}
		break;
		case 12:
			{
			int lev1, lev2, lev3;
			lev1 = zoomLevel[zoom]/10;  if(lev1<10) lev1++;
			lev2 = zoomLevel[zoom]/5; if(lev2<10) lev2++;
			lev3 = zoomLevel[zoom]/2; if(lev3<10) lev3++;

			curve_to_draw.title = "Temps de résurrection en secondes (niveau "+ lev1 + ")";
			curve_to_draw2.title = "Temps de résurrection en secondes (niveau "+ lev2 + ")";
			curve_to_draw3.title = "Temps de résurrection en secondes (niveau "+ lev3 + ")";

			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] 
				= curve_to_draw3.arr_x[x] = tx;

				curve_to_draw.arr_y[x]  =  Player.temps_res(tx,lev1);
				curve_to_draw2.arr_y[x] =  Player.temps_res(tx,lev2);
				curve_to_draw3.arr_y[x] =  Player.temps_res(tx,lev3);
				}

			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			}
		break;
		case 13:
			{
			curve_to_draw.title = "Charge maximale en kilogrammes";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.charge_max(tx);
				}
			}
		break;
		case 14:
			{
			curve_to_draw.title = "Temps moyen pour trouver un adversaire";
			curve_to_draw2.title = "Temps moyen pour trouver un marchand";
			curve_to_draw3.title = "Temps moyen pour trouver une forge mystique";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x]  = curve_to_draw3.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.temps_traque(tx);
				curve_to_draw2.arr_y[x] =  Player.temps_shop(tx);
				curve_to_draw3.arr_y[x] =  Player.temps_forge(tx);
				}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
			}
		break;
		case 15:
			{
			curve_to_draw.title = "Taille moyenne des tas de ressources";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.multiplicateur_res(tx);
				}
			}
		break;
		case 16:
			{
			curve_to_draw.title = "Probabilité qu'un objet normal devienne magique";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.chance_magique(tx);
				}
			}
		break;
		case 17:
			{
			curve_to_draw.title = "Probabilité qu'un objet magique devienne rare";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.chance_rare(tx);
				}
			}
		break;
		case 18:
			{
			curve_to_draw.title = "Probabilité qu'un objet soit de qualité supérieure";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.chance_qualite(tx);
				}
			}
		break;
		case 19:
			{
			curve_to_draw.title = "Nombre moyen d'objets sur un monstre";
			curve_to_draw2.title = "Nombre minimal d'objets sur un monstre";
			curve_to_draw3.title = "Nombre maximal d'objets sur un monstre";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				double ptb = Player.quantite_drop(tx);
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
			curve_to_draw.title = "Puissance minimale des enchantements";
			curve_to_draw2.title = "Puissance maximale des enchantements";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.puissance_ench_inf(tx);
				curve_to_draw2.arr_y[x] = Player.puissance_ench_sup(tx);
				}
			curves_to_draw.add(curve_to_draw2);
			}
		break;
		case 21:
			{
			curve_to_draw.title = "Multiplicateur d'or sur les monstres";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.multiplicateur_or(tx);
				}
			}
		break;
		case 22:
			{
			curve_to_draw.title = "Multiplicateur de dégâts contre les morts-vivants";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.ed_specific_monster(tx);
				}
			}
		break;
		case 23:
			{
			curve_to_draw.title = "Multiplicateur de dégâts contre les animaux";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.ed_specific_monster(tx);
				}
			}
		break;
		case 24:
			{
			curve_to_draw.title = "Multiplicateur de dégâts contre les humains";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.ed_specific_monster(tx);
				}
			}
		break;
		case 25:
			{
			curve_to_draw.title = "Multiplicateur de dégâts contre les peaux-vertes";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.ed_specific_monster(tx);
				}
			}
		break;
		case 26:
			{
			curve_to_draw.title = "Multiplicateur de dégâts contre les démons";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.ed_specific_monster(tx);
				}
			}
		break;
		case 27:
			{
			curve_to_draw.title = "Multiplicateur de dégâts contre les champions";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.ed_specific_monster(tx);
				}
			}
		break;
		case 28:
			{
			curve_to_draw.title = "Coefficient d'achat";
			curve_to_draw2.title = "Coefficient de vente";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = curve_to_draw2.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.coeff_achat(tx);
				curve_to_draw2.arr_y[x] =  Player.coeff_vente(tx);
				}
			curves_to_draw.add(curve_to_draw2);
			}
		break;
		case 29:
			{
			curve_to_draw.title = "Probabilité de fuite à chaque tentative";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.chance_fuite(tx);
				}
			}
		break;
		case 30:
			{
			curve_to_draw.title = "Temps moyen d'une tentative de fuite";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.temps_fuite(tx);
				}
			}
		break;
		case 31:
			{
			curve_to_draw.title = "Temps moyen avant d'attaquer";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.initiative(tx);
				}
			}
		break;
		case 32:
			{
			curve_to_draw.title = "Multiplicateur d'expérience";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.bonus_xp(tx);
				}
			}
		break;
		case 33:
			{
			curve_to_draw.title = "Vitesse du temps";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.facteur_temps(tx);
				}
			}
		break;
		case 34:
			{
			curve_to_draw.title = "Dégats infligés aux attaquants";
			for (int x = 0; x< sizar; x++)
				{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.epines(tx);
				}
			}
		break;
		case 35:
				{
				curve_to_draw.title = "Proportion des dégats réfléchis sur les attaquants";
				for (int x = 0; x< sizar; x++)
					{
					tx = ((double)x/(double)(sizar-1))*max_pt;
					curve_to_draw.arr_x[x] = tx;
					curve_to_draw.arr_y[x] = Player.represailles(tx);
					}
				}
		break;
		case 36:
			{
					curve_to_draw.title = "Proportion de la vie des cadavres récupérée";
					for (int x = 0; x< sizar; x++)
					{
						tx = ((double)x/(double)(sizar-1))*max_pt;
						curve_to_draw.arr_x[x] = tx;
						curve_to_draw.arr_y[x] = Player.necrophagie(tx);
					}
				}
		break;
		case 37:
			{
				curve_to_draw.title = "Temps moyen du crafting en secondes";
				for (int x = 0; x< sizar; x++)
				{
					tx = ((double)x/(double)(sizar-1))*max_pt;
					curve_to_draw.arr_x[x] = tx;
					curve_to_draw.arr_y[x] = Player.temps_craft(tx);
				}
			}
		break;
		case 38:
		{
			curve_to_draw.title = "Rendement du craft";
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.rendement(tx);
			}
		}
		break;
		case 39:
		{
			curve_to_draw.title = "Coût en orbes des formules";
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.economie_orbe(tx);
			}
		}
		break;
		case 40:
		{
			curve_to_draw.title = "Niveau moyen des marchands (ajouté au niveau de la zone)";
			curve_to_draw2.title = "Niveau minimal des marchands (ajouté au niveau de la zone)";
			curve_to_draw3.title = "Niveau maximal des marchands (ajouté au niveau de la zone)";
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				double ptb = Player.niveau_boutique(tx);
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
		case 41:
		{
			curve_to_draw.title = "Nombre moyen d'objets proposés par les marchands";
			curve_to_draw2.title = "Nombre minimal d'objets proposés par les marchands";
			curve_to_draw3.title = "Nombre maximal d'objets proposés par les marchands";
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				double ptb = Player.taille_boutique(tx);
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
			
			curve_to_draw.title = "Probabilité de détection d'un piège (niveau "+ lev1 + ")";
			curve_to_draw2.title = "Probabilité de détection d'un piège (niveau "+ lev2 + ")";
			curve_to_draw3.title = "Probabilité de détection d'un piège (niveau "+ lev3 + ")";
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.proba_trouver_piege(tx,lev1);
				curve_to_draw2.arr_x[x] = tx;
				curve_to_draw2.arr_y[x] = Player.proba_trouver_piege(tx,lev2);
				curve_to_draw3.arr_x[x] = tx;
				curve_to_draw3.arr_y[x] = Player.proba_trouver_piege(tx,lev3);
			}
			curves_to_draw.add(curve_to_draw2);
			curves_to_draw.add(curve_to_draw3);
		}
		break;
		case 43:
		{
			curve_to_draw.title = "Bonus d'initiative face aux pièges (multiplicateur)";
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.bonus_initiative_piege(tx);
			}
		}
		break;
		case 44:
		{
			curve_to_draw.title = "Bonus de résistance aux pièges (multiplicateur)";
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.bonus_resistance_vs_piege(tx);
			}
		}
		break;
		case 45:
		{
			curve_to_draw.title = "Rente en écus par seconde";
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.rente_par_seconde(tx);
			}
		}
		break;
		case 46:
		{
			curve_to_draw.title = "Points à distibuer par niveau";
			for (int x = 0; x< sizar; x++)
			{
				tx = ((double)x/(double)(sizar-1))*max_pt;
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Player.points_par_niveau(tx);
			}
		}
		break;
	}
	}
	
	private javax.swing.JPanel getJFrameContentPane() {
		if (ivjJFrameContentPane == null) {

			prompt = new JLabel();
			prompt.setBounds(new Rectangle(10, 3, 400, 23));
			prompt.setFont(new Font("Dialog", Font.BOLD, 16));

			stats_list = new DefaultListModel();

			stats_list.addElement("Temps (global)");
			stats_list.addElement("Temps (combat/pièges)");
			stats_list.addElement("Temps (résurrection/régénération)");
			stats_list.addElement("Temps (shopping/crafting)");
			stats_list.addElement("Revenu (global)");
			stats_list.addElement("Revenu (vente)");

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

			prompt.setFont(new Font("Times Roman", Font.BOLD, 16));
			liste_s.setFont(new Font("Times Roman", Font.BOLD, 11));

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
		this.setModal(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Courbes sur les mécanismes du jeu");
		this.setContentPane(getJFrameContentPane());
		
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
			KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");

		getRootPane().getActionMap().put("Cancel", new AbstractAction(){
	            public void actionPerformed(ActionEvent e) {setVisible(false);}});
	
		refresh();
	}

} 