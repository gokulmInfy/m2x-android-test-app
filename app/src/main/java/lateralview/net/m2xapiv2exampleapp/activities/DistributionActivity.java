package lateralview.net.m2xapiv2exampleapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import lateralview.net.attm2xapiv2.listeners.ResponseListener;
import lateralview.net.attm2xapiv2.model.Device;
import lateralview.net.attm2xapiv2.model.Distribution;
import lateralview.net.attm2xapiv2.network.ApiV2Response;
import lateralview.net.m2xapiv2exampleapp.R;

public class DistributionActivity extends Activity implements ResponseListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribution);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_distribution, menu);
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

    public void listDistributions(View view) {
        Distribution.list(DistributionActivity.this, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiV2Response result, int requestCode) {

            }

            @Override
            public void onRequestError(ApiV2Response error, int requestCode) {

            }
        });
    }

    public void createDistribution(View view){
        try {
            JSONObject o = new JSONObject("{ \"name\": \"Sample Distribution\",\n" +
                    "  \"description\": \"Longer description for Sample Distribution\",\n" +
                    "  \"visibility\": \"public\" }");
            Distribution.create(DistributionActivity.this, o, this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void viewDistributionDetails(View view){
        Distribution.viewDetails(DistributionActivity.this,"cc86b9c881755506d356659363680d29",this);
    }

    public void updateDistributionDetails(View view){
        try {
            JSONObject o = new JSONObject("{ \"name\": \"Sample Distribution\",\n" +
                    "                \"description\": \"Longer description for Sample Distribution\",\n" +
                    "                \"visibility\": \"public\" }");
            Distribution.updateDetails(DistributionActivity.this, o, "cc86b9c881755506d356659363680d29", this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void listDevicesExistingDistribution(View view){
        Distribution.listDevices(DistributionActivity.this,"cc86b9c881755506d356659363680d29",this);
    }

    public void addDeviceToDistribution(View view){
        try {
            JSONObject o = new JSONObject("{ \"serial\": \"ABC1234\"}");
            Distribution.addDevice(DistributionActivity.this, o, "cc86b9c881755506d356659363680d29", this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void deleteDistribution(View view){
        Distribution.delete(DistributionActivity.this,"cc86b9c881755506d356659363680d29",this);
    }

    public void listDataStreams(View view){
        Distribution.listDataStreams(DistributionActivity.this,"cc86b9c881755506d356659363680d29",this);
    }

    public void createUpdateDataStream(View view){
        try{
            JSONObject o = new JSONObject("{ \"unit\": { \"label\": \"celsius\", symbol: \"C\" }, \"type\": \"numeric\" }");
            Distribution.createUpdateDataStream(DistributionActivity.this,o,"cc86b9c881755506d356659363680d29","LVStream",this);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void viewDataStream(View view){
        Distribution.viewDataStream(DistributionActivity.this,"cc86b9c881755506d356659363680d29","LVStream",this);
    }

    public void deleteDataStream(View view){
        Distribution.deleteDataStream(DistributionActivity.this,"cc86b9c881755506d356659363680d29","LVStreams",this);
    }

    public void listTriggers(View view){
        Distribution.listTriggers(DistributionActivity.this,"cc86b9c881755506d356659363680d29",this);
    }

    public void createTrigger(View view){
        try{
            JSONObject o = new JSONObject("{ \"stream\": \"LVStream\",\n" +
                    "  \"name\": \"High temperature\",\n" +
                    "  \"condition\": \">\",\n" +
                    "  \"value\": 30,\n" +
                    "  \"callback_url\": \"http://example.com\",\n" +
                    "  \"status\": \"enabled\",\n" +
                    "  \"send_location\": true }");
            Distribution.createTrigger(DistributionActivity.this,o,"06a0c83c5c26558c4ea80e47a61f80ad",this);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void viewTrigger(View view){
        Distribution.viewTrigger(DistributionActivity.this,"06a0c83c5c26558c4ea80e47a61f80ad","",this);
    }

    public void updateTrigger(View view){
        try{
            JSONObject o = new JSONObject("{ \"stream\": \"LVStream\",\n" +
                    "  \"name\": \"High temperature\",\n" +
                    "  \"condition\": \">\",\n" +
                    "  \"value\": 30,\n" +
                    "  \"callback_url\": \"http://example.com\",\n" +
                    "  \"status\": \"enabled\",\n" +
                    "  \"send_location\": false }");
            Distribution.updateTrigger(DistributionActivity.this,o,"06a0c83c5c26558c4ea80e47a61f80ad","",this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void testTrigger(View view){
        try{
            JSONObject o = new JSONObject("{\n" +
                    "  \"device_id\": \"e534850ff4fc3c8bab86ac14b41d26f7\",\n" +
                    "  \"stream\": \"LVStream\",\n" +
                    "  \"trigger_name\": \"High Temperature\",\n" +
                    "  \"trigger_description\": \"temperature > 30\",\n" +
                    "  \"condition\": \">\",\n" +
                    "  \"threshold\": 30.0,\n" +
                    "  \"value\": 31.5,\n" +
                    "  \"timestamp\": \"2014-06-27T22:22:58.104Z\",\n" +
                    "  \"location\": {\n" +
                    "               \"name\": \"Machines room\",\n" +
                    "               \"latitude\": 40.77177,\n" +
                    "               \"longitude\": -73.97352,\n" +
                    "               \"elevation\": 0,\n" +
                    "               \"timestamp\": \"2014-06-27T22:22:57.040Z\"\n" +
                    "              }\n" +
                    "}");
            Distribution.testTrigger(DistributionActivity.this,o,"06a0c83c5c26558c4ea80e47a61f80ad","",this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteTrigger(View view){
        Distribution.deleteTrigger(DistributionActivity.this,"06a0c83c5c26558c4ea80e47a61f80ad","",this);
    }

    //API listeners
    @Override
    public void onRequestCompleted(ApiV2Response result, int requestCode) {
        Log.d("APIV2Request", "Completed");
        Log.d("APIV2 - RESULT",result.get_raw());
    }

    @Override
    public void onRequestError(ApiV2Response error, int requestCode) {
        Log.d("APIV2Request","Error");
        Log.d("APIV2 - ERROR",error.get_raw());
    }
}
