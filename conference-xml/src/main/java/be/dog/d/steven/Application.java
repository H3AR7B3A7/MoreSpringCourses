package be.dog.d.steven;

import be.dog.d.steven.service.SpeakerService;
import be.dog.d.steven.service.impl.SpeakerServiceImpl;

public class Application {

    public static void main(String[] args) {
        SpeakerService service = new SpeakerServiceImpl();
        System.out.println(service.findAll().get(0).getFirstName());
    }
}