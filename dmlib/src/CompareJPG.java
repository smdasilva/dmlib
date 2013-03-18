
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

public class CompareJPG {


		static boolean compareImageJPG(String f1,String f2) {
			//String file1 = "/net/cremi/rbenojem/espaces/travail/v2.jpg";
			//String file2 = "/net/cremi/rbenojem/espaces/travail/E990.jpg";

			Image image1 = Toolkit.getDefaultToolkit().getImage(f1);
			Image image2 = Toolkit.getDefaultToolkit().getImage(f2);
			boolean result= false;

			try {

				PixelGrabber grab1 = new PixelGrabber(image1, 0, 0, -1, -1,false);
				PixelGrabber grab2 = new PixelGrabber(image2, 0, 0, -1, -1,false);

				int[] data1 = null;
				if (grab1.grabPixels()) {
					int width = grab1.getWidth();
					int height = grab1.getHeight();
					data1 = new int[width * height];
					data1 = (int[]) grab1.getPixels();
				}

				int[] data2 = null;
				if (grab2.grabPixels()) {
					int width = grab2.getWidth();
					int height = grab2.getHeight();
					data2 = new int[width * height];
					data2 = (int[]) grab2.getPixels();
				}

				//System.out.println("Pixels equal: "+ java.util.Arrays.equals(data1, data2));
				result = java.util.Arrays.equals(data1, data2);

			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			return result;
		}

		public static void main(String args[]) {
			boolean res=compareImageJPG("/net/cremi/rbenojem/espaces/travail/v2.jpg","/net/cremi/rbenojem/espaces/travail/v2.jpg");
			System.out.println(res);
		}
		
	}




