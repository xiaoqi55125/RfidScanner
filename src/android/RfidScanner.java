package com.pigwell.cordova.plugin;

// import android.app.Activity;
// import android.content.Intent;
// import android.util.Log;

// import com.android.hdhe.uhf.reader.Tools;
// import com.android.hdhe.uhf.reader.UhfReader;

// import org.apache.cordova.CallbackContext;
// import org.apache.cordova.CordovaPlugin;
// import org.json.JSONArray;
// import org.json.JSONException;
// import org.json.JSONObject;
// import java.util.List;
// import android.widget.Toast;
// import java.util.ArrayList;
// import org.apache.cordova.PluginResult;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import android.os.Bundle;
/**
 * Created by avila on 28/10/13.
 */
public class RfidScanner extends CordovaPlugin {
 


    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("greet")) {

            String name = data.getString(0);
            String message = "Hello, " + name ;
            callbackContext.success(message);

            return true;

        } else {
            
            return false;

        }
    }

 
}
