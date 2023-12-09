package com.effectivemobile.probation.tms.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public record ErrorMessage(Date timestamp, int status, String error, String path) {
}
