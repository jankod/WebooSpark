package hr.ja;

import hr.ja.lib.*;

import java.util.stream.Collectors;

public class MyWebsite extends WebSite {

    @Override
    public String siteLayout(String pageHtmlBody) {
        String h = """
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
                    font-size: .875rem;
                  }
                  
                  .feather {
                    width: 16px;
                    height: 16px;
                  }
                  
                  .sidebar {
                    position: fixed;
                    top: 0;
                    bottom: 0;
                    left: 0;
                    z-index: 100; /* Behind the navbar */
                    padding: 48px 0 0; /* Height of navbar */
                    box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);
                  }
                  
                  .sidebar-sticky {
                    height: calc(100vh - 48px);
                    overflow-x: hidden;
                    overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
                  }
                  
                  .sidebar .nav-link {
                    font-weight: 500;
                    color: #333;
                  }
                  
                  .sidebar .nav-link .feather {
                    margin-right: 4px;
                    color: #727272;
                  }
                  
                  .sidebar .nav-link.active {
                    color: #2470dc;
                  }
                  
                  .sidebar .nav-link:hover .feather,
                  .sidebar .nav-link.active .feather {
                    color: inherit;
                  }
                  
                  .sidebar-heading {
                    font-size: .75rem;
                  }
                  
                  .navbar-brand {
                    padding-top: .75rem;
                    padding-bottom: .75rem;
                    background-color: rgba(0, 0, 0, .25);
                    box-shadow: inset -1px 0 0 rgba(0, 0, 0, .25);
                  }
                  
                  .navbar .navbar-toggler {
                    top: .25rem;
                    right: 1rem;
                  }
                  
                  .navbar .form-control {
                    padding: .75rem 1rem;
                  }
                  
                  .form-control-dark {
                    color: #fff;
                    background-color: rgba(255, 255, 255, .1);
                    border-color: rgba(255, 255, 255, .1);
                  }
                  
                  .form-control-dark:focus {
                    border-color: transparent;
                    box-shadow: 0 0 0 3px rgba(255, 255, 255, .25);
                  }
                  
                  </style>
                  </head>
              <body>
              <header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
                <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="#">Company name</a>
                <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                </div>
              </header>
                            
              <div class="container-fluid">
                <div class="row">
                 %s
                            
                  <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                      <h1 class="h2">Dashboard</h1>
                      <div class="btn-toolbar mb-2 mb-md-0">
                        <div class="btn-group me-2">
                          <button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
                        </div>
                        <button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle">
                          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-calendar align-text-bottom" aria-hidden="true"><rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect><line x1="16" y1="2" x2="16" y2="6"></line><line x1="8" y1="2" x2="8" y2="6"></line><line x1="3" y1="10" x2="21" y2="10"></line></svg>
                          This week
                        </button>
                      </div>
                    </div>
                      
                      %s
                    
                  </main>
                </div>
              </div>
                            
              </body>
               </body>
              </html>
                            
                            
              """.formatted(MyUtil.escape(getTitle()), sidebarNav().toHtml(), pageHtmlBody);
        ;

        return h;
    }

    @Override
    public Widget sidebarNav() {

        String navHtml = getNavigations().stream().map(nav -> {
            boolean selected = nav.getPageClass().equals(getCurrentPage());
            NavLink navLink = new NavLink(nav, selected);
            return navLink.toHtml();
        }).collect(Collectors.joining("\n"));

        String h = """
                     <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-body-tertiary sidebar collapse">
                          <div class="position-sticky pt-3 sidebar-sticky">
                            <ul class="nav flex-column">
                                %s
                            </ul>
                          </div>
                        </nav>
                    
              """.formatted(navHtml);

        return new Html(h);
    }
}

class NavLink extends Widget {

    private final Navigation navigation;
    private final boolean active;

    public NavLink(Navigation navigation, boolean active) {
        this.navigation = navigation;
        this.active = active;
    }

    @Override
    public String toHtml() {
        String activeClass = "";
        if (active) {
            activeClass = "active";
        }

        //language=HTML
        return """
              <li class="nav-item">
                  <a class="nav-link %s" aria-current="page" href="%s">
                      %s
                      %s
                  </a>
              </li>
                            """.formatted(activeClass, navigation.getUrlPath(), navigation.getIcon().getSvg("align-text-bottom"), navigation.getLabel());
    }
}
