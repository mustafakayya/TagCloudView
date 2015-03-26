package kayya.com.tagcloudview.tagview;

/**
 * Created by kayaMAC on 25.03.15.
 */
public class TagViewParams {

    int horizontalMargin;
    int horizontalPadding;
    int verticalPadding;
    TagViewType type;


    public TagViewParams(int horizontalMargin, int horizontalPadding, int verticalPadding, TagViewType type) {
        this.horizontalMargin = horizontalMargin;
        this.horizontalPadding = horizontalPadding;
        this.verticalPadding = verticalPadding;
        this.type = type;
    }

    public TagViewParams(int horizontalMargin, int horizontalPadding, int verticalPadding) {
        this.horizontalMargin = horizontalMargin;
        this.horizontalPadding = horizontalPadding;
        this.verticalPadding = verticalPadding;
        this.type = TagViewType.Standart;
    }

    public TagViewType getType() {
        return type;
    }

    public void setType(TagViewType type) {
        this.type = type;
    }

    public int getHorizontalMargin() {
        return horizontalMargin;
    }

    public void setHorizontalMargin(int horizontalMargin) {
        this.horizontalMargin = horizontalMargin;
    }

    public int getHorizontalPadding() {
        return horizontalPadding;
    }

    public void setHorizontalPadding(int horizontalPadding) {
        this.horizontalPadding = horizontalPadding;
    }

    public int getVerticalPadding() {
        return verticalPadding;
    }

    public void setVerticalPadding(int verticalPadding) {
        this.verticalPadding = verticalPadding;
    }
}
