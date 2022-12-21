package com.zheye.question.domain.application;

import com.zheye.question.domain.application.command.CreateQuestionCommand;
import com.zheye.question.domain.application.command.QuestionCreatedResult;
import com.zheye.question.domain.model.entity.Question;
import com.zheye.question.domain.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class QuestionApplicationService {

    private final QuestionRepository questionRepository;

    public QuestionApplicationService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public QuestionCreatedResult createQuestion(CreateQuestionCommand command) {
        Question question = new Question(command.questionerId(), command.title(), command.detail());
        questionRepository.save(question);
        return new QuestionCreatedResult(question.getId());
    }
}
