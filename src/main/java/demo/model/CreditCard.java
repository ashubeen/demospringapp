package demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 
 * @author Ashok Muthukrishnan
 *
 */
@Entity
@Table(name = "CREDITCARD")
public class CreditCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer creditId;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "NUMBER")
	private String number;

	@ManyToOne
	private Person person;

	public Integer getCreditId() {
		return creditId;
	}

	public void setCreditId(Integer creditId) {
		this.creditId = creditId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	

}
