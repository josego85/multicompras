<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Productos extends MC_Controller {
	/**
	 * Manejo de productos para mc.
	 * @author Juan Bauer
	 */
	
	public function __construct(){
		parent::__construct();
		$this->load->model('productos_m','productos');
	}	
	
	public function prueba(){
		print_r($this->seguridad_m->getDatosCliente());
	}
	/**
	 * Metodo para traer lista de productos
	 */
	public function listarProductos(){
		$v_productos = $this->productos->listarProductos();
		$v_output = array("success"=>true, "data"=>$v_productos, "cantidad"=>count($v_productos), "mensaje"=>'');
		$this->output($v_output);
	}
	
	/**
	 * Metodo para buscar productos
	 * 
	 * mas adelante la busqueda lo hara por tags
	 */
	public function buscarProductos(){
		$p_search = $this->input->get('search');
		$v_productos = $this->productos->buscarProductos($p_search);
		$v_output = array("success"=>true, "data"=>$v_productos, "cantidad"=>count($v_productos), "mensaje"=>'');
		$this->output($v_output);
	}
	
	/**
	 * Agrega un nuevo producto
	 */
	public function agregarProducto(){
		$p_producto_descripcion = $this->input->post('nombre');
		$p_producto_descripcion = $this->input->post('descripcion');
		$p_producto_precio = $this->input->post('precio');
		$p_cliente_id = $this->seguridad_m->getDatosCliente()->cliente_id;
		$v_productos = $this->productos->agregarProducto($p_producto_descripcion, $p_producto_precio, $p_productonombre, $p_cliente_id);
		$v_output = array("success"=>true, "data"=>$v_productos, "cantidad"=>count($v_productos), "mensaje"=>'');
		$this->output($v_output);
	}
}

/* End of file seguridad.php */
