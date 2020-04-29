package exam.day03.view.datamanagementpro.sqlite.exam;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    int idx;
    String name;
    int price;
    int amount;
    int total;

    public Product(int idx, String name, int price, int amount, int total) {
        this.idx = idx;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.total = total;
    }


    @Override
    public String toString() {
        return idx +
                ", name=" + name +
                ", price=" + price +
                ", amount=" + amount +
                ", total=" + total;
    }

    // Parcelable method space
    protected Product(Parcel in) {
        idx = in.readInt();
        name = in.readString();
        price = in.readInt();
        amount = in.readInt();
        total = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idx);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeInt(amount);
        dest.writeInt(total);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }


}
