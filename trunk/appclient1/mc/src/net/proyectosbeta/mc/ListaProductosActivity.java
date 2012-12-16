package net.proyectosbeta.mc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class ListaProductosActivity extends Activity{
	// Objetos
	ListView listaProductos;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_productos);
        
        listaProductos = (ListView)findViewById(R.id.listViewListaProductos);
        
        // Hashmap for ListView.
        ArrayList<HashMap<String, String>> productList = new ArrayList<HashMap<String, String>>();
 
        HttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost("http://mc.proyectosbeta.net/productos/listarProductos/");
    	
    	try { 
    	    HttpResponse response = httpclient.execute(httppost);
    	    HttpEntity entity = response.getEntity();
    	    InputStream is = entity.getContent();
    	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    	    StringBuilder sb = new StringBuilder();

    	    String line = null;
    	    
    	    try {
    	    	while ((line = reader.readLine()) != null) {
    	    		sb.append((line + "\n"));
    	    		//Toast.makeText(ListaProductosActivity.this, "La linea es: " + line, Toast.LENGTH_LONG).show();
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
    	    	//String jsonStr = "{'productos':" + sb.toString() + "}";  
    	    	//JSONObject jsonObject = new JSONObject(jsonStr);
    	    	JSONObject jsonObject = new JSONObject(sb.toString());
    	         
    	        // Productos JSONArray.
    	        JSONArray productos1 = null;	
    	        
    	        try {
    	            // Getting Array of Productos.  
    	        	productos1 = jsonObject.getJSONArray("data"); 
    	         
    	            // Looping through All Productos.
    	            for(int i = 0; i < productos1.length(); i++){
    	                JSONObject c = productos1.getJSONObject(i);

    	                // Storing each json item in variable
    	                String producto_id = c.getString("producto_id");
    	                String producto_nombre = c.getString("producto_nombre");
    	                String producto_descripcion = c.getString("producto_descripcion");
    	                String producto_imagen = c.getString("producto_imagen");
    	                String producto_precio = c.getString("producto_precio");
    	                String cliente_id = c.getString("cliente_id");
    	                
    	                Toast.makeText(ListaProductosActivity.this, "producto_id" + producto_id, Toast.LENGTH_SHORT).show();   
    	                Toast.makeText(ListaProductosActivity.this, "producto_nombre" + producto_nombre, Toast.LENGTH_SHORT).show();   
    	                Toast.makeText(ListaProductosActivity.this, "producto_descripcion" + producto_descripcion, Toast.LENGTH_SHORT).show();   
    	                Toast.makeText(ListaProductosActivity.this, "producto_imagen" + producto_imagen, Toast.LENGTH_SHORT).show();   
    	                Toast.makeText(ListaProductosActivity.this, "producto_precio" + producto_precio, Toast.LENGTH_SHORT).show();   
    	                Toast.makeText(ListaProductosActivity.this, "cliente_id" + cliente_id, Toast.LENGTH_SHORT).show();   
    	                
    	                // Creating new HashMap.
    	                HashMap<String, String> map = new HashMap<String, String>();
    	 
    	                // Adding each child node to HashMap key => value
    	                map.put("producto_id", producto_id);
    	                map.put("producto_nombre", producto_nombre);
    	                map.put("producto_descripcion", producto_descripcion);
    	                map.put("producto_imagen", producto_imagen);
    	                map.put("producto_precio", producto_precio);
    	                map.put("cliente_id", cliente_id);
    	 
    	                // Adding HashList to ArrayList.
    	                productList.add(map);   
    	            }
    	        } catch (JSONException e) {
    	            e.printStackTrace();
    	        } 
    	    }catch(JSONException e){
    	    	// TODO Auto-generated catch block
    	    	e.printStackTrace();
    	    }  	
    	    //Toast.makeText(HelloWorldActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();   
    	}catch(ClientProtocolException e){
    		Toast.makeText(ListaProductosActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
    	}catch (IOException e) { 
    	    Toast.makeText(ListaProductosActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
    	}   		
    }
	
    public void postData() {
    	HttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost("http://mc.proyectosbeta.net/productos/listarProductos/");
    	
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
    	    		Toast.makeText(ListaProductosActivity.this, "La linea es: " + line, Toast.LENGTH_LONG).show();
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
    	    	String jsonStr = "{'productos':" + sb.toString() + "}";   	     	    
    	    	JSONObject jsonObject = new JSONObject(jsonStr);
    	    	
    	    	// Grabbing the menu object. 
    	        JSONObject meta = jsonObject.getJSONObject("productos");  
    	        
    	        // These 2 are strings.
    	        String success = meta.getString("success");  
    	        Toast.makeText(ListaProductosActivity.this, success, Toast.LENGTH_SHORT).show();
    	        String datos = meta.getString("data");  
    	        Toast.makeText(ListaProductosActivity.this, datos, Toast.LENGTH_SHORT).show();   
    	        
    	        
    	        // The popop is another JSON object. 
    	        JSONObject productos = meta.getJSONObject("productos"); 
    	         
    	        // Productos JSONArray.
    	        JSONArray productos1 = null;	
    	        
    	        
    	        try {
    	            // Getting Array of Contacts
    	        	productos1 = jsonObject.getJSONArray("data");
    	         
    	            // looping through All Productos.
    	            for(int i = 0; i < productos1.length(); i++){
    	                JSONObject c = productos1.getJSONObject(i);

    	                // Storing each json item in variable
    	                String producto_id = c.getString("producto_id");
    	                String producto_nombre = c.getString("producto_nombre");
    	                String producto_descripcion = c.getString("producto_descripcion");
    	                String producto_imagen = c.getString("producto_imagen");
    	                String producto_precio = c.getString("producto_precio");
    	                String cliente_id = c.getString("cliente_id");
    	         

    	         
    	            }
    	        } catch (JSONException e) {
    	            e.printStackTrace();
    	        }
    	        
    	        
    	        
    	         
    	        
    	    }catch(JSONException e){
    	    	// TODO Auto-generated catch block
    	    	e.printStackTrace();
    	    }   
    	    //Toast.makeText(HelloWorldActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
    	}catch(ClientProtocolException e){
    		Toast.makeText(ListaProductosActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
    	}catch (IOException e) { 
    	    Toast.makeText(ListaProductosActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
    	}   	
    } // Fin del metodo publico postData.
    
    
}
