package gov.pcc.pwc.common;

import java.util.Locale;

import org.jvnet.localizer.Localizable;

/**
 * 使用 {@link Localizable} 物件當做狀態訊息來源，可以支援多語系訊息。
 */
public abstract class LocalizableStatusCode implements StatusCode {

    private static final long serialVersionUID = 5276716138470192688L;

    private final Localizable localizable;

    /**
     * 指定 {@code Localizable} 物件建立狀態物件。
     *
     * @param localizable
     *            多語系訊息物件，訊息的鍵值就是狀態碼。
     */
    public LocalizableStatusCode(Localizable localizable) {
        this.localizable = localizable;
    }

    @Override
    public String getName() {
        return localizable.getKey();
    }

    public String getMessage() {
        return this.localizable.toString();
    }

    public String getMessage(Locale locale) {
        return this.localizable.toString(locale);
    }

    /**
     * 以系統預設諨系回傳狀態碼加訊息字串。
     *
     * @return "狀熊語 說明訊息" 字串。
     */
    @Override
    public String toString() {
        return getName() + " " + getMessage();
    }

    /**
     * 指定語系回傳狀態碼加訊息字串。
     *
     * @param locale
     *            語系。
     * @return "狀熊語 說明訊息" 字串。
     */
    public String toString(Locale locale) {
        return getName() + " " + getMessage(locale);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((localizable == null || localizable.getKey() == null)
                ? 0
                : /* 只算 key... 勉強合理吧? */localizable.getKey().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LocalizableStatusCode other = (LocalizableStatusCode) obj;
        if (localizable == null) {
            if (other.localizable != null)
                return false;
        } else if (/* 只比對 key... 勉強算合理吧? */!localizable.getKey().equals(other.localizable.getKey()))
            return false;
        return true;
    }
}
