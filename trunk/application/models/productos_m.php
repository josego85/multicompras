<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Productos_M extends CI_Model {
	/**
	 * Modelo para manejo de productos para 4s.
	 * @author Juan Bauer
	 */
	public function __construct(){
		parent::__construct();
	}
	
	/**
	 * Metodo para traer lista de productos
	 * @param String con id de productos separados por , .Si no se pasa parametro retorna todos los productos
	 * @return Array
	 */
	public function listarProductos($p_id_producto=false){
		if($p_id_producto!==false)
			$this->db->where_in('producto_id',$p_id_producto);
			$v_consulta = $this->db->get('productos');
		return $v_consulta->result_array();
	}	
	
		/**
	 * Metodo para traer lista de productos
	 * @param String con palabra clave, sin palabra clave retorna todos los productos
	 * @todo la busqueda se hara con tags
	 * @return Array
	 */
	public function buscarProductos($p_search=""){
		if($p_search!=""){
			$this->db->like('producto_nombre',$p_search);
			$this->db->or_like('producto_descripcion',$p_search);
		}
		$v_consulta = $this->db->get('productos');
		return $v_consulta->result_array();
	}	

	/**
	 * Metodo para agregar un nuevo producto
	 * @param String descripcion del producto
	 * @param String precio del producto
	 * @param String categoria del producto
	 */
	public function agregarProducto($p_producto_descripcion, $p_producto_precio, $p_cat_producto_id){
		$datos = array(
   			'producto_precio' => $p_producto_precio,
   			'cat_producto_id' => $p_cat_producto_id,
   			'producto_descripcion' => $p_producto_descripcion
		);
		$this->db->insert('productos', $datos);

	}
}

/* End of file productos_m.php */