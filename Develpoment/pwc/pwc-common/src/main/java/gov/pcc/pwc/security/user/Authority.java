package gov.pcc.pwc.security.user;

import java.io.Serializable;
import java.util.Collection;

public interface Authority extends Serializable {
    /**
     * 取得職權識別碼
     *
     * @return 職權識別碼。
     */
    String getId();

    /**
     * 取得職權名稱。
     *
     * @return 職權名稱。
     */
    String getName();

    /**
     * 取得子職權集合。
     *
     * @return 子職權集合。
     */
    Collection<? extends Authority> getAuthorities();

    /**
     * {@inheritDoc}
     * <p>
     * @implSpec 實作應該只拿 {@link Authority#getId()} 的值來判斷。
     */
    @Override
    boolean equals(Object o);
}
