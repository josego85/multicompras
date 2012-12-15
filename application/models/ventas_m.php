<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Ventas_m extends CI_Model {
	/**
	 * Modelo para manejo de ventas 4s.
	 * @author Juan Bauer
	 */
	public function __construct(){
		parent::__construct();
		$this->load->helper('date');
	}
	
	/**
	 * Metodo para traer lista de ventas
	 * @param int, id de la venta
	 * @return Array
	 */
	public function getVenta($p_id_venta=false){
		if($p_id_venta!==false){
			$this->db->where_in('venta_id',$p_id_venta);
		}
		$v_consulta = $this->db->get('ventas');
		return $v_consulta->result_array();
	}
	
	/**
	 * Metodo para traer detalle de una venta
	 * @param int, id de la venta
	 * @return Array
	 */
	public function getDetalle($p_id_venta=false){
		if($p_id_venta!==false){
			$this->db->where_in('venta_id',$p_id_venta);
			$v_consulta = $this->db->get('det_ventas');
			return $v_consulta->result_array();
		}else
			return array();
	}

	public function guardar($p_cliente, $p_envio, $p_monto_total, $p_detalle){
		//insertar cabecera de la venta
		$v_query = $query = $this->db->query('select now() fecha');
		$v_fecha = $v_query->row()->fecha;
		$this->db->trans_begin();
		$v_datos = array(
   			'venta_fecha' => $v_fecha,
   			'venta_cliente' => $p_cliente,
   			'venta_envio' => $p_envio,
			'venta_monto' => $p_monto_total
		);
		$this->db->insert('ventas', $v_datos);
		
		
		//insertar detalles de la venta
		$v_venta_id = $this->db->insert_id();
		$v_count = 1;
		foreach($p_detalle as $v_detalle){			
			$v_datos = array(
				'venta_id' => $v_venta_id,
				'det_venta_item'=> $v_count,
				'producto_id' => $v_detalle->producto_id,
				'det_venta_monto' => $v_detalle->det_venta_monto,
				'det_venta_cantidad' => $v_detalle->det_venta_cant,
				'det_venta_descripcion' => $v_detalle->det_venta_descripcion,
				'det_venta_nombre' => $v_detalle->det_venta_nombre
			);
			$this->db->insert('det_ventas', $v_datos);
			$v_count++;
		}
		
		//verificar si la transaccion se realizo correctamente
		if ($this->db->trans_status() === FALSE){
		    $this->db->trans_rollback();
		    return false;
		}else{
		    $this->db->trans_commit();
		    return $v_venta_id;
		}
	}	
}

/* End of file cliente.php */