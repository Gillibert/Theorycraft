import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Map implements Serializable {
	public String map_img;
	public ArrayList<Rectangle> zonesR;
	public ArrayList<String> zonesName;
	public ArrayList<Double> boss_coeff;
	public ArrayList<Double> trap_coeff;
	public ArrayList<Double> precipitation;
	public ArrayList<Double> temperature;
	public ArrayList<Double> current_temperature_modifier;
	public ArrayList<Double> current_precipitation_modifier;	
	
	static int MASSIF = 0;
	static int LAKE = 1;
	static int FOREST = 2;
	
	public void AddZoneInfos(TheoryGenerator gen,NameGenerator nameGen, int type, ArrayList<Double> tempTemperature, ArrayList<Double> tempprecipitation, ArrayList<String> tempZN)
	{
		if(type==MASSIF)
		{
			if(gen.nextDouble() < 0.5)
			{
			if(gen.nextDouble() < 0.5)
				tempZN.add(nameGen.getNameOf(Local.MASSIF));
			else
				tempZN.add(nameGen.getNameOf(Local.MOUNTAINS));
			tempTemperature.add(-0.4);
			tempprecipitation.add(0.6);
			}
			else
			{
				tempZN.add(nameGen.getNameOf(Local.CAVES));
				tempTemperature.add(2.0);
				tempprecipitation.add(0.0);
			}
		}
		if(type==LAKE)
		{
			tempZN.add(nameGen.getNameOf(Local.LAKE));
			tempTemperature.add(0.9);
			tempprecipitation.add(0.5);
		}
		if(type==FOREST)
		{
			if(gen.nextDouble() < 0.5)
				tempZN.add(nameGen.getNameOf(Local.FOREST));
			else
				tempZN.add(nameGen.getNameOf(Local.WOOD));
			tempTemperature.add(1.1);
			tempprecipitation.add(0.2);
		}
	}
	
	
	public Map(TheoryGenerator gen)
		{
		map_img="images/main_map2.png";

		zonesR = new ArrayList<Rectangle>();
		zonesName = new ArrayList<String>();
		boss_coeff = new ArrayList<Double>();
		trap_coeff = new ArrayList<Double>();
		precipitation = new ArrayList<Double>();
		temperature = new ArrayList<Double>();
		current_precipitation_modifier = new ArrayList<Double>();
		current_temperature_modifier = new ArrayList<Double>();
		
		ArrayList<Rectangle> tempZR = new ArrayList<Rectangle>();
		ArrayList<String> tempZN = new ArrayList<String>();
		ArrayList<Double> tempTemperature = new ArrayList<Double>();
		ArrayList<Double> tempprecipitation = new ArrayList<Double>();
		
		NameGenerator nameGen = new NameGenerator(gen.nextInt(Integer.MAX_VALUE));
		
		tempZR.add(new Rectangle(135, 132, 50, 56));
		tempZN.add(nameGen.getNameOf(Local.BAY));
		tempTemperature.add(0.8);
		tempprecipitation.add(0.6);
		
		tempZR.add(new Rectangle(127, 260, 41, 42));
		tempZN.add(nameGen.getNameOf(Local.ISLAND));
		tempTemperature.add(0.8);
		tempprecipitation.add(0.6);
		
		tempZR.add(new Rectangle(2, 128, 53, 59));
		tempZN.add(nameGen.getNameOf(Local.ISLANDS));
		tempTemperature.add(0.8);
		tempprecipitation.add(0.6);
		
		tempZR.add(new Rectangle(272, 193, 39, 31));
		AddZoneInfos(gen,nameGen,LAKE,tempTemperature,tempprecipitation,tempZN);
		
		tempZR.add(new Rectangle(319, 22, 44, 26));
		tempZN.add(nameGen.getNameOf(Local.FORTRESS));
		tempTemperature.add(0.9);
		tempprecipitation.add(0.0);
		
		tempZR.add(new Rectangle(315, 263, 47, 53));
		tempZN.add(Local.TREE_CITY);
		tempTemperature.add(1.1);
		tempprecipitation.add(0.3);
		
		tempZR.add(new Rectangle(192, 349, 83, 60));
		tempZN.add(nameGen.getNameOf(Local.BAY));
		tempTemperature.add(0.2);
		tempprecipitation.add(0.5);
		
		tempZR.add(new Rectangle(434, 181, 84, 52));
		AddZoneInfos(gen,nameGen,MASSIF,tempTemperature,tempprecipitation,tempZN);
		
		tempZR.add(new Rectangle(439, 78, 94, 51));
		AddZoneInfos(gen,nameGen,MASSIF,tempTemperature,tempprecipitation,tempZN);
		
		tempZR.add(new Rectangle(416, 269, 62, 57));
		AddZoneInfos(gen,nameGen,FOREST,tempTemperature,tempprecipitation,tempZN);

		
		tempZR.add(new Rectangle(112, 343, 48, 45));
		tempZN.add(nameGen.getNameOf(Local.CAPE));
		tempTemperature.add(0.2);
		tempprecipitation.add(0.7);
		
		tempZR.add(new Rectangle(127, 73, 57, 52));
		AddZoneInfos(gen,nameGen,MASSIF,tempTemperature,tempprecipitation,tempZN);
		
		tempZR.add(new Rectangle(189, 73, 60, 54));
		AddZoneInfos(gen,nameGen,MASSIF,tempTemperature,tempprecipitation,tempZN);

		tempZR.add(new Rectangle(255, 60, 57, 37));
		AddZoneInfos(gen,nameGen,MASSIF,tempTemperature,tempprecipitation,tempZN);

		tempZR.add(new Rectangle(418, 336, 55, 50));
		tempZN.add(nameGen.getNameOf(Local.ROADSTEAD));
		tempTemperature.add(0.9);
		tempprecipitation.add(0.2);
		
		tempZR.add(new Rectangle(60, 350, 31, 36));
		tempZN.add(nameGen.getNameOf(Local.ISLAND));
		tempTemperature.add(1.2);
		tempprecipitation.add(0.5);
		
		tempZR.add(new Rectangle(108, 2, 124, 67));
		AddZoneInfos(gen,nameGen,MASSIF,tempTemperature,tempprecipitation,tempZN);
		
		tempZR.add(new Rectangle(480, 271, 68, 68));
		AddZoneInfos(gen,nameGen,FOREST,tempTemperature,tempprecipitation,tempZN);

		tempZR.add(new Rectangle(509, 341, 62, 39));
		tempZN.add(nameGen.getNameOf(Local.MOUTH));
		tempTemperature.add(0.8);
		tempprecipitation.add(0.7);
		
		tempZR.add(new Rectangle(219, 165, 49, 46));
		tempZN.add(nameGen.getNameOf(Local.JUNCTION));
		tempTemperature.add(0.9);
		tempprecipitation.add(0.3);
		
		tempZR.add(new Rectangle(356, 213, 54, 43));
		tempZN.add(nameGen.getNameOf(Local.JUNCTION));
		tempTemperature.add(0.9);
		tempprecipitation.add(0.3);
				
		tempZR.add(new Rectangle(260, 98, 53, 30));
		AddZoneInfos(gen,nameGen,MASSIF,tempTemperature,tempprecipitation,tempZN);

		tempZR.add(new Rectangle(314, 155, 47, 25));
		tempZN.add(nameGen.getNameOf(Local.RAVINE));
		tempTemperature.add(1.8);
		tempprecipitation.add(0.2);
		
		tempZR.add(new Rectangle(118, 390, 50, 29));
		tempZN.add(nameGen.getNameOf(Local.CAPE));
		tempTemperature.add(0.9);
		tempprecipitation.add(0.7);
		
		tempZR.add(new Rectangle(355, 362, 56, 37));
		tempZN.add(nameGen.getNameOf(Local.SWAMP));
		tempTemperature.add(1.8);
		tempprecipitation.add(0.9);
		
		tempZR.add(new Rectangle(452, 138, 75, 27));
		tempZN.add(nameGen.getNameOf(Local.JUNCTION));
		tempTemperature.add(1.1);
		tempprecipitation.add(0.3);
		
		tempZR.add(new Rectangle(13, 343, 42, 56));
		tempZN.add(nameGen.getNameOf(Local.CAPE));
		tempTemperature.add(0.9);
		tempprecipitation.add(0.7);
		
		tempZR.add(new Rectangle(133, 192, 35, 35));
		tempZN.add(nameGen.getNameOf(Local.LAGOON));
		tempTemperature.add(0.9);
		tempprecipitation.add(0.6);
		
		tempZR.add(new Rectangle(73, 82, 42, 47));
		tempZN.add(nameGen.getNameOf(Local.SWAMP));
		tempTemperature.add(1.8);
		tempprecipitation.add(0.9);
		
		tempZR.add(new Rectangle(574, 145, 28, 36));
		tempZN.add(nameGen.getNameOf(Local.BRIDGE));
		tempTemperature.add(1.6);
		tempprecipitation.add(0.3);
		
		tempZR.add(new Rectangle(589, 240, 29, 32));
		tempZN.add(nameGen.getNameOf(Local.BRIDGE));
		tempTemperature.add(1.6);
		tempprecipitation.add(0.3);
		
		tempZR.add(new Rectangle(547, 111, 42, 22));
		AddZoneInfos(gen,nameGen,FOREST,tempTemperature,tempprecipitation,tempZN);
		
		tempZR.add(new Rectangle(503, 21, 67, 51));
		AddZoneInfos(gen,nameGen,FOREST,tempTemperature,tempprecipitation,tempZN);
		
		ArrayList<Integer> numbers = new ArrayList<Integer>(tempZR.size());
		for(int i=0; i<tempZR.size(); i++)
		{
			numbers.add(i);
		}
		Collections.shuffle(numbers,gen.gen) ;
		
		String twn = nameGen.getNameOf(Local.TOWER);
		for(int i=0; i<7; i++)
		{
			tempZR.add(new Rectangle(577, 89-13*i, 55, 12));
			tempZN.add(twn + String.format(Local.FLOOR,i));
			tempTemperature.add(1.1);
			tempprecipitation.add(0.0);
		}
		
		for(int i=tempZR.size()-7; i<tempZR.size(); i++)
		{
			numbers.add(i);
		}
		
		int nbz = tempZR.size(); 
		
		zonesR.add(new Rectangle(345, 69, 55, 37));
		zonesName.add(nameGen.getNameOf(Local.ARENA));
		boss_coeff.add(0.01);
		trap_coeff.add(0.01);
		temperature.add(18.0);
		precipitation.add(0.0);
		current_temperature_modifier.add(0.0);
		current_precipitation_modifier.add(0.0);
		
		zonesR.add(new Rectangle(403, 26, 66, 37));
		zonesName.add(nameGen.getNameOf(Local.ARENA));
		boss_coeff.add(0.002);
		trap_coeff.add(0.01);
		temperature.add(18.0);
		precipitation.add(0.0);
		current_temperature_modifier.add(0.0);
		current_precipitation_modifier.add(0.0);
		
		for(int i=0; i < nbz; i++)
		{
			int index = numbers.get(i);
			zonesR.add(tempZR.get(index));
			zonesName.add(tempZN.get(index));
			boss_coeff.add(0.70+0.40*gen.nextDouble()+(0.2*i)/nbz);
			trap_coeff.add(0.70+0.40*gen.nextDouble()+(0.2*i)/nbz);

			double rt = 20.0*(1.0+(tempTemperature.get(index)-1.0)*(gen.nextDouble()*1.6+(0.4*i)/nbz));
			double rp = Math.min(Math.max(1.0+(tempprecipitation.get(index)-1.0)*(gen.nextDouble()*1.6+(0.4*i)/nbz),0.0),1.0);
			if(Math.abs(tempprecipitation.get(index)) < 0.001) rp = 0.0;
			temperature.add(rt);
			precipitation.add(rp);
			
			current_temperature_modifier.add((0.4*i)/nbz+0.2*gen.nextDouble());
			current_precipitation_modifier.add((0.4*i)/nbz+0.2*gen.nextDouble());
		}
		
		/*System.out.println("Final nbz="+zonesR.size());
		for(int i=0; i < zonesR.size(); i++)
		{
			System.out.println(zonesName.get(i));
		}*/
		}
	
	public static double HolidayPrecipitationMultiplier(Player p)
	{
		double res=1.0;
		if (Game.HOLIDAY == 4 || Game.HOLIDAY == 3) {res = 2*p.bonus_vacances();}
		if (Game.HOLIDAY == 2 || Game.HOLIDAY == 5) {res = 1.0/(2*p.bonus_vacances());}
		return res;
	}
	
	public static double HolidayTemperatureMultiplier(Player p)
	{
		double res=1.0;
		if (Game.HOLIDAY == 4) {res = 1.0/(2*p.bonus_vacances());}
		if (Game.HOLIDAY == 2 || Game.HOLIDAY == 5) {res = 2*p.bonus_vacances();}
		return res;
	}
	
	public int get_zone(int x, int y, int nombre_zones) {
		Rectangle pr = new Rectangle(x-4, y-24, 3, 3);
		int count = 0;
		Rectangle the_object;
		for(int i=0; i< nombre_zones; i++)
		{
			the_object = zonesR.get(i);
			if (pr.intersects(the_object)) return count;
			count++;
		}
		return -1;
	}
}
