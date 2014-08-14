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

	// View for Device Info
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

	// get number
	private String getNumber(TelephonyManager mn) {
		String number = "0000000000";

		number = mn.getLine1Number();

		return "Your mobile Number is: " + number;
	}

	// get Provider
	private String getProvider(TelephonyManager mn) {
		String provider = "Your network Provider info: "
				+ mn.getNetworkOperatorName() + '/' + mn.getNetworkOperator()
				+ '/' + mn.getCellLocation();
		return provider;
	}

	// get Mobile Network Type
	private String getDataType() {

		TelephonyManager mn = connection();

		int data = mn.getNetworkType();

		switch (data) {
		case TelephonyManager.NETWORK_TYPE_1xRTT:
			dataType = "Your Mobile Data Network is 1xRTT";
			break;
		case TelephonyManager.NETWORK_TYPE_CDMA:
			dataType = "Your Mobile Data Network is CDMA";
			break;
		case TelephonyManager.NETWORK_TYPE_EDGE:
			dataType = "Your Mobile Data Network is EDGE";
			break;
		case TelephonyManager.NETWORK_TYPE_EHRPD:
			dataType = "Your Mobile Data Network is eHRPD";
			break;
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
			dataType = "Your Mobile Data Network is EVDO rev. 0";
			break;
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
			dataType = "Your Mobile Data Network is EVDO rev. A";
			break;
		case TelephonyManager.NETWORK_TYPE_EVDO_B:
			dataType = "Your Mobile Data Network is EVDO rev. B";
			break;
		case TelephonyManager.NETWORK_TYPE_GPRS:
			dataType = "Your Mobile Data Network is GPRS";
			break;
		case TelephonyManager.NETWORK_TYPE_HSDPA:
			dataType = "Your Mobile Data Network is HSDPA";
			break;
		case TelephonyManager.NETWORK_TYPE_HSPA:
			dataType = "Your Mobile Data Network is HSPA";
			break;
		case TelephonyManager.NETWORK_TYPE_HSPAP:
			dataType = "Your Mobile Data Network is HSPA+";
			break;
		case TelephonyManager.NETWORK_TYPE_HSUPA:
			dataType = "Your Mobile Data Network is HSUPA";
			break;
		case TelephonyManager.NETWORK_TYPE_IDEN:
			dataType = "Your Mobile Data Network is iDen";
			break;
		case TelephonyManager.NETWORK_TYPE_LTE:
			dataType = "Your Mobile Data Network is LTE";
			break;
		case TelephonyManager.NETWORK_TYPE_UMTS:
			dataType = "Your Mobile Data Network is UMTS";
			break;
		case TelephonyManager.NETWORK_TYPE_UNKNOWN:
			dataType = "Your Mobile Data Network is unknown";
			break;
		}
		return dataType;
	}

	// make connection
	private TelephonyManager connection() {
		TelephonyManager mn = (TelephonyManager) getActivity()
				.getSystemService(Context.TELEPHONY_SERVICE);
		return mn;
	}

	// get device name
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
