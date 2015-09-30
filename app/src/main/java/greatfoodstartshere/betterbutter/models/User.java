package greatfoodstartshere.betterbutter.models;

/**
 * Created by jyot on 29/9/15.
 */
public class User {

    int id;
    String name, imageUrl, lastUpdate, url;

    public User(int id, String name, String imageUrl, String lastUpdate, String url){
        this.name = name;
        this.imageUrl = imageUrl;
        this.lastUpdate = lastUpdate;
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
}
