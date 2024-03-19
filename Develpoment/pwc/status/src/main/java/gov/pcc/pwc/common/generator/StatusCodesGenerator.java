package gov.pcc.pwc.common.generator;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.text.WordUtils;
import org.jvnet.localizer.CodeModelGenerator;
import org.jvnet.localizer.GeneratorConfig;
import org.jvnet.localizer.Localizable;
import org.jvnet.localizer.ResourceBundleHolder;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JVar;

import gov.pcc.pwc.common.DefaultStatusCode;
import gov.pcc.pwc.common.LocalizableStatusCode;

public class StatusCodesGenerator extends CodeModelGenerator {
    private static final String RESOURCE_NAME = "META-INF/services/" + LocalizableStatusCode.class.getName();

    public StatusCodesGenerator(GeneratorConfig config) {
        super(config);
    }

    protected void generateImpl(String className, Properties props) throws AssertionError {
        try {
            JDefinedClass c = this.cm._class(className);
            c.annotate(SuppressWarnings.class).paramArray("value").param("").param("PMD").param("all");
            c.javadoc().add("Generated localization support class.");
            JFieldVar holder = c.field(28, ResourceBundleHolder.class, "holder", this.cm.ref(ResourceBundleHolder.class).staticInvoke("get").arg(c.dotclass()));
            holder.javadoc().add("The resource bundle reference");
            JClass statusCodeJClass = this.getStatusCodeClass();
            Iterator var6 = props.entrySet().iterator();

            while(var6.hasNext()) {
                Entry<Object, Object> e = (Entry)var6.next();
                String key = e.getKey().toString();
                String value = e.getValue().toString();
                Format[] formats = (new MessageFormat(value)).getFormatsByArgumentIndex();
                Map<String, String> params = new HashMap<>();
                List<JVar> args = new ArrayList<>();
                JMethod m = c.method(17, statusCodeJClass, this.toJavaIdentifier(key));

                for(int i = 0; i < formats.length; ++i) {
                    String argName = String.format("arg%d", i);
                    args.add(m.param(this.inferType(formats[i]), argName));
                    if (formats[i] instanceof NumberFormat) {
                        params.put(argName, String.format("%s format parameter, {@code {%d}}, a number.", this.positionalName(i), i));
                    } else if (formats[i] instanceof DateFormat) {
                        params.put(argName, String.format("%s format parameter, {@code {%d}}, a {@link java.util.Date}.", this.positionalName(i), i));
                    } else {
                        params.put(argName, String.format("%s format parameter, {@code {%d}}, as {@link String#valueOf(Object)}.", this.positionalName(i), i));
                    }
                }

                JInvocation nLoc = JExpr._new(this.cm.ref(Localizable.class)).arg(holder).arg(key);
                Iterator var20 = args.iterator();

                while(var20.hasNext()) {
                    JVar arg = (JVar)var20.next();
                    nLoc.arg(arg);
                }

                JInvocation inv = JExpr._new(statusCodeJClass).arg(nLoc);
                m.body()._return(inv);
                m.javadoc().add(WordUtils.wrap(String.format("Key %s: %s.", this.code(key), this.code(value)), 70));
                Iterator var22 = params.entrySet().iterator();

                while(var22.hasNext()) {
                    Entry<String, String> p = (Entry)var22.next();
                    m.javadoc().addParam((String)p.getKey()).add(p.getValue());
                }

                m.javadoc().addReturn().add(WordUtils.wrap(this.code(value), 70));
            }

        } catch (JClassAlreadyExistsException var18) {
            throw new AssertionError(var18);
        }
    }

    private JClass getStatusCodeClass() {
        try {
            List<URL> resources = this.getResources();
            List<String> fullyQualifiedClasses = new ArrayList();
            Iterator var3 = resources.iterator();

            while(var3.hasNext()) {
                URL resource = (URL)var3.next();
                List<String> classNames = Resources.readLines(resource, Charsets.UTF_8);
                Iterator var6 = classNames.iterator();

                while(var6.hasNext()) {
                    String className = (String)var6.next();
                    fullyQualifiedClasses.add(className);
                }
            }

            if (fullyQualifiedClasses.size() == 1) {
                return this.cm.ref((String)fullyQualifiedClasses.get(0));
            } else if (fullyQualifiedClasses.isEmpty()) {
                this.getReporter().debug("No custom LocalizableStatusCode class found through " + RESOURCE_NAME + ", using DefaultStatusCode class.");
                return this.cm.ref(DefaultStatusCode.class);
            } else {
                String message = "Expecting exact one LocalizableStatusCode implementation in classpath. But found " + fullyQualifiedClasses.size() + " (" + fullyQualifiedClasses + ") in the classpath.";
                throw new RuntimeException(message);
            }
        } catch (IOException var8) {
            String message = "Unable to load determine StatusCode class. Caused by: " + var8.getMessage();
            throw new RuntimeException(message, var8);
        }
    }

    private List<URL> getResources() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        List<URL> resources = Collections.list(classLoader.getResources(RESOURCE_NAME));
        Path path = Paths.get(System.getProperty("user.dir") + "/src/main/resources/" + RESOURCE_NAME);
        if (!path.toFile().exists()) {
            path = Paths.get(System.getProperty("user.dir") + "/pwc-common/src/main/resources/" + RESOURCE_NAME);
        }
        if (path.toFile().exists()) {
            resources = new ArrayList(resources);
            resources.add(path.toUri().toURL());
        }

        return resources;
    }

    private String code(String value) {
        return String.format("{@code %s}", value);
    }

    private String escape(String value) {
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    private String positionalName(int i) {
        if (i % 100 >= 9 && i % 100 < 20) {
            return String.format("%dth", i + 1);
        } else {
            switch(i % 10) {
                case 0:
                    return String.format("%dst", i + 1);
                case 1:
                    return String.format("%dnd", i + 1);
                case 2:
                    return String.format("%drd", i + 1);
                default:
                    return String.format("%dth", i + 1);
            }
        }
    }
}
