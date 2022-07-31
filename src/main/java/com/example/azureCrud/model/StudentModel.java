package com.example.azureCrud.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = StudentModel.StudentModelBuilder.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
public class StudentModel {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String docType;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class StudentModelBuilder{}
}
