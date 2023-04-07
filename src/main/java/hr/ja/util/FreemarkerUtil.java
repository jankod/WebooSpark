package hr.ja.util;

//import freemarker.cache.StringTemplateLoader;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import hr.ja.lib.Widget;
//import lombok.SneakyThrows;
//
//import java.io.IOException;
//import java.io.StringWriter;
//import java.lang.reflect.Field;

public class FreemarkerUtil {
//    private Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
//    private StringTemplateLoader templateLoader = new StringTemplateLoader();
//
//    private FreemarkerUtil() {
//
//        cfg.setDefaultEncoding("UTF-8");
//        //cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
//        cfg.setLogTemplateExceptions(false);
//        cfg.setWrapUncheckedExceptions(true);
//        //cfg.setOutputFormat(HTMLOutputFormat.INSTANCE);
//        // Do not fall back to higher scopes when reading a null loop variable:
//        cfg.setFallbackOnNullLoopVariable(false);
//        cfg.setTemplateLoader(templateLoader);
//    }
//
//
//    @SneakyThrows
//    public static String parse(String template, Object data) {
//        Template t = get().getTemplateFromString(template);
//        return get().parseTemplate(t, data);
//
//    }
//
//    @SneakyThrows
//    public static String parse(Widget widget) {
//        Template template = get().getTemplateFromString(widget.toHtml());
//        return get().parseTemplate(template, widget);
//    }
//
////    @SneakyThrows
////    public static String parse(Object objectWithTemplateField) {
////        Template template = get().getTemplate(objectWithTemplateField);
////        return get().parseTemplate(template, objectWithTemplateField);
////    }
//
//    private Template getTemplateFromString(String template) throws NoSuchFieldException, IllegalAccessException, IOException {
//        if (templateLoader.findTemplateSource(template) == null) {
//            templateLoader.putTemplate(template, template);
//        }
//        return cfg.getTemplate(template);
//    }
//
////    private Template getTemplate(Object objectWithTemplateField) throws NoSuchFieldException, IllegalAccessException, IOException {
////        String templateName = objectWithTemplateField.getClass().getName();
////        if (templateLoader.findTemplateSource(templateName) == null) {
////            Field t = objectWithTemplateField.getClass().getField("template");
////            t.setAccessible(true);
////            String templateString = (String) t.get(objectWithTemplateField);
////            templateLoader.putTemplate(templateName, templateString);
////        }
////
////        Template t = cfg.getTemplate(templateName);
////
////        return t;
////    }
//
//
//    @SneakyThrows
//    private String parseTemplate(Template t, Object data) throws IOException, TemplateException {
//        StringWriter out = new StringWriter();
//        t.process(data, out);
//        return out.toString();
//    }
//
//
//    private static FreemarkerUtil instance;
//
//    public static FreemarkerUtil get() {
//        if (instance == null) {
//            instance = new FreemarkerUtil();
//        }
//        return instance;
//
//    }
//
//    public static void main(String[] args) throws IOException, TemplateException {
//        FreemarkerUtil util = new FreemarkerUtil();
////        String html = util.parse(new Page1());
////        System.out.println(html);
//    }

}