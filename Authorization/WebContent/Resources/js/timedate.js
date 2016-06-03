function digitalWatch() {
    var date = new Date();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();
    if (hours < 10) hours = "0" + hours;
    if (minutes < 10) minutes = "0" + minutes;
    if (seconds < 10) seconds = "0" + seconds;
    document.getElementById("digital_watch").innerHTML = hours + ":" + minutes + ":" + seconds;
    setTimeout("digitalWatch()", 1000);
	currentDate();
  }
function currentDate() {
    var d=new Date();
	var day=d.getDate();
	var month=d.getMonth() + 1;
	var year=d.getFullYear();
	if (day < 10) day = "0" + day;
    if (month < 10) month = "0" + month;
    if (year < 10) year = "0" + year;
	document.getElementById("current_date").innerHTML = day + "." + month + "." + year;
	setTimeout("currentDate()", 1000);
  }