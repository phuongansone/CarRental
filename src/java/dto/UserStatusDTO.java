package dto;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class UserStatusDTO implements Serializable {
    private int id;
    private String name;

    public UserStatusDTO() {
    }

    public UserStatusDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
