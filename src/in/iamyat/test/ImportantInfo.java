package in.iamyat.test;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ImportantInfo extends Fragment {
	// view for Important Status
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.info_important, container,
				false);

		// TextView for different functions
		TextView internetConnection = (TextView) rootView
				.findViewById(R.id.internetConnection);
		internetConnection.setText(getInternetConnection());

		TextView battery = (TextView) rootView.findViewById(R.id.battery);
		battery.setText(getBatteryStatus());

		TextView wifi = (TextView) rootView.findViewById(R.id.wifingps);
		wifi.setText(getInfo());

		return rootView;

	}

	// check Internet connection
	private String getInternetConnection() {
		if (isOnline()) {
			return "Your internet connection is working.";
		} else {
			return "Your internet coonection is not working.";
		}
	}
	
	//Get Battery Status
	private String getBatteryStatus() {
		return "Your Battery Level is: " + getMyBatteryLevel() + "%";
	}

	//Check Online Connection
	private boolean isOnline() {
		ConnectivityManager connection = connection();

		return connection.getActiveNetworkInfo() != null
				&& connection.getActiveNetworkInfo().isConnected();
	}

	private ConnectivityManager connection() {
		ConnectivityManager connection = (ConnectivityManager) getActivity()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		return connection;
	}
	
	//get battery level
	private int getMyBatteryLevel() {
		Intent batteryIntent = getActivity().getApplicationContext()
				.registerReceiver(null,
						new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		return batteryIntent.getIntExtra("level", -1);
	}

	//GPS and Wifi
	private String getInfo() {
		ConnectivityManager connection = connection();
		NetworkInfo mWifi = connection
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		final LocationManager manager = location();

		if (mWifi.isConnected()
				&& !manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			int per = getWifiStrength(getActivity());
			return "Your Wifi signal strength is " + per;
		} else if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
				&& !mWifi.isConnected()) {
			return "GPS is ON";

		} else if (!mWifi.isConnected()
				&& !manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			return "Your Wifi and GPS is off.";
		} else if (mWifi.isConnected()
				&& manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			int per = getWifiStrength(getActivity());
			return "Your Wifi signal strength is " + per
					+ " and GPS is also ON";
		}

		return null;
	}

	//Location Function
	private LocationManager location() {
		final LocationManager manager = (LocationManager) getActivity()
				.getSystemService(Context.LOCATION_SERVICE);
		return manager;
	}

	//Wifi Strength
	private static int getWifiStrength(Context context) {
		try {
			WifiManager wifiManager = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			int rssi = wifiManager.getConnectionInfo().getRssi();
			int level = WifiManager.calculateSignalLevel(rssi, 10);
			int percentage = (int) ((level / 10.0) * 100);
			return percentage;
		} catch (Exception e) {
			return 0;
		}
	}
}