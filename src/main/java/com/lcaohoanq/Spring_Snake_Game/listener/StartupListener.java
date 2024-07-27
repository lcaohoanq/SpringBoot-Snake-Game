package com.lcaohoanq.Spring_Snake_Game.listener;

import com.lcaohoanq.Spring_Snake_Game.util.LogUtils;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener {

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LogUtils.ensureLogsFolderExists();
    }

}
