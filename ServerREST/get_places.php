<?php
 
  header('Content-type:application/json;charset=utf-8');


/*
 * Following code will list all the products
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();



    if(isset($_GET['number'])){

        $number = $_GET['number'];
        $result = mysql_query("SELECT `Id`, `Name`, X(`Coordinates`) as 'Longitude', Y(`Coordinates`) as 'Latitude', `ShortDescription`, `Description`, `Address` FROM `places` LIMIT ".$number) or die(mysql_error());

    }
    else
    {
        // get all products from products table
        $result = mysql_query("SELECT `Id`, `Name`, X(`Coordinates`) as 'Longitude', Y(`Coordinates`) as 'Latitude', `ShortDescription`, `Description`, `Address` FROM `places`") or die(mysql_error());
    }

     
    // check for empty result
    if (mysql_num_rows($result) > 0) {
        // looping through all results
        // products node
        $response["places"] = array();
     
        while ($row = mysql_fetch_array($result)) {
            // temp user array
            $product = array();
            $product["id"] = $row["Id"];
            $product["name"] = $row["Name"];
            $product["longitude"] = $row["Longitude"];
            $product["latitude"] = $row["Latitude"];
            //$product["shortDescription"] = utf8_encode($row["ShortDescription"]);
            //$product["description"] = utf8_encode($row["Description"]);
            //$product["address"] = utf8_encode($row["Address"]);
            // push single product into final response array
            array_push($response["places"], $product);
        }
        // success
        $response["success"] = 1;
     
        // echoing JSON response
        echo json_encode($response, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
    } else {
        // no products found
        $response["success"] = 0;
        $response["message"] = "No places found";
     
        // echo no users JSON
        echo json_encode($response);
    }

?>