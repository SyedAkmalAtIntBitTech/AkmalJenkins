<%-- 
    Document   : history
    Created on : Aug 25, 2015, 6:46:46 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
       <head>
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <link rel="SHORTCUT ICON" href="images/Layout-styles/logo_small.png"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
        <link href="css/colpick.css" rel="stylesheet" type="text/css"/>
         <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/jquery.autogrow-textarea.js" type="text/javascript"></script>
          <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">           
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <link href="css/crop.css" rel="stylesheet" type="text/css"/>
        <link href="css/example.css" rel="stylesheet" type="text/css"/>
        
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <link rel="SHORTCUT ICON" href="images/Layout-styles/logo_small.png"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
           <script src="js/colpick.js" type="text/javascript"></script>
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/jquery.autogrow-textarea.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Email Settings</title>
        <script src="js/crop.js" type="text/javascript"></script>
       
        
                <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dojo/resources/dojo.css">
		<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dijit/themes/claro/claro.css">
                <script src="http://ajax.googleapis.com/ajax/libs/dojo/1.6.1/dojo/dojo.xd.js"data-dojo-config="isDebug: true,parseOnLoad: true">
		</script>

         <script>
                dojo.require("dojo.data.ItemFileWriteStore");
		dojo.require("dojo.data.ItemFileReadStore");
                dojo.require("dijit.form.Select");
    
    
// Data here for store-based selects
		var data1 = {
				identifier: "value",
				label: "label",
				items: [ 
					{value: "1", label: "Apple"},
					{value: "2", label: "Pear"},
					{value: "3", label: "Banana"},
					{value: "4", label: "Kiwi"},
					{value: "5", label: "Strawberry"},
					{value: "6", label: "Orange"}
				]
		};		
// END Data and stores here for store-based selects
 
    
    
    </script>

    <style type='text/css'>
			div.selectBox
			{
				position:relative;
				display:inline-block;
				cursor:default;
				text-align:left;
				line-height:30px;
				clear:both;
				color:#888;
			}
			span.selected
			{
				width:167px;
				text-indent:20px;
				border:1px solid #ccc;
				border-right:none;
				border-top-left-radius:5px;
				border-bottom-left-radius:5px;
				background:#f6f6f6;
				overflow:hidden;
			}
			span.selectArrow
			{
				width:30px;
				border:1px solid #60abf8;
				border-top-right-radius:5px;
				border-bottom-right-radius:5px;
				text-align:center;
				font-size:20px;
				-webkit-user-select: none;
				-khtml-user-select: none;
				-moz-user-select: none;
				-o-user-select: none;
				user-select: none;
				background:#4096ee;
			}
			
			span.selectArrow,span.selected
			{
				position:relative;
				float:left;
				height:30px;
				z-index:1;
			}
			
			div.selectOptions
			{
				position:absolute;
				top:28px;
				left:0;
				width:198px;
				border:1px solid #ccc;
				border-bottom-right-radius:5px;
				border-bottom-left-radius:5px;
				overflow:hidden;
				background:#f6f6f6;
				padding-top:2px;
				display:none;
			}
				
			span.selectOption
			{
				display:block;
				width:80%;
				line-height:20px;
				padding:5px 10%;
			}
			
			span.selectOption:hover
			{
				color:#f6f6f6;
				background:#4096ee;	
			}			
		</style>
    <script type='text/javascript' src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
    <script type='text/javascript'><!--
			$(document).ready(function() {
				enableSelectBoxes();
			});
			
			function enableSelectBoxes(){
				$('div.selectBox').each(function(){
					$(this).children('span.selected').html($(this).children('div.selectOptions').children('span.selectOption:first').html());
					$(this).attr('value',$(this).children('div.selectOptions').children('span.selectOption:first').attr('value'));
					
					$(this).children('span.selected,span.selectArrow').click(function(){
						if($(this).parent().children('div.selectOptions').css('display') == 'none'){
							$(this).parent().children('div.selectOptions').css('display','block');
						}
						else
						{
							$(this).parent().children('div.selectOptions').css('display','none');
						}
					});
					
					$(this).find('span.selectOption').click(function(){
						$(this).parent().css('display','none');
						$(this).closest('div.selectBox').attr('value',$(this).attr('value'));
						$(this).parent().siblings('span.selected').html($(this).html());
					});
				});				
			}//-->
		</script>
        
        
        
        
    </head>
    <body ng-app="myapp" class="claro">
        <div  class="container" id="container"> 
            <div class="row">
                 <jsp:include page="leftmenu.html"/><!--/end left column-->
            </div>

      <jsp:include page="submenu.html"/>
    <div class="col-md-8 col-md-offset-2 " ng-app="myapp">
        <div class="col-md-6 col-md-offset-0"><p id="hyshead">Email Settings</p></div>
        <div class="col-md-6 col-md-offset-0 bgcols">

           
 <select id="s11" data-dojo-id="s11" data-dojo-type="dijit.form.Select"  data-dojo-props="name:'s11',store:new dojo.data.ItemFileWriteStore({data:dojo.clone(data1)})">
 </select>

 <div class='selectBox'>
			<span class='selected'></span>
			<span class='selectArrow'>&#9660</span>
			<div class="selectOptions" >
				<span class="selectOption" value="Option 1">Option 1</span>
				<span class="selectOption" value="Option 2">Option 2</span>
				<span class="selectOption" value="Option 3">Option 3</span>
			</div>
		</div>
 
        
    </div>
        </div>
    </body>
</html>