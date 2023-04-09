package hr.ja.lib;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import spark.Request;
import spark.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Slf4j
public class Form<M> extends Widget {

    @Getter
    private M model;

    @SneakyThrows
    public Form(Class<M> modelClass) {
        model = modelClass.getConstructor().newInstance();
    }

    public Form(M model) {
        this.model = model;
    }

    public void onSubmit(SubmitHandler<M> submitHandler) {
        EventManager.event(this, submitHandler);
        //  this.submitHandler = submitHandler;
    }

    public Form<M> add(FormField<M> filed) {
        super.add(filed);
        return this;
    }

    @Override
    public String toHtml() {

        if (validationResult != null) for (Widget child : getChildren()) {
        }

        return """
              <form id='%s' method='post'>
                      %s
              </form>
              """.formatted(getId(), getChildrenHtml());
    }

    private Set<ConstraintViolation<M>> validationResult;


    public boolean isSubmitted() {
        Request req = Context.request();
        return Objects.equals(req.requestMethod(), "POST");
    }

    Boolean hasError = null;

    public boolean isValid() {
        if (hasError != null) {
            return hasError;
        }

        Request req = Context.request();
        List<FormField<M>> fields = getAllFormFileds();

        for (FormField<M> field : fields) {
            field.binderFromWeb.bind(req, model);

            for (ValidatorVrapper<M> validator : field.getValidators()) {
                try {
                    if (validator.getValidator().hasError(model)) {
                        field.addErrorMessage(validator.getErrorMessage());
                        hasError = true;
                    }
                } catch (Exception e) {
                    log.debug("", e);
                    field.addErrorMessage("Exception durign validation: " + e.getMessage());
                    hasError = true;
                }
            }

        }
        if (hasError == null) {
            hasError = false;
        }
        return !hasError;
    }

    private List<FormField<M>> getAllFormFileds() {
        List<FormField<M>> result = new ArrayList<>();

        populateFormField(result, getChildren());

        return result;
    }

    private void populateFormField(List<FormField<M>> result, List<Widget> children) {
        if (CollectionUtils.isEmpty(children)) {
            return;
        }
        for (Widget child : children) {
            if (child instanceof FormField<?>) {
                result.add((FormField<M>) child);
            }
            populateFormField(result, child.getChildren());
        }
    }

    @SneakyThrows
    public boolean submitetdAndValidated() {

        Request req = Context.request();
        if (req.requestMethod().equals("POST")) {
            String body = req.body();

            log.debug("body {}", body);

            model = WebooEngine.getMapper().readValue(body, new TypeReference<M>() {
            });
            log.debug("User {}", model);

            validationResult = WebooEngine.getValidator().validate(model);
            if (validationResult.isEmpty()) {
                return true;
            }
            log.debug("Validation fail {}", validationResult);

        }
        return false;

    }

    public TextField<M> text(String name, String label) {
        TextField<M> textField = new TextField<>(name, label);
        textField.setModel(model);
        add(textField);
        return textField;
    }
}

