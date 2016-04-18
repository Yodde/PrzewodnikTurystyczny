<?php
	require_once __DIR__ . '/db_config.php';


	$conn = new mysqli(DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE);

    if($conn->connect_error) die("Błąd połączenia z bazą".$conn->connect_error);

    $conn->set_charset("utf8");

    $sql = "SELECT p.*, X(p.Coordinates) as 'Longitude', Y(p.Coordinates) as 'Latitude', (SELECT FileName FROM images WHERE PlaceId = p.Id LIMIT 1) as 'ImageName' FROM `places` p 
";

    $result = $conn->query($sql);

    $rows =  array();
    if ($result->num_rows > 0 ) {
    	
    	while($row = $result->fetch_assoc()) {
        	array_push($rows, $row);
    	}

    }
    else{
    	$return = 'Zero elementow';
    }
    $conn->close();

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
  <script src="http://gregpike.net/demos/bootstrap-file-input/bootstrap.file-input.js"></script>

  
  
</head>
<body>

<div class="container-fluid">
	<div class="col-sm-6">
	<?php
		foreach ($rows as $row) {
			echo '<div class="well">';
			echo '<h4>'.$row["Name"].'</h4>';
			echo '<b>'.$row["Address"].'</b><br>';
			echo $row["Longitude"].'N '.$row["Latitude"].'E';
      if(!empty($row["ImageName"]))
        echo '<img src="http://localhost/Serwer%20REST/images/'.$row["ImageName"].'" class="img-responsive" alt="'.$row["ImageName"].'">';
			

      echo '<br><form action="file.php?id='.$row["Id"].'" method="POST" ENCTYPE="multipart/form-data">';
      echo '<input type="file" class="file-inputs" name="plik"/>';
      echo '<input type="submit" class="btn btn-info" value="Wyślij plik"/>';
      echo '</form>';

      echo '</div>';
		}
	?>
		<a href="index.php" class="btn btn-info" role="button">Powrót</a>
	</div>

</div>

<script type="text/javascript">
    $('.file-inputs').bootstrapFileInput();
  </script>

</body>
</html>