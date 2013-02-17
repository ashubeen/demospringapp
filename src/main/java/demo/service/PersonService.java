package demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import demo.model.Person;
/**
 * 
 * @author Ashok Muthukrishnan
 *
 */
@Component
@Transactional
public class PersonService {

	
	  // Injected database connection:
    @PersistenceContext private EntityManager em;
 /**
  * 
  * @param person
  * @return
  */
    public Boolean add(Person person)
    {
        try{
    	em.persist(person);
    	return true;
        }catch(Exception e)
        {
        	return false;
        }
        
    }
 
    /**
     * 
     * @return
     */
    public List<Person> getAll() {
    	TypedQuery<Person> query = em.createQuery(
            "SELECT p FROM Person p ORDER BY p.id", Person.class);
    	return query.getResultList();
    }
    /**
     * 
     * @param id
     * @return
     */
	public Person get( Integer id ) {
		Person p = em.find(Person.class, id);
		
		return p;
	}
	
	/**
	 * 
	 * @param person
	 * @return
	 */
	public Boolean edit(Person person) {
	
		try{
			Person existingPerson = this.get(person.getId());
			existingPerson.setFirstName(person.getFirstName());
			existingPerson.setLastName(person.getLastName());
			existingPerson.setMoney(person.getMoney());
			//Now update the existing person
			em.merge(existingPerson);
			return true;
		}
		catch(Exception e){
		return false;
	}
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Boolean delete(Integer id) 
	{
		try
		{
			Person p = this.get(id);
			if(null !=p)
			{
				em.remove(p);
			}
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	

}
