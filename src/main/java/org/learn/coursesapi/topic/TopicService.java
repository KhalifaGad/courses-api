package org.learn.coursesapi.topic;

import org.learn.coursesapi.topic.dtos.CreateTopicDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    protected List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        this.topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    protected Topic createTopic(CreateTopicDTO input) {
        return this.topicRepository.save(new Topic(input.getName(), input.getDescription()));
    }
}
