package hr.ja.lib;

public class WebSite {

    public String wrap(String htmlBody) {
        return """
              <!doctype html>
              <html lang="en">
                <head>
                  <meta charset="utf-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1">
                  <title>Weboo</title>
                  <link href="/bootstrap-5.3.0-alpha3/bootstrap.min.css" rel="stylesheet">
                  <link href="/tabulator/css/tabulator.min.css" rel="stylesheet">
                </head>
                <body>
                  <div class='container mt-3'>
                      %s
                  </div>
                  <script src="/bootstrap-5.3.0-alpha3/bootstrap.bundle.min.js" ></script>
                  <script type="text/javascript" src="/tabulator/js/tabulator.min.js"></script>
                </body>
              </html>
              """.formatted(htmlBody);
    }

}
