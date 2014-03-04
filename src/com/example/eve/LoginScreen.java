package com.example.eve;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;


import java.io.File;
import net.sqlcipher.database.SQLiteDatabase;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class LoginScreen extends Activity {

	 public  static Key PUBLIC_KEY = null;
	 public  static Key PRIVATE_KEY = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);
		  
	}
	
	 private void InitializeSQLCipher(String pass) {
	        SQLiteDatabase.loadLibs(this);
	        File databaseFile = getDatabasePath("demo.db");
	        databaseFile.mkdirs();
	        databaseFile.delete();
	        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, pass, null);
	        database.execSQL("create table t1(a, b)");
	        database.execSQL("insert into t1(a, b) values(?, ?)", new Object[]{"one for the money",
	                                                                        "two for the show"});
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_screen, menu);
		return true;
	}
	public void setLogin(View view) throws NoSuchAlgorithmException, InvalidKeySpecException {
	    // Do something in response to button
		
	/*	KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair kp = kpg.genKeyPair();
		Key publicKey = kp.getPublic();
		Key privateKey = kp.getPrivate();
		PUBLIC_KEY = publicKey;
		PRIVATE_KEY = privateKey;
		*/
		
		/*KeyFactory fact = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec pub = fact.getKeySpec(kp.getPublic(),
		  RSAPublicKeySpec.class);
		RSAPrivateKeySpec priv = fact.getKeySpec(kp.getPrivate(),
		  RSAPrivateKeySpec.class);*/
		
		Intent intent = new Intent(this, Messenger.class);
		EditText editText = (EditText) findViewById(R.id.editText1);
		String pass = editText.getText().toString();
		
				
		if ("test123".equals(pass)){
		InitializeSQLCipher(pass);
				  
		//intent.putExtra("PUB_KEY_MOD",  pub.getModulus().toByteArray());
		 //intent.putExtra("PUB_KEY_EXP", pub.getPublicExponent().toByteArray());
		 
		 
		startActivity(intent);
		}

		
	}
}
