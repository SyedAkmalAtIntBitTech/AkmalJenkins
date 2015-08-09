var page = require('webpage').create();
//viewportSize being the actual size of the headless browser
page.viewportSize = { width: 1024, height: 768 };
//the clipRect is the portion of the page you are taking a screenshot of
page.clipRect = { top: 'replace_top', left: 'replace_left', width: 'replace_width', height: 'replace_height' };
//the rest of the code is the same as the previous example
page.open('replace_html_location', function() {
  page.render('replace_html_image.png');
  phantom.exit();
});