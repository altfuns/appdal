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
	_getDataSetFromGrupoInco($homicidiosDolososURL, $region_data, 5);
	_getDataSetFromGrupoInco($homicidiosCulpososURL, $region_data, 4);
}

/**
 * Function to get the politic region data
 */
function _getPoliticRegionData() {
	$result = array();
	
	// Query to the database
	$politic_regions = db_select('appdal_politic_region', 'pr1');
	$politic_regions->join('appdal_politic_region', 'pr2', 'pr1.prid = pr2.parent_prid');
 	$politic_regions->fields('pr1', array('prid', 'name'));
 	$politic_regions->fields('pr2', array('name'));
	$query_result = $politic_regions->execute();
 	
 	// Parse the regions to create the array
 	while($record = $query_result->fetchAssoc()) {
        $result[$record['name'] . '-' . $record['pr2_name']] = 0;
    }
 	
 	return $result;
}

/**
 * Function to parse the xml response for Grupo Inco datasets.
 */
function _getDataSetFromGrupoInco($guid, &$region_data, $start_index) {
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
						$region_key = $temp_last . '-' . $distrito;
						
						if (array_key_exists($region_key, $region_data)) {
							$region_data[$region_key] = 
								$region_data[$region_key] + ($row->column[2] . '');
						} else {
							$region_data[$region_key] = $row->column[2] . '';
						}
					}
					
					if ($provincia_canton != $EMPTY_STRING) {
						$temp_last = $provincia_canton . '';	
					}
				}
				
				dpm($region_data);
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