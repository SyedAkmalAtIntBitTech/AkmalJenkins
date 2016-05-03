/*!
 * froala_editor v2.0.1 (https://www.froala.com/wysiwyg-editor)
 * License https://froala.com/wysiwyg-editor/terms
 * Copyright 2014-2015 Froala Labs
 */
var colorcodeArray=[];
var hexDigits = new Array
        ("0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"); 

//Function to convert hex format to a rgb color
function rgb2hex(rgb) {
 rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
 return "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
}

function hex(x) {
  return isNaN(x) ? "00" : hexDigits[(x - x % 16) / 16] + hexDigits[x % 16];
 }

 $.ajax({
    async: false,
    url: global_host_address + 'settings/getColors.do',
    dataType: 'json',
success:function(data){
    for(var i=0;i<data.d.details.length;i++)
    {
        colorcodeArray[i] = rgb2hex(data.d.details[i]);
    }
}
 });
 
 

!function (a) {
    "function" == typeof define && define.amd ? module.exports = function (b, c) {
        return void 0 === c && (c = "undefined" != typeof window ? require("jquery") : require("jquery")(b)), a(c), c
    } : a(jQuery)
}(function (a) {
    "use strict";
    a.extend(a.FroalaEditor.POPUP_TEMPLATES, {"colors.picker": "[_BUTTONS_][_TEXT_COLORS_][_BACKGROUND_COLORS_]"}), a.extend(a.FroalaEditor.DEFAULTS, {colorsText: colorcodeArray, colorsBackground: colorcodeArray, colorsStep: 6, colorsDefaultTab: "text", colorsButtons: ["colorsBack", "|", "-"]}), a.FroalaEditor.PLUGINS.colors = function (b) {
        function c() {
            var a = b.$tb.find('.fr-command[data-cmd="color"]'), c = b.popups.get("colors.picker");
            if (c || (c = e()), !c.hasClass("fr-active")) {
                b.popups.setContainer("colors.picker", b.$tb), h(c.find(".fr-selected-tab").attr("data-param1"));
                var d = a.offset().left + a.outerWidth() / 2, f = a.offset().top + (b.opts.toolbarBottom ? 10 : a.outerHeight() - 10);
                b.popups.show("colors.picker", d, f, a.outerHeight())
            }
        }
        function d() {
            b.popups.hide("colors.picker")
        }
        function e() {
            var a = '<div class="fr-buttons fr-colors-buttons">';
            b.opts.toolbarInline && b.opts.colorsButtons.length > 0 && (a += b.button.buildList(b.opts.colorsButtons)), a += f() + "</div>";
            var c = {buttons: a, text_colors: g("text"), background_colors: g("background")}, d = b.popups.create("colors.picker", c);
            return d
        }
        function f() {
            var a = '<div class="fr-colors-tabs">';
            return a += '<span class="fr-colors-tab ' + ("background" == b.opts.colorsDefaultTab ? "" : "fr-selected-tab ") + 'fr-command" data-param1="text" data-cmd="colorChangeSet" title="' + b.language.translate("Text") + '">' + b.language.translate("Text") + "</span>", a += '<span class="fr-colors-tab ' + ("background" == b.opts.colorsDefaultTab ? "fr-selected-tab " : "") + 'fr-command" data-param1="background" data-cmd="colorChangeSet" title="' + b.language.translate("Background") + '">' + b.language.translate("Background") + "</span>", a + "</div>"
        }
        function g(a) {
            for (var c = "text" == a ? b.opts.colorsText : b.opts.colorsBackground, d = '<div class="fr-color-set fr-' + a + "-color" + (b.opts.colorsDefaultTab == a || "text" != b.opts.colorsDefaultTab && "background" != b.opts.colorsDefaultTab && "text" == a ? " fr-selected-set" : "") + '">', e = 0; e < c.length; e++)
                0 !== e && e % b.opts.colorsStep === 0 && (d += "<br>"), d += "REMOVE" != c[e] ? '<span class="fr-command fr-select-color" style="background: ' + c[e] + ';" data-cmd="' + a + 'Color" data-param1="' + c[e] + '"></span>' : '<span class="fr-command fr-select-color" data-cmd="' + a + 'Color" data-param1="REMOVE" title="' + b.language.translate("Clear Formatting") + '"><i class="fa fa-eraser"></i></span>';
            return d + "</div>"
        }
        function h(c) {
            var d, e = b.popups.get("colors.picker"), f = a(b.selection.element());
            for (d = "background" == c ? "background-color" : "color", e.find(".fr-" + c + "-color .fr-select-color").removeClass("fr-selected-color"); f.get(0) != b.$el.get(0); ) {
                if ("transparent" != f.css(d) && "rgba(0, 0, 0, 0)" != f.css(d)) {
                    e.find(".fr-" + c + '-color .fr-select-color[data-param1="' + b.helpers.RGBToHex(f.css(d)) + '"]').addClass("fr-selected-color");
                    break
                }
                f = f.parent()
            }
        }
        function i(a, b) {
            a.hasClass("fr-selected-tab") || (a.siblings().removeClass("fr-selected-tab"), a.addClass("fr-selected-tab"), a.parents(".fr-popup").find(".fr-color-set").removeClass("fr-selected-set"), a.parents(".fr-popup").find(".fr-color-set.fr-" + b + "-color").addClass("fr-selected-set"), h(b))
        }
        function j(c) {
            "REMOVE" != c ? b.commands.applyProperty("background-color", b.helpers.HEXtoRGB(c)) : (b.commands.applyProperty("background-color", "#123456"), b.selection.save(), b.$el.find("span").each(function (c, d) {
                var e = a(d), f = e.css("background-color");
                ("#123456" === f || "#123456" === b.helpers.RGBToHex(f)) && e.replaceWith(e.html())
            }), b.selection.restore()), d()
        }
        function k(c) {
            "REMOVE" != c ? b.commands.applyProperty("color", b.helpers.HEXtoRGB(c)) : (b.commands.applyProperty("color", "#123456"), b.selection.save(), b.$el.find("span").each(function (c, d) {
                var e = a(d), f = e.css("color");
                ("#123456" === f || "#123456" === b.helpers.RGBToHex(f)) && e.replaceWith(e.html())
            }), b.selection.restore()), d()
        }
        function l() {
            b.popups.hide("colors.picker"), b.toolbar.showInline()
        }
        function m() {
        }
        return{_init: m, showColorsPopup: c, hideColorsPopup: d, changeSet: i, background: j, text: k, back: l}
    }, a.FroalaEditor.DefineIcon("colors", {NAME: "tint"}), a.FroalaEditor.RegisterCommand("color", {title: "Colors", undo: !1, focus: !0, refreshOnCallback: !1, popup: !0, callback: function () {
            this.popups.isVisible("colors.picker") ? (this.$el.find(".fr-marker") && (this.events.disableBlur(), this.selection.restore()), this.popups.hide("colors.picker")) : this.colors.showColorsPopup()
        }}), a.FroalaEditor.RegisterCommand("textColor", {undo: !0, callback: function (a, b) {
            this.colors.text(b)
        }}), a.FroalaEditor.RegisterCommand("backgroundColor", {undo: !0, callback: function (a, b) {
            this.colors.background(b)
        }}), a.FroalaEditor.RegisterCommand("colorChangeSet", {undo: !1, focus: !1, callback: function (a, b) {
            var c = this.popups.get("colors.picker").find('.fr-command[data-cmd="' + a + '"][data-param1="' + b + '"]');
            this.colors.changeSet(c, b)
        }}), a.FroalaEditor.DefineIcon("colorsBack", {NAME: "arrow-left"}), a.FroalaEditor.RegisterCommand("colorsBack", {title: "Back", undo: !1, focus: !1, back: !0, refreshAfterCallback: !1, callback: function () {
            this.colors.back()
        }})
});