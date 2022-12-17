package com.example.myfirebaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText recipe, ingredients, preparation;
    private DatabaseReference mDataBase;
    private  String RECIPE_KEY = "Recipe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        recipe = findViewById(R.id.et_recipe);
        ingredients = findViewById(R.id.et_ingredients);
        preparation = findViewById(R.id.et_preparetion);
        mDataBase = FirebaseDatabase.getInstance().getReference(RECIPE_KEY);

    }
    public void SaveRecipe(View view){
        String id = mDataBase.getKey();
        String field1 = recipe.getText().toString();
        String field2 = ingredients.getText().toString();
        String field3 = preparation.getText().toString();
        Fields newField = new Fields(id, field1, field2, field3);
        if (!TextUtils.isEmpty(field1) && !TextUtils.isEmpty(field2) && !TextUtils.isEmpty(field3)) {
            mDataBase.push().setValue(newField);
            Toast.makeText(this, "Ваші дані збережені", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Не всі поля заповнені", Toast.LENGTH_SHORT).show();
        }
    }
    public void ReadRecipe(View view){
        Intent i = new Intent(MainActivity.this, ReadActivity.class);
        startActivity(i);

    }

}