package com.example.modelpapper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.modelpapper.Database.DBHelper;

public class ProfileManagement extends AppCompatActivity {
    Button AddUser;

    EditText UserName,DateOfBorth,Password;
    RadioButton Male,Femaile;
    String Gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);
        AddUser=findViewById(R.id.UpdateButton);
        UserName=findViewById(R.id.ExUsername);
        DateOfBorth=findViewById(R.id.ExDob);
        Password=findViewById(R.id.ExPassword);
        Male=findViewById(R.id.RadMale);
        Femaile=findViewById(R.id.RadFemale);
        if(Male.isChecked() == true){
            Gender ="Male";
        }else{
            Gender ="Female";
        }
        AddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddUser(UserName.getText().toString(),DateOfBorth.getText().toString(),Password.getText().toString(),Gender);

            }
        });
    }

    public void AddUser(String Username,String Dob,String Password,String gender){
        DBHelper DB= new DBHelper(this);
        long x=DB.addInfo(Username,Dob,Password,gender);
        if (x > 1){
            Toast.makeText(this, "Pass", Toast.LENGTH_SHORT).show();
            Intent i1= new Intent(getApplicationContext(),EditProfile.class);
            startActivity(i1);
        }else{
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }

    }
}