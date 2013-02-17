package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.json.JsonResponse;
import demo.json.PersonJsonRequest;
import demo.model.Person;
import demo.service.CreditCardService;
import demo.service.PersonService;


/**
 * 
 * @author Ashok Muthukrishnan
 *
 */
@Controller
@RequestMapping("/main/record")
public class MainController {

    @Autowired	
	private PersonService personService;
	
    @Autowired
    private CreditCardService creditCardService;
    
	
	/**
	 * Retrieves the "Records" page
	 */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getRecords(Model model) 
    {
 		return "recordajax";
	}
    
    /**
     * 
     * @return
     */
    @RequestMapping(value = "/crud",method = RequestMethod.POST)
    public @ResponseBody  JsonResponse<Person> getAllPerson() {

    	List<Person> persons = personService.getAll();
    	
    	JsonResponse<Person> response = new JsonResponse<Person>();
    	response.setRecords(persons);
    	response.setResult("OK");
    	response.setRecordCount(persons.size());

    	return response;
	}
 
  /**
   * 
   * @param record
   * @return
   */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody  JsonResponse<Person>  createperson(@RequestBody PersonJsonRequest record ) {
  
    	Boolean success = personService.add(record.getPerson());
    	JsonResponse<Person>  response = new JsonResponse<Person>();
    	response.setResult("OK");
    	response.setRecord(record.getPerson());
    	return response;
	}
    
    /**
     * 
     * @param record
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody  JsonResponse<Person> updatePerson(@RequestBody PersonJsonRequest record ) {
    	Boolean success = personService.edit(record.getPerson());
    	JsonResponse<Person> response = new JsonResponse<Person>();
    	response.setResult("OK");
    	//response.setRecord(record.getPerson());
    	return response;
	}
    
    /**
     * 
     * @param record
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody  JsonResponse<Person> deletePerson(@RequestBody PersonJsonRequest record ) {
    	  
    	Boolean success = personService.delete(record.getId());
    	JsonResponse<Person> response = new JsonResponse<Person>();
    	response.setResult("OK");
    	return response;
	}
    

    
}
