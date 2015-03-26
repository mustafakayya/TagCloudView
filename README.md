TagCloudView
==============
This library for made TagCloud for Android.

<img src="https://raw.githubusercontent.com/mustafakayya/TagCloudView/master/app/src/main/res/drawable/sample.png" width="400px" height="235px" />

<img src="https://raw.githubusercontent.com/mustafakayya/TagCloudView/master/app/src/main/res/drawable/sample2.png" width="400px" height="235px" />

Install
--------------

Before you use the project please prepare all required settings in a configuration file.
There is an sample in app folder.

<code> compile project(':library')</code>


Usage
--------------


```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tcv="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <kayya.com.tagcloudview.TagCloudView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/tagCloudView"
        tcv:tcTagViewVerticalPadding="5dp"
        ></kayya.com.tagcloudview.TagCloudView>
</ScrollView>
```

```java
public class TestActivity  extends ActionBarActivity implements TagCloudViewListener{

    TagCloudView view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        String lipsum="There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some";
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
```



Licence
--------------
Copyright 2015 Mustafa KAYA

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

Author
--------------
[Mustafa KAYA](https://github.com/mustafakayya)
