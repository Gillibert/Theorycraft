import java.awt.Rectangle;
import java.util.ArrayList;

public class World {
	public String map_img;
	public Rectangle[] zonesR;
	public int[] zonesN;
	public Zone[] zones;
	private int zonesI;
	public World()
		{
		map_img="images/main_map2.png";
		zonesI = 19;

		zonesR = new Rectangle[zonesI];
		zones = new Zone[zonesI];

		zonesR[0] = new Rectangle(135, 127, 50, 65);
		zones[0]= new Zone("Baie du froid");

		zonesR[1] = new Rectangle(127, 260, 41, 42);
		zones[1]= new Zone("Île du croissant");

		zonesR[2] = new Rectangle(2, 128, 53, 59);
		zones[2]= new Zone("Îles infernales");

		zonesR[3] = new Rectangle(272, 193, 39, 31);
		zones[3]= new Zone("Lac enchanté");	

		zonesR[4] = new Rectangle(319, 22, 44, 26);
		zones[4]= new Zone("Forteresse de Trok");

		zonesR[5] = new Rectangle(315, 263, 47, 53);
		zones[5]= new Zone("Citée-arbre");

		zonesR[6] = new Rectangle(192, 349, 83, 60);
		zones[6]= new Zone("Baie du sang");

		zonesR[7] = new Rectangle(434, 181, 84, 52);
		zones[7]= new Zone("Massif d'Ur");
		
		zonesR[8] = new Rectangle(440, 63, 120, 63);
		zones[8]= new Zone("Massif de Montrude");
		
		zonesR[9] = new Rectangle(416, 269, 62, 57);
		zones[9]= new Zone("Forêt sombre");

		zonesR[10] = new Rectangle(112, 343, 48, 45);
		zones[10]= new Zone("Cap de glace");

		zonesR[11] = new Rectangle(127, 70, 57, 52);
		zones[11]= new Zone("Cavernes mortes");

		zonesR[12] = new Rectangle(418, 336, 55, 50);
		zones[12]= new Zone("Rade de la paix");

		zonesR[13] = new Rectangle(60, 350, 31, 36);
		zones[13]= new Zone("Île du temps");

		zonesR[14] = new Rectangle(108, 2, 124, 67);
		zones[14]= new Zone("Massif des titans");

		zonesR[15] = new Rectangle(480, 271, 68, 68);
		zones[15]= new Zone("Bois de Nokal");

		zonesR[16] = new Rectangle(509, 341, 62, 39);
		zones[16]= new Zone("Bouche de Nokal");

		zonesR[17] = new Rectangle(219, 165, 49, 46);
		zones[17]= new Zone("Croisée des confluents");

		zonesR[18] = new Rectangle(260, 98, 53, 30);
		zones[18]= new Zone("Failles de Mokiss");

		for(int i=0; i<zones.length; i++)
			zones[i].level=i;
		}

	public int get_zone(int x, int y) {
		Rectangle pr = new Rectangle(x-4, y-24, 3, 3);
		for(int i=0; i<zonesI;i++)
				if (pr.intersects(zonesR[i])) return i;
		return -1;
	}
}
