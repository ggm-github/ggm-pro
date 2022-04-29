package com.uway.sports.push.ios;

import com.uway.sports.push.IOSNotification;

public class IOSBroadcast extends IOSNotification {
	public IOSBroadcast(String appkey, String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
		
	}
}
