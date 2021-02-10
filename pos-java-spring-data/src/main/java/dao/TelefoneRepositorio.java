package dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Telefone;

@Repository
public interface TelefoneRepositorio extends CrudRepository<Telefone, Long> {
}
