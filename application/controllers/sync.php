<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Seguridad extends CI_Controller {
	/**
	 * Sync
	 * funcion de sincronizacion con el sistema
	 * @author Juan Bauer
	 */
	public function __construct(){
		parent::__construct();
		$this->load->library('session');
		$this->load->model('Seguridad_M', 'seguridad');
	}
	
	/**
	 * funcion principal del sync
	 * @author Juan Bauer
	 */
	public function index(){
		//verificar que la ultima actividad con el sistema aun no haya caducado
		if(!($this->config->item('session_live')>(time() - $this->session->userdata('session_live')))){
			$this->seguridad->logout();
			$v_sesion_activa = false;
		}
	}
}

/* End of file sync.php */