<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Seguridad_M extends CI_Model {
	/**
	 * Modelo para manejo de seguridad para mc.
	 * @author Juan Bauer
	 */
	public function __construct(){
		parent::__construct();
	}
	
	/**
	 * Metodo para iniciar sesion del usuario
	 * @param String nombre de usuario
	 * @param String password de acceso
	 * @return boolean
	 */
	public function login($p_email,$p_password)
	{
		$this->db->select('cliente_email, cliente_id');
		$this->db->where('cliente_email',$p_email);
		$this->db->where('cliente_password',$p_password);
		
		$v_consulta = $this->db->get('clientes');
		if($v_consulta->num_rows()==1){ //existe
			$v_cliente = $v_consulta->row();
			$this->session->set_userdata(LOGGED,true);
			$this->session->set_userdata('cliente',$v_cliente);
			$this->session->set_userdata(SESSION_LIVE,time());
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Metodo para cerrar sesion del usuario
	 * @return void
	 */
	public function logout()
	{
		$this->session->sess_destroy();
	}
	
	 /**
	 * metodo que retorna permisos del usuario
	 * @return array
	 */
	//por el momento no tendremos en cuenta los permisos
	/*public function getPermisosUsuario()
	{
		$this->db->select('*')->from('usuarios_perfiles up')->join('perfiles p', 'up.usuario_id = p.usuario_id');
		$this->db->join('permisos per', 'per.perfil_id = p.perfil_id');
		$this->db->where('usuario_id',$this->session->userdata('usuario')->usuario_id)->where('estado',E_ACTIVO);		
	}*/
	
	/**
	 * metodo para mantener con vida la sesion
	 * @return void
	 */
	public function keepAlive()
	{
		//log_message(DEBUG_LEVEL_INFO,' * haciendo keep alive');
		$this->session->set_userdata(SESSION_LIVE,time());		
	}
	
	/**
	 * Retorna los datos de usuarios necesarios para el extjs
	 */
	public function getDatosCliente(){
		return $this->session->userdata('cliente');
	}
	
	/**
	 * Comprueba que el usuario esta logueado y su tiempo de session no ha expirado aun.
	 * @author Juan Bauer
	 * @return boolean
	 */
	public function logged(){
		$session_live =  $this->session->userdata(SESSION_LIVE);
		//comprobamos que esta seteado el valor en session
		if($session_live !== FALSE){
			//verificar que la ultima actividad con el sistema aun no haya caducado
			if($this->config->item(SESSION_LIVE)< time() - $session_live  ){
				$this->seguridad->logout();
				return false;
			}else{
				return true;
			}
		}else{
			return false;	
		}
	}
	
}

/* End of file seguridad.php */