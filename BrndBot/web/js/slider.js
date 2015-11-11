/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function overlay(){
                        document.getElementById('light').style.display = 'block';
                            document.getElementById('fade').style.display = 'block';
                             document.getElementById('blk').style.display = 'block';
                            document.getElementById('slider-button').style.display = 'block';
                            document.body.style.overflow = 'hidden';
                            $("#calendar").css("pointer-events","none");
                    }
            function closeoverlay(){
                    document.getElementById('light').style.display = 'none';
                    $("#calendar").css("pointer-events","auto");
                    document.getElementById('fade').style.display = 'none';
                    document.body.style.overflow = 'scroll';
                     document.getElementById('blk').style.display = 'none';
                    document.getElementById('edtfbimg').style.display = 'none';
                    document.getElementById('prevtwtimg').style.display = 'none';
                    document.getElementById('edttwtimg').style.display = 'none';
                    document.getElementById('prevfbimg').style.display = 'none';
            }
            function cancelform()
            {   
//                var chng=false;
//                if(chng)
                $("#signform").change(function() {
//                    alert("changed");
                })
        //    $("#"+cancelbtn).click(function(){
                if(confirm("Do you want to cancel the process?")){
                    $('#slider-button').click();

                }
        //    })
            }
