/*!
 * froala_editor v2.0.0-rc.3 (https://www.froala.com/wysiwyg-editor/v2.0)
 * License http://editor.froala.com/license
 * Copyright 2014-2015 Froala Labs
 */
var FontArray;
               $.ajax({
                   async: false,
                   url: global_host_address+"GetFontsServlet",
                   dataType: 'json',
                   success:function(data){
                    FontArray=data;   
                    }
                });
!function(a){"use strict";a.extend(a.FroalaEditor.DEFAULTS,{fontFamily:FontArray,fontFamilySelection:!1}),a.FroalaEditor.PLUGINS.fontFamily=function(b){function c(a){b.commands.applyProperty("font-family",a)}function d(c,d){var e=a(b.selection.element()).css("font-family").replace(/"/g,"'").replace(/ /g,"");d.find(".fr-command.fr-active").removeClass("fr-active"),d.find('.fr-command[data-param1="'+e+'"]').addClass("fr-active");var f=d.find(".fr-dropdown-list"),g=d.find(".fr-active").parent();g.length?f.parent().scrollTop(g.offset().top-f.offset().top-(f.parent().outerHeight()/2-g.outerHeight()/2)):f.parent().scrollTop(0)}function e(c,d){var e=a(b.selection.element()).css("font-family");c.find("> span").text(d.find('.fr-command[data-param1="'+e.replace(/"/g,"'").replace(/ /g,"")+'"]').text()||"Times New Roman")}return{apply:c,refreshOnShow:d,refresh:e}},a.FroalaEditor.RegisterCommand("fontFamily",{type:"dropdown",displaySelection:function(a){return a.opts.fontFamilySelection},displaySelectionWidth:120,defaultSelection:"Times New Roman",html:function(){var a='<ul class="fr-dropdown-list">',b=this.opts.fontFamily;for(var c in b)a+='<li><a class="fr-command" data-cmd="fontFamily" data-param1="'+c+'" style="font-family: '+b[c]+'" title="'+b[c]+'">'+b[c]+"</a></li>";return a+="</ul>"},title:"Font Family",callback:function(a,b){this.fontFamily.apply(b)},refresh:function(a,b){this.fontFamily.refresh(a,b)},refreshOnShow:function(a,b){this.fontFamily.refreshOnShow(a,b)}}),a.FroalaEditor.DefineIcon("fontFamily",{NAME:"font"})}(jQuery);