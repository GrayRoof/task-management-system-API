package com.effectivemobile.probation.tms.model.comment;

import com.effectivemobile.probation.tms.model.user.NestedUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NestedCommentDto {
    private String text;
    private NestedUserDto author;
}
