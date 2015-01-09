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
import lateralview.net.attm2xapiv2.main.M2XAPI;
import lateralview.net.attm2xapiv2.model.Charts;
import lateralview.net.attm2xapiv2.network.ApiV2Response;
import lateralview.net.m2xapiv2exampleapp.R;

public class ChartsActivity extends Activity implements ResponseListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_charts, menu);
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

    public void listCharts(View view){
        Charts.list(ChartsActivity.this,null,this);
    }

    public void createChart(View view){
        try{
            JSONObject o = new JSONObject("{ " +
                    "\"name\": \"Single-device Chart\",\n" +
                    "  \"series\": [\n" +
                    "    { \"device\": \"437cb907799ae7e01309133006806ba3\", \"stream\": \"LVStream\" }\n" +
                    "  ]\n" +
                    "}");
            Charts.create(ChartsActivity.this,o,this);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void viewChartDetails(View view){
        Charts.viewDetails(ChartsActivity.this,"",this);
    }

    public void updateChart(View view){
        try{
            JSONObject o = new JSONObject("{ \"name\": \"Single-device Chart\",\n" +
                    "  \"series\": [\n" +
                    "    { \"device\": \"437cb907799ae7e01309133006806ba3\", \"stream\": \"LVStream\" }\n" +
                    "  ]\n" +
                    "}");
            Charts.update(ChartsActivity.this,o,"",this);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void deleteChart(View view){
        Charts.delete(ChartsActivity.this,"",this);
    }

    public void renderChart(View view){
        Charts.render(ChartsActivity.this,"","png",this);
    }

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
