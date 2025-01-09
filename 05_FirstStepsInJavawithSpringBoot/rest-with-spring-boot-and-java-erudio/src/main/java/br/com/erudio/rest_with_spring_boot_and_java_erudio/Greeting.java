package br.com.erudio.rest_with_spring_boot_and_java_erudio;

import org.springframework.web.bind.annotation.RequestMapping;

public class Greeting {
    private final long id;

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
