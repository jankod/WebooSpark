package hr.ja.lib;

import java.util.function.Consumer;

public class Form<M> extends Widget {


    private SubmitHandler<M> submitHandler;

    public void onSubmit(SubmitHandler<M> submitHandler) {
        //EventManager.
        this.submitHandler = submitHandler;
    }

    @Override
    public String toHtml() {
        return """
              <form action='/form-handle' id='%s' method='post'>
                      %s
              </form>
              """.formatted(getId(), getChildrenHtml());
    }
}

