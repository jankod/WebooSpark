package hr.ja.lib;

import spark.Request;

public class Form<M> extends Widget {


    //private SubmitHandler<M> submitHandler;

    public void onSubmit(SubmitHandler<M> submitHandler) {
        EventManager.event(this, submitHandler);
        //  this.submitHandler = submitHandler;
    }

    @Override
    public String toHtml() {

        return """
              <form action='/form-handle' id='%s' method='post'>
                            
                      %s
              </form>
              """.formatted(getId(), getChildrenHtml());
    }

    public boolean submitetdAndValidated() {
        Request req = Context.request();
        if (req.requestMethod().equals("POST")) {

        }
        return true;

    }
}

