public class CurveObject {
		public double arr_x[];
		public double arr_y[];
		public String title;

		public CurveObject(double[] a, double[] b, String s1)
		{
		arr_x = a;	arr_x = b; title = s1;
		}
		public CurveObject()
			{
			arr_x = new double[50];
			arr_y = new double[50];
			}
}