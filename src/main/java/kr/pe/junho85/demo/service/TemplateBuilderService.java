package kr.pe.junho85.demo.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Locale;

@Service
public class TemplateBuilderService {
    public String build(String templateString, Object dataModel) {
        try (StringWriter out = new StringWriter();
             StringReader reader = new StringReader(templateString)
        ) {
            Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            cfg.setLocale(Locale.KOREA);

            Template template = new Template("name", reader, cfg);

            template.process(dataModel, out);
            return out.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
