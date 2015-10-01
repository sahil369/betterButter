package greatfoodstartshere.betterbutter.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jyot on 29/9/15.
 */
public class Recipe implements Parcelable{

    int id, likeCount, shares;
    String name, url, imageUrl, next;

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    String previous;
    boolean liked;
    User user;

    public Recipe(int id, String name, String url, String imageUrl, int likeCount, boolean liked) {
        this.id = id;
        this.likeCount = likeCount;
        this.name = name;
        this.url = url;
        this.imageUrl = imageUrl;
        this.liked = liked;
    }

    public Recipe(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe(Parcel source){
        id = source.readInt();
        shares = source.readInt();
        likeCount = source.readInt();
        name = source.readString();
        url = source.readString();
        imageUrl = source.readString();
        next = source.readString();
        liked = (Boolean) source.readValue(null);
        user = source.readParcelable(getClass().getClassLoader());
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(shares);
        dest.writeInt(likeCount);
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(imageUrl);
        dest.writeString(next);
        dest.writeValue(liked);
        dest.writeParcelable(user, flags);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
