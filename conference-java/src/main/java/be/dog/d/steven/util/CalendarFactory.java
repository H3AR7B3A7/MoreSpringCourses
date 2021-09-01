package be.dog.d.steven.util;

import org.springframework.beans.factory.FactoryBean;

import java.util.Calendar;

public class CalendarFactory implements FactoryBean<Calendar> {
    
    private final Calendar instance = Calendar.getInstance();

    @Override
    public Calendar getObject() {
        return instance;
    }

    @Override
    public Class<?> getObjectType() {
        return Calendar.class;
    }
    
    public void addDays(int num) {
        instance.add(Calendar.DAY_OF_YEAR, num);
    }
}