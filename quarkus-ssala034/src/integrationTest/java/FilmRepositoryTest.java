import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.ssala034.app.model.Film;
import org.ssala034.app.model.repository.FilmRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.wildfly.common.Assert.assertTrue;

QuarkusTest

class FilmRepositoryTest {
    @Inject
    FilmRepository filmRepository;

    @Test
    public void test() {
        Optional<Film> film = filmRepository.getFilm((short) 5 );
        assertTrue(film.isPresent());
        assertEquals("AFRICAN EGG", film.get().getTitle());
    }
}
