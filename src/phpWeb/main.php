<?php
$dataHora = date("Y-m-d H:i:s");

$response = "Olá! A hora atual é: " . $dataHora;

header("Content-Type: text/html");

echo $response;
