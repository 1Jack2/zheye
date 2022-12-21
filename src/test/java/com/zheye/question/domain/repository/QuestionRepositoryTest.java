package com.zheye.question.domain.repository;

import com.zheye.question.core.JpaRepositoryTest;
import com.zheye.question.domain.model.entity.Question;
import junit.framework.AssertionFailedError;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@JpaRepositoryTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void repository_should_successfully_save_question() {
        Question question = new Question("UID_00001", "A test title", "A test detail");
        question.editTitle("UID_00002", "for test", "A new test title");
        Question savedQuestion = questionRepository.save(question);
        assertThat(savedQuestion.getId(), is(notNullValue()));
        assertSameQuestion(savedQuestion, question);
    }

    @Test
    void repository_should_successfully_find_by_id() {
        Question question = new Question("UID_00001", "A test title", "A test detail");
        question.editTitle("UID_00002", "for test", "A new test title");
        Question savedQuestion = questionRepository.saveAndFlush(question);
        entityManager.detach(savedQuestion);
        Question foundQuestion = questionRepository.findById(savedQuestion.getId()).orElseThrow(AssertionFailedError::new);
        assertSameQuestion(foundQuestion, question);
    }

    private static void assertSameQuestion(Question actualQuestion, Question expectQuestion) {
        assertThat(actualQuestion.getQuestionerId(), equalTo(expectQuestion.getQuestionerId()));
        assertThat(actualQuestion.getTitle(), equalTo(expectQuestion.getTitle()));
        assertThat(actualQuestion.getDetail(), equalTo(expectQuestion.getDetail()));
        assertThat(actualQuestion.getUpdatedRecords(), hasSize(expectQuestion.getUpdatedRecords().size()));
        for (int i = 0; i < actualQuestion.getUpdatedRecords().size(); i++) {
            assertThat(actualQuestion.getUpdatedRecords().get(i), equalTo(expectQuestion.getUpdatedRecords().get(i)));
        }
    }
}