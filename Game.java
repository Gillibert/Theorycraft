public class Game {

    public static MainWindow MW;
    public static boolean DEBUG = true;
    public static boolean FAST = true;
	public static HiScore HI;

    public static void main(String args[])
    {					
	HI = HiScore.loadScore();
	StaticItem.init();
	Monster.initZoo();
	ChallengeList.init();
	MenuWindow MENU = new MenuWindow();
	MENU.setVisible(true);
    }
}
