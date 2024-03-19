package gov.pcc.pwc.common;

import static java.lang.String.format;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jvnet.localizer.Localizable;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;

/**
 * 訊息代碼。
 */
public class MessageCode extends LocalizableStatusCode {

    private static final Map<String, Level> LEVELS = ImmutableMap.copyOf(
            Map.ofEntries(
                    Map.entry("S", Level.SUCCESS),
                    Map.entry("I", Level.INFO),
                    Map.entry("W", Level.WARN),
                    Map.entry("E", Level.ERROR)
            ));

    private String applicationId;
    private String componentId;
    private int serial;
    private Level level;

    private static final Pattern CODE_PATTERN = Pattern.compile(
            "(?<applicationId>PWC|PWB)-(?<componentId>[A-Z0-9]{4,7})-(?<serial>[0-9]{4})-(?<level>[SIWE])"
    );

    public MessageCode(Localizable localizable) {
        super(localizable);
        parseCode(getName());
    }

    private void parseCode(String code) {
        Matcher matcher = CODE_PATTERN.matcher(code);
        if (matcher.matches()) {
            applicationId = matcher.group("applicationId");
            componentId = matcher.group("componentId");
            serial = Integer.parseInt(matcher.group("serial"));
            level = resolveLevel(matcher.group("level"));
        } else {
            String message = format("%1$s doesn't match pattern: %2$s", code, CODE_PATTERN);
            throw new IllegalArgumentException(message);
        }
    }

    private Level resolveLevel(String levelString) {
        Level levelResolved = LEVELS.get(levelString);
        if (levelResolved != null) {
            return levelResolved;
        } else {
            throw new IllegalArgumentException("Unknown level string \"" + levelString + "\", allowed values are: " + LEVELS.keySet());
        }
    }

    /**
     * 取得應用系統識別碼。
     *
     * @return 系統識別碼; 例如 {@code SYS}。
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * 取得功能代號或模組功識別碼。
     *
     * @return 功能代號或模組功識別碼。
     */
    public String getComponentId() {
        return componentId;
    }

    /**
     * 取得流水序號。
     *
     * @return 序號; 例如 {@code 001}。
     */
    public int getSerial() {
        return serial;
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MessageCode that = (MessageCode) o;
        return serial == that.serial && Objects.equal(applicationId, that.applicationId) && Objects.equal(componentId, that.componentId) && level == that.level;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), applicationId, componentId, serial, level);
    }


}
