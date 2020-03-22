package tr.com.restapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.com.restapi.model.Kullanici;

@Repository
public interface KullaniciRepository extends JpaRepository<Kullanici, Integer> {
	Kullanici getKullaniciByKullaniciAdiAndParola(String kullaniciAdi, String parola);
}