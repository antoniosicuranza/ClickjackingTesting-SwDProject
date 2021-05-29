<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clickjacking test</title>
<link rel="stylesheet" href="style.css">
<script type="text/javascript">  
function selects(){  
    var ele=document.getElementsByClassName('test');  
    for(var i=0; i<ele.length; i++){  
        if(ele[i].type=='checkbox')  
            ele[i].checked=true;  
    }  
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
	  var checkedOne = Array.prototype.slice.call(checkboxes).some(x => x.checked);

	  document.querySelectorAll('input[type="submit"]')[0].disabled = true;
	  if (checkedOne) {
	    document.querySelectorAll('input[type="submit"]')[0].disabled = false;
	  }
}  
function callFunction() {
	  var checkboxes = document.querySelectorAll('input[type="checkbox"]');
	  var checkedOne = Array.prototype.slice.call(checkboxes).some(x => x.checked);

	  document.querySelectorAll('input[type="submit"]')[0].disabled = true;
	  if (checkedOne) {
	    document.querySelectorAll('input[type="submit"]')[0].disabled = false;
	  }
	}
function deSelect(){  
    var ele=document.getElementsByClassName('test');  
    for(var i=0; i<ele.length; i++){  
        if(ele[i].type=='checkbox')  
            ele[i].checked=false;  
          
    } 
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
	  var checkedOne = Array.prototype.slice.call(checkboxes).some(x => x.checked);

	  document.querySelectorAll('input[type="submit"]')[0].disabled = true;
	  if (checkedOne) {
	    document.querySelectorAll('input[type="submit"]')[0].disabled = false;
	  }
}
function change(){
	var ele=document.getElementById('filterXSS');  
	var ele2=document.getElementById('extra');
	if(ele.checked==true){
		ele2.checked=true;
	}else{
		ele2.checked=false;
	}
	 var checkboxes = document.querySelectorAll('input[type="checkbox"]');
	  var checkedOne = Array.prototype.slice.call(checkboxes).some(x => x.checked);

	  document.querySelectorAll('input[type="submit"]')[0].disabled = true;
	  if (checkedOne) {
	    document.querySelectorAll('input[type="submit"]')[0].disabled = false;
	  }
}
function change2(){
	var ele=document.getElementById('disablingJavascript');  
	var ele2=document.getElementById('extra2');
	if(ele.checked==true){
		ele2.checked=true;
	}else{
		ele2.checked=false;
	}
	 var checkboxes = document.querySelectorAll('input[type="checkbox"]');
	  var checkedOne = Array.prototype.slice.call(checkboxes).some(x => x.checked);

	  document.querySelectorAll('input[type="submit"]')[0].disabled = true;
	  if (checkedOne) {
	    document.querySelectorAll('input[type="submit"]')[0].disabled = false;
	  }
}

function loading(){
	var ele=document.getElementById('load');  
	ele.style.opacity=1;
}

</script>
</head>
<body>
<div align="center" style="margin-top:3%;"><img alt="Clickjacking test" src="images/logo.jpg"height=25% width=25% align="top" >
</div>
<div style="margin-top:3%;">
	<form action="ReferenceClickjackingSites" method="get">

		<p align="center" style="font-family: arial;font-size: 23px;">
		Insert the URL to test:
		<input  type="text" class="css-input" placeholder="es. http://localhost:3000" name="url"/>
		</p>

		<p style="font-family: arial;font-size: 28px;margin-top:3%;" align="center">
		Select cases to test:
		</p>
 		<div class="control-group" style="margin-top:2%;"align="center">
     		<input type="button" class="button_all" onclick='selects()' value="Select All"/> 
  			<input type="button" class="button_all" onclick='deSelect()' value="Deselect All"/>  
  			 
   			<label class="control control-checkbox" style="margin-top:3%;">
       			<input type="checkbox" id="disablingJavascript" class="test" name="0" value="1" onclick="change2()"/><div class="control_indicator"></div>
      			Disabling Javascript         
  			</label>
    
   			<label class="control control-checkbox">
     			<input type="checkbox" id="doubleFraming" class="test" name="2" value="1" onclick="callFunction()"/>   <div class="control_indicator"></div>
        		Double Framing
		    </label>
    
    		<label class="control control-checkbox">
        		<input type="checkbox" id="filterXSS" class="test" name="3" value="1" onclick="change()"/><div class="control_indicator"></div>
        		Filter XSS
    		</label>
  			
		    <label class="control control-checkbox">
		      	<input type="checkbox"  id="onBeforeUnloadEvent" class="test" name="5" value="1" onclick="callFunction()"/> <div class="control_indicator"></div> 
		       	onBeforeUnload Event
		    </label>
		    
		    <label class="control control-checkbox">
		       	<input type="checkbox"  id="redefiningLocation" class="test" name="6" value="1" disabled/><div class="control_indicator"></div> 
		        Redefining Location
		    </label>
		    
        		<input type="checkbox" id="extra" class="test" name="4" value="1" style="opacity:0%;"  />
        		<input type="checkbox" id="extra2"  class="test" name="1" value="1" style="opacity:0%;"/>
        	

    		<p align="center">
    			<input type="submit" class="button_start" value="Start testing!" disabled/>
    		</p>
   			</div>
		</div>
	</form>
</div>
</body>
</html>