package test_parser.insargamparsertest.Pages;

import android.app.Activity;
import android.graphics.Bitmap;

import org.jsoup.nodes.Document;

import test_parser.insargamparsertest.Net.HtmlDownloader;
import test_parser.insargamparsertest.Net.IBitmapBehavior;
import test_parser.insargamparsertest.Net.IJsoupBehavior;
import test_parser.insargamparsertest.Net.ImageDownloader;

public abstract class PageBaseActivity extends Activity implements IBitmapBehavior, IJsoupBehavior {
    public abstract String getUrl();

    @Override
    public abstract void onLoadedJsoup(HtmlDownloader loader, Document doc);

    @Override
    public abstract void onException(HtmlDownloader loader, Exception e);

    @Override
    public abstract void onLoadedImage(ImageDownloader loader, Bitmap loadedBitmap);

    @Override
    public abstract void onException(ImageDownloader loader, Exception e);
}
