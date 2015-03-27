package kayya.com.tagcloudview.tagview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kayya.com.tagcloudview.R;
import kayya.com.tagcloudview.utils.Utils;

/**
 * Created by kayaMAC on 25.03.15.
 */
public class TagView extends TextView {

    // Attributes
    private int selectedBgColor;
    private int textColor;
    private float textSize;
    private boolean isSelected;

    public TagView(Context context) {
        super(context);

    }

    public TagView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public static TagView newInstance(Context context, int neighborId, TagViewParams params, TagViewAttributes attributes) {
        TagView tagView = newInstance(context, neighborId, params);
        tagView.setAttributes(attributes);
        return tagView;
    }

    public static TagView newInstance(Context context, int neighborId, TagViewParams params) {
        TagView tagView = new TagView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        switch (params.getType()) {
            case First:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                break;
            case LineStart:
                layoutParams.addRule(
                        RelativeLayout.ALIGN_PARENT_LEFT,
                        RelativeLayout.TRUE);
                layoutParams.addRule(RelativeLayout.BELOW,
                        neighborId);
                break;
            case Standart:
                layoutParams.addRule(RelativeLayout.ALIGN_TOP,
                        neighborId);
                layoutParams.addRule(RelativeLayout.RIGHT_OF,
                        neighborId);
                break;
        }
        tagView.setId(Utils.generateViewId());
        layoutParams.setMargins(params.getHorizontalMargin(), 0,
                params.getHorizontalMargin(), 0);
        tagView.setPadding(params.getHorizontalPadding(), params.getVerticalPadding(), params.getHorizontalPadding(), params.getVerticalPadding());
        tagView.setLayoutParams(layoutParams);
        tagView.setBackgroundResource(R.drawable.bg_tagview);
        tagView.setAttributes(new TagViewAttributes());
        tagView.isSelected = false;
        return tagView;
    }

    private void setAttributes(TagViewAttributes attributes) {
        selectedBgColor = attributes.getSelectedBgColor();
        textColor = attributes.getTextColor();
        textSize = attributes.getTextSize();
        setTextSize(textSize);
        setTextColor(textColor);
    }

    public void selectTag() {
        ((GradientDrawable) getBackground()).setColor(selectedBgColor);
    }

    public void unselectTag() {
        ((GradientDrawable) getBackground()).setColor(Color.TRANSPARENT);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        TagViewSavedState tagViewSavedState = new TagViewSavedState(superState);
        tagViewSavedState.selected = isSelected ? 1 : 0;
        return tagViewSavedState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof TagViewSavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        TagViewSavedState tagViewSavedState = (TagViewSavedState) state;
        isSelected = tagViewSavedState.isSelected();
        if (isSelected)
            selectTag();
        else
            unselectTag();

        super.onRestoreInstanceState(tagViewSavedState.getSuperState());
    }

}
