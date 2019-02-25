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
	private int px=0;
	private int py=0;
	
	public WorldMap(Player J) {
	super();
	previous_zone = -2;
	the_label = "";
	the_color = Color.green;
	the_font = new Font(Local.FONT_TIMES, Font.PLAIN, 24);
	the_map = new ImageIcon(J.universe.map.map_img).getImage();
	Joueur=J;
	initialize();
    }
	
	public void SetPlayer(Player J)
	{
		Joueur = J;
		refresh();
	}
	
    public void paint(Graphics g2) {
	Graphics2D g = (Graphics2D) g2 ;
	g.drawImage(the_map, 4, 24, null);
	g.setFont(the_font);
	g.setColor(the_color);
	g.drawString(the_label, 36, 62);

	g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER,0.15f));
	Rectangle tmp;
	for(int i=0; i < Joueur.universe.map.zonesR.size(); i++)
	    {
		if (Joueur.max_zone_level() < Joueur.universe.get_zone_level(i))
		    the_color = Color.red;
		else the_color = Color.green;
		g.setColor(the_color);
		tmp = Joueur.universe.map.zonesR.get(i);
		g.fillRect(tmp.x+2, tmp.y+22, tmp.width, tmp.height);
	    }
    }

    public void voyager()
    {
	this.setVisible(true);
	previous_zone = -2;
	px = 0;
	py = 0;
	this.refresh();
    }
	
	public void refresh()
	{
	SwingUtilities.invokeLater(new Runnable() {
     public void run() {
			int z=Joueur.universe.map.get_zone(px,py);
		    if(z!=previous_zone)
			{
			    the_label = get_zone_label(z);
			    if(z==-1)  the_color = Color.white;
			    else if (Joueur.max_zone_level() < Joueur.universe.get_zone_level(z))
				the_color = Color.red;
			    else the_color = Color.green;
			    repaint();
			    previous_zone = z;
			}
      }
    });
	}

	public String get_zone_label(int x) {
		if (x==-1) return Local.SELECT_A_ZONE;
		return Joueur.universe.map.zonesName.get(x) + " (" + Joueur.universe.get_zone_level(x) + "-" + Joueur.universe.get_zone_max_level(x) + ")";
	}
		
    private void initialize() {
	this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	this.setBounds(25, 25, 648, 453);
	this.setResizable(false);
	this.setModal(true);
	this.setTitle(Local.WORLD_MAP);

    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
    
	getRootPane().getActionMap().put("Cancel", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {setVisible(false);}});

	ivjJFrameContentPane = new javax.swing.JPanel();
	ivjJFrameContentPane.setLayout(null);

	this.setContentPane(ivjJFrameContentPane);
	
	this.addMouseMotionListener(new java.awt.event.MouseMotionListener() {
		public void mouseMoved(java.awt.event.MouseEvent e) {
			px = e.getX();
			py = e.getY();
		}

		public void mouseDragged(java.awt.event.MouseEvent e) {}
	    });

	this.addMouseListener(new java.awt.event.MouseListener() {
		public void mouseClicked(java.awt.event.MouseEvent e) {
		    int z=Joueur.universe.map.get_zone(e.getX(),e.getY());
		    if(z!=-1 && Joueur.max_zone_level() >= Joueur.universe.get_zone_level(z))
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
