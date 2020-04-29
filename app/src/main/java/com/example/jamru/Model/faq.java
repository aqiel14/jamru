package com.example.jamru.Model;

public class faq {
    private String fquestion;
    private String fanswer;


    public faq(String fquestion, String fanswer) {
        this.fquestion = fquestion;
        this.fanswer = fanswer;
    }

    public String getFquestion() {
        return fquestion;
    }

    public void setFquestion(String fquestion) {
        this.fquestion = fquestion;
    }

    public String getFanswer() {
        return fanswer;
    }

    public void setFanswer(String fanswer) {
        this.fanswer = fanswer;
    }
}
