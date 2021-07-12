package com.nbu.autoshop.config;

import com.nbu.autoshop.data.entity.*;
import com.nbu.autoshop.data.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DbInit implements CommandLineRunner {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    private BrandsRepository brandsRepository;

    @Autowired
    private AutoShopsRepository autoShopsRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder encoder;

    public DbInit() {
    }

    @Override
    public void run(String... args) throws Exception {
//        //create User Roles
//        this.rolesRepository.deleteAll();
//        UserRole admin = new UserRole();
//        admin.setAuthority("ADMIN");
//
//        UserRole worker = new UserRole();
//        worker.setAuthority("WORKER");
//
//        UserRole client = new UserRole();
//        client.setAuthority("CLIENT");
//
//        List<UserRole> userRoles = Arrays.asList(admin, worker, client);
//
//        // Save to db
//        this.rolesRepository.saveAll(userRoles);
//
//        //create Users
//        this.userRepository.deleteAll();
//        User user = new User();
//        user.setUsername("user");
//        user.setPassword(encoder.encode("user_123"));
//        user.setName("Ivan");
//        user.setAccountNonExpired(true);
//        user.setAccountNonLocked(true);
//        user.setEnabled(true);
//        user.setCredentialsNonExpired(true);
//        user.setAuthorities(rolesRepository.findAllByAuthority("ADMIN"));
//
//        User user3 = new Client();
//        user3.setUsername("client");
//        user3.setPassword(encoder.encode("client_123"));
//        user3.setName("Maria");
//        user3.setAccountNonExpired(true);
//        user3.setAccountNonLocked(true);
//        user3.setEnabled(true);
//        user3.setCredentialsNonExpired(true);
//        user3.setAuthorities(rolesRepository.findAllByAuthority("CLIENT"));
//
//        User user4 = new Client();
//        user4.setUsername("client1");
//        user4.setPassword(encoder.encode("client1_123"));
//        user4.setName("Vasil");
//        user4.setAccountNonExpired(true);
//        user4.setAccountNonLocked(true);
//        user4.setEnabled(true);
//        user4.setCredentialsNonExpired(true);
//        user4.setAuthorities(rolesRepository.findAllByAuthority("CLIENT"));
//
//        List<User> users = Arrays.asList(user, user3, user4);
//
//        // Save to db
//        this.userRepository.saveAll(users);
//
//        //create Brands
//        this.brandsRepository.deleteAll();
//        Brand brand = new Brand();
//        brand.setBrand("BMW");
//
//        Brand brand2 = new Brand();
//        brand2.setBrand("Toyota");
//
//        Brand brand3 = new Brand();
//        brand3.setBrand("Mercedes");
//
//        Brand brand4 = new Brand();
//        brand4.setBrand("All");
//
//        List<Brand> brands = Arrays.asList(brand, brand2, brand3, brand4);
//
//        // Save to db
//        this.brandsRepository.saveAll(brands);
//
//        //create Cars
//        this.carsRepository.deleteAll();
//        Car car = new Car();
//        car.setBrand(brand);
//        car.setModel("Regalia");
//        car.setPlate("123ASD22");
//        car.setYear(2012);
//        car.setOwner((Client) user3);
//
//        Car car2 = new Car();
//        car2.setBrand(brand3);
//        car2.setModel("Bentz");
//        car2.setPlate("111FRD23");
//        car2.setYear(2017);
//        car2.setOwner((Client) user3);
//
//        List<Car> cars = Arrays.asList(car, car2);
//
//        // Save to db
//        this.carsRepository.saveAll(cars);
//
//        //create AutoShops
//        this.autoShopsRepository.deleteAll();
//        AutoShop autoShop = new AutoShop();
//        autoShop.setName("Mechanics United");
//        autoShop.setAddress("Novigrad");
//        autoShop.setEmail("newmail@mail.ru");
//        autoShop.setOwnerName("Ivan");
//        autoShop.setPhoneNumber("086643122");
//        autoShop.setBrand(brandsRepository.findByBrand("BMW"));
//
//        AutoShop autoShop2 = new AutoShop();
//        autoShop2.setName("Repairs 2 Soon");
//        autoShop2.setAddress("Sofia");
//        autoShop2.setEmail("rep2soon@mail.bg");
//        autoShop2.setOwnerName("Boris");
//        autoShop2.setPhoneNumber("084443122");
//        autoShop2.setBrand(brandsRepository.findByBrand("All"));
//
//        List<AutoShop> autoShops = Arrays.asList(autoShop, autoShop2);
//        autoShop.setBrand(brandsRepository.findByBrand("All"));
//
//        // Save to db
//        this.autoShopsRepository.saveAll(autoShops);
    }
}
