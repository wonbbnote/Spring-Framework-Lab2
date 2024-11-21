package com.example.gaeryong1.infrastructure;

import com.example.gaeryong1.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ListProductRepository {

    private List<Product> products = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);


    public Product add(Product product){
        product.setId(sequence.getAndAdd(1L));
        products.add(product);
        return product;
    }

    // 특정 아이디의 상품 조회
    public Product findById(Long id){
        return products.stream()
                .filter(product -> product.sameId(id))
                .findFirst()
                .orElseThrow();
    }

    // 전체 상품 조회
    public List<Product> findAll(){
        return products;
    }


}
