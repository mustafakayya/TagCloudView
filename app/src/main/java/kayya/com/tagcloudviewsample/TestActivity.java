package kayya.com.tagcloudviewsample;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import kayya.com.tagcloudview.listeners.TagCloudViewListener;
import kayya.com.tagcloudviewsample.R;
import kayya.com.tagcloudview.TagCloudView;

/**
 * Created by kayaMAC on 25.03.15.
 */
public class TestActivity  extends ActionBarActivity implements TagCloudViewListener{

    TagCloudView view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        String lipsum="There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true";
        String[] sarray=lipsum.split("\\s+");
        ArrayList<String> names=new ArrayList<String>(Arrays.asList(sarray));


        view=(TagCloudView)findViewById(R.id.tagCloudView);
        view.setTags(names);
        view.setListener(this);

    }

    @Override
    public void onTagViewSelected(String tagText, int tagIndex) {
        Toast.makeText(this,tagText,Toast.LENGTH_SHORT).show();
    }
}
