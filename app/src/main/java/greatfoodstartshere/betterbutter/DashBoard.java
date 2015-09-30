package greatfoodstartshere.betterbutter;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
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
import greatfoodstartshere.betterbutter.volley.AppController;

public class DashBoard extends AppCompatActivity {

    private ArrayList<CookBook> dashboardList;
    private Toolbar mToolbar;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        GetCookBook();
    }


    public void GetCookBook(){
        String url = "http://192.168.1.6/json/my_feed.json";
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Getting Data...");
        pDialog.setCancelable(false);
        pDialog.show();

        JsonObjectRequest jsonReq = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                dashboardList = new ArrayList<>();

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
                } catch (JSONException e) {
                    Log.wtf("MainActivity", "Json Parsing Error Caught");
                }

                pDialog.hide();
                Log.wtf("MainActivity", "Get data Success");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("Get Request Error", error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Error Getting Information!",
                        Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonReq);
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dash_board, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
