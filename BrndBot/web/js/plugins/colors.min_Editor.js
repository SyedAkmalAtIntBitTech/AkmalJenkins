/*!
 * froala_editor v2.0.0-rc.3 (https://www.froala.com/wysiwyg-editor/v2.0)
 * License http://editor.froala.com/license
 * Copyright 2014-2015 Froala Labs
 */
var colorcodeArray;
 $.ajax({
    async: false,
    url: 'GetColorsServlet',
    dataType: 'json',
success:function(data){
    colorcodeArray=data;
}
 });
!function (a) {
    "use strict";
    a.extend(a.FroalaEditor.POPUP_TEMPLATES, {"colors.picker": "[_TABS_][_TEXT_COLORS_][_BACKGROUND_COLORS_]"}), a.extend(a.FroalaEditor.DEFAULTS, {colorsText: colorcodeArray , colorsBackground: ["#61BD6D", "#1ABC9C", "#54ACD2", "#2C82C9", "#9365B8", "#475577", "#CCCCCC", "#41A85F", "#00A885", "#3D8EB9", "#2969B0", "#553982", "#28324E", "#000000", "#F7DA64", "#FBA026", "#EB6B56", "#E25041", "#A38F84", "#EFEFEF", "#FFFFFF", "#FAC51C", "#F37934", "#D14841", "#B8312F", "#7C706B", "#D1D5D8", "REMOVE"], colorsStep: 6, colorsDefaultTab: "text"}), a.FroalaEditor.PLUGINS.colors = function (b) {
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
            var a = {tabs: f(), text_colors: g("text"), background_colors: g("background")}, c = b.popups.create("colors.picker", a);
            return c
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
            "REMOVE" != c ? b.commands.applyProperty("background-color", c) : (b.commands.applyProperty("background-color", "#123456"), b.selection.save(), b.$el.find("span").each(function (c, d) {
                var e = a(d), f = e.css("background-color");
                ("#123456" === f || "#123456" === b.helpers.RGBToHex(f)) && e.replaceWith(e.html())
            }), b.selection.restore()), d()
        }
        function k(c) {
            "REMOVE" != c ? b.commands.applyProperty("color", c) : (b.commands.applyProperty("color", "#123456"), b.selection.save(), b.$el.find("span").each(function (c, d) {
                var e = a(d), f = e.css("color");
                ("#123456" === f || "#123456" === b.helpers.RGBToHex(f)) && e.replaceWith(e.html())
            }), b.selection.restore()), d()
        }
        function l() {
        }
        return{_init: l, showColorsPopup: c, hideColorsPopup: d, changeSet: i, background: j, text: k}
    }, a.FroalaEditor.DefineIcon("colors", {NAME: "tint"}), a.FroalaEditor.RegisterCommand("color", {title: "Colors", undo: !1, focus: !1, refreshOnCallback: !1, popup: !0, callback: function () {
            this.popups.isVisible("colors.picker") ? this.popups.hide("colors.picker") : this.colors.showColorsPopup()
        }}), a.FroalaEditor.RegisterCommand("textColor", {undo: !0, callback: function (a, b) {
            this.colors.text(b)
        }}), a.FroalaEditor.RegisterCommand("backgroundColor", {undo: !0, callback: function (a, b) {
            this.colors.background(b)
        }}), a.FroalaEditor.RegisterCommand("colorChangeSet", {undo: !1, focus: !1, callback: function (a, b) {
            var c = this.popups.get("colors.picker").find('.fr-command[data-cmd="' + a + '"][data-param1="' + b + '"]');
            this.colors.changeSet(c, b)
        }})
}(jQuery);