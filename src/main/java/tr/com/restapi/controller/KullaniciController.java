package tr.com.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.restapi.model.Kullanici;
import tr.com.restapi.repository.KullaniciRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/kullanici")
public class KullaniciController {

	@Autowired
	private KullaniciRepository kullaniciRepository;

	@GetMapping("/giris/{kullaniciAdi}/{parola}")
	public ResponseEntity<Kullanici> getKullanici(@Valid @PathVariable(value = "kullaniciAdi") String kullaniciAdi, @PathVariable(value = "parola") String parola) {
		Kullanici kullanici = kullaniciRepository.getKullaniciByKullaniciAdiAndParola(kullaniciAdi, parola);
		if (kullanici != null) {
			return new ResponseEntity<Kullanici>(kullanici, HttpStatus.OK);
		} else {
			return new ResponseEntity<Kullanici>(kullanici, HttpStatus.NOT_FOUND);
		}
	}

}
