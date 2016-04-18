<?php
 
/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */

 header('Content-type:application/json;charset=utf-8');
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// check for post data
if (isset($_GET["Id"])) {
    $id = $_GET['Id'];
 
    // get a product from products table
    $result = mysql_query("SELECT `Id`, `Name`, X(`Coordinates`) as 'Longitude', Y(`Coordinates`) as 'Latitude', `ShortDescription`, `Description`, `Address` FROM `places` WHERE Id = $id");
 
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
 
            $result = mysql_fetch_array($result);
 
            $product = array();
            $product["id"] = $result["Id"];
            $product["name"] = $result["Name"];
            $product["longitude"] = $result["Longitude"];
            $product["latitude"] = $result["Latitude"];
            $product["shortDescription"] = $result["ShortDescription"];
            $product["description"] = $result["Description"];
            $product["address"] = $result["Address"];
            // success
            $response["success"] = 1;
 
            // user node
            $response["place"] = array();
 
            array_push($response["place"], $product);

            //echo $product['name'];
 
            // echoing JSON response
            echo json_encode($response,JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);

            $fp = fopen('results.json', 'w');
            fwrite($fp, json_encode($response, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES));
            fclose($fp);

        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No place found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No place found";
 
        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>