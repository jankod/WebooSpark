package hr.ja.lib;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class WebSite {

    @Setter
    @Getter
    private String defaultTitle = "?Title?";


    @Getter
    @Setter
    private String siteName = "Site name";


    public String siteLayout(String pageHtmlBody, WebSiteParam param) {
        return """
              <!doctype html>
              <html lang="en" data-bs-theme="light">
              <head>
                  <meta charset="utf-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1">
                  <title>%s</title>
                  <link href="/bootstrap-5.3.0-alpha3/bootstrap.min.css" rel="stylesheet">
                  <link href="/tabulator/css/tabulator.min.css" rel="stylesheet">
                  <script type="text/javascript" src="/bootstrap-5.3.0-alpha3/bootstrap.bundle.min.js"></script>
                  <script type="text/javascript" src="/tabulator/js/tabulator.min.js"></script>
                  <script type="text/javascript" src="/jquery-3.6.4.min.js"></script>
                  <script type="text/javascript" src="/all.js"></script>
                  <style>
                      body {
                          min-height: 100vh;
                          min-height: -webkit-fill-available;
                      }
                      html {
                          height: -webkit-fill-available;
                      }
                            
                      main {
                          height: 100vh;
                          height: -webkit-fill-available;
                          max-height: 100vh;
                          overflow-x: auto;
                          overflow-y: hidden;
                      }

                        .b-divider {
                            flex-shrink: 0;
                            width: 0.3rem;
                            height: 100vh;
                            background-color: rgba(0, 0, 0, .1);
                            border: solid rgba(0, 0, 0, .15);
                            border-width: 1px 0;
                            box-shadow: inset 0 0.5em 1.5em rgba(0, 0, 0, .1), inset 0 0.125em 0.5em rgba(0, 0, 0, .15);
                            
                        }
                  </style>
              </head>
              <body>
              <main class='d-flex flex-nowrap'>
                  %s
                  <div class="b-divider"></div>
                  <div class='col py-3'>
                      <div class='container'>
                          %s
                      </div>
                  </div>
                            
                  </div>
                            
              </body>
              </html>
                            """.formatted(MyUtil.escape(param.getTitle()), sidebarNav(param).toHtml(), pageHtmlBody);
    }

    public Widget sidebarNav(WebSiteParam param) {


        String html = """
              <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 280px;">
                            
                  <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <svg class="bi pe-none me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
                    <span class="fs-4">Sidebar</span>
                  </a>
                  <hr>
                  <ul class="nav nav-pills flex-column mb-auto">
                    <li class="nav-item">
                      <a href="#" class="nav-link active" aria-current="page">
                        <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
                        Home
                      </a>
                    </li>
                    <li>
                      <a href="#" class="nav-link text-white">
                        <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
                        Dashboard
                      </a>
                    </li>
                    <li>
                      <a href="#" class="nav-link text-white">
                        <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#table"></use></svg>
                        Orders
                      </a>
                    </li>
                    <li>
                      <a href="#" class="nav-link text-white">
                        <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#grid"></use></svg>
                        Products
                      </a>
                    </li>
                    <li>
                      <a href="#" class="nav-link text-white">
                       <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-ui-radios" viewBox="0 0 16 16">
                         <path d="M7 2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-7a.5.5 0 0 1-.5-.5v-1zM0 12a3 3 0 1 1 6 0 3 3 0 0 1-6 0zm7-1.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-7a.5.5 0 0 1-.5-.5v-1zm0-5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0 8a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zM3 1a3 3 0 1 0 0 6 3 3 0 0 0 0-6zm0 4.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z"/>
                       </svg>
                        Customers
                      </a>
                    </li>
                  </ul>
                  <hr>
                  <div class="dropdown">
                    <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                      <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
                      <strong>mdo</strong>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark text-small shadow" style="">
                      <li><a class="dropdown-item" href="#">New project...</a></li>
                      <li><a class="dropdown-item" href="#">Settings</a></li>
                      <li><a class="dropdown-item" href="#">Profile</a></li>
                      <li><hr class="dropdown-divider"></li>
                      <li><a class="dropdown-item" href="#">Sign out</a></li>
                    </ul>
                  </div>
                </div>
              """;


        return new Html(html);

    }

    @Getter
    private List<Navigation> navigations = new ArrayList<>();

    public void addPage(Class<? extends Page> pageClass, String label, Icon icon) {
        Navigation navigation = new Navigation(pageClass, label, icon);
        navigations.add(navigation);
    }

}
