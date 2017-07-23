package com.pigwell.cordova.plugin;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.android.hdhe.uhf.reader.Tools;
import com.android.hdhe.uhf.reader.UhfReader;

import org.apache.cordova.*;
// import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import android.widget.Toast;
import java.util.ArrayList;
import org.apache.cordova.PluginResult;

/**
 * Created by zhicheng
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
    private boolean runFlag = true;


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if ("greet".equals(action)) {
             startFlag = true;
             try {
                 while (runFlag) {
                     if (startFlag) {
                         Log.e("###", "11111111111111111111");
                         uhfReader = UhfReader.getInstance();
                         epcList = uhfReader.inventoryRealTime(); //实时盘存
                         if (epcList != null && !epcList.isEmpty()) {
                             //扫描到后立即关闭连接,防止多次beep
                             runFlag = false;
                             if (uhfReader != null) {
                                 uhfReader.close();
                             }
                             for (byte[] epc : epcList) {
                                 epcStr = Tools.Bytes2HexString(epc, epc.length);
                             }

                         }
                     }
                 }
                 callbackContext.success(epcStr);
                 return true;
            } catch (Exception e) {
                Log.e("###",e.getMessage());
            }
        }
        return false;
    }

}
