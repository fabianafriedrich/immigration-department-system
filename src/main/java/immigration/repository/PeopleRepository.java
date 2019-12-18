package immigration.repository;

import immigration.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*JpaRepository is a class with all the methods to access the DB
* methods from JpaRepository
* List<T> findAll();
* List<T> findAll(Sort var1);
* List<T> findAllById(Iterable<ID> var1);
* <S extends T> List<S> saveAll(Iterable<S> var1);
* void flush();
* <S extends T> S saveAndFlush(S var1);
* void deleteInBatch(Iterable<T> var1);
* void deleteAllInBatch();
* T getOne(ID var1);
* <S extends T> List<S> findAll(Example<S> var1);
* <S extends T> List<S> findAll(Example<S> var1, Sort var2);*/
@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
}
