package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp(){
       categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveCategory() throws Exception{
        Category category = new Category();
        category.setDescription("EACH");
        categoryReactiveRepository.save(category).block();
        Long countCategory = categoryReactiveRepository.count().block();
        assertEquals(countCategory, Long.valueOf(1L));

    }

    @Test
    public void testFindByDescription() throws Exception{
        Category category = new Category();
        category.setDescription("EACH");
        categoryReactiveRepository.save(category).block();
        Category returnedCategory = categoryReactiveRepository.findByDescription("EACH").block();
        assertEquals(returnedCategory.getDescription(), "EACH");

    }
}
