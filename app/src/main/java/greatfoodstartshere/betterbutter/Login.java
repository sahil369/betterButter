package greatfoodstartshere.betterbutter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import greatfoodstartshere.betterbutter.volley.Request;
import greatfoodstartshere.betterbutter.volley.RequestCallback;

public class Login extends AppCompatActivity implements RequestCallback {

    Request request;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Initialise();

        setSupportActionBar(mToolbar);
    }


    public void Initialise(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        request = new Request(this);
    }


    public void LoginOnClick(View view){
        request.GetCookBook(Login.this);
    }


    @Override
    public void onRequestSuccessful(ArrayList list) {
        Intent i = new Intent(getApplicationContext(), DashBoard.class);
        i.putParcelableArrayListExtra("List", list);
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
