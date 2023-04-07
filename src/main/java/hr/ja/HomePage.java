package hr.ja;

import hr.ja.lib.*;
import hr.ja.model.User;
import lombok.extern.slf4j.Slf4j;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

import static hr.ja.lib.Html.H3;
import static hr.ja.lib.Layout.col;
import static hr.ja.lib.Layout.row;
import static hr.ja.lib.TableBrowser.Event.ROW_SELECTED;

@Slf4j
public class HomePage extends Page {

    public void get(Request req, Response res) {
        Form<User> form = createForm();
        form.onSubmit(user -> {

            log.debug("user ", user);
        });

        Row row = row(
              col(
                    new Card("My table", createTableData())
              ),
              col(
                    new Card(H3("This is form"), form)
              ));

        add(row);
    }

    public static void main(String[] args) {
        HomePage page = new HomePage();
        page.get(null, null);

        log.debug(page.toHtml());
    }


    private static void acceptForm(User user) {
        log.debug("Submited user {}", user);
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
        form.add(new TextField(User.Fields.name));
        form.add(new Button("Save user"));
        return form;
    }


}
