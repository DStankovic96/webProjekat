package dtos;

import model.Trener;

public class UpdateTrenerDTO extends Trener {
	
	private String oldPassword;
	
	public UpdateTrenerDTO(String oldPassword) {
		super();
		this.oldPassword = oldPassword;
	}

	public UpdateTrenerDTO() {
		super();
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

}
