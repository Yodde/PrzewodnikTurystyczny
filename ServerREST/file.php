<?php
require_once __DIR__ . '/db_config.php';
$max_rozmiar = 1024*1024;
$filename = uniqid('img', true) . '.jpg';


if(isset($_GET['id'])){

    $id = $_GET['id'];

        if(is_uploaded_file($_FILES['plik']['tmp_name'])) {
            if ($_FILES['plik']['size'] > $max_rozmiar) {
                echo 'Błąd! Plik jest za duży!';
            } else {
                echo 'Odebrano plik. Początkowa nazwa: '.$_FILES['plik']['name'];
                echo '<br/>';
                if (isset($_FILES['plik']['type'])) {
                    echo 'Typ: '.$_FILES['plik']['type'].'<br/>';
                }
                move_uploaded_file($_FILES['plik']['tmp_name'],
                    $_SERVER['DOCUMENT_ROOT'].'/Serwer REST/images/'.$filename);

                $conn = new mysqli(DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE);

                if($conn->connect_error) die("Błąd połączenia z bazą".$conn->connect_error);

                $conn->set_charset("utf8");

                $sql = "INSERT INTO `images`(`PlaceId`, `FileName`, `Description`) VALUES (".$id.",'".$filename."','Zdjecie')";

                if ($conn->query($sql) === TRUE) {
                    echo '<div class="alert alert-success">Pomyślnie dodano '.$filename.' do bazy</div>';
                } else {
                    echo '<div class="alert alert-danger">Błąd: Nie dodano '.$filename.' do bazy'.$conn->error.'</div>';
                    var_dump($conn->error);
                }

                $conn->close();
            }
                    
        } else {
           echo 'Błąd przy przesyłaniu danych!';
        }
    }

    header('Location: show_places.php');   

?> 