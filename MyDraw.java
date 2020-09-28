import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class MyDraw extends JPanel {

		private ArrayList<CurveObject> the_curves;

		public MyDraw()
		{
		super();
		the_curves = null;
		}

		public void draw_graph(CurveObject cu)
		{
		the_curves = new ArrayList<CurveObject>();
		the_curves.add(cu);
		}

		public void draw_graph(ArrayList<CurveObject> cu)
		{
		the_curves = cu;
		}

		static public double step(double xmax)
		{
			double log = Math.log10(xmax/5);
			double stepx = Math.pow(10,Math.floor(log));
			if(xmax/stepx >= 15)
				{
			 	if(xmax/stepx < 30)
					stepx = 2*stepx;
				else
					stepx = 5*stepx;
				}
			return stepx;
		}
		
		public void paint(Graphics g) 
		{
		setDoubleBuffered(true);
		if (the_curves == null) return;
		g.clearRect(0, 0, getWidth(), getHeight());
		
		double bx = 32;
		double by = 20;
		double mx = getWidth();
		double my = getHeight();

		// Calcul de xmax et ymax (et donc x_cor et y_cor)
		double xmax = 0;
		double ymax = 0;

		for(CurveObject the_curve : the_curves)
		{
		for(int i=0; i<the_curve.arr_x.length; i++)
			{
			if(the_curve.arr_x[i]>xmax) xmax = the_curve.arr_x[i];
			if(the_curve.arr_y[i]>ymax) ymax = the_curve.arr_y[i];
			}
		}

		double x_cor = xmax/(mx-bx);
		double y_cor = 1.001*ymax/(my-by);

 	  	Graphics2D graph = (Graphics2D) g;
   //	graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);


		Color clist[] = {Color.red, Color.magenta, Color.darkGray, new Color(0xF08B00), new Color(0x034EA3), new Color(0x008B00), new Color(0x6225CC), Color.yellow};
		// Dessin des courbes
		double lx,ly, prc_x, prc_y;
		int cc=0;
		for(CurveObject the_curve : the_curves)
		{
	  	graph.setPaint(clist[cc]); cc++;
		prc_x= the_curve.arr_x[0] + bx ;
		prc_y= my - (the_curve.arr_y[0]+by);
		for(int i=0; i<the_curve.arr_x.length; i++)
			{
			lx = (the_curve.arr_x[i]/x_cor) + bx ;
			ly = my - (the_curve.arr_y[i]/y_cor) - by;
			graph.draw(new Line2D.Double(prc_x, prc_y, lx, ly));
			prc_x = lx; prc_y = ly;
			}
		}

		// Dessin des titres
		int pstart = (int)(my-17)-(the_curves.size()*9);
		cc=0;
		
		int max_string_size = 0;
		for(CurveObject the_curve : the_curves)
			if(the_curve.title.length() > max_string_size) 
				max_string_size = the_curve.title.length();
		
		int shift = 20+5*max_string_size;
		for(CurveObject the_curve : the_curves)
		{
    	graph.setFont(new Font("Helvetica", Font.PLAIN, 11));
		graph.setPaint(clist[cc]); cc++;
    	graph.drawString(the_curve.title, (int)(mx-shift), pstart); pstart+=9;
		}

		// Dessin des axes
	  	graph.setPaint(Color.black);
		graph.draw(new Line2D.Double(bx, 0, bx, my-5));
		graph.draw(new Line2D.Double(5, my-by, mx, my-by));

		// Dessin des graduations
	  	graph.setPaint(Color.blue);
    	graph.setFont(new Font("Helvetica", Font.PLAIN, 10));

		String ster;
		double stepx = step(xmax);
		double stepy = step(ymax);		 
		
		for(double tx=stepx; tx <= xmax; tx+=stepx)
		{
			lx = (tx /x_cor) + bx ;
			graph.draw(new Line2D.Double(lx-0.5, my-by-2.0, lx-0.5, my-by+12.0));
			
			if(tx<10.0) ster = String.format("%.2f",tx);
			else if(tx<100.0) ster = String.format("%.1f",tx);
			else ster = String.format("%.0f",tx);
			
			if(tx<10000.0) graph.drawString(ster, (int)lx-26, (int)(my-6));
			else graph.drawString(ster, (int)lx-32, (int)(my-6));
		}
		
		for(double ty=stepy; ty < ymax; ty+=stepy)
			{
			ly = my - (ty /y_cor) - by;

			graph.draw(new Line2D.Double(bx-12.0, ly, bx+2.0, ly));

			if(ty<10.0) ster = String.format("%.2f",ty);
			else if(ty<100.0) ster = String.format("%.1f",ty);
			else if(ty<10000.0) ster = String.format("%.0f",ty);
			else ster = String.format("%2.2e",ty);
			
			graph.drawString(ster, 6, (int)ly+9);
			}
			
		graph.drawString("0.00", 4, (int)(my-6));

	  	}
		
		
}
