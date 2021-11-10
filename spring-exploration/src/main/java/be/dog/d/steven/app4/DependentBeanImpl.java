package be.dog.d.steven.app4;

import be.dog.d.steven.app2.SimpleBean;
import be.dog.d.steven.app2.SimpleBeanImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DependentBeanImpl implements DependentBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleBeanImpl.class);

    private SimpleBean simpleBean;

    public DependentBeanImpl(SimpleBean simpleBean) {
        LOGGER.info("DependentBeanImpl instantiated");
        this.simpleBean = simpleBean;
    }
}