package models;

import java.util.Comparator;

import javax.persistence.*;


@Entity
@Table(name = "requests")
public class Request implements Comparator<Request> {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_Id")
	private int Id;
	
	@Column()
	private int user_Id;
	
	@Column()
	private double amount;
	
	@Column()
	private String title;
	
	@Column()
	private String reason;
	
	/**
	 * options are:
	 *  -1 for rejected
	 *  0 for pending
	 *  1 for approved
	 */
	@Column()
	private int approval = 0;
	
	public Request(int id, int user_Id, double amount, String title, String reason, int approval) {
		super();
		Id = id;
		this.user_Id = user_Id;
		this.title = title;
		this.amount = amount;
		this.reason = reason;
		this.approval = approval;
	}

	public Request() {
		
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getUser_Id() {
		return user_Id;
	}

	public void setUserId(int user_Id) {
		this.user_Id = user_Id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getApproval() {
		return approval;
	}

	public void setApproval(int approval) {
		this.approval = approval;
	}

	@Override
	public String toString() {
		return "Request [Id=" + Id + ", user_Id=" + user_Id + ", amount=" + amount + ", title=" + title + ", reason="
				+ reason + ", approval=" + approval + "]";
	}

	@Override
	public int compare(Request o1, Request o2) {
		return Double.compare(o1.getAmount(), o2.getAmount());
	}
	
}
