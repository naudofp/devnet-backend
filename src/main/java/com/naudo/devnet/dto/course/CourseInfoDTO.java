package com.naudo.devnet.dto.course;

import java.util.List;

public record CourseInfoDTO(Long id, String nameCourse, Integer scoreCourse, List<String> nameStudents) {}
