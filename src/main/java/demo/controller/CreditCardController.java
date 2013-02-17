package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.json.CreditCardJsonRequest;
import demo.json.JsonResponse;
import demo.model.CreditCard;
import demo.model.Person;
import demo.service.CreditCardService;


/**
 * 
 * @author Ashok Muthukrishnan
 *
 */
@Controller
@RequestMapping("/main/creditcard")
public class CreditCardController {
    
	@Autowired
	private CreditCardService creditCardService;
	
    @RequestMapping(value = "/listCreditCard", method = RequestMethod.POST)
    public @ResponseBody JsonResponse<CreditCard> getCreditCardForPerson(@RequestBody CreditCardJsonRequest id) {
    	
    	JsonResponse<CreditCard> res = new JsonResponse<CreditCard>();
    	res.setRecords(creditCardService.getAllCreditCartforPerson(id.getId()));
    	res.setResult("OK");
    	return res;
	}
    
    @RequestMapping(value = "/addCreditCard", method = RequestMethod.POST)
    public @ResponseBody JsonResponse<CreditCard> addCreditCardForPerson(@RequestBody CreditCardJsonRequest record) {
    	
    	JsonResponse<CreditCard> res = new JsonResponse<CreditCard>();
    	Boolean addCC= creditCardService.addCreditCardForaPerson(record.getId(), record.getRecord());
    	res.setRecord(record.getRecord());
    	res.setResult("OK");
    	return res;
	}
    
    
    @RequestMapping(value = "/editCreditCard", method = RequestMethod.POST)
    public @ResponseBody JsonResponse<CreditCard> editCreditCardForPerson(@RequestBody CreditCardJsonRequest record) {
    	
    	JsonResponse<CreditCard> res = new JsonResponse<CreditCard>();
    	Boolean editCC= creditCardService.editSingleCreditCard(record.getRecord());
    	res.setRecord(record.getRecord());
    	res.setResult("OK");
    	return res;
	}
    
    
    @RequestMapping(value = "/deleteCreditCard", method = RequestMethod.POST)
    public @ResponseBody  JsonResponse<Person> deletePerson(@RequestBody CreditCardJsonRequest record ) {
    	  
    	Boolean success = creditCardService.delete(record.getCreditId());
    	JsonResponse<Person> response = new JsonResponse<Person>();
    	response.setResult("OK");
    	return response;
	}

}
