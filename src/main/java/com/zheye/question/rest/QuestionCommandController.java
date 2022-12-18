package com.zheye.question.rest;

import com.zheye.question.domain.application.QuestionApplicationService;
import com.zheye.question.domain.application.command.CreateQuestionCommand;
import com.zheye.question.domain.application.command.QuestionCreatedResult;
import com.zheye.question.rest.request.CreateQuestionRequest;
import com.zheye.question.rest.response.QuestionCreateResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionCommandController {

    private final QuestionApplicationService questionApplicationService;

    public QuestionCommandController(QuestionApplicationService questionApplicationService) {
        this.questionApplicationService = questionApplicationService;
    }

    @PostMapping("/create-question")
    public QuestionCreateResponse createQuestion(@RequestBody CreateQuestionRequest request) {
        CreateQuestionCommand command = new CreateQuestionCommand(request.questionerId(), request.title(), request.detail());
        QuestionCreatedResult result = questionApplicationService.createQuestion(command);
        return new QuestionCreateResponse(result.questionId());
    }
}
