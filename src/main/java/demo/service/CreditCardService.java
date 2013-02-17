package demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import demo.model.CreditCard;
import demo.model.Person;

/**
 * 
 * @author Ashok Muthukrishnan
 * 
 */
@Component
@Transactional
public class CreditCardService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	PersonService persondao;

	/**
	 * 
	 * @param personId
	 * @return
	 */
	public List<CreditCard> getAllCreditCartforPerson(Integer personId) {
		Query query = em
				.createQuery("Select c FROM CreditCard c where c.person.id="
						+ personId);
		return query.getResultList();
	}

	/**
	 * 
	 * @return
	 */
	public List<CreditCard> getAllCreditCard() {
		Query query = em.createQuery("FROM CompanyAddress");
		return query.getResultList();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public CreditCard getCreditCardInfo(Integer id) {
		CreditCard c = (CreditCard) em.find(CreditCard.class, id);
		return c;
	}

	/**
	 * 
	 * @param personId
	 * @param creditCard
	 */
	public Boolean addCreditCardForaPerson(Integer personId, CreditCard creditCard) {
		try
		{
			Person p = persondao.get(personId);
			creditCard.setPerson(p);
			em.merge(creditCard);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	/**
	 * 
	 * @param creditCardId
	 */
	public Boolean delete(Integer creditCardId) {
		try
		{
			CreditCard companyAddress = this.getCreditCardInfo(creditCardId);
			if (null != companyAddress) {
				em.remove(companyAddress);
			}
			
			return true;
		}catch(Exception e)
		{
			return false;
		}
		
	}

	/**
	 * 
	 * @param personId
	 */
	public Boolean deleteAllCreditCardForaPerson(Integer personId) {
		
		try
		{
			Query query = em.createQuery("DELETE FROM CreditCard WHERE person.id="+ personId);
			query.executeUpdate();
			return true;
		}catch(Exception e)
		{
			
			return false;
		}

	}

	/**
	 * 
	 * @param creditCard
	 */
	public Boolean editSingleCreditCard(CreditCard creditCard) 
	{
		
		try
		{
			CreditCard existingCreditCard = this.getCreditCardInfo(creditCard.getCreditId());
			existingCreditCard.setNumber(creditCard.getNumber());
			existingCreditCard.setType(creditCard.getType());
			em.merge(existingCreditCard);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
}
