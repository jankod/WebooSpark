package hr.ja.lib;

import hr.ja.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import spark.Request;

import static org.apache.commons.lang3.StringUtils.*;
import static org.mockito.Mockito.*;


@Slf4j
class TextFieldTest {
    public static void main(String[] args) {

        MockedStatic<Context> c = mockStatic(Context.class);
        c.when(Context::request).thenReturn(new FakeRequest());


        Form<User> form = new Form<>(User.class);

        form.text(User.Fields.name, "Name")
              .bindToWeb(User::getName)
              .bindFromWeb((request, user) -> user.setName(request.params("name")))
              .validate(model -> isEmpty(model.getName()), "Name is empty!")
              .validate(model -> length(model.getName()) > 20, "Length is more than 20");

        form.add(new SubmitButton("Save user"));

        if (form.isSubmitted() && form.isValid()) {
            log.debug("validna je forma");
        }
        System.out.println(form.toHtml());
    }


}

@Slf4j
class FakeRequest extends Request {
    public String params(String paramName) {
        log.debug("Pozvao me {}", paramName);

        return "123456789012345678901234567890";
    }

    public String requestMethod() {
        return "POST";
    }

}