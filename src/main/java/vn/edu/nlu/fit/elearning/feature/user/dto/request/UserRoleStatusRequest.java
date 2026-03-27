package vn.edu.nlu.fit.elearning.feature.user.dto.request;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;

public class UserRoleStatusRequest {
    private Role role;
    private BaseStatus status;

    public UserRoleStatusRequest() {
    }

    public UserRoleStatusRequest(Role role, BaseStatus status) {
        this.role = role;
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
    }
}
