package multi.android.material_design_pro.recycler.exam;
public class CardItem {
    int img;
    String sentence;

    public CardItem(int img, String sentence) {
        this.img = img;
        this.sentence = sentence;
    }

    public int getImg() {
        return img;
    }

    public String getSentence() {
        return sentence;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public String toString() {
        return "CardItem{" +
                "img=" + img +
                ", sentence='" + sentence + '\'' +
                '}';
    }
}
