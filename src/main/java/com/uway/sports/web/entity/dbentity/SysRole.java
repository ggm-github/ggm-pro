package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;


public class SysRole implements Serializable {

	public static final class Builder {
		private Integer roleId;
		/** 角色名 */
		private String	roleName;
		/** 角色包含的功能 */
		private String	roleDetail;
		/** 角色备注 */
		private String	roleBak;

		public Builder withRoleId(Integer roleId) {
			this.roleId = roleId;
			return this;
		}

		public Builder withRoleName(String roleName) {
			this.roleName = roleName;
			return this;
		}

		public Builder withRoleDetail(String roleDetail) {
			this.roleDetail = roleDetail;
			return this;
		}
		public Builder withRoleBak(String roleBak) {
			this.roleBak = roleBak;
			return this;
		}

		public SysRole build() {
			SysRole sysRole = new SysRole();
			sysRole.setRoleId(roleId);
			sysRole.setRoleName(roleName);
			sysRole.setRoleDetail(roleDetail);
			sysRole.setRoleBak(roleBak);
			return sysRole;
		}
	}

    private Integer roleId;
    /** 角色名 */
    private String	roleName;
    /** 角色包含的功能 */
    private String	roleDetail;
	/** 角色备注 */
	private String	roleBak;

  	public Integer getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleDetail() {
		return roleDetail;
	}
	
	public void setRoleDetail(String roleDetail) {
		this.roleDetail = roleDetail;
	}

	public String getRoleBak() {
		return roleBak;
	}

	public void setRoleBak(String roleBak) {
		this.roleBak = roleBak;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SysRole sysRole = (SysRole) o;

		if (roleId != null ? !roleId.equals(sysRole.roleId) : sysRole.roleId != null) return false;
		if (roleName != null ? !roleName.equals(sysRole.roleName) : sysRole.roleName != null) return false;
		if (roleDetail != null ? !roleDetail.equals(sysRole.roleDetail) : sysRole.roleDetail != null) return false;
		return roleBak != null ? roleBak.equals(sysRole.roleBak) : sysRole.roleBak == null;
	}

	@Override
	public int hashCode() {
		int result = roleId != null ? roleId.hashCode() : 0;
		result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
		result = 31 * result + (roleDetail != null ? roleDetail.hashCode() : 0);
		result = 31 * result + (roleBak != null ? roleBak.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "SysRole{" +
				"roleId=" + roleId +
				", roleName='" + roleName + '\'' +
				", roleDetail='" + roleDetail + '\'' +
				", roleBak='" + roleBak + '\'' +
				'}';
	}
}
