package greatfoodstartshere.betterbutter.volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import greatfoodstartshere.betterbutter.models.CookBook;
import greatfoodstartshere.betterbutter.models.Recipe;
import greatfoodstartshere.betterbutter.models.User;

/**
 * Created by jyot on 2/10/15.
 */
public class Request{

    String TAG = "Request";

    RequestCallback requestCallback;

    public Request(RequestCallback listener){
        requestCallback = listener;
    }


    public void GetCookBook(final Context context){
        String url = "http://192.168.1.6/json/my_feed.json";
        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Getting Data...");
        pDialog.setCancelable(false);
        pDialog.show();

        JsonObjectRequest jsonReq = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                ArrayList<CookBook> dashboardList = new ArrayList<>();

                try {
                    CookBook cb = new CookBook();
                    cb.setNext(response.getString("next"));
                    cb.setPrevious(response.getString("previous"));
                    JSONArray results = response.getJSONArray("results");
                    JSONObject result = results.getJSONObject(0);
                    cb.setId(result.getInt("id"));
                    cb.setTitle(result.getString("title"));
                    cb.setUrl(result.getString("url"));
                    cb.setLikeCount(result.getInt("like_count"));
                    cb.setHasLiked(result.getBoolean("has_liked"));
                    cb.setMemberFollowerCount(result.getInt("member_follower_count"));
                    cb.setIsFollowing(result.getBoolean("is_following"));
                    cb.setShareCount(result.getInt("share_count"));
                    cb.setDescription(result.getString("description"));
                    cb.setCaption(result.getString("caption"));
                    cb.setEmailEnabled(result.getJSONObject("email").getBoolean("enabled"));
                    JSONArray recipes = result.getJSONArray("recipes");
                    ArrayList<Recipe> r = new ArrayList<Recipe>(recipes.length());
                    JSONObject item;
                    for(int i = 0; i < recipes.length(); i++){
                        item = recipes.getJSONObject(i);
                        r.add(new Recipe(item.getInt("id"), item.getString("name"),
                                item.getString("url"), item.getString("image_url"),
                                item.getInt("likes_count"), item.getBoolean("has_liked")));
                    }
                    cb.setRecipe(r);
                    JSONObject user = result.getJSONObject("user");
                    User u = new User(user.getInt("id"), user.getString("name"),
                            user.getString("image_url"), user.getString("last_update"),
                            user.getString("url"));
                    cb.setUser(u);

                    //Parsing ends

                    dashboardList.add(cb);
                    dashboardList.add(cb);
                    dashboardList.add(cb);

                    requestCallback.onRequestSuccessful(dashboardList);

                } catch (JSONException e) {
                    Log.wtf("MainActivity", "Json Parsing Error Caught");
                }
                pDialog.hide();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("Get Request Error", error.getMessage());
                Toast.makeText(context,
                        "Error Getting Information!",
                        Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonReq);
    }


    public void GetBrowseRecipes(final Context context){
        String url = "http://192.168.1.6/json/browse_recipe_response_new.json";

        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Getting Data...");
        pDialog.setCancelable(false);
        pDialog.show();

        JsonObjectRequest jsonReq = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                ArrayList<Recipe> browseList = new ArrayList<>();

                try {
                    Recipe re = new Recipe();
                    re.setNext(response.getString("next"));
                    re.setPrevious(response.getString("previous"));
                    JSONArray results = response.getJSONArray("results");
                    JSONObject result = results.getJSONObject(0);
                    re.setId(result.getInt("id"));
                    re.setName(result.getString("name"));
                    re.setUrl(result.getString("url"));
                    re.setLikeCount(result.getInt("likes"));
                    re.setLiked(result.getBoolean("has_liked"));
                    re.setShares(result.getInt("shares"));
                    re.setImageUrl(result.getString("image_url"));
                    JSONObject user = result.getJSONObject("user");
                    User u = new User(user.getInt("id"), user.getString("name"),
                            user.getString("url"));
                    re.setUser(u);

                    //Parsing ends
                    browseList.add(re);
                    browseList.add(re);

                    requestCallback.onRequestSuccessful(browseList);

                } catch (JSONException e) {
                    Log.wtf(TAG, "Json Parsing Error Caught");
                }
                pDialog.hide();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("Get Request Error", error.getMessage());
                Toast.makeText(context,
                        "Error Getting Information!",
                        Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonReq);
    }

}
