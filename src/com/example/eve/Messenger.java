package com.example.eve;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Messenger extends Activity {
	
	private Key  pubKey = null; 
	private Key privKey = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
//		String pubKey = intent.getStringExtra("PUB_KEY");
		
		
		KeyPairGenerator kpg;
		try {
			kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(1024);
			KeyPair kp = kpg.genKeyPair();
			Key publicKey = kp.getPublic();
			Key privateKey = kp.getPrivate();
			this.pubKey = publicKey;
			this.privKey = privateKey;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
		
				
		
		setContentView(R.layout.activity_messenger);
		
		
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.messenger, menu);
		return true;
	}
	
	public void sendMessage(View view) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
		EditText editText = (EditText) findViewById(R.id.editText1);
		EditText msgText = (EditText) findViewById(R.id.multiLine);
		
		
		String message = editText.getText().toString();
		editText.setText("");
		
		byte[] msgByte = message.getBytes();
		
		byte[] msgEncryptedByte = rsaEncrypt(msgByte);
		
		
		
		String msg = new String(msgEncryptedByte,"ASCII");
		String msgD = new String(rsaDecrypt(msgEncryptedByte));
		
		msgText.append("\n"+msg + " ---> " + msgD);
			
		
	}
	
	public byte[] rsaEncrypt(byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		  
		  Cipher cipher = Cipher.getInstance("RSA");
		  cipher.init(Cipher.ENCRYPT_MODE, this.pubKey);
		  byte[] cipherData = cipher.doFinal(data);
		  return cipherData;
	}
	public byte[] rsaDecrypt(byte[] data) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		  Cipher cipher = Cipher.getInstance("RSA");
		  cipher.init(Cipher.DECRYPT_MODE,this.privKey );
		  byte[] cipherData = cipher.doFinal(data);
		  return cipherData;
		}

}
