package be.steven.d.dog;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.AbstractAggregateRoot;

@Data
@EqualsAndHashCode(callSuper = false)
public class Video extends AbstractAggregateRoot<Video> {
    private Integer id;
    private String name;
    private Integer amount;
    
    public void test() {
        registerEvent(new DrivenEvent(this, "test"));
    }
}