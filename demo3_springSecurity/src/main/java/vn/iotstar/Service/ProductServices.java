package vn.iotstar.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.iotstar.entity.Product;
@Service
public interface ProductServices {

	void delete(Long id);

	Product get(Long id);

	Product save(Product product);

	List<Product> listAll();

}
