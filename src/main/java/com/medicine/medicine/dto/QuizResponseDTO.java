package com.medicine.medicine.dto;
public class QuizResponseDTO {
    private Long id;
    private String category;
    private String problem;
    private String explanation;
    private ExampleDTO example;

    public QuizResponseDTO() {}

    public QuizResponseDTO(Long id, String category, String problem, String explanation, ExampleDTO example) {
        this.id = id;
        this.category = category;
        this.problem = problem;
        this.explanation = explanation;
        this.example = example;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public ExampleDTO getExample() {
        return example;
    }

    public void setExample(ExampleDTO example) {
        this.example = example;
    }
}