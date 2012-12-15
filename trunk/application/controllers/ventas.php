<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Ventas extends SMG_Controller {
	/**
	 * Manejo de ventas para 4s.
	 * @author Juan Bauer
	 */
	
	public function __construct(){
		parent::__construct();
		$this->load->model('ventas_m','ventas');
	}

	public function lista(){
		$v_lista = $this->ventas->getVenta();
		$v_output = array("success"=>true, "data"=>$v_lista, "cantidad"=>count($v_lista), "mensaje"=>'');
		$this->output($v_output);
	}
	
	public function detalle($p_id_venta){
		$v_detalle = $this->ventas->getDetalle($p_id_venta);
		$v_output = array("success"=>true, "data"=>$v_detalle, "cantidad"=>count($v_detalle), "mensaje"=>'');
		$this->output($v_output);
	}
	
	public function guardar(){
		$v_cliente = $this->input->post('cliente');
		$v_envio = $this->input->post('envio');
		$v_monto_total = $this->input->post('monto');
		$v_detalle = json_decode(stripslashes($this->input->post('detalle')));
		$v_success = true;
		$v_mensaje = '';
		$v_guardar = $this->ventas->guardar($v_cliente, $v_envio, $v_monto_total, $v_detalle);
		if(!$v_guardar){
			$v_success = false;
			$v_mensaje = "Ocurrio un error al intentar guardar el pedido ".$v_guardar;
		}else{
			$v_mensaje = "El pedido se guardo en forma exitosa ".$v_guardar;
		}
			
			
		$v_output = array("success"=>$v_success, "mensaje"=>$v_mensaje);
		$this->output($v_output);
	}
}

/* End of file seguridad.php */
