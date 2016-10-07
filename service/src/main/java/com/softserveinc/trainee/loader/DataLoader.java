package com.softserveinc.trainee.loader;

import com.softserveinc.trainee.entity.metadata.Entity;
import org.springframework.stereotype.Component;

import java.io.IOException;


public interface DataLoader {

    public void load(Entity entity) throws IOException;
}
