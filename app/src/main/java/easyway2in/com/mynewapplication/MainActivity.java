package easyway2in.com.mynewapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String MESSAGE_KEY="easyway2in.com.mynewapplication.message_key";

    public final static String firstNameKey = "firstName";
    public final static String lastNameKey = "lastName";
    public final static String emailKey = "email";
    public final static String mobileNumberKey = "mobileNumber";
    public final static String MYPREFERENCES = "MyPrefs";

    EditText message_text;
    EditText message1_text;
    EditText EmailId;
    EditText Mobilenumber;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);

        if(sharedPreferences.contains(firstNameKey)&&sharedPreferences.contains(mobileNumberKey)){

            Intent intent = new Intent(this,ThirdActivity.class);
            startActivity(intent);
        }


        setContentView(R.layout.activity_main);

        message_text=(EditText)findViewById(R.id.message_Text);
        message1_text=(EditText)findViewById(R.id.message1_Text);
        EmailId=(EditText)findViewById(R.id.message2_Text);
        Mobilenumber=(EditText)findViewById(R.id.message3_Text);

        //Here you need to get the shared preferences using the key MYPREFERENCES .
        sharedPreferences = getSharedPreferences(MYPREFERENCES,Context.MODE_PRIVATE);
        //get the value of firstname from the shared pred using the firstNameKey and setting it in the editText message_text
        message_text.setText(sharedPreferences.getString(firstNameKey,null));//default value is assigned null . So that hint can show up
        message1_text.setText(sharedPreferences.getString(lastNameKey,null));
        EmailId.setText(sharedPreferences.getString(emailKey, null));
        Mobilenumber.setText(sharedPreferences.getString(mobileNumberKey, null));





    }
    public void sendMessage(View view)
    {

        ////String message = message_text.getText().toString();
        ////String message1 = message_text.getText().toString();
        ////String e1 =Emailid.getText().toString();
        ////String Mn= Mobilenumber.getText().toString();



     String message = message_text.getText().toString();
     String message1 =message1_text.getText().toString();
     String eI=EmailId.getText().toString();
     String Mn= Mobilenumber.getText().toString();
        SharedPreferences.Editor editor=sharedPreferences.edit();
        //key for every thing cannot be same . other wise it will override.
        //Need to put different key for every value .
        editor.putString(firstNameKey,message);
        editor.putString(lastNameKey,message1);
        editor.putString(emailKey,eI);
        editor.putString(mobileNumberKey,Mn);
        editor.commit();
        Intent intent = new Intent(this,ThirdActivity.class);//This will open the third activity
        intent.putExtra(MESSAGE_KEY,message);  //no need to put values in intent becoz already storing in shared preferences.
        intent.putExtra(MESSAGE_KEY,message1);
        intent.putExtra(MESSAGE_KEY,eI);
        intent.putExtra(MESSAGE_KEY,Mn);
        startActivity(intent);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
