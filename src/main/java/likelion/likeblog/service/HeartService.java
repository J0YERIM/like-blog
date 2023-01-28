package likelion.likeblog.service;

import likelion.likeblog.repository.HeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeartService {

    private HeartRepository heartRepository;

    @Autowired
    public HeartService(HeartRepository heartRepository) {
        this.heartRepository = heartRepository;
    }
}
