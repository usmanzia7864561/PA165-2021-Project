package muni.pa165.services.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Taken from the eshop project from github
 */
@Service
public class DozerConverterImpl implements DozerConverter {
    DozerBeanMapper mapper;

    public DozerConverterImpl(){
        mapper = new DozerBeanMapper();
    }

    @Override
    public <T, F> T convert(F from, Class<T> to) {
        return mapper.map(from, to);
    }

    @Override
    public <T, F> List<T> convert(List<F> from, Class<T> to) {
        return from.stream().map(entity->convert(entity,to)).collect(Collectors.toList());
    }
}
