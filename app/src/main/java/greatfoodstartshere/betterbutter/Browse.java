package greatfoodstartshere.betterbutter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import greatfoodstartshere.betterbutter.adapters.RecipeAdapter;
import greatfoodstartshere.betterbutter.models.Recipe;


public class Browse extends AppCompatActivity{

    String TAG = "Browse Activity";

    ArrayList<Recipe> browseList;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse);

        Bundle bdl = getIntent().getExtras();
        browseList = bdl.getParcelableArrayList("List");

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            BrowseRecipe firstFragment = new BrowseRecipe();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("list", browseList);
            firstFragment.setArguments(bundle);

            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, firstFragment)
                    .commit();
        }
        Initialise();

        //setSupportActionBar(mToolbar);
    }


   public void Initialise(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }


    public void filterButtonClick(View view){
        BrowseFilter newFragment = new BrowseFilter();

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, newFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
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
