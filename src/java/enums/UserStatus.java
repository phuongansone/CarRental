package enums;

/**
 *
 * @author PC
 */
public enum UserStatus {
    NEW(1),
    ACTIVATE(2),
    DEACTIVATE(3);
    
    public final int id;
    
    UserStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
}
