package kayya.com.tagcloudview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import kayya.com.tagcloudview.listeners.TagCloudViewListener;
import kayya.com.tagcloudview.tagview.TagView;
import kayya.com.tagcloudview.tagview.TagViewAttributes;
import kayya.com.tagcloudview.tagview.TagViewParams;
import kayya.com.tagcloudview.tagview.TagViewType;
import kayya.com.tagcloudview.utils.Utils;

/**
 * Created by kayaMAC on 25.03.15.
 */
public class TagCloudView extends RelativeLayout implements View.OnClickListener {

    private final static String TAG = "TagCloudView";
    private ArrayList<String> tags;
    private int selectedTagId = -1;
    private TagViewAttributes tagViewAttributes;
    private TagViewParams tagViewParams;
    private TagCloudViewListener listener;
    // Dimens
    private float tagView_verticalPadding;
    private float tagView_horizontalPadding;
    private float tagView_horizontalMargin;

    // Default Dimens
    private final float def_tagView_verticalPadding = 0f;
    private final float def_tagView_horizontalPadding = 7f;
    private final float def_tagView_horizontalMargin = 8f;

    // Colors
    private int tagCloudView_bgColor;
    private int tagView_textColor;
    private int tagView_selectedColor;

    // Default Colors
    private final int def_tagCloudView_bgColor = 0xFF000000;
    private final int def_tagView_textColor = 0xFFFFFFFF;
    private final int def_tagView_selectedColor = 0xFFFF0000;


    // Font Sizes
    private float tagView_textSize;

    // Default Font Sizes
    private final float def_tagView_textSize = 10f;


    public TagCloudView(Context context) {
        super(context);
        loadDefaultValues();
        initView();

    }

    public TagCloudView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TagCloudView,
                0, 0
        );
        try {
            tagCloudView_bgColor = a.getColor(R.styleable.TagCloudView_tcTagCloudBgColor, def_tagCloudView_bgColor);
            tagView_textColor = a.getColor(R.styleable.TagCloudView_tcTagViewTextColor, def_tagView_textColor);
            tagView_selectedColor = a.getColor(R.styleable.TagCloudView_tcTagViewSelectedBgColor, def_tagView_selectedColor);
            tagView_horizontalMargin = a.getDimension(R.styleable.TagCloudView_tcTagViewHorizontalMargin, Utils.dpToPx(def_tagView_horizontalMargin));
            tagView_verticalPadding = a.getDimension(R.styleable.TagCloudView_tcTagViewVerticalPadding, Utils.dpToPx(def_tagView_verticalPadding));
            tagView_horizontalPadding = a.getDimension(R.styleable.TagCloudView_tcTagViewHorizontalPadding, Utils.dpToPx(def_tagView_horizontalPadding));
            tagView_textSize = a.getDimension(R.styleable.TagCloudView_tcTagViewTextSize, Utils.dpToPx(def_tagView_textSize));
        } finally {
            a.recycle();
        }
        initView();
    }


    private void loadDefaultValues() {
        tagCloudView_bgColor = def_tagCloudView_bgColor;
        tagView_textColor = def_tagView_textColor;
        tagView_selectedColor = def_tagView_selectedColor;
        tagView_horizontalMargin = def_tagView_horizontalMargin;
        tagView_verticalPadding = def_tagView_verticalPadding;
        tagView_horizontalPadding = def_tagView_horizontalPadding;
        tagView_textSize = def_tagView_textSize;
    }


    protected void initView() {
        tags = new ArrayList<>();
        tagViewAttributes = new TagViewAttributes(tagView_textColor, tagView_selectedColor, tagView_textSize);
        tagViewParams = new TagViewParams((int) tagView_horizontalMargin, (int) tagView_horizontalPadding, (int) tagView_verticalPadding);
        setBackgroundColor(tagCloudView_bgColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (oldw != w && w > 0) {
            removeAllViews();
            addTags();
        }
    }


    private void addTags() {
        int layoutWidth= getMeasuredWidth() - getPaddingLeft()
                - getPaddingRight();
        int currentWidth = 0;
        if (tags != null && tags.size() > 0 && layoutWidth != 0) {
            for (int i = 0; i < tags.size(); i++) {
                TagView tagView = null;
                int neighborId = 0;
                if (i == 0) {
                    tagViewParams.setType(TagViewType.First);
                    tagView = TagView.newInstance(getContext(), 1, tagViewParams,tagViewAttributes);
                    tagView.setText(tags.get(i));
                    currentWidth = correctWidth(tagView);
                } else {
                    neighborId = getChildAt(i - 1).getId();
                    tagViewParams.setType(TagViewType.Standart);
                    tagView = TagView.newInstance(getContext(), neighborId, tagViewParams,tagViewAttributes);
                    tagView.setText(tags.get(i));
                    currentWidth += correctWidth(tagView);
                    if (currentWidth > layoutWidth) {
                        tagViewParams.setType(TagViewType.LineStart);
                        tagView = TagView.newInstance(getContext(), neighborId, tagViewParams,tagViewAttributes);
                        tagView.setText(tags.get(i));
                        currentWidth = correctWidth(tagView);
                    }
                }
                tagView.setTag(i);
                tagView.setOnClickListener(this);
                addView(tagView);
            }
        }

    }

    public int correctWidth(TagView textView) {
        String s = textView.getText().toString();
        float currentWidth = textView.getPaint().measureText(s);

        return (int) currentWidth + 2 * (int) tagView_horizontalMargin +
                +textView.getCompoundPaddingLeft()
                + textView.getCompoundPaddingRight();
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
        addTags();
    }

    public void setListener(TagCloudViewListener listener) {
        this.listener = listener;
    }

    @Override
    public Parcelable onSaveInstanceState() {
        // begin boilerplate code that allows parent classes to save state
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        // end
        ss.selectedId = this.selectedTagId;

        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        // begin boilerplate code so parent classes can restore state
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;

        this.selectedTagId = ss.selectedId;
        ((TagView) getChildAt(selectedTagId)).selectTag();

        super.onRestoreInstanceState(ss.getSuperState());
    }

    @Override
    public void onClick(View v) {
        int newSelectedTag = (int) v.getTag();
        if (selectedTagId != -1)
            ((TagView) getChildAt(selectedTagId)).unselectTag();
        selectedTagId = newSelectedTag;
        ((TagView) getChildAt(selectedTagId)).selectTag();
        if(listener!=null)
            listener.onTagViewSelected( ((TagView) getChildAt(selectedTagId)).getText().toString(),selectedTagId);
    }

    static class SavedState extends BaseSavedState {
        int selectedId;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.selectedId = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.selectedId);
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
