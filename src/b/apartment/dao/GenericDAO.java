package b.apartment.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @author ducda referenced from CaveatEmptor project tm JBoss Hibernate version
 */
public interface GenericDAO<E, Id extends Serializable> {

	public E find(Id id);

	public E find(Id id, boolean lock) throws Exception;

	public List<E> findAll() throws Exception;

	public List<E> findByExample(E exampleInstance);

	public List<E> findByExample(E exampleInstance, String[] excludeProperty);

	public int count(E exampleInstance, String[] excludeProperty, boolean isLike);

	public int count();

	public int count(Criterion... criterion);

	public E makePersistent(E entity) throws Exception;

	public void makeTransient(E entity) throws Exception;

	public List<E> findByCriteria(Criterion... criterion);

	public Timestamp getSystemTimestamp();

	public Page<E> paginate(Pageable pageable);

}
