package com.pigwell.cordova.plugin;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.android.hdhe.uhf.reader.Tools;
import com.android.hdhe.uhf.reader.UhfReader;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import android.widget.Toast;
import java.util.ArrayList;
import org.apache.cordova.PluginResult;

/**
 * Created by avila on 28/10/13.
 */
public class RfidScanner extends CordovaPlugin {
    public static final int SCAN_CODE = 0;
    public static final int RADAR_CODE = 1;
    private static final String SCAN_INTENT = "eficid.intent.action.RFID_SCAN";
    private static final String RADAR_INTENT = "eficid.intent.action.RFID_RADAR";
    private static final String CANCELLED = "cancelled";
    private static final String LOG_TAG = "RfidScanner";
    private UhfReader uhfReader;
    private boolean startFlag = false;
    private List<byte[]> epcList;
    String epcStr = null;

    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if (action.equals("scan")) {
            startFlag = true;
            uhfReader = UhfReader.getInstance();
            epcList = uhfReader.inventoryRealTime(); //实时盘存
            if (epcList != null && !epcList.isEmpty()) {
                //扫描到后立即关闭连接,防止多次beep
                if (uhfReader != null) {
                    uhfReader.close();
                }
                for (byte[] epc : epcList) {
                    epcStr = Tools.Bytes2HexString(epc, epc.length);
                }
                // Intent calIntent = new Intent(Intent.ACTION_EDIT)
                //         .putExtra("tfidid", epcStr);
                // this.cordova.getActivity().startActivity(calIntent);
                JSONObject json = new JSONObject();
                try{
                    json.put("type", "1");
                    json.put("signin", "2");
                }catch(JSONException ex){
                }
                PluginResult mPlugin = new PluginResult(PluginResult.Status.OK,json);  
                mPlugin.setKeepCallback(true);  
                callbackContext.sendPluginResult(mPlugin);  
                callbackContext.success();
                return true;
            }
        } else {
            return false;
        }
        return true;
    }

 
}
