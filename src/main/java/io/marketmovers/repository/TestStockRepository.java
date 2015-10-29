package io.marketmovers.repository;

import io.marketmovers.models.TestStock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by mikechabot on 10/28/15.
 */
public interface TestStockRepository extends MongoRepository<TestStock, String> {

    List<TestStock> findByNumberOfStocksGreaterThan(int number);

}
