package greatfoodstartshere.betterbutter.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jyot on 29/9/15.
 */
public class User implements Parcelable{

    int id;
    String name, imageUrl, lastUpdate, url;

    public User(int id, String name, String imageUrl, String lastUpdate, String url){
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.lastUpdate = lastUpdate;
        this.url = url;
    }

    public User(int id, String name, String url){
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public String getLastUpdate(){
        return lastUpdate;
    }

    public String getUrl(){
        return url;
    }


    public User(Parcel source){
        id = source.readInt();
        name = source.readString();
        imageUrl = source.readString();
        lastUpdate = source.readString();
        url = source.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeString(lastUpdate);
        dest.writeString(url);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
