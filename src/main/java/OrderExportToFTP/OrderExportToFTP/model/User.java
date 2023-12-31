package OrderExportToFTP.OrderExportToFTP.model;


public class User {

	private Long userId;

	private Long erpId;
	private String company;
	private String firstname;
	private String lastname;
	private String phone;
	private String email;

	private Address deliveryAddress;

	private Address billingAddress;

	private String username;
	private boolean business;
	private boolean deleted;
	
	public User() {
		
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getErpId() {
		return erpId;
	}

	public void setErpId(Long erpId) {
		this.erpId = erpId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isBusiness() {
		return business;
	}
	public void setBusiness(boolean business) {
		this.business = business;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", erpId=" + erpId + ", company=" + company + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", phone=" + phone + ", email=" + email + ", deliveryAddress="
				+ deliveryAddress + ", billingAddress=" + billingAddress + ", username=" + username + ", business="
				+ business + ", deleted=" + deleted + "]";
	}
	
}