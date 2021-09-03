package be.dog.d.steven.trackzilla.service;

import be.dog.d.steven.trackzilla.entity.Release;
import be.dog.d.steven.trackzilla.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReleaseServiceImpl implements ReleaseService {
    @Autowired
    private ReleaseRepository releaseRepository;

    @Override
    public Iterable<Release> listReleases() {
        return releaseRepository.findAll();
    }

}
