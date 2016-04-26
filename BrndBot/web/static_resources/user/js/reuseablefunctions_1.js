/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//$(document).ready(function (){
function  imagefilevalidation(id) {
    if ($("#"+id).val() === "") {
        alert(chooseimage);
        return false;
    }
    else
    {
        var filename=$("#"+id).val();
        var array=filename.split('.');
        var length=array.length;
        var extenion=array[length-1];
        var error=1;
        switch (extenion)
        {
            case 'jpg':
                    error=0;
                    break;
            case 'png':
                    error=0;
                    break;
            case 'jpeg':
                    error=0;
                    break;
            case 'JPG':
                    error=0;
                    break;
            case 'PNG':
                    error=0;
                    break;
            case 'JPEG':
                    error=0;
                    break;
            case 'svg':
                    error=0;
                    break;
            case 'SVG':
                    error=0;
                    break;
            case 'bmp':
                    error=0;
                    break;
            case 'BMP':
                    error=0;
                    break;
            case 'TIF':
                    error=0;
                    break;
            case 'tif':
                    error=0;
                    break;
            case 'gif':
                    error=0;
                    break;
            case 'GIF':
                    error=0;
                    break;
            case 'PSD':
                    error=0;
                    break;
            case 'psd':
                    error=0;
                    break;
            case 'yuv':
                    error=0;
                    break;
            case 'YUV':
                    error=0;
                    break;
            case 'THM':
                    error=0;
                    break;
            case 'thm':
                    error=0;
                    break;
            case 'PSPIMAGE':
                    error=0;
                    break;
            case 'pspimage':
                    error=0;
                    break;
        }
        if(error===1)
        {
            $("#"+id).val("");
            alert(errorimagefile);
            return false;
        }
        else return true;
    }
}
//});