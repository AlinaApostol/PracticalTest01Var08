package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {

    private TextView textView = null;
    private Button topleftButton = null;
    private Button toprightButton = null;

    private Button bottomleftButton = null;
    private Button bottomrightButton = null;
    private Button centerButton = null;

    private Button navigateButton = null;

    private int nrDeINcercari = 0;
    private int nrIncercariIncorect = 0;
    private int nrIncercariCorect = 0;

    private int serviceStatus = 2;
    private IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        topleftButton = (Button)findViewById(R.id.topLeftButton);
        toprightButton = (Button)findViewById(R.id.topRightButton);
        centerButton = (Button)findViewById(R.id.centerButton);
        bottomleftButton = (Button)findViewById(R.id.bottomLeftButton);
        bottomrightButton = (Button)findViewById(R.id.bottomRightButton);

        textView = (TextView)findViewById(R.id.textView);

        topleftButton.setOnClickListener(buttonClickListener);
        toprightButton.setOnClickListener(buttonClickListener);
        bottomleftButton.setOnClickListener(buttonClickListener);
        bottomrightButton.setOnClickListener(buttonClickListener);
        centerButton.setOnClickListener(buttonClickListener);

        navigateButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
        navigateButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("corect")) {
                nrIncercariCorect = savedInstanceState.getInt("corect");
            }
            if (savedInstanceState.containsKey("incorect")) {
                nrIncercariIncorect = savedInstanceState.getInt("incorect");
            }
        }

        for (int index = 0; index < actionTypes.length; index++) {
            intentFilter.addAction(actionTypes[index]);
        }
    }

    private String[] actionTypes = {
            "com.example.root.practic.actionType1",
            "com.example.root.practic.actionType2",
            "com.example.root.practic.actionType3"
    };

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("corect", nrIncercariCorect);
        savedInstanceState.putInt("incorect", nrIncercariIncorect);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("corect")) {
            nrIncercariCorect =  savedInstanceState.getInt("corect");
        }
        if (savedInstanceState.containsKey("incorect")) {
            nrIncercariIncorect = savedInstanceState.getInt("incorect");
        }
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private boolean start = true;

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.topLeftButton:
                    if (start == true) {
                        textView.setText(topleftButton.getText().toString());
                        start = false;
                    } else {
                        textView.setText(textView.getText().toString() + "," + topleftButton.getText().toString());
                    }
                    break;

                case R.id.topRightButton:
                    if (start == true) {
                        textView.setText(toprightButton.getText().toString());
                        start = false;
                    } else {
                        textView.setText(textView.getText().toString() + "," + toprightButton.getText().toString());
                    }

                    break;
                case R.id.bottomLeftButton:
                    if (start == true) {
                        textView.setText(bottomleftButton.getText().toString());
                        start = false;
                    } else {
                        textView.setText(textView.getText().toString() + "," + bottomleftButton.getText().toString());
                    }
                    break;
                case R.id.bottomRightButton:
                    if (start == true) {
                        textView.setText(bottomrightButton.getText().toString());
                        start = false;
                    } else {
                        textView.setText(textView.getText().toString() + "," + bottomrightButton.getText().toString());
                    }
                    break;
                case R.id.centerButton:
                    if (start == true) {
                        textView.setText(centerButton.getText().toString());
                        start = false;
                    } else {
                        textView.setText(textView.getText().toString() + "," + centerButton.getText().toString());
                    }
                    break;
                case R.id.navigate_to_secondary_activity_button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08SecondaryActivity.class);
                    intent.putExtra("text", textView.getText().toString());
                    startActivityForResult(intent, 1);

                    String textDeTrimis = textView.getText().toString();
                    String[] pars = textDeTrimis.split("//,");
                    int nr = 0;
                    for(int i = 0; i < pars.length; i++) {
                        nr++;
                    }
                    //if ( nr > 2 && serviceStatus == 2) { //stop=2
                        Intent inte = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
                        inte.putExtra("text", textDeTrimis);
                        getApplicationContext().startService(inte);
                        serviceStatus = 1; //start=1
                    //}
                    break;


            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            nrIncercariCorect++;
            Toast.makeText(this, "The activity returned with nrIncercariCorect " + nrIncercariCorect, Toast.LENGTH_LONG).show();
        } else {
            nrIncercariIncorect++;
            Toast.makeText(this, "The activity returned with nrIncercariIncorect " + nrIncercariIncorect, Toast.LENGTH_LONG).show();
        }
        nrDeINcercari = nrIncercariCorect + nrIncercariIncorect;
        Toast.makeText(this, "The activity returned with nrTotal " + nrDeINcercari, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practical_test01_var08_main, menu);
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

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var08Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}
