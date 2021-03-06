package com.example.demo.domain;

import com.example.demo.batch.domain.product.Product;
import com.example.demo.batch.domain.product.enums.ProductStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("h2")
public class ProductTest {

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void test() {
        System.out.println("sdsd");
    }

    @Test
    @Transactional
    public void create_product() {
        Product product = Product.createProduct("product1", ProductStatus.SALE);
        entityManager.persist(product);

        Product findProduct = entityManager.createQuery("select p from Product p where p.name =: name", Product.class)
                .setParameter("name", product.getName())
                .getSingleResult();

        assertThat(product).isEqualTo(findProduct);
    }
}
