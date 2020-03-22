package tr.com.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.com.restapi.model.Barkod;

@Repository
public interface BarkodRepository extends JpaRepository<Barkod, Integer>{
	List<Barkod> getBarkodByKullaniciId(int kullaniciId);
}
