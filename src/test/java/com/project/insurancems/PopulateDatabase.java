package com.project.insurancems;

import com.project.insurancems.entity.*;
import com.project.insurancems.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.Arrays;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class PopulateDatabase {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CoverageRepository coverageRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private PolicyRepository policyRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClaimRepository claimRepository;

    @Test
    public void populate() {

        Role role1 = new Role();
        role1.setTitle(ERole.ROLE_AGENT);
        roleRepository.save(role1);

        Role role2 = new Role();
        role2.setTitle(ERole.ROLE_CLIENT);
        roleRepository.save(role2);

        Category category1 = new Category();
        category1.setTitle("Vehicle Insurance");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setTitle("Property Insurance");
        categoryRepository.save(category2);

        Category category3 = new Category();
        category3.setTitle("Life Insurance");
        categoryRepository.save(category3);

        Policy policy1 = new Policy();
        policy1.setTitle("RCA");
        policy1.setCategory(category1);
        policyRepository.save(policy1);

        Policy policy2 = new Policy();
        policy2.setTitle("CASCO");
        policy2.setCategory(category1);
        policyRepository.save(policy2);

        Policy policy3 = new Policy();
        policy3.setTitle("PAD");
        policy3.setCategory(category2);
        policyRepository.save(policy3);

        Coverage coverage1 = new Coverage();
        coverage1.setCoveredRisk("Personal injury");
        coverage1.setPolicies(Arrays.asList(policy1));
        coverageRepository.save(coverage1);

        Coverage coverage2 = new Coverage();
        coverage2.setCoveredRisk("Death");
        coverage2.setPolicies(Arrays.asList(policy1));
        coverageRepository.save(coverage2);

        Coverage coverage3 = new Coverage();
        coverage3.setCoveredRisk("Property damage");
        coverage3.setPolicies(Arrays.asList(policy1));
        coverageRepository.save(coverage3);

        Coverage coverage4 = new Coverage();
        coverage4.setCoveredRisk("Court charges");
        coverage4.setPolicies(Arrays.asList(policy1));
        coverageRepository.save(coverage4);

        Coverage coverage5 = new Coverage();
        coverage5.setCoveredRisk("Accidental damage");
        coverage5.setPolicies(Arrays.asList(policy2));
        coverageRepository.save(coverage5);

        Coverage coverage6 = new Coverage();
        coverage6.setCoveredRisk("Theft");
        coverage6.setPolicies(Arrays.asList(policy2));
        coverageRepository.save(coverage6);

        Coverage coverage7 = new Coverage();
        coverage7.setCoveredRisk("Natural phenomena");
        coverage7.setPolicies(Arrays.asList(policy2));
        coverageRepository.save(coverage7);

        Coverage coverage8 = new Coverage();
        coverage8.setCoveredRisk("Acts of vandalism");
        coverage8.setPolicies(Arrays.asList(policy2));
        coverageRepository.save(coverage8);

        Coverage coverage9 = new Coverage();
        coverage9.setCoveredRisk("Loss of documents/keys");
        coverage9.setPolicies(Arrays.asList(policy2));
        coverageRepository.save(coverage9);

        Coverage coverage10 = new Coverage();
        coverage10.setCoveredRisk("Engine failures");
        coverage10.setPolicies(Arrays.asList(policy2));
        coverageRepository.save(coverage10);

        Coverage coverage11 = new Coverage();
        coverage11.setCoveredRisk("Earthquake");
        coverage11.setPolicies(Arrays.asList(policy3));
        coverageRepository.save(coverage11);

        Coverage coverage12 = new Coverage();
        coverage12.setCoveredRisk("Land slides");
        coverage12.setPolicies(Arrays.asList(policy3));
        coverageRepository.save(coverage12);

        Coverage coverage13 = new Coverage();
        coverage13.setCoveredRisk("Flood");
        coverage13.setPolicies(Arrays.asList(policy3));
        coverageRepository.save(coverage13);

        User user1 = new User();
        user1.setLastName("Kai");
        user1.setFirstName("Aurelia");
        user1.setEmail("kai.aurelia@email.com");
        user1.setPassword("$2a$10$V9pq9dmlhBYwx/EgEI7Ba.mU5VxPjMLc53e8QbmK4ACu4g0rL8Fhm");
        user1.setRole(role1);
        userRepository.save(user1);

        User user2 = new User();
        user2.setLastName("Caldwell");
        user2.setFirstName("Emily");
        user2.setEmail("emily.caldwell@email.com");
        user2.setPassword("$2a$10$3FRyPKE8necswwYmfGq.SuqXjtUK/fAKE.wsaVGsUbhbvNsXjoi/O");
        user2.setRole(role2);
        userRepository.save(user2);

        User user3 = new User();
        user3.setLastName("Dalton");
        user3.setFirstName("Thomas");
        user3.setEmail("dalton_thomas@email.com");
        user3.setPassword("$2a$10$3FRyPKE8necswwYmfGq.SuqXjtUK/fAKE.wsaVGsUbhbvNsXjoi/O");
        user3.setRole(role2);
        userRepository.save(user3);

        Client client1 = new Client();
        client1.setCNP("6426493462677");
        client1.setDob(LocalDate.parse("2000-09-12"));
        client1.setGender('F');
        client1.setUser(user2);
        client1.setPhoneNumber("0777777777");
        client1.setIncome(3599.45);
        clientRepository.save(client1);

        Client client2 = new Client();
        client2.setCNP("4903817798615");
        client2.setDob(LocalDate.parse("1985-04-01"));
        client2.setGender('M');
        client2.setUser(user3);
        client2.setPhoneNumber("0777777777");
        client2.setIncome(2659.6);
        clientRepository.save(client2);

        Contract contract1 = new Contract();
        contract1.setClient(client1);
        contract1.setPolicy(policy1);
        contract1.setPrice(915.99);
        contract1.setMaxClaimAmount(10000);
        contract1.setEffectiveDate(LocalDate.parse("2022-05-10"));
        contract1.setExpirationDate(LocalDate.parse("2023-05-10"));
        contract1.setStatus(ContractStatus.ACTIVE);
        contractRepository.save(contract1);

        Contract contract2 = new Contract();
        contract2.setClient(client1);
        contract2.setPolicy(policy2);
        contract2.setPrice(1512.65);
        contract2.setMaxClaimAmount(20000);
        contract2.setEffectiveDate(LocalDate.parse("2023-01-21"));
        contract2.setExpirationDate(LocalDate.parse("2024-01-20"));
        contract2.setStatus(ContractStatus.ACTIVE);
        contractRepository.save(contract2);

        Contract contract3 = new Contract();
        contract3.setClient(client2);
        contract3.setPolicy(policy1);
        contract3.setPrice(615.88);
        contract3.setMaxClaimAmount(9999.99);
        contract3.setEffectiveDate(LocalDate.parse("2022-09-01"));
        contract3.setExpirationDate(LocalDate.parse("2023-09-01"));
        contract3.setStatus(ContractStatus.ACTIVE);
        contractRepository.save(contract3);

        Contract contract4 = new Contract();
        contract4.setClient(client2);
        contract4.setPolicy(policy3);
        contract4.setPrice(80.99);
        contract4.setMaxClaimAmount(5000);
        contract4.setEffectiveDate(LocalDate.parse("2022-06-28"));
        contract4.setExpirationDate(LocalDate.parse("2023-06-28"));
        contract4.setStatus(ContractStatus.ACTIVE);
        contractRepository.save(contract4);

        Claim claim1 = new Claim();
        claim1.setAmount(2500.65);
        claim1.setStatus(ClaimStatus.PENDING);
        claim1.setClient(client1);
        claim1.setContract(contract1);
        claim1.setCoverage(coverage2);
        claim1.setSubmissionDate(LocalDate.now());
        claimRepository.save(claim1);
    }
}
