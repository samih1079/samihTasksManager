package neveen.samih.samihtasksmanager;
//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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainTasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    //
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.mnitmSettings)
        {
            Intent i=new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.mnitemHistory)
        {

        }
        if(item.getItemId()==R.id.mnitmSignOut)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Are you sure?");



        }
        return true;
    }
}