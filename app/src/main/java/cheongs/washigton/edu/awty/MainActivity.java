package cheongs.washigton.edu.awty;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private PendingIntent alarmIntent;
    private AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText message = (EditText) findViewById(R.id.message);
        final EditText phone = (EditText) findViewById(R.id.phone);
        final EditText delay = (EditText) findViewById(R.id.delay);
        final Button start = (Button) findViewById(R.id.button);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // For the delay, only numbers can be inputted because I set the Input Type to be numbers.
                // The other two just needs to have values or than an empty string
                if(!message.getText().toString().equals("") && (!phone.getText().equals("")) && !delay.getText().equals("")){
                    if(start.getText().equals("START")) {
                        start.setText("STOP");
                        Intent intent = new Intent(MainActivity.this, Receiver.class);
                        intent.putExtra("passedMessage", message.getText().toString());
                        intent.putExtra("passedPhone", phone.getText().toString());
                        alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

                        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                        int delayTimer = Integer.parseInt(delay.getText().toString()) * 60000; // 60,000 ms per minute
                        am.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis(), delayTimer, alarmIntent);

                        // Toasts have delay once "START" has been pressed. This toast is for right when "START" is clicked
                        Toast.makeText(MainActivity.this, phone.getText() + ": " + message.getText(), Toast.LENGTH_SHORT).show();

                    } else {
                        start.setText("START");
                        am.cancel(alarmIntent);
                        alarmIntent.cancel();
                    }
                }
            }
        });

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
