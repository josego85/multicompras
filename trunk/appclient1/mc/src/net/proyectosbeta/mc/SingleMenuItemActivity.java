package net.proyectosbeta.mc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SingleMenuItemActivity extends Activity{
	TextView producto_id;
	TextView producto_nombre;
	TextView producto_description;
	TextView producto_imagen;
	TextView producto_precio;
	TextView cliente_id;
	Button botonGuardar;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.detalle_producto);
	        
	     producto_id = (TextView)findViewById(R.id.textViewProductoID);
         producto_nombre = (TextView)findViewById(R.id.textViewProductoNombre);
         producto_description = (TextView)findViewById(R.id.textViewProductoDescripcion);
         producto_imagen = (TextView)findViewById(R.id.textViewProductoImagen);
         producto_precio = (TextView)findViewById(R.id.textViewProductoPrecio);
         cliente_id = (TextView)findViewById(R.id.textViewClienteID);
         botonGuardar = (Button)findViewById(R.id.buttonComprar);
            
         Bundle datos = getIntent().getExtras();
         producto_id.setText(datos.getString("producto_id"));
         producto_nombre.setText(datos.getString("producto_nombre"));
         producto_description.setText(datos.getString("producto_description"));
         producto_imagen.setText(datos.getString("producto_imagen"));
         producto_precio.setText(datos.getString("producto_precio"));
         cliente_id.setText(datos.getString("cliente_id"));
         
         botonGuardar.setOnClickListener(new OnClickListener() {
        	 @Override
        	 public void onClick(View arg0) {
        		 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(SingleMenuItemActivity.this);  
        	     dialogo1.setTitle("Importante");  
        	     dialogo1.setMessage("¿Desea realizar la compra?");            
        	     dialogo1.setCancelable(false);  
        	     dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {  
        	    	 public void onClick(DialogInterface dialogo1, int id) {  
        	    		 aceptar();  
        	    	 }  
        	     });  
        	     dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {  
        	    	 public void onClick(DialogInterface dialogo1, int id) {  
        	             cancelar();
        	    	 }  
        	     });            
        	     dialogo1.show();        
        	 }
         });
	 } // Fin dek metodo publico onCreate.
	
	 public void aceptar() {
	     // Ir al Intent BingMapsActivity.
         Intent in = new Intent(getApplicationContext(), BingMapsActivity.class);
         startActivity(in);
	 }
	    
	 public void cancelar() {
		 finish();
	 }
}