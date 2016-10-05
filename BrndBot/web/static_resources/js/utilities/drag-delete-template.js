/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
    $(document).ready(function () {
        dragactions();
        removeElm();
        $(".editor_rightside .emailrender").sortable({
            connectWith: ".module",
            opacity: .8,
            handle: ".drag",
            axis: "y",
            placeholder: 'highlight',
            helper: function () {
                return $("<div class='highlight' style='height:40px;text-align:center;line-height:40px;'><div style='background-color:#0F76A6;width:600px;margin:auto; color:#ffffff;'>Move This Block</div></div>");
            }
        });

        $(".modules_sidebar .module").draggable({
            connectToSortable: ".emailrender",
            placeholder: "highlight",
            helper: function () {
                var modimg = $(this).find('.mod_thumb img').attr('src');
                return $("<div style='position:absolute;background-color:#ffffff;border-radius:3px;border:1px solid #dadada;width:40px;height:40px;'><img src='" + modimg + "' width='40' height='40'></div>");
            },
            cursorAt: {left: -1, top: -10},
            drag: function (e, t) {
                t.helper.width(40);

                t.helper.find('.preview').remove();
                t.helper.find('.module').css({"background": "none!important", "border": "none!important"});
                $('.emailrender').find('.preview').remove();
            },
            stop: function () {
                $('.view').find('table:first').find('table:first .colorSelectorinner:first').css({"margin-left": "-20px"});
                $('.emailrender a').click(function (event) {
                    event.preventDefault();
                });
                dragactions();
                move_up_down();
                colorselector();
                $('.innerbg').mouseenter(function () {
                    var seldiv = $(this).parents('[bb-bgcolor]');
                    $(this).colpick({
                        layout: 'hex',
                        submit: 0,
                        onChange: function (hsb, hex, rgb, fromSetColor) {
                            $(seldiv).find('table:first').attr('bgcolor', '#' + hex);
                        }
                    });
                });
                upload_buttons();

            }
        });
    });
    function dragactions() {
        $('.view').find('table:first').find('td:first').mouseenter(function () {
            $(this).find('table:first').addClass('selecthover');
        });
        $('.view').find('table:first').find('td:first').mouseleave(function () {
            $(this).find('table:first').removeClass('selecthover');
        });
    }
    function removeElm() {
        $(".emailrender").delegate(".remove", "click", function (e) {
            e.preventDefault();
            $(this).closest('table').parents('.module ').slideUp('200', function () {
                $(this).remove();
            });
            $(this).closest('.remove').css("opacity", "0");
            $(this).parents('.view').find('table:first').find('table:first').removeClass('selecthoverred');
            $(this).parents('.view').find('.move_controls').show();
        });
    }