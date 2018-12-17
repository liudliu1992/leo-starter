package com.leo.util.http.common;

import com.leo.util.exception.HttpProcessException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * 设置ssl
 * 
 * @author arron
 * @date 2015年11月3日 下午3:11:54
 * @version 1.0
 */
public class SSLs {

	private static final SSLHandler SIMPLE_VERIFIER = new SSLHandler();
	private static SSLConnectionSocketFactory sslConnFactory ;
	private static SSLs SSL_UTIL = new SSLs();
	private SSLContext sc;

	public static SSLs getInstance(){
		return SSL_UTIL;
	}
	public static SSLs custom(){
		return new SSLs();
	}

	/**
	 * 重写X509TrustManager类的三个方法,信任服务器证书
	 */
	private static class SSLHandler implements  X509TrustManager, HostnameVerifier{

		@Override
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		@Override
		public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
									   String authType) throws CertificateException {
		}

		@Override
		public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
									   String authType) throws CertificateException {
		}

		@Override
		public boolean verify(String paramString, SSLSession paramSSLSession) {
			return true;
		}
	}

    /**
     *  信任主机
     * @return HostnameVerifier
     */
	public static HostnameVerifier getVerifier() {
		return SIMPLE_VERIFIER;
	}

	public synchronized SSLConnectionSocketFactory getSSLCONNSF() throws HttpProcessException {
		if (sslConnFactory != null) {
			return sslConnFactory;
		}
		try {
			SSLContext sc = getSSLContext();
			sc.init(null, new TrustManager[] {SIMPLE_VERIFIER}, null);
			sslConnFactory = new SSLConnectionSocketFactory(sc, SIMPLE_VERIFIER);
		} catch (KeyManagementException e) {
			throw new HttpProcessException(e);
		}
		return sslConnFactory;
	}

	public SSLs customSSL(String keyStorePath, String keyStorepass) throws HttpProcessException{
		FileInputStream inStream =null;
		KeyStore trustStore;
		try {
			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			inStream = new FileInputStream(new File(keyStorePath));
			trustStore.load(inStream, keyStorepass.toCharArray());
			// 相信自己的CA和所有自签名的证书
			sc= SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()) .build();
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | KeyManagementException e) {
			throw new HttpProcessException(e);
		}finally{
			try {
				assert inStream != null;
				inStream.close();
			} catch (IOException ignored) {}
		}
		return this;
	}

	public SSLContext getSSLContext() throws HttpProcessException{
		try {
			if(sc==null){
				sc = SSLContext.getInstance("SSLv3");
			}
			return sc;
		} catch (NoSuchAlgorithmException e) {
			throw new HttpProcessException(e);
		}
	}
}