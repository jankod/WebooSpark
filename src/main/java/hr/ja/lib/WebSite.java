package hr.ja.lib;

import lombok.Setter;

public class WebSite {

    @Setter
    private String title = "?Title?";

    String siteLayout(String pageHtmlBody) {
        return """
              <!doctype html>
              <html lang="en">
                <head>
                  <meta charset="utf-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1">
                  <title>%s</title>
                  <link href="/bootstrap-5.3.0-alpha3/bootstrap.min.css" rel="stylesheet">
                  <link href="/tabulator/css/tabulator.min.css" rel="stylesheet">
                  <script type="text/javascript" src="/bootstrap-5.3.0-alpha3/bootstrap.bundle.min.js" ></script>
                  <script type="text/javascript" src="/tabulator/js/tabulator.min.js"></script>
                  <script type="text/javascript" src="/all.js"></script>
                </head>
                <body>
                  <div class='container mt-3'>
                      %s
                  </div>
                </body>
              </html>
              """.formatted(MyUtil.escape(title), pageHtmlBody);
    }

}
