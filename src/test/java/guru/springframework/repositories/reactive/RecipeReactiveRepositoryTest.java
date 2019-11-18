package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryTest {

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @Before
    public void setUp() throws Exception{
        recipeReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveRecipe() throws Exception{
         Recipe recipe = new Recipe();
         recipe.setDescription("EACH");
         recipeReactiveRepository.save(recipe).block();
         Long recipeCount = recipeReactiveRepository.count().block();
         assertEquals(recipeCount, Long.valueOf(1L));

    }

    @Test
    public void testFindByDescription() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setDescription("EACH");
        recipeReactiveRepository.save(recipe).block();
        Recipe returnedRecipe = recipeReactiveRepository.findByDescription("EACH").block();
        assertEquals(returnedRecipe.getDescription(), "EACH");

    }
}
