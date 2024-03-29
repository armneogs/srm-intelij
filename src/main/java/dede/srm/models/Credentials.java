package dede.srm.models;
// Generated Apr 20, 2021, 2:23:40 PM by Hibernate Tools 5.2.12.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Credentials generated by hbm2java
 */
@Entity
@Table(name = "credentials", schema = "public")
public class Credentials implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2245860222524670747L;
	private Long id;
	private String username;
	private String password;
	private String description;
	private String refTable;
	private Long refId;
	private Date createDate;
	private Date updateDate;
	private String remark;

	public Credentials() {
	}

	public Credentials(Long id, String username, String password, String refTable, Long refId, Date createDate,
			Date updateDate) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.refTable = refTable;
		this.refId = refId;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public Credentials(Long id, String username, String password, String description, String refTable, Long refId,
			Date createDate, Date updateDate, String remark) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.description = description;
		this.refTable = refTable;
		this.refId = refId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.remark = remark;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "username", nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ref_table", nullable = false, length = 50)
	public String getRefTable() {
		return this.refTable;
	}

	public void setRefTable(String refTable) {
		this.refTable = refTable;
	}

	@Column(name = "ref_id", nullable = false)
	public Long getRefId() {
		return this.refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false, length = 22)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", nullable = false, length = 22)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
