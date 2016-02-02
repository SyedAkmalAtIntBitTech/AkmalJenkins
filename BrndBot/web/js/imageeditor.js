 var featherEditor = new Aviary.Feather({
       apiKey: '75ed2ca5-7d87-454f-ab9f-e46446ed542d',
       apiVersion: 3,
       theme: 'dark', // Check out our new 'light' and 'dark' themes!
       tools: 'all',
       appendTo: '',
       onSave: function(imageID, newURL) {
           var img = document.getElementById(imageID);
           img.src = newURL;
           if(imageID === "facebookpreviewimage")
           {
               imageID = "twitterpreviewimage";
               var img = document.getElementById(imageID);
               img.src = newURL;
           }
           if(imageID === "twitterpreviewimage")
           {
               imageID = "facebookpreviewimage";
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
       var clickedImageId=0;
       $(function() {
        if(clickedImageId === 0)
        {
            $(".feditImage").click(function(){
                clickedImageId=$("#facebookpreviewimage").attr("id");
                launchEditor($("#facebookpreviewimage").attr("id"));
            });
            $("#twitterimg").click(function(){   
                clickedImageId=$("#twitterpreviewimage").attr("id");
                launchEditor($("#twitterpreviewimage").attr("id"));
            });
        }
        else
        {
            launchEditor(clickedImageId);
        }
       });