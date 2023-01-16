package com.ifood.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static void setUp() {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        mapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);

    }

    public static <Origin, Destination> Destination parseObject(Origin origin, Class<Destination> destinationClass) {
        setUp();
        return mapper.map(origin, destinationClass);
    }

    public static <Origin, Destination> List<Destination> parseObjects(List<Origin> origins, Class<Destination> destinationClass) {
        setUp();
        ArrayList<Destination> destinationList = new ArrayList<>();
        for (Origin o : origins) {
            mapper.map(o, destinationClass);
        }

        return destinationList;

    }

    public static <Origin, Destination> void copyProperties(Origin origin, Destination destination) {
        setUp();
        mapper.map(origin, destination);
    }

}
