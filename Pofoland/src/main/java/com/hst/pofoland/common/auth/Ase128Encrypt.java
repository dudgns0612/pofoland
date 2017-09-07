package com.hst.pofoland.common.auth;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.hst.pofoland.common.utils.LoggerManager;

public class Ase128Encrypt {
	
    private String ips;
    private Key keySpec;
	
	public void createEncryptKey(String key) {
		 try {
	            byte[] keyBytes = new byte[16];
	            byte[] b = key.getBytes("UTF-8");

	            System.arraycopy(b, 0, keyBytes, 0, keyBytes.length);
	            
	            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
	            this.ips = key.substring(0, 16);
	            this.keySpec = keySpec;
	        } catch (UnsupportedEncodingException e) {
	        	LoggerManager.error(getClass(), "ERROR : {}", e);
	        }

	}
	
	public String encode(String encodeMsg){
		Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(ips.getBytes()));

            byte[] encrypted = cipher.doFinal(encodeMsg.getBytes("UTF-8"));
            String str = new String(Base64.encodeBase64(encrypted));

            return str;
        } catch (NoSuchAlgorithmException e) {
        	LoggerManager.error(getClass(), "ERROR : {}", e.toString());
        } catch (InvalidKeyException e) {
        	LoggerManager.error(getClass(), "ERROR : {}", e.toString());
        } catch (InvalidAlgorithmParameterException e) {
        	LoggerManager.error(getClass(), "ERROR : {}", e.toString());
        } catch (NoSuchPaddingException e) {
        	LoggerManager.error(getClass(), "ERROR : {}", e.toString());
        } catch (IllegalBlockSizeException e) {
        	LoggerManager.error(getClass(), "ERROR : {}", e.toString());
        } catch (BadPaddingException e) {
        	LoggerManager.error(getClass(), "ERROR : {}", e.toString());
        } catch (UnsupportedEncodingException e) {
        	LoggerManager.error(getClass(), "ERROR : {}", e.toString());
        }
        return null;

	}
	
	public String decode(String decodeMsg){
		try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec,
                    new IvParameterSpec(ips.getBytes("UTF-8")));

            byte[] byteStr = Base64.decodeBase64(decodeMsg.getBytes());
            String Str = new String(cipher.doFinal(byteStr), "UTF-8");

            return Str;
        } catch (NoSuchAlgorithmException e) {
        	LoggerManager.error(getClass(), "ERROR : {}", e.toString());
        } catch (NoSuchPaddingException e) {
        	LoggerManager.error(getClass(), "ERROR : {}", e.toString());
        } catch (InvalidKeyException e) {
        	LoggerManager.error(getClass(), "ERROR : {}", e.toString());
        } catch (InvalidAlgorithmParameterException e) {
        	LoggerManager.error(getClass(), "ERROR : {}", e.toString());
        } catch (UnsupportedEncodingException e) {
        	LoggerManager.error(getClass(), "ERROR : {}", e.toString());
        } catch (IllegalBlockSizeException e) {
        	LoggerManager.error(getClass(), "ERROR : {}", e.toString());
        } catch (BadPaddingException e) {
        	LoggerManager.error(getClass(), "ERROR : {}", e.toString());
        }
        return null;

	}
	
	
}
