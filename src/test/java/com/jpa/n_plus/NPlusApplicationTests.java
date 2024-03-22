package com.jpa.n_plus;

import com.jpa.n_plus.api.entity.Owner;
import com.jpa.n_plus.api.entity.Pet;
import com.jpa.n_plus.api.repository.OwnerRepository;
import com.jpa.n_plus.api.repository.PetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class NPlusApplicationTests {

	@Autowired
	private OwnerRepository ownerRepository;
	@Autowired
	private PetRepository petRepository;

	@Test
	@DisplayName("N+1 발생 테스트")
	void test() {
		List<Pet> pets = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Pet pet = Pet.builder().name("pet" + i).build();
			pets.add(pet);
		}
		petRepository.saveAll(pets);

		List<Owner> owners = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Owner owner = Owner.builder().name("owner" + i).build();
			owner.setPets(pets);
			owners.add(owner);
		}
		ownerRepository.saveAll(owners);

		System.out.println("------------------------------ 1");
		List<Owner> ownerList = ownerRepository.findAll();
	}

	@Test
	@DisplayName("Fetch Join 을 이용한 테스트")
	void jpqlTest() {
		List<Pet> pets = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Pet pet = Pet.builder().name("pet" + i).build();
			pets.add(pet);
		}
		petRepository.saveAll(pets);

		List<Owner> owners = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Owner owner = Owner.builder().name("owner" + i).build();
			owner.setPets(pets);
			owners.add(owner);
		}
		ownerRepository.saveAll(owners);

		System.out.println("------------------------------ 2");
		List<Owner> ownerList = ownerRepository.findAllJoinFetch();
	}

	@Test
	@DisplayName("EntityGraph 을 이용한 테스트")
	void graphTest() {
		List<Pet> pets = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Pet pet = Pet.builder().name("pet" + i).build();
			pets.add(pet);
		}
		petRepository.saveAll(pets);

		List<Owner> owners = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Owner owner = Owner.builder().name("owner" + i).build();
			owner.setPets(pets);
			owners.add(owner);
		}
		ownerRepository.saveAll(owners);

		System.out.println("------------------------------ 3");
		List<Owner> ownerList = ownerRepository.findAllEntityGraph();
	}
}
