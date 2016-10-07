package com.softserveinc.trainee.loader;

import java.io.BufferedReader;

public interface DataSource {

    public BufferedReader getReader(String filename);
}
