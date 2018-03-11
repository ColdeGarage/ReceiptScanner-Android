//*********vars****************************************
var dateTemp=new Date();
var day;
var date,currdate,selencodedate;
var month,currmonth;
var year,curryear;
var j=0;
var i=0;
var k=0;
var Caldates;
var dateinfo;
var tempconvert;
var selectedevent=0;
//*****************************************************



//*********Main****************************************
var i;
var a = new Date();
updatetime();
document.getElementById('currtime').textContent=a
function updatetime(){
  setInterval(function(){a=Date();document.getElementById('currtime').textContent=a},1000);
}

selencodedate=100000*(dateTemp.getFullYear())+100*(dateTemp.getMonth()+1)+dateTemp.getDate();
//console.log("initial"+selencodedate);
//document.getElementById('currtime').textContent




//****************************************************

//***********initialize*******************************
  var initialDate=dateTemp.getFullYear()*100000+(dateTemp.getMonth()+1)*100+dateTemp.getDate();
  console.log(initialDate);


function SenddateAndAppend(undecodedate){

  $.ajax({
    url:"final_select.php",
    method:"POST",
    data:{Date:decodedate(undecodedate)},
    dataType:"json",
    success:function(data){
      var newArr=data;
      for(var i=0;i<newArr.length;i++){
        //alert(newArr[i][0]);
        console.log("success!");
        var events_date=document.createElement('li');
        events_date.textContent = newArr[i][0];
        document.getElementById('datalist').appendChild(events_date);
        events_date.setAttribute("class","DataDate");
        events_date.setAttribute("id",(i*1000+1));
        events_date.setAttribute("onclick","selectevent(this.id)");
        //console.log(this.id);

        var events=document.createElement('li');
        events.textContent = newArr[i][2];
        document.getElementById('datalist').appendChild(events);
        events.setAttribute("class","DataInfo");
        events.setAttribute("id",(i*1000+2));
        events.setAttribute("onclick","selectevent(this.id)");

        var events_type=document.createElement('li');
        var decodeeve = decodeevent(newArr[i][3])
        events_type.textContent = decodeeve;
        document.getElementById('datalist').appendChild(events_type);
        events_type.setAttribute("class","DataType");
        events_type.setAttribute("id",(i*1000+3));
        events_type.setAttribute("onclick","selectevent(this.id)");

        var events_cash=document.createElement('li');
        events_cash.textContent = newArr[i][1];
        document.getElementById('datalist').appendChild(events_cash);
        events_cash.setAttribute("class","DataCash");
        events_cash.setAttribute("id",(i*1000+4));
        events_cash.setAttribute("onclick","selectevent(this.id)");
      }
    },
    error:function(){
      //alert("NOOOO");
    }
  })
}

SenddateAndAppend(initialDate);

function cleanevents(){
  var childdata=document.getElementById('datalist');
  for(i=0;childdata.childNodes[0]!=null;i++){
    childdata.removeChild(childdata.childNodes[0]);
  }
}


//****************************************************
//***********The date related block ******************
var date=new Date();
var seldate=new Date();

//console.log(date.getMonth());
//console.log(date.getFullYear());


function DateToText(month){
  if(month==0){
    return 'January';
  }
  if(month==1){
    return 'Febuary';
  }
  if(month==2){
    return 'March';
  }
  if(month==3){
    return 'April';
  }
  if(month==4){
    return' May';
  }
  if(month==5){
    return 'June';
  }
  if(month==6){
    return 'July';
  }
  if(month==7){
    return 'August';
  }
  if(month==8){
    return 'September';
  }
  if(month==9){
    return 'October';
  }
  if(month==10){
    return 'November';
  }
  if(month==11){
    return 'December';
  }
}

makedatechild(date.getFullYear(),date.getMonth(),1);
document.getElementById(selencodedate).style.color='#1abc9c';
//****************************************************

//*******CALENDER RELATED*****************************
console.log("another mark"+seldate.getMonth());
document.getElementById('currmonth').textContent=DateToText(seldate.getMonth());
document.getElementById('curryear').textContent=curryear;

function makedatechild(selfullyear,selmonth,seldate){

  dateTemp.setFullYear(selfullyear);
  dateTemp.setMonth(selmonth);
  dateTemp.setDate(seldate);
  date=dateTemp.getDate();
  day=dateTemp.getDay();
  month=dateTemp.getMonth()+1;
  year=dateTemp.getFullYear();
  currmonth=month;
  currdate=date;
  curryear=year;
  //console.log(year+' '+month+' '+date+' '+day);
  //console.log('current '+curryear+'/'+currmonth+'/'+currdate);

  dateTemp.setDate(1);
  date=dateTemp.getDate();
  day=dateTemp.getDay();
  month=dateTemp.getMonth()+1;
  //console.log(month+' '+date+' '+day);
  console.log(currmonth+' '+currdate);

  for(i=0;(day%7!=6);i++){
    if(date!=1){
      k=date;
      dateTemp.setDate(k-1);
      date=dateTemp.getDate();
      day=dateTemp.getDay();
      month=dateTemp.getMonth()+1;
    }
    else{
      dateTemp.setDate(0);
      date=dateTemp.getDate();
      k=date;
      day=dateTemp.getDay();
      month=dateTemp.getMonth()+1;
    }
  }
  if(date==1&&day%7==6){
    dateTemp.setDate(0);
    date=dateTemp.getDate();
    k=date;
    day=dateTemp.getDay();
    month=dateTemp.getMonth()+1;
    for(i=0;i<6;i++){
      k=date;
      dateTemp.setDate(k-1);
      date=dateTemp.getDate();
      day=dateTemp.getDay();
      month=dateTemp.getMonth()+1;
      year=dateTemp.getFullYear();
    }
    //console.log(month+" "+date+" "+day);
  }
  //console.log(month+' '+date+' '+day);
  //console.log(month+' '+currmonth);

  for(i=0;i<7;i++){
    //console.log(month+' '+currmonth);
    if(((month==12&&currmonth==1)||((month<=currmonth)))){
      if(month==1&&currmonth==12){
        break;
      }
      else{
        for(j=0;j<7;j++){
          k=date;
          dateTemp.setDate(k+1);
          date=dateTemp.getDate();
          day=dateTemp.getDay();
          month=dateTemp.getMonth()+1;
          year=dateTemp.getFullYear();
          k++;
          //console.log(year+' '+month+' '+date+' '+day);
          var Caldates=document.createElement('li');
          Caldates.textContent = date;
          document.getElementById('displaydays').appendChild(Caldates);
          Caldates.onclick=clickdatescript;/*Ad not to click design*/
          Caldates.setAttribute("id", (100000*year+100*month+date));
      }
      }
      console.log(currmonth+' '+month);
    }
    else{
      break;
    }
  }
}

function clickdatescript(){
  var det;
  document.getElementById(selencodedate).style.color="black";
  //console.log(this.id);
  selencodedate=parseInt(this.id);
  //if((this.id)>15&&)
  //console.log(seldate.getDate());
  this.style.color='#1abc9c';
  console.log(selencodedate);
  var search=decodedate(selencodedate);
  console.log(search);
  selectedevent=0;
  cleanevents();
  SenddateAndAppend(search);
}
function page(command){
  var i;
  var j;
    console.log(selencodedate);
  i=seldate.getMonth();
  j=seldate.getFullYear();
  removechilddates();
  //console.log('i= '+i);
  if(command==1){
    if(seldate.getMonth()==11){
      seldate.setMonth(0);
      seldate.setFullYear(j+1);
      i=0;
      j++;
      selencodedate=100000*seldate.getFullYear()+100*(seldate.getMonth()+1)+1;
    }
    else{
      i++;
      seldate.setMonth(i);
      selencodedate=100000*seldate.getFullYear()+100*(seldate.getMonth()+1)+1;
    }
    cleanevents();
    SenddateAndAppend(selencodedate);
  }
  else if(command==-1){
    if(seldate.getMonth()==0){
      seldate.setMonth(11);
      seldate.setFullYear(j-1);
      i=11;
      j--;
      selencodedate=100000*seldate.getFullYear()+100*(seldate.getMonth()+1)+1;
    }
    else{
      i--;
      seldate.setMonth(i);
      selencodedate=100000*seldate.getFullYear()+100*(seldate.getMonth()+1)+1;
    }
    selectedevent=0;
    cleanevents();
    SenddateAndAppend(selencodedate);
  }
  makedatechild(j,i,1);
  document.getElementById('currmonth').textContent=DateToText(i);
  document.getElementById('curryear').textContent=j;
  document.getElementById(selencodedate).style.color='#1abc9c';
}
function removechilddates(){
  var i;
  var Fcalen=document.getElementById('displaydays');
  for(i=0;Fcalen.childNodes[0]!=null;i++){
    Fcalen.removeChild(Fcalen.childNodes[0]);
  }

}
//****************************************************
//*******modal related codes**************************
var cash_input;
var info
$(document).on('click','#windowaddConfirm',function(e){
  e.preventDefault();
  console.log('close modal');
  document.getElementById('smallwindowadd').style.display='none';
  cash_input=document.getElementsByName('cash')[0].value;
  info=document.getElementsByClassName('infotext')[0].value;
  type=document.getElementById('genreType').value;


  $.ajax({
    url:"final_add.php",
    method:"POST",
    data:{YMD:decodedate(selencodedate),MD:decodedateMD(selencodedate),Month:decodedateM(selencodedate),Day:decodedateD(selencodedate),Date:decodedate(selencodedate),Money:cash_input,Remark:info,Type:type},
    dataType:"text",
    success:function(){
      //alert("yes we made it");
      cleanevents();
      SenddateAndAppend(selencodedate);
    },
    error:function(){
      cleanevents();
      SenddateAndAppend(selencodedate);
    }
  })
  //cleanevents();
  //SenddateAndAppend(selencodedate);
})

function ConfirmCancel(){
  console.log('close modal');
  document.getElementById('smallwindowadd').style.display='none';
}

function openmodaladd(){
  document.getElementsByName('cash')[0].value="";
  document.getElementsByClassName('infotext')[0].value="";
  console.log('open modal');
  document.getElementById('smallwindowadd').style.display='block';
  console.log(selencodedate);
  document.getElementById('fulldate').value=decodedate(selencodedate);
}
function closemodaladd() {
  console.log('close modal');
  document.getElementById('smallwindowadd').style.display='none';
}

function decodedate(encodedate){
  var Yea;
  var Mon;
  var Dat;
  Yea=parseInt(selencodedate/100000);
  Mon=parseInt((selencodedate%100000)/100);
  Dat=selencodedate%100;
  if(Mon>9){
    tempconvert=Yea+'-'+Mon+'-'+Dat;
  }
  else{
    tempconvert=Yea+'-0'+Mon+'-'+Dat;
  }
  return tempconvert;
}
function decodedateMD(encodedate){
  var Mon=parseInt((selencodedate%100000)/100);
  var Dat=selencodedate%100;
  if(Mon>9){
    tempconvert=Mon+'-'+Dat;
  }
  else{
    tempconvert='0'+Mon+'-'+Dat;
  }
  return tempconvert;
}

function decodedateM(encodedate){
  var Mon=parseInt((selencodedate%100000)/100);
  if(Mon>9){
    return Mon;
  }
  else{
    return '0'+Mon;
  }
}

function decodedateD(encodedate){
  var Dat=encodedate%100;
  if(Dat>9){
    return Dat;
  }
  else{
    return '0'+Dat;
  }
}
//********************************************************

//************event related*******************************
function selectevent(thisid){
  document.getElementById(selectedevent*1000+1).style.color="#000";
  document.getElementById(selectedevent*1000+2).style.color="#000";
  document.getElementById(selectedevent*1000+3).style.color="#000";
  document.getElementById(selectedevent*1000+4).style.color="#000";
  document.getElementById(selectedevent*1000+1).style.backgroundColor="#eee";
  document.getElementById(selectedevent*1000+2).style.backgroundColor="#eee";
  document.getElementById(selectedevent*1000+3).style.backgroundColor="#eee";
  document.getElementById(selectedevent*1000+4).style.backgroundColor="#eee";
  selectedevent=parseInt(thisid/1000);
  console.log(selectedevent);
  document.getElementById(selectedevent*1000+1).style.color="#fff";
  document.getElementById(selectedevent*1000+2).style.color="#fff";
  document.getElementById(selectedevent*1000+3).style.color="#fff";
  document.getElementById(selectedevent*1000+4).style.color="#fff";
  document.getElementById(selectedevent*1000+1).style.backgroundColor="#1abc9c";
  document.getElementById(selectedevent*1000+2).style.backgroundColor="#1abc9c";
  document.getElementById(selectedevent*1000+3).style.backgroundColor="#1abc9c";
  document.getElementById(selectedevent*1000+4).style.backgroundColor="#1abc9c";
}
//********************************************************
//****************** delete button ***********************
function deletebutton(){
  var selevedate;
  var seleveinfo;
  var selevecash;
  selevedate=document.getElementById(selectedevent*1000+1).textContent;
  seleveinfo=document.getElementById(selectedevent*1000+2).textContent;
  selevetype=document.getElementById(selectedevent*1000+3).textContent;
  selevecash=document.getElementById(selectedevent*1000+4).textContent;
  alert(selevedate+" "+seleveinfo+" "+selevetype+" "+selevecash);

  $.ajax({
    url:"final_delete.php",
    method:"POST",
    data:{Date:selevedate,Remark:seleveinfo,Type:encodeevent(selevetype),Money:selevecash},
    dataType:"text",
    success:function(data){
        //alert(data);
        selectedevent=0;
        cleanevents();
        SenddateAndAppend(selencodedate);
    },
    error:function(datafail){
      //alert(datafail);
      selectedevent=0;
      cleanevents();
      SenddateAndAppend(selencodedate);
    }
  })
}
//********************************************************
//***************back to home ****************************
function backtohome(){
  window.location="http://makerthon.nthuee.org/2018/OnlineAccounting/";
}
//********************************************************

function decodeevent(string){
  if(string=="FOOD"){
    return "Food";
  }
  else if (string=="TRANSPORTS") {
    return "Transportation";
  }
  else if(string=="CLOTH"){
    return "Clothes";
  }
  else if(string=="TEACH"){
    return "Education";
  }
  else if(string=="ENTERTAIN"){
    return "Entertainment";
  }
  else if(string=="DAILY"){
    return "Daily uses";
  }
  else if(string=="DOCTOR"){
    return "Medical Expenses";
  }
  else if(string=="BILL"){
    return "Bill";
  }
}

function encodeevent(string){
  if(string=="Food"){
    return "FOOD";
  }
  else if(string=="Transportation"){
    return "TRANSPORTS";
  }
  else if(string=="Clothes"){
    return "CLOTH";
  }
  else if(string=="Education"){
    return "TEACH";
  }
  else if(string=="Entertainment"){
    return "ENTERTAIN";
  }
  else if(string=="Daily uses"){
    return "DAILY";
  }
  else if(string=="Medical Expenses"){
    return "DOCTOR";
  }
  else if(string=="Bill"){
    return "BILL";
  }
}
