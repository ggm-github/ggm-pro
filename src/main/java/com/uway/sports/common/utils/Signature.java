package com.uway.sports.common.utils;

import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Signature {
    private String secretId;
    private String secretKey;
    private long currentTime;
    private int random;
    private int signValidDuration;
    private Integer userId;
    private Integer videoType;
    private Integer teamId;
    private Integer transcoding = 1;//是否需要转码 1需要 0不需要

    private static final String HMAC_ALGORITHM = "HmacSHA1";
    private static final String CONTENT_CHARSET = "UTF-8";

    public static byte[] byteMerger(byte[] byte1, byte[] byte2) {
        byte[] byte3 = new byte[byte1.length + byte2.length];
        System.arraycopy(byte1, 0, byte3, 0, byte1.length);
        System.arraycopy(byte2, 0, byte3, byte1.length, byte2.length);
        return byte3;
    }

    public String getUploadSignature() throws Exception {
        String strSign = "";
        String contextStr = "";

        long endTime = (currentTime + signValidDuration);
        contextStr += "secretId=" + java.net.URLEncoder.encode(secretId, "utf8");
        contextStr += "&currentTimeStamp=" + currentTime;
        contextStr += "&expireTime=" + endTime;
        contextStr += "&random=" + random;
        contextStr += "&sourceContext=" + userId + "_" + teamId + "_" + videoType;
        if (transcoding == 1) {
            contextStr += "&procedure=" + "workflow_1";
        } else {
            contextStr += "&procedure=" + "workflow_2";
        }


        try {
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(this.secretKey.getBytes(CONTENT_CHARSET), mac.getAlgorithm());
            mac.init(secretKey);

            byte[] hash = mac.doFinal(contextStr.getBytes(CONTENT_CHARSET));
            byte[] sigBuf = byteMerger(hash, contextStr.getBytes("utf8"));
            strSign = new String(new BASE64Encoder().encode(sigBuf).getBytes());
            strSign = strSign.replace(" ", "").replace("\n", "").replace("\r", "");
        } catch (Exception e) {
            throw e;
        }
        return strSign;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public void setSignValidDuration(int signValidDuration) {
        this.signValidDuration = signValidDuration;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public void setTranscoding(Integer transcoding) {
        this.transcoding = transcoding;
    }
}

