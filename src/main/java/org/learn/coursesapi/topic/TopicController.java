package org.learn.coursesapi.topic;

import jakarta.validation.Valid;
import org.learn.coursesapi.topic.dtos.CreateTopicDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping("/topics")
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @PostMapping("/topics")
    public Topic createTopic(@Valid @RequestBody CreateTopicDTO dto) {
        return topicService.createTopic(dto);
    }
}
