package com.project.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MD5Util {
	public static String md5Encode(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(source.getBytes(StandardCharsets.UTF_8));
			return getHexString(bytes);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getHexString(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : bytes) {
			String hex = Integer.toHexString(0x00FF & b);
			if (hex.length() == 1) {
				stringBuilder.append("0");
			}
			stringBuilder.append(hex);
		}
		return stringBuilder.toString();
	}

	public static String hmacSHA256(String data, String key) {
		try {
			return hmacWithJava("HmacSHA256", data, key);
		} catch (Exception e) {
			return null;
		}
	}

	public static String hmacWithJava(String algorithm, String data, String key) throws Exception {
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
		Mac mac = Mac.getInstance(algorithm);
		mac.init(secretKeySpec);
		return bytesToHex(mac.doFinal(data.getBytes()));
	}

	public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte hashByte : bytes) {
            int intVal = 0xff & hashByte;
            if (intVal < 0x10) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(intVal));
        }
        return sb.toString();
    }
}
