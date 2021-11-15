package dede.srm.models;
// Generated Feb 17, 2021, 2:52:29 PM by Hibernate Tools 5.2.12.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DataType generated by hbm2java
 */
@Entity
@Table(name = "data_type", schema = "public")
public class DataType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8288093329012922937L;
	private Long id;
	private String name;
	private Date createDate;
	private Date updateDate;
	private String remark;
	private Long createBy;
	private Long updateBy;

	public DataType() {
	}

	public DataType(Long id, String name, Date createDate, Date updateDate) {
		this.id = id;
		this.name = name;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public DataType(Long id, String name, Date createDate, Date updateDate, String remark, Long createBy,
			Long updateBy) {
		this.id = id;
		this.name = name;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.remark = remark;
		this.createBy = createBy;
		this.updateBy = updateBy;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "create_by")
	public Long getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	@Column(name = "update_by")
	public Long getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

}
