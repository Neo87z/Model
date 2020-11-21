package com.example.modelpapper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.modelpapper.Database.DBHelper;

import java.util.List;

public class EditProfile extends AppCompatActivity {

    EditText Username,Password,Dob;
    RadioButton Male,Female;
    Button Search,Edit,Delete;
    String Gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Username=findViewById(R.id.UserNmaeinput);
        Password=findViewById(R.id.pass);
        Dob=findViewById(R.id.DOB);
        Male=findViewById(R.id.male);
        Female=findViewById(R.id.fmale);
        Search=findViewById(R.id.button3);
        Edit=findViewById(R.id.EditButton);
        Delete=findViewById(R.id.DleetButton);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateUser();
            }
        });
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchMember(Username.getText().toString());
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteUser(Username.getText().toString());
            }
        });
    }

    public void DeleteUser(String UserName){
        DBHelper db= new DBHelper(this);
        db.deleteInfo(UserName);
        Username=findViewById(R.id.UserNmaeinput);
        Password=findViewById(R.id.pass);
        Dob=findViewById(R.id.DOB);
        Male=findViewById(R.id.male);
        Female=findViewById(R.id.fmale);
        Username.setText("");
        Password.setText("");
        Username.setText("");
        Dob.setText(" ");
        Male.setChecked(false);
        Female.setChecked(false);

    }

    public  void UpdateUser(){
        Username=findViewById(R.id.UserNmaeinput);
        Password=findViewById(R.id.pass);
        Dob=findViewById(R.id.DOB);
        Male=findViewById(R.id.male);
        Female=findViewById(R.id.fmale);
        if(Male.isChecked() == true){
            Gender ="Male";
        }else{
            Gender ="Female";
        }
        DBHelper Db= new DBHelper(this);
        Boolean x=Db.UpdateInfo(Username.getText().toString(),Dob.getText().toString(),Gender,Password.getText().toString());
        if(x == true){
            Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }

    }
    public void SearchMember(String UserName){
        DBHelper DB= new DBHelper(this);
        List Names=DB.readAllInfor(UserName);

        if(Names.size() == 0){
            Toast.makeText(this, "No User", Toast.LENGTH_SHORT).show();

        }
        else if(Names.get(0).equals(" ")){
            Toast.makeText(this, "No User", Toast.LENGTH_SHORT).show();
        }
        else{
            Username=findViewById(R.id.UserNmaeinput);
            Password=findViewById(R.id.pass);
            Dob=findViewById(R.id.DOB);
            Male=findViewById(R.id.male);
            Female=findViewById(R.id.fmale);
            Password.setText(Names.get(1).toString());
            Dob.setText(Names.get(0).toString());
            if(Names.get(2).equals("Female") ){
                Female.setChecked(true);

            }else{
                Male.setChecked(true);
            }
        }

    }
}