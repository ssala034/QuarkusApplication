import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class FilmResourceTest {

    @Test
    public void test(){
        given().when().get("film/5")
                .then()
                .statusCode(200)
                .body(containsString("AFRICAN EGG"));
    }

    @Test
    public void test2(){
        // great way to test with quarkus
        given().when().get("film/5")
                .then()
                .statusCode(200)
                .body(containsString("EGG"));
    }
}
