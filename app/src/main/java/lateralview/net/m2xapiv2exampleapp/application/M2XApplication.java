package lateralview.net.m2xapiv2exampleapp.application;

import android.app.Application;

import lateralview.net.attm2xapiv2.main.M2XAPI;

/**
 * Created by Joaquin on 2/12/14.
 */
public class M2XApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize communication library
        M2XAPI.initialize(getApplicationContext(),"<YOUR API KEY HERE>");
    }
}
