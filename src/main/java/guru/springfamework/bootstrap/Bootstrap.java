package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner{

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }


    private void loadCategories(){
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);


        System.out.println("Data loaded = " + categoryRepository.count());
    }

    private void loadCustomers(){
        Customer joe = new Customer();
        joe.setId(1L);
        joe.setFirstName("Joe");
        joe.setLastName("Newman");

        Customer michael = new Customer();
        michael.setId(2L);
        michael.setFirstName("Michael");
        michael.setLastName("Lachappelle");

        Customer david = new Customer();
        david.setId(3L);
        david.setFirstName("David");
        david.setLastName("Winter");

        Customer anne = new Customer();
        anne.setId(4L);
        anne.setFirstName("Anne");
        anne.setLastName("Hine");

        Customer alice = new Customer();
        alice.setId(5L);
        alice.setFirstName("Alice");
        alice.setLastName("Eastman");

        customerRepository.save(joe);
        customerRepository.save(michael);
        customerRepository.save(david);
        customerRepository.save(anne);
        customerRepository.save(alice);

        System.out.println("Data customer loaded = " + customerRepository.count());
    }

    private void loadVendors() {
        Vendor fruitsLtd = new Vendor();
        fruitsLtd.setId(672L);
        fruitsLtd.setName("Western Tasty Fruits Ltd.");

        Vendor fruitsComp = new Vendor();
        fruitsComp.setId(32L);
        fruitsComp.setName("Exotic Fruits Company");

        Vendor fruitsHome = new Vendor();
        fruitsHome.setId(501L);
        fruitsHome.setName("Home Fruits");

        Vendor fruitsFresh = new Vendor();
        fruitsFresh.setId(810L);
        fruitsFresh.setName("Fun Fresh Fruits Ltd.");

        Vendor nuts = new Vendor();
        nuts.setId(67L);
        nuts.setName("Nuts for Nuts Company");

        vendorRepository.save(fruitsLtd);
        vendorRepository.save(fruitsComp);
        vendorRepository.save(fruitsHome);
        vendorRepository.save(fruitsFresh);
        vendorRepository.save(nuts);

        System.out.println("Data vendors loaded = " + vendorRepository.count());
    }
}
