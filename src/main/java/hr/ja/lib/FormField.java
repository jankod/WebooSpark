package hr.ja.lib;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class FormField<M> extends Widget {

    @Setter
    protected M model;

    protected String name;
    protected String label;


    protected BindToWeb<M> binderToWeb = new BindToWeb<M>() {
        @SneakyThrows
        @Override
        public String getModelValue(M t) {
            BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor d : descriptors) {
                Method readMethod = d.getReadMethod();
            }
            return null;
        }
    };

    protected BindFromWeb<M> binderFromWeb;

    @Getter
    protected List<ValidatorVrapper<M>> validators = new ArrayList<>();

    protected List<String> errorMessages = new ArrayList<>();

    public FormField<M> validate(Validator<M> validator, String errorMessage) {
        validators.add(new ValidatorVrapper<>(validator, name, errorMessage));
        return this;
    }

    public FormField<M> bindFromWeb(BindFromWeb<M> binderFromWeb) {
        this.binderFromWeb = binderFromWeb;
        return this;
    }

    public FormField<M> bindToWeb(BindToWeb<M> binderToWeb) {
        this.binderToWeb = binderToWeb;
        return this;
    }

    public void addErrorMessage(String errorMessage) {
        errorMessages.add(errorMessage);
    }

    public void validateNotEmpty(String errMsg) {
        validate(model -> {
            String value = binderToWeb.getModelValue(model);
            return StringUtils.isEmpty(value);
        }, errMsg);
    }
}
