package com.xxy.scandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.xxy.mlkitscanner.MNScanManager;
import com.xxy.mlkitscanner.callback.act.MNScanCallback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_scan).setOnClickListener(view -> {
            MNScanManager.startScan(this, (resultCode, data) -> {
                handlerResult(resultCode, data);
            });
        });
    }

    private void handlerResult(int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        switch (resultCode) {
            default:
                break;
            case MNScanManager.RESULT_SUCCESS:
                ArrayList<String> results = data.getStringArrayListExtra(MNScanManager.INTENT_KEY_RESULT_SUCCESS);
                StringBuilder resultStr = new StringBuilder();
                for (int i = 0; i < results.size(); i++) {
                    resultStr.append("第" + (i + 1) + "条：");
                    resultStr.append(results.get(i));
                    resultStr.append("\n");
                }
                Log.d("TagTest", "handlerResult: " + resultStr);
                break;
            case MNScanManager.RESULT_FAIL:
                String resultError = data.getStringExtra(MNScanManager.INTENT_KEY_RESULT_ERROR);
                Log.d("TagTest", "handlerResult: " + resultError);
                break;
            case MNScanManager.RESULT_CANCLE:
                Log.d("TagTest", "handlerResult: 取消扫码");
                break;
        }
    }
}