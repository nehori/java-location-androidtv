/**
 * Copyright (c) 2015 Kazutaka Yasuda
 * Released under the MIT license
 * http://opensource.org/licenses/mit-license.php
 */
package com.nehori.device_location;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {
         
    private LocationManager mLocationManager = null;
    private String mProvider = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Criteriaオブジェクトを生成
        Criteria criteria = new Criteria();

        // LocationManagerを取得
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 位置情報機能非搭載端末の場合
        if (mLocationManager == null) {
                Toast.makeText(this, "mLocationManager = null", Toast.LENGTH_SHORT).show();
                return;
        }
        // ロケーションプロバイダの取得
        mProvider = mLocationManager.getBestProvider(criteria, true);

        // 取得したロケーションプロバイダを表示
        Toast.makeText(this, "Provider: " + mProvider, Toast.LENGTH_SHORT).show();
        Log.d("Location", "Provider: " + mProvider); 
    }

    @Override
    public void onResume() {
        super.onResume();
        // LocationListenerの更新
        if (mProvider != null) {
            mLocationManager.requestLocationUpdates(mProvider, 0, 0, this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // LocationListenerの解除
        mLocationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        // 緯度の表示
        Toast.makeText(this, "Latitude:" + location.getLatitude(), Toast.LENGTH_SHORT).show();
        Log.d("Location", "Latitude:" + location.getLatitude()); 

        // 経度の表示
        Toast.makeText(this, "Longitude:" + location.getLongitude(), Toast.LENGTH_SHORT).show();
        Log.d("Location", "Longitude:" + location.getLongitude()); 

        // 方位の表示
        Toast.makeText(this, "Bearing:" + location.getBearing(), Toast.LENGTH_SHORT).show();
        Log.d("Location", "Bearing:" + location.getBearing()); 

        // 精度の表示
        Toast.makeText(this, "Accuracy(m):" + location.getAccuracy(), Toast.LENGTH_SHORT).show();
        Log.d("Location", "Accuracy:" + location.getAccuracy()); 

        // 時間の表示
        Toast.makeText(this, "Time:" + location.getTime(), Toast.LENGTH_SHORT).show();
        Log.d("Location", "Time:" + location.getTime()); 

        // 標高の表示
        Toast.makeText(this, "Altitude:" + location.getAltitude(), Toast.LENGTH_SHORT).show();
        Log.d("Location", "Altitude:" + location.getAltitude()); 

        // 速度の表示
        Toast.makeText(this, "Speed:" + location.getSpeed(), Toast.LENGTH_SHORT).show();
        Log.d("Location", "Speed" + location.getSpeed()); 
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
}
