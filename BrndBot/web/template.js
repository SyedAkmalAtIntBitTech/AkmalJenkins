var options = {
    viewportSize: {
        height: 10,
        width: 10
    }
};
var page = require('webpage').create(options);
//viewportSize being the actual size of the headless browser
page.viewportSize = { width: 'replace_width', height: 'replace_height' };
//the clipRect is the portion of the page you are taking a screenshot of
page.clipRect = { top: 'replace_top', left: 'replace_left', width: 'replace_width', height: 'replace_height' };
//the rest of the code is the same as the previous example
page.open('replace_html_location', function() {
  page.render('replace_html_image.png');
  phantom.exit();
});