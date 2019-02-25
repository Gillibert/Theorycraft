import javax.swing.table.AbstractTableModel;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UniversWindow extends javax.swing.JDialog  {
    private Player ThePlayer;
	private World TheWorld;
    private javax.swing.JPanel ivjJFrameContentPane = null;

    private JTextArea infos;
    private JScrollPane scroll;

    public UniversWindow(Player play, World wrd) {
	super();
	ThePlayer = play;
	TheWorld = wrd;
	initialize();
    }

	public void montre()
	{
	refresh();
	this.setVisible(true);
	}

    public void refresh()
    {
	infos.setText(ThePlayer.universe.infos(TheWorld));
    }
	
    private javax.swing.JPanel getJFrameContentPane() {
	if (ivjJFrameContentPane == null) {
		
	    infos = new JTextArea();
	    infos.setEditable(false);
	    DefaultCaret caret = (DefaultCaret)infos.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

	    scroll = new JScrollPane(infos);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setBounds(new Rectangle(5, 5, 655, 386));
	    infos.setFont(new Font("Times Roman", Font.PLAIN, 11));

	    ivjJFrameContentPane = new javax.swing.JPanel();
	    ivjJFrameContentPane.setLayout(null);

	    ivjJFrameContentPane.add(scroll);
	}
	return ivjJFrameContentPane;
    }
	
    private void initialize() {

	this.setLocation(new Point(15, 15));
	this.setSize(new Dimension(670, 420));
	this.setResizable(false);
	this.setModal(true);
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setTitle("Informations sur l'univers");
	this.setContentPane(getJFrameContentPane());

	getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");

	getRootPane().getActionMap().put("Cancel", new AbstractAction(){
	    public void actionPerformed(ActionEvent e) {setVisible(false);}});

	refresh();
    }


} 
