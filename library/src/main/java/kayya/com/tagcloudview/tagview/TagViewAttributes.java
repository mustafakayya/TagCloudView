package kayya.com.tagcloudview.tagview;

/**
 * Created by kayaMAC on 26.03.15.
 */
public class TagViewAttributes {

    int textColor;
    int selectedBgColor;
    float textSize;

    public TagViewAttributes() {
        selectedBgColor = 0xFFFF0000;
        textColor = 0xFFFFFFFF;
        textSize=10f;
    }

    public TagViewAttributes(int textColor, int selectedBgColor, float textSize) {
        this.textColor = textColor;
        this.selectedBgColor = selectedBgColor;
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getSelectedBgColor() {
        return selectedBgColor;
    }

    public void setSelectedBgColor(int selectedColor) {
        this.selectedBgColor = selectedColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }
}
