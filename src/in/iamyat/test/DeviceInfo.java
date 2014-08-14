package in.iamyat.test;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DeviceInfo extends Fragment {

	String dataType;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater
				.inflate(R.layout.info_device, container, false);

		TelephonyManager mn = connection();

		String number = getNumber(mn);

		String provider = getProvider(mn);

		TextView mobileNumber = (TextView) rootView
				.findViewById(R.id.mobileNumber);
		mobileNumber.setText(number);

		TextView networkProvider = (TextView) rootView
				.findViewById(R.id.networkProvider);
		networkProvider.setText(provider);

		TextView mobileData = (TextView) rootView.findViewById(R.id.mobileData);
		mobileData.setText(getDataType());

		TextView deviceLog = (TextView) rootView.findViewById(R.id.deviceLog);
		deviceLog.setText(getDeviceName());

		return rootView;

	}

	private String getNumber(TelephonyManager mn) {
		String number = "Your mobile Number is: " + mn.getLine1Number();
		return number;
	}

	private String getProvider(TelephonyManager mn) {
		String provider = "Your network Provider info: "
				+ mn.getNetworkOperatorName() + '/' + mn.getNetworkOperator()
				+ '/' + mn.getCellLocation();
		return provider;
	}

	private String getDataType() {

		TelephonyManager mn = connection();

		int data = mn.getNetworkType();

		switch (data) {
		case 7:
			dataType = "Your Mobile Data Network is 1xRTT";
			break;
		case 4:
			dataType = "Your Mobile Data Network is CDMA";
			break;
		case 2:
			dataType = "Your Mobile Data Network is EDGE";
			break;
		case 14:
			dataType = "Your Mobile Data Network is eHRPD";
			break;
		case 5:
			dataType = "Your Mobile Data Network is EVDO rev. 0";
			break;
		case 6:
			dataType = "Your Mobile Data Network is EVDO rev. A";
			break;
		case 12:
			dataType = "Your Mobile Data Network is EVDO rev. B";
			break;
		case 1:
			dataType = "Your Mobile Data Network is GPRS";
			break;
		case 8:
			dataType = "Your Mobile Data Network is HSDPA";
			break;
		case 10:
			dataType = "Your Mobile Data Network is HSPA";
			break;
		case 15:
			dataType = "Your Mobile Data Network isHSPA+";
			break;
		case 9:
			dataType = "Your Mobile Data Network is HSUPA";
			break;
		case 11:
			dataType = "Your Mobile Data Network is iDen";
			break;
		case 13:
			dataType = "Your Mobile Data Network is LTE";
			break;
		case 3:
			dataType = "Your Mobile Data Network is UMTS";
			break;
		case 0:
			dataType = "Your Mobile Data Network is Unknown";
			break;
		}
		return dataType;
	}

	private TelephonyManager connection() {
		TelephonyManager mn = (TelephonyManager) getActivity()
				.getSystemService(Context.TELEPHONY_SERVICE);
		return mn;
	}

	private String getDeviceName() {
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;

		if (model.startsWith(manufacturer)) {
			return capitalize(model);
		} else {
			return capitalize(manufacturer) + " " + model;
		}
	}

	private String capitalize(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		char first = s.charAt(0);
		if (Character.isUpperCase(first)) {
			return s;
		} else {
			return Character.toUpperCase(first) + s.substring(1);
		}
	}
}
