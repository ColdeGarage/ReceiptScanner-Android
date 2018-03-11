<?php
	header("Content-Type:text/html; charset=utf-8");


    $servername = "192.168.0.2";
    $username = "OnlineAccounting";
    $password = "!N-e3G";
    $dbname = "OnlineAccounting";



    $da = $_GET['date'] ;
//    echo $da;

    list($year, $month, $day) = explode("-", $da);
/*	echo "<br>$year<br>";
	echo "$month<br>";
	echo "$day<br>";*/


	$con = mysqli_connect($servername, $username,$password, $dbname);
	mysqli_query($con,"SET NAMES utf8"); //for chinese words
	mysqli_query($con,"SET CHARACTER_SET_CLIENT utf8");//for chinese words
	mysqli_query($con,"SET CHARACTER_SET_RESULTS utf8");//for chinese words
	mysqli_query($con,"SET CHARACTER SET utf8");	//
	mysqli_query($con,"SET collate utf8_unicode_ci");
	// Check connection
	if ( mysqli_connect_errno() )
	{
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}

	$sql = "SELECT * FROM `accounting` WHERE `date` = '$da'";
//	echo "========================";
//	echo $sql;
//	echo "========================";
/*
	if (!$sql)
		echo "SELECT FAILED!<br>";
	else
		echo "SELECT SUCCEED!<br>";

	print_r($sql);
	echo"<br>";
*/
	$result = mysqli_query($con,$sql);

/*	if (!$result)
		echo "!result FAILED!<br>";
	else
		echo "result SUCCEED!<br>";
	print_r($result);
*/

/*	if ($result)
	{
	  // Fetch one and one row
		/*$row = mysqli_fetch_array($result);
		if (!$row)
			echo "Fetch FAILED!<br>";
		else
			echo "Fetch SUCCEED!<br>";	*/
/*		while ($row=mysqli_fetch_row($result))
		{
			//print_r($row);
	    	echo "$row[0], $row[1], $row[2], $row[3], $row[4], $row[5] <br>";
		}
  		echo "Free result set";
		mysqli_free_result($result);
	}

	mysqli_close($con);
*/
	?>



<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<style>
		* {
		    box-sizing: border-box;
		}
		body {
		    font-family: Arial;
		    padding: 5px;
		    padding-left: 80px;
		    padding-right:  80px;
		    background: #fff5cc;
		}

		.header {
			text-align: left;
			font-size: 20px;
			font-family: TimeNewRoman;
			color: #000000;
		    height: 100px;
			background: #ffffff;
		}
		.header h2{
			padding: 20px 0 10px 0;
			text-align: center;
		}

/* Top navigation bar*/
		.topnav {
		    overflow: hidden;
		    background-color: #333;
		}

		.topnav a{
		    float: left;
		    display: block;
		    color: #f2f2f2;
		    text-align: center;
		    padding: 14px 16px;
		    text-decoration: none;
		}

/* Change color on hover */
		.topnav a:hover {
		    background-color: #fafafa;
		    color: black;
		}

		.content {
		    height: 980px;
		    background-color: white;
		}

		.left{
			float: left;
		    height: 980px;
		    width: 50%;
		    border: 1px solid black;
		    padding: 20px;
		}
		.leftup{
			height: 200px;
		    padding: 20px;
		//    border: 1px solid black;
		}
		.leftmid{
			height: 650px;
		    padding: 20px;
		//    border: 1px solid black;
		}
		.leftdown{
			height: 80px;
		//    border: 1px solid black;
		}
		table, th, td{
			border: 1px solid black;
			border-collapse: collapse;
		}
		div.gallery {
			margin: 5px;
		    border: 1px solid #ccc;
		    float: left;
		    width: 80px;
		    height: auto;
		}
		div.gallery:hover {
		    border: 1px solid #777;
		}
		div.gallery img {
		    width: 100%;
		    float: left;
			height: auto;
		}


		.right{
			float: left;
		    height: 980px;
			width:50%;
		    border: 1px solid black;
		    padding: 20px;
		}

		.rightup{
			height: 500px;
			border: 1px solid black;
		    padding: 20px;
		}

		.rightdown{
			height: 480px;
			border: 1px solid black;
		    padding: 20px;
		}

		.footer {
		    text-align: center;
		    font-size:15px;
		    color: white;
		    height: 25px;
		    background-color: black;
		}
	</style>
	</head>

<body>

<div class="header">
 <h2>母湯隊 - Accounting App and Webpage</h2>
</div>

<div class="topnav">
	<a href="http://makerthon.nthuee.org/2018/OnlineAccounting/reception_search.php">發票紀錄</a>
	<a href="http://makerthon.nthuee.org/2018/OnlineAccounting/calender.html">記帳</a>
	<a href="http://makerthon.nthuee.org/2018/OnlineAccounting/" style="float: right">主頁</a>
</div>

<div class="content">

	<div class="left">

        	<div class="leftup">
        	<form action="index.php" method="GET" >
		<fieldset>
			<legend>Inputs</legend>
			Choose a date to check your accounting records.<br>
			<input type="date" name="date"></input>
			<input type="submit" Value="Search"></input>
		</fieldset>
		</form>
		</div>

        	<div class="leftmid">
								<br></br>
								<canvas id="myCanvas"></canvas>
								<ul id="colors">
								</ul>
        	</div>

        	<div class="leftdown">
			<div class="gallery">
				<a target="_blank" href="http://140.114.28.225/">
				<img src="https://goo.gl/yKmogv" alt="lab822" >
				</a>

			</div>
			<div class="gallery">
				<a target="_blank" href="https://goo.gl/vu3vsU">
				<img src="http://ez2o.com/7VRnU" alt="nthuee" style="position: right">
				</a>
			</div>
		</div>

	</div>



	<div class="right">
    		<div class="rightup">
        		<table align="center" width="85%">
				<tr>
					<th>Date</th>
					<th>Type</th>
					<th>Money</th>
					<th>Remarks</th>
				</tr>
				<?php
					$sum=0;
					$sum_f=0;
					$sum_tr=0;
					$sum_c=0;
					$sum_te=0;
					$sum_e=0;
					$sum_da=0;
					$sum_do=0;
					$sum_b=0;
					while($row=mysqli_fetch_row($result) ):
						$sum=$sum+$row[5];
						if($row[7]==="FOOD"){
								$sum_f=$sum_f+$row[5];
						}
						else if($row[7]==="TRANSPORTS"){
								$sum_tr=$sum_tr+$row[5];
						}
						else if($row[7]==="CLOTH"){
								$sum_c=$sum_c+$row[5];
						}
						else if($row[7]==="TEACH"){
								$sum_te=$sum_te+$row[5];
						}
						else if($row[7]==="ENTERTAIN"){
								$sum_e=$sum_e+$row[5];
						}
						else if($row[7]==="DAILY"){
								$sum_da=$sum_da+$row[5];
						}
						else if($row[7]==="DOCTOR"){
								$sum_do=$sum_do+$row[5];
						}
						else if($row[7]==="BILL"){
								$sum_b=$sum_b+$row[5];
						}
				?>
				<tr>
					<td><?php echo $row[0]; ?></td>
					<td><?php echo $row[7]; ?></td>
					<td><?php echo $row[5]; ?></td>
					<td><?php echo $row[6]; ?></td>
				</tr>
				<?php
					endwhile;
				?>
				</table>
        	</div>



        	<div class="rightdown">
	        	This is for the statistic result of left date choose.
						<div id="chartContainer" style="height: 370px; width: 100%;"></div>
	        </div>
	</div>

</div>


<div class="footer">Copyright &copy; EECS Lab822</div>

<script>

	var sum=[<?php echo $sum; ?>,<?php echo $sum_f; ?>,<?php echo $sum_tr; ?>,<?php echo $sum_c; ?>,
	<?php echo $sum_te; ?>,<?php echo $sum_e; ?>,<?php echo $sum_da; ?>,<?php echo $sum_do; ?>,<?php echo $sum_b; ?>]
	var color=["#F44336","#00BCD4","#03A9F4","yellow","orange","green","purple","#1abc9c"];
	var tags=["food","transportation","clothes","education","entertainment","daily uses","medical expenses","bill"]

	var myCanvas = document.getElementById("myCanvas");
	myCanvas.width=400;
	myCanvas.height=400;

	var ctx = myCanvas.getContext("2d");
	//ctx.clearRect(0,0,300,300);


	var currentStartAng=-Math.PI/2;
	var currentEndAng=-Math.PI/2;


		for(var i=0;i<8;i++){
				currentEndAng=currentEndAng+2*Math.PI*sum[i+1]/sum[0];
				drawPieSlice(ctx,200,200,200,currentStartAng,currentEndAng,color[i]);
				currentStartAng=currentEndAng;
		}



	function drawPieSlice(ctx,centerX, centerY, radius, startAngle, endAngle, color ){
		ctx.fillStyle = color;
		ctx.beginPath();
		ctx.moveTo(centerX,centerY);
		ctx.arc(centerX, centerY, radius, startAngle, endAngle);
		ctx.closePath();
		ctx.fill();
	}

	for(i=0;i<8;i++){
		console.log(sum[i+1]);
		if(sum[i+1]!=0){
			//alert("build tag!");
			var colortag=document.createElement('li');
			colortag.textContent = tags[i]+" : "+sum[i+1];
			colortag.style.color=color[i];
			document.getElementById("colors").appendChild(colortag);
		}
	}



</script>
<!--

-->
</body>
</html>
