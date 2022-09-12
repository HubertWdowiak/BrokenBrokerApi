package com.example.brokebroker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BrokeBrokerApplication {

    private static final Logger log = LoggerFactory.getLogger(BrokeBrokerApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(BrokeBrokerApplication.class, args);

    }

//    @Bean
//    public CommandLineRunner demo(UserRepository repository, WalletRepository walletRepository) {
//        return (args) -> {
//            // save a few customers
//            repository.save(new User("Jack", "Bauer"));
//            repository.save(new User("Chloe", "O'Brian"));
//            repository.save(new User("Kim", "Bauer"));
//            repository.save(new User("David", "Palmer"));
//            repository.save(new User("Michelle", "Dessler"));
//
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            for (User customer : repository.findAll()) {
//                log.info(customer.toString());
//            }
//            log.info("");
//            log.info(String.valueOf(repository.findById(1003).getWallets()));
//
//            // fetch an individual customer by ID
//            User customer = repository.findById(1003L);
//            log.info("Customer found with findById(1L):");
//            log.info("--------------------------------");
//            log.info(customer.toString());
//            log.info("");
//
//            // fetch customers by last name
//            log.info("Customer found with findByLastName('Bauer'):");
//            log.info("--------------------------------------------");
//            repository.findByLogin("Bauer").forEach(bauer -> {
//                log.info(bauer.toString());
//            });
//            // for (Customer bauer : repository.findByLastName("Bauer")) {
//            //  log.info(bauer.toString());
//            // }
//            log.info("");
//        };
//    }

}
