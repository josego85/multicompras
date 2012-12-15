<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Load extends CI_Controller {
	/**
	 * Pagina principal para 4s.
	 * Hace llamada del view que carga todos los js y los css
	 * @author Juan Bauer
	 */
	public function index()
	{
		$this->load->view('cliente/cliente');
	}
}

/* End of file cliente.php */