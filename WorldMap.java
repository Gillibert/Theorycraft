import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class WorldMap extends javax.swing.JDialog {
    private Image the_map;
    private String the_label;
    private Font the_font;
    private Color the_color;
    private int previous_zone;
    private javax.swing.JPanel ivjJFrameContentPane = null;
    private Player Joueur;
    private World Monde;

    public void paint(Graphics g2) {
	Graphics2D g = (Graphics2D) g2 ;
	g.drawImage(the_map, 4, 24, null);
	g.setFont(the_font);
	g.setColor(the_color);
	g.drawString(the_label, 36, 62);

	g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER,0.15f));
	Rectangle tmp;
	for(int i=0; i < Monde.zonesR.length; i++)
	    {
		if (Joueur.level < Joueur.universe.get_zone_level(Monde.zones[i].level))
		    the_color = Color.red;
		else the_color = Color.green;
		g.setColor(the_color);
		tmp = Monde.zonesR[i];
		g.fillRect(tmp.x+2, tmp.y+22, tmp.width, tmp.height);
	    }
    }

    public WorldMap(World W,Player J) {
	super();
	previous_zone = -2;
	the_label = "";
	the_color = Color.green;
	the_font = new Font("Times New Roman", Font.PLAIN, 24);
	the_map = new ImageIcon(W.map_img).getImage();
	Joueur=J;
	Monde=W;
	initialize();
    }

    public void voyager()
    {
	this.setVisible(true);
	previous_zone = -2;
	this.repaint();
    }

	public String get_zone_label(int x) {
		if (x==-1) return "Sélectionnez une zone";
		return Monde.zones[x].name + " (" + Joueur.universe.get_zone_level(Monde.zones[x].level) + "-" + Joueur.universe.get_zone_max_level(Monde.zones[x].level) + ")";
	}
		
    private void initialize() {
	this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	this.setBounds(25, 25, 648, 453);
	this.setResizable(false);
	this.setModal(true);
	this.setTitle("Carte du monde");

    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
    
	getRootPane().getActionMap().put("Cancel", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {setVisible(false);}});

	ivjJFrameContentPane = new javax.swing.JPanel();
	ivjJFrameContentPane.setLayout(null);

	this.setContentPane(ivjJFrameContentPane);

	this.addMouseMotionListener(new java.awt.event.MouseMotionListener() {
		public void mouseMoved(java.awt.event.MouseEvent e) {
		    int z=Monde.get_zone(e.getX(),e.getY());
		    if(z!=previous_zone)
			{
			    the_label = get_zone_label(z);
			    if(z==-1)  the_color = Color.white;
			    else if (Joueur.level < Joueur.universe.get_zone_level(Monde.zones[z].level))
				the_color = Color.red;
			    else the_color = Color.green;
			    repaint();
			    previous_zone = z;
			}
		}

		public void mouseDragged(java.awt.event.MouseEvent e) {}
	    });

	this.addMouseListener(new java.awt.event.MouseListener() {
		public void mouseClicked(java.awt.event.MouseEvent e) {
		    int z=Monde.get_zone(e.getX(),e.getY());
		    if(z!=-1 && Joueur.level >= Joueur.universe.get_zone_level(Monde.zones[z].level))
			{
			    Joueur.zone=z;
			    setVisible(false);
			}
		}

		public void mouseExited(java.awt.event.MouseEvent e) {}
		public void mousePressed(java.awt.event.MouseEvent e) {}
		public void mouseEntered(java.awt.event.MouseEvent e) {}
		public void mouseReleased(java.awt.event.MouseEvent e) {}
	    });


    }


} 
