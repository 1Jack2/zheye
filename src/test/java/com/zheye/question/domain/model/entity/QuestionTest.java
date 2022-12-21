package com.zheye.question.domain.model.entity;

import com.zheye.question.core.JpaRepositoryTest;
import com.zheye.question.domain.model.vo.QuestionUpdatedRecord;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.zheye.question.domain.model.vo.QuestionUpdatedRecord.UpdatedType.CREATED;
import static com.zheye.question.domain.model.vo.QuestionUpdatedRecord.UpdatedType.DETAIL_EDITED;
import static com.zheye.question.domain.model.vo.QuestionUpdatedRecord.UpdatedType.TITLE_EDITED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@JpaRepositoryTest
class QuestionTest {

    @Test
    void should_generate_created_record_after_question_created() {
        Question question = new Question("UID_00001", "A test title", "A test detail");
        List<QuestionUpdatedRecord> updatedRecords = question.getUpdatedRecords();
        assertThat(updatedRecords, hasSize(1));
        QuestionUpdatedRecord updatedRecord = updatedRecords.get(0);
        assertThat(updatedRecord.getUpdateType(), is(CREATED));
        assertThat(updatedRecord.getCreatedTitle(), is(question.getTitle()));
        assertThat(updatedRecord.getCreatedDetail(), is(question.getDetail()));
    }

    @Test
    void should_generate_edited_record_after_question_edited() {
        String originalTitle = "A test title";
        String originalDetail = "A test detail";
        Question question = new Question("UID_00001", originalTitle, originalDetail);
        String editedTitle = "A new test title";
        String reason = "test reason";
        question.editTitle("UID_00002", reason, editedTitle);
        String editedDetail = "A new test detail";
        question.editDetail("UID_00003", reason, editedDetail);
        List<QuestionUpdatedRecord> updatedRecords = question.getUpdatedRecords();
        assertThat(updatedRecords, hasSize(3));
        QuestionUpdatedRecord updatedRecord = updatedRecords.get(0);
        assertThat(updatedRecord.getUpdateType(), is(CREATED));
        assertThat(updatedRecord.getCreatedTitle(), is(originalTitle));
        assertThat(updatedRecord.getCreatedDetail(), is(originalDetail));

        updatedRecord = updatedRecords.get(1);
        assertThat(updatedRecord.getUpdateType(), is(TITLE_EDITED));
        assertThat(updatedRecord.getUneditedTitle(), is(originalTitle));
        assertThat(updatedRecord.getEditedTitle(), is(editedTitle));

        updatedRecord = updatedRecords.get(2);
        assertThat(updatedRecord.getUpdateType(), is(DETAIL_EDITED));
        assertThat(updatedRecord.getUneditedDetail(), is(originalDetail));
        assertThat(updatedRecord.getEditedDetail(), is(editedDetail));

        assertThat(question.getTitle(), is(editedTitle));
        assertThat(question.getDetail(), is(editedDetail));
    }

}