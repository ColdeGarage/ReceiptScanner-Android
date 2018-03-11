<?php
header("Content-Type:text/html; charset=utf-8");
 $connect = mysqli_connect("192.168.0.2", "OnlineAccounting", "!N-e3G", "OnlineAccounting");
 $sql = "DELETE FROM  `accounting` WHERE
 `Date` = '".$_POST["Date"]."' && `Remark` = '".$_POST["Remark"]."' && `Money` = '".$_POST["Money"]."'&& `Type` = '".$_POST["Type"]."'limit 1";

 //echo $_POST["deletekey"];
 //echo $_POST["deletevalue"];
 /* ".$_POST["deletekey"]."  ".$_POST["deletevalue"]."   */
 mysqli_query($connect,"SET NAMES utf8"); //for chinese words
 mysqli_query($connect,"SET CHARACTER_SET_CLIENT utf8");//for chinese words
 mysqli_query($connect,"SET CHARACTER_SET_RESULTS utf8");//for chinese words
 mysqli_query($connect,"SET CHARACTER SET utf8");	//
 mysqli_query($connect,"SET collate utf8_unicode_ci");


 if(mysqli_query($connect, $sql))
 {
      //echo 'Data Deleted';
 }
 ?>
