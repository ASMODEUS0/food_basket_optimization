package com.example.food_basket_optimization.repository;

import com.example.food_basket_optimization.controller.request.ProductRequest;
import com.example.food_basket_optimization.dto.ProductDto;
import com.example.food_basket_optimization.entity.Product;
import com.example.food_basket_optimization.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaPredicate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepository {

    public ProductRepository() {

    }


    public List<Product> getProduct(String byName) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> cr = cb.createQuery(Product.class);
            Root<Product> root = cr.from(Product.class);

            String pattern = "%" + byName + "%";
            cr.select(root).where(cb.like(cb.lower(root.get("title")), pattern.toLowerCase()));

            return session.createQuery(cr).getResultList();
        }
    }

    public List<ProductDto> byRequest(ProductRequest request) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> cr = cb.createQuery(Product.class);
            Root<Product> root = cr.from(Product.class);
            Predicate shopPredicate = root.get("shopType").in(request.shops.toArray());
            String pattern = "%" + request.productName + "%";
            Predicate namePredicate = cb.like(cb.lower(root.get("title")), pattern.toLowerCase());
            cr.select(root).where(shopPredicate, namePredicate);
            List<Product> products = session.createQuery(cr).getResultList();

            return products.stream().map(product-> new ProductDto(product.getStock(),
                    product.getBrand(),
                    product.getTitle(),
                    product.getSubTitle(),
                    product.getImageUrl(),
                    product.getPrice(),
                    product.getShopType())).toList();
        }
    }

}
