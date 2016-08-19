/*!
 * froala_editor v2.0.0-rc.3 (https://www.froala.com/wysiwyg-editor/v2.0)
 * License http://editor.froala.com/license
 * Copyright 2014-2015 Froala Labs
 */
var image;
 function uploadImageToEditor(imageid){
        image=imageid;
            }


!function (a) {
    "use strict";
    a.extend(a.FroalaEditor.POPUP_TEMPLATES, {"image.insert": "[_BUTTONS_][_UPLOAD_LAYER_][_BY_URL_LAYER_][_PROGRESS_BAR_]", "image.edit": "[_BUTTONS_]", "image.alt": "[_BUTTONS_][_ALT_LAYER_]", "image.size": "[_BUTTONS_][_SIZE_LAYER_]"}), a.extend(a.FroalaEditor.DEFAULTS, {imageInsertButtons: ["imageBack", "|", "imageUpload"], imageEditButtons: ["imageReplace", "imageRemove", "|", "imageLink", "linkOpen", "linkEdit", "linkRemove", "imageStyle"], imageAltButtons: ["imageBack", "|"], imageSizeButtons: ["imageBack", "|"], imageUploadURL: global_host_address+"/UploadByUser", imageUploadParam: "file", imageUploadParams: {}, imageUploadToS3: !1, imageUploadMethod: "POST", imageMaxSize: 10485760, imageAllowedTypes: ["jpeg", "jpg", "png", "gif", "svg+xml"], imageResize: !0, imageResizeWithPercent: !1, imageMove: !0, imageDefaultWidth: 300, imageDefaultAlign: "center", imageDefaultDisplay: "block", imageStyles: {"fr-rounded": "Rounded", "fr-bordered": "Bordered"}, imageMultipleStyles: !0, imageTextNear: !0, imagePaste: !0, imageIframeStyle: "body img {max-width:100%;}body img{z-index:3;position:relative;overflow:auto;cursor:pointer}body img.fr-dib{margin:auto;display:block;float:none;vertical-align:top}body img.fr-dib.fr-fil{margin:5px auto 5px 0}body img.fr-dib.fr-fir{margin:5px 0 5px auto}body img.fr-dii{margin:auto;display:inline-block;float:none}body img.fr-dii.fr-fil{margin:5px 5px 5px 0;float:left}body img.fr-dii.fr-fir{margin:5px 0 5px 5px;float:right}"}), a.FroalaEditor.PLUGINS.image = function (b) {
        function c() {
            var a = b.popups.get("image.insert"), c = a.find(".fr-image-by-url-layer input");
            c.val(""), ka && c.val(ka.attr("src")), c.trigger("change")
        }
        function d() {
            var a = b.$tb.find('.fr-command[data-cmd="insertImage"]'), c = b.popups.get("image.insert");
            if (c || (c = F()), s(), !c.hasClass("fr-active"))
                if (b.popups.refresh("image.insert"), b.popups.setContainer("image.insert", b.$tb), b.core.hasFocus() && b.selection.save(), a.is(":visible")) {
                    var d = a.offset().left + a.outerWidth() / 2, e = a.offset().top + (b.opts.toolbarBottom ? 10 : a.outerHeight() - 10);
                    b.popups.show("image.insert", d, e, a.outerHeight())
                } else
                    b.position.forSelection(c), b.popups.show("image.insert")
        }
        function e() {
            var c = b.popups.get("image.edit");
            c || (c = q()), b.popups.setContainer("image.edit", a(b.opts.scrollableContainer)), b.popups.refresh("image.edit");
            var d = ka.offset().left + ka.outerWidth() / 2, e = ka.offset().top + ka.outerHeight();
            b.popups.show("image.edit", d, e, ka.outerHeight())
        }
        function f() {
            !ka && b.$el.find(".fr-marker").length && (b.events.focus(), b.selection.restore()), s()
        }
        function g(b) {
            var c = {"z-index": 1, position: "relative", overflow: "auto"};
            b.hasClass("fr-dib") ? (a.extend(c, {"vertical-align": "top", display: "block"}), b.hasClass("fr-fir") ? a.extend(c, {"float": "none", "margin-right": "0", "margin-left": "auto"}) : b.hasClass("fr-fil") ? a.extend(c, {"float": "none", "margin-left": "0", "margin-right": "auto"}) : a.extend(c, {"float": "none", margin: "auto"})) : (a.extend(c, {display: "inline-block"}), b.hasClass("fr-fir") ? a.extend(c, {"float": "right"}) : b.hasClass("fr-fil") ? a.extend(c, {"float": "left"}) : a.extend(c, {"float": "none"})), b.removeClass("fr-dib fr-dii fr-fir fr-fil fr-fin"), "" === b.attr("class") && b.removeAttr("class"), b.css(c)
        }
        function h(a) {
            if (!a.hasClass("fr-dii") && !a.hasClass("fr-dib")) {
                var c = a.css("float");
                a.css("float", "none"), "block" == a.css("display") ? (a.css("float", c), 0 === parseInt(a.css("margin-left"), 10) && (a.attr("style") || "").indexOf("margin-right: auto") >= 0 ? a.addClass("fr-fil") : 0 === parseInt(a.css("margin-right"), 10) && (a.attr("style") || "").indexOf("margin-left: auto") >= 0 && a.addClass("fr-fir"), a.addClass("fr-dib")) : (a.css("float", c), "left" == a.css("float") ? a.addClass("fr-fil") : "right" == a.css("float") && a.addClass("fr-fir"), a.addClass("fr-dii")), a.css("margin", ""), a.css("float", ""), a.css("display", ""), a.css("z-index", ""), a.css("position", ""), a.css("overflow", ""), a.css("vertical-align", "")
            }
            a.attr("width") && (a.css("width", a.width()), a.removeAttr("width")), b.opts.imageTextNear || a.removeClass("fr-dii").addClass("fr-dib")
        }
        function i() {
            for (var c = "IMG" == b.$el.get(0).tagName ? [b.$el.get(0)] : b.$el.get(0).querySelectorAll("img"), d = 0; d < c.length; d++)
                h(a(c[d])), b.opts.iframe && a(c[d]).on("load", b.size.syncIframe)
        }
        function j() {
            var c = Array.prototype.slice.call(b.$el.get(0).querySelectorAll("img"));
            if (wa)
                for (var d = 0; d < wa.length; d++)
                    c.indexOf(wa[d]) < 0 && b.events.trigger("image.removed", [a(wa[d])]);
            wa = c
        }
        function k() {
            la || R();
            var a = b.$wp ? b.$wp.scrollTop() - (b.$wp.offset().top + 1) : -1, c = b.$wp ? b.$wp.scrollLeft() - (b.$wp.offset().left + 1) : -1;
            c -= b.helpers.getPX(b.$wp.css("border-left-width")), la.css("top", b.opts.iframe ? ka.offset().top - 1 : ka.offset().top + a).css("left", b.opts.iframe ? ka.offset().left - 1 : ka.offset().left + c).css("width", ka.outerWidth()).css("height", ka.outerHeight()).addClass("fr-active")
        }
        function l(a) {
            return'<div class="fr-handler fr-h' + a + '"></div>'
        }
        function m(c) {
            c.preventDefault(), c.stopPropagation(), ma = a(this), ma.data("start-x", c.pageX || c.originalEvent.touches[0].pageX), na.show(), b.popups.hideAll()
        }
        function n(a) {
            if (ma) {
                a.preventDefault();
                var c = a.pageX || (a.originalEvent.touches ? a.originalEvent.touches[0].pageX : null);
                if (!c)
                    return!1;
                var d = ma.data("start-x");
                ma.data("start-x", c);
                var e = c - d, f = ka.width();
                (ma.hasClass("fr-hnw") || ma.hasClass("fr-hsw")) && (e = 0 - e), b.opts.imageResizeWithPercent ? ka.css("width", ((f + e) / ka.parent().outerWidth() * 100).toFixed(2) + "%") : ka.css("width", f + e), ka.css("height", "").removeAttr("height"), k(), b.events.trigger("image.resize", [ia()])
            }
        }
        function o(a) {
            ma && (a && a.stopPropagation(), ma = null, na.hide(), k(), e(), b.events.trigger("image.resizeEnd", [ia()]))
        }
        function p(a, c) {
            b.edit.on(), u(b.language.translate("Something went wrong. Please try again.")), b.events.trigger("image.error", [{code: a, message: va[a]}, c])
        }
        function q() {
            var a = "";
            b.opts.imageEditButtons.length > 1 && (a += '<div class="fr-buttons">', a += b.button.buildList(b.opts.imageEditButtons), a += "</div>");
            var c = {buttons: a}, d = b.popups.create("image.edit", c);
            return b.$wp && (b.$wp.on("scroll.image-edit", function () {
                ka && b.popups.isVisible("image.edit") && e()
            }), b.events.on("destroy", function () {
                b.$wp.off("scroll.image-edit")
            })), d
        }
        function r() {
            var a = b.popups.get("image.insert");
            a.find(".fr-layer.fr-active").removeClass("fr-active").addClass("fr-pactive"), a.find(".fr-image-progress-bar-layer").addClass("fr-active"), a.find(".fr-buttons").hide(), t("Uploading", 0)
        }
        function s(a) {
            var c = b.popups.get("image.insert");
            c && (c.find(".fr-layer.fr-pactive").addClass("fr-active").removeClass("fr-pactive"), c.find(".fr-image-progress-bar-layer").removeClass("fr-active"), c.find(".fr-buttons").show(), a && b.popups.show("image.insert", null, null))
        }
        function t(a, c) {
            var d = b.popups.get("image.insert");
            if (d) {
                var e = d.find(".fr-image-progress-bar-layer");
                e.find("h3").text(a + (c ? " " + c + "%" : "")), e.removeClass("fr-error"), c ? (e.find("div").removeClass("fr-indeterminate"), e.find("div > span").css("width", c + "%")) : e.find("div").addClass("fr-indeterminate")
            }
        }
        function u(a) {
            var c = b.popups.get("image.insert"), d = c.find(".fr-image-progress-bar-layer");
            d.addClass("fr-error"), d.find("h3").text(a)
        }
        function v() {
            var a = b.popups.get("image.insert"), c = a.find(".fr-image-by-url-layer input");
            c.val().length > 0 && (r(), t("Loading image"), w(b.helpers.sanitizeURL(c.val()), !0, [], ka), c.val(""), c.blur())
        }
        function w(c, d, e, f, g) {
            b.edit.off(), t("Loading image");
            var h = new Image;
            h.onload = function () {
                var d, h;
                if (f) {
                    d = f, d.off("load");
                    for (var i = d.get(0).attributes, j = 0; j < i.length; j++) {
                        var k = i[j];
                        0 === k.nodeName.indexOf("data-") && d.removeAttr(k.nodeName)
                    }
                    if ("undefined" != typeof e)
                        for (h in e)
                            "link" != h && d.attr("data-" + h, e[h]);
                    d.on("load", function () {
                        d.trigger("click").trigger("touchend"), b.events.trigger("image.loaded", [d])
                    }), d.attr("src", c), b.edit.on(), b.undo.saveStep(), b.events.trigger("image.replaced", [d, g])
                } else {
                    var l = "";
                    if ("undefined" != typeof e)
                        for (h in e)
                            "link" != h && (l = " data-" + h + '="' + e[h] + '"');
                    var m = b.opts.imageDefaultWidth;
                    m && "auto" != m && (m += b.opts.imageResizeWithPercent ? "%" : "px"), d = a('<img class="fr-di' + b.opts.imageDefaultDisplay[0] + ("center" != b.opts.imageDefaultAlign ? " fr-fi" + b.opts.imageDefaultAlign[0] : "") + '" src="' + c + '"' + l + (m ? ' style="width: ' + m + ';"' : "") + ">"), d.on("load", function () {
                        d.trigger("click").trigger("touchend"), b.events.trigger("image.loaded", [d])
                    }), b.edit.on(), b.events.focus(!0), b.selection.restore(), b.selection.isCollapsed() || b.selection.remove(), b.markers.insert();
                    var n = b.$el.find(".fr-marker");
                    n.replaceWith(d), b.selection.clear(), b.undo.saveStep(), b.events.trigger("image.inserted", [d, g])
                }
            }, h.onerror = function () {
                p(oa)
            }, h.src = c
        }
        function x(c) {
            try {
                if (b.events.trigger("image.uploaded", [c], !0) === !1)
                    return b.edit.on(), !1;
                var d = a.parseJSON(c);
                return d.link ? d : (p(pa, c), !1)
            } catch (e) {
                return p(ra, c), !1
            }
        }
        function y(c) {
            try {
                var d = a(c).find("Location").text(), e = a(c).find("Key").text();
                return b.events.trigger("image.uploadedToS3", [d, e, c], !0) === !1 ? (b.edit.on(), !1) : d
            } catch (f) {
                return p(ra, c), !1
            }
        }
        function z(a) {
            t("Loading image");
            var c = this.status, d = this.response, e = this.responseXML, f = this.responseText;
            try {
                if (b.opts.imageUploadToS3)
                    if (201 == c) {
                        var g = y(e);
                        g && w(g, !1, [], a, d || e)
                    } else
                        p(ra, d || e);
                else if (c >= 200 && 300 > c) {
                    var h = x(f);
                    h && w(h.link, !1, h, a, d || f)
                } else
                    p(qa, d || f)
            } catch (i) {
                p(ra, d || f)
            }
        }
        function A() {
            p(ra, this.response || this.responseText || this.responseXML)
        }
        function B(a) {
            if (a.lengthComputable) {
                var b = a.loaded / a.total * 100 | 0;
                t("Uploading", b)
            }
        }
        function C(a) {
            if (b.events.trigger("image.beforeUpload", [a]) === !1)
                return!1;
            if ("undefined" != typeof a && a.length > 0) {
                var c = a[0];
                if (c.size > b.opts.imageMaxSize)
                    return p(sa), !1;
                if (b.opts.imageAllowedTypes.indexOf(c.type.replace(/image\//g, "")) < 0)
                    return p(ta), !1;
                var d;
                if (b.drag_support.formdata && (d = b.drag_support.formdata ? new FormData : null), d) {
                    var e;
                    if (b.opts.imageUploadToS3 !== !1) {
                        d.append("key", b.opts.imageUploadToS3.keyStart + (new Date).getTime() + "-" + (c.name || "untitled")), d.append("success_action_status", "201"), d.append("X-Requested-With", "xhr"), d.append("Content-Type", c.type);
                        for (e in b.opts.imageUploadToS3.params)
                            d.append(e, b.opts.imageUploadToS3.params[e])
                    }
                    for (e in b.opts.imageUploadParams)
                        d.append(e, b.opts.imageUploadParams[e]);
                    d.append(b.opts.imageUploadParam, c);
                    var f = b.opts.imageUploadURL;
                    b.opts.imageUploadToS3 && (f = "https://" + b.opts.imageUploadToS3.region + ".amazonaws.com/" + b.opts.imageUploadToS3.bucket);
                    var g = b.core.getXHR(f, b.opts.imageUploadMethod), h = ka;
                    g.onload = function () {
                        z.call(g, h)
                    }, g.onerror = A, g.upload.onprogress = B, r(), b.edit.off(), g.send(d)
                }
            }
        }
        function D(b) {
            b.on("dragover dragenter", ".fr-image-upload-layer", function () {
                return a(this).addClass("fr-drop"), !1
            }), b.on("dragleave dragend", ".fr-image-upload-layer", function () {
                return a(this).removeClass("fr-drop"), !1
            }), b.on("drop", ".fr-image-upload-layer", function (b) {
                b.preventDefault(), b.stopPropagation(), a(this).removeClass("fr-drop");
                var c = b.originalEvent.dataTransfer;
                c && c.files && C(c.files)
            }), b.on("change", '.fr-image-upload-layer input[type="file"]', function () {
                this.files && C(this.files), a(this).val(""), a(this).blur()
            })
        }
        function E() {
            b.$el.on(b._mousedown, "IMG" == b.$el.get(0).tagName ? null : "img", function (c) {
                b.selection.clear(), b.browser.msie && (b.events.disableBlur(), b.$el.attr("contenteditable", !1)), b.opts.imageMove || c.preventDefault(), c.stopPropagation(), b.opts.imageMove && (b.opts.toolbarInline && b.toolbar.hide(), a(this).addClass("fr-img-move"))
            }), b.$el.on(b._mouseup, "IMG" == b.$el.get(0).tagName ? null : "img", function (c) {
                c.stopPropagation(), b.browser.msie && (b.$el.attr("contenteditable", !0), b.events.enableBlur()), a(this).removeClass("fr-img-move")
            });
            var c = function (a) {
                var c = b.$document.find("img.fr-img-move").get(0);
                return c ? "undefined" != typeof b.browser.msie : void a.preventDefault()
            }, d = function (a) {
                a.preventDefault()
            };
            b.events.on("dragenter", d, !0), b.events.on("dragover", c, !0), b.events.on("drop", function (c) {
                for (var d, e, f = 0; f < a.FroalaEditor.INSTANCES.length; f++)
                    if (d = a.FroalaEditor.INSTANCES[f].$el.find("img.fr-img-move").get(0)) {
                        e = a.FroalaEditor.INSTANCES[f];
                        break
                    }
                if (d) {
                    c.preventDefault(), c.stopPropagation(), $(!0), e != b && e.image && (e.image.exitEdit(!0), e.popups.hide("image.edit"));
                    var g, h;
                    "A" == d.parentNode.tagName && 0 === d.parentNode.textContent.length ? (h = a(d.parentNode), g = a(d.parentNode).clone(), g.find("img").removeClass("fr-img-move").on("load", Z)) : (h = a(d), g = a(d).clone(), g.removeClass("fr-img-move").on("load", Z)), b.markers.insertAtPoint(c.originalEvent);
                    var i = b.$el.find(".fr-marker");
                    return i.replaceWith(g), h.remove(), b.undo.saveStep(), !1
                }
                $(!0), b.popups.hideAll();
                var j = c.originalEvent.dataTransfer;
                if (j && j.files && j.files.length && (d = j.files[0], d && d.type && b.opts.imageAllowedTypes.indexOf(d.type.replace(/image\//g, "")) >= 0)) {
                    b.markers.insertAtPoint(c.originalEvent), b.markers.remove(), b.popups.hideAll();
                    var k = b.popups.get("image.insert");
                    return k || (k = F()), b.popups.setContainer("image.insert", a(b.opts.scrollableContainer)), b.popups.show("image.insert", c.originalEvent.pageX, c.originalEvent.pageY), r(), C(j.files), c.preventDefault(), c.stopPropagation(), !1
                }
            }, !0), b.events.on("document.drop", function (a) {
                b.$el.find("img.fr-img-move").length && (a.preventDefault(), a.stopPropagation(), b.$el.find("img.fr-img-move").removeClass("fr-img-move"))
            }), b.events.on("mousedown", _), b.events.on("window.mousedown", _), b.events.on("window.touchmove", aa), b.events.on("mouseup", $), b.events.on("window.mouseup", $), b.events.on("commands.mousedown", function (a) {
                a.parents(".fr-toolbar").length > 0 && $()
            }), b.events.on("image.hideResizer", $), b.events.on("commands.undo", $), b.events.on("commands.redo", $), b.events.on("destroy", function () {
                b.$el.off(b._mouseup, "img")
            }, !0)
        }
        function F() {
            var a, d = "";
            b.opts.imageInsertButtons.length > 1 && (d = '<div class="fr-buttons">' + b.button.buildList(b.opts.imageInsertButtons) + "</div>");
            var e = "";
            b.opts.imageInsertButtons.indexOf("imageUpload") >= 0 && (a = b.opts.imageInsertButtons.indexOf("imageUpload") < b.opts.imageInsertButtons.indexOf("imageByURL") ? " fr-active" : "", e = '<div class="fr-image-upload-layer' + a + ' fr-layer" id="fr-image-upload-layer-' + b.id + '"><strong>' + b.language.translate("Drop image") + "</strong><br>(" + b.language.translate("or click") + ')<form><input type="file" name="' + b.opts.imageUploadParam + '" accept="image/*" tabIndex="-1"></form></div>');
            var g = "";
            b.opts.imageInsertButtons.indexOf("imageByURL") >= 0 && (a = b.opts.imageInsertButtons.indexOf("imageUpload") > b.opts.imageInsertButtons.indexOf("imageByURL") ? " fr-active" : "", g = '<div class="fr-image-by-url-layer' + a + ' fr-layer" id="fr-image-by-url-layer-' + b.id + '"><div class="fr-input-line"><input type="text" placeholder="http://" tabIndex="1"></div><div class="fr-action-buttons"><button type="button" class="fr-command fr-submit" data-cmd="imageInsertByURL" tabIndex="2">' + b.language.translate("Insert") + "</button></div></div>");
            var h = '<div class="fr-image-progress-bar-layer fr-layer"><h3 class="fr-message">Uploading</h3><div class="fr-loader"><span class="fr-progress"></span></div><div class="fr-action-buttons"><button type="button" class="fr-command fr-back" data-cmd="imageDismissError" tabIndex="2">OK</button></div></div>', i = {buttons: d, upload_layer: e, by_url_layer: g, progress_bar: h}, j = b.popups.create("image.insert", i);
            return b.popups.onRefresh("image.insert", c), b.popups.onHide("image.insert", f), b.$wp && b.$wp.on("scroll.image-insert", function () {
                ka && b.popups.isVisible("image.insert") && ga()
            }), b.events.on("destroy", function () {
                b.$wp && b.$wp.off("scroll.image-insert"), j.off("dragover dragenter", ".fr-image-upload-layer"), j.off("dragleave dragend", ".fr-image-upload-layer"), j.off("drop", ".fr-image-upload-layer"), j.off("change", '.fr-image-upload-layer input[type="file"]')
            }), D(j), j
        }
        function G() {
            if (ka) {
                var a = b.popups.get("image.alt");
                a.find("input").val(ka.attr("alt") || "").trigger("change")
            }
        }
        function H() {
            var c = b.popups.get("image.alt");
            c || (c = I()), s(), b.popups.refresh("image.alt"), b.popups.setContainer("image.alt", a(b.opts.scrollableContainer));
            var d = ka.offset().left + ka.width() / 2, e = ka.offset().top + ka.height();
            b.popups.show("image.alt", d, e, ka.outerHeight())
        }
        function I() {
            var a = "";
            a = '<div class="fr-buttons">' + b.button.buildList(b.opts.imageAltButtons) + "</div>";
            var c = "";
            c = '<div class="fr-image-alt-layer fr-layer fr-active" id="fr-image-alt-layer-' + b.id + '"><div class="fr-input-line"><input type="text" placeholder="' + b.language.translate("Alternate Text") + '" tabIndex="1"></div><div class="fr-action-buttons"><button type="button" class="fr-command fr-submit" data-cmd="imageSetAlt" tabIndex="2">' + b.language.translate("Update") + "</button></div></div>";
            var d = {buttons: a, alt_layer: c}, e = b.popups.create("image.alt", d);
            return b.popups.onRefresh("image.alt", G), b.$wp && (b.$wp.on("scroll.image-alt", function () {
                ka && b.popups.isVisible("image.alt") && H()
            }), b.events.on("destroy", function () {
                b.$wp.off("scroll.image-alt")
            })), e
        }
        function J(a) {
            if (ka) {
                var c = b.popups.get("image.alt");
                ka.attr("alt", a || c.find("input").val() || ""), c.find("input").blur(), setTimeout(function () {
                    ka.trigger("click").trigger("touchend")
                }, b.helpers.isAndroid() ? 50 : 0)
            }
        }
        function K() {
            if (ka) {
                var a = b.popups.get("image.size");
                a.find('input[name="width"]').val(ka.get(0).style.width).trigger("change"), a.find('input[name="height"]').val(ka.get(0).style.height).trigger("change")
            }
        }
        function L() {
            var c = b.popups.get("image.size");
            c || (c = M()), s(), b.popups.refresh("image.size"), b.popups.setContainer("image.size", a(b.opts.scrollableContainer));
            var d = ka.offset().left + ka.width() / 2, e = ka.offset().top + ka.height();
            b.popups.show("image.size", d, e, ka.outerHeight())
        }
        function M() {
            var a = "";
            a = '<div class="fr-buttons">' + b.button.buildList(b.opts.imageSizeButtons) + "</div>";
            var c = "";
            c = '<div class="fr-image-size-layer fr-layer fr-active" id="fr-image-size-layer-' + b.id + '"><div class="fr-image-group"><div class="fr-input-line"><input type="text" name="width" placeholder="' + b.language.translate("Width") + '" tabIndex="1"></div><div class="fr-input-line"><input type="text" name="height" placeholder="' + b.language.translate("Height") + '" tabIndex="1"></div></div><div class="fr-action-buttons"><button type="button" class="fr-command fr-submit" data-cmd="imageSetSize" tabIndex="2">' + b.language.translate("Update") + "</button></div></div>";
            var d = {buttons: a, size_layer: c}, e = b.popups.create("image.size", d);
            return b.popups.onRefresh("image.size", K), b.$wp && (b.$wp.on("scroll.image-size", function () {
                ka && b.popups.isVisible("image.size") && L()
            }), b.events.on("destroy", function () {
                b.$wp.off("scroll.image-size")
            })), e
        }
        function N(a, c) {
            if (ka) {
                var d = b.popups.get("image.size");
                ka.css("width", a || d.find('input[name="width"]').val()), ka.css("height", c || d.find('input[name="height"]').val()), d.find("input").blur(), setTimeout(function () {
                    ka.trigger("click").trigger("touchend")
                }, b.helpers.isAndroid() ? 50 : 0)
            }
        }
        function O(a) {
            var c, d, e = b.popups.get("image.insert");
            if (ka || b.opts.toolbarInline)
                ka && (d = ka.offset().top + ka.outerHeight());
            else {
                var f = b.$tb.find('.fr-command[data-cmd="insertImage"]');
                c = f.offset().left + f.outerWidth() / 2, d = f.offset().top + (b.opts.toolbarBottom ? 10 : f.outerHeight() - 10)
            }
            !ka && b.opts.toolbarInline && (d = e.offset().top - b.helpers.getPX(e.css("margin-top")), e.hasClass("fr-above") && (d += e.outerHeight())), e.find(".fr-layer").removeClass("fr-active"), e.find(".fr-" + a + "-layer").addClass("fr-active"), b.popups.show("image.insert", c, d, ka ? ka.outerHeight() : 0)
        }
        function P(a) {
            var c = b.popups.get("image.insert");
            c.find(".fr-image-upload-layer").hasClass("fr-active") && a.addClass("fr-active")
        }
        function Q(a) {
            var c = b.popups.get("image.insert");
            c.find(".fr-image-by-url-layer").hasClass("fr-active") && a.addClass("fr-active")
        }
        function R() {
            if (la = a('<div class="fr-image-resizer"></div>'), (b.$wp || a(b.opts.scrollableContainer)).append(la), la.on("mousedown", function (a) {
                a.stopPropagation()
            }), a(b.original_window).on("resize.image" + b.id, function () {
                b.helpers.isMobile() || $(!0)
            }), b.events.on("destroy", function () {
                la.html("").removeData().remove(), a(b.original_window).off("resize.image" + b.id)
            }, !0), b.opts.imageResize) {
                la.append(l("nw") + l("ne") + l("sw") + l("se"));
                var c = la.get(0).ownerDocument;
                la.on(b._mousedown + ".imgresize" + b.id, ".fr-handler", m), a(c).on(b._mousemove + ".imgresize" + b.id, n), a(c.defaultView || c.parentWindow).on(b._mouseup + ".imgresize" + b.id, o), na = a('<div class="fr-image-overlay"></div>'), a(c).find("body").append(na), na.on("mouseleave", o), b.events.on("destroy", function () {
                    la.off(b._mousedown + ".imgresize" + b.id), a(c).off(b._mousemove + ".imgresize" + b.id), a(c.defaultView || c.parentWindow).off(b._mouseup + ".imgresize" + b.id), na.off("mouseleave").remove()
                }, !0)
            }
        }
        function S(c) {
            c = c || ka, c && b.events.trigger("image.beforeRemove", [c]) !== !1 && (b.popups.hideAll(), $(!0), c.get(0) == b.$el.get(0) ? c.removeAttr("src") : ("A" == c.get(0).parentNode.tagName ? (b.selection.setBefore(c.get(0).parentNode) || b.selection.setAfter(c.get(0).parentNode), a(c.get(0).parentNode).remove()) : (b.selection.setBefore(c.get(0)) || b.selection.setAfter(c.get(0)), c.remove()), b.selection.restore(), b.html.fillEmptyBlocks(!0)))
        }
        function T() {
            b.$el.find("img").each(function () {
                g(a(this))
            })
        }
        function U() {
            b.$el.find("img").each(function () {
                h(a(this))
            })
        }
        function V() {
            E(), b.$el.on(b.helpers.isMobile() ? "touchend" : "click", "IMG" == b.$el.get(0).tagName ? null : "img", Z), b.helpers.isMobile() && (b.$el.on("touchstart", "IMG" == b.$el.get(0).tagName ? null : "img", function () {
                xa = !1
            }), b.$el.on("touchmove", function () {
                xa = !0
            })), b.events.on("keydown", function (c) {
                var d = c.which;
                return!ka || d != a.FroalaEditor.KEYCODE.BACKSPACE && d != a.FroalaEditor.KEYCODE.DELETE ? ka && d == a.FroalaEditor.KEYCODE.ESC ? ($(!0), c.preventDefault(), !1) : ka && !b.keys.ctrlKey(c) ? (c.preventDefault(), !1) : void 0 : (c.preventDefault(), S(), !1)
            }, !0), b.events.on("paste.before", X), b.events.on("paste.beforeCleanup", Y), b.events.on("paste.after", W), b.events.on("html.set", i), i(), b.opts.useClasses || (b.events.on("html.beforeGet", T), b.events.on("html.afterGet", U)), b.opts.iframe && (b.events.on("html.set", function () {
                b.core.injectStyle(b.opts.imageIframeStyle)
            }), b.core.injectStyle(b.opts.imageIframeStyle)), b.opts.iframe && b.events.on("image.loaded", b.size.syncIframe), b.$wp && (j(), b.events.on("contentChanged", j)), b.events.on("destroy", function () {
                b.$el.off("click touchstart touchend touchmove", "img"), b.$el.off("load", "img.fr-img-dirty"), b.$el.off("error", "img.fr-img-dirty")
            }, !0), b.events.on("node.remove", function (a) {
                return"IMG" == a.get(0).tagName ? (S(a), !1) : void 0
            })
        }
        function W() {
            b.opts.imagePaste ? b.$el.find("img[data-fr-image-pasted]").each(function (c, d) {
                if (0 === d.src.indexOf("data:")) {
                    if (b.events.trigger("image.beforePasteUpload", [d]) === !1)
                        return!1;
                    var f = b.opts.imageDefaultWidth;
                    "auto" != f && (f += b.opts.imageResizeWithPercent ? "%" : "px"), a(d).css("width", f), a(d).addClass("fr-dib"), ka = a(d), k(), e(), ga(), r(), b.edit.off();
                    for (var g = atob(a(d).attr("src").split(",")[1]), h = [], i = 0; i < g.length; i++)
                        h.push(g.charCodeAt(i));
                    var j = new Blob([new Uint8Array(h)], {type: "image/jpeg"});
                    C([j]), a(d).removeAttr("data-fr-image-pasted")
                } else
                    0 !== d.src.indexOf("http") ? (b.selection.save(), a(d).remove(), b.selection.restore()) : a(d).removeAttr("data-fr-image-pasted")
            }) : b.$el.find("img[data-fr-image-pasted]").remove()
        }
        function X(a) {
            if (a && a.clipboardData && a.clipboardData.items && a.clipboardData.items[0]) {
                var c = a.clipboardData.items[0].getAsFile();
                if (c) {
                    var d = new FileReader;
                    return d.onload = function (a) {
                        var c = a.target.result;
                        b.html.insert('<img data-fr-image-pasted="true" src="' + c + '" />'), b.events.trigger("paste.after")
                    }, d.readAsDataURL(c), !1
                }
            }
        }
        function Y(a) {
            return a = a.replace(/<img /gi, '<img data-fr-image-pasted="true" ')
        }
        function Z(c) {
            return c && "touchend" == c.type && xa ? !0 : b.edit.isDisabled() ? (c.stopPropagation(), c.preventDefault(), !1) : (b.toolbar.disable(), c.stopPropagation(), c.preventDefault(), b.helpers.isMobile() && (b.events.disableBlur(), b.$el.blur(), b.events.enableBlur()), b.opts.iframe && b.size.syncIframe(), ka = a(this), k(), e(), b.selection.clear(), b.button.bulkRefresh(), void b.events.trigger("video.hideResizer"))
        }
        function $(a) {
            a === !0 && (ya = !0), ka && ya && (b.toolbar.enable(), la.removeClass("fr-active"), ka = null), ya = !1
        }
        function _() {
            ya = !0
        }
        function aa() {
            ya = !1
        }
        function ba(a) {
            ka.removeClass("fr-fir fr-fil"), "left" == a ? ka.addClass("fr-fil") : "right" == a && ka.addClass("fr-fir"), k(), e()
        }
        function ca(a) {
            ka && (ka.hasClass("fr-fil") ? a.find("> i").attr("class", "fa fa-align-left") : ka.hasClass("fr-fir") ? a.find("> i").attr("class", "fa fa-align-right") : a.find("> i").attr("class", "fa fa-align-justify"))
        }
        function da(a, b) {
            if (ka) {
                var c = "justify";
                ka.hasClass("fr-fil") ? c = "left" : ka.hasClass("fr-fir") && (c = "right"), b.find('.fr-command[data-param1="' + c + '"]').addClass("fr-active")
            }
        }
        function ea(a) {

            
            ka.removeClass("fr-dii fr-dib"), "inline" == a ? ka.addClass("fr-dii") : "block" == a && ka.addClass("fr-dib"), k(), e()
        }
       var featherEditor = new Aviary.Feather({
       apiKey: '75ed2ca5-7d87-454f-ab9f-e46446ed542d',
       apiVersion: 3,
       theme: 'dark', // Check out our new 'light' and 'dark' themes!
       tools: 'all',
       appendTo: '',
       onSave: function(imageID, newURL) {
           var img = document.getElementById(imageID);
           img.src = newURL;
       },
       onError: function(errorObj) {
           alert(errorObj.message);
       }
   }); 
   
      function launchEditor(id, src) {
       featherEditor.launch({
           image: id,
           url: src
       });
      return false;
   }
        
        
        
        
        function fa(a, b) {
            var c = "block";
            ka.hasClass("fr-dii") && (c = "inline"), b.find('.fr-command[data-param1="' + c + '"]').addClass("fr-active")
        }
        function ga() {
            var c = b.popups.get("image.insert");
            c || (c = F()), b.popups.isVisible("image.insert") || (s(), b.popups.refresh("image.insert"), b.popups.setContainer("image.insert", a(b.opts.scrollableContainer)));
            var d = ka.offset().left + ka.width() / 2, e = ka.offset().top + ka.height();
            b.popups.show("image.insert", d, e, ka.outerHeight())
        }
        function ha() {
            ka ? ka.trigger("click").trigger("touchend") : (b.popups.hide("image.insert"), b.toolbar.showInline())
        }
        function ia() {
            return ka
        }
        function ja(a) {
//            if (!ka)
//                return!1;
//            if (!b.opts.imageMultipleStyles) {
//                var c = Object.keys(b.opts.imageStyles);
//                c.splice(c.indexOf(a), 1), ka.removeClass(c.join(" "))
//            }
//            ka.toggleClass(a), ka.trigger("click").trigger("touchend")
            launchEditor(image);
        }
        var ka, la, ma, na, oa = 1, pa = 2, qa = 3, ra = 4, sa = 5, ta = 6, ua = 7, va = {};
        va[oa] = "Image cannot be loaded from the passed link.", va[pa] = "No link in upload response.", va[qa] = "Error during file upload.", va[ra] = "Parsing response failed.", va[sa] = "File is too large.", va[ta] = "Image file type is invalid.", va[ua] = "Files can be uploaded only to same domain in IE 8 and IE 9.";
        var wa, xa, ya = !1;
        return{_init: V, showInsertPopup: d, showLayer: O, refreshUploadButton: P, refreshByURLButton: Q, upload: C, insertByURL: v, align: ba, refreshAlign: ca, refreshAlignOnShow: da, display: ea, refreshDisplayOnShow: fa, replace: ga, back: ha, get: ia, insert: w, showProgressBar: r, remove: S, hideProgressBar: s, applyStyle: ja, showAltPopup: H, showSizePopup: L, setAlt: J, setSize: N, exitEdit: $}
    }, a.FroalaEditor.DefineIcon("insertImage", {NAME: "image"}), a.FroalaEditor.RegisterShortcut(80, "insertImage"), a.FroalaEditor.RegisterCommand("insertImage", {title: "Insert Image", undo: !1, focus: !1, refershAfterCallback: !1, popup: !0, callback: function () {
            this.popups.isVisible("image.insert") ? this.popups.hide("image.insert") : this.image.showInsertPopup()
        }}), a.FroalaEditor.DefineIcon("imageUpload", {NAME: "upload"}), a.FroalaEditor.RegisterCommand("imageUpload", {title: "Upload Image", undo: !1, focus: !1, callback: function () {
            this.image.showLayer("image-upload")
        }, refresh: function (a) {
            this.image.refreshUploadButton(a)
        }}), 
//            a.FroalaEditor.DefineIcon("imageByURL", {NAME: "link"}), a.FroalaEditor.RegisterCommand("imageByURL", {title: "By URL", undo: !1, focus: !1, callback: function () {
//            this.image.showLayer("image-by-url")
//        }
//        , refresh: function (a) {
//            this.image.refreshByURLButton(a)
//        }}),
            a.FroalaEditor.RegisterCommand("imageInsertByURL", {title: "Insert Image", undo: !0, refreshAfterCallback: !1, callback: function () {
            this.image.insertByURL()
        }, refresh: function (a) {
            var b = this.image.get();
            b ? a.text("Replace") : a.text(this.language.translate("Insert"))
        }}), a.FroalaEditor.DefineIcon("imageDisplay", {NAME: "star"}), a.FroalaEditor.RegisterCommand("imageDisplay", {title: "Display", type: "dropdown", options: {inline: "Inline", block: "Break Text"}, callback: function (a, b) {
            alert(this.className+":"+this.type);
        this.image.display(b)
        }, refresh: function (a) {
            this.opts.imageTextNear || a.addClass("fr-hidden")
        }, refreshOnShow: function (a, b) {
            this.image.refreshDisplayOnShow(a, b)
        }}), a.FroalaEditor.DefineIcon("imageAlign", {NAME: "align-center"}), a.FroalaEditor.RegisterCommand("imageAlign", {type: "dropdown", title: "Align", options: {left: "Align Left", justify: "None", right: "Align Right"}, html: function () {
            var b = '<ul class="fr-dropdown-list">', c = a.FroalaEditor.COMMANDS.imageAlign.options;
            for (var d in c)
                b += '<li><a class="fr-command" data-cmd="imageAlign" data-param1="' + d + '" title="' + this.language.translate(c[d]) + '"><i class="fa fa-align-' + d + '"></i></a></li>';
            return b += "</ul>"
        }, callback: function (a, b) {
            this.image.align(b)
        }, refresh: function (a) {
            this.image.refreshAlign(a)
        }, refreshOnShow: function (a, b) {
            this.image.refreshAlignOnShow(a, b)
        }}), a.FroalaEditor.DefineIcon("imageReplace", {NAME: "exchange"}), a.FroalaEditor.RegisterCommand("imageReplace", {title: "Replace", undo: !1, focus: !1, refreshAfterCallback: !1, callback: function () {
            this.image.replace()
        }}), a.FroalaEditor.DefineIcon("imageRemove", {NAME: "trash"}), a.FroalaEditor.RegisterCommand("imageRemove", {title: "Remove", callback: function () {
            this.image.remove()
        }}), a.FroalaEditor.DefineIcon("imageBack", {NAME: "arrow-left"}), a.FroalaEditor.RegisterCommand("imageBack", {title: "Back", undo: !1, focus: !1, back: !0, callback: function () {
            this.image.back()
        }, refresh: function (a) {
            var b = this.image.get();
            b || this.opts.toolbarInline ? (a.removeClass("fr-hidden"), a.next(".fr-separator").removeClass("fr-hidden")) : (a.addClass("fr-hidden"), a.next(".fr-separator").addClass("fr-hidden"))
        }}), a.FroalaEditor.RegisterCommand("imageDismissError", {title: "OK", callback: function () {
            this.image.hideProgressBar(!0)
        }}), a.FroalaEditor.DefineIcon("imageStyle", {NAME: "magic"}), a.FroalaEditor.RegisterCommand("imageStyle", {title: "Style", html: function () {
            var a = '<ul class="fr-dropdown-list">', b = this.opts.imageStyles;
            for (var c in b)
                a += '<li><a class="fr-command" data-cmd="imageStyle" data-param1="' + c + '">' + b[c] + "</a></li>";
            return a += "</ul>"
        }, callback: function (a, b) {
            this.image.applyStyle(b)
        }, refreshOnShow: function (b, c) {
            var d = this.image.get();
            d && c.find(".fr-command").each(function () {
                var b = a(this).data("param1");
                a(this).toggleClass("fr-active", d.hasClass(b))
            })
        }}), a.FroalaEditor.DefineIcon("imageAlt", {NAME: "info"}), a.FroalaEditor.RegisterCommand("imageAlt", {undo: !1, focus: !1, title: "Alternate Text", callback: function () {
            this.image.showAltPopup()
        }}), a.FroalaEditor.RegisterCommand("imageSetAlt", {undo: !0, focus: !1, title: "Update", refreshAfterCallback: !1, callback: function () {
            this.image.setAlt()
        }}), a.FroalaEditor.DefineIcon("imageSize", {NAME: "arrows-alt"}), a.FroalaEditor.RegisterCommand("imageSize", {undo: !1, focus: !1, title: "Change Size", callback: function () {
            this.image.showSizePopup()
        }}), a.FroalaEditor.RegisterCommand("imageSetSize", {undo: !0, focus: !1, callback: function () {
            this.image.setSize()
        }})
}(jQuery);