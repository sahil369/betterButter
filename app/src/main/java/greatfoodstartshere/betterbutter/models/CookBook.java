package greatfoodstartshere.betterbutter.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by jyot on 29/9/15.
 */
public class CookBook implements Parcelable {

    String next, previous, title, url, description, caption;
    int id, likeCount, memberFollowerCount, shareCount;
    boolean hasLiked, isFollowing, emailEnabled;
    User user;
    ArrayList<Recipe> recipe = new ArrayList<>();

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getMemberFollowerCount() {
        return memberFollowerCount;
    }

    public void setMemberFollowerCount(int memberFollowerCount) {
        this.memberFollowerCount = memberFollowerCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public boolean isHasLiked() {
        return hasLiked;
    }

    public void setHasLiked(boolean hasLiked) {
        this.hasLiked = hasLiked;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setIsFollowing(boolean isFollowing) {
        this.isFollowing = isFollowing;
    }

    public boolean isEmailEnabled() {
        return emailEnabled;
    }

    public void setEmailEnabled(boolean emailEnabled) {
        this.emailEnabled = emailEnabled;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Recipe> getRecipe() {
        return recipe;
    }

    public void setRecipe(ArrayList<Recipe> recipe) {
        this.recipe = recipe;
    }


    public CookBook(){}


    public CookBook(Parcel source){
        id = source.readInt();
        likeCount = source.readInt();
        memberFollowerCount = source.readInt();
        shareCount = source.readInt();
        next = source.readString();
        previous = source.readString();
        title = source.readString();
        url = source.readString();
        description = source.readString();
        caption = source.readString();
        hasLiked = (Boolean) source.readValue(null);
        isFollowing = (Boolean) source.readValue(null);
        emailEnabled = (Boolean) source.readValue(null);
        user = source.readParcelable(getClass().getClassLoader());
        source.readTypedList(recipe, Recipe.CREATOR);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(likeCount);
        dest.writeInt(memberFollowerCount);
        dest.writeInt(shareCount);
        dest.writeString(next);
        dest.writeString(previous);
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(description);
        dest.writeString(caption);
        dest.writeValue(hasLiked);
        dest.writeValue(isFollowing);
        dest.writeValue(emailEnabled);
        dest.writeParcelable(user, flags);
        dest.writeTypedList(recipe);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public CookBook createFromParcel(Parcel in) {
            return new CookBook(in);
        }

        public CookBook[] newArray(int size) {
            return new CookBook[size];
        }
    };
}
