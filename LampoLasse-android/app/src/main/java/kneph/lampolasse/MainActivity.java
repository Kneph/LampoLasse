package kneph.lampolasse;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fab;
    ImageView image;

    public String messageToSend;
    public String smsnumber;
    public String SUCCESS;
    public String FAIL;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // find views and set onClickListeners
        findViews();
        // number to send text message to
        smsnumber = "+358405863261";

        // fail and success messages
        SUCCESS = getResources().getString(R.string.onnistui);
        FAIL = getResources().getString(R.string.epaonnistui);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void findViews() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        image = (ImageView) findViewById(R.id.image);

        fab.setOnClickListener(this);
        image.setOnClickListener(this);

    }

    private void textmessage(String smsnumber, String messageToSend) {
        // call SmsManager
        SmsManager smsManager = SmsManager.getDefault();
        // define the message and the number you wanna send it to
        smsManager.sendTextMessage(smsnumber, null, messageToSend, null, null);
        // log message for shits and giggles
        Log.i("textmessage", messageToSend + " lähetetty numeroon " + smsnumber);
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://kneph.lampolasse/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://kneph.lampolasse/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == fab.getId()) {
            // if floating action button was clicked
            // define message
            messageToSend = getResources().getString(R.string.kaynnistys_viesti);
            messagePackingDistrict(v, messageToSend);

        } else if (v.getId() == image.getId()) {
            // if image was clicked
            // define message
            messageToSend = getResources().getString(R.string.kysely_viesti);
            messagePackingDistrict(v, messageToSend);
        }
    }

    private void messagePackingDistrict(View v, String messageToSend) {
        try {
            // sends message to defined number
            textmessage(smsnumber, messageToSend);
            // snackbar näyttää onnistuko vai feilasko viestin lähetys
            makeASnack(v, SUCCESS);
        } catch (Exception e) {
            makeASnack(v, FAIL);
            e.getStackTrace();
        }
    }

    private void makeASnack(View v, String viesti) {
        Snackbar.make(v, viesti, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
