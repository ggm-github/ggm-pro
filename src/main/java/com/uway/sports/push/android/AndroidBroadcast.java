package com.uway.sports.push.android;

import com.uway.sports.push.AndroidNotification;

public class AndroidBroadcast extends AndroidNotification {
	public AndroidBroadcast(String appkey, String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
	}
}
