package hr.ja;

import hr.ja.lib.*;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class MyWebsite extends WebSite {

    public MyWebsite(String siteName) {
        setSiteName(siteName);
    }

    @Override
    public String siteLayout(String pageHtmlBody, WebSiteParam param) {
        String h = """
               <!doctype html>
              <html lang="en" data-bs-theme="light">
              <head>
                  <meta charset="utf-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1">
                  <title>{title}</title>
                  <link href="/bootstrap-5.3.0-alpha3/bootstrap.min.css" rel="stylesheet">
                  <link href="/tabulator/css/tabulator.min.css" rel="stylesheet">
                  <script type="text/javascript" src="/bootstrap-5.3.0-alpha3/bootstrap.bundle.min.js"></script>
                  <script type="text/javascript" src="/tabulator/js/tabulator.min.js"></script>
                  <script type="text/javascript" src="/jquery-3.6.4.min.js"></script>
                  <script type="text/javascript" src="/all.js"></script>
                  <style>
                    {mainStile}
                  </style>
                  </head>
              <body>
              <header class="navbar sticky-top bg-secondary  flex-md-nowrap p-0 shadow">
                <a class="navbar-brand text-bg-secondary col-md-3 col-lg-2 me-0 px-3 fs-6" href="#">{siteName}</a>
                <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                </div>
              </header>
                            
              <div class="container-fluid">
                <div class="row">
                 {nav}
                  <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 py-3">
                      {body}
                  </main>
                </div>
              </div>
                            
              </body>
               </body>
              </html>
                            
                            
              """;
        return MyUtil.qute(h, Map.of(
              "mainStile", getMainStyle(),
              "title", MyUtil.escape(ObjectUtils.defaultIfNull(param.getTitle(), getDefaultTitle())),
              "nav", sidebarNav(param).toHtml(),
              "body", pageHtmlBody,
              "siteName", getSiteName()
        ));
        //.formatted(MyUtil.escape(getTitle()), getSiteName(), sidebarNav().toHtml(), pageHtmlBody);

    }

    @Override
    public Widget sidebarNav(WebSiteParam param) {

        String navHtml = getNavigations().stream().map(nav -> {
            boolean selected = nav.getPageClass().equals(param.getCurrentPage());
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

    private String getMainStyle() {
        return """
              body {
                  font-size: .875rem;
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
                            """;
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
