/*!
 * froala_editor v2.0.0-rc.3 (https://www.froala.com/wysiwyg-editor/v2.0)
 * License http://editor.froala.com/license
 * Copyright 2014-2015 Froala Labs
 */
!function (a) {
   var uid= $("#userid").val();
    "use strict";
    if (a.extend(a.FroalaEditor.DEFAULTS, {imageManagerLoadURL: global_host_address+"GetFilesListServlet?image_type=GALLERY&user_id="+uid, imageManagerLoadMethod: "get", imageManagerLoadParams: {}, imageManagerPreloader: "", imageManagerDeleteURL: "", imageManagerDeleteMethod: "post", imageManagerDeleteParams: {}, imageManagerPageSize: 12, imageManagerScrollOffset: 20}), a.FroalaEditor.PLUGINS.imageManager = function (b) {
        function c() {
            z.show(), F.show(), U = b.image.get(), A || x(), i(), b.$document.find("body").addClass("prevent-scroll"), b.helpers.isMobile() && b.$document.find("body").addClass("fr-mobile")
        }
        function d() {
            b.events.enableBlur(), z.hide(), F.hide(), b.$document.find("body").removeClass("prevent-scroll fr-mobile")
        }
        function e() {
            var b = a(window).outerWidth();
            return 768 > b ? 2 : 1200 > b ? 3 : 4
        }
        function f() {
            B.empty();
            for (var a = 0; K > a; a++)
                B.append('<div class="fr-list-column"></div>')
        }
        function g() {
            var c = "";
            b.opts.theme && (c = " " + b.opts.theme + "-theme");
            var d = '<div class="fr-modal' + c + '"><div class="fr-modal-wrapper">';
            return d += '<div class="fr-modal-title"><div class="fr-modal-title-line"><i class="fa fa-bars fr-modal-more fr-not-available" id="fr-modal-more-' + b.id + '" title="' + b.language.translate("Tags") + '"></i><h4 data-text="true">' + b.language.translate("Manage Images") + '</h4><i title="' + b.language.translate("Cancel") + '" class="fa fa-times fr-modal-close" id="fr-modal-close-' + b.id + '"></i></div>', d += '<div class="fr-modal-tags" id="fr-modal-tags-' + b.id + '"></div>', d += "</div>", d += '<img class="fr-preloader" id="fr-preloader-' + b.id + '" alt="' + b.language.translate("Loading") + '.." src="' + b.opts.imageManagerPreloader + '" style="display: none;">', d += '<div class="fr-scroller" id="fr-scroller-' + b.id + '"><div class="fr-image-list" id="fr-image-list-' + b.id + '"></div></div>', d += "</div></div>", a(d)
        }
        function h() {
            z = g(), b.helpers.isMobile() || z.addClass("fr-desktop"), z.appendTo("body"), F = a('<div class="fr-overlay">').appendTo("body"), b.opts.theme && F.addClass(b.opts.theme + "-theme"), d()
        }
        function i() {
            A.show(), B.find(".fr-list-column").empty(), b.opts.imageManagerLoadURL ? a.ajax({url: b.opts.imageManagerLoadURL, method: b.opts.imageManagerLoadMethod, data: b.opts.imageManagerLoadParams, dataType: "json"}).done(function (a, c, d) {
                b.events.trigger("imageManager.imagesLoaded", [a]), j(a, d.response), A.hide()
            }).fail(function () {
                var a = this.xhr();
                s(M, a.response || a.responseText)
            }) : s(N)
        }
        function j(a, b) {
            try {
                B.find(".fr-list-column").empty(), H = 0, I = 0, J = 0, G = a, k()
            } catch (c) {
                s(O, b)
            }
        }
        function k() {
            if (I < G.length && (B.outerHeight() <= C.outerHeight() + b.opts.imageManagerScrollOffset || C.scrollTop() + b.opts.imageManagerScrollOffset > B.outerHeight() - C.outerHeight())) {
                H++;
                for (var a = b.opts.imageManagerPageSize * (H - 1); a < Math.min(G.length, b.opts.imageManagerPageSize * H); a++)
                    l(G[a])
            }
        }
        function l(c) {
            var d = new Image, e = a('<div class="fr-image-container fr-empty fr-image-' + J++ + '" data-loading="' + b.language.translate("Loading") + '.." data-deleting="' + b.language.translate("Deleting") + '..">');
            p(!1), d.onload = function () {
                e.height(Math.floor(e.width() / d.width * d.height));
                var f = a("<img/>");
                if (c.thumb)
                    f.attr("src", c.thumb), c.url && f.attr("data-url", c.url);
                else {
                    if (s(P, c), !c.url)
                        return s(Q, c), !1;
                    f.attr("src", c.url)
                }
                if (c.tag)
                    if (E.find(".fr-modal-more.fr-not-available").removeClass("fr-not-available"), E.find(".fr-modal-tags").show(), c.tag.indexOf(",") >= 0) {
                        for (var g = c.tag.split(","), h = 0; h < g.length; h++)
                            g[h] = g[h].trim(), 0 === D.find('a[title="' + g[h] + '"]').length && D.append('<a role="button" title="' + g[h] + '">' + g[h] + "</a>");
                        f.attr("data-tag", g.join())
                    } else
                        0 === D.find('a[title="' + c.tag.trim() + '"]').length && D.append('<a role="button" title="' + c.tag.trim() + '">' + c.tag.trim() + "</a>"), f.attr("data-tag", c.tag.trim());
                for (var i in c)
                    "thumb" != i && "url" != i && "tag" != i && f.attr("data-" + i, c[i]);
                e.append(f).append('<i class="fa fa-trash-o fr-delete-img" title="' + b.language.translate("Delete") + '"></i>').append('<i class="fa fa-plus fr-insert-img" title="' + b.language.translate("Insert") + '"></i>'), D.find(".fr-selected-tag").each(function (a, b) {
                    w(f, b.text) || e.hide()
                }), f.on("load", function () {
                    e.removeClass("fr-empty"), e.height("auto"), I++;
                    var a = n(parseInt(f.parent().attr("class").match(/fr-image-(\d+)/)[1], 10) + 1);
                    o(a), p(!1), I % b.opts.imageManagerPageSize === 0 && k()
                }), b.events.trigger("imageManager.imageLoaded", [f])
            }, d.onerror = function () {
                I++, e.remove();
                var a = n(parseInt(e.attr("class").match(/fr-image-(\d+)/)[1], 10) + 1);
                o(a), s(L, c), I % b.opts.imageManagerPageSize === 0 && k()
            }, d.src = c.url, m().append(e)
        }
        function m() {
            var b, c;
            return B.find(".fr-list-column").each(function (d, e) {
                var f = a(e);
                0 === d ? (c = f.outerHeight(), b = f) : f.outerHeight() < c && (c = f.outerHeight(), b = f)
            }), b
        }
        function n(b) {
            void 0 === b && (b = 0);
            for (var c = [], d = J - 1; d >= b; d--) {
                var e = B.find(".fr-image-" + d);
                e.length && (c.push(e), a('<div id="fr-image-hidden-container">').append(e), B.find(".fr-image-" + d).remove())
            }
            return c
        }
        function o(a) {
            for (var b = a.length - 1; b >= 0; b--)
                m().append(a[b])
        }
        function p(a) {
            if (void 0 === a && (a = !0), !z.is(":visible"))
                return!0;
            var c = e();
            if (c != K) {
                K = c;
                var d = n();
                f(), o(d)
            }
            var g = b.$window.height(), h = z.find(".fr-modal-wrapper"), i = parseFloat(h.css("margin-top")) + parseFloat(h.css("margin-bottom")), j = parseFloat(h.css("padding-top")) + parseFloat(h.css("padding-bottom")), l = parseFloat(h.css("border-top-width")), m = h.find("h4").outerHeight();
            C.height(Math.min(B.outerHeight(), g - i - j - m - l)), a && k()
        }
        function q(c) {
            var e = a(c.currentTarget).siblings("img");
            if (d(), b.image.showProgressBar(), U)
                U.trigger("click");
            else {
                b.events.focus(!0), b.selection.restore();
                var f = b.position.getBoundingRect(), g = f.left + f.width / 2, h = f.top + f.height;
                b.popups.setContainer("image.insert", b.$box || a("body")), b.popups.show("image.insert", g, h)
            }
            var i = {}, j = e.data();
            for (var k in j)
                "url" != k && "tag" != k && (i[k] = j[k]);
            b.image.insert(e.data("url"), !1, i, U)
        }
        function r(c) {
            var d = a(c.currentTarget).siblings("img"), e = "Are you sure? Image will be deleted.";
            confirm(e) && (b.opts.imageManagerDeleteURL ? b.events.trigger("imageManager.beforeDeleteImage", [d]) !== !1 && (d.parent().addClass("fr-image-deleting"), a.ajax({method: b.opts.imageManagerDeleteMethod, url: b.opts.imageManagerDeleteURL, data: a.extend({src: d.attr("src")}, b.opts.imageManagerDeleteParams)}).done(function (a) {
                b.events.trigger("imageManager.imageDeleted", [a]);
                var c = n(parseInt(d.parent().attr("class").match(/fr-image-(\d+)/)[1], 10) + 1);
                d.parent().remove(), o(c), p(!0)
            }).fail(function () {
                var a = this.xhr();
                s(R, a.response || a.responseText)
            })) : s(S))
        }
        function s(c, d) {
            c >= 10 && 20 > c ? A.hide() : c >= 20 && 30 > c && a(".fr-image-deleting").removeClass("fr-image-deleting"), b.events.trigger("imageManager.error", [{code: c, message: T[c]}, d])
        }
        function t() {
            var a = E.find(".fr-modal-title-line").outerHeight(), b = D.outerHeight();
            E.toggleClass(".fr-show-tags"), E.hasClass(".fr-show-tags") ? (E.css("height", a + b), D.find("a").css("opacity", 1)) : (E.css("height", a), D.find("a").css("opacity", 0))
        }
        function u() {
            var b = D.find(".fr-selected-tag");
            b.length > 0 ? (B.find("img").parent().hide(), b.each(function (b, c) {
                B.find("img").each(function (b, d) {
                    var e = a(d);
                    w(e, c.text) && e.parent().show()
                })
            })) : B.find("img").parent().show();
            var c = n();
            o(c), k()
        }
        function v(b) {
            b.preventDefault();
            var c = a(b.currentTarget);
            c.toggleClass("fr-selected-tag"), u()
        }
        function w(a, b) {
            for (var c = a.attr("data-tag").split(","), d = 0; d < c.length; d++)
                if (c[d] == b)
                    return!0;
            return!1
        }
        function x() {
            A = z.find("#fr-preloader-" + b.id), B = z.find("#fr-image-list-" + b.id), C = z.find("#fr-scroller-" + b.id), D = z.find("#fr-modal-tags-" + b.id), E = D.parent(), K = e(), f();
            var c = E.find(".fr-modal-title-line").outerHeight();
            E.css("height", c), C.css("margin-top", c), b.events.bindClick(z, "i#fr-modal-close-" + b.id, d), a(window).on("resize.imagemanager" + b.id, p), b.helpers.isMobile() && (b.events.bindClick(B, "div.fr-image-container", function (b) {
                z.find(".fr-mobile-selected").removeClass("fr-mobile-selected"), a(b.currentTarget).addClass("fr-mobile-selected")
            }), z.on(b._mousedown, function () {
                z.find(".fr-mobile-selected").removeClass("fr-mobile-selected")
            }), b.events.on("destroy", function () {
                z.off(b._mousedown)
            }, !0)), b.events.bindClick(B, ".fr-insert-img", q), b.events.bindClick(B, ".fr-delete-img", r), z.on(b._mousedown + " " + b._mouseup, function (a) {
                a.stopPropagation()
            }), z.on(b._mousedown, "*", function () {
                b.events.disableBlur()
            }), C.on("scroll", k), b.events.bindClick(z, "i#fr-modal-more-" + b.id, t), b.events.bindClick(D, "a", v), b.events.on("destroy", function () {
                a(window).off("resize.imagemanager" + b.id), z.off(b._mousedown), z.off(b._mousedown, "*"), C.off("scroll"), D.off("click", "a")
            }, !0)
        }
        function y() {
            return b.$wp || "IMG" == b.$el.get(0).tagName ? void h() : !1
        }
        var z, A, B, C, D, E, F, G, H, I, J, K, L = 10, M = 11, N = 12, O = 13, P = 14, Q = 15, R = 21, S = 22, T = {};
        T[L] = "Image cannot be loaded from the passed link.", T[M] = "Error during load images request.", T[N] = "Missing imageManagerLoadURL option.", T[O] = "Parsing load response failed.", T[P] = "Missing image thumb.", T[Q] = "Missing image URL.", T[R] = "Error during delete image request.", T[S] = "Missing imageManagerDeleteURL option.";
        var U;
        return{require: ["image"], _init: y, show: c, hide: d}
    }, !a.FroalaEditor.PLUGINS.image)
        throw new Error("Image manager plugin requires image plugin.");
    a.FroalaEditor.DEFAULTS.imageInsertButtons.push("imageManager"), a.FroalaEditor.RegisterCommand("imageManager", {title: "Browse", undo: !1, focus: !1, callback: function () {
            this.imageManager.show()
        }}), a.FroalaEditor.DefineIcon("imageManager", {NAME: "fa fa-folder"})
}(jQuery);