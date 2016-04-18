  <?php
  require_once __DIR__ . '/db_config.php';

  $nameErr = $adressErr = $short_descErr = $descErr = $longitudeErr = $latitudeErr = null;
  $name = $adress = $short_desc = $desc = $result = '';
  $longitude = $latitude = 0;

  if ($_SERVER["REQUEST_METHOD"] == "POST"){

    if (empty($_POST["name"])){
      $nameErr = '<div class="alert alert-danger">Nazwa nie może być pusta</div>';
    }
    else{
      $name = htmlspecialchars($_POST["name"]);
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
      $adress = htmlspecialchars($_POST["adress"]);
    }

    if (empty($_POST["short_desc"])){
      $short_descErr = '<div class="alert alert-danger">Brak podanego opisu</div>';
    }
    else{
      $short_desc = htmlspecialchars($_POST["short_desc"]);
    }

    if (empty($_POST["desc"])){
      $descErr = '<div class="alert alert-danger">Brak podanego opis</div>';
    }
    else{
      $desc = htmlspecialchars($_POST["desc"]);
    }

    $result = '<div class="alert alert-success">Pomyślnie dodano '.$name.' do bazy</div>';


    if(empty($nameErr) && empty($latitudeErr) && empty($longitudeErr) && empty($adressErr) && empty($descErr) && empty($short_descErr)){

    	$conn = new mysqli(DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE);

    	if($conn->connect_error) die("Błąd połączenia z bazą".$conn->connect_error);

    	$conn->set_charset("utf8");

    	$sql = "INSERT INTO places( Name, Coordinates, ShortDescription, Description, Address) VALUES ('".$name."',POINT(".$longitude.",".$latitude."),'".$short_desc."','".$desc."','".$adress."')";

    	if ($conn->query($sql) === TRUE) {
    		$result = '<div class="alert alert-success">Pomyślnie dodano '.$name.' do bazy</div>';
		} else {
    		$result = '<div class="alert alert-danger">Błąd: Nie dodano '.$name.' do bazy'.$conn->error.'</div>';
    		var_dump($conn->error);
		}

    	$conn->close();

	}
	else{
		$result = '<div class="alert alert-danger">Błąd: Nie dodano </div>';
	}

  }

  ?>


<!DOCTYPE html>
<html lang="pl">
<head>
  <title>Formularz</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container-fluid">

  	<form class="form-horizontal col-sm-8" role="form" method="post" action="index.php">
  		<h1>Dodawanie objektu do bazy</h1><br>
	  	<div class="form-group">
		  <label class="col-sm-1 control-label" for="name">Nazwa:</label>
		  <div class="col-sm-7">
		  	<input type="text" class="form-control" id="name" name="name">
		  	<?php echo $nameErr; ?>
		  </div>
		</div>
		<div class="form-group">
		  <label class="col-sm-1 control-label" for="longitude">Długość:</label>
		  <div class="col-sm-7">
		  	<input type="text" class="form-control" id="longitude" name="longitude">
		  	<?php echo $longitudeErr; ?>
		  </div>
		</div>
		<div class="form-group">
		  <label class="col-sm-1 control-label" for="Latitude">Szerokość:</label>
		  <div class="col-sm-7">
		  	<input type="text" class="form-control" id="latitude" name="latitude">
		  	<?php echo $latitudeErr; ?>
		  </div>
		</div>
		<div class="form-group">
		  <label class="col-sm-1 control-label" for="adress">Adres:</label>
		  <div class="col-sm-7">
		  	<input type="text" class="form-control" id="adress" name="adress">
		  	<?php echo $adressErr; ?>
		  </div>
		</div>
		<div class="form-group">
	  		<label class="col-sm-1 control-label" for="short_desc">Krótki opis:</label>
	  		<div class="col-sm-7">
	  			<textarea class="form-control" rows="2" id="short_desc" name="short_desc"></textarea>
	  			<?php echo $short_descErr; ?>
	  		</div>
		</div>
		<div class="form-group">
	  		<label class="col-sm-1 control-label" for="desc">Opis:</label>
	  		<div class="col-sm-7">
	  			<textarea class="form-control" rows="5" id="desc" name="desc"></textarea>
	  			<?php echo $descErr; ?>
	  		</div>
		</div>
		<div class="form-group">
        	<div  class="col-sm-6 col-sm-offset-1">
            	<input id="submit" name="submit" type="submit" value="Zapisz" class="btn btn-primary">
              <a href="show_places.php" class="btn btn-info" role="button">Zobacz wszystkie</a>
       		</div>
    	</div>
    	<div class="form-group">
			<div class="col-sm-7 col-sm-offset-1">
				<?php echo $result; ?>	
			</div>
		</div>
	</form>
</div>

</body>
</html>