package com.zheye.question.domain.model.vo;

import com.zheye.question.domain.util.OffsetDateTimes;
import org.springframework.data.annotation.PersistenceCreator;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.OffsetDateTime;
import java.util.Objects;

@Embeddable
public class QuestionUpdatedRecord {

    @Enumerated(EnumType.STRING)
    private UpdatedType updateType;
    private String updaterId;
    private OffsetDateTime updatedAt;
    private String reason;
    private String createdTitle;
    private String createdDetail;
    private String uneditedTitle;
    private String editedTitle;
    private String uneditedDetail;
    private String editedDetail;

    public static QuestionUpdatedRecord ofCreated(String updaterId,
                                                  String createdTitle, String createdDetail) {
        return new QuestionUpdatedRecord(UpdatedType.CREATED, updaterId, OffsetDateTimes.currentTime(),
                null, createdTitle, createdDetail,
                null, null, null, null);
    }

    public static QuestionUpdatedRecord ofTitleEdited(String updaterId, String reason,
                                                      String uneditedTitle, String editedTitle) {
        return new QuestionUpdatedRecord(UpdatedType.TITLE_EDITED, updaterId, OffsetDateTimes.currentTime(),
                reason, null, null,
                uneditedTitle, editedTitle, null, null);

    }

    public static QuestionUpdatedRecord ofDetailEdited(String updaterId, String reason,
                                                  String uneditedDetail, String editedDetail) {
        return new QuestionUpdatedRecord(UpdatedType.DETAIL_EDITED, updaterId, OffsetDateTimes.currentTime(),
                reason, null, null,
                null, null, uneditedDetail, editedDetail);
    }

    @PersistenceCreator
    protected QuestionUpdatedRecord() {
    }

    public QuestionUpdatedRecord(UpdatedType updatedType, String updaterId, OffsetDateTime updatedAt,
                                 String reason, String createdTitle, String createdDetail,
                                 String uneditedTitle, String editedTitle, String uneditedDetail,
                                 String editedDetail) {
        this.updateType = updatedType;
        this.updaterId = updaterId;
        this.updatedAt = updatedAt;
        this.reason = reason;
        this.createdTitle = createdTitle;
        this.createdDetail = createdDetail;
        this.uneditedTitle = uneditedTitle;
        this.editedTitle = editedTitle;
        this.uneditedDetail = uneditedDetail;
        this.editedDetail = editedDetail;
    }

    public UpdatedType getUpdateType() {
        return updateType;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getReason() {
        return reason;
    }

    public String getCreatedTitle() {
        return createdTitle;
    }

    public String getCreatedDetail() {
        return createdDetail;
    }

    public String getUneditedTitle() {
        return uneditedTitle;
    }

    public String getEditedTitle() {
        return editedTitle;
    }

    public String getUneditedDetail() {
        return uneditedDetail;
    }

    public String getEditedDetail() {
        return editedDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionUpdatedRecord that)) return false;
        return getUpdateType() == that.getUpdateType()
                && Objects.equals(getUpdaterId(), that.getUpdaterId())
                && Objects.equals(getUpdatedAt(), that.getUpdatedAt())
                && Objects.equals(getReason(), that.getReason())
                && Objects.equals(getCreatedTitle(), that.getCreatedTitle())
                && Objects.equals(getCreatedDetail(), that.getCreatedDetail())
                && Objects.equals(getUneditedTitle(), that.getUneditedTitle())
                && Objects.equals(getEditedTitle(), that.getEditedTitle())
                && Objects.equals(getUneditedDetail(), that.getUneditedDetail())
                && Objects.equals(getEditedDetail(), that.getEditedDetail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUpdateType(), getUpdaterId(), getUpdatedAt(), getReason(),
                getCreatedTitle(), getCreatedDetail(), getUneditedTitle(), getEditedTitle(),
                getUneditedDetail(), getEditedDetail());
    }

    public enum UpdatedType {
        CREATED, TITLE_EDITED, DETAIL_EDITED
    }
}
