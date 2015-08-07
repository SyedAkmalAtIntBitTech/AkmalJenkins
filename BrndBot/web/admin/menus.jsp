<%-- 
    Document   : menus
    Created on : Jul 8, 2015, 11:31:10 AM
    Author     : intbit
--%>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="index.jsp"><p>BrndBot</p></a>
    </div>
    <div>
      <ul class="nav navbar-nav">
          <li class="active"><a href="index.jsp">Home</a></li>
        <li><a href="organizations.jsp">Organization</a></li>
        <li><a href="looks.jsp">Looks</a></li>
        <li><a href="brandpersonality.jsp">Brand Personality</a></li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Fonts <span class="caret"></span></a>
          <ul class="dropdown-menu">
              <li><a href="fontsSize.jsp">Fonts Size</a></li>
              <li><a href="fontsfamily.jsp">Fonts Family</a></li>
              <li><a href="fontsstyle.jsp">Fonts Style</a></li>
              <li><a href="fonttheme.jsp">Font theme</a></li>
          </ul>
        </li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Colors<span class="caret"></span></a>
          <ul class="dropdown-menu">
              <li><a href="colors.jsp">Colors</a></li>
              <li><a href="colortheme.jsp">Color theme</a></li>
          </ul>
        </li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Category<span class="caret"></span></a>
          <ul class="dropdown-menu">
              <li><a href="categories.jsp">Category</a></li>
              <li><a href="subcategories.jsp">Sub category</a></li>
              <li><a href="blocks.jsp">Blocks</a></li>
          </ul>
        </li>
         <li><a href="layoutmodel.jsp">Layout Model</a></li>
         
        <li><a href="logout.jsp">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
