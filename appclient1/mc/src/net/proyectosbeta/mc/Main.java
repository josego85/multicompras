package net.proyectosbeta.mc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Main extends ListActivity{
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listplaceholder);
        
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
      
       
        //JSONObject json = JSONfunctions.getJSONfromURL("http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username=demo");
       // JSONObject json = JSONfunctions.getJSONfromURL("mc.proyectosbeta.net/seguridad/login/bauerpy/123");   
        //JSONObject json = JSONfunctions.getJSONfromURL("http://localhost/ejemplo.json");
        
        try{
        	
        	//JSONArray  earthquakes = json.getJSONArray("earthquakes");
        	//JSONArray  usuarios = json.getJSONArray("usuario");
        	postData();
        	
        	/*
	        for(int i = 0; i < earthquakes.length(); i++){						
				HashMap<String, String> map = new HashMap<String, String>();	
				
				map.put("id",  String.valueOf(i));
	        	map.put("name", "Earthquake name:" + e.getString("eqid"));
	        	map.put("magnitude", "Magnitude: " +  e.getString("magnitude"));
	        	
	        	mylist.add(map);			
			}
			*/		
        }catch(Exception e)        {
        	 Log.e("log_tag", "Error parsing data "+e.toString());
        }
        /*
        ListAdapter adapter = new SimpleAdapter(this, mylist , R.layout.main_json1, 
                        new String[] { "name", "magnitude" }, 
                        new int[] { R.id.item_title, R.id.item_subtitle });
        
        setListAdapter(adapter);
        
        final ListView lv = getListView();
        Toast.makeText(Main.this, "Contador: " + lv.getCount() + " was clicked.", Toast.LENGTH_LONG).show(); 
        lv.setTextFilterEnabled(true);	
        lv.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {        		
        		@SuppressWarnings("unchecked")
				HashMap<String, String> o = (HashMap<String, String>) lv.getItemAtPosition(position);	        		
        		Toast.makeText(Main.this, "ID1: '" + o.get("id") + "' was clicked.", Toast.LENGTH_LONG).show(); 
        		//Toast.makeText(Main.this, "Name: " + o.get("name") + " was clicked.", Toast.LENGTH_LONG).show(); 
			}
		});
		*/
    }
    
    public void postData() {
    	HttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost("http://mc.proyectosbeta.net/seguridad/login/bauerpy/123");
    	
    	try { 
    		// Add your data
    		//List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
    	    //nameValuePairs.add(new BasicNameValuePair("username", un.getText().toString()));
    	    //nameValuePairs.add(new BasicNameValuePair("username", pw.getText().toString()));
    	    //httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));


    	    HttpResponse response = httpclient.execute(httppost);
    	    HttpEntity entity = response.getEntity();
    	    InputStream is = entity.getContent();
    	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    	    StringBuilder sb = new StringBuilder();

    	    String line = null;
    	    
    	    try {
    	    	while ((line = reader.readLine()) != null) {
    	    		sb.append((line + "\n"));
    	    		//Toast.makeText(Main.this, "La linea es: " + line, Toast.LENGTH_LONG).show();
    	        }
    	    }catch(IOException e) {
    	    	e.printStackTrace();
    	    }finally {
    	    	try {
    	    		is.close();
    	         }catch(IOException e){
    	        	 e.printStackTrace();
    	         }
    	    }

    	    try {
    	    	String jsonStr = "{'usuario':" + sb.toString() + "}";   	     	    
    	    	JSONObject jsonObject = new JSONObject(jsonStr);
    	    	
    	    	// Grabbing the menu object 
    	        JSONObject meta = jsonObject.getJSONObject("usuario");  
    	        
    	        // These 2 are strings 
    	        String success = meta.getString("success");  
    	        Toast.makeText(Main.this, success, Toast.LENGTH_SHORT).show();
    	        String mensaje = meta.getString("mensaje");  
    	        Toast.makeText(Main.this, mensaje, Toast.LENGTH_SHORT).show();   	      
    	         
    	        
    	    }catch(JSONException e){
    	    	// TODO Auto-generated catch block
    	    	e.printStackTrace();
    	    }   
    	    //Toast.makeText(HelloWorldActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
    	}catch(ClientProtocolException e){
    		Toast.makeText(Main.this, e.toString(), Toast.LENGTH_SHORT).show();
    	}catch (IOException e) { 
    	    Toast.makeText(Main.this, e.toString(), Toast.LENGTH_SHORT).show();
    	}   	
    } // Fin del metodo publico postData.
}