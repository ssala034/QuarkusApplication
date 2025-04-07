package org.ssala034.app.model.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.projection.Projection;
import com.speedment.jpastreamer.streamconfiguration.StreamConfiguration;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.ssala034.app.model.Film;

import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class FilmRepository {

    @Inject
    JPAStreamer streamer;

    private static final int PAGE_SIZE = 20;

    public Optional<Film> getFilm(short filmId) {
        return streamer.stream(Film.class)
                .filter(Film$.filmId.equal(filmId))
                .findFirst();
    }

    public Stream<Film> paged(long page, short minLength) {
        return streamer.stream(Projection.select(Film$.filmId, Film$.title, Film$.length))
                .filter(Film$.length.greaterThan(minLength))
                .sorted(Film$.length)
                .skip(page*PAGE_SIZE)
                .limit(PAGE_SIZE);
    }

    public Stream<Film> getFilms(short minLength) {
        return streamer.stream(Film.class)
                .filter(Film$.length.greaterThan(minLength))
                .sorted(Film$.length);
    }

    public Stream<Film> actors(String startsWith, short minLength) {
        final StreamConfiguration<Film> sc = StreamConfiguration.of(Film.class).joining(Film$.actors);
        return streamer.stream(sc).filter(Film$.title.startsWith(startsWith).and(Film$.length.greaterThan(minLength)))
                .sorted(Film$.length.reversed());
    }
    @Transactional
    public void updateRentalRate(short minLength, Float rentalRate) {
        streamer.stream(Film.class).filter(Film$.length.greaterThan(minLength))
                .forEach(f ->{
                        f.setRentalRate(rentalRate);
                });
    };

}
