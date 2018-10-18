package test_parser.insargamparsertest.Net;

import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlDownloader extends AsyncTask<Void, Void, Void> {

    private IJsoupBehavior mParent;
    private String mUrl;
    private Document mLoadedDoc = null;
    private Exception mException = null;

    public HtmlDownloader(IJsoupBehavior parent, String url) {
        super();
        mParent = parent;
        mUrl = url;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            mLoadedDoc = Jsoup.connect(mUrl).get();
        } catch (Exception e) {
            mException = e;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        if(mException != null)
            mParent.onException(this, mException);
        else
            mParent.onLoadedJsoup(this, mLoadedDoc);
    }
}
