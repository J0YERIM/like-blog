package likelion.likeblog.controller;

import likelion.likeblog.service.HeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HeartController {

    private HeartService heartService;

    @Autowired
    public HeartController(HeartService heartService) {
        this.heartService = heartService;
    }
}
