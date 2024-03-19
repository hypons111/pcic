package gov.pcc.pwc.security.user;

import java.io.Serializable;
import java.util.Collection;

/**
 * 使用者物件。
 */
public interface User extends Serializable {
    /**
     * 取得使用者識別碼。
     *
     * @return 使用者識別碼。
     */
    String getId();

    /**
     * 取得使用者名稱。
     *
     * @return 使用者名稱。
     */
    String getName();

    /**
     * 取得使用者所屬的角色。
     *
     * @return 角色集合，絕不會回傳 {@code null}。
     */
    Collection<? extends Role> getRoles();

    /**
     * 取得使用者所擁有的權職集合。
     *
     * @return 權職集合，絕不會回傳 {@code null}。
     */
    Collection<? extends Authority> getAuthorities();
}
