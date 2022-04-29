package com.uway.sports.common.utils;

import sun.misc.BASE64Decoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class MiscUtils {

    public static String getBasePath(HttpServletRequest request) {
        String RequestUrl = request.getRequestURL().toString();
        String RequestURI = request.getRequestURI();

        return RequestUrl.replaceAll(RequestURI, "");
    }

    /**
     * base64 编码
     *
     * @param plain
     *            明文
     * @return
     */
    @SuppressWarnings("restriction")
    public static String base64Encode(byte[] plain) {
        return new sun.misc.BASE64Encoder().encode(plain);
    }

    /**
     * base64 解码
     *
     * @param cipher
     *            密文
     * @return
     */
    @SuppressWarnings("restriction")
    public static byte[] base64Decode(String cipher) {
        byte[] plain = null;
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            plain = decoder.decodeBuffer(cipher);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return plain;
    }

    /**
     * md5 32 位加密
     *
     * @param plain
     * @return
     */
    public static String MD532(String plain) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(plain.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            plain = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return plain;
    }

    /**
     * md5 16位 加密
     *
     * @param encryptStr
     * @return
     */
    public static String MD516(String encryptStr) {
        return MD532(encryptStr).substring(8, 24);
    }

    /**
     * AES加密
     *
     * @param
     *
     * @param encryptKey
     *            加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String plain, String encryptKey)
            throws Exception {
        byte[] raw = new byte[16];
        byte[] tempRaw = encryptKey.getBytes("utf-8");
        for (int i = 0; i < tempRaw.length; i++) {
            raw[i] = tempRaw[i];
        }

        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        return cipher.doFinal(plain.getBytes("utf-8"));
    }

    /**
     * AES解密
     *
     * @param cipherBytes
     *            待解密的byte[]
     * @param decryptKey
     *            解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] cipherBytes, String decryptKey)
            throws Exception {
        byte[] raw = new byte[16];
        byte[] tempRaw = decryptKey.getBytes("utf-8");
        for (int i = 0; i < tempRaw.length; i++) {
            raw[i] = tempRaw[i];
        }

        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] original = cipher.doFinal(cipherBytes);
        return new String(original, "utf-8");
    }

    /**
     * 1、进行AES加密； 2、将加密后的密文进行base64转码；
     *
     * @param plain
     * @param encryptKey
     * @return
     * @throws Exception
     */
    public static String aesAndBase64Encode(String plain, String encryptKey)
            throws Exception {
        return base64Encode(aesEncryptToBytes(plain, encryptKey));
    }

    /**
     * 1、进行base64解码；2、进行AES解密；
     *
     * @param cipher
     * @param decryptKey
     * @return
     * @throws Exception
     */
    public static String base64AndAesDecode(String cipher, String decryptKey)
            throws Exception {
        return aesDecryptByBytes(base64Decode(cipher), decryptKey);
    }


    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static final String PUBLIC_KEY = "JFPublicKey";
    private static final String PRIVATE_KEY = "JFPrivateKey";
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 生成密钥对(公钥和私钥)
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 用私钥对信息生成数字签名
     * @param data 已加密数据
     * @param privateKey 私钥(BASE64编码)
     *
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = base64Decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        java.security.Signature signature = java.security.Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return base64Encode(signature.sign());
    }

    /**
     * 校验数字签名
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     *
     * @return
     * @throws Exception
     *
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        byte[] keyBytes = base64Decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        java.security.Signature signature = java.security.Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(base64Decode(sign));
    }

    /**
     * 私钥解密
     * @param encryptedData 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)
            throws Exception {
        byte[] keyBytes = base64Decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * 公钥解密
     * @param encryptedData 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)
            throws Exception {
        byte[] keyBytes = base64Decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        byte[] keyBytes = base64Decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * 私钥加密
     * @param data 源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey)
            throws Exception {
        byte[] keyBytes = base64Decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * 获取私钥
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return base64Encode(key.getEncoded());
    }

    /**
     * 获取公钥
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return base64Encode(key.getEncoded());
    }


    /**
     * 测试
     * @param
     * @throws Exception
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    public static void main() throws Exception{

        Map<String, Object> keyMap = genKeyPair();
        String publicKey = getPublicKey(keyMap);
        String privateKey = getPrivateKey(keyMap);
        System.err.println("公钥: \n\r" + publicKey);
        System.err.println("私钥： \n\r" + privateKey);

        System.err.println("公钥加密——私钥解密");
        String source = "这是一行要进行RSA加密的原始数据&100";
        System.out.println("\r加密前文字：\r\n" + source);
        byte[] data = source.getBytes();
        //公钥加密
        byte[] encodedData = encryptByPublicKey(data, publicKey);
        System.out.println("加密后文字：\r\n" + new String(encodedData));
        //私钥解密
        byte[] decodedData = decryptByPrivateKey(encodedData, privateKey);
        String target = new String(decodedData);
        System.out.println("解密后文字: \r\n" + target);

        System.err.println("私钥加密——公钥解密");
        String source1 = "my Test -----200---测试RSA数字签名的原始数据";
        System.out.println("原文字：\r\n" + source1);
        byte[] data1 = source1.getBytes();
        //私钥加密
        byte[] encodedData1 = encryptByPrivateKey(data1, privateKey);
        System.out.println("加密后：\r\n" + new String(encodedData1));
        //公钥解密
        byte[] decodedData1 = decryptByPublicKey(encodedData1, publicKey);
        String target1 = new String(decodedData1);
        System.out.println("解密后: \r\n" + target1);

        System.err.println("私钥签名——公钥验证签名");
        String sign = sign(encodedData1, privateKey);
        System.err.println("签名:\r" + sign);
        boolean status = verify(encodedData1, publicKey, sign);
        System.err.println("验证结果:\r" + status);

    }


    /**
     * 获取MD5加密
     *
     * @param pwd
     *            需要加密的字符串
     * @return String字符串 加密后的字符串
     */
    public static String MD5(String pwd) {
        try {
            // 创建加密对象
            MessageDigest digest = MessageDigest.getInstance("md5");
            // 调用加密对象的方法，加密的动作已经完成
            byte[] bs = new byte[]{};

            try {
                bs = digest.digest(pwd.getBytes("utf-8"));
            }catch (Exception e){

            }
            // 接下来，我们要对加密后的结果，进行优化，按照mysql的优化思路走
            // mysql的优化思路：
            // 第一步，将数据全部转换成正数：
            String hexString = "";
            for (byte b : bs) {
                // 第一步，将数据全部转换成正数：
                // 解释：为什么采用b&255
                /*
                 * b:它本来是一个byte类型的数据(1个字节) 255：是一个int类型的数据(4个字节)
                 * byte类型的数据与int类型的数据进行运算，会自动类型提升为int类型 eg: b: 1001 1100(原始数据)
                 * 运算时： b: 0000 0000 0000 0000 0000 0000 1001 1100 255: 0000
                 * 0000 0000 0000 0000 0000 1111 1111 结果：0000 0000 0000 0000
                 * 0000 0000 1001 1100 此时的temp是一个int类型的整数
                 */
                int temp = b & 255;
                // 第二步，将所有的数据转换成16进制的形式
                // 注意：转换的时候注意if正数>=0&&<16，那么如果使用Integer.toHexString()，可能会造成缺少位数
                // 因此，需要对temp进行判断
                if (temp < 16 && temp >= 0) {
                    // 手动补上一个“0”
                    hexString = hexString  + "0" +  Integer.toHexString(temp);
                } else {
                    hexString = hexString  + Integer.toHexString(temp);
                }
            }
            return hexString;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 解密方法
     *
     * @param DESkey
     *            DES加密的私钥
     * @param data
     *            需要进行解密的秘闻
     * @return URL 解密后的内容
     */
    public static String KMdecode(byte[] DESkey, String data) throws Exception {
        DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
        byte[] DESIV = { 0, 0, 0, 0, 0,0, 0, 0 };// 设置向量
        AlgorithmParameterSpec iv = new IvParameterSpec(DESIV);// 加密算法的参数接口，IvParameterSpec是它的一个实现
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
        SecretKey key = keyFactory.generateSecret(keySpec);// 得到密钥对象

        Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, key, iv);
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
        return new String(pasByte, "UTF-8");
    }

    /**
     * 汉字 转换为对应的 UTF-8编码
     * @param s 木
     * @return E69CA8
     */
    public static String convertStringToUTF8(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        try {
            char c;
            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                if (c >= 0 && c <= 255) {
                    sb.append(c);
                } else {
                    byte[] b;
                    b = Character.toString(c).getBytes("utf-8");
                    for (int j = 0; j < b.length; j++) {
                        int k = b[j];
                        //转换为unsigned integer  无符号integer
                    /*if (k < 0)
                        k += 256;*/
                        k = k < 0? k+256:k;
                        //返回整数参数的字符串表示形式 作为十六进制（base16）中的无符号整数
                        //该值以十六进制（base16）转换为ASCII数字的字符串
                        sb.append(Integer.toHexString(k).toUpperCase());

                        // url转置形式
                        // sb.append("%" +Integer.toHexString(k).toUpperCase());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
