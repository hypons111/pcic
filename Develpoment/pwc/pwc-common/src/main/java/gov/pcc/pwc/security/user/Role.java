package gov.pcc.pwc.security.user;

import java.io.Serializable;

/**
 * 角色物件。
 */
public interface Role extends Serializable {
    /**
     * 取得角色識別碼。
     *
     * @return 角色識別碼。
     */
    String getId();

    /**
     * 取得角色名稱。
     *
     * @return 角色名稱。
     */
    String getName();

    /**
     * {@inheritDoc}
     * <p>
     * @implSpec 實作應該只拿 {@link Role#getId()} 的值來判斷。
     */
    @Override
    boolean equals(Object o);
}
