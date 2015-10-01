package greatfoodstartshere.betterbutter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import greatfoodstartshere.betterbutter.adapters.CookBookAdapter;
import greatfoodstartshere.betterbutter.models.CookBook;
import greatfoodstartshere.betterbutter.models.Recipe;
import greatfoodstartshere.betterbutter.models.User;
import greatfoodstartshere.betterbutter.volley.AppController;

public class DashBoard extends AppCompatActivity {

    String TAG = "DashBoard Activity";

    private ArrayList<CookBook> dashboardList;
    ArrayList<Recipe> browseList;
    private Toolbar mToolbar;
    private SearchView searchView;
    RecyclerView rv;
    LinearLayoutManager llm;
    CookBookAdapter cookBookAdapter;
    ImageView navDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        Bundle bdl = getIntent().getExtras();
        dashboardList = bdl.getParcelableArrayList("List");

        Initialise();
        InitialiseListeners();

        setSupportActionBar(mToolbar);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);
        rv.setAdapter(cookBookAdapter);
    }


    public void Initialise(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        rv = (RecyclerView)findViewById(R.id.rv);
        llm = new LinearLayoutManager(this);
        cookBookAdapter = new CookBookAdapter(dashboardList);
        navDrawer = (ImageView) findViewById(R.id.menu);
    }


    public void InitialiseListeners(){
        navDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetBrowseRecipes();
            }
        });
    }


    public void GetBrowseRecipes(){
        String url = "http://192.168.1.6/json/browse_recipe_response_new.json";
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Getting Data...");
        pDialog.setCancelable(false);
        pDialog.show();

        JsonObjectRequest jsonReq = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                browseList = new ArrayList<>();

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

                } catch (JSONException e) {
                    Log.wtf(TAG, "Json Parsing Error Caught");
                }
                pDialog.hide();

                //Browse Launched
                Intent i = new Intent(getApplicationContext(), Browse.class);
                i.putParcelableArrayListExtra("List", browseList);
                startActivity(i);

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
