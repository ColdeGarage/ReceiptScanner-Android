<?php
  header("Content-Type:text/html; charset=utf-8");
  header('Access-Control-Allow-Origin: *');



  $server="192.168.0.2";
  $user="OnlineAccounting";
  $psd="!N-e3G";
  $db="OnlineAccounting";

  $connect = mysqli_connect($server,$user,$psd,$db);

  mysqli_query($connect,"SET NAMES utf8"); //for chinese words
  mysqli_query($connect,"SET CHARACTER_SET_CLIENT utf8");//for chinese words
  mysqli_query($connect,"SET CHARACTER_SET_RESULTS utf8");//for chinese words
  mysqli_query($connect,"SET CHARACTER SET utf8");	//
  mysqli_query($connect,"SET collate utf8_unicode_ci");

  $sql="INSERT INTO `accounting`(YMD,MD,Month,Day,Date,Money,Remark,Type)
Values('".$_POST["YMD"]."','".$_POST["MD"]."','".$_POST["Month"]."','".$_POST["Day"]."','".$_POST["Date"]."','".$_POST["Money"]."','".$_POST["Remark"]."','".$_POST["Type"]."')";
  if(mysqli_query($connect,$sql)){
    echo 'Data Inserted';
  }
?>
