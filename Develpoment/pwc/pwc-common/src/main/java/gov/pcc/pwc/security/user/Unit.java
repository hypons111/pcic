package gov.pcc.pwc.security.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 組織單位物件。
 */
public class Unit {

    private final String id;
    private final String name;

    @JsonCreator
    public Unit(@JsonProperty("id") String id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 取得 7 碼單位代碼。
     * <p>
     * 代碼規則為:
     * <table>
     *     <tr>
     *         <th>第 1-3 碼</th>
     *         <td>局所代碼，例如 {@code A05}</td>
     *     </tr>
     *     <tr>
     *         <th>第 4, 5 碼</th>
     *         <td>科室代碼，例如 {@code 05}</td>
     *     </tr>
     *     <tr>
     *         <th>第 6, 7 碼</th>
     *         <td>股別代碼，例如 {@code 00}</td>
     *     </tr>
     * </table>
     * 就算是局長室也一定會有足七碼單位代碼。
     */
    public String getId() {
        return id;
    }

    /**
     * 取得單位名稱。
     *
     * @return 單位名稱。
     */
    public String getName() {
        return name;
    }
}
