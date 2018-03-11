<?php
header("Content-Type:text/html; charset=utf-8");
$connect = mysqli_connect("192.168.0.2", "OnlineAccounting", "!N-e3G", "OnlineAccounting");
$sql = "SELECT * FROM `accounting` WHERE `Date`='".$_POST["Date"]."'";
  /* ".$_POST["Date"]." */


  mysqli_query($connect,"SET NAMES utf8"); //for chinese words
	mysqli_query($connect,"SET CHARACTER_SET_CLIENT utf8");//for chinese words
	mysqli_query($connect,"SET CHARACTER_SET_RESULTS utf8");//for chinese words
	mysqli_query($connect,"SET CHARACTER SET utf8");	//
	mysqli_query($connect,"SET collate utf8_unicode_ci");

  $result = mysqli_query($connect,$sql);

  $num=mysqli_num_rows($result);
  $bigarray=array();
  for($i=0;$i<$num;$i++){
    $row=mysqli_fetch_array($result,MYSQLI_NUM);
    $array= array($row[4],$row[5],$row[6],$row[7]);
    array_push($bigarray,array($row[4],$row[5],$row[6],$row[7]));
  }
  $output=json_encode($bigarray,JSON_UNESCAPED_UNICODE);
  echo $output;
?>
