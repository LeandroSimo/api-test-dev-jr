package com.dev.apitestdevjr;

import com.dev.apitestdevjr.address.model.Address;
import com.dev.apitestdevjr.address.repositories.AddressRepository;
import com.dev.apitestdevjr.person.model.Person;
import com.dev.apitestdevjr.person.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ApiTestDevJrApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ApiTestDevJrApplication.class, args);
	}

	@Autowired
	private PersonRepository repository;

	@Autowired
	AddressRepository addressRepository;

	@Override
	public void run(String... args) throws Exception {
		LocalDate localDate = LocalDate.now();
		Person person = new Person("leandro", localDate);

		Address address = new Address("rua", 123, 123, "cidade", person);

		repository.saveAll(List.of(person));

		addressRepository.saveAll(List.of(address));
	}
}
