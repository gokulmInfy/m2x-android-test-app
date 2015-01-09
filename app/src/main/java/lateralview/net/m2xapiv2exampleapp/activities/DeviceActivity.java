package lateralview.net.m2xapiv2exampleapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import lateralview.net.attm2xapiv2.listeners.ResponseListener;
import lateralview.net.attm2xapiv2.model.Device;
import lateralview.net.attm2xapiv2.network.ApiV2Response;
import lateralview.net.m2xapiv2exampleapp.R;

public class DeviceActivity extends Activity implements ResponseListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_device, menu);
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

    //Test Methods
    public void searchPublicDevicesCatalog(View view) {
        HashMap<String,String> params = new HashMap<String, String>();
        params.put("p","1");
        Device.searchPublicCatalog(DeviceActivity.this, params, this);
    }

    public void listSearchDevices(View view) {
        HashMap<String,String> params = new HashMap<String, String>();
        params.put("p","1");
        Device.searchDevices(DeviceActivity.this, params, this);
    }

    public void listGroups(View view) {
        Device.listDeviceGroups(DeviceActivity.this, this);
    }

    public void createDevice(View view) {
        JSONObject params = new JSONObject();
        try {
            params.put("name","LVTest");
            params.put("visibility","public");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Device.createDevice(DeviceActivity.this,params,this);
    }

    public void updateDeviceDetails(View view) {
        JSONObject params = new JSONObject();
        try {
            params.put("name","LVTesty");
            params.put("description","test");
            params.put("visibility","public");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Device.updateDeviceDetails(DeviceActivity.this, params, "437cb907799ae7e01309133006806ba3", this);
    }

    public void viewDeviceDetails(View view) {
        Device.viewDeviceDetails(DeviceActivity.this, "437cb907799ae7e01309133006806ba3", this);
    }

    public void readDeviceLocation(View view) {
        Device.readDeviceLocation(DeviceActivity.this, "437cb907799ae7e01309133006806ba3", this);
    }

    public void updateDeviceLocation(View view) {
        try {
            JSONObject obj = new JSONObject("{ \"name\": \"Storage Room\",\n" +
                    "  \"latitude\": -37.9788423562422,\n" +
                    "  \"longitude\": -57.5478776916862,\n" +
                    "  \"timestamp\": \"2014-09-10T19:15:00.756Z\",\n" +
                    "  \"elevation\": 5 }");
            Device.updateDeviceLocation(DeviceActivity.this,obj,"437cb907799ae7e01309133006806ba3",this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void listDataStreams(View view) {
        Device.listDataStreams(DeviceActivity.this, "437cb907799ae7e01309133006806ba3", this);
    }

    public void createUpdateDataStream(View view){
        try {
            JSONObject o = new JSONObject("{ \"unit\": { \"label\": \"celsius\", symbol: \"C\" }}");
            Device.createUpdateDataStreams(DeviceActivity.this,o,"437cb907799ae7e01309133006806ba3","LVStream",this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void updateDataStreamValue(View view){
        try {
            JSONObject o = new JSONObject("{ \"timestamp\": \"2014-10-01T12:00:00Z\", \"value\": 100 }");
            Device.createUpdateDataStreams(DeviceActivity.this,o,"437cb907799ae7e01309133006806ba3","LVStream",this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void viewDataStream(View view){
        Device.viewDataStream(DeviceActivity.this,"437cb907799ae7e01309133006806ba3","LVStream",this);
    }

    public void listDataStream(View view){
        Device.listDataStreams(DeviceActivity.this,"437cb907799ae7e01309133006806ba3",this);
    }

    public void dataStreamSampling(View view){
        Device.listDataStreams(DeviceActivity.this,"437cb907799ae7e01309133006806ba3",this);
    }

    public void dataStreamStats(View view){
        Device.dataStreamStats(DeviceActivity.this,"437cb907799ae7e01309133006806ba3","LVStream",this);
    }

    public void postDataStreamValues(View view){
        try {
            JSONObject o = new JSONObject("{ \"values\": [\n" +
                    "  { \"timestamp\": \"2014-09-09T19:15:00.624Z\", \"value\": 32 },\n" +
                    "  { \"timestamp\": \"2014-09-09T20:15:00.522Z\", \"value\": 30 },\n" +
                    "  { \"timestamp\": \"2014-09-09T21:15:00.522Z\", \"value\": 15 } ] }");
            Device.postDataStreamValues(DeviceActivity.this,o,"437cb907799ae7e01309133006806ba3","LVStream",this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void deleteDataStreamValues(View view){
        try {
            JSONObject o = new JSONObject("{ " +
                    "\"from\": \"2014-09-09T19:15:00.624Z\",\n" +
                    "  \"end\": \"2014-09-09T20:15:00.522Z\" }");
            Device.deleteDataStreamValues(DeviceActivity.this,o,"437cb907799ae7e01309133006806ba3","LVStream",this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void deleteDataStream(View view){
        Device.deleteDataStream(DeviceActivity.this,"437cb907799ae7e01309133006806ba3","LVStream",this);
    }

    public void postDeviceUpdates(View view){
        try {
            JSONObject o = new JSONObject("{ \"values\": {\n" +
                    "  \"temperature\": [\n" +
                    "    { \"timestamp\": \"2014-09-09T19:15:00.981Z\", \"value\": 32 },\n" +
                    "    { \"timestamp\": \"2014-09-09T20:15:00.124Z\", \"value\": 30 },\n" +
                    "    { \"timestamp\": \"2014-09-09T21:15:00.124Z\", \"value\": 15 } ],\n" +
                    "  \"humidity\": [\n" +
                    "    { \"timestamp\": \"2014-09-09T19:15:00.874Z\", \"value\": 88 },\n" +
                    "    { \"timestamp\": \"2014-09-09T20:15:00.752Z\", \"value\": 60 },\n" +
                    "    { \"timestamp\": \"2014-09-09T21:15:00.752Z\", \"value\": 75 } ]\n" +
                    "  }\n" +
                    "}");
            Device.postDeviceUpdates(DeviceActivity.this,o,"437cb907799ae7e01309133006806ba3",this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void listTriggers(View view){
        Device.listTriggers(DeviceActivity.this,"437cb907799ae7e01309133006806ba3",this);
    }

    public void createTrigger(View view){
        try {
            JSONObject o = new JSONObject("{ \"stream\": \"LVStream\",\n" +
                    "  \"name\": \"High temperature\",\n" +
                    "  \"condition\": \">\",\n" +
                    "  \"value\": 30,\n" +
                    "  \"callback_url\": \"http://example.com\",\n" +
                    "  \"status\": \"enabled\",\n" +
                    "  \"send_location\": true }");
            Device.createTrigger(DeviceActivity.this,o,"437cb907799ae7e01309133006806ba3",this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void viewTrigger(View view){
        Device.viewTrigger(DeviceActivity.this,"437cb907799ae7e01309133006806ba3","AUo1jryrrmG-mLbSanOV",this);
    }

    public void updateTrigger(View view){
        try {
            JSONObject o = new JSONObject("{ \"stream\": \"temperature\",\n" +
                    "  \"name\": \"High temperature\",\n" +
                    "  \"condition\": \">\",\n" +
                    "  \"value\": 30,\n" +
                    "  \"callback_url\": \"http://example.com\",\n" +
                    "  \"status\": \"enabled\",\n" +
                    "  \"send_location\": false }");
            Device.updateTrigger(DeviceActivity.this,o,"437cb907799ae7e01309133006806ba3","AUo1jryrrmG-mLbSanOV",this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void testTrigger(View view){
        try {
            JSONObject o = new JSONObject("{\n" +
                    "  \"device_id\": \"437cb907799ae7e01309133006806ba3\",\n" +
                    "  \"stream\": \"temperature\",\n" +
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
                    "}\n");
            Device.testTrigger(DeviceActivity.this,o,"437cb907799ae7e01309133006806ba3","AUo1jryrrmG-mLbSanOV",this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void deleteTrigger(View view){
        Device.deleteTrigger(DeviceActivity.this,"437cb907799ae7e01309133006806ba3","AUo1jryrrmG-mLbSanOV",this);
    }

    public void viewRequestLog(View view){
        Device.viewRequestLog(DeviceActivity.this,"437cb907799ae7e01309133006806ba3",this);
    }

    public void deleteDevice(View view){
        Device.deleteDevice(DeviceActivity.this,"437cb907799ae7e01309133006806ba3",this);
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
