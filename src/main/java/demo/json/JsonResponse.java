package demo.json;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Ashok Muthukrishnan
 *
 * @param <T>
 */
public class JsonResponse<T> 
{

	@JsonProperty("Result")
	private String result;
	
	@JsonProperty("Records")
	private List<T> records;

	@JsonProperty("Record")
	private T record;

	@JsonProperty("TotalRecordCount")
	private int recordCount;
	
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public T getRecord() {
		return record;
	}

	public void setRecord(T record) {
		this.record = record;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	
	
}
