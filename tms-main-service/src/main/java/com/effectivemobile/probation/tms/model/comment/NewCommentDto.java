package com.effectivemobile.probation.tms.model.comment;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewCommentDto {
    @NotEmpty
    String text;
}
