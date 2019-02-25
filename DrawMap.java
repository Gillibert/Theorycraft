import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class DrawMap extends JPanel {
		private Image the_map;
		private Font the_font;
		private Color the_color;
		private String the_label;
		private Player Joueur;
		private int zone;
		
		private ArrayList<CurveObject> the_curves;

		public DrawMap(Player p)
		{
		super();
		Joueur = p;
		the_label = "";
		the_color = Color.green;
		the_font = new Font(Local.FONT_TIMES, Font.PLAIN, 24);
		the_map = new ImageIcon(Joueur.universe.map.map_img).getImage();
		}

		public void SetPlayer(Player p)
		{
			Joueur = p;
		}
		
		public void setZone(int z)
		{
			zone = z;
		}
		
		public String get_zone_label(int x) {
			if (x==-1) return Local.SELECT_A_ZONE;
			return Joueur.universe.map.zonesName.get(x) + " (" + Joueur.universe.get_zone_level(x) + "-" + Joueur.universe.get_zone_max_level(x) + ")";
		}
		
		
		public void paint(Graphics g2) 
		{
		double mzl = Joueur.max_zone_level();
		setDoubleBuffered(true);
		Graphics2D g = (Graphics2D) g2 ;
		the_label = get_zone_label(zone);
		
		g.drawImage(the_map, 0, 0, null);
		g.setFont(the_font);
		
		g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER,0.15f));
		Rectangle tmp;
		for(int i=0; i < Joueur.universe.nombre_zones()-1.0; i++)
	    {
		if (mzl < Joueur.universe.get_zone_level(i))
		    the_color = Color.red;
		else the_color = Color.green;
		g.setColor(the_color);
		tmp = Joueur.universe.map.zonesR.get(i);
		g.fillRect(tmp.x, tmp.y, tmp.width, tmp.height);
	    }

		g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER,1.0f));
		if (mzl < Joueur.universe.get_zone_level(zone))
		    the_color = Color.red;
		else the_color = Color.green;
		g.setColor(the_color);
		g.drawString(the_label, 10, 30);
	  	}
		
		
}