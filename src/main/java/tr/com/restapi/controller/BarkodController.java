package tr.com.restapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.restapi.model.Barkod;
import tr.com.restapi.model.BarkodResponse;
import tr.com.restapi.model.Kullanici;
import tr.com.restapi.repository.BarkodRepository;
import tr.com.restapi.repository.KullaniciRepository;

@RestController
@RequestMapping("/barkod")
public class BarkodController {

	@Autowired
	private BarkodRepository barkodRepository;

	@Autowired
	private KullaniciRepository kullaniciRepository;
	
	// kullanıcıya göre tüm barkodları getiren metot

	@GetMapping("/barkodlar/{kullaniciId}")
	public List<BarkodResponse> getAllBarkods(@Valid @PathVariable(value = "kullaniciId") int kullaniciId) {
		List<Barkod> barkods = barkodRepository.getBarkodByKullaniciId(kullaniciId);
		List<BarkodResponse> barkodResponseList = new ArrayList<>();
		for (Barkod barkod : barkods) {
			BarkodResponse barkodResponse = new BarkodResponse();
			barkodResponse.setId(barkod.getId());
			barkodResponse.setKullaniciId(barkod.getKullaniciId());
			barkodResponse.setBarkod(barkod.getBarkod());
			barkodResponseList.add(barkodResponse);
		}
		return barkodResponseList;

	}

	// girilen barkod ile
	@PostMapping("/olustur")
	public ResponseEntity<BarkodResponse> createBarkod(@Valid @RequestBody Barkod barkod) {
		BarkodResponse barkodResponse = new BarkodResponse();

		if (barkod.getKullaniciId() <= 0) {
			return new ResponseEntity<BarkodResponse>(barkodResponse, HttpStatus.NOT_ACCEPTABLE);
		} else {
			Optional<Kullanici> kullanici = kullaniciRepository.findById(barkod.getKullaniciId());
			if (kullanici.equals(Optional.empty())) {
				return new ResponseEntity<BarkodResponse>(barkodResponse, HttpStatus.NOT_ACCEPTABLE);
			}
		}
		if (barkod.getBarkod() == null || barkod.getBarkod().equals("")) {
			return new ResponseEntity<BarkodResponse>(barkodResponse, HttpStatus.NOT_ACCEPTABLE);
		}
		barkodRepository.save(barkod);
		barkodResponse.setId(barkod.getId());
		barkodResponse.setKullaniciId(barkod.getKullaniciId());
		barkodResponse.setBarkod(barkod.getBarkod());
		return new ResponseEntity<BarkodResponse>(barkodResponse, HttpStatus.OK);
	}
}
