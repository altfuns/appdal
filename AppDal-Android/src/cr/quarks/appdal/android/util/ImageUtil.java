package cr.quarks.appdal.android.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class ImageUtil {

	public static Bitmap getImage(String url) {
		Bitmap result = null;

		try {
			result = ImageUtil.getBitmapFromURL(url);
		} catch (IOException e) {
			LogIt.e(ImageUtil.class, e, e.getMessage());
		}

		return result;
	}
	
	public static Bitmap getBitmapFromURL(String address) throws IOException {
        Bitmap result = null;

        java.net.URL url = new URL(address);
        Object content = url.getContent();
        java.io.InputStream inputStream = (InputStream) content;
        result = BitmapFactory.decodeStream(inputStream);

        return result;
    }
	
	
}
