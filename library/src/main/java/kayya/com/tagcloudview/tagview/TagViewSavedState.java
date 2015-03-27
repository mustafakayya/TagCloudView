package kayya.com.tagcloudview.tagview;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;


/**
 * Created by kayaMAC on 27.03.15.
 */
public class TagViewSavedState extends View.BaseSavedState {
    int selected;

    TagViewSavedState(Parcelable superState) {
        super(superState);
    }

    private TagViewSavedState(Parcel in) {
        super(in);
        this.selected = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(this.selected);
    }

    public static final Parcelable.Creator<TagViewSavedState> CREATOR = new Parcelable.Creator<TagViewSavedState>() {
        public TagViewSavedState createFromParcel(Parcel in) {
            return new TagViewSavedState(in);
        }

        public TagViewSavedState[] newArray(int size) {
            return new TagViewSavedState[size];
        }
    };

    boolean isSelected() {
        return selected == 1 ? true : false;
    }

}
