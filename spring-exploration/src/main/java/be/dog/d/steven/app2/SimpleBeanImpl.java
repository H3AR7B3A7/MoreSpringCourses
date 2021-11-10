package be.dog.d.steven.app2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleBeanImpl implements SimpleBean{
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleBeanImpl.class);

    public SimpleBeanImpl() {
        LOGGER.info("SimpleBeanImpl instantiated");
    }

    @Override
    public String toString() {
        return "SimpleBeanImpl{ code: "+ hashCode() +"}";
    }
}