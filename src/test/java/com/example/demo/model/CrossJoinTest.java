package com.example.demo.model;

import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CrossJoinTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void crossJoinProducesCartesianProduct() {
        Category c1 = new Category("Electronics");
        Category c2 = new Category("Accessories");
        em.persist(c1);
        em.persist(c2);

        Product p1 = new Product("Phone", 100.0);
        Product p2 = new Product("Charger", 20.0);
        Product p3 = new Product("Mystery", 0.0);
        p1.setCategory(c1);
        p2.setCategory(c2);
        // p3 has no category (null)
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.flush();

        List<Object[]> rows = productRepository.crossJoin();
        // 3 products * 2 categories = 6 rows
        assertThat(rows).hasSize(3 * 2);

        Set<String> combos = rows.stream()
                .map(r -> {
                    Product p = (Product) r[0];
                    Category c = (Category) r[1];
                    return p.getName() + "|" + c.getName();
                }).collect(Collectors.toSet());

        assertThat(combos).containsExactlyInAnyOrder(
                "Phone|Electronics",
                "Phone|Accessories",
                "Charger|Electronics",
                "Charger|Accessories",
                "Mystery|Electronics",
                "Mystery|Accessories"
        );
    }
}
