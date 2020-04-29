package exam.day03.view.selectview.view.adapter;

public class ActorItem {
    int img;
    String name;
    String date;
    String memo;

    public ActorItem(int img, String name, String date, String memo) {
        this.img = img;
        this.name = name;
        this.date = date;
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "ActorItem{" +
                "img=" + img +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
