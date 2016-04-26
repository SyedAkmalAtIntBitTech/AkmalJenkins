/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
var base64ImgString = "";
var imageFileName = "";
var addImageFileName ="";


function addImgToBase64Converter(id) {
    var obj =  document.getElementById(id);
    obj.addEventListener("change", readFile, false);
}

function readFile() {
    if (this.files.length === 1) {
        var reader = new FileReader();
        var file = this.files[0];
        imageFileName = file.name;
         addImageFileName = file.name;
        reader.addEventListener("load", function () {
            var data = reader.result;
            base64ImgString = data;
        }, false);
        reader.readAsDataURL(file);
    }

}

function getImageData(){
 
    return {
        "imageFileName" : imageFileName,
        "base64ImgString" : base64ImgString,
         "addImageFileName" : addImageFileName,
    };
}
       