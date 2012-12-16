<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Seguridad extends CI_Controller {
	/**
	 * Manejo de seguridad para 4s.
	 * Controla el login, logout, permisos
	 * @author Juan Bauer
	 */
	
	public function __construct(){
		parent::__construct();
		$this->load->library('session');
		$this->load->model('Seguridad_M', 'seguridad');
	}
	
	
	/**
	 * Metodo para iniciar sesion del usuario
	 * 
	 * @return true or false
	 */
	public function login($p_email, $p_password){
		//$p_email = $this->input->post('email');
		//$p_password = $this->input->post('password');
		if($this->seguridad->login($p_email,$p_password)===true)
			$v_output = array("success"=>true, "mensaje"=>'xD');
		else
			$v_output = array("success"=>false, "mensaje"=>'no entro!');
			
		$v_data = array('p_output'=>$v_output);	//encapsula el array en otro array para que la vista pueda procesarlo como array
		$this->load->view('output', $v_data);
	}
	
	/**
	 * Metodo para cerrar sesion del usuario
	 * @return void
	 */	
	public function logout(){
		$this->seguridad->logout();
		$v_output = array("success"=>true, "mensaje"=>'usted salio');
		$v_data = array('p_output'=>$v_output);	//encapsula el array en otro array para que la vista pueda procesarlo como array
		$this->load->view('output', $v_data);
	}
	
	/**
	 * revisa que el usuario este logueado para acceder al sistema
	 * @author Pablo
	 * @return void
	 */
	public function validarSesion(){
		if($this->seguridad->logged()){
			$v_output = array("success"=>true, "datos_usuario"=>$this->seguridad->getDatosUsuario(), "mensaje"=>'');
		}else{
			$v_output = array("success"=>false, "mensaje"=>'');
		}
		$v_data = array('p_output'=>$v_output);	//encapsula el array en otro array para que la vista pueda procesarlo como array
		$this->load->view('output',$v_data);
	}

}

/* End of file seguridad.php */