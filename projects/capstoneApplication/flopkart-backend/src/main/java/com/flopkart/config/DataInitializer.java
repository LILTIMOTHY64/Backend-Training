package com.flopkart.config;

import com.flopkart.model.Product;
import com.flopkart.model.User;
import com.flopkart.repository.ProductRepository;
import com.flopkart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

/**
 * Seeds the database with initial users and products on first startup.
 * Skips seeding if data already exists, so it is safe to run repeatedly.
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner seedData() {
        return args -> {
            seedUsers();
            seedProducts();
        };
    }

    // ─── Users ───────────────────────────────────────────────────────────────

    private void seedUsers() {
        if (userRepository.count() > 0) {
            log.info("Users already seeded — skipping.");
            return;
        }

        List<User> users = List.of(
            User.builder()
                .username("emilys")
                .password(passwordEncoder.encode("emilyspass"))
                .firstName("Emily")
                .lastName("Johnson")
                .email("emily.johnson@x.dummyjson.com")
                .role(User.Role.BUYER)
                .build(),

            User.builder()
                .username("michaelw")
                .password(passwordEncoder.encode("michaelwpass"))
                .firstName("Michael")
                .lastName("Williams")
                .email("michael.williams@x.dummyjson.com")
                .role(User.Role.OWNER)
                .build()
        );

        userRepository.saveAll(users);
        log.info("Seeded {} users.", users.size());
    }

    // ─── Products ─────────────────────────────────────────────────────────────

    private void seedProducts() {
        if (productRepository.count() > 0) {
            log.info("Products already seeded — skipping.");
            return;
        }

        List<Product> products = List.of(
            Product.builder()
                .title("Fjallraven - Foldsack No. 1 Backpack")
                .price(new BigDecimal("109.95"))
                .description("Your perfect pack for everyday use and walks in the forest.")
                .category("men's clothing")
                .image("https://fakestoreapi.com/img/81fAn1X3LvL._AC_UY879_.jpg")
                .ratingRate(new BigDecimal("3.9"))
                .ratingCount(120)
                .build(),

            Product.builder()
                .title("Mens Casual Premium Slim Fit T-Shirts")
                .price(new BigDecimal("22.30"))
                .description("Slim-fitting style, contrast raglan long sleeve, three-button henley placket.")
                .category("men's clothing")
                .image("https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg")
                .ratingRate(new BigDecimal("4.1"))
                .ratingCount(259)
                .build(),

            Product.builder()
                .title("Mens Cotton Jacket")
                .price(new BigDecimal("55.99"))
                .description("Great outerwear jackets for Spring/Autumn/Winter.")
                .category("men's clothing")
                .image("https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg")
                .ratingRate(new BigDecimal("4.7"))
                .ratingCount(500)
                .build(),

            Product.builder()
                .title("Mens Casual Slim Fit")
                .price(new BigDecimal("15.99"))
                .description("The color could be slightly different between on the screen and in practice.")
                .category("men's clothing")
                .image("https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg")
                .ratingRate(new BigDecimal("2.1"))
                .ratingCount(430)
                .build(),

            Product.builder()
                .title("John Hardy Women's Legends Naga Gold & Silver Dragon Bracelet")
                .price(new BigDecimal("695.00"))
                .description("From our Legends Collection, the Naga was inspired by the mythical water dragon.")
                .category("jewelery")
                .image("https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_FMwebp_QL65_.jpg")
                .ratingRate(new BigDecimal("4.6"))
                .ratingCount(400)
                .build(),

            Product.builder()
                .title("Solid Gold Petite Micropave")
                .price(new BigDecimal("168.00"))
                .description("Satisfaction Guaranteed. Return or exchange any order within 30 days.")
                .category("jewelery")
                .image("https://fakestoreapi.com/img/61sbMiUnoGL._AC_UL640_FMwebp_QL65_.jpg")
                .ratingRate(new BigDecimal("3.9"))
                .ratingCount(70)
                .build(),

            Product.builder()
                .title("White Gold Plated Princess")
                .price(new BigDecimal("9.99"))
                .description("Classic Created Wedding Engagement Solitaire Diamond Promise Ring.")
                .category("jewelery")
                .image("https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_FMwebp_QL65_.jpg")
                .ratingRate(new BigDecimal("3.0"))
                .ratingCount(400)
                .build(),

            Product.builder()
                .title("Pierced Owl Rose Gold Plated Stainless Steel Double")
                .price(new BigDecimal("10.99"))
                .description("Rose Gold Plated Double Flared Tunnel Plug Earrings.")
                .category("jewelery")
                .image("https://fakestoreapi.com/img/51UDEzMJVpL._AC_UL640_FMwebp_QL65_.jpg")
                .ratingRate(new BigDecimal("1.9"))
                .ratingCount(100)
                .build(),

            Product.builder()
                .title("WD 2TB Elements Portable External Hard Drive - USB 3.0")
                .price(new BigDecimal("64.00"))
                .description("USB 3.0 and USB 2.0 Compatibility. Fast data transfers.")
                .category("electronics")
                .image("https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg")
                .ratingRate(new BigDecimal("3.3"))
                .ratingCount(203)
                .build(),

            Product.builder()
                .title("SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s")
                .price(new BigDecimal("109.00"))
                .description("Easy install 2.5\" SATA II connector form factor.")
                .category("electronics")
                .image("https://fakestoreapi.com/img/61U7T1koQqL._AC_SX679_.jpg")
                .ratingRate(new BigDecimal("2.9"))
                .ratingCount(470)
                .build(),

            Product.builder()
                .title("Silicon Power 256GB SSD 3D NAND")
                .price(new BigDecimal("109.00"))
                .description("3D NAND flash are applied to deliver high transfer speeds.")
                .category("electronics")
                .image("https://fakestoreapi.com/img/71kWymZ+c+L._AC_SX679_.jpg")
                .ratingRate(new BigDecimal("4.8"))
                .ratingCount(319)
                .build(),

            Product.builder()
                .title("WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive")
                .price(new BigDecimal("114.00"))
                .description("Expand your PS4 gaming experience. Play anywhere, anytime.")
                .category("electronics")
                .image("https://fakestoreapi.com/img/61mtL65D4cL._AC_SX679_.jpg")
                .ratingRate(new BigDecimal("4.8"))
                .ratingCount(400)
                .build(),

            Product.builder()
                .title("Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin")
                .price(new BigDecimal("599.00"))
                .description("21. 5 inches Full HD (1920 x 1080) widescreen IPS display.")
                .category("electronics")
                .image("https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_.jpg")
                .ratingRate(new BigDecimal("2.9"))
                .ratingCount(250)
                .build(),

            Product.builder()
                .title("Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor")
                .price(new BigDecimal("999.99"))
                .description("49 INCH SUPER ULTRAWIDE 32:9 CURVED GAMING MONITOR.")
                .category("electronics")
                .image("https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg")
                .ratingRate(new BigDecimal("2.2"))
                .ratingCount(140)
                .build(),

            Product.builder()
                .title("BIYLACLESEN Women's 3-in-1 Snowboard Jacket")
                .price(new BigDecimal("56.99"))
                .description("Note: The Jackets is US standard size. Please refer to size chart.")
                .category("women's clothing")
                .image("https://fakestoreapi.com/img/51Y5NI-I5jL._AC_UX679_.jpg")
                .ratingRate(new BigDecimal("2.6"))
                .ratingCount(235)
                .build(),

            Product.builder()
                .title("Lock and Love Women's Removable Hooded Faux Leather Moto Biker Jacket")
                .price(new BigDecimal("29.95"))
                .description("100% POLYURETHANE(shell) 100% POLYESTER(lining).")
                .category("women's clothing")
                .image("https://fakestoreapi.com/img/81XH0e8fefL._AC_UY879_.jpg")
                .ratingRate(new BigDecimal("2.9"))
                .ratingCount(340)
                .build(),

            Product.builder()
                .title("Rain Jacket Women Windbreaker Striped Climbing Raincoats")
                .price(new BigDecimal("39.99"))
                .description("Lightweight perfect for trip or casual wear. Long sleeve with hooded.")
                .category("women's clothing")
                .image("https://fakestoreapi.com/img/71HblAHs1xL._AC_UY879_-2.jpg")
                .ratingRate(new BigDecimal("3.8"))
                .ratingCount(679)
                .build(),

            Product.builder()
                .title("MBJ Women's Solid Short Sleeve Boat Neck V")
                .price(new BigDecimal("9.85"))
                .description("95% RAYON 5% SPANDEX, Made in USA or Imported.")
                .category("women's clothing")
                .image("https://fakestoreapi.com/img/71z3kpMAYsL._AC_UY879_.jpg")
                .ratingRate(new BigDecimal("4.7"))
                .ratingCount(130)
                .build(),

            Product.builder()
                .title("Opna Women's Short Sleeve Moisture Tunic")
                .price(new BigDecimal("7.95"))
                .description("100% Polyester, Machine wash, 100% Polyester.")
                .category("women's clothing")
                .image("https://fakestoreapi.com/img/51eg55uWmdL._AC_UX679_.jpg")
                .ratingRate(new BigDecimal("4.5"))
                .ratingCount(146)
                .build(),

            Product.builder()
                .title("DANVOUY Womens T Shirt Casual Cotton Short")
                .price(new BigDecimal("12.99"))
                .description("95% COTTON, 5% SPANDEX. Size chart is for reference only.")
                .category("women's clothing")
                .image("https://fakestoreapi.com/img/61pHAEJ4NML._AC_UX679_.jpg")
                .ratingRate(new BigDecimal("3.6"))
                .ratingCount(145)
                .build()
        );

        productRepository.saveAll(products);
        log.info("Seeded {} products.", products.size());
    }
}
