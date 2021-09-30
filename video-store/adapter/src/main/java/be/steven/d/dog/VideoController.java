package be.steven.d.dog;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Import(VideoService.class)
@AllArgsConstructor
public class VideoController {
    
    private final VideoService videoService;
    
    @GetMapping("test")
    public String test(){
        return "test";
    }

    @GetMapping("/videos/{id}")
    public Video getVideoById(@PathVariable("id") int id){
        return videoService.getVideoById(id);
    }
}