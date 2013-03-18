
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

public class CompareJPG {


		static int[] compareImageJPG(String f1) {
			

			Image image1 = Toolkit.getDefaultToolkit().getImage(f1);
			
			int[] result = null;

			try {

				PixelGrabber grab1 = new PixelGrabber(image1, 0, 0, -1, -1,false);
				
				int[] data = null;
				if (grab1.grabPixels()) {
					int width = grab1.getWidth();
					int height = grab1.getHeight();
					data = new int[width * height];
					data = (int[]) grab1.getPixels();
				}
				
				result = data;

			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			return result;
		}

		public static void main(String args[]) {
			int[] res=compareImageJPG("/net/cremi/rbenojem/espaces/travail/ex.jpg");
			System.out.println(res);
		}
		
	}




