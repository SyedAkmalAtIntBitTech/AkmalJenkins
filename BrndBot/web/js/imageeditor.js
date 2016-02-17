 var number=0;
 var featherEditor = new Aviary.Feather({
       apiKey: '75ed2ca5-7d87-454f-ab9f-e46446ed542d',
       apiVersion: 3,
       theme: 'dark', // Check out our new 'light' and 'dark' themes!
       tools: 'all',
       appendTo: '',
       onSave: function(imageID, newURL) {  
            if(number>1){
                var img = document.getElementById("twitterpreviewimage");
                img.src = newURL;

                var img = document.getElementById("facebookpreviewimage");
                img.src = newURL;
            }
            else{
                var img = document.getElementById(imageID);
                img.src = newURL;
            }
       },
       onError: function(errorObj) {
       }
   }); 
   
      function launchEditor(id, src) {
       featherEditor.launch({
           image: id,
           url: src
       });
      return false;
       }
       var clickedImageId="";
       $(function() {
            $(".feditImage").click(function(){  
                var facebook=$("#isFacebook").val();
                var twitter=$("#isTwitter").val();
                if(facebook==="true" && twitter==="true") { number=2; }
                if(facebook==="true" && twitter!=="true") { number=1; }
                if(facebook!=="true" && twitter==="true") { number=1; }                
                var idvalue=this.id; 
                if(idvalue === "facebookimg") { clickedImageId="facebookpreviewimage";  }       
                if(idvalue === "twitterimg" && number==2) { clickedImageId="facebookpreviewimage";  }
                if(idvalue === "twitterimg" && number==1) { clickedImageId="twitterpreviewimage";  }
                launchEditor(clickedImageId);  
            });
       });