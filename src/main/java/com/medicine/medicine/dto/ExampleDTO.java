package com.medicine.medicine.dto;
public class ExampleDTO {
    private String answer;
    private String example_a;
    private String example_b;
    private String example_c;

    public ExampleDTO() {}

    public ExampleDTO(String answer, String example_a, String example_b, String example_c) {
        this.answer = answer;
        this.example_a = example_a;
        this.example_b = example_b;
        this.example_c = example_c;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getExample_a() {
        return example_a;
    }

    public void setExample_a(String example_a) {
        this.example_a = example_a;
    }

    public String getExample_b() {
        return example_b;
    }

    public void setExample_b(String example_b) {
        this.example_b = example_b;
    }

    public String getExample_c() {
        return example_c;
    }

    public void setExample_c(String example_c) {
        this.example_c = example_c;
    }
}