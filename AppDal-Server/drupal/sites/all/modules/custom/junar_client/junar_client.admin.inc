<?php

/**
 * @file
 * Administrative page callbacks for the Junar Client module.
 */
 
 /**
  * Function to render the Junar Client configuration
  */
function junar_client_configuration_form($form, &$form_state) {
  $form['junar_client_main_endpoint'] = array(
    '#type' => 'textfield',
    '#title' => t('Main endpoing'),
    '#default_value' => variable_get('junar_client_main_endpoint', ''),
    '#description' => t('URL of the Junar API endpoint'),
    '#size' => 120,
    '#required' => TRUE,
  );
  
  $form['junar_client_api_key'] = array(
    '#type' => 'textfield',
    '#title' => t('API Key'),
    '#default_value' => variable_get('junar_client_api_key', ''),
    '#description' => t('String with the API key to request Junar platform.'),
    '#size' => 120,
    '#required' => TRUE,
  );

  return system_settings_form($form);
}