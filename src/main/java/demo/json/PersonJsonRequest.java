package demo.json;

import org.codehaus.jackson.annotate.JsonProperty;

import demo.model.Person;
/**
 * 
 * @author Ashok Muthukrishnan
 *
 */
public class PersonJsonRequest 
{

	@JsonProperty("record")
	private Person person;
	
	private int id;
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
