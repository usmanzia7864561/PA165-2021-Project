package muni.pa165.services.converter;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Taken from the eshop project from github
 */
@Service
public interface DozerConverter {
    <T, F> T convert(F from, Class<T> to);
    <T, F> List<T> convert(List<F> from, Class<T> to);
}
