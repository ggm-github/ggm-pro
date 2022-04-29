package com.uway.sports.push;

import com.uway.sports.common.config.Global;
import com.uway.sports.common.utils.JSONUtils;
import com.uway.sports.push.android.*;
import com.uway.sports.push.ios.*;
import com.uway.sports.web.entity.paraentity.PushMessage;
import org.json.JSONArray;
import org.json.JSONObject;

public class PushService {
    private String appkey = null;
    private String appMasterSecret = null;
    private String timestamp = null;
    private PushClient client = new PushClient();

    public PushService(String key, String secret) {
        try {
            appkey = key;
            appMasterSecret = secret;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void sendAndroidBroadcast(PushMessage condition) throws Exception {
        AndroidBroadcast broadcast = new AndroidBroadcast(appkey,appMasterSecret);
        //broadcast.setTicker( "Android broadcast ticker");
        broadcast.setTitle(condition.getTitle());
        broadcast.setText(condition.getBody());
        broadcast.setPlayVibrate(true);

        if("0".equals(condition.getType())){
            broadcast.goUrlAfterOpen(condition.getUrl());
        } else if("1".equals(condition.getType())){
            broadcast.goAppAfterOpen();
            broadcast.setExtraField("modelId",condition.getModelId());
            broadcast.setExtraField("controller",condition.getActivity());
        }
        if(!"".equals(condition.getImageurl())) {
            broadcast.setExpand_image(Global.getConfig("prefix.visit.path") + condition.getImageurl());
        }
        broadcast.setmipush("true");
        broadcast.setMiactivity("com.caochang.sports.push.MipushTestActivity");
        broadcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        broadcast.setProductionMode();
        if ("1".equals(Global.getConfig("com.uway.isUmengTest"))){
            broadcast.setTestMode();
        }
        System.out.println("======================" + JSONUtils.toJson(broadcast));
        client.send(broadcast);
    }

    public void sendAndroidUnicast(PushMessage condition) throws Exception {
        AndroidUnicast unicast = new AndroidUnicast(appkey,appMasterSecret);
        // TODO Set your device token
        unicast.setDeviceToken(condition.getAndroidDeviceIds());
        unicast.setTicker( "Android unicast ticker");
        unicast.setTitle(condition.getTitle());
        unicast.setText(condition.getBody());
        if("0".equals(condition.getType())){
            unicast.goUrlAfterOpen(condition.getUrl());
        }
        else if("1".equals(condition.getType())){
            unicast.goActivityAfterOpen(condition.getActivity());
        }
        if(!"".equals(condition.getImageurl())) {
            unicast.setExpand_image(condition.getImageurl());
        }
        unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        unicast.setmipush("true");
        unicast.setMiactivity("com.caochang.sports.push.MipushTestActivity");
        // TODO Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        unicast.setProductionMode();
        if ("1".equals(Global.getConfig("com.uway.isUmengTest"))){
            unicast.setTestMode();
        }
        //unicast.setExtraField("test", "helloworld");
        client.send(unicast);
    }

    public void sendAndroidListcast(PushMessage condition) throws Exception {
        AndroidListcast unicast = new AndroidListcast(appkey,appMasterSecret);
        // TODO Set your device token
        unicast.setDeviceToken(condition.getAndroidDeviceIds());
        //unicast.setTicker( "Android unicast ticker");
        unicast.setTitle(condition.getTitle());
        unicast.setText(condition.getBody());
        if("0".equals(condition.getType())){
            unicast.goUrlAfterOpen(condition.getUrl());
        }
        else if("1".equals(condition.getType())){
            unicast.goActivityAfterOpen(condition.getActivity());
        }
        if(!"".equals(condition.getImageurl())) {
            unicast.setExpand_image(condition.getImageurl());
        }
        unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        unicast.setmipush("true");
        unicast.setMiactivity("com.caochang.sports.push.MipushTestActivity");
        // TODO Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        unicast.setProductionMode();
        if ("1".equals(Global.getConfig("com.uway.isUmengTest"))){
            unicast.setTestMode();
        }
        //unicast.setExtraField("test", "helloworld");
        client.send(unicast);
    }

    public void sendAndroidGroupcast() throws Exception {
        AndroidGroupcast groupcast = new AndroidGroupcast(appkey,appMasterSecret);
		/*  TODO
		 *  Construct the filter condition:
		 *  "where":
		 *	{
    	 *		"and":
    	 *		[
      	 *			{"tag":"test"},
      	 *			{"tag":"Test"}
    	 *		]
		 *	}
		 */
        JSONObject filterJson = new JSONObject();
        JSONObject whereJson = new JSONObject();
        JSONArray tagArray = new JSONArray();
        JSONObject testTag = new JSONObject();
        JSONObject TestTag = new JSONObject();
        testTag.put("tag", "test");
        TestTag.put("tag", "Test");
        tagArray.put(testTag);
        tagArray.put(TestTag);
        whereJson.put("and", tagArray);
        filterJson.put("where", whereJson);
        System.out.println(filterJson.toString());

        groupcast.setFilter(filterJson);
        groupcast.setTicker( "Android groupcast ticker");
        groupcast.setTitle(  "中文的title");
        groupcast.setText(   "Android groupcast text");
        groupcast.goAppAfterOpen();
        groupcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // TODO Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        groupcast.setProductionMode();
        client.send(groupcast);
    }

    public void sendAndroidCustomizedcast(PushMessage condition) throws Exception {
        AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey,appMasterSecret);
        // TODO Set your alias here, and use comma to split them if there are multiple alias.
        // And if you have many alias, you can also upload a file containing these alias, then
        // use file_id to send customized notification.
        customizedcast.setAlias(condition.getAlias(), "userId");
        //customizedcast.setTicker( "Android customizedcast ticker");
        customizedcast.setTitle(condition.getTitle());
        customizedcast.setText(condition.getBody());
        customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        customizedcast.setmipush("true");
        customizedcast.setMiactivity("com.caochang.sports.push.MipushTestActivity");
        if("0".equals(condition.getType())){
            customizedcast.goUrlAfterOpen(condition.getUrl());
        }
        else if("1".equals(condition.getType())){
            customizedcast.goAppAfterOpen();
            customizedcast.setExtraField("modelId",condition.getModelId());
            customizedcast.setExtraField("controller",condition.getActivity());
        }
        if(!"".equals(condition.getImageurl())) {
            customizedcast.setExpand_image(Global.getConfig("prefix.visit.path") + condition.getImageurl());
        }
        // TODO Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        customizedcast.setProductionMode();
        if ("1".equals(Global.getConfig("com.uway.isUmengTest"))){
            customizedcast.setTestMode();
        }
        System.out.println("=================" + JSONUtils.toJson(customizedcast));
        client.send(customizedcast);
    }

    public void sendAndroidCustomizedcastFile() throws Exception {
        AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey,appMasterSecret);
        // TODO Set your alias here, and use comma to split them if there are multiple alias.
        // And if you have many alias, you can also upload a file containing these alias, then
        // use file_id to send customized notification.
        String fileId = client.uploadContents(appkey,appMasterSecret,"aa"+"\n"+"bb"+"\n"+"alias");
        customizedcast.setFileId(fileId, "alias_type");
        customizedcast.setTicker( "Android customizedcast ticker");
        customizedcast.setTitle(  "中文的title");
        customizedcast.setText(   "Android customizedcast text");
        customizedcast.goAppAfterOpen();
        customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // TODO Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        customizedcast.setProductionMode();
        client.send(customizedcast);
    }

    public void sendAndroidFilecast() throws Exception {
        AndroidFilecast filecast = new AndroidFilecast(appkey,appMasterSecret);
        // TODO upload your device tokens, and use '\n' to split them if there are multiple tokens
        String fileId = client.uploadContents(appkey,appMasterSecret,"aa"+"\n"+"bb");
        filecast.setFileId( fileId);
        filecast.setTicker( "Android filecast ticker");
        filecast.setTitle(  "中文的title");
        filecast.setText(   "Android filecast text");
        filecast.goAppAfterOpen();
        filecast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        client.send(filecast);
    }

    public void sendIOSBroadcast(PushMessage condition) throws Exception {
        IOSBroadcast broadcast = new IOSBroadcast(appkey,appMasterSecret);
        broadcast.setTitle(condition.getTitle());
        broadcast.setSubtitle("");
        broadcast.setBody(condition.getBody());
        broadcast.setBadge(1);
        broadcast.setSound( "default");
        broadcast.setDescription("操场推送");

        if(!"".equals(condition.getImageurl())){
            broadcast.setImage(Global.getConfig("prefix.visit.path")+condition.getImageurl());
            broadcast.setMutablecontent("1");
        }
        broadcast.setCategory("category");
        //broadcast.setContentAvailable(1);
        // TODO set 'production_mode' to 'true' if your app is under production mode
        broadcast.setProductionMode();
        if ("1".equals(Global.getConfig("com.uway.isUmengTest"))){
            broadcast.setTestMode();
        }
        if("0".equals(condition.getType())){
            broadcast.setCustomizedField("url", condition.getUrl());
        } else if("1".equals(condition.getType())){
            broadcast.setCustomizedField("controller", condition.getActivity());
            broadcast.setCustomizedField("modelId",condition.getModelId());
        }

        client.send(broadcast);
    }

    public void sendIOSUnicast(PushMessage condition) throws Exception {
        IOSUnicast unicast = new IOSUnicast(appkey,appMasterSecret);
        // TODO Set your device token
        unicast.setDeviceToken(condition.getAndroidDeviceIds());
        //unicast.setAlert("IOS 单播测试");
        unicast.setBadge(0);
        unicast.setSound("default");
        // TODO set 'production_mode' to 'true' if your app is under production mode
        unicast.setProductionMode();
        if ("1".equals(Global.getConfig("com.uway.isUmengTest"))){
            unicast.setTestMode();
        }
        if("0".equals(condition.getType())){
            unicast.setCustomizedField("url", condition.getUrl());
        }
        else if("1".equals(condition.getType())){
            unicast.setCustomizedField("controller", condition.getActivity());
        }
        // Set customized fields
        //unicast.setCustomizedField("test", "helloworld");
        client.send(unicast);
    }

    public void sendIOSListcast(PushMessage condition) throws Exception {
        IOSListcast unicast = new IOSListcast(appkey,appMasterSecret);
        // TODO Set your device token
        unicast.setDeviceToken(condition.getIosDeviceIds());
        //unicast.setAlert("IOS 单播测试");
        unicast.setBadge(0);
        unicast.setSound("default");
        // TODO set 'production_mode' to 'true' if your app is under production mode
        unicast.setProductionMode();
        if ("1".equals(Global.getConfig("com.uway.isUmengTest"))){
            unicast.setTestMode();
        }
        if("0".equals(condition.getType())){
            unicast.setCustomizedField("url", condition.getUrl());
        } else if("1".equals(condition.getType())){
            unicast.setCustomizedField("controller", condition.getActivity());
        }
        // Set customized fields
        //unicast.setCustomizedField("test", "helloworld");
        client.send(unicast);
    }

    public void sendIOSGroupcast() throws Exception {
        IOSGroupcast groupcast = new IOSGroupcast(appkey,appMasterSecret);
		/*  TODO
		 *  Construct the filter condition:
		 *  "where":
		 *	{
    	 *		"and":
    	 *		[
      	 *			{"tag":"iostest"}
    	 *		]
		 *	}
		 */
        JSONObject filterJson = new JSONObject();
        JSONObject whereJson = new JSONObject();
        JSONArray tagArray = new JSONArray();
        JSONObject testTag = new JSONObject();
        testTag.put("tag", "iostest");
        tagArray.put(testTag);
        whereJson.put("and", tagArray);
        filterJson.put("where", whereJson);
        System.out.println(filterJson.toString());

        // Set filter condition into rootJson
        groupcast.setFilter(filterJson);
        groupcast.setAlert("IOS 组播测试");
        groupcast.setBadge( 0);
        groupcast.setSound( "default");
        // TODO set 'production_mode' to 'true' if your app is under production mode
        groupcast.setTestMode();
        client.send(groupcast);
    }

    public void sendIOSCustomizedcast(PushMessage condition) throws Exception {
        IOSCustomizedcast customizedcast = new IOSCustomizedcast(appkey,appMasterSecret);
        // TODO Set your alias and alias_type here, and use comma to split them if there are multiple alias.
        // And if you have many alias, you can also upload a file containing these alias, then
        // use file_id to send customized notification.
        customizedcast.setAlias(condition.getAlias(), "userId");
        customizedcast.setTitle(condition.getTitle());
        customizedcast.setSubtitle("");
        customizedcast.setBody(condition.getBody());
        //customizedcast.setAlert("IOS 个性化测试");
        customizedcast.setBadge( 0);
        customizedcast.setSound( "default");
        if(!"".equals(condition.getImageurl())){
            customizedcast.setImage(Global.getConfig("prefix.visit.path") + condition.getImageurl());
            customizedcast.setMutablecontent("1");
        }
        // TODO set 'production_mode' to 'true' if your app is under production mode
        customizedcast.setProductionMode();
        if ("1".equals(Global.getConfig("com.uway.isUmengTest"))){
            customizedcast.setTestMode();
        }
        if("0".equals(condition.getType())){
            customizedcast.setCustomizedField("url", condition.getUrl());
        }
        else if("1".equals(condition.getType())){
            customizedcast.setCustomizedField("controller", condition.getActivity());
            customizedcast.setCustomizedField("modelId",condition.getModelId());
        }
        client.send(customizedcast);
    }

    public void sendIOSFilecast() throws Exception {
        IOSFilecast filecast = new IOSFilecast(appkey,appMasterSecret);
        // TODO upload your device tokens, and use '\n' to split them if there are multiple tokens
        String fileId = client.uploadContents(appkey,appMasterSecret,"aa"+"\n"+"bb");
        filecast.setFileId( fileId);
        filecast.setAlert("IOS 文件播测试");
        filecast.setBadge( 0);
        filecast.setSound( "default");
        // TODO set 'production_mode' to 'true' if your app is under production mode
        filecast.setTestMode();
        client.send(filecast);
    }
}
