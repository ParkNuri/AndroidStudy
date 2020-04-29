package multi.android.material_design_pro.recycler.exam2;
public class CircleItem {
    int img;

    public CircleItem(int data) {
        this.img = data;
    }

    public int getData() {
        return img;
    }

    public void setData(int data) {
        this.img = data;
    }

    @Override
    public String toString() {
        return "CircleItem{" +
                "data='" + img + '\'' +
                '}';
    }
}
