package OrderExportToFTP.OrderExportToFTP.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Order {

	private Long orderId;

	private User user;

	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();

	private String comment;

	private Date orderDate;

	private Timestamp created;


	protected void onCreate() {
		created = new Timestamp(System.currentTimeMillis());
	}

	public Order() {
	}

	public Long getOrderId() {
		return orderId;
	}

	public User getUser() {
		return user;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public String getComment() {
		return comment;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}



	public void addOrderDetail(OrderDetail orderDetail) {
		orderDetails.add(orderDetail);
		orderDetail.setOrder(this);
	}

	public void removeOrderDetail(OrderDetail orderDetail) {
		orderDetails.remove(orderDetail);
		orderDetail.setOrder(null);
	}

}
