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
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListaProductosActivity extends Activity{
	// Objetos
	ListView listaProductos;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_productos);
        
        listaProductos = (ListView)findViewById(R.id.listview);
        
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
    	                
    	                // Creating new HashMap.
    	                HashMap<String, String> map = new HashMap<String, String>();
    	 
    	                // Adding each child node to HashMap key => value
    	                map.put("producto_id", producto_id);
    	                map.put("producto_nombre", producto_nombre);
    	                map.put("producto_descripcion", producto_descripcion);
    	                map.put("producto_imagen", "http://mc.proyectosbeta.net/images/chico/" + producto_imagen);
    	                map.put("producto_precio", producto_precio);
    	                //map.put("cliente_id", cliente_id);
    	 
    	                // Adding HashList to ArrayList.
    	                productList.add(map);   
    	            }
    	            
    	            // Keys used in Hashmap.
    	            /*
    	            String[] from = { "producto_id","producto_nombre","producto_descripcion", "producto_imagen", "producto_precio",  
    	            		"cliente_id"};
    	            		*/
    	            String[] from = { "producto_id","producto_nombre","producto_descripcion", "producto_imagen", "producto_precio"};
    	            //String[] from = {"producto_nombre","producto_descripcion"};
    	            
    	            // Ids of views in listview_layout.
    	            /*
    	            int[] to = { R.id.producto_id, R.id.producto_nombre, R.id.producto_descripcion, R.id.producto_imagen,
    	            		R.id.producto_precio, R.id.cliente_id};
    	            		*/     
    	            int[] to = { R.id.producto_id, R.id.producto_nombre, R.id.producto_descripcion, R.id.producto_imagen,
    	            		R.id.producto_precio};    
    	            //int[] to = {R.id.producto_nombre, R.id.producto_descripcion}; 
    	            
    	            // Instantiating an adapter to store each items
    	            // R.layout.listview_layout defines the layout of each item.
    	            SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), productList, R.layout.listview_layout, from, to);
    	            
    	            // Getting a reference to listview of main.xml layout file.
    	            listaProductos = (ListView)findViewById(R.id.listview);
    	            
    	            // Setting the adapter to the listView
    	            listaProductos.setAdapter(adapter);      
    	            
    	            // Launching new screen on Selecting Single ListItem
    	            listaProductos.setOnItemClickListener(new OnItemClickListener() {
    	                @Override
    	                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    	                    // Getting values from selected ListItem.
    	                    String producto_id = ((TextView) view.findViewById(R.id.producto_id)).getText().toString();
    	                    String producto_nombre = ((TextView) view.findViewById(R.id.producto_nombre)).getText().toString();
    	                    String producto_description = ((TextView) view.findViewById(R.id.producto_descripcion)).getText().toString();
    	                    String producto_imagen = ((TextView) view.findViewById(R.id.producto_imagen)).getText().toString();
    	                    String producto_precio = ((TextView) view.findViewById(R.id.producto_precio)).getText().toString();
    	                    String cliente_id = ((TextView) view.findViewById(R.id.cliente_id)).getText().toString();
    	     
    	                    // Starting new intent.
    	                    Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
    	                    in.putExtra("producto_id", producto_id);
    	                    in.putExtra("producto_nombre", producto_nombre);
    	                    in.putExtra("producto_description", producto_description);
    	                    in.putExtra("producto_imagen", producto_imagen);
    	                    in.putExtra("producto_precio", producto_precio);
    	                    in.putExtra("cliente_id", cliente_id);
    	                    startActivity(in);
    	                }
    	            });
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
}