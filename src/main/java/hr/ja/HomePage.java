package hr.ja;

import hr.ja.lib.*;
import hr.ja.lib.annotation.UrlPath;
import hr.ja.model.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;

import static hr.ja.lib.Html.H3;
import static hr.ja.lib.Layout.*;
import static hr.ja.lib.TableBrowser.Event.ROW_SELECTED;

@Slf4j
public class HomePage extends Page {

    @UrlPath("/")
    public PageResponse request() {

        WebSite webSite = Context.site();
        webSite.setTitle("Home page");

        Form<User> form = createForm();

        if (form.submitetdAndValidated()) {
            return PageResponse.redirect(new UserPage("User is created"));
        }

        Table<User> table = createTableData();
        Row row = row(
              col(
                    new Card("My table", h3("tu ce biti table"))
              ),
              col(
                    new Card(H3("This is form"), form)
              ));

        Context.site().setTitle("ssdf");

        log.debug("dela dela");

        return PageResponse.show(row);
    }

    private Table<User> createTableData() {
        Table<User> t = new Table<>();
        TableConfiguration tableConfiguration = t.getConfiguration();

        t.column("ID", User::getId);
        t.column("Name", User::getName);

        t.browser().on(ROW_SELECTED, () -> """
                  console.log("row ", row)
              """);

        t.setData(generate20sampleUser());

        return t;
    }

    private Collection<User> generate20sampleUser() {
        Collection<User> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User u = new User(i, "pero " + i);
            users.add(u);
        }
        return users;
    }

    private Form<User> createForm() {
        Form<User> form = new Form<>();
        form.add(new TextField(User.Fields.name, "Name"));
        form.add(new Button("Save user"));


        return form;
    }

}
