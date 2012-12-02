<?php

/**
 * @file
 * Administrative page callbacks for the AppDAL module.
 */
 
 /**
  * Function to render the AppDAL configuration
  */
function appdal_configuration_form($form, &$form_state) {
	$form['appdal'] = array(
		'#type' => 'fieldset',
  		'#title' => t('Update Data'), 
		'#weight' => 1, 
  		'#collapsible' => FALSE, 
  		'#collapsed' => FALSE,
	);
	
	$form['appdal']['submit'] = array(
	    '#type' => 'submit',
	    '#value' => t('Update Data'),
	    '#submit' => array('appdal_configuration_form_submit'),
   	);

  return system_settings_form($form);
}

/**
 * Function to update the AppDAL data.
 */
function appdal_configuration_form_submit($form, &$form_state) {
	_update_appdal_data();
}

/**
 * Function to update the data from the different resources.
 */
function _update_appdal_data() {
	module_load_include('php', 'junar_client', 'JunarApiAccess');
	
	$region_data = _getPoliticRegionData();
	$homicidiosDolososURL = 'C:\Users\Rodolfo\git\appdal\AppDal-Server\drupal\sites\all\modules\custom\appdal\response.xml';
	$homicidiosCulpososURL = 'C:\Users\Rodolfo\git\appdal\AppDal-Server\drupal\sites\all\modules\custom\appdal\response2.xml';
	
	// Call Junar to get and process the data
	_getDataSetFromGrupoInco($homicidiosDolososURL, $region_data, 5, true);
	_getDataSetFromGrupoInco($homicidiosCulpososURL, $region_data, 4, false);

	// Create or update the nodes for 'comunidades'
	foreach ($region_data as $key => $region) {
		_create_comunidad_node($region['region_name'], $region['score'], 
			$region['region_id'], $key);
	}
}

/**
 * Function to get the politic region data
 */
function _getPoliticRegionData() {
	$result = array();
	
	// Query to the database
	$politic_regions = db_select('appdal_politic_region', 'pr1');
	$politic_regions->join('appdal_politic_region', 'pr2', 'pr1.prid = pr2.parent_prid');
 	$politic_regions->fields('pr2', array('prid', 'name'));
 	$politic_regions->fields('pr1', array('name'));
 	$politic_regions->condition('pr1.parent_prid', -1, '!=');
	$query_result = $politic_regions->execute();
 	
 	// Parse the regions to create the array
 	while($record = $query_result->fetchAssoc()) {
        $result[$record['name'] . ', ' . $record['pr1_name']] = 
        	array('score' => 0, 'region_id' => $record['prid'], 'region_name' => $record['name']);
    }
 	
 	return $result;
}

/**
 * Function to parse the xml response for Grupo Inco datasets.
 */
function _getDataSetFromGrupoInco($guid, &$region_data, $start_index, $include_records) {
	try {
		$EMPTY_STRING = '';
		
		// Get the Junar API Key 
		$junarApiKey = variable_get('junar_client_api_key', $EMPTY_STRING);
		
		if ($junarApiKey != $EMPTY_STRING) {
			//$junarResponse = _callJunarAPI($guid, $junarApiKey);
		    $junarResponse = simplexml_load_file($guid);
			
			// Check for the result of the request
		    if ($junarResponse != null) {
				$temp_last = '';
				
				// Process each row
				for ($index_row = $start_index; $index_row < count($junarResponse->row); $index_row++) {
					$row = $junarResponse->row[$index_row];
					$provincia_canton = $row->column[0] . '';
					$distrito = $row->column[1] . '';	
					
					// Check if the region is a 'distrito'
					if ($distrito != $EMPTY_STRING) {
						$region_key = $distrito . ', ' . $temp_last;
						
						if (array_key_exists($region_key, $region_data)) {
							$region_data[$region_key]['score'] = 
								$region_data[$region_key]['score'] + ($row->column[2] . '');
						} else {
							if ($include_records) {
								$region_data[$region_key]['score'] = $row->column[2] . '';
							}
						}
					}
					
					if ($provincia_canton != $EMPTY_STRING) {
						$temp_last = $provincia_canton . '';	
					}
				}
//				dpm($region_data);
				drupal_set_message('The data from '. $guid . ' was updated successfully');
			}
		}
	} catch (Exception $exception) {
		dpm($exception);
	}
}

/**
 * Function to call the Junar API.
 */
function _callJunarAPI($guid, $junarApiKey) {
	$OPEN_DATA_ENDPOINT = 'http://gobiernodigitalcr.cloudapi.junar.com';
	$OUTPUT_DATA_TYPE = 'xml';
	
//    $junarAPIClient = new Junar($junarApiKey, $OPEN_DATA_ENDPOINT);
//    $datastream = $junarAPIClient->datastream($guid);
//    $response = $datastream->invoke($params = array(), $output = $OUTPUT_DATA_TYPE);
//	  return $response;
}

/**
 * Function to create the 'comunidad' nodes.
 */
function _create_comunidad_node($node_title, $node_calificacion, 
		$node_id_region_politica, $node_region_politica) {
	global $user;
	
	$COMUNIDAD_CONTENT_TYPE = 'comunidad';

	$node = new stdClass();
	$node->title = $node_title;
	$node->type = 'comunidad';
		  
	// Sets some defaults. Invokes hook_prepare() and hook_node_prepare().
	node_object_prepare($node); 
	$node->language = LANGUAGE_NONE; 
	$node->uid = $user->uid; 
	$node->status = 1;
	$node->promote = 0;
	$node->comment = 1;

	// Fields of the content type	
	$node->field_ranking[$node->language][0]['value'] = _get_comunity_ranking($node_calificacion);
	$node->field_calificacion[$node->language][0]['value'] = $node_calificacion;
	$node->field_id_region_politica[$node->language][0]['value'] = $node_id_region_politica;
	$node->field_region_politica[$node->language][0]['value'] = $node_region_politica;
		
	// Node saving
	$node = node_submit($node); 
	node_save($node);
}

/**
 * Function to get the rankings of the communities.
 */
function _get_comunity_ranking($score) {
	$rankings = array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
	
	for ($index_ranking = 0; $index_ranking < count($rankings) - 1; $index_ranking++) {
		if ($score >= $rankings[$index_ranking] && $score < $rankings[$index_ranking + 1]) {
			return $index_ranking + 1;
		}		
	}
	
	return 0;
}