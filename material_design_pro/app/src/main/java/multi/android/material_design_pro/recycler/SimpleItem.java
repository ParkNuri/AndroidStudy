package multi.android.material_design_pro.recycler;
// RecyclerView의 한 항목을 구성하는 데이터를 저장하는 객체
public class SimpleItem {
    String data;

    public SimpleItem(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SimpleItem{" +
                "data='" + data + '\'' +
                '}';
    }
}
