package enums;

/**
 *
 * @author PC
 */
public enum Role {
    USER(1),
    ADMIN(2);
    
    public final int roleId;
    
    Role(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}
