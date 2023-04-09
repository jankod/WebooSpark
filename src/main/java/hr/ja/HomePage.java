package hr.ja;

import hr.ja.lib.*;
import hr.ja.lib.annotation.UrlPath;
import hr.ja.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

import static hr.ja.lib.HtmlUtil.H3;
import static hr.ja.lib.Layout.*;
import static hr.ja.lib.TableBrowser.Event.ROW_SELECTED;

@Slf4j
public class HomePage extends Page {

    @UrlPath("/")
    public PageResponse request() {
        Context.site().setTitle("Home page");

        log.debug("param name {}", Context.request().queryParams("name"));

        Form<User> form = createForm();

        if (form.isSubmitted() && form.isValid()) {
            log.debug("User name: " + form.getModel().getName());
            return PageResponse.redirect(UserPage.class, "User is created");
        }
        log.debug("usrr " + form.getModel());

        Table<User> table = createTableData();
        Row row = row(
              col(
                    new Card("My table", h3("tu ce biti table"))
              ),
              col(
                    new Card(H3("This is form"), form)
              ));

        Context.site().setTitle("ssdf");

        log.debug("prikaz home page");

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
        Form<User> form = new Form<>(new User());

        Validator<User> userValidatorLength = u -> StringUtils.length(u.getName()) > 20;

        form.text(User.Fields.name, "Name")
              .bindToWeb(User::getName)
              .bindFromWeb((request, user) -> user.setName(request.queryParams("name")))
//              .validate(model -> isNotEmpty(model.getName()), "Name is empty!")
              .validate(userValidatorLength, "Length is more than 20")
              .validateNotEmpty("Name is empty");


        form.add(new SubmitButton("Save user"));


        return form;
    }

}
