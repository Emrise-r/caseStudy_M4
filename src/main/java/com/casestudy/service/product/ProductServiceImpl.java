//package com.casestudy.service.product;
//
//import com.casestudy.model.Product;
//import com.casestudy.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//public class ProductServiceImpl implements ProductService {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Override
//    public Page<Product> findAll(Pageable pageable) {
//        return productRepository.findAll(pageable);
//    }
//
//    @Override
//    public Product findByProductId(Long product_id) {
//        return productRepository.findOne(product_id);
//    }
//
//    @Override
//    public void save(Product product) {
//        productRepository.save(product);
//    }
//
//    @Override
//    public void remove(Long product_id) {
//        productRepository.delete(product_id);
//    }
//}
