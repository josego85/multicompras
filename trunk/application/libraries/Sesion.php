<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Sesion {
	
	var $id_sesion;
	var $id_usuario;
	var $ip;
	var $fecha_hora_live;
	
	function __construct($p_id_sesion=false) {
		// Set the super object to a local variable for use throughout the class
		$this->CI =& get_instance();
		//Se pregunta si no se envió el id de una sesión
		if(!$p_id_sesion){
			//Se crea una nueva sesión
			$this->crearSesion();
		} else {
			//$p_log->notice("2", "sesion: Se han solicitados los datos de la sesión $p_sesion_id");
			//La sesión existe y se cargan los valores
			$this->id_sesion = $p_id_sesion;
			$v_obj_db = new db();
			$v_consulta = "SELECT id_usuario, fecha_hora_live FROM sesiones WHERE id_sesion='$this->id_sesion' AND estado_sesion='activa'";
			//echo $v_consulta;
			$v_resultado = $v_obj_db->consulta($v_consulta, $p_log);
			//echo $v_obj_db->cantidad_lineas()."<br>";
			if ($v_obj_db->cantidad_lineas()>0) {
				$v_linea = $v_obj_db->tomar_linea($v_resultado);
				//$v_linea;
				$this->id_usuario = $v_linea["id_usuario"];
				$this->fecha_hora_alive = $v_linea['fecha_hora_live'];
   				//$p_log->notice("3", "sesion: Se han entregado los datos de la sesión $p_sesion_id.");
				$v_obj_db->__destruct();
			}
			else {
				$v_obj_db->__destruct();
				return false;
			}
		}
		return;
	}
	
	// --------------------------------------------------------------------

	/**
	 * Create a new session
	 *
	 * @access	private
	 * @return	void
	 */
	function _crearSesion()
	{
		$this->id_sesion = md5(uniqid(time(),true));
		$this->ip = $this->CI->input->ip_address();

		$this->datos_sesion = array(
							'sesion_id'	=> $this->id_sesion,
							'sesion_ip'	=> $this->ip,
							'usuario_id'	=> substr($this->CI->input->user_agent(), 0, 50),
							'last_activity'	=> $this->now
							);


		// Save the data to the DB if needed
		if ($this->sess_use_database === TRUE)
		{
			$this->CI->db->query($this->CI->db->insert_string($this->sess_table_name, $this->userdata));
		}

		// Write the cookie
		$this->_set_cookie();
	}
}

/* End of file Someclass.php */