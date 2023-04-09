package hr.ja.lib;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import spark.utils.CollectionUtils;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;

@Slf4j
public class TextField<M> extends FormField<M> {

    @Setter
    @Getter
    private String value;

    public TextField(String name, String label) {
        this.name = name;
        this.label = label;
    }

    @Override
    public String toHtml() {
        String errorClass = "";
        if (!CollectionUtils.isEmpty(errorMessages)) {
            errorClass = "is-invalid";
        }

        String value = defaultIfEmpty(binderToWeb.getModelValue(model), "");

        String html = """
               <div class="mb-3" id='field_{id}'>
                  <label for="{name}" class="form-label">{label}</label>
                  <input type="text" class="form-control {errorClass}" id="{name}" name="{name}" value="{value}">
                  {#for err in errMsgs}
                  <div class="invalid-feedback">
                      {err}
                  </div>
                  {/for}
                  
                </div>
                            
              """;
        return MyUtil.qute(html, Map.of("id", getId(), "name", name, "label", label, "errMsgs", errorMessages, "errorClass", errorClass,
              "value", value));

    }

}
