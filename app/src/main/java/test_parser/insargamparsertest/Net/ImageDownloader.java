package test_parser.insargamparsertest.Net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.net.URL;

public class ImageDownloader extends AsyncTask<Void, Void, Void> {

    private String mUrl;
    private Exception mException;
    private IBitmapBehavior mParent;
    private Bitmap mBitmap;

    public ImageDownloader(IBitmapBehavior parent, String url) {
        mUrl = url;
        mParent = parent;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url = new URL(mUrl);
            mBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (Exception e) {
            mException = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        if(mException != null) {
            mParent.onException(this, mException);
        } else {
            mParent.onLoadedImage(this, mBitmap);
        }
    }
}
