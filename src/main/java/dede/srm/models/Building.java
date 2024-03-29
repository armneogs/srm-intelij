package dede.srm.models;
// Generated May 13, 2021, 12:04:31 PM by Hibernate Tools 5.2.12.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Building generated by hbm2java
 */
@Entity
@Table(name = "building", schema = "public")
public class Building implements java.io.Serializable {

	private static final long serialVersionUID = 3454173008622659630L;
	private Long id;
	private String name;
	private String detail;
	private Date createDate;

	public Building() {
	}

	public Building(Long id, String name, Date createDate) {
		this.id = id;
		this.name = name;
		this.createDate = createDate;
	}

	public Building(Long id, String name, String detail, Date createDate) {
		this.id = id;
		this.name = name;
		this.detail = detail;
		this.createDate = createDate;
	}

	@Id
	@SequenceGenerator(name = "building_id_seq", sequenceName = "building_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "building_id_seq")
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

	@Column(name = "detail")
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false, length = 22)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
