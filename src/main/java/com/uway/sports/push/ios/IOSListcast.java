package com.uway.sports.push.ios;

import com.uway.sports.push.IOSNotification;

public class IOSListcast extends IOSNotification {
	public IOSListcast(String appkey, String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "listcast");
	}
	
	public void setDeviceToken(String token) throws Exception {
    	setPredefinedKeyValue("device_tokens", token);
    }
}
