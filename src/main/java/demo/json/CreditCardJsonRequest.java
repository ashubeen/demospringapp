package demo.json;

import demo.model.CreditCard;

/**
 * 
 * @author Ashok Muthukrishnan
 *
 */
public class CreditCardJsonRequest 
{

	
	private CreditCard record;
	
	private int id;
	
	private int creditId;
	

	public CreditCard getRecord() {
		return record;
	}

	public void setRecord(CreditCard record) {
		this.record = record;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreditId() {
		return creditId;
	}

	public void setCreditId(int creditId) {
		this.creditId = creditId;
	}
	
	
	
	
}
