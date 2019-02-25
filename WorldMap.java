import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class WorldMap extends javax.swing.JDialog {
    private int previous_zone;
    private javax.swing.JPanel ivjJFrameContentPane = null;
    private Player Joueur;
	private int px=0;
	private int py=0;
	private DrawMap drawMap;
	
	public WorldMap(Player J) {
	super();
	previous_zone = -2;
	Joueur=J;
	drawMap = new DrawMap(J);
	initialize();
    }
	
	public void SetPlayer(Player J)
	{
		Joueur = J;
		refresh();
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
				drawMap.setZone(z);
				drawMap.repaint();
			    previous_zone = z;
			}
      }
    });
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
	
	drawMap.setBounds(new Rectangle(0, 0, 648, 453));

			
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
				Joueur.voyage(z);
			    setVisible(false);
			}
		}

		public void mouseExited(java.awt.event.MouseEvent e) {}
		public void mousePressed(java.awt.event.MouseEvent e) {}
		public void mouseEntered(java.awt.event.MouseEvent e) {}
		public void mouseReleased(java.awt.event.MouseEvent e) {}
	    });

	ivjJFrameContentPane.add(drawMap);
    }


} 
