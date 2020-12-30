package com.example.sharedpreferencesact41;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences ;
    private SharedPreferences.Editor mEditor ;

    private static final String TAG = "MainActivity";
    private EditText mName , mPassword;
    private Button btnLogin;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mName = (EditText) findViewById(R.id.editTextTextPersonName3);
        mPassword = (EditText) findViewById(R.id.editTextTextPassword3);
        btnLogin = (Button) findViewById(R.id.button3);
        mCheckBox = (CheckBox) findViewById(R.id.checkBox2);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit() ;
        checkSharedPreferences();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save the checkbox preference
                if (mCheckBox.isChecked()){
                    //set a checkbox when the app starts
                    mEditor.putString(getString(R.string.checkbox),"True");
                    mEditor.commit();
                    //save name
                    String name = mName.getText().toString();
                    mEditor.putString(getString(R.string.name),name);
                    mEditor.commit();
                    //save password
                    String password = mPassword.getText().toString();
                    mEditor.putString(getString(R.string.password),password);
                    mEditor.commit();
                }else {

                    //set a checkbox when the app starts
                    mEditor.putString(getString(R.string.checkbox),"False");
                    mEditor.commit();
                    // never save name

                    mEditor.putString(getString(R.string.name), "");
                    mEditor.commit();
                    // never save password

                    mEditor.putString(getString(R.string.password), "");
                    mEditor.commit();
                }
            }
        });
    }

    private void checkSharedPreferences () {
        String chechbox = mPreferences.getString(getString(R.string.checkbox),"False");
        String name = mPreferences.getString(getString(R.string.name) , "");
        String password = mPreferences.getString(getString(R.string.password),"");

        mName.setText(name);
        mPassword.setText(password);

        if (chechbox.equals("True")){
            mCheckBox.setChecked(true);
        }else {
            mCheckBox.setChecked(false);
        }
    }
}