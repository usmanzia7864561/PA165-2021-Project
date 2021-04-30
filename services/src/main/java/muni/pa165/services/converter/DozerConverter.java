package muni.pa165.services.converter;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Taken from the eshop project from github
 */
@Service
public interface DozerConverter {
    public  <T> List<T> convert(Collection<?> objects, Class<T> mapToClass);
    public  <T> T convert(Object u, Class<T> mapToClass);
}
