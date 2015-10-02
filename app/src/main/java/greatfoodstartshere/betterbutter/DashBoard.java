package greatfoodstartshere.betterbutter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import java.util.ArrayList;

import greatfoodstartshere.betterbutter.adapters.CookBookAdapter;
import greatfoodstartshere.betterbutter.models.CookBook;
import greatfoodstartshere.betterbutter.models.Recipe;
import greatfoodstartshere.betterbutter.volley.Request;
import greatfoodstartshere.betterbutter.volley.RequestCallback;

public class DashBoard extends AppCompatActivity implements RequestCallback {

    String TAG = "DashBoard Activity";

    private ArrayList<CookBook> dashboardList;
    ArrayList<Recipe> browseList;
    private Toolbar mToolbar;
    private SearchView searchView;
    RecyclerView rv;
    LinearLayoutManager llm;
    CookBookAdapter cookBookAdapter;
    ImageView navDrawer;

    Request request;

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

        request = new Request(this);
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
        request.GetBrowseRecipes(DashBoard.this);
    }


    @Override
    public void onRequestSuccessful(ArrayList list) {
        browseList = list;
        Intent i = new Intent(getApplicationContext(), Browse.class);
        i.putParcelableArrayListExtra("List", browseList);
        startActivity(i);
    }


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
