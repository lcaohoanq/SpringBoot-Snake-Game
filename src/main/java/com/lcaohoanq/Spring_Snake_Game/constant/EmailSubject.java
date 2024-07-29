package com.lcaohoanq.Spring_Snake_Game.constant;

public class EmailSubject {

    public static final String subjectGreeting(String name) {
        return """
            Java Snake Game Corporation - Welcome %s, thanks for joining us!
            """.formatted(name);
    }

    public static final String subjectRunningApp() {
        return """
            Java Snake Game Corporation - Your app is running, Happy Coding! 
            """;
    }

}
