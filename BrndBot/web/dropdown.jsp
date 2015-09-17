<%-- 
    Document   : dropdown
    Created on : Aug 17, 2015, 4:23:04 PM
    Author     : Sandeep Kumar at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/theone.css"> 
        <link rel="stylesheet" type="text/css" href="css/pages.css"> 
        <link rel="stylesheet" type="text/css" href="css/patterns.css"> 
    <script src="js/dojo.js" data-dojo-config="parseOnLoad: true, usePlainJson: true, isDebug: false"></script> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     
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
                                font-weight:600;
				color: #000000;
                                border-top-left-radius: 5px;
                                border-bottom-left-radius:  5px;
                               background-color: #C6C6C6;
                                border:transparent;
			}
                        .selectBox{
                                position:relative;
				display:inline-block;
				cursor:default;
				text-align:left;
				line-height:30px;
				clear:both;
                                font-weight:600;
				color: #000000;
                                border-top-left-radius: 5px;
                                border-bottom-left-radius:  5px;
                                background-color: #C6C6C6;
                                border:transparent;
                                
                        }
			span.selected
			{
				width:167px;
				text-indent:10px;
				border:0px solid #ccc;
                                border-top-left-radius: 5px;
                                border-bottom-left-radius:  5px;
				border-right:none;
				background-color: #C6C6C6;
				overflow:hidden;
			}
			span.selectArrow
			{
				width:20px;
				text-align:center;
				font-size:22px;
                                border-top-right-radius:5px;
                                border-bottom-right-radius:5px;
				-webkit-user-select: none;
				-khtml-user-select: none;
				-moz-user-select: none;
				-o-user-select: none;
				user-select: none;
				background-image: url('images/dropdown.png');
                                background-repeat: space;
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
				top:35px;
				left:0;
				width:188px;
				border:1px solid #ccc;
                                border-radius:7px;
				overflow:hidden;
				background:#f6f6f6;
				padding-top:2px;
				display:none;
			}
				
			span.selectOption
			{   
                             
				display:block;
				width:100%;
				line-height:20px;
				padding:5px 10%;
                        }
			
			span.selectOption:hover
			{
				color: #000000;
				background:#7ab5d3;
                                
			}	
                        select#s11 option{
                            border: 20px red solid ;
                            border-width: 200px;
                            border-radius: 10px;
                            position: relative;
                            font-size: 18pt;
                            font-weight:normal;
                            color: #888;
                            background: #fff;
                            
                        }
                        select#s11 option:focus{
                            color: #000000;
                            background: #7ab5d3;
                        }
                        #s11{
                            font-size: 18pt;
                            font-weight:normal;
                            position:relative;
                            left:50%;
				display:inline-block;
				cursor:default;
				text-align:left;
				line-height:30px;
				clear:both;
				color: #000;
                                background: #ccc;
                                border-radius: 5px;
                            
                        }
		</style>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
           <script src="js/colpick.js" type="text/javascript"></script>
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/jquery.autogrow-textarea.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                
                
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
           <jsp:include page="basejsp.jsp" />
    </head>
    <body>
      
        <select id="s11" maxheight="-1" data-dojo-type="dijit/form/Select"><option  value="">Select an option</option> <option value="1">option 1</option> <option value="2">option 2</option> <option value="3">option 3</option> </select>
        
        
        
            <div class='selectBox'>
			<span class='selected'></span>
			<span class='selectArrow'></span>
			<div class="selectOptions">
				<span class="selectOption" value="Option 1">Option 1</span>
				<span class="selectOption" value="Option 2">Option 2</span>
				<span class="selectOption" value="Option 3">Option 3</span>
			</div>
		</div>
        
    </body>
   
</html>
