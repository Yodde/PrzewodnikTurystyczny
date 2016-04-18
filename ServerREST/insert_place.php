<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<body>

  <?php
  require_once __DIR__ . '/db_config.php';

  $nameErr = $adressErr = $short_descErr = $descErr = $longitudeErr = $latitudeErr = '';
  $name = $adress = $short_desc = $desc = '';
  $longitude = $latitude = 0;

  if ($_SERVER["REQUEST_METHOD"] == "POST"){

    if (empty($_POST["name"])){
      $nameErr = '<div class="alert alert-danger">Nazwa nie może być pusta</div>';
    }
    else{
      $name = $_POST["name"];
    }

    if (empty($_POST["longitude"])){
      $longitudeErr = '<div class="alert alert-danger">Brak podanej długości geograficznej</div>';
    }
    else{
      $longitude = $_POST["longitude"];
    }

    if (empty($_POST["latitude"])){
      $latitudeErr = '<div class="alert alert-danger">Brak podanej szerokości geograficznej</div>';
    }
    else{
      $latitude = $_POST["latitude"];
    }

    if (empty($_POST["adress"])){
      $adressErr = '<div class="alert alert-danger">Brak podanego adresu</div>';
    }
    else{
      $adress = $_POST["adress"];
    }

    if (empty($_POST["short_desc"])){
      $short_descErr = '<div class="alert alert-danger">Brak podanego opisu</div>';
    }
    else{
      $short_desc = $_POST["short_desc"];
    }

    if (empty($_POST["desc"])){
      $descErr = '<div class="alert alert-danger">Brak podanego opis</div>';
    }
    else{
      $desc = $_POST["desc"];
    }



    $conn = new mysqli(DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE);

    if($conn->connect_error) die("Błąd połączenia z bazą".$conn->connect_error);

    $conn->close();

  }
  ?>

</html>
</body>