<?php
/**
 * Super controlador para mc.
 * Todos los controladores que tienen funcionalidades en comun haran un extends de este controlador
 * @author Juan Bauer
 */
class MC_Controller extends CI_Controller{
	
	
	
	 /**
       * Instancia del Singleton Pattern del CI
       * @var CI_Base
       */
       var $ci;
       /**
        * @var CI_Loader
        */
       var $load;
       var $session;
	/**
	 * Constructor.
	 * Verifica si esta logeado y mantiene con vida la sesion, redirecciona al inicio de la aplicacion.
	 */
    public function __construct()
    {
    	parent::__construct();
        $this->load->library('session');
        $this->load->model('seguridad_m');
        
      /* if ($this->session->userdata('logged')===true && $this->seguridad_m->logged() === true){
        	$this->seguridad_m->keepAlive();
        }else{
        	echo json_encode(array('success'=>false,'mensaje'=>'La session ha expirado!'));
    		die();
        }
        */
        
    }
    
     /**
     * Metodo que retornar valores a la aplicacion cliente
     */
    public function output($p_output){
    	$v_data = array('p_output'=>$p_output);	//encapsula el array en otro array para que la vista pueda procesarlo como array
		$this->load->view('output', $v_data);
    }
}