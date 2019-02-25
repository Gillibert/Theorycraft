import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Map implements Serializable {
	public String map_img;
	public ArrayList<Rectangle> zonesR;
	public ArrayList<String> zonesName;
	public Map(Random gen, int zl_14)
		{
		map_img="images/main_map2.png";

		zonesR = new ArrayList<Rectangle>();
		zonesName = new ArrayList<String>();
		
		ArrayList<Rectangle> tempZR = new ArrayList<Rectangle>();
		ArrayList<String> tempZN = new ArrayList<String>();
		
		NameGenerator nameGen = new NameGenerator(gen.nextInt());
		
		tempZR.add(new Rectangle(135, 132, 50, 56));
		tempZN.add(nameGen.getNameOf(Local.BAY));

		tempZR.add(new Rectangle(127, 260, 41, 42));
		tempZN.add(nameGen.getNameOf(Local.ISLAND));

		tempZR.add(new Rectangle(2, 128, 53, 59));
		tempZN.add(nameGen.getNameOf(Local.ISLANDS));

		tempZR.add(new Rectangle(272, 193, 39, 31));
		tempZN.add(nameGen.getNameOf(Local.LAKE));

		tempZR.add(new Rectangle(319, 22, 44, 26));
		tempZN.add(nameGen.getNameOf(Local.FORTRESS));

		tempZR.add(new Rectangle(315, 263, 47, 53));
		tempZN.add(Local.TREE_CITY);

		tempZR.add(new Rectangle(192, 349, 83, 60));
		tempZN.add(nameGen.getNameOf(Local.BAY));

		tempZR.add(new Rectangle(434, 181, 84, 52));
		tempZN.add(nameGen.getNameOf(Local.MASSIF));
		
		tempZR.add(new Rectangle(439, 78, 94, 51));
		tempZN.add(nameGen.getNameOf(Local.MASSIF));
		
		tempZR.add(new Rectangle(416, 269, 62, 57));
		tempZN.add(nameGen.getNameOf(Local.FOREST));

		tempZR.add(new Rectangle(112, 343, 48, 45));
		tempZN.add(nameGen.getNameOf(Local.CAPE));

		tempZR.add(new Rectangle(127, 73, 57, 52));
		tempZN.add(nameGen.getNameOf(Local.CAVES));

		tempZR.add(new Rectangle(189, 73, 60, 54));
		tempZN.add(nameGen.getNameOf(Local.MOUNTAINS));
		
		tempZR.add(new Rectangle(418, 336, 55, 50));
		tempZN.add(nameGen.getNameOf(Local.ROADSTEAD));

		tempZR.add(new Rectangle(60, 350, 31, 36));
		tempZN.add(nameGen.getNameOf(Local.ISLAND));

		tempZR.add(new Rectangle(108, 2, 124, 67));
		tempZN.add(nameGen.getNameOf(Local.MASSIF));

		tempZR.add(new Rectangle(480, 271, 68, 68));
		tempZN.add(nameGen.getNameOf(Local.WOOD));

		tempZR.add(new Rectangle(509, 341, 62, 39));
		tempZN.add(nameGen.getNameOf(Local.MOUTH));

		tempZR.add(new Rectangle(219, 165, 49, 46));
		tempZN.add(nameGen.getNameOf(Local.JUNCTION));

		tempZR.add(new Rectangle(260, 98, 53, 30));
		tempZN.add(nameGen.getNameOf(Local.RIFT));
		
		tempZR.add(new Rectangle(314, 155, 47, 25));
		tempZN.add(nameGen.getNameOf(Local.RAVINE));

		tempZR.add(new Rectangle(118, 390, 50, 29));
		tempZN.add(nameGen.getNameOf(Local.CAPE));

		tempZR.add(new Rectangle(355, 362, 56, 37));
		tempZN.add(nameGen.getNameOf(Local.SWAMP));
		
		tempZR.add(new Rectangle(452, 138, 75, 27));
		tempZN.add(nameGen.getNameOf(Local.JUNCTION));
		
		tempZR.add(new Rectangle(13, 343, 42, 56));
		tempZN.add(nameGen.getNameOf(Local.CAPE));
		
		tempZR.add(new Rectangle(133, 192, 35, 35));
		tempZN.add(nameGen.getNameOf(Local.LAGOON));
		
		tempZR.add(new Rectangle(73, 82, 42, 47));
		tempZN.add(nameGen.getNameOf(Local.SWAMP));
		
		tempZR.add(new Rectangle(574, 145, 28, 36));
		tempZN.add(nameGen.getNameOf(Local.BRIDGE));
		
		tempZR.add(new Rectangle(589, 240, 29, 32));
		tempZN.add(nameGen.getNameOf(Local.BRIDGE));
		
		tempZR.add(new Rectangle(547, 111, 42, 22));
		tempZN.add(nameGen.getNameOf(Local.WOOD));
		
		tempZR.add(new Rectangle(503, 21, 67, 51));
		tempZN.add(nameGen.getNameOf(Local.WOOD));
		
		ArrayList<Integer> numbers = new ArrayList<Integer>(tempZR.size());
		for(int i=0; i<tempZR.size(); i++)
		{
			numbers.add(i);
		}
		Collections.shuffle(numbers,gen) ;
		
		int MIN_NBZ;
		if(zl_14 > 400) {MIN_NBZ = 12;}
		else {MIN_NBZ = 20;}
		
		int nbz = MIN_NBZ+gen.nextInt(tempZR.size()-MIN_NBZ+1);
		for(int i=0; i < nbz; i++)
		{
			int index = numbers.get(i);
			zonesR.add(tempZR.get(index));
			zonesName.add(tempZN.get(index));
		}
		}

	public int get_zone(int x, int y) {
		Rectangle pr = new Rectangle(x-4, y-24, 3, 3);
		int count = 0;
		for(Rectangle the_object : zonesR)
		{
		if (pr.intersects(the_object)) return count;
		count++;
		}
		return -1;
	}
}
