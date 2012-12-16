<?php
/**
 * Esta vista se encargara de devolver los valores solicitados a las aplicaciones clientes. Ej json, xml
 * @author Juan Bauer
 */
/*echo json_encode($p_output);*/
$RSS_PHP = new rss_php;

$RSS_PHP->loadArray($p_output); #the second param is an optional root node name.

echo $RSS_PHP->getXML();