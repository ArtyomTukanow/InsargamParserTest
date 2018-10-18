package test_parser.insargamparsertest.Pages;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import test_parser.insargamparsertest.Net.HtmlDownloader;
import test_parser.insargamparsertest.Net.ImageDownloader;
import test_parser.insargamparsertest.R;

public class UserActivity extends PageBaseActivity {

    private String mUserName = "evgenbad/";
    private final String mUrl = "http://instagram.com/";
    private Document mUserDoc;
    private LinearLayout mUserLinearLayout;
    private TextView mFullName;
    private TextView mStatus;
    private ImageView mAvatar;
    private ImageDownloader mAvatarLoader;
    public static final String USER_NAME = "user_name";

    @Override
    public String getUrl() {
        return  mUrl + mUserName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String s = getIntent().getStringExtra(USER_NAME);
        if(s.length() > 0) mUserName = s;
        setContentView(R.layout.activity_user);
        new HtmlDownloader(this, getUrl()).execute();
        mUserLinearLayout = findViewById(R.id.bodyContayner);
        mFullName = findViewById(R.id.fullNameTextFiew);
        mStatus = findViewById(R.id.statusTextView);
        mAvatar = findViewById(R.id.avatarImageView);
    }

    @Override
    public void onLoadedJsoup(HtmlDownloader loader, Document doc) {
//        setTitle(doc.title());
        mUserDoc = doc;
        loadUserJson();
    }

    @Override
    public void onLoadedImage(ImageDownloader loader, Bitmap loadedBitmap) {
        if(loader == mAvatarLoader) {
            mAvatar.setImageBitmap(loadedBitmap);
        } else {
            ImageView imageView = new ImageView(findViewById(R.id.bodyContayner).getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setImageBitmap(loadedBitmap);
            mUserLinearLayout.addView(imageView, params);
        }
    }

    @Override
    public void onException(HtmlDownloader loader, Exception e) {
        showException(e.getMessage());
    }

    @Override
    public void onException(ImageDownloader loader, Exception e) {
        showException(e.getMessage());
    }

    private void showException(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    private void loadUserJson() {
        if(mUserDoc == null)
            return;

        Elements scripts = mUserDoc.getElementsByTag("script");
        for(Element script : scripts) {
            for (DataNode node : script.dataNodes()) {
                if(node.getWholeData().contains("window._sharedData = ")) {
                    String json = node.getWholeData().substring("window._sharedData = ".length());
                    parseJsonByString(json);
                }
            }
        }
    }

    private void parseJsonByString(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONObject user = jsonObject.getJSONObject("entry_data").getJSONArray("ProfilePage").getJSONObject(0).getJSONObject("graphql").getJSONObject("user");
            mAvatarLoader = new ImageDownloader(this, user.get("profile_pic_url_hd").toString());
            mAvatarLoader.execute();
            mFullName.setText(user.get("full_name").toString());
            mStatus.setText(user.get("biography").toString());

            JSONArray photos = user.getJSONObject("edge_owner_to_timeline_media").getJSONArray("edges");
            for(int i = 0; i < photos.length(); i ++) {
                String photo = photos.getJSONObject(i).getJSONObject("node").getString("display_url");
                new ImageDownloader(this, photo).execute();
            }
        } catch (JSONException e) {
            showException(e.getMessage());
        }
    }
}
