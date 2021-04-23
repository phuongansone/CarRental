package service;

import common.RequestParam.UserParam;
import dao.UserDAO;
import dto.RoleDTO;
import dto.UserDTO;
import dto.UserStatusDTO;
import enums.Role;
import enums.UserStatus;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author PC
 */
public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }
    
    public int insertUser(HttpServletRequest request) 
            throws SQLException, ClassNotFoundException {
        return userDAO.insertUser(getUserDTOFromRegisterRequest(request));
    }
    
    public UserDTO getUserDTOFromRegisterRequest(HttpServletRequest request) {
        UserDTO userDTO = new UserDTO();
        
        userDTO.setEmail(request.getParameter(UserParam.EMAIL));
        userDTO.setPhone(request.getParameter(UserParam.PHONE));
        userDTO.setName(request.getParameter(UserParam.NAME));
        userDTO.setAddress(request.getParameter(UserParam.ADDRESS));
        userDTO.setPassword(request.getParameter(UserParam.PASSWORD));
        
        // default role is user
        userDTO.setRole(new RoleDTO(Role.USER.getRoleId(), null));
        
        // default status is new
        userDTO.setUserStatus(new UserStatusDTO(UserStatus.NEW.getId(), null));
        
        return userDTO;
    }
    
    public boolean checkEmailExisted(HttpServletRequest request) 
            throws SQLException, ClassNotFoundException {
        return userDAO.checkEmailExisted(request.getParameter(UserParam.EMAIL));
    }
}
