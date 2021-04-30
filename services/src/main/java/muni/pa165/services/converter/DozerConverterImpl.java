package muni.pa165.services.converter;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Taken from the eshop project from github
 */
@Named("DozerConverter")
public class DozerConverterImpl implements DozerConverter {
    private final Mapper dozer;

	public DozerConverterImpl(){
        dozer = DozerBeanMapperBuilder.buildDefault();
    }

    public  <T> List<T> convert(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    public  <T> T convert(Object u, Class<T> mapToClass){ return dozer.map(u,mapToClass); }
}
