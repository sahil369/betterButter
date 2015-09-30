package greatfoodstartshere.betterbutter.models;

/**
 * Created by jyot on 29/9/15.
 */
public class Recipe {

    int id, likeCount;
    String name, url, imageUrl;
    boolean liked;

    public Recipe(int id, String name, String url, String imageUrl, int likeCount, boolean liked) {
        this.id = id;
        this.likeCount = likeCount;
        this.name = name;
        this.url = url;
        this.imageUrl = imageUrl;
        this.liked = liked;
    }

    public int getId() {
        return id;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isLiked() {
        return liked;
    }
}
