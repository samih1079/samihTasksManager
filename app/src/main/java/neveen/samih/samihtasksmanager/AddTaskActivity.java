package neveen.samih.samihtasksmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import MyData.MyTask;

public class AddTaskActivity extends AppCompatActivity {

    private TextInputEditText etTitle,etSubject;
    private Button btnSave;
    private ImageButton imgvTaskPic;
    private SeekBar skbNecessity;
    private Spinner spnrImprotance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        etSubject=findViewById(R.id.etSubject);
        etTitle=findViewById(R.id.etTitle);
        imgvTaskPic=findViewById(R.id.imgvTaskPic);
        btnSave=findViewById(R.id.btnSave);
        spnrImprotance=findViewById(R.id.spnrImprotance);
        skbNecessity=findViewById(R.id.skbNecessity);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 validateFeilds();
            }
        });




    }

    private void validateFeilds()
    {
        boolean isOk=true;
        String title=etTitle.getText().toString();
        String subj=etSubject.getText().toString();
        int necc =skbNecessity.getProgress();
        //String st=spnrImprotance.getSelectedItem().toString();
        if(title.length()==0)
        {
            etTitle.setError("must enter titile");
            isOk=false;
        }

        if(isOk)
        {
            MyTask myTask=new MyTask();
            myTask.setTitle(title);
            myTask.setSubject(subj);
            myTask.setNecessity(necc);

            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            myTask.setOwner(uid);

            FirebaseDatabase db=FirebaseDatabase.getInstance();
            DatabaseReference ref = db.getReference();
            String key = ref.child("mytasks").push().getKey();
            myTask.setKey(key);

            ref.child("mytasks").child(key).setValue(myTask).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {//response
                    if(task.isSuccessful())
                    {
                        Toast.makeText(getApplicationContext(), "Add Successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Add Not Successful", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}






